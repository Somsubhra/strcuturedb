package org.structuredb.exception.app;

public class AppDirectoryDeletionException extends RuntimeException {

    private String appName;

    public AppDirectoryDeletionException(String appName) {
        this.appName = appName;
    }

    @Override
    public String getMessage() {
        return "Error deleting app directory for '" + appName + "'";
    }
}
