package org.bayisehat.rdb2fhir.core.fhir;

import java.io.IOException;

public interface HasUSCore {

    default void loadUSPackage(Conformance conformanceService) throws IOException {
        conformanceService.loadPackageFromPath("us-core", "src/test/resources/package/us-core.tgz");
    }

}
