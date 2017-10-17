package org.structuredb.exception;

public class DataDirectoryInitializationException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Error initializing data directory";
    }
}
