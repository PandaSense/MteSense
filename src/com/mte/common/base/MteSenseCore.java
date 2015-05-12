package com.mte.common.base;

import com.mte.util.DateTimeUtil;
import com.mte.util.FileUtil;
import com.mte.util.PropUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * Project :  mtesense
 * Created :  java
 * Date    :  3/26/15
 */
public class MteSenseCore {

    private PropUtil props = new PropUtil("./config/mtesense.properties");

    private Logger logger = Logger.getLogger(MteSenseCore.class);

    private int pauseTime = Integer.parseInt(props.get("mte.pauseTime.second"));

    private IOSDriver ios = null;
    private AndroidDriver android = null;

    public AppiumDriver getDriver() {
        return driver;
    }

    private AppiumDriver driver = null;

    private String captureImagePath;

    private String testCaseName=null;

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public MteSenseCore(AppiumDriver driver) {
        this.driver = driver;
    }

    public Set<String> getContextHandles(){

        return driver.getContextHandles();
    }

    /**
     * @param name String context name
     */

    public WebDriver context(String name){

        return driver.context(name);
    }

    public void hideKeyboard(){
        driver.hideKeyboard();
    }

    /**
     * wait for the specified element appears with timeout setting.
     *
     * @param locator the element locator on the page
     * @param timeout second
     */

    public boolean isElementPresent(By locator, long timeout) {

        boolean isFine = false;

        long timeBegins = System.currentTimeMillis();

        do {
            try {
                driver.findElement(locator);
                isFine = true;
                break;
            } catch (Exception e) {
                logger.error("isElementPresent error :",e);

            }
            pause(pauseTime);
        } while (System.currentTimeMillis() - timeBegins <= timeout * 1000);

        return isFine;
    }

    public boolean isElementNotPresent(By locator, long timeout) {

        boolean isFine = true;

        long timeBegins = System.currentTimeMillis();

        do {
            try {
                driver.findElement(locator);
                isFine = false;
                break;
            } catch (Exception e) {

                logger.error("isElementNotPresent error :",e);

            }
            pause(pauseTime);
        } while (System.currentTimeMillis() - timeBegins <= timeout * 1000);

        return isFine;
    }


    /**
     * wait for the specified element appears with timeout setting.
     *
     * @param locator the element locator on the page
     */
    public boolean isElementPresent(By locator) {
        return isElementPresent(locator, 0);
    }

    public boolean isElementNotPresent(By locator) {
        return isElementNotPresent(locator, 0);
    }

    /**
     * wait for the specified element appears with timeout setting.
     *
     * @param locator the element locator on the page
     * @param timeout second
     */

    public boolean isElementPresentDisplayed(By locator, long timeout) {

        boolean isFine = false;

        long timeBegins = System.currentTimeMillis();

        do {
            try {
                isFine = driver.findElement(locator).isDisplayed();
                break;
            } catch (Exception e) {
                logger.error("isElementPresentDisplayed error :",e);
            }
            pause(pauseTime);
        } while (System.currentTimeMillis() - timeBegins <= timeout * 1000);

        return isFine;
    }

    /**
     * wait for the specified element appears with timeout setting.
     *
     * @param locator the element locator on the page
     */
    public boolean isElementPresentDisplayed(By locator) {
        return isElementPresentDisplayed(locator, 0);
    }

