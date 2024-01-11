package org.bayisehat.rdb2fhir.core.databasefetcher;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.bayisehat.rdb2fhir.core.example.util.HasTestContainer;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.JdbcDatabaseContainer;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionServiceTest implements HasTestContainer {

    @Test
    void connectionValid() throws Exception {
        ConnectionService connectionService = new ConnectionService("org.h2.Driver", "jdbc:h2:mem:dcbapp;DATABASE_TO_LOWER=TRUE",
                "", "");
       ScriptRunner scriptRunner = new ScriptRunner(connectionService.getSqlSessionFactory().openSession().getConnection());
        scriptRunner.setLogWriter(null);
        assertDoesNotThrow(()-> scriptRunner.runScript(new StringReader("Select 1;")));
    }

    @Test
    void connectionExceptionCredentialInvalid() throws Exception {
        ConnectionService connectionService = new ConnectionService("unknown driver", "jdbc:h2:mem:dcbapp;DATABASE_TO_LOWER=TRUE",
                "", "");
        assertThrows(PersistenceException.class, () -> connectionService.getSqlSessionFactory().openSession().getConnection());
    }

    @Test
    void connectionClosedAndReopen() throws Exception {
        JdbcDatabaseContainer container = buildUpContainer();
        ConnectionService connectionService = new ConnectionService(container.getDriverClassName(), container.getJdbcUrl(),
                container.getUsername(), container.getPassword());

        ScriptRunner scriptRunner = new ScriptRunner(connectionService.getSqlSessionFactory().openSession().getConnection());
        scriptRunner.setLogWriter(null);
        scriptRunner.runScript(new StringReader("Select 1;"));

        container.stop();
        assertThrows(Exception.class, () -> scriptRunner.runScript(new StringReader("Select 1;")));


        //reopen
        container = buildUpContainer();
        //need to create another connectionservice since testcontainer create container with random port
        connectionService = new ConnectionService(container.getDriverClassName(), container.getJdbcUrl(),
                container.getUsername(), container.getPassword());

        connectionService.rebuildSqlSessionFactory();;
        ScriptRunner scriptRunner2 = new ScriptRunner(connectionService.getSqlSessionFactory().openSession().getConnection());
        scriptRunner2.runScript(new StringReader("Select 1;"));
    }

    @Test
    void connectionClosed() throws Exception {
        JdbcDatabaseContainer container = buildUpContainer();
        ConnectionService connectionService = new ConnectionService(container.getDriverClassName(), container.getJdbcUrl(),
                container.getUsername(), container.getPassword());

        ScriptRunner scriptRunner = new ScriptRunner(connectionService.getSqlSessionFactory().openSession().getConnection());
        scriptRunner.setLogWriter(null);
        scriptRunner.runScript(new StringReader("Select 1;"));

        container.stop();

        assertThrows(Exception.class, () -> scriptRunner.runScript(new StringReader("Select 1;")));
    }

}