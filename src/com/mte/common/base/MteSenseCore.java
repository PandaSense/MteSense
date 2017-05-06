package com.mte.common.base;

import com.mte.component.WebDriverTable;
import com.mte.util.DateTimeUtil;
import com.mte.util.FileUtil;
import com.mte.util.PropUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Project :  mtesense
 * Created :  java
 * Date    :  3/26/15f
 * Note : Some codes come from internet
 */
public class MteSenseCore {

    private PropUtil props = new PropUtil("./config/mtesense.properties");

    private Logger logger = Logger.getLogger(MteSenseCore.class);

    private int pauseTime = Integer.parseInt(props.get("mte.pauseTime.second"));

    public WebDriver getDriver() {
        return driver;
    }

    private WebDriver driver = null;

    private String captureImagePath;

    private String testCaseName = null;

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    private Actions actionDriver;


    public MteSenseCore(WebDriver driver) {
        this.driver = driver;
        actionDriver = new Actions(this.driver);
    }

    public Set<String> getContextHandles() {

        return ((AppiumDriver) driver).getContextHandles();
    }


    /**
     * special slide up
     *
     * @param width (1-99)
     */
    public void slideUP(int width) {

        if (width <= 0 || width > 100) {
            logger.error("The slide width should be between 1 t0 99 .");
        } else {
            int x = ((AppiumDriver) driver).manage().window().getSize().width;
            int y = ((AppiumDriver) driver).manage().window().getSize().height;
            ((AppiumDriver) driver).swipe(x / 10 * width, y / 3 * 2, x / 10 * width, y / 3 * 1, 0);
        }

    }

    /**
     * special slide down
     *
     * @param width (1-99)
     */
    public void slideDown(int width) {
        if (width <= 0 || width > 100) {
            logger.error("The slide width should be between 1 t0 99 .");
        } else {
            int x = ((AppiumDriver) driver).manage().window().getSize().width;
            int y = ((AppiumDriver) driver).manage().window().getSize().height;
            ((AppiumDriver) driver).swipe(x / 10 * width, y / 3 * 1, x / 10 * width, y / 3 * 2, 0);
        }

    }

    /**
     * special slide left
     *
     * @param width (1-99)
     */
    public void slideLeft(int width) {
        if (width <= 0 || width > 100) {
            logger.error("The slide width should be between 1 t0 99 .");
        } else {
            int x = ((AppiumDriver) driver).manage().window().getSize().width;
            int y = ((AppiumDriver) driver).manage().window().getSize().height;
            ((AppiumDriver) driver).swipe(x / 4 * 3, y / 10 * width, x / 4 * 2, y / 10 * width, 0);
        }

    }

    /**
     * special slide right
     *
     * @param width (1-99)
     */
    public void slideRight(int width) {
        if (width <= 0 || width > 100) {
            logger.error("The slide width should be between 1 t0 99 .");
        } else {
            int x = ((AppiumDriver) driver).manage().window().getSize().width;
            int y = ((AppiumDriver) driver).manage().window().getSize().height;
            ((AppiumDriver) driver).swipe(x / 4 * 2, y / 10 * width, x / 4 * 3, y / 10 * width, 0);
        }

    }


    /**
     * up slide 1/4
     */
    public void slideUP() {
        int x = ((AppiumDriver) driver).manage().window().getSize().width;
        int y = ((AppiumDriver) driver).manage().window().getSize().height;
        ((AppiumDriver) driver).swipe(x / 2, y / 3 * 2, x / 2, y / 3 * 1, 0);
    }

    /**
     * down slide 1/4
     */
    public void slideDown() {
        int x = ((AppiumDriver) driver).manage().window().getSize().width;
        int y = ((AppiumDriver) driver).manage().window().getSize().height;
        ((AppiumDriver) driver).swipe(x / 2, y / 3 * 1, x / 2, y / 3 * 2, 0);
    }

    /**
     * left slide 1/2
     */
    public void slideLeft() {
        int x = ((AppiumDriver) driver).manage().window().getSize().width;
        int y = ((AppiumDriver) driver).manage().window().getSize().height;
        ((AppiumDriver) driver).swipe(x / 4 * 3, y / 2, x / 4 * 1, y / 2, 0);
    }

    /**
     * right slide 1/2
     */
    public void slideRight() {
        int x = ((AppiumDriver) driver).manage().window().getSize().width;
        int y = ((AppiumDriver) driver).manage().window().getSize().height;
        ((AppiumDriver) driver).swipe(x / 4 * 1, y / 2, x / 4 * 3, y / 2, 0);
    }


    /**
     * Switch to webview for elements
     */
    public void switchtoWebView() {
        try {
            Set<String> contextNames = ((AppiumDriver) driver).getContextHandles();
            for (String contextName : contextNames) {
                if (contextName.contains("WEBVIEW") || contextName.contains("webview")) {
                    ((AppiumDriver) driver).context(contextName);
                    System.out.println("Webdriver forward to webview");
                }
            }
        } catch (Exception e) {
            logger.error("Switch to webview error !");
            e.printStackTrace();
        }
    }


    /**
     * @param name String context name
     */

    public WebDriver context(String name) {

        return ((AppiumDriver) driver).context(name);
    }

    /**
     * Close app in mobile platform
     */
    public void closeApp() {
        ((AppiumDriver) driver).closeApp();
    }


    /**
     * Close app in mobile platform
     */
    public void closeIosApp() {
        ((IOSDriver) driver).closeApp();
    }


