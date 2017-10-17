package org.structuredb.exception;

public class QueryDataParseException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Invalid query data";
    }
}
