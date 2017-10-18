package org.structuredb.fileops.app;

import org.structuredb.exception.app.AppIndexEntryException;
import org.structuredb.utils.Console;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class AppIndex {

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
            Files.write(Paths.get(dataPath, "apps"), (appName + "\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new AppIndexEntryException(appName);
        }
    }

}
