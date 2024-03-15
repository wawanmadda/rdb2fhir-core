package org.bayisehat.rdb2fhir.core;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bayisehat.rdb2fhir.core.databasefetcher.ConnectionService;
import org.bayisehat.rdb2fhir.core.databasefetcher.DatabaseFetcher;
import org.bayisehat.rdb2fhir.core.fetcher.Fetchable;
import org.bayisehat.rdb2fhir.core.fetcher.ResultQuadrupleFactory;
import org.bayisehat.rdb2fhir.core.fhir.Conformance;
import org.bayisehat.rdb2fhir.core.serialization.Serializer;
import org.bayisehat.rdb2fhir.core.validation.CachingValidationSupportWrapper;
import org.bayisehat.rdb2fhir.core.validation.ValidatorService;
import org.hl7.fhir.common.hapi.validation.support.CachingValidationSupport;
import org.hl7.fhir.common.hapi.validation.support.CommonCodeSystemsTerminologyService;
import org.hl7.fhir.common.hapi.validation.support.ValidationSupportChain;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.HumanName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestTest {


    @Test
    void test() throws IOException {
//        FhirContext fhirContext = FhirContext.forR4();
//        Conformance conformance = new Conformance(fhirContext);
//
//        ConnectionService connectionService = new ConnectionService("jdbcdriver", "jdbcurl", "username", "pass");
//        Fetchable databaseFetcher = new DatabaseFetcher(connectionService, new ResultQuadrupleFactory());
//
//        RDB2FHIR rdb2FHIR = RDB2FHIRFactory.getInstance(conformance, databaseFetcher, fhirContext);
//
//        Serializer xmlSerializer = new Serializer(fhirContext.newXmlParser());
//        RDB2FHIR rdb2FHIR = RDB2FHIRFactory.getInstance(conformance, databaseFetcher, xmlSerializer, fhirContext);
//        xmlSerializer.disableRemovingArrayContainOnlyNull();
//
//        rdb2FHIR.enableValidator();
//
//        rdb2FHIR.setOutputFormat(RDB2FHIR.OutputFormat.JSON);
//
//        CachingValidationSupportWrapper cachingValidationSupportWrapper = new CachingValidationSupportWrapper(new ValidationSupportChain(), fhirContext);
//        cachingValidationSupportWrapper.addValidationSupport(conformance.getDefaultProfileValidationSupport());
//        cachingValidationSupportWrapper.addValidationSupport(new CommonCodeSystemsTerminologyService(fhirContext));
//        cachingValidationSupportWrapper.buildCache();
//
//        ValidatorService validatorService =  new ValidatorService(conformance,
//                        cachingValidationSupportWrapper);
//
//        RDB2FHIRFactory.getInstance(conformance, fetcher, fhirContext, new ObjectMapper(), serializer, validatorService);
//
//        IParser parser = fhirContext.newJsonParser();
//        parser.setPrettyPrint(false);
//        parser.setStripVersionsFromReferences(true);
//        parser.setSuppressNarratives(false);
//        parser.setSummaryMode(true);

        FhirContext fhirContext = FhirContext.forR4();
        IParser parser = fhirContext.newJsonParser();

        HumanName humanName = new HumanName();
        humanName.addGiven("hahaha");
        String a = parser.encodeResourceToString((IBaseResource) humanName);
    }
}
