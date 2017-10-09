package com.zyjr.emergencylending.base.http;

/**
 * Created by neil on 2017/9/28
 * 备注:
 */
public class RequestFactory {

    public static IRequestManager getRequestManager(Object tag){
        return OkHttpRequestManager.getInstance(tag);
    }
}
