package org.bayisehat.rdb2fhir.core;

import ca.uhn.fhir.context.FhirContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bayisehat.rdb2fhir.core.fetcher.Fetchable;
import org.bayisehat.rdb2fhir.core.fhir.Conformance;
import org.bayisehat.rdb2fhir.core.fhir.NameServiceFactory;
import org.bayisehat.rdb2fhir.core.rdb2ol.*;
import org.bayisehat.rdb2fhir.core.serialization.Serializer;
import org.bayisehat.rdb2fhir.core.validation.CachingValidationSupportWrapper;
import org.bayisehat.rdb2fhir.core.validation.ValidatorService;
import org.bayisehat.rdb2fhir.core.valueservice.*;
import org.bayisehat.rdb2fhir.core.valueservice.jython.PyInterpreter;
import org.hl7.fhir.common.hapi.validation.support.CommonCodeSystemsTerminologyService;
import org.hl7.fhir.common.hapi.validation.support.ValidationSupportChain;
import org.python.util.PythonInterpreter;

import java.io.IOException;

public class RDB2FHIRFactory {

    public static RDB2FHIR getInstance(Conformance conformance, Fetchable fetcher, FhirContext fhirContext,
                                       ObjectMapper objectMapper, Serializer serializer, ValidatorService validatorService) throws IOException {
        return new RDB2FHIR(conformance, fetcher,
                new Rdb2olFactory(new QuadrupleFactory(conformance), objectMapper),
                new ValueManager(new PyInterpreter(new PythonInterpreter()),
                        new Allocator(new NameServiceFactory()),
                        new InstancePoolFactory(new InstanceIdentifierFactory()), new KeyValueWrapperFactory()),
                fhirContext,
                serializer,
                validatorService
                );
    }


    public static RDB2FHIR getInstance(Conformance conformance, Fetchable fetcher, FhirContext fhirContext,
                                       ObjectMapper objectMapper, Serializer serializer) throws IOException {
        return getInstance(conformance, fetcher, fhirContext, objectMapper, serializer,
                new ValidatorService(conformance,
                        new CachingValidationSupportWrapper(new ValidationSupportChain(), fhirContext),
                        new CommonCodeSystemsTerminologyService(fhirContext))
                );
    }

    public static RDB2FHIR getInstance(Conformance conformance, Fetchable fetcher, FhirContext fhirContext,
                                       ObjectMapper objectMapper) throws IOException {
        return getInstance(conformance, fetcher, fhirContext, objectMapper,
                new Serializer(fhirContext.newJsonParser().setStripVersionsFromReferences(false))
                );

    }

    public static RDB2FHIR getInstance(Conformance conformance, Fetchable fetcher, Serializer serializer, FhirContext fhirContext,
                                       ObjectMapper objectMapper) throws IOException {
        return getInstance(conformance, fetcher, fhirContext, objectMapper, serializer);
    }

    public static RDB2FHIR getInstance(Conformance conformance, Fetchable fetcher, Serializer serializer, FhirContext fhirContext) throws IOException {
        return getInstance(conformance, fetcher, fhirContext, new ObjectMapper(), serializer);
    }

    public static RDB2FHIR getInstance(Conformance conformance, Fetchable fetcher, FhirContext fhirContext) throws IOException {
        return getInstance(conformance, fetcher, fhirContext, new ObjectMapper());
    }

    public static RDB2FHIR getInstance(Fetchable fetcher, FhirContext fhirContext) throws IOException {
        return getInstance(new Conformance(fhirContext), fetcher, fhirContext);
    }


}
