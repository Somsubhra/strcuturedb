package org.structuredb.exception.app;

public class AppIndexParseException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Error while parsing app index";
    }
}
