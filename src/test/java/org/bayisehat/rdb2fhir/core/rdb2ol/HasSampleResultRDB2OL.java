package org.bayisehat.rdb2fhir.core.rdb2ol;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bayisehat.rdb2fhir.core.databasefetcher.ConnectionService;
import org.bayisehat.rdb2fhir.core.databasefetcher.DatabaseFetcher;
import org.bayisehat.rdb2fhir.core.util.HelperTool;
import org.bayisehat.rdb2fhir.core.fetcher.ResultQuadruple;
import org.bayisehat.rdb2fhir.core.fetcher.ResultQuadrupleFactory;
import org.bayisehat.rdb2fhir.core.fhir.Conformance;
import org.bayisehat.rdb2fhir.core.fhir.NameServiceFactory;

import java.util.ArrayList;

public interface HasSampleResultRDB2OL {

    default ArrayList<ResultQuadruple> getResultRDB2OL(String path, ConnectionService connectionService, Conformance conformance) throws Exception {
        String rdb2ol = HelperTool.loadFile(path);

        Rdb2olFactory rdb2olFactory = new Rdb2olFactory(new QuadrupleFactory(conformance), new ObjectMapper());
        Rdb2ol rdb2olObj = rdb2olFactory.create(rdb2ol);

        DatabaseFetcher databaseFetcher = new DatabaseFetcher(connectionService, new ResultQuadrupleFactory());
        return databaseFetcher.fetch(rdb2olObj);
    }

}
