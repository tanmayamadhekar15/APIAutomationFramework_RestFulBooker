package com.automationtestingrestassured.utils;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class YamlReader {

    public static String yamlFileData(String key){
        {
            try {
                InputStream inputStream = new FileInputStream(new File("src/main/java/com/automationtestingrestassured/resource/yamlFile"));
                Yaml yaml = new Yaml();
                Map<String,Object> data = yaml.load(inputStream);
                System.out.println(data.get(key));
                return (String) data.get(key);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }


    }

}
