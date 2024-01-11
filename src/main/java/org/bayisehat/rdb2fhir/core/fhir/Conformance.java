package org.bayisehat.rdb2fhir.core.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.support.DefaultProfileValidationSupport;
import ca.uhn.fhir.context.support.IValidationSupport;
import org.hl7.fhir.common.hapi.validation.support.PrePopulatedValidationSupport;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.StructureDefinition;

import java.io.IOException;
import java.util.*;

/**
 * Conformance Service manages StructureDefinition,
 * Base StructureDefinition are loaded from DefaultProfileValidationSupport,
 * Additional StructureDefinition can be loaded using packages, which are loaded to NpmPackageValidationSupport,
 * or provides the StructureDefinition directly, which is loaded to PrePopulatedValidation
 * */
public class Conformance {

    private final CustomNpmPackageValidationSupportFactory customNpmPackageValidationSupportFactory;

    private final PrePopulatedValidationSupportFactory prePopulatedValidationSupportFactory;

    /**
     * List of validation support list
     * DefaultProfileValidationSupport is the highest priority, so searching a StructureDefinition by ID/type
     * will be prioritized from it.
     * Key is profile group, "base" for DefaultProfileValidationSupport
     */
    private final LinkedHashMap<String, IValidationSupport> validationSupportList = new LinkedHashMap<>();

    /**
     * A Map for StructureDefinition to their ValidationSupport,
     * Key: StructureDefinition, Value: IValidationSupport
     * */
    private final HashMap<StructureDefinition, IValidationSupport> structureDefinitionToIValidationSupportHashMap = new HashMap<>();

    /**
     * Validation Support hold default base definition Resource (structure definition, value set, or code system)
     */
    private final DefaultProfileValidationSupport defaultProfileValidationSupport;


    public Conformance(DefaultProfileValidationSupport defaultProfileValidationSupport,
                       CustomNpmPackageValidationSupportFactory customNpmPackageValidationSupportFactory,
                       PrePopulatedValidationSupportFactory prePopulatedValidationSupportFactory
    ) throws IOException {
        this.customNpmPackageValidationSupportFactory = customNpmPackageValidationSupportFactory;
        this.prePopulatedValidationSupportFactory = prePopulatedValidationSupportFactory;

        //load all base StructureDefinition
        this.defaultProfileValidationSupport = defaultProfileValidationSupport;
        addValidationSupport("base", defaultProfileValidationSupport);
        for (IBaseResource baseResource : defaultProfileValidationSupport.fetchAllStructureDefinitions()) {
            setStructureDefinitionToIValidationSupportMap((StructureDefinition) baseResource, defaultProfileValidationSupport);
        }
    }

    public Conformance(FhirContext fhirContext) throws IOException {
        this(new DefaultProfileValidationSupport(fhirContext), new CustomNpmPackageValidationSupportFactory(fhirContext),
                new PrePopulatedValidationSupportFactory(fhirContext));
    }

    /**
     * Get IValidationSupport by a StructureDefinition
     * */
    public IValidationSupport getValidationSupportByStructureDefinition(StructureDefinition structureDefinition) {
        return structureDefinitionToIValidationSupportHashMap.get(structureDefinition);
    }
    private void setStructureDefinitionToIValidationSupportMap(StructureDefinition structureDefinition, IValidationSupport validationSupport) {
        structureDefinitionToIValidationSupportHashMap.put(structureDefinition, validationSupport);
    }

    private void addValidationSupport(String name, IValidationSupport validationSupport) {
        validationSupportList.put(name, validationSupport);
    }

    /**
     * Load StructureDefinition from package, example us-core.tgz,
     * Key: arbitrary name for package, Value: path to package
     * E.g.  loadPackageFromClasspath("us-core", "classpath:package/us-core.tgz");
     * */
    public void loadPackageFromPath(String name, String classpath) throws IOException {
        CustomNpmPackageValidationSupport npmPackageValidationSupport = customNpmPackageValidationSupportFactory.createInstance();
        npmPackageValidationSupport.loadPackageFromPath(classpath);
        addValidationSupport(name, npmPackageValidationSupport);

        for (IBaseResource baseResource : npmPackageValidationSupport.fetchAllStructureDefinitions()) {
            setStructureDefinitionToIValidationSupportMap((StructureDefinition) baseResource, npmPackageValidationSupport);
        }
    }

