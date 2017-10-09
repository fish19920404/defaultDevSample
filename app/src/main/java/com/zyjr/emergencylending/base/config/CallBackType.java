package com.zyjr.emergencylending.base.config;

/**
 * 项目名称：急借通
 * 类描述：CallBack类型
 * 创建人：szx
 * 创建时间：2016/8/29 10:12
 * 修改人：szx
 * 修改时间：2016/8/29 10:12
 * 修改备注：
 */
public class CallBackType {
    public static final int REGISTER = 8;
    public static final int LOGIN = 9;

    public static final int HOME_AD = 10;
    public static final int PRESENT_STORE = 12;
    public static final int MINE_BORROW = 13;
    public static final int MINE_BORROW_AMOUNT = 14;
    public static final int COMMIT_BORROW_INFO = 15;

    public static final int BONUS_TOTAL = 16;
    public static final int BONUS_AVAILABLE = 17;
    public static final int BONUS_EXTRACT = 18;
    public static final int BONUS_EARN_RECORD = 19;
    public static final int BONUS_EXTRACT_HISTORY = 20;

    public static final int LOCATION = 21;
    public static final int MESSAGE_READ_COUNT = 22;
    public static final int MESSAGE_GET = 23;
    public static final int MESSAGE_READ = 24;
    public static final int MESSAGE_DEL = 25;
    public static final int MESSAGE_DEL_ALL = 26;
    public static final int BORRABLE = 27;
    public static final int CREDENTIAL_STATE = 28;


    public static final int SAVE_CREDIT = 29;
    public static final int GET_CREDIT = 30;
    public static final int BORROW_STATUS = 31;
    public static final int BORROW_STATUS_BACK = 32;

    public static final int GET_PERSONAL_INFOR = 33;

    public static final int SAVA_PERSONAL_INFOR = 34;

    public static final int GET_JOB_INFOR = 35;

    public static final int SAVA_JOB_INFOR = 36;

    public static final int GET_CONTACT_LIST = 37;

    public static final int GET_CONTACT_INFO = 38;

    public static final int SAVA_CONTACT = 39;

    public static final int LOGINOUT = 40;

    public static final int SUBMIT_SUGGESTION = 41;

    public static final int IDCARD = 42;

    public static final int HEAD = 43;

    public static final int GET_USER_STATU = 44;
    public static final int UPDATE_PASSWORD = 45;
    public static final int MY_CODE = 46;

    public static final int CLERK_INFO = 47;
    public static final int CLERK_DIRECT = 48;
    public static final int CLERK_INDIRECT = 49;

    public static final int OPEN_ACCOUNT = 50;
    public static final int TIED_CARD = 51;

    public static final int CUSTOMER_CONFIRM_QUERY = 52;
    public static final int CUSTOMER_CONFIRM_CLAIM = 53;
    public static final int CUSTOMER_CONFIRM_REFUSE = 54;

    public static final int CREDENTIAL_FACE_STATE = 55;
    public static final int PREPARE_AUDIT = 56;
    public static final int GET_CONFIRM_INFO = 57;

    public static final int PUSH_CUST = 58;
    public static final int FACE_VERIFY = 59;

    public static final int ELECTRONIC_AGREEMENT = 60;
    public static final int ORDER_STATUS_TIME = 61;

    public static final int IDCARD_RESPONSE_FORNT = 62;

    public static final int IDCARD_RESPONSE_BACK = 63;

    public static final int GRAPHICAL_VERIFICATION_CODE = 64;

    public static final int GET_USER_STATU_FROM_AUTH = 65;

    public static final int OFFLINE_SKIP_JXL = 66;

    public static final int REPAYBORROW_INFO = 67;
    public static final int CHECK_CONTACTS_SAVE = 68;

    public static final int GET_CUSTOMER_ID = 69;
    //获取客户资料
    public static final int GET_CUSTOMER_DATA = 70;
    //保存客户资料
    public static final int SAVE_CUSTOMER_DATA = 71;
    //获取客户工作资料
    public static final int GET_CUSTOMER_JOB = 72;
    //保存客户工作资料
    public static final int SAVE_CUSTOMER_JOB = 73;
    //获取客户工作资料
    public static final int GET_CUSTOMER_CONTACT = 74;
    //保存客户联系人资料
    public static final int SAVE_CUSTOMER_CONTACT = 75;
    //业务员申请记录查询
    public static final int QUERY_CUSTOMER_RECORD = 76;
    //获取产品ID
    public static final int GET_PRODUCT_ID = 77;
    //获取产品ID 与之前相同
    public static final int GET_PRODUCT_ID_SAME = 78;
    //业务员线下
    public static final int CUSTOMER_OFFLINE_SKIP_JXL = 79;

    public static final int CUSTOMER_COMMIT_BORROW_INFO = 80;
    //芝麻认证
    public static final int CREDENTIAL_ZHIMA_AUTHORIZATION = 90;
    //芝麻积分
    public static final int CREDENTIAL_ZHIMA_INTEGRAL = 91;
}
