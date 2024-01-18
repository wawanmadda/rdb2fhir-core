package org.bayisehat.rdb2fhir.core.jdbc;

public class MySql implements IDriver{
    @Override
    public String getJdbcDriver() {
        return "com.mysql.cj.jdbc.Driver";
    }

    @Override
    public String getJdbcUrl(String path) {
        return "jdbc:mysql://" + path;
    }
}
