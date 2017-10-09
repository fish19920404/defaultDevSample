package com.zyjr.emergencylending.base.common;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.res.Resources;
import android.os.Vibrator;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.zyjr.emergencylending.base.config.SPKey;
import com.zyjr.emergencylending.base.http.okhttp.OkHttpUtils;
import com.zyjr.emergencylending.base.http.okhttp.log.LoggerInterceptor;
import com.zyjr.emergencylending.base.utils.SharedPreferencesUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by neil on 2017/9/27
 * 备注:
 */
public class IApplication extends MultiDexApplication {

    private static final String TAG = IApplication.class.getSimpleName();
    public static Resources globleResource;
    public static Context globleContext;
    public static int displayWidth;
    public static int displayHeight;
    public static float xdpi;
    public static float ydpi;
    private static IApplication myApplicationInstance;
    public static boolean isBack = false;//是否点击主界面的BACK退出键
    public Vibrator mVibrator;

    public static boolean isRefresh = false;
    public static boolean isToMine = false;
    public static boolean isToHome = false;
    public static boolean isFinish = false;
    public static boolean isLoginOut = false;
    public static Class currClass;
    public static String currCity;
    public static String latitude;
    public static String longitude;


    /***
     * 寄存整个应用Activity
     **/
    private final Stack<WeakReference<Activity>> activitys = new Stack<WeakReference<Activity>>();

    /***
     * 寄存整个应用Activity
     **/
    private final List<Activity> activityList = new ArrayList<Activity>();
    /**
     * 对外提供整个应用生命周期的Context
     **/
    private static Context instance;
    public static String mCurrPackName = "";

    public List<Activity> getActivityList() {
        return activityList;
    }

    public Activity getActivity(String className) {
        Activity retActivity = null;
        for (Activity activity : getActivityList()) {
            String currClassName = activity.getClass().getName();
            if (currClassName.equals(className)) {
                retActivity = activity;
            }
        }
        if (retActivity == null) {
            for (Activity activity : getActivityList()) {
                return activity;
            }
        }
        return retActivity;
    }

    public Activity getRunningActivity() {
        ActivityManager activityManager = (ActivityManager) globleContext.getSystemService(Context.ACTIVITY_SERVICE);
        String runningActivity = activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
        return getActivity(runningActivity);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = getApplicationContext();
        globleResource = this.getResources();
        globleContext = this;
        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        displayWidth = dm.widthPixels;
        displayHeight = dm.heightPixels;
        xdpi = dm.xdpi;
        ydpi = dm.ydpi;
        mCurrPackName = getPackageName();
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
//        City.getInstance().initCitys(); // 省份地区数据初始化
        currCity = "";
        latitude = "";
        longitude = "";
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("",true))
                .connectTimeout(30000L, TimeUnit.MILLISECONDS)
                .readTimeout(30000L, TimeUnit.MILLISECONDS)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static IApplication getInstance() {
        if (null == myApplicationInstance) {
            myApplicationInstance = new IApplication();
        }
        return myApplicationInstance;
    }

    public static Context getContext() {
        return globleContext;
    }

    /**
     * 将Activity压入Application栈
     *
     * @param task 将要压入栈的Activity对象
     */
    public void pushTask(WeakReference<Activity> task) {
        activitys.push(task);
    }

    /**
     * 将传入的Activity对象从栈中移除
     *
     * @param task
     */
    public void removeTask(WeakReference<Activity> task) {
        activitys.remove(task);
    }

    /**
     * 根据指定位置从栈中移除Activity
     *
     * @param taskIndex Activity栈索引
     */
    public void removeTask(int taskIndex) {
        if (activitys.size() > taskIndex)
            activitys.remove(taskIndex);
    }

    /**
     * 将栈中Activity移除至栈顶
     */
    public void removeToTop() {
        int end = activitys.size();
        int start = 1;
        for (int i = end - 1; i >= start; i--) {
            if (!activitys.get(i).get().isFinishing()) {
                activitys.get(i).get().finish();
            }
        }
    }


    /**
     * 移除全部（用于整个应用退出）
     */
    public void removeAll() {
        // finish所有的Activity
        for (WeakReference<Activity> task : activitys) {
            if (task != null
                    && task.get() != null
                    && !task.get().isFinishing()) {
                task.get().finish();
            }
        }
    }

    /**
     * 用户是否登录
     *
     * @return true--- 未登录；false---登录
     */
    public boolean isLogin() {
        return TextUtils.isEmpty(SharedPreferencesUtil.getInstance(IApplication.globleContext).getString(SPKey.JUID)) && TextUtils.isEmpty(SharedPreferencesUtil.getInstance(IApplication.globleContext).getString(SPKey.LOGIN_TOKEN));
    }

    public void removeTask(Class mineInstantLoanActivityClass) {
        for (WeakReference<Activity> task : activitys) {
            if (task.get().getClass().equals(mineInstantLoanActivityClass))
                if (task != null
                        && task.get() != null
                        && !task.get().isFinishing()) {
                    task.get().finish();
                }
        }
    }

    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public void finishAllActivity() {
        for (int i = activityList.size() - 1; i >= 0; --i) {
            ((Activity) activityList.get(i)).finish();
        }
    }

    public void clearUserInfo(Context context) {
        clearInfoCache(context);
        SharedPreferencesUtil.getInstance(context).clearItem(SPKey.JUID);
        SharedPreferencesUtil.getInstance(context).clearItem(SPKey.LOGIN_TOKEN);
        SharedPreferencesUtil.getInstance(context).clearItem(SPKey.NAME);
        SharedPreferencesUtil.getInstance(context).clearItem(SPKey.USERNAME);
        SharedPreferencesUtil.getInstance(context).clearItem(SPKey.ID_CARD);
        SharedPreferencesUtil.getInstance(context).clearItem(SPKey.RENEW_LOANS);
        SharedPreferencesUtil.getInstance(context).clearItem(SPKey.PASSWORD);
        SharedPreferencesUtil.getInstance(context).clearItem(SPKey.SHARE_CODE);
        SharedPreferencesUtil.getInstance(context).clearItem(SPKey.APPLY_INFO);
    }

    public void clearInfoCache(Context context) {
        for (int i = 1; i < 5; i++) {
            SharedPreferencesUtil.getInstance(context).clearItem(SPKey.contactKEY + i);
        }
        SharedPreferencesUtil.getInstance(context).clearItem(SPKey.personalKey);
        SharedPreferencesUtil.getInstance(context).clearItem(SPKey.jobInfor);
        SharedPreferencesUtil.getInstance(context).clearItem(SPKey.CUSTOMER_DERECT);
        SharedPreferencesUtil.getInstance(context).clearItem(SPKey.CUSTOMER_INDERECT);
        SharedPreferencesUtil.getInstance(context).clearItem(SPKey.IS_CLERK);
        SharedPreferencesUtil.getInstance(context).clearItem(SPKey.CITY);
    }


    public void backToLogin(Context context) {
        clearUserInfo(context);
        if (!isLoginOut) {
            isLoginOut = true;
            Toast.makeText(IApplication.globleContext, "您的帐号在其他设备登录，请重新登录！",Toast.LENGTH_LONG).show();
        }
        finishAllActivity();
        // 回到主页面 0

    }

    public static void AppExit() {
        System.exit(0);
    }

}
