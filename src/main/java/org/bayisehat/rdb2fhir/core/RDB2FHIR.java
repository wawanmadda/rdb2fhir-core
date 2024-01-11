package org.bayisehat.rdb2fhir.core;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.validation.SingleValidationMessage;
import org.bayisehat.rdb2fhir.core.fetcher.Fetchable;
import org.bayisehat.rdb2fhir.core.fetcher.ResultQuadruple;
import org.bayisehat.rdb2fhir.core.fhir.Conformance;
import org.bayisehat.rdb2fhir.core.rdb2ol.*;
import org.bayisehat.rdb2fhir.core.serialization.Serializer;
import org.bayisehat.rdb2fhir.core.validation.ValidatorService;
import org.bayisehat.rdb2fhir.core.valueservice.*;

import java.util.ArrayList;
import java.util.List;

public class RDB2FHIR {

    private Conformance conformance;

    private Fetchable fetcher;

    private Rdb2olFactory rdb2olFactory;

    private ValueManager valueManager;

    private ValidatorService validatorService;

    private Serializer serializer;

    private FhirContext fhirContext;

    private boolean validatorEnabled = false;

    private List<List<SingleValidationMessage>> validationMessageList;

    public enum OutputFormat {
        JSON, RDF, XML
    }


    public RDB2FHIR(Conformance conformance, Fetchable fetcher, Rdb2olFactory rdb2olFactory, ValueManager valueManager,
                    FhirContext fhirContext, Serializer serializer, ValidatorService validatorService) {
        this.conformance = conformance;
        this.fetcher = fetcher;
        this.rdb2olFactory = rdb2olFactory;
        this.valueManager = valueManager;
        this.fhirContext = fhirContext;
        this.serializer = serializer;
        this.validatorService = validatorService;
    }


    public String transform(String rdb2olStr) throws Exception {
        Rdb2ol rdb2ol = rdb2olFactory.create(rdb2olStr);
        ArrayList<ResultQuadruple> resultQuadrupleList = fetcher.fetch(rdb2ol);
        InstancePool instancePool = valueManager.transform(resultQuadrupleList);

        if (validatorEnabled) {
            validationMessageList = validatorService.validate(instancePool);
        }

        return serializer.serialize(instancePool);
    }

    /**
     * Get validation messages
     * */
    public List<List<SingleValidationMessage>> getMessage() {
        return validationMessageList;
    }

    /**
     * Enable to perform validation against resource instances
     * */
    public void enableValidator() {
        validatorEnabled = true;
    }

    /**
     * Disable validation against resource instances
     * */
    public void disableValidator() {
        validatorEnabled = false;
    }

    /**
     * Choose output generated for resource instances.
     * */
    public void setOutputFormat(OutputFormat format) {
        switch (format) {
            case RDF -> serializer.setParser(fhirContext.newRDFParser());
            case XML -> serializer.setParser(fhirContext.newXmlParser());
            default -> serializer.setParser(fhirContext.newJsonParser());
        }
    }

}
