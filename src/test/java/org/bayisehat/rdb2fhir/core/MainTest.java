package org.bayisehat.rdb2fhir.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @Disabled
    //need running postgres
    void runOnPostgre() throws Exception {
        Main main = new Main();
        main.run(Main.DATA_SOURCE_SYSTEM.POSTGRESQL, "localhost:5432/postgres?autosave=conservative", "postgres", "postgres",
                RDB2FHIR.OutputFormat.JSON,
                "/Users/bayisehat/projects/java/rdb2fhir/rdb2fhir-core/rdb2ol/us/mix/CapabilityStatement-us-core-client.json", "");
    }

    @Test
    void runOnCsv() {
        Main main = new Main();
        assertDoesNotThrow(()->{
            main.run(Main.DATA_SOURCE_SYSTEM.CSV, "src/test/resources/csv", "", "",
                    RDB2FHIR.OutputFormat.JSON,
                    "src/test/resources/rdb2ol/Purchase.json", ".");

            removeFile("output.json");
        });
    }

    @Test
    void runOnTxt() {
        Main main = new Main();
        assertDoesNotThrow(()->{
            main.run(Main.DATA_SOURCE_SYSTEM.CSV, "src/test/resources/csv/?separator=;&fileExtension=.txt", "", "",
                    RDB2FHIR.OutputFormat.JSON,
                    "src/test/resources/rdb2ol/sample.json", "");

            removeFile("output.json");
        });
    }

    void removeFile(String fileName) throws IOException {
        File file = new File(fileName);
        boolean result = Files.deleteIfExists(file.toPath());
    }
}