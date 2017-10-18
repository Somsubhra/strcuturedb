package org.structuredb.exception.query;

public class QueryDataParseException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Invalid query data";
    }
}
