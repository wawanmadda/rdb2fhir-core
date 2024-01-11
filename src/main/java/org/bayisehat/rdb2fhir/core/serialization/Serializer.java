package org.bayisehat.rdb2fhir.core.serialization;

import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.parser.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.bayisehat.rdb2fhir.core.valueservice.InstanceIdentifier;
import org.bayisehat.rdb2fhir.core.valueservice.InstancePool;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Base;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.StructureDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * This class serialize resources instances to a specific format (RDF, JSON, or XML)
 * If only 1 resource instance to be serialized, a single resource returned.
 * If more than 2 resource instances to be serialized, a bundle returned.
 * */
public class Serializer {

    private IParser parser;

    /**
     * remove "[null]": when null value is the only value inside an array
     * */
    private boolean removingArrayContainOnlyNull = true;


    public Serializer(IParser parser) {
        this.parser = parser;
    }


    /**
     * Serialize resource instances
     * */
    public String serialize(InstancePool instancePool) throws JsonProcessingException {
        if (instancePool.getPool().isEmpty()) {
            return "";
        }

        boolean singlePool = instancePool.getPool().size() == 1;
        Bundle bundle = null;

        for (Map.Entry<StructureDefinition, HashMap<String, HashMap<InstanceIdentifier, Base>>> entry : instancePool.getPool().entrySet()) {
            HashMap<String, HashMap<InstanceIdentifier, Base>> pathList = entry.getValue();
            boolean single =  singlePool && pathList.get("").size() == 1; //"" => \root
            if (!single) {
                bundle = new Bundle();
                bundle.setType(Bundle.BundleType.COLLECTION);
            }
            for (Map.Entry<InstanceIdentifier, Base> row : pathList.get("").entrySet()) {
                if (single) {
                    // TODO: 28/09/23
                    if (removingArrayContainOnlyNull && parser instanceof JsonParser) {
                        return removeArrayContainOnlyNull(parser.encodeResourceToString((IBaseResource) row.getValue()));
                    }
                    return parser.encodeResourceToString((IBaseResource) row.getValue());
                }
                bundle.addEntry().setResource((Resource) row.getValue());
            }
        }

        return bundleSerialize(bundle);
    }

    public void setParser(IParser parser) {
        this.parser = parser;
    }

    private String bundleSerialize(Bundle bundle) {
        if (removingArrayContainOnlyNull && parser instanceof JsonParser) {
            return removeArrayContainOnlyNull(parser.encodeResourceToString(bundle));
        }
        return parser.encodeResourceToString(bundle);
    }

    /**
     * Disable removing "[null]" value and its element if exists.
     * This only applicable to JSON format as output
     * */
    public void disableRemovingArrayContainOnlyNull() {
        this.removingArrayContainOnlyNull = false;
    }

    /**
     * Enable removing "[null]" value and its element if exists.
     * This only applicable to JSON format as output
     * */
    public void enableRemovingArrayContainOnlyNull() {
        this.removingArrayContainOnlyNull = true;
    }


    /**
     * HAPI FHIR parser sometimes returned value "[null]". This method removes the value and its element
     * */
    private String removeArrayContainOnlyNull(String str) {
        return  str.replaceAll("\\s*\"[A-Za-z]+\":\\s?\\[\\s?null\\s?\\],", "");
    }







}
