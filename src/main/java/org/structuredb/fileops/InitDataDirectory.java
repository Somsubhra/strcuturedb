package org.structuredb.fileops;

import org.structuredb.utils.Console;

import java.io.File;

public class InitDataDirectory {

    public static void init(String dataPath) {
        File file = new File(dataPath);

        if (!file.exists()) {
            Console.info("Data path " + dataPath + " does not exist. Creating it now.");
            if(file.mkdirs()) {
                Console.info("Created data path successfully");
            } else {
                Console.error("Failed to create data path");
                System.exit(1);
            }
        }
    }

}
