package org.structuredb;

import org.structuredb.configuration.ConfigReader;
import org.structuredb.connector.SDBServer;
import org.structuredb.utils.Console;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Application {

    public static void main(String[] args) {
        if (args.length != 1) {
            Console.error("Usage: structuredb /path/to/conf");
            System.exit(1);
        }

        Console.info("Loading configuration from " + args[0]);
        ConfigReader configReader = new ConfigReader(args[0]);

        Properties properties = configReader.getProperties();
        String host = properties.getProperty("host", "127.0.0.1");
        String port = properties.getProperty("port", "9876");
        String poolSize = properties.getProperty("pool-size", "10");
        String dataPath = properties.getProperty("data", "/structuredb");

        Console.info("Using data path " + dataPath);
        createDataDirectoryIfNotExists(dataPath);

        Console.info("Starting StructureDB");
        Console.info("Connector listening on " + host + ":" + port);

        try {
            Thread t = new SDBServer(host, Integer.parseInt(port), Integer.parseInt(poolSize), dataPath);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createDataDirectoryIfNotExists(String dataPath) {
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
