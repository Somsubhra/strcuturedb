package org.structuredb.exception.app;

public class AppDirectoryNotFoundException extends RuntimeException {

    private String appName;

    public AppDirectoryNotFoundException(String appName) {
        this.appName = appName;
    }

    @Override
    public String getMessage() {
        return "App directory for '" + appName + "' not found";
    }
}
