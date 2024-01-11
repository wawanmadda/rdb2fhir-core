package org.bayisehat.rdb2fhir.core.example.util;

import org.testcontainers.containers.JdbcDatabaseContainer;

public interface HasTestContainer {

    default JdbcDatabaseContainer buildUpContainer(){
        DockerContainer dockerContainer = new DockerContainer();

        //add a hook to clean up after test is done
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            dockerContainer.destroy();
        }));

        dockerContainer.init(DockerContainer.DatabaseType.PostgreSQL);
        return  dockerContainer.getDatabaseContainer();
    }

}
