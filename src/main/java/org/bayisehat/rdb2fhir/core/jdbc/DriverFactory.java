package org.bayisehat.rdb2fhir.core.jdbc;

import org.bayisehat.rdb2fhir.core.Main;

public class DriverFactory {

    public IDriver getDriver(Main.DATA_SOURCE_SYSTEM dataSourceSystem) throws Exception {

        IDriver driver;

        switch (dataSourceSystem) {
            case MYSQL -> driver = new MySql();
            case POSTGRESQL -> driver = new PostgreSql();
            case CSV -> driver = new Csv();
            default -> throw new Exception("Unsupported Data Source");
        }

        return driver;
    }
}
