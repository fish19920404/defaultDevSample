package com.zyjr.emergencylending.base.http;

import android.net.Uri;
import android.os.Handler;

import com.alibaba.fastjson.JSON;
import com.zyjr.emergencylending.base.http.okhttp.OkHttpUtils;
import com.zyjr.emergencylending.base.http.okhttp.callback.FileCallBack;
import com.zyjr.emergencylending.base.http.okhttp.callback.StringCallback;
import com.zyjr.emergencylending.base.http.okhttp.request.RequestCall;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by chenjianwei on 2016/12/11.
 */
public class OkHttpRequestManager implements IRequestManager {

    private final static String MEDIA_TYPE = "application/json; charset=utf-8";
    private Handler handler;
    private Object tag;
    private static OkHttpRequestManager mInstance;

    public static OkHttpRequestManager getInstance(Object tag) {
        if (mInstance == null) {
            mInstance = new OkHttpRequestManager(tag);
        }
        return mInstance;
    }

    public OkHttpRequestManager(Object tag) {
        this.tag = tag;
        //在哪个线程创建该对象，则最后的请求结果将在该线程回调
        handler = new Handler();
    }

    @Override
    public void post(String url, IRequestCallback requestCallback) {
        post(url, null, requestCallback);
    }

    @Override
    public void post(String url, Map<String, String> params, IRequestCallback requestCallback) {
        post(url, params, null, requestCallback);
    }

    @Override
    public void post(String url, Map<String, String> params, String token, IRequestCallback requestCallback) {
        RequestCall request = OkHttpUtils.postString().url(url).content(JSON.toJSONString(params)).mediaType(MediaType.parse(MEDIA_TYPE)).addHeader("Authorization", "Bearer " + token).tag(tag).build();
        addCallBack(requestCallback, request, url);
    }

    @Override
    public void postFormBody(String url, IRequestCallback requestCallback) {
        postFormBody(url, null, requestCallback);
    }

    @Override
    public void postFormBody(String url, Map<String, String> paramsp, IRequestCallback requestCallback) {
        postFormBody(url, paramsp, null, requestCallback);
    }

    @Override
    public void postFormBody(String url, Map<String, String> paramsp, String token, IRequestCallback requestCallback) {
        RequestCall request = OkHttpUtils.post().url(url).params(paramsp).addHeader("Authorization", "Bearer " + token).tag(tag).build();
        addCallBack(requestCallback, request, url);
    }

    @Override
    public void download(String url, IRequestCallback requestCallback) {
        RequestCall request = OkHttpUtils.get().url(url).tag(tag).build();
        addFileBack(requestCallback, request);
    }

    @Override
    public void options(String url, IRequestCallback requestCallback) {
        options(url, null, requestCallback);
    }

    @Override
    public void options(String url, Map<String, String> paramsp, IRequestCallback requestCallback) {
        options(url, paramsp, null, requestCallback);
    }

    @Override
    public void options(String url, Map<String, String> paramsp, String token, IRequestCallback requestCallback) {
        RequestCall request = OkHttpUtils.options().url(url).addHeader("Authorization", "Bearer " + token).tag(tag).build();
        addCallBack(requestCallback, request, url);
    }

    @Override
    public void get(String url, IRequestCallback requestCallback) {
        get(url, null, requestCallback);
    }

    @Override
    public void get(String url, Map<String, String> params, IRequestCallback requestCallback) {
        get(url, params, null, requestCallback);
    }

    @Override
    public void get(String url, Map<String, String> params, String token, IRequestCallback requestCallback) {
        RequestCall request = OkHttpUtils.get().url(url).params(params).addHeader("Authorization", "Bearer " + token).tag(tag).build();
        addCallBack(requestCallback, request, appendParams(url, params));
    }


    @Override
    public void put(String url, IRequestCallback requestCallback) {
        RequestCall request = OkHttpUtils.put().url(url).tag(tag).build();

        addCallBack(requestCallback, request, url);
    }

    @Override
    public void delete(String url, IRequestCallback requestCallback) {
        RequestCall request = OkHttpUtils.delete().url(url).tag(tag).build();
        addCallBack(requestCallback, request, url);
    }

    @Override
    public void delete(String url, String token, IRequestCallback requestCallback) {
        RequestCall request = OkHttpUtils.delete().url(url).addHeader("Authorization", "Bearer " + token).tag(tag).build();
        addCallBack(requestCallback, request, url);
    }


    @Override
    public void cancel() {
        OkHttpUtils.getInstance().cancelTag(tag);
    }

    private void addCallBack(final IRequestCallback requestCallback, RequestCall request, final String url) {
        request.execute(new StringCallback() {
                            @Override
                            public void onError(Call call, final Exception e, int id) {
                                e.printStackTrace();
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        requestCallback.onFailure(e, url);
                                    }
                                });
                            }

                            @Override
                            public void onResponse(final String response, int id) {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        requestCallback.onSuccess(response, url);
                                    }
                                });
                            }
                        }

        );
    }

    private void addFileBack(final IRequestCallback requestCallback, RequestCall request) {

        request.execute(new FileCallBack("", "") {

            @Override
            public void inProgress(float progress, long total, int id) {
                super.inProgress(progress, total, id);
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(File response, int id) {

            }
        });
    }

    private String appendParams(String url, Map<String, String> params) {
        if (url == null || params == null || params.isEmpty()) {
            return url;
        }
        Uri.Builder builder = Uri.parse(url).buildUpon();
        Set<String> keys = params.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            builder.appendQueryParameter(key, params.get(key));
        }
        return builder.build().toString();
    }
}
