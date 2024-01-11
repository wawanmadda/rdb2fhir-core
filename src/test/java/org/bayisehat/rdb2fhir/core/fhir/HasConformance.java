package org.bayisehat.rdb2fhir.core.fhir;

import ca.uhn.fhir.context.FhirContext;

import java.io.IOException;

public interface HasConformance {

    default Conformance getConformance() throws IOException {
        return  new Conformance(FhirContext.forR4());
    }

    default Conformance getConformance(FhirContext fhirContext) throws IOException {
        return  new Conformance(fhirContext);
    }
}
