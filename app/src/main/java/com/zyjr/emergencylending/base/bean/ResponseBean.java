package com.zyjr.emergencylending.base.bean;

import java.io.Serializable;

/**
 * Created by neil on 2017/9/27
 * 备注: 公共响应参数
 */
public class ResponseBean implements Serializable {

    private static final long serialVersionUID = 6134381323722046732L;

    /**
     * flag : 0000
     * msg : 请求成功
     * result : {"juid":"90d11ef91fda4c80bde8bb605bc39c17","recommendCode":"A631271","is_clerk":"0","login_token":"8ad9e577cbdd4adb97eb5c8771034ebb"}
     * lockerFlag : true
     */
    private String flag;
    private String msg;
    private String result;
    private boolean lockerFlag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isLockerFlag() {
        return lockerFlag;
    }

    public void setLockerFlag(boolean lockerFlag) {
        this.lockerFlag = lockerFlag;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
