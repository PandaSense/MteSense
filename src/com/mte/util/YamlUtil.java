package com.mte.util;


import org.ho.yaml.Yaml;

import java.io.FileInputStream;
import java.util.HashMap;

/**
 * Project :  appiumsense
 * Created :  java
 * Date    :  4/10/15
 */
public class YamlUtil {

    private String yamlFilePath;

    private HashMap<String, HashMap<String, String>> elementsMap = new HashMap<String, HashMap<String, String>>();

    public YamlUtil(String yamlFilePath) {
        this.yamlFilePath = yamlFilePath;
        loadYaml();
    }

    private void loadYaml() {

        try {
            elementsMap = Yaml.loadType(new FileInputStream(yamlFilePath),HashMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public HashMap<String, HashMap<String, String>> getElementsMap() {
        return elementsMap;
    }

}
