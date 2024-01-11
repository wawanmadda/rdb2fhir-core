package org.bayisehat.rdb2fhir.core.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.support.DefaultProfileValidationSupport;
import ca.uhn.fhir.parser.IParser;
import org.bayisehat.rdb2fhir.core.util.HelperTool;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ConformanceTest implements HasConformance {

    private Conformance conformanceService;

    private FhirContext fhirContext;

    @BeforeAll
    void setup() throws IOException {
        FhirContext fhirContext = FhirContext.forR4();
        conformanceService = getConformance();
        this.fhirContext = fhirContext;
    }

    @Test
    void getValidationSupportByStructureDefinitionForBase() throws Exception {
        StructureDefinition structureDefinition = conformanceService.getStructureDefinition("Patient");
        assertTrue(conformanceService.getValidationSupportByStructureDefinition(structureDefinition) instanceof DefaultProfileValidationSupport);
    }

    @Test
    void getStructureDefinitionPatientByType() throws Exception {
        StructureDefinition structureDefinition = conformanceService.getStructureDefinition("Patient");
        assertEquals("Patient", structureDefinition.getType());
    }

    @Test
    void getStructureDefinitionPatientByURL() throws Exception {
        StructureDefinition structureDefinition = conformanceService.getStructureDefinition("http://hl7.org/fhir/StructureDefinition/Patient");
        assertEquals("Patient", structureDefinition.getType());
    }

    @Test
    void loadPackageFromClasspathExist() {
        assertDoesNotThrow(() -> conformanceService.loadPackageFromPath("us-core", "src/test/resources/package/us-core.tgz"));
    }

    @Test
    void loadPackageFromClasspathNotExist() {
        assertThrows(IOException.class, () -> conformanceService.loadPackageFromPath("us-core", "src/test/resources/package/not-exist.tgz"));
    }

    @Test
    void getStructureDefinitionPatientByURLFromPackage() throws Exception {
        conformanceService.loadPackageFromPath("us-core", "src/test/resources/package/us-core.tgz");
        StructureDefinition structureDefinition = conformanceService.getStructureDefinition("http://hl7.org/fhir/us/core/StructureDefinition/us-core-patient");
        assertEquals("http://hl7.org/fhir/us/core/StructureDefinition/us-core-patient", structureDefinition.getUrl());
    }


    @Test
    void getStructureDefinitionByGroupUScore() throws IOException, ConformanceException {
        conformanceService.loadPackageFromPath("us-core", "src/test/resources/package/us-core.tgz");
        assertNotNull(conformanceService.getStructureDefinitionByGroup("us-core"));
    }

    @Test
    void getStructureDefinitionByGroupNotExist() {
        assertThrows(ConformanceException.class, () -> conformanceService.getStructureDefinitionByGroup("not-exist"));
    }

    @Test
    void addStructureDefinitionPathNotExist() throws Exception {
        IParser parser = fhirContext.newJsonParser();
        assertThrows(IOException.class, () -> parser.parseResource(HelperTool.loadFile("src/test/resources/structuredefinition/not-exist.json")));
    }

    @Test
    void addStructureDefinitionPathExist() throws Exception {
        IParser parser = fhirContext.newJsonParser();
        StructureDefinition structureDefinition = (StructureDefinition) parser.parseResource(HelperTool.loadFile("src/test/resources/structuredefinition/StructureDefinition-NzPatient.json"));
        conformanceService.addStructureDefinition("nz", structureDefinition);
        assertDoesNotThrow(() -> conformanceService.getStructureDefinition("http://hl7.org.nz/fhir/StructureDefinition/NzPatient"));
    }

    @Test
    void addStructureDefinitionPathExist2() throws Exception {
        IParser parser = fhirContext.newJsonParser();
        StructureDefinition structureDefinition = (StructureDefinition) parser.parseResource(HelperTool.loadFile("src/test/resources/structuredefinition/StructureDefinition-NzPatient.json"));
        StructureDefinition structureDefinition2 = (StructureDefinition) parser.parseResource(HelperTool.loadFile("src/test/resources/structuredefinition/StructureDefinition-NzPractitioner.json"));
        conformanceService.addStructureDefinition("nz", structureDefinition);
        conformanceService.addStructureDefinition("nz", structureDefinition2);
        assertEquals(2, conformanceService.getStructureDefinitionByGroup("nz").size());
    }

    @Test
    void addStructureDefinitionExceptionAddToBase() throws Exception {
        IParser parser = fhirContext.newJsonParser();
        StructureDefinition structureDefinition = (StructureDefinition) parser.parseResource(HelperTool.loadFile("src/test/resources/structuredefinition/StructureDefinition-NzPatient.json"));
        assertThrows(Exception.class, () -> conformanceService.addStructureDefinition("base", structureDefinition));
    }

    @Test
    void addStructureDefinitionToPackage() throws Exception {
        conformanceService.loadPackageFromPath("us-core", "src/test/resources/package/us-core.tgz");

        IParser parser = fhirContext.newJsonParser();
        StructureDefinition structureDefinition = (StructureDefinition) parser.parseResource(HelperTool.loadFile("src/test/resources/structuredefinition/StructureDefinition-NzPatient.json"));
        assertDoesNotThrow(() -> conformanceService.addStructureDefinition("us-core", structureDefinition));
    }



}