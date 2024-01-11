package org.bayisehat.rdb2fhir.core.valueservice;

/**
 * This exception class is intended for Exception related to value assignment to model instances
 * */
public class ValueAssignmentException extends Exception {

    protected int errorCode;

    public ValueAssignmentException(String errorMessage, int errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
