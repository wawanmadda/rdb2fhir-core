package org.bayisehat.rdb2fhir.core.fhir;

public class ConformanceException extends Exception {

    protected final int errorCode;

    public ConformanceException(String errorMessage, int errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
