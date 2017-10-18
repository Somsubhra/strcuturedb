package org.structuredb.exception;

public class AppIndexEntryException extends RuntimeException {

    private String appName;

    public AppIndexEntryException(String appName) {
        this.appName = appName;
    }

    @Override
    public String getMessage() {
        return "Error while adding '" + appName + "' to app index";
    }
}
