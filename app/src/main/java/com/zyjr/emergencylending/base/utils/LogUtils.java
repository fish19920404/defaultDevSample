package com.zyjr.emergencylending.base.utils;

import android.text.TextUtils;
import android.util.Log;

public class LogUtils {

    private final static String TAG = "Alsan";
    public final static boolean DEBUG = true;
    private final static int LOG_LENGTH = 3500;

    protected enum LogLevel {
        INFO, DEBUG, WARN, ERROR
    }

    private static void level(LogLevel level, String tag, String msg) {
        if (level == LogLevel.DEBUG) {
            Log.d(tag, msg);
        } else if (level == LogLevel.WARN) {
            Log.w(tag, msg);
        } else if (level == LogLevel.ERROR) {
            Log.e(tag, msg);
        } else {
            Log.i(tag, msg);
        }
    }

    private static void log(boolean isDebug, LogLevel level, String tag, String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        int len = msg.length();
        if (level == LogLevel.INFO || isDebug) {
//            level(level, tag, msg);
            if (len < LOG_LENGTH) {
                level(level, tag, msg);
            } else {
                int i = 0;
                while (i <= len) {
                    if ((i + LOG_LENGTH) <= len) {
                        level(level, tag, msg.substring(i, i + LOG_LENGTH));
                    } else {
                        level(level, tag, msg.substring(i, len));
                    }
                    i += LOG_LENGTH;
                }
            }
        }
    }

    public static void i(String msg) {
        log(true, LogLevel.INFO, TAG, msg);
    }

    public static void d(String msg) {
        log(DEBUG, LogLevel.DEBUG, TAG, msg);
    }

    public static void w(String msg) {
        log(true, LogLevel.WARN, TAG, msg);
    }

    public static void e(String msg) {
        log(true, LogLevel.ERROR, TAG, msg);
    }

    public static void i(String tag, String msg) {
        log(true, LogLevel.INFO, tag, msg);
    }

    public static void d(String tag, String msg) {
        log(DEBUG, LogLevel.DEBUG, tag, msg);
    }

    public static void w(String tag, String msg) {
        log(true, LogLevel.WARN, tag, msg);
    }

    public static void e(String tag, String msg) {
        log(true, LogLevel.ERROR, tag, msg);
    }

}
