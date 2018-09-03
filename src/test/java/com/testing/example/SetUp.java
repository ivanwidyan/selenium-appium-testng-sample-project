/*
 * Copyright (c) 2018. Ivan Widyan - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Email: ivanwidyan@yahoo.com
 */

package com.testing.example;

import com.testing.Handler;
import com.testing.constants.ConfigConstants;
import com.testing.logging.Log;
import com.testing.example.constants.ExampleConfigConstants;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SetUp {

    @BeforeSuite
    public void Init() {
        Log.Debug("SetUp initiated");
        Handler.init();
    }

    @BeforeTest
    @Parameters({"platform", "browser", "devicename", "udid", "ip", "port"})
    public void SetUp(String platform, @Optional String browser, @Optional String devicename,
                      @Optional String udid, @Optional String ip, @Optional String port) throws Exception {

        String info = "";

        Log.Error("Test " + platform);

        if (platform.equalsIgnoreCase("android")) {
            if (com.testing.Handler.GetCurrentAppiumDriver() == null) {
                if (devicename == null)
                    devicename = ExampleConfigConstants.DEVICE_NAME;

                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(ConfigConstants.CAPABILITIES_DEVICE_NAME, devicename);
                capabilities.setCapability(CapabilityType.BROWSER_NAME, ExampleConfigConstants.BROWSER_NAME);
                capabilities.setCapability(ConfigConstants.CAPABILITIES_PLATFORM_NAME, ExampleConfigConstants.PLATFORM_NAME);
                capabilities.setCapability(ConfigConstants.CAPABILITIES_APP_PACKAGE, ExampleConfigConstants.APP_PACKAGE);
                capabilities.setCapability(ConfigConstants.CAPABILITIES_APP_ACTIVITY, ExampleConfigConstants.APP_ACTIVITY);

                if (udid != null)
                    capabilities.setCapability(ConfigConstants.CAPABILITIES_UDID, udid);

                if (ip == null)
                    ip = ExampleConfigConstants.DEFAULT_IP;

                if (port == null)
                    port = ExampleConfigConstants.DEFAULT_PORT;

                String url = "http://" + ip + ":" + port + "/wd/hub";
                Handler.SetCurrentAppiumDriver(new AndroidDriver(new URL(url), capabilities));

                info = "SetUp Appium Driver for Device = " + com.testing.Handler.GetCurrentAppiumDriver()
                        .getCapabilities().getCapability(ConfigConstants.CAPABILITIES_DEVICE_NAME);
                Log.Debug(info);

            } else {
                info = "Duplicate Appium driver in the same thread";
                Log.Error(info);
            }
        } else if (platform.equalsIgnoreCase("web")) {
//            System.setProperty("webdriver.gecko.driver", "/Users/ivanwidyan/Desktop/Ivan-Widyan/Tools/GeckoDriver/geckodriver");
            System.setProperty("webdriver.gecko.driver", "/lib/geckodriver-v0.21.0/geckodriver.exe");
            Handler.SetCurrentWebDriver(new FirefoxDriver());

            Handler.GetCurrentWebDriver().manage().timeouts().implicitlyWait(ConfigConstants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);

            String url = "https://www.example.com/en";

            Handler.GetCurrentWebDriver().get(url);
        }
    }

    @AfterTest
    public void AfterTest() {
        if (Handler.GetCurrentAppiumDriver() != null) {
            String info = "Quit Driver for Device = " + com.testing.Handler.GetCurrentAppiumDriver()
                    .getCapabilities().getCapability(ConfigConstants.CAPABILITIES_DEVICE_NAME);
            Log.Debug(info);
            Handler.GetCurrentAppiumDriver().quit();
        }

        if (Handler.GetCurrentWebDriver() != null) {
            String info = "Quit Driver for Web Driver = " + com.testing.Handler.GetCurrentWebDriver();
            Log.Debug(info);
            Handler.GetCurrentWebDriver().quit();
        }
    }

    @AfterSuite
    public void AfterSuite() throws Exception {
        String info = "Clear Driver Hashmap";
        Log.Debug(info);
        Handler.ClearAppiumDriverHashmap();
        Handler.ClearWebDriverHashmap();
    }
}
