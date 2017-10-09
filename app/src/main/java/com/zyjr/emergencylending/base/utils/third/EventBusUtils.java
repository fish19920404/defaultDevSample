package com.zyjr.emergencylending.base.utils.third;


/**
 * Created by l3 on 2017/3/6.
 */
public class EventBusUtils {

    public final static String LOGIN_EVENT = "login";
    public final static String LOGOUT_EVENT = "logout";
    public final static String INVEST_SUCCTSS = "invest_success";
    public final static String CASH_WITHDRAW_SUCCESS = "cash_withdraw_success";
    public static final String CANCEL_CASH_DRAW_REFRESH = "cancel_cash_draw_refresh";
    public static final String RECHARGE_REFRESH = "recharge_refresh";//充值刷新
    public static final String SETGESTURE_PASSWORD = "set_gestures_password";//设置手势密码
    public static final String CHANGEGESTURE_PASSWORD = "change_gestures_password";//修改手势密码
    public static final String POINT_STORE = "point_store_page";
    public static final String BIND_SUCCESS_EVENT = "bind_card_success_event";
    public static final String RECHARGE_FINISH = "recharge_finish";


    // 在要接收消息的页面注册
    public static void register(Object obj) {
    }

    //解除注册
    public static void unregister(Object obj) {
    }

    // 发送消息
    public static void post(Object obj) {
    }
}
