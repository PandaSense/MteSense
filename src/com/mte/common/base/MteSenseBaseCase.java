package com.mte.common.base;

import com.mte.util.PropUtil;
import com.mte.util.ReportUtil;
import io.appium.java_client.AppiumDriver;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Project :  mtesense
 * Created :  java
 * Date    :  3/28/15
 */
public class MteSenseBaseCase {

    private PropUtil props = new PropUtil("./config/mtesense.properties");

    private Logger logger = Logger.getLogger(MteSenseBaseCase.class);

    protected AppiumDriver driver = null;

    private MteSense sense = new MteSense();

    private ReportUtil reporter = null;

    public String getSessionId() {
        return sense.getSessionId();
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    protected MteSenseCore asBaseCore;

    public void beforeClass(String driverType) {

        if (driverType == null) {
            driverType = props.get("mte.mobile.platform");
        }
        if (driverType.equals("ios")) {
            driver = sense.getIOSDriver();
        } else {
            driver = sense.getAndroidDriver();
        }
        asBaseCore = new MteSenseCore(driver);
    }

    public void beforeClass(String driverType,DesiredCapabilities capabilities,String url) {

        if (driverType == null) {
            driverType = props.get("mte.mobile.platform");
        }
        if (driverType.equals("ios")) {
            driver = sense.getIOSDriver(capabilities,url);
        } else {
            driver = sense.getAndroidDriver(capabilities,url);
        }
        asBaseCore = new MteSenseCore(driver);
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
