
package com.wikifish.utils;

import android.util.Log;

/**
 * The Class MyLog.
 */
public final class Logger {

    /**
     * The Constant LEVEL. None: 0; ERROR: 1; WARN: 2; INFO: 3; DEBUG: 4;
     */
    private final int LEVEL = 4;

    private final String APP_NAME = "WIKIFISH";

    private final String TAG;

    public Logger(final String className) {
        TAG = APP_NAME + ":" + className;
    }

    /**
     * Sends a debug message.
     *
     * @param logMessage the message to be sent
     */
    public void debug(final String logMessage) {
        if (LEVEL >= 4) {
            Log.d(TAG, logMessage);
        }
    }

    /**
     * Sends a debug message and the exception message.
     *
     * @param logMessage the message to be sent
     * @param e the exception
     */
    public void debug(final String logMessage, final Exception e) {
        if (LEVEL >= 4) {
            Log.d(TAG, logMessage + " : " + e.getMessage());
        }
    }

    /**
     * Sends a error message.
     *
     * @param logMessage the message to be sent
     */
    public void error(final String logMessage) {
        if (LEVEL >= 1) {
            Log.e(TAG, logMessage);
        }
    }

    /**
     * Sends a error message and the exception message.
     *
     * @param logMessage the message to be sent
     * @param e the exception
     */
    public void error(final String logMessage, final Exception e) {
        if (LEVEL >= 1) {
            Log.e(TAG, logMessage + " : " + e.getMessage());
        }
    }

    /**
     * Sends a info message.
     *
     * @param logMessage the message to be sent
     */
    public void info(final String logMessage) {
        if (LEVEL >= 3) {
            Log.i(TAG, logMessage);
        }
    }

    /**
     * Sends a warning message.
     *
     * @param logMessage the message to be sent
     */
    public void warning(final String logMessage) {
        if (LEVEL >= 2) {
            Log.w(TAG, logMessage);
        }
    }
}
