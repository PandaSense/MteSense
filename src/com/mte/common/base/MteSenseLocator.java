package com.mte.common.base;

import com.mte.util.YamlUtil;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Project :  mtesense
 * Created :  java
 * Date    :  4/11/15
 */
public class MteSenseLocator {

    private String locatorFile;
    private HashMap<String, HashMap<String, String>> elementsMap;

    private final String type = "type";
    private final String value = "value";

    public MteSenseLocator(String locatorFile) {

        this.locatorFile = locatorFile;
        setLocatorsMapFromFile(locatorFile);
    }

    private void setLocatorsMapFromFile(String locatorFile) {

        YamlUtil util = new YamlUtil(locatorFile);

        elementsMap = util.getElementsMap();

    }

    public By getLocator(String key, ArrayList<String> locatorStringReplacement) {

        By locator = null;

        if (elementsMap == null) {
            return null;
        }

        if (elementsMap.containsKey(key)) {
            String locatorString = elementsMap.get(key).get(value);
            if (locatorStringReplacement != null
                    && locatorStringReplacement.size() != 0) {
                for (String replaceItem : locatorStringReplacement) {
                    locatorString = locatorString.replaceFirst("%s", replaceItem);
                }
            }

                locator = getLocatorByType(elementsMap.get(key).get(type), locatorString);

        } else {
            return null;
        }

        return locator;
    }

    private By getLocatorByType(String type, String locatorString) {

        By locator = null;

        switch (type) {
            case "id":
                locator = By.id(locatorString);
                break;
            case "name":
                locator = By.name(locatorString);
                break;
            case "xpath":
                locator = By.xpath(locatorString);
                break;
            case "linkText":
                locator = By.linkText(locatorString);
                break;
            case "partialLinkText":
                locator = By.partialLinkText(locatorString);
                break;
            case "className":
                locator = By.className(locatorString);
                break;
            case "tagName":
                locator = By.tagName(locatorString);
                break;
            case "cssSelector":
                locator = By.cssSelector(locatorString);
                break;
            default:
                locator = null;
                break;
        }

        return locator;
    }


    public By getLocator(String key) {
        return getLocator(key, null);
    }

    public String getLocatorType(String key) {
        if (elementsMap == null)
            return null;
        if (elementsMap.containsKey(key)) {
            return elementsMap.get(key).get(type);
        } else {
            return null;
        }
    }

    public String getLocatorValue(String key) {
        System.out.println(elementsMap.size());
        if (elementsMap == null)
            return null;
        if (elementsMap.containsKey(key)) {
            return elementsMap.get(key).get(value);
        } else {
            return null;
        }
    }

}
