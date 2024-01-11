package org.bayisehat.rdb2fhir.core.example.suite;


import org.bayisehat.rdb2fhir.core.example.*;
import org.bayisehat.rdb2fhir.core.example.r4.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import java.io.File;

@Suite
@SelectClasses({
        AccountToCodeableGroup.class,
        CodesystemGroup.class,
        CodingToExternalGroup.class,
        FamilyToOxygenGroup.class,
        ParameterToV3Group.class,
        ValuesetToXhtmlGroup.class
})
@IncludeTags("multi")

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class R4MultiTestSuite extends BaseSuite {

    @BeforeAll
    void beforeAll() {
        BaseExampleTest.setDbSource(BaseExampleTest.DB_SOURCE.H2);
        BaseExampleTest.setTestType(BaseExampleTest.TEST_TYPE.MULTI);
        BaseExampleTest.setExampleFolder(String.join(File.separator, "src","test","resources","example","r4"));
        BaseExampleTest.setRdb2olFolder(String.join(File.separator, "rdb2ol","r4","multi"));
    }

}
