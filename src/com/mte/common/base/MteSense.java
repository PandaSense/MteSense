package com.mte.common.base;

import com.mte.util.PropUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

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

    private WebDriver driver;

    int pageLoadTimeout = Integer.valueOf(props.get("mte.loadTime.second"));
    int waitTimeout = Integer.valueOf(props.get("mte.timeOut.second"));
    int scriptTimeout = Integer.valueOf(props.get("mte.pauseTime.second"));

    protected static MteSenseCore mteSenseCore = null;

    public MteSenseCore getMteSenseCore() {
        return mteSenseCore;
    }

    public void setMteSenseCore(MteSenseCore mteBaseCore) {
        this.mteSenseCore = mteBaseCore;
    }

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


    public WebDriver getFirefoxDriver() {
        System.setProperty("webdriver.firefox.bin", props.get("mte.firefoxdriver.path"));
        try {
            DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            driver = new FirefoxDriver(capabilities);
            logger.info("start FirefoxDriver");
            driver.manage().timeouts()
                    .pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
            logger.debug("set pageLoadTimeout : " + pageLoadTimeout);
            driver.manage().timeouts()
                    .implicitlyWait(waitTimeout, TimeUnit.SECONDS);
            logger.debug("set waitTimeout : " + waitTimeout);
            driver.manage().timeouts()
                    .setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);
            logger.debug("set scriptTimeout : " + scriptTimeout);
            driver.manage().window().maximize();

        } catch (Exception e) {
            logger.error("Loading Firefox Driver Error : " + e);
        }

        return driver;
    }

    public WebDriver getFirefoxDriver(DesiredCapabilities capabilities) {
        System.setProperty("webdriver.firefox.bin", props.get("mte.firefoxdriver.path"));
        try {
            driver = new FirefoxDriver(capabilities);
            logger.info("start FirefoxDriver");
            driver.manage().timeouts()
                    .pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
            logger.debug("set pageLoadTimeout : " + pageLoadTimeout);
            driver.manage().timeouts()
                    .implicitlyWait(waitTimeout, TimeUnit.SECONDS);
            logger.debug("set waitTimeout : " + waitTimeout);
            driver.manage().timeouts()
                    .setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);
            logger.debug("set scriptTimeout : " + scriptTimeout);
            driver.manage().window().maximize();

        } catch (Exception e) {
            logger.error("Loading Firefox Driver Error : " + e);
        }

        return driver;
    }

    public WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", props.get("mte.chromedriver.path"));
//        System.setProperty("webdriver.chrome.bin", "chrome_dir");
        try {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            driver = new ChromeDriver(capabilities);
            logger.info("start ChromeDriver");
            driver.manage().timeouts()
                    .pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
            logger.debug("set pageLoadTimeout : " + pageLoadTimeout);
            driver.manage().timeouts()
                    .implicitlyWait(waitTimeout, TimeUnit.SECONDS);
            logger.debug("set waitTimeout : " + waitTimeout);
            driver.manage().timeouts()
                    .setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);
            logger.debug("set scriptTimeout : " + scriptTimeout);
            driver.manage().window().maximize();
        } catch (Exception e) {
            logger.error("Loading Firefox Driver Error : " + e);
        }

        return driver;
    }

    public WebDriver getChromeDriver(DesiredCapabilities capabilities) {
        System.setProperty("webdriver.chrome.driver", props.get("mte.chromedriver.path"));
//        System.setProperty("webdriver.chrome.bin", "chrome_dir");
        try {
            driver = new ChromeDriver(capabilities);
            logger.info("start ChromeDriver");
            driver.manage().timeouts()
                    .pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
            logger.debug("set pageLoadTimeout : " + pageLoadTimeout);
            driver.manage().timeouts()
                    .implicitlyWait(waitTimeout, TimeUnit.SECONDS);
            logger.debug("set waitTimeout : " + waitTimeout);
            driver.manage().timeouts()
                    .setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);
            logger.debug("set scriptTimeout : " + scriptTimeout);
            driver.manage().window().maximize();
        } catch (Exception e) {
            logger.error("Loading Firefox Driver Error : " + e);
        }

        return driver;
    }


    public WebDriver getIEDriver() {
        System.setProperty("webdriver.ie.driver", props.get("mte.iedriver.path"));
        try {
            DesiredCapabilities capabilities = DesiredCapabilities
                    .internetExplorer();
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
            capabilities
                    .setCapability(
                            InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                            true);
            driver = new InternetExplorerDriver(capabilities);
            logger.info("start InternetExplorerDriver");
            driver.manage().timeouts()
                    .pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
            logger.debug("set pageLoadTimeout : " + pageLoadTimeout);
            driver.manage().timeouts()
                    .implicitlyWait(waitTimeout, TimeUnit.SECONDS);
            logger.debug("set waitTimeout : " + waitTimeout);
            driver.manage().timeouts()
                    .setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);
            logger.debug("set scriptTimeout : " + scriptTimeout);
            driver.manage().window().maximize();

        } catch (Exception e) {
            logger.error("Loading Firefox Driver Error : " + e);
        }

        return driver;
    }


    public WebDriver getIEDriver(DesiredCapabilities capabilities) {
        System.setProperty("webdriver.ie.driver", props.get("mte.iedriver.path"));
        try {
            driver = new InternetExplorerDriver(capabilities);
            logger.info("start InternetExplorerDriver");
            driver.manage().timeouts()
                    .pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
            logger.debug("set pageLoadTimeout : " + pageLoadTimeout);
            driver.manage().timeouts()
                    .implicitlyWait(waitTimeout, TimeUnit.SECONDS);
            logger.debug("set waitTimeout : " + waitTimeout);
            driver.manage().timeouts()
                    .setScriptTimeout(scriptTimeout, TimeUnit.SECONDS);
            logger.debug("set scriptTimeout : " + scriptTimeout);
            driver.manage().window().maximize();

        } catch (Exception e) {
            logger.error("Loading Firefox Driver Error : " + e);
        }

        return driver;
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
