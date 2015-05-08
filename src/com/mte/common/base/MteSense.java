package com.mte.common.base;

import com.mte.util.PropUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by java on 3/25/15.
 */
public class MteSense {

    private PropUtil props = new PropUtil("./config/mtesense.properties");

    private Logger logger = Logger.getLogger(MteSense.class);

    public String getSessionId() {
        return sessionId;
    }

    private String sessionId = null;

    private IOSDriver ios = null;
    private AndroidDriver android = null;

    private static HashMap<String, String> senseMap = new HashMap<String, String>();

    private static String testCaseName = "testcasename";

    public static void markTestCaseName(String testCase) {

        setSenseMap(testCaseName,testCase);
    }


    public static String getTestCaseName(){
        return getSenseMapProperty(testCaseName);
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


    /**
     * base on mtesense.properties to create AppiumDriver include ios and android
     *
     * @return IOSDriver
     */

    public IOSDriver getIOSDriver() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformVersion", props.get("as.ios.platformVersion"));
        capabilities.setCapability("platformName", props.get("as.ios.platformName"));
        capabilities.setCapability("deviceName", props.get("as.ios.deviceName"));
        capabilities.setCapability("app", new File(props.get("as.ios.app.path")));
        try {
            ios = new IOSDriver(new URL(props.get("as.url")), capabilities);
            sessionId = ios.getSessionId().toString();
        } catch (Exception e) {
            logger.error(e);
        }
        return ios;

    }

    /**
     * base on mtesense.properties to create AppiumDriver include ios and android
     *
     * @return AndroidDriver
     */


    public AndroidDriver getAndroidDriver() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformVersion", props.get("as.android.platformVersion"));
        capabilities.setCapability("platformName", props.get("as.android.platformName"));
        capabilities.setCapability("deviceName", props.get("as.android.deviceName"));
        capabilities.setCapability("appPackage", props.get("as.android.appPackage"));
        capabilities.setCapability("appActivity", props.get("as.android.appActivity"));
        capabilities.setCapability("app", new File(props.get("as.android.app.path")));
        try {
            android = new AndroidDriver(new URL(props.get("as.url")), capabilities);
            sessionId = android.getSessionId().toString();
        } catch (Exception e) {
            logger.error(e);
        }
        return android;
    }

}
