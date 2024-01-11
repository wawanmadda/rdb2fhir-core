package org.bayisehat.rdb2fhir.core.valueservice;

import ca.uhn.fhir.context.FhirContext;
import org.bayisehat.rdb2fhir.core.fhir.*;
import org.bayisehat.rdb2fhir.core.rdb2ol.IdentifierFactory;
import org.bayisehat.rdb2fhir.core.rdb2ol.MappingItemFactory;
import org.bayisehat.rdb2fhir.core.rdb2ol.PathServiceFactory;
import org.bayisehat.rdb2fhir.core.rdb2ol.Quadruple;
import org.hl7.fhir.r4.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AllocatorTest implements HasConformance {

    private Conformance conformance;

    private Allocator allocator;

    private InstancePool instancePool;

    private KeyValueWrapperFactory keyValueWrapperFactory;

    private Quadruple quadruple;

    private HashMap<String, Object> rowResult;

    private InstanceIdentifier instanceIdentifier;

    HashMap<String, String> pathToValue;

    @BeforeAll
    void setUpAll() throws IOException {
        allocator = new Allocator(new NameServiceFactory());
        keyValueWrapperFactory = new KeyValueWrapperFactory();

        FhirContext fhirContext = FhirContext.forR4();
        conformance = getConformance();
    }

    @BeforeEach
    void setUp() throws Exception {
        instancePool = new InstancePoolFactory(new InstanceIdentifierFactory()).create();

        quadruple = new Quadruple(conformance, new IdentifierFactory(), new MappingItemFactory(), new NameServiceFactory(), new PathServiceFactory());
        quadruple.setClass("Patient");
        quadruple.addIdentifier("Patient", new HashSet<>(Arrays.asList("col1")));

        rowResult = new HashMap<>();
        rowResult.put("col1", "v_col1");
        rowResult.put("col2", "v_col2");
        rowResult.put("col3", "v_col3");
        rowResult.put("col4", "v_col4");
        rowResult.put("col5", "v_col5");

        HashMap<String, String> keyValueIdentifier = new HashMap<>();
        keyValueIdentifier.put("col1", "v_col1");

        instanceIdentifier = new InstanceIdentifier(keyValueIdentifier);

        pathToValue = new HashMap<>();

    }

    @Test
    void fillSingle() throws Exception {
        pathToValue.put("active", "true");
        KeyValueWrapper keyValueWrapper = keyValueWrapperFactory.create(quadruple, rowResult, pathToValue);
        allocator.fill(instancePool, keyValueWrapper);

        Base base = instancePool.getInstance(conformance.getStructureDefinition("Patient"), "", instanceIdentifier);
        assertTrue(((Patient) base).getActive());
    }

    @Test
    void fillSingleOverwritingSameValue() throws Exception {
        pathToValue.put("active", "true");
        KeyValueWrapper keyValueWrapper = keyValueWrapperFactory.create(quadruple, rowResult, pathToValue);
        allocator.fill(instancePool, keyValueWrapper);
        allocator.fill(instancePool, keyValueWrapper);

        Base base = instancePool.getInstance(conformance.getStructureDefinition("Patient"), "", instanceIdentifier);
        assertTrue(((Patient) base).getActive());
    }

    @Test
    void fillSingleExceptionOverwritingDifferentValue() throws Exception {
        pathToValue.put("active", "true");
        KeyValueWrapper keyValueWrapper = keyValueWrapperFactory.create(quadruple, rowResult, pathToValue);
        allocator.fill(instancePool, keyValueWrapper);

        pathToValue.put("active", "false");
        assertThrows(Exception.class, () -> allocator.fill(instancePool, keyValueWrapper));
    }

    @Test
    void fillList() throws Exception {
        pathToValue.put("name.text", "v_col1");
        KeyValueWrapper keyValueWrapper = keyValueWrapperFactory.create(quadruple, rowResult, pathToValue);
        allocator.fill(instancePool, keyValueWrapper);

        Base base = instancePool.getInstance(conformance.getStructureDefinition("Patient"), "", instanceIdentifier);
        assertEquals("v_col1", ((Patient) base).getName().get(0).getText());
    }

    @Test
    void fillSlice() throws Exception {
        pathToValue.put("name:slice1.text", "v_col1");
        KeyValueWrapper keyValueWrapper = keyValueWrapperFactory.create(quadruple, rowResult, pathToValue);
        allocator.fill(instancePool, keyValueWrapper);

        Base base = instancePool.getInstance(conformance.getStructureDefinition("Patient"), "", instanceIdentifier);
        assertEquals("v_col1", ((Patient) base).getName().get(0).getText());
    }

    @Test
    void fillMultipleList() throws Exception {
        pathToValue.put("name.given", "v_col1");
        KeyValueWrapper keyValueWrapper = keyValueWrapperFactory.create(quadruple, rowResult, pathToValue);
        allocator.fill(instancePool, keyValueWrapper);

        Base base = instancePool.getInstance(conformance.getStructureDefinition("Patient"), "", instanceIdentifier);
        assertEquals("v_col1", ((Patient) base).getName().get(0).getGiven().get(0).toString());
    }

    @Test
    void fillListMultipleSameRow() throws Exception {
        pathToValue.put("name.text", "v_col1");
        pathToValue.put("name.family", "v_col2");
        KeyValueWrapper keyValueWrapper = keyValueWrapperFactory.create(quadruple, rowResult, pathToValue);
        allocator.fill(instancePool, keyValueWrapper);

        Base base = instancePool.getInstance(conformance.getStructureDefinition("Patient"), "", instanceIdentifier);
        assertEquals("v_col1", ((Patient) base).getName().get(0).getText());
        assertEquals("v_col2", ((Patient) base).getName().get(0).getFamily());
    }

    @Test
    void fillListAppendValues() throws Exception {
        pathToValue.put("name.text", "v_col1");
        KeyValueWrapper keyValueWrapper = keyValueWrapperFactory.create(quadruple, rowResult, pathToValue);
        allocator.fill(instancePool, keyValueWrapper);

        pathToValue = new HashMap<>();
        pathToValue.put("name.text", "v_col2");
        KeyValueWrapper keyValueWrapper2 = keyValueWrapperFactory.create(quadruple, rowResult, pathToValue);
        allocator.fill(instancePool, keyValueWrapper2);

        Base base = instancePool.getInstance(conformance.getStructureDefinition("Patient"), "", instanceIdentifier);
        assertEquals(2, ((Patient) base).getName().size());
        assertEquals("v_col1", ((Patient) base).getName().get(0).getText());
        assertEquals("v_col2", ((Patient) base).getName().get(1).getText());
    }

    @Test
    void fillListIndexed() throws Exception {
        pathToValue.put("name[3].text", "v_col1");
        KeyValueWrapper keyValueWrapper = keyValueWrapperFactory.create(quadruple, rowResult, pathToValue);
        allocator.fill(instancePool, keyValueWrapper);

        Base base = instancePool.getInstance(conformance.getStructureDefinition("Patient"), "", instanceIdentifier);
        assertEquals("v_col1", ((Patient) base).getName().get(3).getText());
    }

    @Test
    void fillListIndexedFromDifferentRow() throws Exception {
        pathToValue.put("name[3].text", "v_col1");
        KeyValueWrapper keyValueWrapper = keyValueWrapperFactory.create(quadruple, rowResult, pathToValue);
        allocator.fill(instancePool, keyValueWrapper);

        pathToValue = new HashMap<>();
        pathToValue.put("name[3].family", "v_col2");
        KeyValueWrapper keyValueWrapper2 = keyValueWrapperFactory.create(quadruple, rowResult, pathToValue);
        allocator.fill(instancePool, keyValueWrapper2);

        Base base = instancePool.getInstance(conformance.getStructureDefinition("Patient"), "", instanceIdentifier);
        assertEquals("v_col1", ((Patient) base).getName().get(3).getText());
        assertEquals("v_col2", ((Patient) base).getName().get(3).getFamily());
    }

    @Test
    void fillContained() throws Exception {
        pathToValue.put("contained{Patient}.active", "true");
        KeyValueWrapper keyValueWrapper = keyValueWrapperFactory.create(quadruple, rowResult, pathToValue);
        allocator.fill(instancePool, keyValueWrapper);

        Base base = instancePool.getInstance(conformance.getStructureDefinition("Patient"), "", instanceIdentifier);
        Patient patient = (Patient) ((Patient) base).getContained().get(0);
        assertTrue(patient.getActive());
    }

    @Test
    void fillContainedIndexed() throws Exception {
        pathToValue.put("contained{Patient}[3].active", "true");
        KeyValueWrapper keyValueWrapper = keyValueWrapperFactory.create(quadruple, rowResult, pathToValue);
        allocator.fill(instancePool, keyValueWrapper);

        Base base = instancePool.getInstance(conformance.getStructureDefinition("Patient"), "", instanceIdentifier);
        Patient patient = (Patient) ((Patient) base).getContained().get(3);
        assertTrue(patient.getActive());
    }

    @Test
    void fillOpenType() throws Exception {
        pathToValue.put("multipleBirth{boolean}", "true");
        KeyValueWrapper keyValueWrapper = keyValueWrapperFactory.create(quadruple, rowResult, pathToValue);
        allocator.fill(instancePool, keyValueWrapper);

        Base base = instancePool.getInstance(conformance.getStructureDefinition("Patient"), "", instanceIdentifier);
        assertTrue(((Patient) base).getMultipleBirthBooleanType().booleanValue());
    }

    @Test
    void fillOpenType2() throws Exception {
        pathToValue.put("multipleBirth{integer}", "1");
        KeyValueWrapper keyValueWrapper = keyValueWrapperFactory.create(quadruple, rowResult, pathToValue);
        allocator.fill(instancePool, keyValueWrapper);

        Base base = instancePool.getInstance(conformance.getStructureDefinition("Patient"), "", instanceIdentifier);
        assertEquals("1", ((Patient) base).getMultipleBirth().primitiveValue());
    }

    @Test
    void fillExtensionOpenType() throws Exception {
        pathToValue.put("extension.value{HumanName}.text", "v_col1");
        KeyValueWrapper keyValueWrapper = keyValueWrapperFactory.create(quadruple, rowResult, pathToValue);
        allocator.fill(instancePool, keyValueWrapper);

        Base base = instancePool.getInstance(conformance.getStructureDefinition("Patient"), "", instanceIdentifier);
        assertEquals("v_col1", ((HumanName)((Patient) base).getExtension().get(0).getValue()).getText());
    }

    @Test
    void fillExtensionDeepLevel() throws Exception {
        pathToValue.put("extension.extension[3].extension.url", "v_col1");
        KeyValueWrapper keyValueWrapper = keyValueWrapperFactory.create(quadruple, rowResult, pathToValue);
        allocator.fill(instancePool, keyValueWrapper);

        Base base = instancePool.getInstance(conformance.getStructureDefinition("Patient"), "", instanceIdentifier);
        assertEquals("v_col1", ((DomainResource) base).getExtension().get(0).getExtension().get(3).getExtension().get(0).getUrl());
    }

    @Test
    void fillListMultipleIdentifier() throws Exception {
        quadruple.addIdentifier("Patient.name", new HashSet<>(Arrays.asList("col2")));

        pathToValue.put("name.given", "aaa");
        pathToValue.put("name.text", "taaa");
        KeyValueWrapper keyValueWrapper = keyValueWrapperFactory.create(quadruple, rowResult, pathToValue);
        allocator.fill(instancePool, keyValueWrapper);

        HashMap<String, Object> rowResult2 = new HashMap<>(rowResult);
        pathToValue = new HashMap<>();
        pathToValue.put("name.given", "bbb");
        KeyValueWrapper keyValueWrapper2 = keyValueWrapperFactory.create(quadruple, rowResult2, pathToValue);
        allocator.fill(instancePool, keyValueWrapper2);

        Base base = instancePool.getInstance(conformance.getStructureDefinition("Patient"), "", instanceIdentifier);
        assertEquals("aaa", ((Patient) base).getName().get(0).getGiven().get(0).toString());
        assertEquals("taaa", ((Patient) base).getName().get(0).getText());
        assertEquals("bbb", ((Patient) base).getName().get(0).getGiven().get(1).toString());
    }




}