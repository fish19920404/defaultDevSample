package com.zyjr.emergencylending.base.http;

import java.util.Map;

/**
 * Created by neil on 2017/9/27
 * 备注:
 */
public interface IRequestManager {

    void get(String url, IRequestCallback requestCallback);

    void get(String url, Map<String, String> paramsp, IRequestCallback requestCallback);

    void get(String url, Map<String, String> paramsp, String token, IRequestCallback requestCallback);

    void post(String url, IRequestCallback requestCallback);

    void post(String url, Map<String, String> paramsp, IRequestCallback requestCallback);

    void post(String url, Map<String, String> paramsp, String token, IRequestCallback requestCallback);

    void postFormBody(String url, IRequestCallback requestCallback);

    void postFormBody(String url, Map<String, String> paramsp, IRequestCallback requestCallback);

    void postFormBody(String url, Map<String, String> paramsp, String token, IRequestCallback requestCallback);

    void download(String url, IRequestCallback requestCallback);

    void options(String url, IRequestCallback requestCallback);

    void options(String url, Map<String, String> paramsp, IRequestCallback requestCallback);

    void options(String url, Map<String, String> paramsp, String token, IRequestCallback requestCallback);

    void put(String url, IRequestCallback requestCallback);

    void delete(String url, IRequestCallback requestCallback);
    void delete(String url,String token, IRequestCallback requestCallback);

    void cancel();

}
