package org.bayisehat.rdb2fhir.core.databasefetcher;

public interface HasConnectionService {

    default ConnectionService getH2ConnectionService() {
        return new ConnectionService("org.h2.Driver",
                "jdbc:h2:mem:dcbapp;DATABASE_TO_LOWER=TRUE","", "");
    }

    default ConnectionService getConnectionService(String driver, String url, String userName, String password) {
        return new ConnectionService(driver, url, userName, password);
    }
}
