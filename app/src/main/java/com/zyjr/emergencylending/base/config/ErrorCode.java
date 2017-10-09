package com.zyjr.emergencylending.base.config;

/**
 * 项目名称：急借通
 * 类描述：错误码
 * 创建人：szx
 * 创建时间：2016/8/22 14:40
 * 修改人：szx
 * 修改时间：2016/8/22 14:40
 * 修改备注：
 */
public class ErrorCode {

    public static final String SUCCESS = "0000";
    public static final String IS_APPLYING = "0004";
    public static final String LOGIN_TO_FORGET_PASSWORD = "0005";
    public static final String EQUIPMENT_KICKED_OUT = "0006";
    public static final String JXL_AUTHCODE_SUCCESS = "00040004";//聚信力认证验证码获取成功
    public static final String CHECK_TIME_OUR = "00040005";//认证超时
    public static final String NONUPDATE = "0012";
    public static final String FAIL_DATA = "网络数据异常";
    public static final String FAIL_NETWORK = "网络连接异常";
}
