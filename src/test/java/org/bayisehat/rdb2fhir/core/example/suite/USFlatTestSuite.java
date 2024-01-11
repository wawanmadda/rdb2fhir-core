package org.bayisehat.rdb2fhir.core.example.suite;


import org.bayisehat.rdb2fhir.core.example.BaseExampleTest;
import org.bayisehat.rdb2fhir.core.example.us.USExampleGroup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import java.io.File;

@Suite
@SelectClasses({
        USExampleGroup.class
})

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class USFlatTestSuite extends BaseSuite {

    @BeforeAll
    void beforeAll() {
        BaseExampleTest.setDbSource(BaseExampleTest.DB_SOURCE.H2);
        BaseExampleTest.setTestType(BaseExampleTest.TEST_TYPE.FLAT);
        BaseExampleTest.setExampleFolder(String.join(File.separator, "src","test","resources","example","us"));
        BaseExampleTest.setRdb2olFolder(String.join(File.separator, "rdb2ol","us","flat"));
    }

}
