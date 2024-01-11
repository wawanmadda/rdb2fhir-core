package org.bayisehat.rdb2fhir.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void run() throws Exception {
        Main main = new Main();
        main.run(Main.RDBMS.POSTGRESQL, "localhost:5432/postgres?autosave=conservative", "postgres", "postgres",
                RDB2FHIR.OutputFormat.JSON,
                "/Users/bayisehat/projects/java/rdb2fhir/rdb2fhir-core/rdb2ol/us/mix/CapabilityStatement-us-core-client.json",
                "/Users/bayisehat/projects/java/rdb2fhir/rdb2fhir-core");
    }
}