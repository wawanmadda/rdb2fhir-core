package org.bayisehat.rdb2fhir.core.databasefetcher;

import ca.uhn.fhir.context.support.DefaultProfileValidationSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bayisehat.rdb2fhir.core.fetcher.ResultQuadruple;
import org.bayisehat.rdb2fhir.core.fetcher.ResultQuadrupleFactory;
import org.bayisehat.rdb2fhir.core.fhir.Conformance;
import org.bayisehat.rdb2fhir.core.fhir.HasConformance;
import org.bayisehat.rdb2fhir.core.fhir.NameServiceFactory;
import org.bayisehat.rdb2fhir.core.rdb2ol.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DatabaseFetcherTest implements HasSampleSchema, HasConnectionService, HasConformance {

    private ConnectionService connectionService;

    private Rdb2olFactory rdb2olFactory;

    @BeforeAll
    void setup() throws Exception {
        connectionService = this.getH2ConnectionService();
        setupSampleSchema(connectionService);

        rdb2olFactory = new Rdb2olFactory(new QuadrupleFactory(getConformance()), new ObjectMapper());
    }

    @Test
    void fetch() throws Exception {
        DatabaseFetcher databaseFetcher = new DatabaseFetcher(connectionService, new ResultQuadrupleFactory());

        QuadrupleFactory quadrupleFactory = new QuadrupleFactory(getConformance());
        Quadruple quadruple1 = quadrupleFactory.create().setClass("Patient").setTableName("pasien");
        Quadruple quadruple2 = quadrupleFactory.create().setClass("Patient").setTableName("pasien_mati");

        Rdb2ol rdb2ol = rdb2olFactory.create();
        rdb2ol.addQuadruple(quadruple1);
        rdb2ol.addQuadruple(quadruple2);

        ArrayList<ResultQuadruple> resultList = databaseFetcher.fetch(rdb2ol);
        assertEquals(2, resultList.size());
        assertEquals(18, resultList.get(0).result().size()); //table pasien
        assertEquals(1, resultList.get(1).result().size()); //table pasien_mati

    }
}