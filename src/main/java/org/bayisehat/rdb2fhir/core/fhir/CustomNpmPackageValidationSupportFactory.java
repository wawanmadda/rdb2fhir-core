package org.bayisehat.rdb2fhir.core.fhir;

import ca.uhn.fhir.context.FhirContext;


public class CustomNpmPackageValidationSupportFactory {

    private final FhirContext fhirContext;

    public CustomNpmPackageValidationSupportFactory(FhirContext fhirContext) {
        this.fhirContext = fhirContext;
    }

    public CustomNpmPackageValidationSupport createInstance() {
        return new CustomNpmPackageValidationSupport(fhirContext);
    }
}
