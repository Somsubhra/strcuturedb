package org.structuredb.exception.app;

public class AppRenameRequiredException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Field 'rename' is missing";
    }
}
