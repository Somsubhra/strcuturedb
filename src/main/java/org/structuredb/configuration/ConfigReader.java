package org.structuredb.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private String path;

    private Properties properties;

    private InputStream inputStream;

    public ConfigReader(String path) {
        this.path = path;
        this.properties = new Properties();
        process();
    }

    private void process() {
        try {
            this.inputStream = new FileInputStream(this.path);
            this.properties.load(this.inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if(this.inputStream != null) {
                try {
                    this.inputStream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public Properties getProperties() {
        return this.properties;
    }
}