    public void hideKeyboard() {
        ((AppiumDriver) driver).hideKeyboard();
    }

    /**
     * Get element for IOS 10+ version with Appium 1.6.3 +
     *
     * @param locatorType
     * @param locatorValue
     * @return
     */


    public WebElement findElementForIos10(String locatorType, String locatorValue) {

        WebElement element = null;
        switch (locatorType) {

            case "iOSNsPredicateString":
                element = ((IOSDriver) driver).findElement(MobileBy.iOSNsPredicateString(locatorValue));
                break;
            case "className":
                element = ((IOSDriver) driver).findElement(MobileBy.className(locatorValue));
                break;
            case "iOSClassChain":
                element = ((IOSDriver) driver).findElement(MobileBy.iOSClassChain(locatorValue));
                break;
            default:
                element = null;
                break;
        }
        return element;

    }


    /**
     * IOSDriver
     *
     * @param using the element using
     */

    public WebElement findElementsByIosUIAutomation(String using) {


        return ((IOSDriver) driver).findElementByIosUIAutomation(using);
    }


    /**
     * IOSDriver
     *
     * @param using the element using
     */

    public List<WebElement> findElementByIosUIAutomation(String using) {


        return ((IOSDriver) driver).findElementsByIosUIAutomation(using);
    }


    /**
     * AndroidDriver
     *
     * @param using the element using
     */

    public WebElement findElementByAndroidUIAutomator(String using) {


        return ((AndroidDriver) driver).findElementByAndroidUIAutomator(using);
    }


    /**
     * AndroidDriver
     *
     * @param using the element using
     */

    public List<WebElement> findElementsByAndroidUIAutomator(String using) {


        return ((AndroidDriver) driver).findElementsByAndroidUIAutomator(using);
    }


    /*
     *
     *Selenium and Appium common mathods include common webdriver
     *
     */


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
                logger.error("isElementPresent error :", e);

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

