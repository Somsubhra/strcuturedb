package org.structuredb.exception;

public class UnknownQueryType extends RuntimeException {

    private String rawQueryType;

    public UnknownQueryType(String rawQueryType) {
        this.rawQueryType = rawQueryType;
    }

    @Override
    public String getMessage() {
        return "Unknown query type '" + rawQueryType + "'";
    }
}
