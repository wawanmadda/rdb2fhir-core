package org.bayisehat.rdb2fhir.core.rdb2ol;

import java.util.Set;

public class IdentifierFactory {

    public Identifier create(PathService path, Set<String> columnList) {
        return new Identifier(path, columnList);
    }

}
