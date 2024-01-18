package org.bayisehat.rdb2fhir.core.jdbc;

public class PostgreSql implements IDriver{
    @Override
    public String getJdbcDriver() {
        return "org.postgresql.Driver";
    }

    @Override
    public String getJdbcUrl(String path) {
        return "jdbc:postgresql://" + path;
    }
}
