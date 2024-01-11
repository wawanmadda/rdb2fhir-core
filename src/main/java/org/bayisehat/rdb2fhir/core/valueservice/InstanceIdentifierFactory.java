package org.bayisehat.rdb2fhir.core.valueservice;

import java.util.HashMap;

public class InstanceIdentifierFactory {

    public InstanceIdentifier create(HashMap<String, String> keyValueList) {
        return new InstanceIdentifier(keyValueList);
    }

}
