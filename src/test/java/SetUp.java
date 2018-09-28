/*
 * Copyright (c) 2018. Ivan Widyan - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Email: ivanwidyan@yahoo.com
 */

import com.testing.Handler;
import com.testing.constants.ConfigConstants;
import example.constants.ExampleConfigConstants;
import com.testing.logging.Log;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

import java.net.URL;
import java.util.concurrent.TimeUnit;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"example"},
//        tags = {"~@Ignore"},
        tags = {"@LoginProfile"},
        format = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt"
        })

public class SetUp {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeSuite
    public void Init() {
        Log.Debug("SetUp initiated");
        Handler.init();
    }

    @BeforeTest
    @Parameters({"platform", "browser", "devicename", "udid", "ip", "port"})
    public void SetUp(String platform, @Optional String browser, @Optional String devicename,
                      @Optional String udid, @Optional String ip, @Optional String port) throws Exception {

        String info;

        Log.Debug("Test platform: " + platform);

        if (platform.equalsIgnoreCase(ConfigConstants.PLATFORM_ANDROID)) {
            if (Handler.GetCurrentAppiumDriver() == null) {
                if (devicename == null)
                    devicename = ConfigConstants.DEVICE_NAME;

                DesiredCapabilities capabilities = DesiredCapabilities.android();
                capabilities.setCapability(ConfigConstants.CAPABILITIES_DEVICE_NAME, devicename);

                capabilities.setCapability(ConfigConstants.CAPABILITIES_PLATFORM_NAME,
                        ConfigConstants.PLATFORM_ANDROID);

                capabilities.setCapability(ConfigConstants.CAPABILITIES_APP_PACKAGE,
                        ExampleConfigConstants.APP_PACKAGE);

                capabilities.setCapability(ConfigConstants.CAPABILITIES_APP_ACTIVITY,
                        ExampleConfigConstants.APP_ACTIVITY);

                if (udid != null)
                    capabilities.setCapability(ConfigConstants.CAPABILITIES_UDID, udid);

                if (ip == null)
                    ip = ConfigConstants.DEFAULT_IP;

                if (port == null)
                    port = ConfigConstants.DEFAULT_PORT;

                String url = "http://" + ip + ":" + port + "/wd/hub";
                Handler.SetCurrentAppiumDriver(new AndroidDriver(new URL(url), capabilities));

                info = "SetUp Appium Driver for Device = " + Handler.GetCurrentAppiumDriver()
                        .getCapabilities().getCapability(ConfigConstants.CAPABILITIES_DEVICE_NAME);
                Log.Debug(info);

            } else {
                info = "Duplicate Appium driver in the same thread";
                Log.Error(info);
            }

        } else if (platform.equalsIgnoreCase(ConfigConstants.PLATFORM_WEB)) {
            System.setProperty(ConfigConstants.GECKO_DRIVER_PROPERTY,
                    ConfigConstants.GECKO_DRIVER_PATH);

            if (ConfigConstants.BROWSER_FIREFOX.equalsIgnoreCase(browser))
                Handler.SetCurrentWebDriver(new FirefoxDriver());
            else if (ConfigConstants.BROWSER_SAFARI.equalsIgnoreCase(browser)) {
                Handler.SetCurrentWebDriver(new SafariDriver());
            } else if (ConfigConstants.BROWSER_CHROME.equalsIgnoreCase(browser)) {
                System.setProperty(ConfigConstants.CHROME_DRIVER_PROPERTY,
                        ConfigConstants.CHROME_DRIVER_PATH);

                Handler.SetCurrentWebDriver(new ChromeDriver());
            }

            Handler.GetCurrentWebDriver().manage().timeouts().implicitlyWait(
                    ConfigConstants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);

            Handler.GetCurrentWebDriver().get(ExampleConfigConstants.URL);
        }
    }

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
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
