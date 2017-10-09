package com.zyjr.emergencylending.base.config;


import com.zyjr.emergencylending.pak.BuildConfig;

/**
 * Created by neil on 2017/9/27
 * 备注:
 */
public class AppConfig {

    public final static boolean DEBUG = !BuildConfig.LOG_DEBUG;
    public static final String DEBUG_TAG = "logger";// LogCat的标记

    public static final int SHARE_AD = 0;
    public static final int SHARE_BONUS = 1;
    public static final int SHARE_WITHDRAW = 2;

    public final static int LIST_PAGE_SIZE = 8;
    public final static int LIST_START_PAGE = 1;
    public final static String PAGE_SIZE_KEY = "pageRows";
    public final static String CURRENT_PAGE_KEY = "pageNumber";


    public final static String APP_ROOT_PATH = "/jojo/";
    public final static String APP_CACHE_DIR = "cache";
    public final static String APP_LOG_DIR = "log";
    public final static String APP_PATCH_DIR = "patch";
    public final static String APP_SHARE_DIR = "share";
    public final static String APP_CRASH_DIR = "crash";
    public final static String APP_DATA_DIR = "data";
    public final static String APP_IMAGE_DIR = "image";

    //adb command
    public final static String ADB_LOGCAT = "logcat *:e *:w | grep \"(" + android.os.Process.myPid() + ")\"";

    public final static String TEST_URL_KEY = "test_url_key";
    public final static String TEST_API_KEY = "test_api_key";


    //sharePreference key
    public final static String UID = "uid";
    public final static String TOKEN = "token";
    public final static String AUTHTOKEN = "authToken";
    public final static String IDCARD = "idCard";
    public final static String ACCOUNT = "user_account";
    public final static String USER_PHONE_NUM = "userPhoneNum";
    public final static String BALANCE = "balance";
    public final static String VERSION_CODE = "version_code";
    public final static String GESTTURES_PASSWORD = "gesturespassword";
    public final static String COUPONCOUNT = "couponcount";
    public final static String THIRD_RECHARGE_TYPE = "thirdrechargetype";
    public final static String CURRENT_TIME = "currentTime";

}
