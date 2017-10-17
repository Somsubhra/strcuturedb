package org.structuredb.structure;

public class Error extends Structure {

    private Throwable exception;

    public Error(Throwable exception) {
        this.exception = exception;
    }

    @Override
    public String serialize() {
        return exception.getMessage();
    }
}
