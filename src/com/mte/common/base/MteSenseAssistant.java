package com.mte.common.base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.HashMap;

/**
 * Project :  mtesense
 * Created :  java
 * Date    :  6/16/15
 */
public class MteSenseAssistant {

    public static String firefox = "firefox";
    public static String chrome = "chrome";
    public static String ie = "ie";
    public static String ios = "ios";
    public static String android = "android";

    private static HashMap<String, String> senseMap = new HashMap<String, String>();
    private static HashMap<String, WebDriver> senseDriverMap = new HashMap<String, WebDriver>();


    public static void setMteSenseDriverMap(String driverType, WebDriver driver) {
        senseDriverMap.put(driverType, driver);
    }

    public static WebDriver getWebDriver(String driverType) {
        WebDriver driver = null;
        if (senseMap.containsKey(driverType)) {
            driver = senseDriverMap.get(driverType);
        }
        return driver;
    }


    /**
     * Set MteSense main map for property
     *
     * @param key
     * @param value
     */

    private static void setSenseMap(String key, String value) {

        senseMap.put(key, value);

    }

    /**
     * get MteSense main value for property
     *
     * @param key
     */

    private static String getSenseMapProperty(String key) {
        String value = null;
        if (senseMap.containsKey(key)) {
            value = senseMap.get(key);
        }
        return value;
    }


}
