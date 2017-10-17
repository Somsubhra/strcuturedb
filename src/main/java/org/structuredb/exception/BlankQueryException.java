package org.structuredb.exception;

public class BlankQueryException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Query is blank";
    }
}
