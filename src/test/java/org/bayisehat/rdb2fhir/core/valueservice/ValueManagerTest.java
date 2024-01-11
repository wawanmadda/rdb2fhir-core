package org.bayisehat.rdb2fhir.core.valueservice;

import org.bayisehat.rdb2fhir.core.databasefetcher.ConnectionService;
import org.bayisehat.rdb2fhir.core.databasefetcher.HasConnectionService;
import org.bayisehat.rdb2fhir.core.databasefetcher.HasSampleSchema;
import org.bayisehat.rdb2fhir.core.fetcher.ResultQuadruple;
import org.bayisehat.rdb2fhir.core.fhir.*;
import org.bayisehat.rdb2fhir.core.rdb2ol.HasSampleResultRDB2OL;
import org.bayisehat.rdb2fhir.core.rdb2ol.RDB2OLException;
import org.bayisehat.rdb2fhir.core.valueservice.jython.PyInterpreter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.python.util.PythonInterpreter;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ValueManagerTest implements HasSampleSchema, HasConnectionService,  HasSampleResultRDB2OL, HasConformance {

    private ConnectionService connectionService;

    private ValueManager valueManager;

    private Conformance conformance;

    @BeforeAll
    void setUp() throws Exception {
        Allocator allocator = new Allocator(new NameServiceFactory());
        valueManager = new ValueManager(new PyInterpreter(new PythonInterpreter()), allocator,
                new InstancePoolFactory(new InstanceIdentifierFactory()), new KeyValueWrapperFactory());

        connectionService = this.getH2ConnectionService();
        setupSampleSchema(connectionService);
        conformance = getConformance();

    }

    @Test
    void transform() throws Exception {
        ArrayList<ResultQuadruple> resultQuadrupleList = getResultRDB2OL("src/test/resources/rdb2ol/sample1.json", connectionService, conformance);
        InstancePool pool = valueManager.transform(resultQuadrupleList);

        //18 resource instances
        assertEquals(18, pool.getPool().get(conformance.getStructureDefinition("Patient")).get("").size());
    }

    @Test
    void transformEmptyMapping()  {
        assertThrows(RDB2OLException.class, () -> getResultRDB2OL("src/test/resources/rdb2ol/empty-mapping.json", connectionService, conformance));
    }

    @Test
    void transformEmptyIdentifier() {
        assertThrows(RDB2OLException.class, () -> getResultRDB2OL("src/test/resources/rdb2ol/empty-identifier.json", connectionService, conformance));
    }
}