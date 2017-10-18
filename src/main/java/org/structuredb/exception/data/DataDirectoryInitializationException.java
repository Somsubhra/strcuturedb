package org.structuredb.exception.data;

public class DataDirectoryInitializationException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Error initializing data directory";
    }
}
