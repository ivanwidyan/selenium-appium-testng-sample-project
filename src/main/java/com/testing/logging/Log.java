/*
 * Copyright (c) 2018. Ivan Widyan - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Email: ivanwidyan@yahoo.com
 */

package com.testing.logging;

public class Log {

    private static final String DEBUG_PREFIX = "[DEBUG] ";
    private static final String INFO_PREFIX = "[INFO] ";
    private static final String WARNING_PREFIX = "[WARNING] ";
    private static final String ERROR_PREFIX = "[ERROR] ";

    public static void Debug (Object o) {
        System.out.println(DEBUG_PREFIX + o);
    }

    public static void Info (Object o) {
        System.out.println(INFO_PREFIX + o);
    }

    public static void Warning (Object o) {
        System.out.println(WARNING_PREFIX + o);
    }

    public static void Error (Object o) {
        System.out.println(ERROR_PREFIX + o);
    }
}
