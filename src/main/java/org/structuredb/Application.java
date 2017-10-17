package org.structuredb;

import org.structuredb.configuration.ConfigReader;
import org.structuredb.connector.TCPServer;
import org.structuredb.utils.Console;

import java.io.IOException;
import java.util.Properties;

public class Application {

    public static void main(String[] args) {
        if(args.length != 1) {
            Console.error("Usage: structuredb /path/to/conf");
            System.exit(1);
        }

        Console.info("Loading configuration from " + args[0]);
        ConfigReader configReader = new ConfigReader(args[0]);

        Properties properties = configReader.getProperties();
        String host = properties.getProperty("host", "127.0.0.1");
        String port = properties.getProperty("port", "9876");

        Console.info("Starting StructureDB");
        Console.info("Connector listening on " + host + ":" + port);

        try {
            Thread t = new TCPServer(host, port);
            t.start();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
