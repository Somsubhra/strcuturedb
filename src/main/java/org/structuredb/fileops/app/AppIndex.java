package org.structuredb.fileops.app;

import org.structuredb.exception.app.AppFilesInitializationException;
import org.structuredb.exception.app.AppIndexEntryException;
import org.structuredb.exception.app.AppIndexParseException;
import org.structuredb.utils.Console;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppIndex {

    public static void initAppIndex(String dataPath) throws IOException {

        File file = new File(Paths.get(dataPath, "apps").toString());

        if (!file.exists()) {
            Console.info("App index not initialized. Creating now.");
            if (file.createNewFile()) {
                Console.info("Initialized app index successfully");
            } else {
                Console.error("Error initializing app index");
                throw new AppFilesInitializationException();
            }
        }
    }

    public static List<String> getAppsList(String dataPath) {
        List<String> apps = new ArrayList<>();

        File appIndexFile = new File(Paths.get(dataPath, "apps").toString());

        try {
            Scanner appIndexScanner = new Scanner(appIndexFile);

            while (appIndexScanner.hasNextLine()) {
                String appName = appIndexScanner.nextLine().trim();

                if (appName.equals("")) {
                    continue;
                }

                apps.add(appName);
            }
        } catch (Exception e) {
            Console.error(e.getMessage());
            throw new AppIndexParseException();
        }

        return apps;
    }

    public static boolean appEntryExists(String dataPath, String appName) {
        File appIndexFile = new File(Paths.get(dataPath, "apps").toString());

        boolean appInIndex = false;

        try {
            Scanner appIndexScanner = new Scanner(appIndexFile);

            while (appIndexScanner.hasNextLine()) {
                if (appName.equals(appIndexScanner.nextLine().trim())) {
                    appInIndex = true;
                    break;
                }
            }
        } catch (Exception e) {
            Console.error(e.getMessage());
        }

        return appInIndex;
    }

    public static void addAppEntry(String dataPath, String appName) {
        try {
            Console.info("Adding '" + appName + "' to app index");
            Files.write(Paths.get(dataPath, "apps"), (appName + "\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            Console.error("Error adding '" + appName + "' to app index");
            throw new AppIndexEntryException(appName);
        }
    }

    public static void removeAppEntry(String dataPath, String appName) {

    }

    public static void renameAppEntry(String dataPath, String appName, String newName) {

    }
}
