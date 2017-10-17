package org.structuredb.exception;

public class AppExistsException extends RuntimeException {

    private String appName;

    public AppExistsException(String appName) {
        this.appName = appName;
    }

    @Override
    public String getMessage() {
        return "App '" + appName + "' already exists";
    }
}