    /**
     * Manually added non-package StructureDefinition
     * Key: arbitrary name for group (PrePopulatedValidationSupport), Value: a StructureDefinition.
     * Manually added StructureDefinition  will be placed in PrePopulatedValidationSupport.
     * DefaultProfileValidation (base) must not be modified since the class does not have method addResource()
     * */
    public void addStructureDefinition(String group, StructureDefinition structureDefinition) throws ConformanceException {
        for (Map.Entry<String, IValidationSupport> entry : validationSupportList.entrySet()) {
            IValidationSupport validationSupport = entry.getValue();
            String groupName = entry.getKey();
            if (group.equals(groupName)) {
                if(! (validationSupport instanceof PrePopulatedValidationSupport)){
                    throw new ConformanceException("Couldn't added StructureDefinition to this group: " + group, 3001);
                }
                ((PrePopulatedValidationSupport) validationSupport).addResource(structureDefinition);
                return;
            }
        }
      PrePopulatedValidationSupport validationSupportNonPackage = prePopulatedValidationSupportFactory.createInstance();
      validationSupportNonPackage.addResource(structureDefinition);
      addValidationSupport(group, validationSupportNonPackage);
    }

    /**
     * Get StructureDefinition by a URL, a resource type, or by ID
     * @param structureDefinition a URL, a resource type, or by ID
     * */
    public StructureDefinition getStructureDefinition(String structureDefinition) throws ConformanceException {
        StructureDefinition retVal;
        for (Map.Entry<String, IValidationSupport> entry : validationSupportList.entrySet()) {
            IValidationSupport validationSupport = entry.getValue();
            retVal = (StructureDefinition) validationSupport.fetchStructureDefinition(structureDefinition);
            if (retVal != null) {
                return retVal;
            }
        }

        throw new ConformanceException("Couldn't find StructureDefinition for: " + structureDefinition, 3002);
    }


    /**
     * Get all (resources type) StructureDefinition by package/group name, sorted by the resource type
     * */
    public ArrayList<StructureDefinition> getStructureDefinitionByGroup(String groupName) throws ConformanceException {
        HashSet<StructureDefinition> sdSet = new HashSet<>();
        IValidationSupport validationSupport = validationSupportList.get(groupName);
        if (validationSupport == null) {
            throw new ConformanceException("Cannot find package or group: " + groupName, 3003);
        }

        for (IBaseResource baseResource : validationSupport.fetchAllStructureDefinitions()) {
            StructureDefinition sd = (StructureDefinition) baseResource;

            if (validationSupport instanceof DefaultProfileValidationSupport) {
                /* Base
                * Note: HAPI FHIR validation r4 includes SubstanceNucleicAcid which is not mentioned as a resource
                * type list in FHIR R4
                * */
                String type = sd.getType();
                if ((isBaseDomainResource(sd) || type.equals("Binary") || type.equals("Bundle") || type.equals("Parameters")) && ! type.equals("SubstanceNucleicAcid")) {
                    sdSet.add(sd);
                }
            }else{
                if (sd.getKind() == StructureDefinition.StructureDefinitionKind.RESOURCE) {
                    sdSet.add(sd);
                }
            }

        }

        ArrayList<StructureDefinition> retVal = new ArrayList<>(sdSet);
        Collections.sort(retVal, new UrlComparator());
        return retVal;
    }


    private boolean isBaseDomainResource(StructureDefinition sd) {
        return sd.getBaseDefinition() != null
                && sd.getBaseDefinition().equals("http://hl7.org/fhir/StructureDefinition/DomainResource")
                && sd.getKind() == StructureDefinition.StructureDefinitionKind.RESOURCE;
    }

    /**
     * Get DefaultProfileValidationSupport
     * */
    public DefaultProfileValidationSupport getDefaultProfileValidationSupport() {
        return defaultProfileValidationSupport;
    }

    private static class UrlComparator implements Comparator<StructureDefinition> {
        @Override
        public int compare(StructureDefinition o1, StructureDefinition o2) {
            return o1.getUrl().compareTo(o2.getUrl());
        }
    }



}
