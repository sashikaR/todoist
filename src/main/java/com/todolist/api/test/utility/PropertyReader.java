package com.todolist.api.test.utility;

import lombok.extern.log4j.Log4j2;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class PropertyReader {

    public static final String propertyFilePath ="src/test/resources/test.properties";

    public static Properties propertyReader() {

        Properties properties = new Properties();

        try {
            FileReader reader = new FileReader(propertyFilePath);
            properties.load(reader);

        } catch (IOException e) {
            log.error(e);
        }
        return properties;
    }
}
