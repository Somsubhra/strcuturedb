package org.structuredb.exception.query;

public class BlankQueryException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Query is blank";
    }
}