                logger.error("isElementNotPresent error :", e);

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
                logger.error("isElementPresentDisplayed error :", e);
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
            FileUtil.copy(screenShotFile.getAbsolutePath(), createCaptureFolder(testCaseName) + "/" + testCaseName + DateTimeUtil.getCurrentDateTime() + ".png");
        } catch (Exception e) {
            logger.error("Save screencapture failed：", e);
        }
    }

    public void captureScreenshotByCase(String testCaseName) {
        File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtil.copy(screenShotFile.getAbsolutePath(), createCaptureFolder(testCaseName) + "/" + testCaseName + DateTimeUtil.getCurrentDateTime() + ".png");
        } catch (Exception e) {
            logger.error("Save screencapture failed：", e);
        }
    }


    public File getCaptureScreenshotFile() {
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
            logger.error("createCaptureFolder error :", e);
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
                logger.error("isTextContentsDisplayed error :", e);
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
                logger.error("isTextContentsNotDisplayed error :", e);
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
     * rewrite the findElements method, adding user defined log
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
                logger.error("findElements error :", e);
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
                logger.error("findElement error :", e);
            }
            pause(pauseTime);
        } while (System.currentTimeMillis() - timeBegins <= timeout * 1000);

        return element;
    }

    public WebElement findElement(By by) {
        return findElement(by, 3);
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
            logger.error("click error :", e);
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
            logger.error("clear error :", e);
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
            logger.error("selectByIndex error :", e);
        }

    }

    public void selectByIndex(WebElement element, int index, long timeout) {
        boolean isSucceed = false;
        try {
            Select select = new Select(element);
            select.selectByIndex(index);
            isSucceed = true;

        } catch (Exception e) {
            logger.error("selectByIndex error :", e);
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
                logger.error("getNumberOfElements error :", e);
            }
            pause(pauseTime);
        } while (System.currentTimeMillis() - timeBegins <= timeout * 1000);
        return elementCount;
    }

    public int getNumberOfElements(By by) {
        return getNumberOfElements(by, 0);
    }

    /**
     * rewrite the get method, adding user defined log</BR>
     *
     * @param url        the url you want to open.
     * @param retryCount retry times when load timeout occuers.
     */

    public void get(String url, int retryCount) {
        boolean isSucceed = false;
        for (int i = 0; i < retryCount; i++) {
            try {
                driver.get(url);
                logger.debug("navigate to url [ " + url + " ]");
                isSucceed = true;
                break;
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }

    public void get(String url, int retryCount, WebDriver driver) {
        boolean isSucceed = false;
        for (int i = 0; i < retryCount; i++) {
            try {
                driver.get(url);
                logger.debug("navigate to url [ " + url + " ]");
                isSucceed = true;
                break;
            } catch (Exception e) {
                logger.error(e);
            }
        }
    }

    /**
     * wait for specified text within timeout setting, fail if not found.
     *
     * @param expectedText the element text you want to wait for
     * @param timeout      second
     */
    public boolean isContentNotAppeared(String expectedText, long timeout) {
        boolean isSucceed = true;
        logger.debug("expectedText is : " + expectedText);

        long timeBegins = System.currentTimeMillis();
        do {
            try {
                driver.findElement(By.xpath("//*[contains(.,'" + expectedText
                        + "')]"));
                isSucceed = false;
                logger.debug("find expectedText [" + expectedText + "] success");
                break;
            } catch (Exception e) {
                logger.error(e);
            }
            pause(pauseTime);
        } while (System.currentTimeMillis() - timeBegins <= timeout * 1000);

        return isSucceed;
    }

    /**
     * wait for specified text within timeout setting, fail if not found.
     *
     * @param expectedText the element text you want to wait for
     */
    public boolean isContentNotAppeared(String expectedText) {
        return isContentNotAppeared(expectedText, 0);
    }


    /**
     * rewrite the getTitle method, adding user defined log
     *
     * @return the title on your current session
     */
    public String getWindowTitle() {
        String title = driver.getTitle();
        logger.debug("current window title is :" + title);
        return title;
    }


    /**
     * rewrite the getCurrentUrl method, adding user defined log
     *
     * @return the url on your current session
     */
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        logger.debug("current page url is :" + url);
        return url;
    }

    /**
     * rewrite the getWindowHandles method, adding user defined log<
     *
     * @return the window handles set
     */
    public Set<String> getWindowHandles() {
        Set<String> handles = driver.getWindowHandles();
        logger.debug("window handles count are:" + handles.size());
        logger.debug("window handles are: " + handles.toString());
        return handles;
    }


    /**
     * rewrite the getWindowHandle method, adding user defined log
     *
     * @return the window handle string
     */
    public String getWindowHandle() {
        String handle = driver.getWindowHandle();
        logger.debug("current window handle is:" + handle);
        return handle;
    }


    /**
     * rewrite the getTagName method, adding user defined log
     *
     * @param element the webelement you want to operate
     * @return the tagname string
     */
    public String getTagName(WebElement element) {
        String tagName = element.getTagName();
        logger.debug("element's TagName is: " + tagName);
        return tagName;
    }

    /**
     * rewrite the getTagName method, find the element by By and get its tag
     *
     * @param by the locator you want to find the element
     * @return the tagname string
     */
    public String getTagName(By by) {
        String tagName = driver.findElement(by).getTagName();
        logger.debug("element [ " + by.toString() + " ]'s TagName is: "
                + tagName);
        return tagName;
    }

    /**
     * rewrite the getText method, adding user defined log
     *
     * @param element the webelement you want to operate
     */
    public String getText(WebElement element) {
        String text = element.getText();
        logger.debug("element text is:" + text);
        return text;
    }

    /**
     * rewrite the getText method, find the element by By and get its own
     * text
     *
     * @param by the locator you want to find the element
     * @return the text string
     */
    public String getText(By by) {
        String text = driver.findElement(by).getText();
        logger.debug("element [ " + by.toString() + " ]'s text is: " + text);
        return text;
    }


    /**
     * get its css property value
     *
     * @param element      the webelement you want to operate
     * @param propertyName the name of the property you want to get
     * @return the css property value string
     */
    public String getCssValue(WebElement element, String propertyName) {
        String cssValue = element.getCssValue(propertyName);
        logger.debug("element's css [" + propertyName + "] value is:"
                + cssValue);
        return cssValue;
    }

    /**
     * find the element by By and get its css property value
     *
     * @param by           the locator you want to find the element
     * @param propertyName the name of the property you want to get
     * @return the css property value string
     */
    public String getCssValue(By by, String propertyName) {
        String cssValue = driver.findElement(by).getCssValue(propertyName);
        logger.debug("element [ " + by.toString() + " ]'s css[" + propertyName
                + "] value is: " + cssValue);
        return cssValue;
    }

    /**
     * construct with parameters initialize.
     * <p/>
     * the WebDriver instance.
     *
     * @param tabFinder  the By locator of the table.
     * @param bodyOrHead choice of table body and head to operate.
     */
    public WebDriverTable getTable(By tabFinder, String bodyOrHead) {
        return new WebDriverTable(driver, tabFinder, bodyOrHead);
    }

    /**
     * construct with parameters initialize.
     * <p/>
     * the WebDriver instance.
     *
     * @param tabFinder the By locator of the table.
     */
    public WebDriverTable getTable(By tabFinder) {
        return new WebDriverTable(driver, tabFinder);
    }

    /**
     * Description: clear error handles does not actruely.
     *
     * @param windowHandles the window handles Set.
     */
    private Set<String> clearHandleCache(Set<String> windowHandles) {
        List<String> errors = new ArrayList<String>();
        for (String handle : windowHandles) {
            try {
                driver.switchTo().window(handle);
                driver.getTitle();
            } catch (Exception e) {
                errors.add(handle);
                logger.debug("window handle " + handle
                        + " does not exist acturely!");
            }
        }
        for (int i = 0; i < errors.size(); i++) {
            windowHandles.remove(errors.get(i));
        }
        return windowHandles;
    }


    /**
     * judge if the browser is existing, using part of the page title
     *
     * @param browserTitle part of the title to see if browser exists
     */
    public boolean browserExists(String browserTitle) {
        try {
            String defaultHandle = driver.getWindowHandle();
            Set<String> windowHandles = driver.getWindowHandles();
            windowHandles = clearHandleCache(windowHandles);
            for (int i = 0; i <= 20; i++) {
                pause(500);
                if (driver.getWindowHandles().equals(windowHandles)) {
                    break;
                }
                if (i == 20 && !driver.getWindowHandles().equals(windowHandles)) {
                    return false;
                }
            }
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
                if (driver.getTitle().contains(browserTitle)) {
                    driver.switchTo().window(defaultHandle);
                    return true;
                }
            }
            driver.switchTo().window(defaultHandle);
        } catch (Exception e) {
            logger.error(e);
        }
        return false;
    }

    /**
     * judge if the browser is present by title reg pattern in specified
     * seconds
     *
     * @param browserTitle part of the title to see if browser exists
     * @param timeout      timeout in seconds
     */
    public boolean isBrowserExists(String browserTitle, int timeout) {
        boolean isSucceed = false;
        long timeBegins = System.currentTimeMillis();
        do {
            try {
                isSucceed = browserExists(browserTitle);
                break;
            } catch (Exception e) {
                logger.error(e);
            }
        } while (System.currentTimeMillis() - timeBegins <= timeout * 1000);
        return isSucceed;
    }

    /**
     * judge if the browser is present by title reg pattern in specified
     * seconds
     *
     * @param browserTitle part of the title to see if browser exists
     */
    public boolean isBrowserExists(String browserTitle) {
        return isBrowserExists(browserTitle, 0);
    }

    /**
     * maximize browser window
     */
    public void windowMaximize() {
        driver.manage().window().maximize();
    }


    /**
     * select default window and default frame
     */
    public void selectDefaultFrame() {
        driver.switchTo().defaultContent();
    }


    /**
     * switch to window by title
     *
     * @param windowTitle the title of the window to be switched to
     */
    public void selectWindow(String windowTitle) {
        boolean isSucceed = false;
        try {
            Set<String> windowHandles = driver.getWindowHandles();
            windowHandles = clearHandleCache(windowHandles);
            for (String handle : windowHandles) {
                driver.switchTo().window(handle);
                String title = driver.getTitle();
                if (windowTitle.equals(title)) {
                    selectDefaultFrame();
                    isSucceed = true;
                    break;
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * switch to window by handle
     *
     * @param windowHandle the handle of the window to be switched to
     */
    public void selectWindowHandle(String windowHandle) {
        boolean isSucceed = false;
        try {
            Set<String> windowHandles = driver.getWindowHandles();
            windowHandles = clearHandleCache(windowHandles);
            for (String handle : windowHandles) {
                if (windowHandle.equals(handle)) {
                    driver.switchTo().window(handle);
                    selectDefaultFrame();
                    isSucceed = true;
                    break;
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * switch to window by title
     *
     * @param windowTitle the title of the window to be switched to.
     * @param timeout     time to wait for the window appears, unit of seconds.
     */
    public void selectWindowWithTimeout(String windowTitle, int timeout) {
        if (isBrowserExists(windowTitle, timeout)) {
            selectWindow(windowTitle);
        }
    }


    /**
     * Description: switch to a window handle that exists now.</BR>
     */
    public void selectExistWindow() {
        Set<String> windowHandles = driver.getWindowHandles();
        windowHandles = clearHandleCache(windowHandles);
        String exist_0 = windowHandles.toArray()[0].toString();
        if (!(exist_0 == null) && (!"".equalsIgnoreCase(exist_0))) {
            driver.switchTo().window(exist_0);
        } else {
            logger.debug("no opened windows!");
        }
    }

    /**
     * close window by window title and its index if has the same title, by
     * string full pattern
     *
     * @param windowTitle the title of the window to be closed.
     * @param index       the index of the window which shared the same title, begins
     *                    with 1.
     */
    public void closeWindow(String windowTitle, int index) {
        boolean isSucceed = false;
        try {
            List<String> winList = new ArrayList<String>();
            Set<String> windowHandles = driver.getWindowHandles();
            windowHandles = clearHandleCache(windowHandles);
            for (String handle : windowHandles) {
                driver.switchTo().window(handle);
                if (windowTitle.equals(driver.getTitle())) {
                    winList.add(handle);
                }
            }
            driver.switchTo().window(winList.get(index - 1));
            driver.close();
            logger.debug("window [ " + windowTitle + " ] closed by index ["
                    + index + "]");
            isSucceed = true;
        } catch (Exception e) {
            logger.error(e);
        }
    }


    /**
     * close the last window by the same window title, by string full
     * pattern
     *
     * @param windowTitle the title of the window to be closed.
     */
    public void closeWindow(String windowTitle) {
        boolean isSucceed = false;
        try {
            Set<String> windowHandles = driver.getWindowHandles();
            windowHandles = clearHandleCache(windowHandles);
            for (String handle : windowHandles) {
                driver.switchTo().window(handle);
                if (windowTitle.equals(driver.getTitle())) {
                    driver.close();
                    break;
                }
            }
            logger.debug("window [ " + windowTitle + " ] closed ");
            isSucceed = true;
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * close windows except specified window title, by string full pattern</BR>
     *
     * @param windowTitle the title of the window not to be closed
     */
    public void closeWindowExcept(String windowTitle) {
        boolean isSucceed = false;
        try {
            Set<String> windowHandles = driver.getWindowHandles();
            windowHandles = clearHandleCache(windowHandles);
            for (String handle : windowHandles) {
                driver.switchTo().window(handle);
                String title = driver.getTitle();
                if (!windowTitle.equals(title)) {
                    driver.close();
                }
            }
            logger.debug("all windows closed except [ " + windowTitle + " ]");
            isSucceed = true;
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * close window by specified window hanlde, by string full pattern
     *
     * @param windowHandle the hanlde of the window to be closed.
     */
    public void closeWindowHandle(String windowHandle) {
        boolean isSucceed = false;
        try {
            Set<String> windowHandles = driver.getWindowHandles();
            windowHandles = clearHandleCache(windowHandles);
            for (String handle : windowHandles) {
                if (windowHandle.equals(handle)) {
                    driver.switchTo().window(handle);
                    driver.close();
                    break;
                }
            }
            logger.debug("window [ " + windowHandle + " ] closed ");
            isSucceed = true;
        } catch (Exception e) {
            logger.error(e);
        }
    }


    /**
     * close windows except specified window hanlde, by string full pattern</BR>
     *
     * @param windowHandle the hanlde of the window not to be closed.
     */
    public void closeWindowExceptHandle(String windowHandle) {
        boolean isSucceed = false;
        try {
            Set<String> windowHandles = driver.getWindowHandles();
            windowHandles = clearHandleCache(windowHandles);
            for (String handle : windowHandles) {
                if (!windowHandle.equals(handle)) {
                    driver.switchTo().window(handle);
                    driver.close();
                }
            }
            logger.debug("all windows closed except handle [ " + windowHandle
                    + " ]");
            isSucceed = true;
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * select a frame by index
     *
     * @param index   the index of the frame to select
     * @param timeout 超时时间，单位：秒
     */
    public void selectFrame(int index, long timeout) {
        boolean isSucceed = false;
        long timeBegins = System.currentTimeMillis();
        do {
            try {
                selectDefaultFrame();
                driver.switchTo().frame(index);
                logger.debug("select frame by index [ " + index + " ]");
                isSucceed = true;
                break;
            } catch (Exception e) {
                logger.error(e);
            }
            pause(pauseTime);
        } while (System.currentTimeMillis() - timeBegins <= timeout * 1000);
    }

    /**
     * select a frame by index
     *
     * @param index the index of the frame to select
     */
    public void selectFrame(int index) {
        selectFrame(index, 0);
    }


    /**
     * select a frame by name or id
     *
     * @param nameOrId the name or id of the frame to select
     * @param timeout  超时时间，单位：秒
     */
    public void selectFrame(String nameOrId, long timeout) {
        boolean isSucceed = false;
        long timeBegins = System.currentTimeMillis();
        do {
            try {
                selectDefaultFrame();
                driver.switchTo().frame(nameOrId);
                logger.debug("select frame by name or id [ " + nameOrId + " ]");
                isSucceed = true;
                break;
            } catch (Exception e) {
                logger.error(e);
            }
            pause(pauseTime);
        } while (System.currentTimeMillis() - timeBegins <= timeout * 1000);
    }

    /**
     * select a frame by name or id
     *
     * @param nameOrId the name or id of the frame to select
     */
    public void selectFrame(String nameOrId) {
        selectFrame(nameOrId, 0);
    }


//    /**
//     * select a frame by frame element locator: By
//     *
//     * @param by      the frame element locator
//     * @param timeout 超时时间，单位：秒
//     */
//    public void selectFrame(By by, long timeout) {
//        boolean isSucceed = false;
//        long timeBegins = System.currentTimeMillis();
//        do {
//            try {
//                selectDefaultFrame();
//                driver.switchTo().frame(driver.findElement(by));
//                logger.debug("select frame by frame locator [ " + by.toString()
//                        + " ]");
//                isSucceed = true;
//                break;
//            } catch (Exception e) {
//                logger.error(e);
//            }
//            pause(pauseTime);
//        } while (System.currentTimeMillis() - timeBegins <= timeout * 1000);
//    }
//
//    /**
//     * select a frame by frame element locator: By
//     *
//     * @param by the frame element locator
//     */
//    public void selectFrame(By by) {
//        selectFrame(by, 0);
//    }

    /**
     * select a frame by frame tagName: tagName
     *
     * @param tagName the frame tagName,such as : frame or iframe
     * @param index   the frame index,begin with 0
     * @param timeout 超时时间，单位：秒
     */
    public void selectFrame(String tagName, int index, long timeout) {
        boolean isSucceed = false;
        long timeBegins = System.currentTimeMillis();
        do {
            try {
                List<WebElement> frames = driver.findElements(By
                        .tagName(tagName));
                selectDefaultFrame();
                driver.switchTo().frame(frames.get(index));
                logger.debug("select frame by tagName [ " + tagName + " ]");
                isSucceed = true;
                break;
            } catch (Exception e) {
                logger.error(e);
            }
            pause(pauseTime);
        } while (System.currentTimeMillis() - timeBegins <= timeout * 1000);
    }

    /**
     * select a frame by frame tagName: tagName
     *
     * @param tagName the frame element locator
     * @param index   the frame index,begin with 0
     */
    public void selectFrame(String tagName, int index) {
        selectFrame(tagName, index, 0);
    }


    /**
     * select a frame by index
     *
     * @param index   the index of the frame to select
     * @param timeout
     */
    public void selectFrameNoDefault(int index, long timeout) {
        boolean isSucceed = false;
        long timeBegins = System.currentTimeMillis();
        do {
            try {
                driver.switchTo().frame(index);
                logger.debug("select frame by index [ " + index + " ]");
                isSucceed = true;
                break;
            } catch (Exception e) {
                logger.error(e);
            }
            pause(pauseTime);
        } while (System.currentTimeMillis() - timeBegins <= timeout * 1000);
    }

    /**
     * select a frame by index
     *
     * @param index the index of the frame to select
     */
    public void selectFrameNoDefault(int index) {
        selectFrameNoDefault(index, 0);
    }


    /**
     * select a frame by name or id
     *
     * @param nameOrId the name or id of the frame to select
     * @param timeout  超时时间，单位：秒
     */
    public void selectFrameNoDefault(String nameOrId, long timeout) {
        boolean isSucceed = false;
        long timeBegins = System.currentTimeMillis();
        do {
            try {
                driver.switchTo().frame(nameOrId);
                logger.debug("select frame by name or id [ " + nameOrId + " ]");
                isSucceed = true;
                break;
            } catch (Exception e) {
                logger.error(e);
            }
            pause(pauseTime);
        } while (System.currentTimeMillis() - timeBegins <= timeout * 1000);
    }

    /**
     * select a frame by name or id
     *
     * @param nameOrId the name or id of the frame to select
     */
    public void selectFrameNoDefault(String nameOrId) {
        selectFrameNoDefault(nameOrId, 0);
    }


//    /**
//     * select a frame by frame element locator: By
//     *
//     * @param by      the frame element locator
//     * @param timeout 超时时间，单位：秒
//     */
//    public void selectFrameNoDefault(By by, long timeout) {
//        boolean isSucceed = false;
//        long timeBegins = System.currentTimeMillis();
//        do {
//            try {
//                driver.switchTo().frame(driver.findElement(by));
//                logger.debug("select frame by frame locator [ " + by.toString()
//                        + " ]");
//                isSucceed = true;
//                break;
//            } catch (Exception e) {
//                logger.error(e);
//            }
//            pause(pauseTime);
//        } while (System.currentTimeMillis() - timeBegins <= timeout * 1000);
//    }
//
//    /**
//     * select a frame by frame element locator: By
//     *
//     * @param by the frame element locator
//     */
//    public void selectFrameNoDefault(By by) {
//        selectFrameNoDefault(by, 0);
//    }


    /**
     * select a frame by frame tagName: tagName
     *
     * @param tagName the frame tagName,such as : frame or iframe
     * @param index   the frame index,begin with 0
     * @param timeout
     */
    public void selectFrameNoDefault(String tagName, int index, long timeout) {
        boolean isSucceed = false;
        long timeBegins = System.currentTimeMillis();
        do {
            try {
                List<WebElement> frames = driver.findElements(By
                        .tagName(tagName));
                driver.switchTo().frame(frames.get(index));
                logger.debug("select frame by tagName [ " + tagName + " ]");
                isSucceed = true;
                break;
            } catch (Exception e) {
                logger.error(e);
            }
            pause(pauseTime);
        } while (System.currentTimeMillis() - timeBegins <= timeout * 1000);
    }

    /**
     * select a frame by frame tagName: tagName
     *
     * @param tagName the frame element locator
     * @param index   the frame index,begin with 0
     */
    public void selectFrameNoDefault(String tagName, int index) {
        selectFrame(tagName, index, 0);
    }

    /**
     * edit a content editable iframe
     *
     * @param index the index of the frame to select
     * @param text  the text string to be input
     */
    protected void editFrameText(int index, String text) {
        boolean isSucceed = false;
        try {
            driver.switchTo().frame(index);
            driver.switchTo().activeElement().sendKeys(text);
            logger.debug("input text [ " + text + " ] to frame by index [ "
                    + index + " ]");
            isSucceed = true;
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * edit a content editable iframe
     *
     * @param nameOrId the name or id of the frame to select
     * @param text     the text string to be input
     */
    protected void editFrameText(String nameOrId, String text) {
        boolean isSucceed = false;
        try {
            driver.switchTo().frame(nameOrId);
            driver.switchTo().activeElement().sendKeys(text);
            logger.debug("input text [ " + text + " ] to frame [ " + nameOrId
                    + " ]");
            isSucceed = true;
        } catch (Exception e) {
            logger.error(e);
        }
    }

//    /**
//     * edit a content editable iframe
//     *
//     * @param by   the frame element locaotr
//     * @param text the text string to be input
//     */
//    protected void editFrameText(By by, String text) {
//        boolean isSucceed = false;
//        try {
//            driver.switchTo().frame(driver.findElement(by));
//            driver.switchTo().activeElement().sendKeys(text);
//            logger.debug("input text [ " + text + " ] to frame [ "
//                    + by.toString() + " ]");
//            isSucceed = true;
//        } catch (Exception e) {
//            logger.error(e);
//        }
//    }


    /**
     * doubleclick on the element
     *
     * @param by      the locator you want to find the element
     * @param timeout 超时时间，单位：秒
     */
    public void doubleClick(By by, long timeout) {
        boolean isSucceed = false;
        try {
            if (isElementPresent(by, timeout)) {
                actionDriver.doubleClick(driver.findElement(by));
                actionDriver.perform();
                logger.debug("doubleClick on element [ " + by.toString()
                        + " ] ");
                isSucceed = true;
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * doubleclick on the element
     *
     * @param by the locator you want to find the element
     */
    public void doubleClick(By by) {
        doubleClick(by, 0);
    }


    /**
     * moveToElement
     *
     * @param by      the locator you want to find the element
     * @param timeout 超时时间，单位：秒
     */
    public void moveToElement(By by, long timeout) {
        boolean isSucceed = false;
        try {
            if (isElementPresent(by, timeout)) {
                actionDriver.moveToElement(driver.findElement(by));
                actionDriver.perform();
                logger.debug("moveToElement [ " + by.toString() + " ] ");
                isSucceed = true;
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * moveToElement
     *
     * @param by the locator you want to find the element
     */
    public void moveToElement(By by) {
        moveToElement(by, 0);
    }

    /**
     * right click on the element
     *
     * @param by      the locator you want to find the element
     * @param timeout seconds
     */
    public void rightClick(By by, long timeout) {
        boolean isSucceed = false;
        try {
            if (isElementPresent(by, timeout)) {
                actionDriver.contextClick(driver.findElement(by));
                actionDriver.perform();
                logger.debug("rightClick on element [ " + by.toString() + " ] ");
                isSucceed = true;
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * right click on the element
     *
     * @param by the locator you want to find the element
     */
    public void rightClick(By by) {
        rightClick(by, 0);
    }


    /**
     * rewrite the submit method, submit on the element to be find by By
     *
     * @param by      the locator you want to find the element
     * @param timeout seconds
     */
    public void submitForm(By by, long timeout) {
        boolean isSucceed = false;
        try {
            if (isElementPresent(by, timeout)) {
                driver.findElement(by).submit();
                logger.debug("submit on element [ " + by.toString() + " ]");
                isSucceed = true;
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * rewrite the submit method, submit on the element to be find by By
     *
     * @param by the locator you want to find the element
     */
    public void submitForm(By by) {
        submitForm(by, 0);
    }

    /**
     * rewrite the sendKeys method, sendKeys on the element to be find by
     * By
     *
     * @param by      the locator you want to find the element
     * @param text    the text you want to input to element
     * @param timeout 超时时间，单位：秒
     */
    public void sendKeysAppend(By by, String text, long timeout) {
        boolean isSucceed = false;
        try {
            if (isElementPresent(by, timeout)) {
                driver.findElement(by).sendKeys(text);
                logger.debug("input text [ " + text + " ] to element [ "
                        + by.toString() + " ]");
                isSucceed = true;
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * rewrite the sendKeys method, sendKeys on the element to be find by
     * By
     *
     * @param by   the locator you want to find the element
     * @param text the text you want to input to element
     */
    public void sendKeysAppend(By by, String text) {
        sendKeysAppend(by, text, 0);
    }

    /**
     * rewrite the sendKeys method, sendKeys on the element to be find by
     * By
     *
     * @param by      the locator you want to find the element
     * @param text    the text you want to input to element
     * @param timeout 超时时间，单位：秒
     */
    public void sendKeys(By by, String text, long timeout) {
        boolean isSucceed = false;
        try {
            if (isElementPresent(by, timeout)) {
                clear(by);
                driver.findElement(by).sendKeys(text);
                logger.debug("input text [ " + text + " ] to element [ "
                        + by.toString() + " ]");
                isSucceed = true;
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * rewrite the sendKeys method, sendKeys on the element to be find by
     * By
     *
     * @param by   the locator you want to find the element
     * @param text the text you want to input to element
     */
    public void sendKeys(By by, String text) {
        sendKeys(by, text, 0);
    }


    /**
     * select an item from a picklist by item value
     *
     * @param by        the locator you want to find the element
     * @param itemValue the item value of the item to be selected
     * @param timeout   seconds
     */
    public void selectByValue(By by, String itemValue, long timeout) {
        boolean isSucceed = false;
        try {
            if (isElementPresent(by, timeout)) {
                WebElement element = driver.findElement(by);
                Select select = new Select(element);
                select.selectByValue(itemValue);
                logger.debug("item selected by item value [ " + itemValue
                        + " ] on [ " + by.toString() + " ]");
                isSucceed = true;
            }
        } catch (Exception e) {
            logger.error(e);
        }

    }

    /**
     * select an item from a picklist by item value
     *
     * @param by        the locator you want to find the element
     * @param itemValue the item value of the item to be selected
     */
    public void selectByValue(By by, String itemValue) {
        selectByValue(by, itemValue, 0);
    }


    /**
     * select an item from a picklist by item value
     *
     * @param by      the locator you want to find the element
     * @param text    the item value of the item to be selected
     * @param timeout 超时时间，单位：秒
     */
    public void selectByVisibleText(By by, String text, long timeout) {
        boolean isSucceed = false;
        try {
            if (isElementPresent(by, timeout)) {
                WebElement element = driver.findElement(by);
                Select select = new Select(element);
                select.selectByVisibleText(text);
                logger.debug("item selected by visible text [ " + text
                        + " ] on [ " + by.toString() + " ]");
                isSucceed = true;
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * select an item from a picklist by item value
     *
     * @param by   the locator you want to find the element
     * @param text the item value of the item to be selected
     */
    public void selectByVisibleText(By by, String text) {
        selectByVisibleText(by, text, 0);
    }


    /**
     * set the checkbox on or off
     *
     * @param by      the locator you want to find the element
     * @param onOrOff on or off to set the checkbox
     * @param timeout seconds
     */
    public void setCheckBox(By by, String onOrOff, long timeout) {
        boolean isSucceed = false;
        try {
            if (isElementPresent(by, timeout)) {
                WebElement checkBox = driver.findElement(by);
                if ((onOrOff.toLowerCase().contains("on") && !checkBox
                        .isSelected())
                        || (onOrOff.toLowerCase().contains("off") && checkBox
                        .isSelected())) {
                    checkBox.click();
                    logger.debug("the checkbox [ " + by.toString()
                            + " ] is set to [ " + onOrOff.toUpperCase() + " ]");
                } else {
                    if ((onOrOff.toLowerCase().contains("on") && checkBox
                            .isSelected())) {
                        logger.debug("the checkbox [ " + by.toString()
                                + " ] is already set to [ " + onOrOff.toUpperCase()
                                + " ]");
                    }

                    if ((onOrOff.toLowerCase().contains("off") && !checkBox
                            .isSelected())) {
                        logger.debug("the checkbox [ " + by.toString()
                                + " ] is already set to [ " + onOrOff.toUpperCase()
                                + " ]");
                    }
                }
                isSucceed = true;
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * set the checkbox on or off
     *
     * @param by      the locator you want to find the element
     * @param onOrOff on or off to set the checkbox
     */
    public void setCheckBox(By by, String onOrOff) {
        setCheckBox(by, onOrOff, 0);
    }


    /**
     * set the RadioGroup on or off
     *
     * @param by      the locator you want to find the element
     * @param onOrOff on or off to set the RadioGroup
     * @param timeout seconds
     */
    public void setRadioGroup(By by, String onOrOff, long timeout) {
        boolean isSucceed = false;
        try {
            if (isElementPresent(by, timeout)) {
                WebElement radioGroup = driver.findElement(by);
                if ((onOrOff.toLowerCase().contains("on") && !radioGroup
                        .isSelected())
                        || (onOrOff.toLowerCase().contains("off") && radioGroup
                        .isSelected())) {
                    radioGroup.click();
                    logger.debug("the radioGroup [ " + by.toString()
                            + " ] is set to [ " + onOrOff.toUpperCase() + " ]");
                } else {
                    if ((onOrOff.toLowerCase().contains("on") && radioGroup
                            .isSelected())) {
                        logger.debug("the radioGroup [ " + by.toString()
                                + " ] is already set to [ " + onOrOff.toUpperCase()
                                + " ]");
                    }

                    if ((onOrOff.toLowerCase().contains("off") && !radioGroup
                            .isSelected())) {
                        logger.debug("the radioGroup [ " + by.toString()
                                + " ] is already set to [ " + onOrOff.toUpperCase()
                                + " ]");
                    }
                }
                isSucceed = true;
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * set the RadioGroup on or off
     *
     * @param by      the locator you want to find the element
     * @param onOrOff on or off to set the RadioGroup
     */
    public void setRadioGroup(By by, String onOrOff) {
        setRadioGroup(by, onOrOff, 0);
    }

    /**
     * judge if the alert is present in specified seconds
     *
     * @param timeout timeout in seconds
     */
    public boolean isAlertExists(long timeout) {
        boolean isSucceed = false;
        long timeBegins = System.currentTimeMillis();
        do {
            try {
                driver.switchTo().alert();
                isSucceed = true;
                break;
            } catch (Exception e) {
                logger.error(e);
            }
            pause(pauseTime);
        } while (System.currentTimeMillis() - timeBegins <= timeout * 1000);
        return isSucceed;
    }

    /**
     * judge if the alert is existing
     */
    public boolean isAlertExists() {
        return isAlertExists(0);
    }


    /**
     * choose OK/Cancel button's OK on alerts
     *
     * @param timeout seconds
     */
    public void chooseOKOnAlert(long timeout) {
        boolean isSucceed = false;
        try {
            if (isAlertExists(timeout)) {
                driver.switchTo().alert().accept();
                logger.debug("click OK button on alert");
                isSucceed = true;
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * choose OK/Cancel button's OK on alerts
     */
    public void chooseOKOnAlert() {
        chooseOKOnAlert(0);
    }

    /**
     * choose Cancel on alerts
     *
     * @param timeout seconds
     */
    public void chooseCancelOnAlert(long timeout) {
        boolean isSucceed = false;
        try {
            if (isAlertExists(timeout)) {
                driver.switchTo().alert().dismiss();
                logger.debug("click Cancel on alert dialog");
                isSucceed = true;
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * choose Cancel on alerts
     */
    public void chooseCancelOnAlert() {
        chooseCancelOnAlert(0);
    }

    /**
     * set text on alerts
     *
     * @param text    the text string you want to input on alerts
     * @param timeout seconds
     */
    public void setTextOnAlert(String text, long timeout) {
        boolean isSucceed = false;
        try {
            if (isAlertExists(timeout)) {
                driver.switchTo().alert().sendKeys(text);
                logger.debug("set text [ " + text + " ] on alert");
                isSucceed = true;
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * set text on alerts
     *
     * @param text the text string you want to input on alerts
     */
    public void setTextOnAlert(String text) {
        setTextOnAlert(text, 0);
    }


    /**
     * run js functions.
     *
     * @param js js function string
     */
    public void jsExecutor(String js) {
        logger.debug("execute js [ " + js + " ]");
        ((JavascriptExecutor) driver).executeScript(js);
    }

    /**
     * execute js functions to do something
     *
     * @param js   js function string
     * @param args js execute parameters
     */
    public void jsExecutor(String js, Object... args) {
        logger.debug("execute js [ : " + js + " ],arguments is : "
                + args.toString());
        ((JavascriptExecutor) driver).executeScript(js, args);
    }

    /**
     * get some value from js functions.
     *
     * @param js js function string
     */
    public Object jsReturner(String js) {
        logger.debug("execute js [ " + js + " ]");
        return ((JavascriptExecutor) driver).executeScript(js);
    }

    /**
     * get some value from js functions.
     *
     * @param js   js function string
     * @param args js execute parameters
     */
    public Object jsReturner(String js, Object... args) {
        logger.debug("execute js [ : " + js + " ],arguments is : "
                + args.toString());
        return ((JavascriptExecutor) driver).executeScript(js, args);
    }

    /**
     * scroll screen to the nominated element position
     *
     * @param table the nominated table
     * @ Tony
     */
    public void scrollScreenToElementPosition(WebElement table) {
        Coordinates coor = ((Locatable) table).getCoordinates();
        coor.inViewPort();
    }

}
