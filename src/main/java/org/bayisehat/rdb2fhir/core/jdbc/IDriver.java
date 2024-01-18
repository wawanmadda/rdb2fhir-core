package org.bayisehat.rdb2fhir.core.jdbc;

public interface IDriver {

    String getJdbcDriver();

    String getJdbcUrl(String url);

}
