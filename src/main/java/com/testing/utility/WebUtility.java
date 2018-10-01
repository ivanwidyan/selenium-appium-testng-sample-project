/*
 * Copyright (c) 2018. Ivan Widyan - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Email: ivanwidyan@yahoo.com
 */

package com.testing.utility;

import com.testing.Handler;
import com.testing.constants.ConfigConstants;
import com.testing.constants.Constants;
import com.testing.constants.ElementConstants;
import com.testing.logging.Log;
import example.constants.ExampleConfigConstants;
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

public class WebUtility {

    public static void GoTo (String url) {
        GoTo(null, url);
    }

    public static void GoTo (WebDriver driver, String url) {

        if (driver == null)
            driver = Handler.GetCurrentWebDriver();

        driver.get(url);
    }

}