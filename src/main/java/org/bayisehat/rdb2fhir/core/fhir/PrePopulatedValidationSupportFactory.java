package org.bayisehat.rdb2fhir.core.fhir;

import ca.uhn.fhir.context.FhirContext;
import org.hl7.fhir.common.hapi.validation.support.PrePopulatedValidationSupport;

public class PrePopulatedValidationSupportFactory {

    private final FhirContext fhirContext;

    public PrePopulatedValidationSupportFactory(FhirContext fhirContext) {
        this.fhirContext = fhirContext;
    }

    public PrePopulatedValidationSupport createInstance() {
        return new PrePopulatedValidationSupport(fhirContext);
    }
}
