package org.bayisehat.rdb2fhir.core;

import ca.uhn.fhir.context.FhirContext;
import org.bayisehat.rdb2fhir.core.databasefetcher.ConnectionService;
import org.bayisehat.rdb2fhir.core.databasefetcher.DatabaseFetcher;
import org.bayisehat.rdb2fhir.core.databasefetcher.HasConnectionService;
import org.bayisehat.rdb2fhir.core.databasefetcher.HasSampleSchema;
import org.bayisehat.rdb2fhir.core.util.HelperTool;
import org.bayisehat.rdb2fhir.core.fetcher.Fetchable;
import org.bayisehat.rdb2fhir.core.fetcher.ResultQuadrupleFactory;
import org.bayisehat.rdb2fhir.core.fhir.Conformance;
import org.bayisehat.rdb2fhir.core.fhir.HasConformance;
import org.bayisehat.rdb2fhir.core.serialization.Serializer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class RDB2FHIRTest implements HasConformance, HasConnectionService, HasSampleSchema {
    private Conformance conformance;

    private Fetchable fetcher;

    private RDB2FHIR RDB2FHIR;

    private FhirContext fhirContext;

    @BeforeAll
    void setUpAll() throws Exception {
        this.conformance = getConformance();
        ConnectionService connectionService = this.getH2ConnectionService();
        setupSampleSchema(connectionService);
        fetcher = new DatabaseFetcher(connectionService, new ResultQuadrupleFactory());
        fhirContext = FhirContext.forR4();
    }

    @BeforeEach
    void setUp() throws IOException {
        RDB2FHIR = RDB2FHIRFactory.getInstance(conformance, fetcher, fhirContext);
    }

    @Test
    void transformValidationDisabled() throws Exception {
        String result = RDB2FHIR.transform(HelperTool.loadFile("src/test/resources/rdb2ol/sample1.json"));
        assertEquals(HelperTool.loadFile("src/test/resources/output/output_from_sample1.json"), result);
    }

    @Test
    void transformValidationEnabled() throws Exception {
        RDB2FHIR.enableValidator();
        String result = RDB2FHIR.transform(HelperTool.loadFile("src/test/resources/rdb2ol/sample1.json"));
        assertEquals(HelperTool.loadFile("src/test/resources/output/output_from_sample1.json"), result);
    }

    @Test
    void transformRDF() throws Exception {
        Serializer serializer = new Serializer(FhirContext.forR4().newJsonParser().setPrettyPrint(true), conformance);
        RDB2FHIR = RDB2FHIRFactory.getInstance(conformance, fetcher, serializer, FhirContext.forR4());
        RDB2FHIR.setOutputFormat(org.bayisehat.rdb2fhir.core.RDB2FHIR.OutputFormat.RDF);
        String result = RDB2FHIR.transform(HelperTool.loadFile("src/test/resources/rdb2ol/sample1.json"));

        //Compare by list since RDF generated is randomly unordered
        ArrayList<String> resultList = new ArrayList<>(Arrays.asList(result.split("\\n")));

        //load file as list, since loadFile() do not preserve new line character (return only a list with 1 value)
        assertTrue(resultList.containsAll(HelperTool.loadFileAsList("src/test/resources/output/output_from_sample1.ttl")));
    }

    @Test
    void transformXML() throws Exception {
        RDB2FHIR.setOutputFormat(org.bayisehat.rdb2fhir.core.RDB2FHIR.OutputFormat.XML);
        String result = RDB2FHIR.transform(HelperTool.loadFile("src/test/resources/rdb2ol/sample1.json"));
        assertEquals(HelperTool.loadFile("src/test/resources/output/output_from_sample1.xml"), result);
    }

    @Test
    void getMessage() {
    }



}