package com.mte.util;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Project :  mtesense
 * Created :  java
 * Date    :  3/24/15
 */
public class PropUtil {

    private static Properties properties = null;

    private static ResourceBundle mainViewResource;

    public PropUtil(String path) {
        initialize(path);
    }

    public PropUtil(ResourceBundle resource) {

        this.mainViewResource = resource;

    }

    public String getProperty(String key) {

        String keyValue = null;
        if (mainViewResource.containsKey(key)) {
            keyValue = mainViewResource.getString(key);
        }
        return keyValue;
    }


    private void initialize(String path) {
//        InputStream is = getClass().getClassLoader().getResourceAsStream(path);

        FileInputStream is = null;
        try {
            is = new FileInputStream(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (is == null) {
            return;
        }
        properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * get specified key in config files
     *
     * @param key the key name to get value
     */
    public String get(String key) {
        String keyValue = null;
        if (properties.containsKey(key)) {
            keyValue = (String) properties.get(key);
        }
        return keyValue;
    }
}
