package org.structuredb.fileops;

import org.structuredb.exception.DataDirectoryInitializationException;
import org.structuredb.utils.Console;

import java.io.File;
import java.io.IOException;

public class DataFiles {

    public static void init(String dataPath) throws IOException {
        File file = new File(dataPath);

        if (!file.exists()) {
            Console.info("Data directory " + dataPath + " not initialized. Creating now.");
            if(file.mkdirs()) {
                Console.info("Initialized data directory successfully");
            } else {
                throw new DataDirectoryInitializationException();
            }
        }

        AppFiles.init(dataPath);
    }

}
