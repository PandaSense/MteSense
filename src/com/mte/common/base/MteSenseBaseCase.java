package com.mte.common.base;

import com.mte.util.PropUtil;
import com.mte.util.ReportUtil;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Project :  mtesense
 * Created :  java
 * Date    :  3/28/15
 */
public class MteSenseBaseCase {

    private PropUtil props = new PropUtil("./config/mtesense.properties");

    private Logger logger = Logger.getLogger(MteSenseBaseCase.class);

    protected WebDriver driver = null;

    private MteSense sense = new MteSense();

    private ReportUtil reporter = null;

    public String getSessionId() {
        return sense.getSessionId();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public AppiumDriver getMobileDriver() {
        return (AppiumDriver) driver;
    }

    protected MteSenseCore asBaseCore;

    public void beforeClass(String driverType) {

        if (driverType == null) {
            driverType = props.get("mte.test.platform=");
        }
        initWebDriver(driverType);
        asBaseCore = new MteSenseCore(driver);
        if(!driverType.equals("ios")&&!driverType.equals("android")){
            asBaseCore.get(props.get("mte.url"),3);
        }
        sense.setMteSenseCore(asBaseCore);
    }

    public void beforeClass(String driverType, DesiredCapabilities capabilities, String url) {

        if (driverType == null) {
            driverType = props.get("mte.test.platform=");
        }
        initWebDriver(driverType, capabilities, url);
        asBaseCore = new MteSenseCore(driver);
        if(!driverType.equals("ios")&&!driverType.equals("android")){
            asBaseCore.get(url,3);
        }
        sense.setMteSenseCore(asBaseCore);
    }

    public void initWebDriver(String driverType) {

        switch (driverType.trim()) {
            case "ios":
                driver = sense.getIOSDriver();
                break;
            case "android":
                driver = sense.getAndroidDriver();
                break;
            case "firefox":
                driver = sense.getFirefoxDriver();
                break;
            case "chrome":
                driver = sense.getChromeDriver();
                break;
            case "ie":
                driver = sense.getIEDriver();
                break;
            default:
                driver = null;
        }
    }


    public void initWebDriver(String driverType, DesiredCapabilities capabilities, String url) {

        switch (driverType.trim()) {
            case "ios":
                driver = sense.getIOSDriver(capabilities, url);
                break;
            case "android":
                driver = sense.getAndroidDriver(capabilities, url);
                break;
            case "firefox":
                driver = sense.getFirefoxDriver(capabilities);
                break;
            case "chrome":
                driver = sense.getChromeDriver(capabilities);
                break;
            case "ie":
                driver = sense.getIEDriver(capabilities);
                break;
            default:
                driver = null;
        }

    }

    public void afterClass() {
        if (reporter != null) {
            reporter.printReport("END");
        }
        driver.quit();
    }

    public void setReporter(ReportUtil reporter) {
        this.reporter = reporter;
        this.reporter.printReport("START");
    }
}
