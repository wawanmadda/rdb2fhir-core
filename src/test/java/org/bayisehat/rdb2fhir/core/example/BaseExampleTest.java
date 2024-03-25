package org.bayisehat.rdb2fhir.core.example;

import ca.uhn.fhir.context.FhirContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import net.javacrumbs.jsonunit.assertj.JsonAssertions;
import net.javacrumbs.jsonunit.core.Option;
import org.apache.ibatis.session.SqlSessionFactory;
import org.bayisehat.rdb2fhir.core.RDB2FHIRFactory;
import org.bayisehat.rdb2fhir.core.RDB2FHIR;
import org.bayisehat.rdb2fhir.core.databasefetcher.ConnectionService;
import org.bayisehat.rdb2fhir.core.databasefetcher.DatabaseFetcher;
import org.bayisehat.rdb2fhir.core.databasefetcher.HasConnectionService;
import org.bayisehat.rdb2fhir.core.rdb2ol.QuadrupleFactory;
import org.bayisehat.rdb2fhir.core.rdb2ol.Rdb2olFactory;
import org.bayisehat.rdb2fhir.core.util.HelperTool;
import org.bayisehat.rdb2fhir.core.fetcher.ResultQuadrupleFactory;
import org.bayisehat.rdb2fhir.core.fhir.Conformance;
import org.bayisehat.rdb2fhir.core.fhir.HasConformance;
import org.bayisehat.rdb2fhir.core.example.util.*;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.JdbcDatabaseContainer;

import java.io.File;
import java.io.FileNotFoundException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseExampleTest implements HasConnectionService, HasConformance, HasTestContainer {

    public RDB2FHIR RDB2FHIR;

    public ConverterManager converterManager;

    private static String exampleFolder;

    private static String rdb2olFolder;

    private static DB_SOURCE dbSource;

    private static TEST_TYPE testType;

    private static boolean statusLogging;

    public enum DB_SOURCE {
        POSTGRESQL, DOCKER_POSTGRESQL, H2
    }

    public enum TEST_TYPE {
        MIX
    }

    private void setDefault() {
        setTestType(TEST_TYPE.MIX);
        setDbSource(DB_SOURCE.H2);
        setExampleFolder(String.join(File.separator, "src","test","resources","example","r4"));
        setRdb2olFolder("separate_test");
    }


    @BeforeAll
    void init() throws Exception {
        if (dbSource == null) {
            setDefault();
        }
        ConnectionService connectionService = getConnection();
        setup(connectionService);

        if (statusLogging) {
            logHeader();
        }
    }

    private void logHeader() {
        System.out.println(String.join("\t", "File Name", "Resource Type", "Num Tables", "Has one-to-one", "Has One-to-Many",  "Has Constant Value Assignment", "Number of Cells Transformed"));
    }

    private void setup(ConnectionService connectionService) throws Exception {

        SqlSessionFactory sqlSessionFactory = connectionService.getSqlSessionFactory();

        FhirContext fhirContext = FhirContext.forR4();
        Conformance conformance = getConformance(fhirContext);
        RDB2FHIR = RDB2FHIRFactory.getInstance(conformance, new DatabaseFetcher(connectionService, new ResultQuadrupleFactory()), fhirContext);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setNodeFactory(JsonNodeFactory.withExactBigDecimals(true));
        objectMapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);

        if (testType == TEST_TYPE.MIX) {
            converterManager = new ConverterManager(new ParserMix(objectMapper), new DbSeeder(sqlSessionFactory),
                    new MappingFileGenerator(objectMapper, conformance),
                    objectMapper,
                    conformance,
                    new Rdb2olFactory(new QuadrupleFactory(conformance), objectMapper)
            );
            converterManager.setOutputFolder(rdb2olFolder);
            return;
        }


    }

    private ConnectionService getConnection() throws Exception {
        if (dbSource == DB_SOURCE.H2) {
            return setupMapper(getH2ConnectionService());
        }

        if (dbSource == DB_SOURCE.POSTGRESQL) {
//            return setupMapper(getConnectionService("org.postgresql.Driver",
//                "jdbc:postgresql://localhost:5432/sample_base?autosave=conservative", "postgres", "postgres"));
            return setupMapper(getConnectionService("org.postgresql.Driver",
                "jdbc:postgresql://localhost:5432/postgres?autosave=conservative", "postgres", "postgres"));
        }

//        if (dbSource == DB_SOURCE.MYSQL) {
//            return setupMapper(getConnectionService("com.mysql.cj.jdbc.Driver",
//                    "jdbc:mysql://localhost:3306/test", "root", "root"));
//        }

        if (dbSource == DB_SOURCE.DOCKER_POSTGRESQL) {
            JdbcDatabaseContainer db = buildUpContainer();
            return setupMapper(getConnectionService(db.getDriverClassName(),
                    db.getJdbcUrl(), db.getUsername(), db.getPassword()));
        }

        throw new Exception("set Db source and test type first");
    }

    private ConnectionService setupMapper(ConnectionService connectionService) throws Exception {
        connectionService.getSqlSessionFactory().getConfiguration().addMapper(IMapperInsert.class);
        connectionService.getSqlSessionFactory().getConfiguration().addMapper(IMapperUpdate.class);
        return connectionService;
    }

    private String expected(String filePath) throws FileNotFoundException {
        return HelperTool.loadFile(getPathToFile(filePath));
    }

    private String actual(String filePath) throws Exception {
        return RDB2FHIR.transform(converterManager.exec(getPathToFile(filePath)));
    }

    protected void assertEqual(String file) throws Exception {
        JsonAssertions.assertThatJson(actual(file)).when(Option.IGNORING_ARRAY_ORDER).isEqualTo(expected(file));
//        JsonAssertions.assertThatJson(actual(file)).isEqualTo(expected(file));
//        actual(file);
    }

    public static void setDbSource(DB_SOURCE dbSource) {
        BaseExampleTest.dbSource = dbSource;
    }

    public static void setTestType(TEST_TYPE testType) {
        BaseExampleTest.testType = testType;
    }

    protected String getPathToFile(String fileName) {
        return String.join(File.separator, exampleFolder, fileName);
    }

    public static void setExampleFolder(String path) {
        exampleFolder = path;
    }

    public static void setRdb2olFolder(String path) {
        rdb2olFolder = path;
    }

    public static void enableLogging() {
        statusLogging = true;
    }

    public static boolean getStatusLogging() {
        return statusLogging;
    }
}
