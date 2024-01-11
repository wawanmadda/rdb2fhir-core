package org.bayisehat.rdb2fhir.core.rdb2ol;


public class RDB2OLException extends Exception {

    protected final int errorCode;

    public RDB2OLException(String errorMessage, int errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
