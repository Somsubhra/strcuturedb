package org.structuredb.exception;

public class AppDirectoryInitializationException extends RuntimeException {

    private String appName;

    public AppDirectoryInitializationException(String appName) {
        this.appName = appName;
    }

    @Override
    public String getMessage() {
        return "Error initializing app directory for '" + appName + "'";
    }
}