    /**
     * @param millisecond time to wait, in millisecond
     */
    public void pause(long millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            logger.error("pause error:", e);
        }
    }

    /**
     * Get screencapture and save picture
     *
     * @param filePath
     * @return 无
     */
    public void captureScreenshot(String filePath) {
        File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtil.copy(screenShotFile.getAbsolutePath(), filePath);
        } catch (Exception e) {
            logger.error("Save screencapture failed：", e);
        }
    }

    public void captureScreenshotByCase() {
        File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtil.copy(screenShotFile.getAbsolutePath(), createCaptureFolder(testCaseName) + "/" + testCaseName + DateTimeUtil.getCurrentDateTime()+".png");
        } catch (Exception e) {
            logger.error("Save screencapture failed：", e);
        }
    }

    public void captureScreenshotByCase(String testCaseName) {
        File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtil.copy(screenShotFile.getAbsolutePath(), createCaptureFolder(testCaseName) + "/" + testCaseName + DateTimeUtil.getCurrentDateTime()+".png");
        } catch (Exception e) {
            logger.error("Save screencapture failed：", e);
        }
    }


    public File getCaptureScreenshotFile(){
        File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        return screenShotFile;

    }


    private String createCaptureFolder(String testcasename) {

        String folderName = null;
        try {
            File report_file = new File(props.get("mte.screenCapture.path") + "/" + testcasename);

            if (!report_file.exists()) {
                report_file.mkdir();
            }
            folderName = report_file.getAbsolutePath();
        } catch (Exception e) {
            logger.error("createCaptureFolder error :",e);
            e.printStackTrace();
        }
        return folderName;

    }


    /**
     * wait for specified text within timeout setting, fail if not found.
     *
     * @param contents the element text you want to wait for
     * @param timeout  second
     */

    public boolean isTextContentsDisplayed(String contents, long timeout) {

        boolean isDisplayed = false;

        long timeBegins = System.currentTimeMillis();
        do {
            try {
                if (driver.getPageSource().contains(contents)) {
                    isDisplayed = true;
                    break;
                }
            } catch (Exception e) {
                logger.error("isTextContentsDisplayed error :",e);
            }
            pause(pauseTime);
        } while (System.currentTimeMillis() - timeBegins <= timeout * 1000);

        return isDisplayed;

    }

    public boolean isTextContentsNotDisplayed(String contents, long timeout) {

        boolean isDisplayed = false;

        long timeBegins = System.currentTimeMillis();
        do {
            try {
                if (!driver.getPageSource().contains(contents)) {
                    isDisplayed = true;
                    break;
                }
            } catch (Exception e) {
                logger.error("isTextContentsNotDisplayed error :",e);
            }
            pause(pauseTime);
        } while (System.currentTimeMillis() - timeBegins <= timeout * 1000);

        return isDisplayed;

    }

    /**
     * wait for specified text within timeout setting, fail if not found.
     *
     * @param contents the element text you want to wait for
     */

    public boolean isTextContentsDisplayed(String contents) {

        return isTextContentsDisplayed(contents, 0);
    }

    public boolean isTextContentsNotDisplayed(String contents) {

        return isTextContentsNotDisplayed(contents, 0);
    }

    /**
     * rewrite the findElements method, adding user defined log</BR>
     * 按照指定的定位方式寻找象。
     *
     * @param by      the locator of the elements to be find
     * @param timeout second
     * @return the webelements you want to find
     */

    public List<WebElement> findElements(By by, long timeout) {
        boolean isSucceed = false;
        List<WebElement> elements = null;

        long timeBegins = System.currentTimeMillis();
        do {
            try {
                elements = driver.findElements(by);
                isSucceed = true;

                break;
            } catch (Exception e) {
                logger.error("findElements error :",e);
            }
            pause(pauseTime);
        } while (System.currentTimeMillis() - timeBegins <= timeout * 1000);

        return elements;
    }

    public List<WebElement> findElements(By by) {
        return findElements(by, 0);
    }

    /**
     * rewrite the findElement method, adding user defined log</BR>
     *
     * @param by      the locator of the element to be find
     * @param timeout second
     * @return the first element accord your locator
     */

    public WebElement findElement(By by, long timeout) {
        boolean isSucceed = false;
        WebElement element = null;
        long timeBegins = System.currentTimeMillis();
        do {
            try {
                element = driver.findElement(by);
                isSucceed = true;
                break;
            } catch (Exception e) {
                logger.error("findElement error :",e);
            }
            pause(pauseTime);
        } while (System.currentTimeMillis() - timeBegins <= timeout * 1000);

        return element;
    }

    public WebElement findElement(By by) {
        return findElement(by, 0);
    }


    /**
     * rewrite the getPageSource method, adding user defined log</BR>
     *
     * @return the page source string
     */
    public String getPageSource() {
        String source = driver.getPageSource();
        return source;
    }

    /**
     * rewrite the getAttribute method, adding user defined log</BR>
     *
     * @param element       the webelement you want to operate
     * @param attributeName the name of the attribute you want to get
     * @return the attribute value string
     */
    public String getAttribute(WebElement element, String attributeName) {
        String value = element.getAttribute(attributeName);
        logger.debug("Attribute is [ " + value + " ]");
        return value;
    }

    /**
     * rewrite the getAttribute method, find the element by By and get its
     * attribute value
     *
     * @param by            the locator you want to find the element
     * @param attributeName the name of the attribute you want to get
     * @return the attribute value string
     */
    public String getAttribute(By by, String attributeName) {
        String value = driver.findElement(by).getAttribute(attributeName);
        logger.debug("Attribute is [ " + value + " ]");
        return value;
    }

    /**
     * rewrite the clear method, adding user defined log
     *
     * @param element the webelement you want to operate
     */
    public void clear(WebElement element) {
        element.clear();
    }

    /**
     * rewrite the click method, adding user defined log
     *
     * @param element the webelement you want to operate
     */
    public void click(WebElement element) {
        element.click();
    }

    /**
     * rewrite the sendKeys method, adding user defined log。
     *
     * @param element the webelement you want to operate
     */
    public void sendKeys(WebElement element, String text) {
        element.sendKeys(text);
    }

    public void sendKeysByClear(WebElement element, String text) {
        clear(element);
        element.sendKeys(text);
    }

    /**
     * rewrite the isSelected method, adding user defined log
     *
     * @param element the webelement you want to operate
     * @return the bool value of whether is the WebElement selected
     */
    public boolean isSelected(WebElement element) {
        boolean isSelected = element.isSelected();
        return isSelected;
    }

    /**
     * rewrite the isSelected method, the element to be find by By
     *
     * @param by the locator you want to find the element
     * @return the bool value of whether is the WebElement selected
     */
    public boolean isSelected(By by) {
        boolean isSelected = driver.findElement(by).isSelected();
        return isSelected;
    }

    /**
     * rewrite the isEnabled method, adding user defined log
     *
     * @param element the webelement you want to operate
     * @return the bool value of whether is the WebElement enabled
     */
    public boolean isEnabled(WebElement element) {
        boolean isEnabled = element.isEnabled();
        return isEnabled;
    }

    /**
     * rewrite the isEnabled method, the element to be find by By
     *
     * @param by the locator you want to find the element
     * @return the bool value of whether is the WebElement enabled
     */
    public boolean isEnabled(By by) {
        boolean isEnabled = driver.findElement(by).isEnabled();
        return isEnabled;
    }


    /**
     * rewrite the isDisplayed method, adding user defined log</BR>
     *
     * @param element the webelement you want to operate
     * @return the bool value of whether is the WebElement displayed
     */
    public boolean isDisplayed(WebElement element) {
        boolean isDisplayed = element.isDisplayed();
        return isDisplayed;
    }

    public boolean isDisplayed(By locator) {
        boolean isDisplayed = driver.findElement(locator).isDisplayed();
        return isDisplayed;
    }


    /**
     * rewrite the click method, click on the element to be find by By
     *
     * @param by      the locator you want to find the element
     * @param timeout second
     */
    public void click(By by, long timeout) {
        boolean isSucceed = false;
        try {
            if (isElementPresent(by, timeout)) {
                driver.findElement(by).click();
                isSucceed = true;
            }
        } catch (Exception e) {
            logger.error("click error :",e);
        }
    }

    public void click(By by) {
        click(by, 0);
    }

    /**
     * rewrite the clear method, clear on the element to be find by By</BR>
     *
     * @param by the locator you want to find the element
     */
    public void clear(By by) {
        boolean isSucceed = false;
        try {
            WebElement element = driver.findElement(by);
            element.clear();
            isSucceed = true;
        } catch (Exception e) {
            logger.error("clear error :",e);
        }
    }

    /**
     * select an item from a picklist by index
     *
     * @param by      the locator you want to find the element
     * @param index   the index of the item to be selected
     * @param timeout second
     */
    public void selectByIndex(By by, int index, long timeout) {
        boolean isSucceed = false;
        try {
            if (isElementPresent(by, timeout)) {
                WebElement element = driver.findElement(by);
                Select select = new Select(element);
                select.selectByIndex(index);
                isSucceed = true;
            }
        } catch (Exception e) {
            logger.error("selectByIndex error :",e);
        }

    }

    public void selectByIndex(WebElement element, int index, long timeout) {
        boolean isSucceed = false;
        try {
            Select select = new Select(element);
            select.selectByIndex(index);
            isSucceed = true;

        } catch (Exception e) {
            logger.error("selectByIndex error :",e);
        }

    }

    public void selectByIndex(By by, int index) {
        selectByIndex(by, index, 0);
    }

    public void selectByIndex(WebElement element, int index) {
        selectByIndex(element, index, 0);
    }


    /**
     * Get count for By
     *
     * @param by
     * @param timeout second
     * @return number
     */
    public int getNumberOfElements(By by, long timeout) {
        boolean isSucceed = false;
        int elementCount = 0;
        long timeBegins = System.currentTimeMillis();
        do {
            try {
                elementCount = driver.findElements(by).size();
                isSucceed = true;
                break;
            } catch (Exception e) {
                logger.error("getNumberOfElements error :",e);
            }
            pause(pauseTime);
        } while (System.currentTimeMillis() - timeBegins <= timeout * 1000);
        return elementCount;
    }

    public int getNumberOfElements(By by) {
        return getNumberOfElements(by, 0);
    }

}
