package org.structuredb.fileops.app;

import org.structuredb.exception.app.AppDirectoryInitializationException;
import org.structuredb.utils.Console;

import java.io.File;
import java.nio.file.Paths;

public class AppDirectory {

    public static boolean appDirectoryExists(String dataPath, String appName) {
        File appDirectory = new File(Paths.get(dataPath, appName).toString());
        return appDirectory.exists();
    }

    public static void addAppDirectory(String dataPath, String appName) {
        File file = new File(Paths.get(dataPath, appName).toString());

        if (!file.exists()) {
            Console.info("Creating app directory for '" + appName + "'");

            if (file.mkdirs()) {
                Console.info("Initialized app directory successfully for '" + appName + "'");
            } else {
                Console.error("Error creating app directory for '" + appName + "'");
                throw new AppDirectoryInitializationException(appName);
            }
        }
    }

    public static void removeAppDirectory(String dataPath, String appName) {

    }

    public static void renameAppDirectory(String dataPath, String appName, String newName) {

    }
}
