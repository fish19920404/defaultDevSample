package com.zyjr.emergencylending.base.http;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.zyjr.emergencylending.base.bean.ResponseBean;
import com.zyjr.emergencylending.base.config.CallBackType;
import com.zyjr.emergencylending.base.config.ErrorCode;
import com.zyjr.emergencylending.base.utils.LangUtil;
import com.zyjr.emergencylending.base.utils.LogUtils;
import com.zyjr.emergencylending.base.widget.dialog.loading.CustomerProgressDialog;

/**
 * Created by neil on 2017/9/28
 * 备注: 内部通用返回
 */
public abstract class CommonCallBack<T extends ResponseBean> implements IRequestCallback {

    private final static String TAG = "CommonCallBack";

    public abstract void success(T t, String url);

    public abstract void failure(int returnCode, String errorMessage);

    public abstract void failure(String errorMsg, String url);

    private Context mContext;
    private boolean needCache;
    private Class<T> clazz;
    private String className;
    private String url;
    private CustomerProgressDialog dialog = null;
    private boolean needPg;//是否需要加载动画

    public CommonCallBack(Context context, Class<T> clazz, String className, boolean needProgress) {
        this(context, clazz, className, needProgress, false);
    }

    public CommonCallBack(Context context, Class<T> clazz, String className, boolean needProgress, boolean cache) {
        mContext = context;
        this.clazz = clazz;
        this.className = className;
        needPg = needProgress;
        needCache = cache;
        if (mContext == null) {
            LogUtils.d(TAG, "context is null");
        }
//        showPDialog()
    }

    @Override
    public void onSuccess(String response, String url) {
        T bean = null;
        LogUtils.d(TAG, "[" + className + "],onSuccess --->response: " + response);
        try {
            bean = JSON.parseObject(response, clazz);
            if (LangUtil.isBlank(bean.getResult())) {
                failure(bean.getMsg(), url);
            } else {
                success(bean, url);
            }
        } catch (Exception e) {
            // 错误
            LogUtils.e(TAG, "[" + className + "],请求成功，数据处理异常" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Throwable throwable, String url) {
        LogUtils.d(TAG, "[" + className + "],onFailure --->response: " + throwable.getMessage());
        LogUtils.d(TAG, "网络请求地址:" + url);
        failure(CallBackType.BORRABLE, ErrorCode.FAIL_NETWORK);
    }

}
