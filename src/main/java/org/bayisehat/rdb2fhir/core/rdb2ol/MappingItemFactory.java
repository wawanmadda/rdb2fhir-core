package org.bayisehat.rdb2fhir.core.rdb2ol;

import java.util.Set;

public class MappingItemFactory {

    public MappingItem create(PathService path, String function, Set<String> columnList) {
        return new MappingItem(path, function, columnList);
    }
}
