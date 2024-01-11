package org.bayisehat.rdb2fhir.core.valueservice;

import org.bayisehat.rdb2fhir.core.fhir.Conformance;
import org.bayisehat.rdb2fhir.core.fhir.HasConformance;
import org.hl7.fhir.r4.model.Base;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InstancePoolTest implements HasConformance {

    private InstancePool instancePool;

    private InstanceIdentifierFactory instanceIdentifierFactory = new InstanceIdentifierFactory();


    private StructureDefinition structureDefinition;

    @BeforeAll
    void init() throws Exception {
        Conformance conformance = getConformance();
        structureDefinition = conformance.getStructureDefinition("Patient");
    }

    @BeforeEach
    void setUp() {
        instancePool = new InstancePool(instanceIdentifierFactory);
    }

    @Test
    void putInstance() {
        HashMap<String, String> columnToValue = new HashMap<>();
        columnToValue.put("col1", "1");
        columnToValue.put("col2", "1");
        instancePool.putInstance(structureDefinition, "", columnToValue, new Patient());
        assertEquals(1, instancePool.getPool().get(structureDefinition).get("").size());
    }

    @Test
    void putInstanceMultiple() {
        HashMap<String, String> columnToValue = new HashMap<>();
        columnToValue.put("col1", "1");
        columnToValue.put("col2", "1");
        instancePool.putInstance(structureDefinition, "", columnToValue, new Patient());

        HashMap<String, String> columnToValue2 = new HashMap<>();
        columnToValue2.put("col1", "1");
        columnToValue2.put("col2", "2");
        instancePool.putInstance(structureDefinition, "", columnToValue2, new Patient());

        assertEquals(2, instancePool.getPool().get(structureDefinition).get("").size());
    }

    @Test
    void putInstanceOverwrite() throws Exception {
        HashMap<String, String> columnToValue = new HashMap<>();
        columnToValue.put("col1", "1");
        columnToValue.put("col2", "1");
        instancePool.putInstance(structureDefinition, "", columnToValue, new Patient());

        HashMap<String, String> columnToValue2 = new HashMap<>();
        columnToValue2.put("col1", "1");
        columnToValue2.put("col2", "1");
        instancePool.putInstance(structureDefinition, "", columnToValue, new Patient());

        assertEquals(1, instancePool.getPool().get(structureDefinition).get("").size());
    }


    @Test
    void createNotExist() throws Exception {
        HashMap<String, String> columnToValue = new HashMap<>();
        columnToValue.put("col1", "1");
        columnToValue.put("col2", "1");
        Base base = instancePool.createIfNotExist(structureDefinition, "", columnToValue);
        assertSame(base, instancePool.getPool().get(structureDefinition).get("").get(instanceIdentifierFactory.create(columnToValue)));
    }

    @Test
    void createNotExistMultiple() throws Exception {
        HashMap<String, String> columnToValue = new HashMap<>();
        columnToValue.put("col1", "1");
        columnToValue.put("col2", "1");
        Base base1 = instancePool.createIfNotExist(structureDefinition, "", columnToValue);

        HashMap<String, String> columnToValue2 = new HashMap<>();
        columnToValue2.put("col1", "1");
        columnToValue2.put("col2", "2");
        Base base2 = instancePool.createIfNotExist(structureDefinition, "", columnToValue2);
        assertNotSame(base1, base2);
    }

    @Test
    void createExist() throws Exception {
        HashMap<String, String> columnToValue = new HashMap<>();
        columnToValue.put("col1", "1");
        columnToValue.put("col2", "1");
        Base base1 = instancePool.createIfNotExist(structureDefinition, "", columnToValue);

        HashMap<String, String> columnToValue2 = new HashMap<>();
        columnToValue2.put("col1", "1");
        columnToValue2.put("col2", "1");
        Base base2 = instancePool.createIfNotExist(structureDefinition, "", columnToValue2);

        assertSame(base1, base2);

    }

    @Test
    void getInstance() {

    }

    @Test
    void getBox() {
    }
}