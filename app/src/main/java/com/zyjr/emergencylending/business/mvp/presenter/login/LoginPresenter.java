package com.zyjr.emergencylending.business.mvp.presenter.login;

import android.content.Context;

import com.zyjr.emergencylending.base.bean.CommonResp;
import com.zyjr.emergencylending.base.http.CommonCallBack;
import com.zyjr.emergencylending.business.mvp.model.login.LoginModel;
import com.zyjr.emergencylending.business.mvp.presenter.BasePresenter;
import com.zyjr.emergencylending.business.mvp.view.login.LoginViewExtend;

import java.util.Map;

/**
 * Created by neil on 2017/9/29
 * 备注:
 */
public class LoginPresenter extends BasePresenter<LoginViewExtend> {

    private LoginModel loginModel;

    public LoginPresenter() {
        this.loginModel = new LoginModel();
    }

    //登录
    public void loginApp(Context context, Map<String, String> parameter, String className) {

        loginModel.loginApp(context, parameter, new CommonCallBack<CommonResp>(context, CommonResp.class, className, false) {
            @Override
            public void success(CommonResp commonResp, String url) {
                if (getMvpView() != null) {
                    getMvpView().loginResult(commonResp, url);
                }
            }

            @Override
            public void failure(int returnCode, String errorMessage) {
                getMvpView().fail(returnCode, errorMessage);
            }

            @Override
            public void failure(String errorMsg, String url) {
                getMvpView().loginResult(errorMsg, url);
            }
        });

    }


    // 获取二维码
    public void getQrCode(Context context, Map<String, String> parameter, String className) {

        loginModel.getQrCode(context, parameter, new CommonCallBack<CommonResp>(context, CommonResp.class, className, false) {
            @Override
            public void success(CommonResp commonResp, String url) {
                if (getMvpView() != null) {
                    getMvpView().loginResult(commonResp, url);
                }
            }

            @Override
            public void failure(int returnCode, String errorMessage) {
                getMvpView().fail(returnCode, errorMessage);
            }

            @Override
            public void failure(String errorMsg, String url) {
                getMvpView().loginResult(errorMsg, url);
            }
        });

    }


}
