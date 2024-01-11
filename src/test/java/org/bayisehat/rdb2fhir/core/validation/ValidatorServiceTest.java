package org.bayisehat.rdb2fhir.core.validation;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.validation.SingleValidationMessage;
import org.bayisehat.rdb2fhir.core.fhir.Conformance;
import org.bayisehat.rdb2fhir.core.fhir.HasConformance;
import org.bayisehat.rdb2fhir.core.fhir.HasUSCore;
import org.bayisehat.rdb2fhir.core.valueservice.InstanceIdentifier;
import org.bayisehat.rdb2fhir.core.valueservice.InstanceIdentifierFactory;
import org.bayisehat.rdb2fhir.core.valueservice.InstancePool;
import org.hl7.fhir.common.hapi.validation.support.CommonCodeSystemsTerminologyService;
import org.hl7.fhir.common.hapi.validation.support.ValidationSupportChain;
import org.hl7.fhir.r4.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ValidatorServiceTest implements HasConformance, HasUSCore {

    private ValidatorService validatorService;

    private InstancePool instancePool;

    private Conformance conformance;

    private InstanceIdentifierFactory instanceIdentifierFactory;

    @BeforeAll
    void setUpAll() throws IOException {
        conformance = getConformance();
        validatorService = new ValidatorService(conformance,
                new CachingValidationSupportWrapper(new ValidationSupportChain(),FhirContext.forR4()),
                new CommonCodeSystemsTerminologyService(FhirContext.forR4()));

        instanceIdentifierFactory = new InstanceIdentifierFactory();

    }

    @BeforeEach
    void setUp() {
        instancePool = new InstancePool(instanceIdentifierFactory);
    }

    @Test
    void validateNoMessage() throws Exception {
        StructureDefinition structureDefinition = conformance.getStructureDefinition("Patient");

        HashMap<String, String> keyValue = new HashMap<>();
        keyValue.put("col1", "1");
        InstanceIdentifier instanceIdentifier = instanceIdentifierFactory.create(keyValue);
        Patient patient = new Patient();
        patient.setActive(true);
        instancePool.putInstance(structureDefinition, "", instanceIdentifier, patient);

        List<List<SingleValidationMessage>> result = validatorService.validate(instancePool);
        assertEquals(0, result.size());
    }

    @Test
    void validateProduceMessage() throws Exception {
        StructureDefinition structureDefinition = conformance.getStructureDefinition("Observation");

        HashMap<String, String> keyValue = new HashMap<>();
        keyValue.put("col1", "1");
        InstanceIdentifier instanceIdentifier = instanceIdentifierFactory.create(keyValue);
        Observation observation = new Observation();
        instancePool.putInstance(structureDefinition, "", instanceIdentifier, observation);

        //will failed since Observation.status at least 1, 0 provided
        List<List<SingleValidationMessage>> result = validatorService.validate(instancePool);
        assertEquals(1, result.size()); //one instance failed
    }

    @Test
    void validateMultipleProduceMessage() throws Exception {
        StructureDefinition structureDefinition = conformance.getStructureDefinition("Observation");

        HashMap<String, String> keyValue1 = new HashMap<>();
        keyValue1.put("col1", "1");
        InstanceIdentifier instanceIdentifier1 = instanceIdentifierFactory.create(keyValue1);
        Patient patient = new Patient();
        patient.setActive(true);
        instancePool.putInstance(structureDefinition, "", instanceIdentifier1, patient);

        HashMap<String, String> keyValue2 = new HashMap<>();
        keyValue2.put("col1", "1");
        InstanceIdentifier instanceIdentifier2 = instanceIdentifierFactory.create(keyValue2);
        Observation observation = new Observation();
        instancePool.putInstance(structureDefinition, "", instanceIdentifier2, observation);

        HashMap<String, String> keyValue3 = new HashMap<>();
        keyValue3.put("col1", "2");
        InstanceIdentifier instanceIdentifier3 = instanceIdentifierFactory.create(keyValue3);
        Observation observation2 = new Observation();
        instancePool.putInstance(structureDefinition, "", instanceIdentifier3, observation2);

        //will failed since Observation.status at least 1, 0 provided
        List<List<SingleValidationMessage>> result = validatorService.validate(instancePool);
        assertEquals(2, result.size()); //two instance failed, one passed
    }

    @Test
    void validateProfileUSCore() throws Exception {
        conformance.loadPackageFromPath("us-core", "src/test/resources/package/us-core.tgz");

        StructureDefinition structureDefinition = conformance.getStructureDefinition("http://hl7.org/fhir/us/core/StructureDefinition/us-core-patient");

        HashMap<String, String> keyValue = new HashMap<>();
        keyValue.put("col1", "1");
        InstanceIdentifier instanceIdentifier = instanceIdentifierFactory.create(keyValue);
        Patient patient = new Patient();
        instancePool.putInstance(structureDefinition, "", instanceIdentifier, patient);

        List<List<SingleValidationMessage>> result = validatorService.validate(instancePool);
        assertEquals(3, result.get(0).size()); //3 validation messages
        assertEquals(0, patient.getMeta().getProfile().size()); // meta profile removed
    }


}