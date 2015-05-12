package com.mte.common.base;

import com.mte.util.PropUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

/**
 * Project :  mtesense
 * Created :  java
 * Date    :  3/25/15
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

    private String IEDriverServer = System.getProperty("user.dir")
            + "/resource/IEDriverServer.exe";
    private String chromedriver = System.getProperty("user.dir")
            + "/resource/chromedriver.exe";


    public static String FIREFOX_DRIVER = "firefox";
    public static String CHROME_DRIVER = "chrome";
    public static String IOS_DRIVER = "ios";
    public static String ANDROID_DRIVER = "android";
    public static String SAFARI_DRIVER = "safari";
    public static String EXPLORER_DRIVER = "safari";


    private static HashMap<String, String> senseMap = new HashMap<String, String>();

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
        capabilities.setCapability("platformVersion", props.get("mte.ios.platformVersion"));
        capabilities.setCapability("platformName", props.get("mte.ios.platformName"));
        capabilities.setCapability("deviceName", props.get("mte.ios.deviceName"));
        capabilities.setCapability("app", new File(props.get("mte.ios.app.path")));

        return getIOSDriver(capabilities, props.get("mte.url"));
    }

    /**
     * base on mtesense.properties to create AppiumDriver include ios and android
     *
     * @return AndroidDriver
     */


    public AndroidDriver getAndroidDriver() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformVersion", props.get("mte.android.platformVersion"));
        capabilities.setCapability("platformName", props.get("mte.android.platformName"));
        capabilities.setCapability("deviceName", props.get("mte.android.deviceName"));
        capabilities.setCapability("appPackage", props.get("mte.android.appPackage"));
        capabilities.setCapability("appActivity", props.get("mte.android.appActivity"));
        capabilities.setCapability("app", new File(props.get("mte.android.app.path")));

        return getAndroidDriver(capabilities, props.get("mte.url"));
    }

    public IOSDriver getIOSDriver(DesiredCapabilities capabilities, String url) {
        try {
            if (capabilities != null) {
                ios = new IOSDriver(new URL(url), capabilities);
                sessionId = ios.getSessionId().toString();
            } else {
                logger.error("DesiredCapabilities is null, please check with your setting");
                return null;
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return ios;
    }

    public AndroidDriver getAndroidDriver(DesiredCapabilities capabilities, String url) {
        try {
            if (capabilities != null) {
                android = new AndroidDriver(new URL(url), capabilities);
                sessionId = android.getSessionId().toString();
            } else {
                logger.error("DesiredCapabilities is null, please check with your setting");
                return null;
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return android;
    }

}
