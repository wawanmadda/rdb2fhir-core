package org.bayisehat.rdb2fhir.core.validation;

import ca.uhn.fhir.context.support.DefaultProfileValidationSupport;
import ca.uhn.fhir.context.support.IValidationSupport;
import ca.uhn.fhir.validation.FhirValidator;
import ca.uhn.fhir.validation.SingleValidationMessage;
import ca.uhn.fhir.validation.ValidationResult;
import org.bayisehat.rdb2fhir.core.fhir.Conformance;
import org.bayisehat.rdb2fhir.core.valueservice.InstanceIdentifier;
import org.bayisehat.rdb2fhir.core.valueservice.InstancePool;
import org.hl7.fhir.common.hapi.validation.support.CommonCodeSystemsTerminologyService;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.*;

import java.util.*;

public class ValidatorService {

    private final Conformance conformanceService;

    private final CachingValidationSupportWrapper cachingValidationSupportWrapper;


    public ValidatorService(Conformance conformance, CachingValidationSupportWrapper cachingValidationSupportWrapper) {
        this.conformanceService = conformance;
        this.cachingValidationSupportWrapper = cachingValidationSupportWrapper;
    }

    public ValidatorService(Conformance conformanceService, CachingValidationSupportWrapper cachingValidationSupportWrapper,
                            CommonCodeSystemsTerminologyService commonCodeSystemsTerminologyService) {
       this(conformanceService, cachingValidationSupportWrapper);

        //Add DefaultProfileValidationSupport and CommonCodeSystemTerminologyService
        this.cachingValidationSupportWrapper.addValidationSupport(conformanceService.getDefaultProfileValidationSupport());
        this.cachingValidationSupportWrapper.addValidationSupport(commonCodeSystemsTerminologyService);
        this.cachingValidationSupportWrapper.buildCache();
    }


    public List<List<SingleValidationMessage>> validate(InstancePool instancePool) {
        List<List<SingleValidationMessage>> validationMessageList = new ArrayList<>(); //reset

        for (Map.Entry<StructureDefinition, HashMap<String, HashMap<InstanceIdentifier, Base>>> entry : instancePool.getPool().entrySet()) {
            StructureDefinition structureDefinition = entry.getKey();
            IValidationSupport validationSupport = conformanceService.getValidationSupportByStructureDefinition(structureDefinition);

            //add validation to support chain
            if(! (validationSupport instanceof DefaultProfileValidationSupport) &&
                    ! (validationSupport instanceof CommonCodeSystemsTerminologyService)){
                cachingValidationSupportWrapper.addValidationSupport(validationSupport);
                cachingValidationSupportWrapper.buildCache(); //rebuild cache
            }
            FhirValidator validator = cachingValidationSupportWrapper.getFhirValidator();

            HashMap<String, HashMap<InstanceIdentifier, Base>> pathList = entry.getValue();
            for (Map.Entry<InstanceIdentifier, Base> row : pathList.get("").entrySet()) {
                //need to add meta profile to make package validation works
                if(! (validationSupport instanceof DefaultProfileValidationSupport)){
                    addMetaProfile(structureDefinition, (Resource) row.getValue());
                }

               ValidationResult result = validator.validateWithResult((IBaseResource) row.getValue());
                if (!result.isSuccessful()) {
                    validationMessageList.add(result.getMessages());
                }

                //remove meta profile if done
                if(! (validationSupport instanceof DefaultProfileValidationSupport)){
                    removeMetaProfile(structureDefinition, (Resource) row.getValue());
                }
            }

            //remove validation from support chain
            if(! (validationSupport instanceof DefaultProfileValidationSupport) &&
                    ! (validationSupport instanceof CommonCodeSystemsTerminologyService)){
                cachingValidationSupportWrapper.removeValidationSupport(validationSupport);
            }
        }

        return validationMessageList;
    }

    private void addMetaProfile(StructureDefinition structureDefinition, Resource resource) {
        resource.setMeta(new Meta().addProfile(structureDefinition.getUrl()));
    }

    private void removeMetaProfile(StructureDefinition structureDefinition, Resource resource) {
        Iterator<CanonicalType> iter = resource.getMeta().getProfile().iterator();
        while (iter.hasNext()) {
            if (iter.next().getValue().equals(structureDefinition.getUrl())) {
                iter.remove();
            }
        }

    }



}
