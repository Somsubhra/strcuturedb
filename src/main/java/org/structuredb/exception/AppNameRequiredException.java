package org.structuredb.exception;

public class AppNameRequiredException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Field 'app' is missing";
    }
}
