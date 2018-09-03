/*
 * Copyright (c) 2018. Ivan Widyan - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Email: ivanwidyan@yahoo.com
 */

package com.testing;

import com.testing.constants.ConfigConstants;
import com.testing.constants.Constants;
import com.testing.constants.ElementConstants;
import com.testing.logging.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Utility {
    private final static String NO_SENDKEYS = null;

    private final static boolean CLICK_TRUE = true;
    private final static boolean CLICK_FALSE = false;

    /**
     * This method used to get element by id with default timeout
     * @param driver WebDriver
     * @param id element
     * @return WebElement
     */
    public static WebElement GetElementById (WebDriver driver, String id) {
        return webElementUtility(driver, id, ElementConstants.FIND_ELEMENT_TYPE_ID, CLICK_FALSE,
                NO_SENDKEYS, ConfigConstants.DEFAULT_TIMEOUT);
    }

    /**
     * This method used to get element by id and click with default timeout
     * @param driver WebDriver
     * @param id
     * @return WebElement
     */
    public static WebElement ClickElementById (WebDriver driver, String id) {
        return webElementUtility(driver, id, ElementConstants.FIND_ELEMENT_TYPE_ID, CLICK_TRUE,
                NO_SENDKEYS, ConfigConstants.DEFAULT_TIMEOUT);
    }

    /**
     * This method used to get element by id and click with input timeout
     * @param driver WebDriver
     * @param id
     * @param timeout in seconds
     * @return
     */
    public static WebElement ClickElementById (WebDriver driver, String id, long timeout) {
        return webElementUtility(driver, id, ElementConstants.FIND_ELEMENT_TYPE_ID, CLICK_TRUE,
                NO_SENDKEYS, timeout);
    }

    /**
     * This method used to get element by id and send keys with default timeout
     * @param driver WebDriver
     * @param id
     * @return WebElement
     */
    public static WebElement SendKeysElementById (WebDriver driver, String id, String sendKeys) {
        return webElementUtility(driver, id, ElementConstants.FIND_ELEMENT_TYPE_ID, CLICK_FALSE,
                sendKeys, ConfigConstants.DEFAULT_TIMEOUT);
    }

    /**
     * This method used to get element by class name and click with default timeout
     * @param driver
     * @param className
     * @return WebElement
     */
    public static WebElement ClickElementByClassName (WebDriver driver, String className) {
        return webElementUtility(driver, className, ElementConstants.FIND_ELEMENT_TYPE_CLASSNAME, CLICK_TRUE,
                NO_SENDKEYS, ConfigConstants.DEFAULT_TIMEOUT);
    }

    /**
     * This method used to get element by class name and send keys with default timeout
     * @param driver
     * @param className
     * @return WebElement
     */
    public static WebElement SendKeysElementByClassName (WebDriver driver, String className, String sendKeys) {
        return webElementUtility(driver, className, ElementConstants.FIND_ELEMENT_TYPE_CLASSNAME, CLICK_FALSE,
                sendKeys, ConfigConstants.DEFAULT_TIMEOUT);
    }

    /**
     * This method used to find element by text with default timeout
     * @param driver
     * @return WebElement
     */
    public static WebElement GetElementByXPath(WebDriver driver, String element, String param, String value) {
        String xpath = "//" + element + "[@" + param + "='" + value + "']";
        return webElementUtility(driver, xpath, ElementConstants.FIND_ELEMENT_TYPE_XPATH, CLICK_FALSE,
                NO_SENDKEYS, ConfigConstants.DEFAULT_TIMEOUT);
    }

    /**
     * This method used to find element by text and click with default timeout
     * @param driver web driver
     * @return WebElement
     */
    public static WebElement ClickElementByXPath(WebDriver driver, String element, String param, String value) {
        String xpath = "//" + element + "[@" + param + "='" + value + "']";
        return webElementUtility(driver, xpath, ElementConstants.FIND_ELEMENT_TYPE_XPATH, CLICK_TRUE,
                NO_SENDKEYS, ConfigConstants.DEFAULT_TIMEOUT);
    }

    /**
     * This method used to find element by text and click with input timeout
     * @param driver web driver
     * @param timeout in seconds
     * @return WebElement
     */
    public static WebElement ClickElementByXPath(WebDriver driver, String element, String param, String value, long timeout) {
        String xpath = "//" + element + "[@" + param + "='" + value + "']";
        return webElementUtility(driver, xpath, ElementConstants.FIND_ELEMENT_TYPE_XPATH, CLICK_TRUE,
                NO_SENDKEYS, timeout);
    }

    /**
     * This method used to find element by text and send keys with default timeout
     * @param driver web driver
     * @param sendKeys
     * @return WebElement
     */
    public static WebElement SendKeysElementByXPath(WebDriver driver, String element, String param, String value, String sendKeys) {
        String xpath = "//" + element + "[@" + param + "='" + value + "']";
        return webElementUtility(driver, xpath, ElementConstants.FIND_ELEMENT_TYPE_XPATH, CLICK_FALSE,
                sendKeys, ConfigConstants.DEFAULT_TIMEOUT);
    }

    /**
     * This method used to get element by css selector without element and click with default timeout
     * @param driver
     * @param param
     * @param value
     * @return WebElement
     */
    public static WebElement ClickElementByCssSelector (WebDriver driver, String param, String value) {
        return ClickElementByCssSelector(driver, Constants.EMPTY, param, value);
    }

    /**
     * This method used to get element by css selector and click with default timeout
     * @param driver
     * @param element or tag
     * @param param
     * @param value
     * @return WebElement
     */
    public static WebElement ClickElementByCssSelector (WebDriver driver, String element, String param, String value) {
        String cssSelector = element + "[" + param + "='" + value + "']";
        return webElementUtility(driver, cssSelector, ElementConstants.FIND_ELEMENT_TYPE_CSSSELECTOR,
                CLICK_TRUE, NO_SENDKEYS, ConfigConstants.DEFAULT_TIMEOUT);
    }

    /**
     * This method used to get element by css selector without element and send keys with default timeout
     * @param driver
     * @param param
     * @param value
     * @return WebElement
     */
    public static WebElement SendKeysElementByCssSelector (WebDriver driver, String param,
                                                           String value, String sendKeys) {
        return SendKeysElementByCssSelector(driver, Constants.EMPTY, param, value, sendKeys);
    }

    /**
     * This method used to get element by css selector and send keys with default timeout
     * @param driver
     * @param element or tag
     * @param param
     * @param value
     * @return WebElement
     */
    public static WebElement SendKeysElementByCssSelector (WebDriver driver, String element,
                                                           String param, String value, String sendKeys) {
        String cssSelector = element + "[" + param + "='" + value + "']";
        return webElementUtility(driver, cssSelector, ElementConstants.FIND_ELEMENT_TYPE_CSSSELECTOR,
                CLICK_FALSE, sendKeys, ConfigConstants.DEFAULT_TIMEOUT);
    }

    private static WebElement webElementUtility (WebDriver driver, String input, String type, boolean click,
                                                 String sendKeys, long timeout) {
        if (driver == null) {
            return null;
        } else {
            WebElement webElement = null;

            webElement = findWebElement(driver, type, input, timeout);

            if (webElement == null) {
                Log.Warning("Result: Timeout " + timeout + "s with input = " + input + ", type = " + type);
                return null;
            } else {

                if (click)
                    webElement.click();

                if (sendKeys != null)
                    webElement.sendKeys(sendKeys);
            }

            return webElement;
        }
    }

    private static WebElement findWebElement (WebDriver driver, String type, String input, long timeout) {
        WebElement webElement;
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        // TODO: Report bug to appium
        /* This code is not working when using CLI setup appium server
        driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        */

        try {
            if (type.equals(ElementConstants.FIND_ELEMENT_TYPE_ID)) {
                wait.until(ExpectedConditions.elementToBeClickable(By.id(input)));
                webElement = driver.findElement(By.id(input));

            } else if (type.equals(ElementConstants.FIND_ELEMENT_TYPE_CLASSNAME)) {
                wait.until(ExpectedConditions.elementToBeClickable(By.className(input)));
                webElement = driver.findElement(By.className(input));

            } else if (type.equals(ElementConstants.FIND_ELEMENT_TYPE_XPATH)) {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(input)));
                webElement = driver.findElement(By.xpath(input));

            } else if (type.equals(ElementConstants.FIND_ELEMENT_TYPE_CSSSELECTOR)) {
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(input)));
                webElement = driver.findElement(By.cssSelector(input));

            } else {
                Log.Error("Type is not defined");
                return null;
            }
        } catch (Exception e) {
            Log.Error("Error: " + e);
            throw new TimeoutException();
        }
        return webElement;
    }

    /**
     * This method used to get elements by id with default timeout
     * @param driver
     * @param id
     * @return List<WebElement>
     */
    public static List<WebElement> GetElementsById (WebDriver driver, String id) {
        return webElementsUtility(driver, id, ElementConstants.FIND_ELEMENT_TYPE_ID, CLICK_FALSE,
                Constants.FIRST_INDEX, ConfigConstants.DEFAULT_TIMEOUT);
    }

    /**
     * This method used to get elements by id and click by index with default timeout
     * @param driver
     * @param id
     * @return List<WebElement>
     */
    public static List<WebElement> ClickElementsById (WebDriver driver, String id, int index) {
        return webElementsUtility(driver, id, ElementConstants.FIND_ELEMENT_TYPE_ID, CLICK_TRUE,
                index, ConfigConstants.DEFAULT_TIMEOUT);
    }

    public static List<WebElement> ClickElementsByXpath(WebDriver driver, String element,
                                                        String param, String value, int index) {
        String xpath = "//" + element + "[@" + param + "='" + value + "']";
        return webElementsUtility(driver, xpath, ElementConstants.FIND_ELEMENT_TYPE_XPATH, CLICK_TRUE,
                index, ConfigConstants.DEFAULT_TIMEOUT);
    }

    public static List<WebElement> GetElementsByCssSelector(WebDriver driver,
                                                            String param, String value) {
        return GetElementsByCssSelector(driver, Constants.EMPTY, param, value);
    }

    public static List<WebElement> GetElementsByCssSelector(WebDriver driver, String element,
                                                              String param, String value) {
        String cssSelector = element + "[" + param + "='" + value + "']";
        return webElementsUtility(driver, cssSelector, ElementConstants.FIND_ELEMENT_TYPE_CSSSELECTOR,
                CLICK_FALSE, Constants.FIRST_INDEX, ConfigConstants.DEFAULT_TIMEOUT);
    }

    public static List<WebElement> ClickElementsByCssSelector(WebDriver driver, String param,
                                                              String value, int index) {
        return ClickElementsByCssSelector(driver, Constants.EMPTY, param, value, index);
    }

    public static List<WebElement> ClickElementsByCssSelector(WebDriver driver, String element,
                                                              String param, String value, int index) {
        String cssSelector = element + "[" + param + "='" + value + "']";
        return webElementsUtility(driver, cssSelector, ElementConstants.FIND_ELEMENT_TYPE_CSSSELECTOR,
                CLICK_TRUE, index, ConfigConstants.DEFAULT_TIMEOUT);
    }

    /**
     * This method used to get elements by class name with default timeout
     * @param driver
     * @param className
     * @return List<WebElement>
     */
    public static List<WebElement> GetElementsByClass (WebDriver driver, String className) {
        return webElementsUtility(driver, className, ElementConstants.FIND_ELEMENT_TYPE_CLASSNAME,
                CLICK_FALSE, Constants.FIRST_INDEX, ConfigConstants.DEFAULT_TIMEOUT);
    }

    private static List<WebElement> webElementsUtility (WebDriver driver, String input, String type, boolean click,
                                                 int index, long timeout) {
        if (driver == null) {
            return null;
        } else {
            List<WebElement> webElements;

            webElements = findWebElements(driver, type, input, timeout);

            Log.Debug("Web elements input: " + input + "|type: " + type + "|size: " + webElements.size());

            if (webElements == null) {
                Log.Warning("Result: Timeout " + timeout + "s with input = " + input + ", type = " + type);
                return null;
            } else {
                if (click) {
                    WebElement element = webElements.get(index);
                    element.click();
                }
            }

            return webElements;
        }
    }

    private static List<WebElement> findWebElements (WebDriver driver, String type, String input, long timeout) {
        List<WebElement> webElement;
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        if (type.equals(ElementConstants.FIND_ELEMENT_TYPE_ID)) {
            wait.until(ExpectedConditions.elementToBeClickable(By.id(input)));
            webElement = driver.findElements(By.id(input));

        } else if (type.equals(ElementConstants.FIND_ELEMENT_TYPE_CLASSNAME)) {
            wait.until(ExpectedConditions.elementToBeClickable(By.className(input)));
            webElement = driver.findElements(By.className(input));

        } else if (type.equals(ElementConstants.FIND_ELEMENT_TYPE_XPATH)) {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(input)));
            webElement = driver.findElements(By.xpath(input));

        } else if (type.equals(ElementConstants.FIND_ELEMENT_TYPE_CSSSELECTOR)) {
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(input)));
            webElement = driver.findElements(By.cssSelector(input));

        } else {
            Log.Error("Type is not defined");
            return null;
        }
        return webElement;
    }

    /**
     * Tap in x and y coordinates
     * @param x coordinate
     * @param y coordinate
     */
    public static void TapByCoordinates (int x, int y) {
        TouchAction touchAction = new TouchAction(Handler.GetCurrentAppiumDriver());
        touchAction.tap(PointOption.point(x, y)).perform();
    }

    /**
     * Swipe screen vertically by anchor start point, end point, and anchor
     * @param driver
     * @param startPoint in y coordinates
     * @param endPoint in y coordinates
     * @param anchor in x coordinates
     */
    public static void SwipeVerticalByCoordinates(AppiumDriver driver, int startPoint, int endPoint, int anchor) {
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(anchor, startPoint))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(ConfigConstants.SWIPE_DURATION)))
                .moveTo(PointOption.point(anchor, endPoint))
                .release().perform();
    }

    /**
     * Swipe screen horizontally by anchor start point, end point, and anchor
     * @param driver
     * @param startPoint in x coordinates
     * @param endPoint in y coordinates
     * @param anchor in y coordinates
     */
    public static void SwipeHorizontalByCoordinates (AppiumDriver driver, int startPoint, int endPoint, int anchor) {
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(startPoint, anchor))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(ConfigConstants.SWIPE_DURATION)))
                .moveTo(PointOption.point(endPoint, anchor))
                .release().perform();
    }

    /**
     * Swipe screen horizontally by x and y start point to x and y end point.
     * @param driver
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     */
    public static void SwipeByCoordinates (AppiumDriver driver, int startX, int startY, int endX, int endY) {
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(ConfigConstants.SWIPE_DURATION)))
                .moveTo(PointOption.point(endX, endY))
                .release().perform();
    }
}