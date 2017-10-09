package com.zyjr.emergencylending.base.bean;

import java.io.Serializable;

/**
 * Created by neil on 2017/9/27
 * 备注: 公共请求参数
 */
public class RequestBean implements Serializable {
    private static final long serialVersionUID = 6174381323722046732L;

    private String app_no; //服务端提供通道编 XP20161116100001
    private String user_name;// 服务端提供用户名 appuser
    private String token; // 服务端提供登陆标识码 DZXh8eeTuAHoYE3w1J
    private String out_run_no; // 执行流水号（需保证当前商户下唯一）规则:通道简称（2位）+当前时间(yyyyMMddHHmmss)（14位）+ 顺序号（6位）。注意：基础接口可为空
    private String method; // 接口名称 chinazyjr_opr_custpush
    private String charset; // 编码方式，本版定死UTF-8
    private String version; //调用接口版本，本版定死v2.0
    private String sign;// 签名
    private String out_url;  // 回调地址
    private String request_content;  // 请求参数集合，除公共参数外所有请求参数都必须放在这个参数中传递

    public String getApp_no() {
        return app_no;
    }

    public void setApp_no(String app_no) {
        this.app_no = app_no;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOut_run_no() {
        return out_run_no;
    }

    public void setOut_run_no(String out_run_no) {
        this.out_run_no = out_run_no;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOut_url() {
        return out_url;
    }

    public void setOut_url(String out_url) {
        this.out_url = out_url;
    }

    public String getRequest_content() {
        return request_content;
    }

    public void setRequest_content(String request_content) {
        this.request_content = request_content;
    }

    @Override
    public String toString() {
        return "RequestBean{" +
                "app_no='" + app_no + '\'' +
                ", user_name='" + user_name + '\'' +
                ", token='" + token + '\'' +
                ", out_run_no='" + out_run_no + '\'' +
                ", method='" + method + '\'' +
                ", charset='" + charset + '\'' +
                ", version='" + version + '\'' +
                ", sign='" + sign + '\'' +
                ", out_url='" + out_url + '\'' +
                ", request_content='" + request_content + '\'' +
                '}';
    }
}
