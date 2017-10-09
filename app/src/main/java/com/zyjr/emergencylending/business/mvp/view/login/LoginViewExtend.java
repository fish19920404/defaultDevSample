package com.zyjr.emergencylending.business.mvp.view.login;

import com.zyjr.emergencylending.base.bean.CommonResp;
import com.zyjr.emergencylending.business.mvp.view.BaseView;

/**
 * Created by neil on 2017/9/29
 * 备注:
 */
public interface LoginViewExtend extends BaseView {

    // 登录操作
    void loginResult(CommonResp commonResp, String url);

    // 请求失败
    void loginResult(String errorMsg, String url);

}
