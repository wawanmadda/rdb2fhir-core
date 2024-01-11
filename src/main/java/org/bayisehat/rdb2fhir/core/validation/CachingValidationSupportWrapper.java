package org.bayisehat.rdb2fhir.core.validation;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.support.IValidationSupport;
import ca.uhn.fhir.validation.FhirValidator;
import org.hl7.fhir.common.hapi.validation.support.CachingValidationSupport;
import org.hl7.fhir.common.hapi.validation.support.ValidationSupportChain;
import org.hl7.fhir.common.hapi.validation.validator.FhirInstanceValidator;

/**
 * This class provides interface to get cached FhirValidator by configuring CachingValidation Validation Support
 * to improve performance when validating resources
 * */
public class CachingValidationSupportWrapper {

    private final ValidationSupportChain validationSupportChain;

    private final FhirContext fhirContext;

    private FhirValidator fhirValidator;

    public CachingValidationSupportWrapper(ValidationSupportChain validationSupportChain, FhirContext fhirContext) {
        this.validationSupportChain = validationSupportChain;
        this.fhirContext = fhirContext;
    }

    /**
     * Rebuild Caching Validation Support
     * This should be executed whenever Validation Module are added or removed
     * */
    public void buildCache() {
        CachingValidationSupport cachingValidationSupport = new CachingValidationSupport(validationSupportChain);
        FhirInstanceValidator instanceValidator = new FhirInstanceValidator(cachingValidationSupport);
        fhirValidator = new FhirValidator(fhirContext);
        fhirValidator.registerValidatorModule(instanceValidator);
    }

    /**
     * Get cached FHIR Validator
     * */
    public FhirValidator getFhirValidator() {
        return fhirValidator;
    }

    /**
     * Adding a Validation Support to Validation Chain
     * */
    public void addValidationSupport(IValidationSupport validationSupport) {
        validationSupportChain.addValidationSupport(validationSupport);
    }

    /**
     * Remove a Validation Support from Validation chain
     * */
    public void removeValidationSupport(IValidationSupport validationSupport) {
        validationSupportChain.removeValidationSupport(validationSupport);
    }
}
