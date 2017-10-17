package org.structuredb.fileops;

import org.structuredb.exception.AppFilesInitializationException;
import org.structuredb.utils.Console;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

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
}
