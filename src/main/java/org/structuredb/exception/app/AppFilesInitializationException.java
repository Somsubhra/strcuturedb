package org.structuredb.exception.app;

public class AppFilesInitializationException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Error initializing app files";
    }
}
