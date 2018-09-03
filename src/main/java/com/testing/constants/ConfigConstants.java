/*
 * Copyright (c) 2018. Ivan Widyan - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Email: ivanwidyan@yahoo.com
 */

package com.testing.constants;

public class ConfigConstants {
    // Platform Config
    public final static String PLATFORM_ANDROID = "Android";
    public final static String PLATFORM_WEB = "Web";

    // Android Only
    public final static String CAPABILITIES_DEVICE_NAME = "deviceName";
    public final static String CAPABILITIES_UDID = "udid";
    public final static String CAPABILITIES_PLATFORM_NAME = "platformName";
    public final static String CAPABILITIES_APP_PACKAGE = "appPackage";
    public final static String CAPABILITIES_APP_ACTIVITY = "appActivity";

    // Webdriver timeout
    public final static long DEFAULT_TIMEOUT = 30;
    public final static long SWIPE_DURATION = 1;
    public final static long NO_TIMEOUT = 0;
}
