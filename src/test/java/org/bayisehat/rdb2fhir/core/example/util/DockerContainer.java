package org.bayisehat.rdb2fhir.core.example.util;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

public class DockerContainer {

    private JdbcDatabaseContainer rdbContainer;

    private String dbName = "dbtest";

    private String user = "usertest";

    private String password = "passtest";

    public enum DatabaseType {
        PostgreSQL
    }

    public void init(DatabaseType databaseType) {
        if (rdbContainer != null && rdbContainer.isRunning()) {
            return;
        }

        if (databaseType == DatabaseType.PostgreSQL) {
            GenericContainer rdbContainer = withConfig(new PostgreSQLContainer("postgres:15"))
                    .withUrlParam("autosave", "conservative");
//                    .withCommand("configure --with-blocksize=16");
            this.rdbContainer = (JdbcDatabaseContainer) rdbContainer;
            rdbContainer.start();
        }

    }

    public JdbcDatabaseContainer getDatabaseContainer() {
        return rdbContainer;
    }

    private JdbcDatabaseContainer withConfig(JdbcDatabaseContainer container) {
        return container.withDatabaseName(dbName).withUsername(user).withPassword(password);
    }


    public void destroy() {
        rdbContainer.stop();
    }


}
