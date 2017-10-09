package com.zyjr.emergencylending.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zyjr.emergencylending.base.bean.CommonResp;
import com.zyjr.emergencylending.base.common.BaseActivity;
import com.zyjr.emergencylending.base.common.IApplication;
import com.zyjr.emergencylending.base.common.MvpBaseActivity;
import com.zyjr.emergencylending.base.config.NetContants;
import com.zyjr.emergencylending.base.http.CommonCallBack;
import com.zyjr.emergencylending.base.http.IRequestManager;
import com.zyjr.emergencylending.base.http.RequestFactory;
import com.zyjr.emergencylending.base.utils.IntentUtils;
import com.zyjr.emergencylending.base.utils.LangUtil;
import com.zyjr.emergencylending.base.utils.LogUtils;
import com.zyjr.emergencylending.base.utils.ToastUtils;
import com.zyjr.emergencylending.base.widget.dialog.CustomerDialog;
import com.zyjr.emergencylending.business.activities.MainActivity;
import com.zyjr.emergencylending.business.mvp.presenter.login.LoginPresenter;
import com.zyjr.emergencylending.business.mvp.view.login.LoginViewExtend;
import com.zyjr.emergencylending.pak.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeActivity extends MvpBaseActivity<LoginViewExtend, LoginPresenter> implements LoginViewExtend {
    private final static String TAG = "HomeActivity";
    private Unbinder unbinder;

    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_getCode)
    Button btnGetCode;
    @BindView(R.id.btn_shopw_code)
    Button btnShowCode;
    @BindView(R.id.et_back_content)
    EditText etShow;

    private Map<String, String> codeParameter = new HashMap<>();
    private CustomerDialog dialog;
    private String qrCodeUrl = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        unbinder = ButterKnife.bind(this);
        dialog = new CustomerDialog(this);
        initBindListener();
    }

    public void initBindListener() {
    }

    @OnClick({R.id.btn_shopw_code, R.id.btn_getCode, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                Map<String, String> parameter = new HashMap<>();
                parameter.put("app_version_no", "1.29");
                parameter.put("register_platform", "JJT_1");
                parameter.put("phone", "17621920452");
                parameter.put("password", "123456");
                parameter.put("login_ip", "192.168.9.57");
                parameter.put("clientid", "");
                parameter.put("login_platform", "JJT_1");
                parameter.put("login_device_no", "Xiaomi,MI 4LTE,6.0.1");
                parameter.put("longitude", IApplication.longitude);
                parameter.put("latitude", IApplication.latitude);
                getPresenter().loginApp(HomeActivity.this, parameter, HomeActivity.class.getName());
                IntentUtils.goToActivity(this, MainActivity.class);
                break;
            case R.id.btn_getCode:
                getPresenter().getQrCode(HomeActivity.this, codeParameter, HomeActivity.class.getName());
                break;
            case R.id.btn_shopw_code:
                String code = "123456";
                dialog.qrCode(qrCodeUrl + "?register_platform=JJT_1", code);
                dialog.show();
                break;

        }
    }


    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public LoginViewExtend createView() {
        return this;
    }

    @Override
    public void fail(int returnCode, String errorMessage) {
        LogUtils.d(TAG, "请求失败---->" + returnCode + "," + errorMessage);
    }

    @Override
    public void loginResult(CommonResp commonResp, String url) {
        if (url.contains("serService.login")) {
            LogUtils.d(TAG, "登录请求成功---->" + commonResp.getResult());
            ToastUtils.showShortToast("请求成功---->" + commonResp.getResult());
            JSONObject json = JSON.parseObject(commonResp.getResult());
            codeParameter.put("juid", json.getString("juid"));
            codeParameter.put("login_token", json.getString("login_token"));
            getPresenter().getQrCode(HomeActivity.this, codeParameter, HomeActivity.class.getName());
            etShow.setText(commonResp.getResult());
        } else if (url.contains("homeService.getMyQrCode")) {
            LogUtils.d(TAG, "获取二维码请求成功---->" + commonResp.getResult());
            JSONObject json = JSON.parseObject(commonResp.getResult());
            qrCodeUrl = json.getString("qr_code_url");
            if (!LangUtil.isBlank(etShow.getText().toString())) {
                etShow.setText(qrCodeUrl);
            }
        }

    }

    @Override
    public void loginResult(String errorMsg, String url) {
        LogUtils.d(TAG, "请求失败---->" + errorMsg);
        ToastUtils.showShortToast("请求失败---->" + errorMsg);
    }

}
