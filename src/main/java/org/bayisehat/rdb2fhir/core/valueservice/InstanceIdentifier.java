package org.bayisehat.rdb2fhir.core.valueservice;

import java.util.HashMap;

/**
 * InstanceIdentifier is used to identify resources within a resource type.
 * It uses combination of columns and its corresponding values
 * */
public final class InstanceIdentifier {

    private final HashMap<String, String> keyValueList = new HashMap<>();

    public InstanceIdentifier(HashMap<String, String> keyValueList) {
        this.keyValueList.putAll(keyValueList);
    }

    @Override
    public boolean equals(Object obj) {
        //compare with hashmap
        if (obj instanceof HashMap<?, ?>) {
            return keyValueList.equals(obj);
        }

        //compare with identifier
        return keyValueList.equals(((InstanceIdentifier) obj).keyValueList);
    }

    /**
     * Override hashCode to provides better performance when looking up in HashMap
     * */
    @Override
    public int hashCode() {
        return keyValueList.hashCode();
    }

    public HashMap<String, String> getKeyValueList() {
        return new HashMap<>(keyValueList);
    }
}
