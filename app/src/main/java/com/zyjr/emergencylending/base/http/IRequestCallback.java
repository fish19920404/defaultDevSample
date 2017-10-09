package com.zyjr.emergencylending.base.http;

/**
 * Created by neil on 2017/9/27
 * 备注: 返回信息回调
 */
public interface IRequestCallback {

    void onSuccess(String response, String url);

    void onFailure(Throwable throwable, String url);

}
