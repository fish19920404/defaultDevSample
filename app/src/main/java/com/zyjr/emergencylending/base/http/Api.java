package com.zyjr.emergencylending.base.http;

import com.zyjr.emergencylending.base.config.AppConfig;

/**
 * Created by neil on 2017/9/27
 */
public class Api {

    public static final String BASE_URL;
    public static final String URL;
    private static final String HTTP = "http://";
    private static final String ROOT_API = "api/";

    static {
        if (AppConfig.DEBUG) {
//            BASE_URL = IApplication.tempApi;
            BASE_URL = "";
        } else {
            BASE_URL = "api.gcjiujiu.com/"; //存放正式地址
        }
        URL = HTTP + BASE_URL;// + ROOT_API;
    }

    /**
     * 获取首页数据
     */
    public static final String HOME = "Home/HomePageData";


}
