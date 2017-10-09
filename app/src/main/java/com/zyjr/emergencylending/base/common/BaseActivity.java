package com.zyjr.emergencylending.base.common;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;


import com.zyjr.emergencylending.base.http.HttpMethod;
import com.zyjr.emergencylending.base.utils.LogUtils;
import com.zyjr.emergencylending.base.utils.SharedPreferencesUtil;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

import butterknife.ButterKnife;


/**
 * Created by wangyaping
 */
public abstract class BaseActivity extends FragmentActivity implements IBaseActivity {
    protected Context baseContext;
    protected View mContentView;
    protected WeakReference<Activity> content = null;
    protected IApplication iApplication;
    public Resources resources;
    public SharedPreferencesUtil mSharedPreferencesUtil;
    private long beginTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IApplication.getInstance().addActivity(this);
        LogUtils.d("BaseActivity:", this.getClass().getName());// 打印出每个activity的类名
        try {
            iApplication = (IApplication) getApplication();
            baseContext = this;
            content = new WeakReference<Activity>(this);
            iApplication.pushTask(content);
            resources = this.getResources();
            mSharedPreferencesUtil = SharedPreferencesUtil.getInstance(getApplicationContext());
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);
            ImageView iv = new ImageView(this);
            iv.setImageResource(setStatusColor());
            LayoutParams statusParams;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Window window = getWindow();
                window.setFlags(
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                if (getStick()) {
                    statusParams = new LayoutParams(LayoutParams.MATCH_PARENT, 0);
                } else {
                    statusParams = new LayoutParams(LayoutParams.MATCH_PARENT, getStatusBarHeight());
                }
            } else {
                statusParams = new LayoutParams(LayoutParams.MATCH_PARENT, 0);
            }
            layout.addView(iv, statusParams);
            mContentView = LayoutInflater.from(this).inflate(setContent(), null);
            LayoutParams contentParams = new LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            layout.addView(mContentView, contentParams);
            setContentView(layout);
            ButterKnife.bind(this);
            init();
            setData();
            setListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected boolean getStick() {
        return false;
    }

    //网络请求方式
    public String getMethod() {
        return HttpMethod.GET;
    }

//    protected abstract  int setBarColor();

    /**
     * 获取当前Activity
     *
     * @return
     */
    protected Activity getContext() {
        if (null != content)
            return content.get();
        else
            return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d("---------BaseActivity:::onResume-----------------");
        resume();
        beginTime = System.currentTimeMillis();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        IApplication.getInstance().removeActivity(this);
        destroy();
        super.onDestroy();
    }

    public int getStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            LogUtils.e("BaseActivity", "get status bar height fail");
            e1.printStackTrace();
        }
        return sbar;
    }

    protected void dolongtimen(int page_id) {
        long ontime = System.currentTimeMillis() - beginTime;
    }

}