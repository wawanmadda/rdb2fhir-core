package org.bayisehat.rdb2fhir.core.databasefetcher;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.bayisehat.rdb2fhir.core.util.HelperTool;

import java.io.StringReader;

public interface HasSampleSchema {

    default void setupSampleSchema(ConnectionService connectionService) throws Exception {
        ScriptRunner scriptRunner = new ScriptRunner(connectionService.getSqlSessionFactory().openSession().getConnection());
        scriptRunner.setLogWriter(null);
        scriptRunner.setSendFullScript(false);
        scriptRunner.setStopOnError(true);
        scriptRunner.runScript(new StringReader(HelperTool.loadFile("src/test/resources/sql/pasien.sql")));
    }
}
