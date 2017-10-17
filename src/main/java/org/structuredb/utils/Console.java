package org.structuredb.utils;

public class Console {

    public static void info(Object message) {
        System.out.println("[INFO] " + message.toString());
    }

    public static void error(Object message) {
        System.err.println("[ERR] " + message.toString());
    }

    public static void connection(Object message) {
        System.out.println("[CONN] " + message.toString());
    }

    public static void query(Object message) {
        System.out.println("[QUERY] " + message.toString());
    }
}
