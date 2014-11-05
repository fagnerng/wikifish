package com.wikifish.utils;

import android.util.Log;

public class Logger {
    /** Enable or disable log */
    private static final boolean DOLOG = true;

    public static void log(String tag, String msg) {
        if (DOLOG) {
            Log.d(tag, msg);
        }
    }

    /** Used in unit tests */
    public static boolean isDolog() {
        return DOLOG;
    }
}
