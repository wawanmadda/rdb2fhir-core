package org.bayisehat.rdb2fhir.core.rdb2ol;

import org.bayisehat.rdb2fhir.core.fhir.NameServiceFactory;

public class PathServiceFactory {

    public PathService create() {
        return new PathService(new NameServiceFactory());
    }

}
