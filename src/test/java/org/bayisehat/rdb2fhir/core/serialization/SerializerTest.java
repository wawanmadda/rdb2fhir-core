package org.bayisehat.rdb2fhir.core.serialization;

import ca.uhn.fhir.context.FhirContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.bayisehat.rdb2fhir.core.fhir.Conformance;
import org.bayisehat.rdb2fhir.core.fhir.HasConformance;
import org.bayisehat.rdb2fhir.core.valueservice.InstanceIdentifierFactory;
import org.bayisehat.rdb2fhir.core.valueservice.InstancePool;
import org.hl7.fhir.r4.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SerializerTest implements HasConformance {

    private Serializer serializer;

    private InstancePool instancePool;

    private Conformance conformance;

    @BeforeAll
    void setUpAll() throws IOException {
        conformance = getConformance();
    }

    @BeforeEach
    void setUp() throws Exception {
        instancePool = new InstancePool(new InstanceIdentifierFactory());
        StructureDefinition structureDefinition = conformance.getStructureDefinition("Patient");
        Patient patient = new Patient();
        patient.setActive(true);

        HashMap<String, String> keyValueList = new HashMap<>();
        keyValueList.put("col1", "val1");
        instancePool.putInstance(structureDefinition, "", keyValueList, patient);

        serializer = new Serializer(FhirContext.forR4().newJsonParser());

    }

    @Test
    void serializeOneResource() throws JsonProcessingException {
        String str = serializer.serialize(instancePool);
        assertEquals("{\"resourceType\":\"Patient\",\"active\":true}", str);
    }

    @Test
    void serializeBundle() throws Exception {
        //add other resource
        StructureDefinition structureDefinition = conformance.getStructureDefinition("Patient");
        Patient patient = new Patient();
        patient.setActive(true);

        HashMap<String, String> keyValueList = new HashMap<>();
        keyValueList.put("col1", "val2");
        instancePool.putInstance(structureDefinition, "", keyValueList, patient);

        String str = serializer.serialize(instancePool);
        assertEquals("{\"resourceType\":\"Bundle\",\"type\":\"collection\",\"entry\":[{\"resource\":{\"resourceType\":\"Patient\",\"active\":true}},{\"resource\":{\"resourceType\":\"Patient\",\"active\":true}}]}", str);
    }

    @Test
    void setRemovingArrayContainNullOnlyEnabled() throws Exception {

        HumanName hn = new HumanName()
                .setUse(HumanName.NameUse.OFFICIAL)
                .setFamilyElement(getAbsentStringType("masked"))
                .setGiven(new ArrayList<StringType>(Collections.singletonList(getAbsentStringType("masked"))));

        Patient patient = new Patient().addName(hn);

        instancePool = new InstancePool(new InstanceIdentifierFactory());
        StructureDefinition structureDefinition = conformance.getStructureDefinition("Patient");

        HashMap<String, String> keyValueList = new HashMap<>();
        keyValueList.put("col1", "val1");
        instancePool.putInstance(structureDefinition, "", keyValueList, patient);

        serializer = new Serializer(FhirContext.forR4().newJsonParser());
        String str = serializer.serialize(instancePool);
        assertEquals("{\"resourceType\":\"Patient\",\"name\":[{\"use\":\"official\",\"_family\":{\"extension\":[{\"url\":\"http://hl7.org/fhir/StructureDefinition/data-absent-reason\",\"valueCode\":\"masked\"}]},\"_given\":[{\"extension\":[{\"url\":\"http://hl7.org/fhir/StructureDefinition/data-absent-reason\",\"valueCode\":\"masked\"}]}]}]}", str);
    }

    @Test
    void setRemovingArrayContainNullOnlyEnabledPrettyPrintEnabled() throws Exception {

        HumanName hn = new HumanName()
                .setUse(HumanName.NameUse.OFFICIAL)
                .setFamilyElement(getAbsentStringType("masked"))
                .setGiven(new ArrayList<StringType>(Collections.singletonList(getAbsentStringType("masked"))));

        Patient patient = new Patient().addName(hn);

        instancePool = new InstancePool(new InstanceIdentifierFactory());
        StructureDefinition structureDefinition = conformance.getStructureDefinition("Patient");

        HashMap<String, String> keyValueList = new HashMap<>();
        keyValueList.put("col1", "val1");
        instancePool.putInstance(structureDefinition, "", keyValueList, patient);

        serializer = new Serializer(FhirContext.forR4().newJsonParser().setPrettyPrint(true));
        String str = serializer.serialize(instancePool);
        assertEquals("{\n" +
                "  \"resourceType\": \"Patient\",\n" +
                "  \"name\": [ {\n" +
                "    \"use\": \"official\",\n" +
                "    \"_family\": {\n" +
                "      \"extension\": [ {\n" +
                "        \"url\": \"http://hl7.org/fhir/StructureDefinition/data-absent-reason\",\n" +
                "        \"valueCode\": \"masked\"\n" +
                "      } ]\n" +
                "    },\n" +
                "    \"_given\": [ {\n" +
                "      \"extension\": [ {\n" +
                "        \"url\": \"http://hl7.org/fhir/StructureDefinition/data-absent-reason\",\n" +
                "        \"valueCode\": \"masked\"\n" +
                "      } ]\n" +
                "    } ]\n" +
                "  } ]\n" +
                "}", str);
    }

    @Test
    void setRemovingArrayContainNullOnlyDisabled() throws Exception {

        HumanName hn = new HumanName()
                .setUse(HumanName.NameUse.OFFICIAL)
                .setFamilyElement(getAbsentStringType("masked"))
                .setGiven(new ArrayList<StringType>(Collections.singletonList(getAbsentStringType("masked"))));

        Patient patient = new Patient().addName(hn);

        instancePool = new InstancePool(new InstanceIdentifierFactory());
        StructureDefinition structureDefinition = conformance.getStructureDefinition("Patient");

        HashMap<String, String> keyValueList = new HashMap<>();
        keyValueList.put("col1", "val1");
        instancePool.putInstance(structureDefinition, "", keyValueList, patient);

        serializer = new Serializer(FhirContext.forR4().newJsonParser());
        serializer.disableRemovingArrayContainOnlyNull();
        String str = serializer.serialize(instancePool);
        assertEquals("{\"resourceType\":\"Patient\",\"name\":[{\"use\":\"official\",\"_family\":{\"extension\":[{\"url\":\"http://hl7.org/fhir/StructureDefinition/data-absent-reason\",\"valueCode\":\"masked\"}]},\"given\":[null],\"_given\":[{\"extension\":[{\"url\":\"http://hl7.org/fhir/StructureDefinition/data-absent-reason\",\"valueCode\":\"masked\"}]}]}]}", str);
    }

    private static StringType getAbsentStringType(String reasonCode) {
        return (StringType) (new StringType()
                .addExtension(getDataAbsentExtension(reasonCode)));
    }

    private static Extension getDataAbsentExtension(String reasonCode) {
        return new Extension()
                .setUrl("http://hl7.org/fhir/StructureDefinition/data-absent-reason")
                .setValue(new CodeType().setValue(reasonCode));
    }

    @Test
    void testXML() throws JsonProcessingException {
        serializer.setParser(FhirContext.forR4().newXmlParser());
        assertEquals("<Patient xmlns=\"http://hl7.org/fhir\"><active value=\"true\"></active></Patient>", serializer.serialize(instancePool));
    }

    @Test
    void testRDF() throws JsonProcessingException {
        serializer.setParser(FhirContext.forR4().newRDFParser());
        assertEquals("@prefix fhir: <http://hl7.org/fhir/> .\n" +
                "@prefix rdf:  <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .\n" +
                "@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> .\n" +
                "@prefix sct:  <http://snomed.info/id#> .\n" +
                "@prefix xsd:  <http://www.w3.org/2001/XMLSchema#> .\n" +
                "\n" +
                "[ rdf:type             fhir:Patient ;\n" +
                "  fhir:Patient.active  [ fhir:value  true ] ;\n" +
                "  fhir:nodeRole        fhir:treeRoot\n" +
                "] .\n", serializer.serialize(instancePool));
    }

    @Test
    void emptyPool() throws JsonProcessingException {
        instancePool = new InstancePool(new InstanceIdentifierFactory());
        String result = serializer.serialize(instancePool);
        assertEquals("", result);
    }

    @Test
    void emptyBase() throws Exception {
        instancePool = new InstancePool(new InstanceIdentifierFactory());
        StructureDefinition structureDefinition = conformance.getStructureDefinition("Patient");

        HashMap<String, String> keyValueList = new HashMap<>();
        keyValueList.put("col1", "val1");

        instancePool.putInstance(structureDefinition, "", keyValueList, new Patient()); //empty resource

        String result = serializer.serialize(instancePool);
        assertEquals("{\"resourceType\":\"Patient\"}", result);
    }



}