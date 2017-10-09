package com.zyjr.emergencylending.business.mvp.model.login;

import android.content.Context;

import com.zyjr.emergencylending.base.bean.CommonResp;
import com.zyjr.emergencylending.base.config.NetContants;
import com.zyjr.emergencylending.base.http.CommonCallBack;
import com.zyjr.emergencylending.base.http.IRequestManager;
import com.zyjr.emergencylending.base.http.RequestFactory;

import java.util.Map;

/**
 * Created by neil on 2017/9/29
 * 备注:
 */

public class LoginModel {

    // 登录App
    public void loginApp(Context context, Map<String, String> parameter, CommonCallBack<CommonResp> commonCallBack) {
        IRequestManager requestManager = RequestFactory.getRequestManager(context);
        requestManager.postFormBody(NetContants.LOGIN, parameter, "", commonCallBack);
    }

    // 获取二维码
    public void getQrCode(Context context, Map<String, String> parameter, CommonCallBack<CommonResp> commonCallBack) {
        IRequestManager requestManager = RequestFactory.getRequestManager(context);
        requestManager.postFormBody(NetContants.MY_QR_CODE, parameter, "", commonCallBack);
    }


}
