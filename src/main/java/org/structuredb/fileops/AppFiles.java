package org.structuredb.fileops;

import org.structuredb.exception.AppFilesInitializationException;
import org.structuredb.utils.Console;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class AppFiles {

    public static void init(String dataPath) throws IOException {

        File file = new File(Paths.get(dataPath, "apps").toString());

        if(!file.exists()) {
            Console.info("App index not initialized. Creating now.");
            if(file.createNewFile()) {
                Console.info("Initialized app index successfully");
            } else {
                throw new AppFilesInitializationException();
            }
        }
    }

    public static boolean appExists(String dataPath, String appName) {
        File appDirectory = new File(Paths.get(dataPath, appName).toString());

        File appIndexFile = new File(Paths.get(dataPath, "apps").toString());

        boolean appInIndex = false;

        try {
            Scanner appIndexScanner = new Scanner(appIndexFile);

            while(appIndexScanner.hasNextLine()) {
                if(appName.equals(appIndexScanner.nextLine().trim())) {
                    appInIndex = true;
                    break;
                }
            }
        } catch (Exception e) {
            Console.error(e.getMessage());
        }

        return appDirectory.exists() && appInIndex;
    }
}
