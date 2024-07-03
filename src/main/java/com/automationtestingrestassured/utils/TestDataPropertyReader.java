package com.automationtestingrestassured.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestDataPropertyReader {

    public static String readAKeyFromPropertyFile(String propertyKey) throws IOException {

        FileInputStream fis=new FileInputStream("src/main/java/com/automationtestingrestassured/resource/TestData.properties");
        Properties p=new Properties();
        p.load(fis);
        return p.getProperty(propertyKey);

        /*Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/java/resource/data.properties");
            properties.load(fileInputStream);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return properties.getProperty(key);*/

    }
}
