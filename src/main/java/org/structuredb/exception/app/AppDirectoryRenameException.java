package org.structuredb.exception.app;

public class AppDirectoryRenameException extends RuntimeException {

    private String appName;

    public AppDirectoryRenameException(String appName) {
        this.appName = appName;
    }

    @Override
    public String getMessage() {
        return "Error renaming app directory for '" + appName + "'";
    }
}
