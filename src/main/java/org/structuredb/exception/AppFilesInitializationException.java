package org.structuredb.exception;

public class AppFilesInitializationException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Error initializing app files";
    }
}
