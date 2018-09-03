/*
 * Copyright (c) 2018. Ivan Widyan - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Email: ivanwidyan@yahoo.com
 */

package com.testing;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class Handler {

    private static HashMap<Long, AppiumDriver> AppiumDriver;
    private static HashMap<Long, WebDriver> WebDriver;

    /**
     * Handler initialization
     */
    public static void init () {
        SetAppiumDriverHashmap(new HashMap<Long, AppiumDriver>());
        SetWebDriverHashmap(new HashMap<Long, WebDriver>());
    }

    /**
     * Get AppiumDriver Hashmap
     * @return Appium Appium Driver Hashmap
     */
    public static HashMap<Long, AppiumDriver> GetAppiumDriverHashmap() {
        return AppiumDriver;
    }

    /**
     * Set Appium Driver Hashmap
     * @param driverHashMap
     */
    public static void SetAppiumDriverHashmap(HashMap<Long, AppiumDriver> driverHashMap) {
        AppiumDriver = driverHashMap;
    }

    /**
     * Get Current Appium Driver which running on the Current Thread
     * @return AppiumDriver
     */
    public static AppiumDriver GetCurrentAppiumDriver() {
        return Handler.AppiumDriver.get(Thread.currentThread().getId());
    }

    /**
     * Set Current Appium Driver to run on the Current Thread
     * @param driver
     */
    public static void SetCurrentAppiumDriver(AppiumDriver driver) {
        Handler.AppiumDriver.put(Thread.currentThread().getId(), driver);
    }

    /**
     * Clear AppiumDriver Hashmap
     */
    public static void ClearAppiumDriverHashmap() {
        AppiumDriver.clear();
    }

    /**
     * Get WebDriver Hashmap
     * @return Web Driver Hashmap
     */
    public static HashMap<Long, WebDriver> GetWebDriverHashmap() {
        return WebDriver;
    }

    /**
     * Set Web Driver Hashmap
     * @param driverHashMap
     */
    public static void SetWebDriverHashmap(HashMap<Long, WebDriver> driverHashMap) {
         WebDriver = driverHashMap;
    }

    /**
     * Get Current Web Driver which running on the Current Thread
     * @return AppiumDriver
     */
    public static WebDriver GetCurrentWebDriver() {
        return Handler.WebDriver.get(Thread.currentThread().getId());
    }

    /**
     * Set Current Web Driver to run on the Current Thread
     * @param driver
     */
    public static void SetCurrentWebDriver (WebDriver driver) {
        Handler.WebDriver.put(Thread.currentThread().getId(), driver);
    }

    /**
     * Clear WebDriver Hashmap
     */
    public static void ClearWebDriverHashmap() {
        WebDriver.clear();
    }

}