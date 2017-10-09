package com.zyjr.emergencylending.base.config;

/**
 *
 */
public class NetContants {
    /*接口服务器地址*/
//    public static final String SERVER_ADDRESS_BASE = "http://192.168.12.197:10011/";//李涛本地
//    public static final String SERVER_ADDRESS_BASE = "http://192.168.11.10:10011/";//向彬本地

    //测试地址
    public static final String SERVER_ADDRESS_BASE = "http://192.168.6.107:9090/";//测试服务器
    //        /*接口H5测试服务器地址*/
    public static final String H5_ADDRESS = "http://192.168.5.185:80/cffgloan/";//H5登录测试服务器
    public static final String H5_PAGE = "http://192.168.6.107:9090/moblie_web_new/h5repayment/";//jjt H5还款测试服务器


    //    //生产地址
//    public static final String SERVER_ADDRESS_BASE = "http://58.247.46.114:12880/";// 生产服务器
    /*接口H5生产服务器地址*/
//    public static final String H5_PAGE = "http://m.jijietong.com:8680/h5-static/moblie_web_new/h5repayment/";//H5 还款生产服务器
//    public static final String H5_ADDRESS = "http://weixin.jijietong.com:6225/";//H5 登录生产服务器

    public static final String CREDITLOAD_HOST = SERVER_ADDRESS_BASE + "JJT-API/http/do.jhtml?router=";
    //图片地址
    public static final String FACE_ADDRESS = "https://api.megvii.com/faceid/v2/verify";


    public static final String H5_FXDREPAYMETT = "http://192.168.6.130:9090/fxd-h5/page/thirdIndex.html?merchant_code_=M11_20170329_00001";
    public static final String FXD_REPAYMENT_HISTORY = "http://192.168.6.130:9090/fxd-h5/page/protocol/jjt_repay_record.html";

    /*接口方法地址*/
    public static final String REPAYMENT_LOGIN = H5_ADDRESS + "appJJTHttpService/login";//hTML登录IP地址不变   http://192.168.11.104/cffg_loan
    public static final String REPAYMENT = H5_PAGE + "page/main.html";//还款页面IP地址改变

    //图形验证码
    public static final String GRAPHICAL_VERIFICATION_CODE = NetContants.SERVER_ADDRESS_BASE + "JJT-API/verCodeServlet.png";
    /**
     * 发送验证码
     */
    public static final String sendsms = NetContants.CREDITLOAD_HOST + "zyCommonService.sendSms";

    public static final String VERSION = NetContants.CREDITLOAD_HOST + "zyAppVersionService.checkAppVersion";
    /*
     *注册
     */
    public static final String REGISTER = NetContants.CREDITLOAD_HOST + "zyUserService.register";
    /*
    退出接口
     */
    public static final String LOGINOUT = NetContants.CREDITLOAD_HOST + "zyUserService.logout";
    public static final String UPDATEPASSWORD = NetContants.CREDITLOAD_HOST + "zyUserService.findPassword";
    public static final String LOGIN = NetContants.CREDITLOAD_HOST + "zyUserService.login";
    //上传头像
    public static final String uploadhead = NetContants.CREDITLOAD_HOST + "zyUserService.uploadHead";
    //上传身份证
    public static final String upload = NetContants.CREDITLOAD_HOST + "zyCommonService.commonUploadFile";

    public static final String MY_QR_CODE = NetContants.CREDITLOAD_HOST + "homeService.getMyQrCode";//我的二维码

    //我的信息 获取用户状态接口
    public static final String getmydata = NetContants.CREDITLOAD_HOST + "customerService.getMyData";

    //认证接口
    public static final String JUXINLI_AUTH = NetContants.CREDITLOAD_HOST + "juxinliService.jxlAuth";


    public static final String JUXINLI_AUTH_2 = NetContants.CREDITLOAD_HOST + "juxinliService.isVaild";
    //提交认证接口
    public static final String TIJIAOAUTH = NetContants.CREDITLOAD_HOST + "juxinliService.submitAuth";
    //获取认证状态
    public static final String GETAUTHSTATUS = NetContants.CREDITLOAD_HOST + "customerService.getAuthStatus";
    //线下jxl无需认证
    public static final String offLineSubmitJxl = NetContants.CREDITLOAD_HOST + "juxinliService.offLineSubmitJxl";

    //保存人脸识别
    public static final String SAVE_FACE_TATUS = NetContants.CREDITLOAD_HOST + "customerService.saveUserFaceValMes";

    //芝麻信用状态
    public static final String ZHI_MA_AUTHORIZATION = NetContants.CREDITLOAD_HOST + "zyZmxyService.zmxyAuth";
    //获取芝麻积分
    public static final String ZHI_MA_INTEGRAL = NetContants.CREDITLOAD_HOST + "zyZmxyService.zmxyScoreGet";
    //预检接口
    public static final String PREPARE_AUDIT = NetContants.CREDITLOAD_HOST + "preAuditService.preAuditForApp";
    //订单信息获取
    public static final String GET_CONFIRM_INFO = NetContants.CREDITLOAD_HOST + "userBorrowService.getUserBorrowInfo";
    //征信保存接口
    public static final String SAVE_CREDIT = NetContants.CREDITLOAD_HOST + "zyUserCreditService.saveCredit";
    //获取征信
    public static final String GET_CREDIT = NetContants.CREDITLOAD_HOST + "zyUserCreditService.getCredit";

    //用户反馈
    public static final String feedback = NetContants.CREDITLOAD_HOST + "feedbackService.save";

    //个人资料保存
    public static final String saveuserdata = NetContants.CREDITLOAD_HOST + "customerService.saveUserData";
    //个人资料获取
    public static final String getuserdata = NetContants.CREDITLOAD_HOST + "customerService.getUserData";
    //工作信息保存
    public static final String userJob = NetContants.CREDITLOAD_HOST + "customerService.saveUserJob";
    //工作信息获取接口
    public static final String getUserJobByCons = NetContants.CREDITLOAD_HOST + "customerService.getUserJobByCons";
    //联系人保存
    public static final String saveusercontact = NetContants.CREDITLOAD_HOST + "customerService.saveUserContact";
    //获取联系人
    public static final String getusercontact = NetContants.CREDITLOAD_HOST + "customerService.getUserContact";
    //获取联系人列表
    public static final String getusercontactlist = NetContants.CREDITLOAD_HOST + "customerService.getUserContactList";
    //获取首页广告
    public static final String GET_HOME_AD = NetContants.CREDITLOAD_HOST + "homeService.getHomeAds";
    //提交借款信息
    public static final String COMMIT_BORROW_INFO = NetContants.CREDITLOAD_HOST + "userBorrowService.saveBorrowinfo";
    //获取门店信息
    public static final String GET_STORES_INFO = NetContants.CREDITLOAD_HOST + "storeService.getStoreByCons";
    //获取我的借款
    public static final String GET_MINE_BORROW = NetContants.CREDITLOAD_HOST + "userBorrowService.getUserBorrowByCons";
    //获取我的借款金额
    public static final String GET_MINE_BORROW_AMOUNT = NetContants.CREDITLOAD_HOST + "zy/zyUserBorrow/getBorrowAmout";
    //获取赚取信息
    public static final String GET_EARN_RECORD = NetContants.CREDITLOAD_HOST + "commissionService.getUserCommMsgPage";
    //获取提现历史
    public static final String GET_EXTRACT_HISTORY = NetContants.CREDITLOAD_HOST + "commissionService.getCommHisPage";
    //获取奖金金额
    public static final String GET_BONUS_AMOUNT = NetContants.CREDITLOAD_HOST + "commissionService.getCommissionAmount";
    //获取消息
    public static final String GET_MESSAGE = NetContants.CREDITLOAD_HOST + "newsService.getUserNewsByCons";
    //更新消息
    public static final String UPDATE_MESSAGE = NetContants.CREDITLOAD_HOST + "newsService.updateByCons";
    //是否能借款
    public static final String BORROWABLE = NetContants.CREDITLOAD_HOST + "userBorrowService.getUserIsBorrow";
    //借款状态
    public static final String BORROW_STATUS = NetContants.CREDITLOAD_HOST + "userBorrowService.getUserBorrowStatus";
    //续贷注册
    public static final String REGISTER_RENEW = NetContants.CREDITLOAD_HOST + "zyOldUserService.renewRegister";
    //业务经理信息
    public static final String CLERK_INFO = NetContants.CREDITLOAD_HOST + "clerkService.initClerkTable";
    //直接客户
    public static final String CLERK_DIRECT = NetContants.CREDITLOAD_HOST + "clerkService.getDirUser";
    //间接客户
    public static final String CLERK_INDIRECT = NetContants.CREDITLOAD_HOST + "clerkService.getIndirUser";
    //开户
    public static final String OPEN_ACCOUNT = NetContants.CREDITLOAD_HOST + "complainceService.openAccount";
    //账户信息查询、保存
    public static final String ACCOUNTDEAL = NetContants.CREDITLOAD_HOST + "complainceService.accountDeal";
    //绑卡
    public static final String BIND_CARD = NetContants.CREDITLOAD_HOST + "complainceService.bindBankCard";
    //绑卡信息查询、保存
    public static final String BIND_CARD_DEAL = NetContants.CREDITLOAD_HOST + "complainceService.bindBankCardDeal";

    //用户坐标信息
    public static final String GET_GPSMES = NetContants.CREDITLOAD_HOST + "customerService.saveGPMes";

    //上传手机联系人接口
    public static final String GET_PHONE_CONTACT_list = NetContants.CREDITLOAD_HOST + "customerService.saveUserMobileContact";
    //获取手机联系人接口
    public static final String OPENBARD = NetContants.CREDITLOAD_HOST + "customerService.getSupBankList";
    public static final String UserLoanType = NetContants.CREDITLOAD_HOST + "customerService.getUserLoanType";

    //订单状态时间
    public static final String ORDER_STATUS_TIME = NetContants.CREDITLOAD_HOST + "userBorrowService.getUserBorrowAudiTims";
    //客户确认查询
    public static final String CUSTOMER_CONFIRM_GET = NetContants.CREDITLOAD_HOST + "userBorrowService.getAuditPassBorrowInfo";
    //客户确认认领
    public static final String CUSTOMER_CONFIRM_CLAIM = NetContants.CREDITLOAD_HOST + "contractService.createContractAndStaging";
    //客户确认拒绝
    public static final String CUSTOMER_CONFIRM_REFUSE = NetContants.CREDITLOAD_HOST + "contractService.refuseToLend";

    //获取签约地址
    public static final String ELECTRONIC_AGREEMENT_GET_URL = NetContants.CREDITLOAD_HOST + "contractService.getSignAddressByUserId";

    //线下推送
    public static final String PUSH_CUST = NetContants.CREDITLOAD_HOST + "pushCustService.pushCust";
    //签约成功
    public static final String SIGN_SUCCESS = "contractService.signSucessCallBack";

    public static final String GET_PRODUCCT_ID = NetContants.CREDITLOAD_HOST + "customerService.getProductID";
    //
    public static final String GET_REPAYBORROW_INFO = NetContants.CREDITLOAD_HOST + "userBorrowService.getRepayBorrowInfo";

    //检验联系人并保存
    public static final String CHECK_CONTACTS_SAVE = NetContants.CREDITLOAD_HOST + "userBorrowService.saveBorrowinfoByCons";

    //客户经理
    //获取客户ID
    public static final String GET_CUSTOMER_ID = NetContants.CREDITLOAD_HOST + "clerkService.calCustomerID";
    //获取产品ID
    public static final String GET_CUSTOMER_PRODUCCT_ID = NetContants.CREDITLOAD_HOST + "clerkService.getProductID";
    //获取客户资料
    public static final String GET_CUSTOMER_DATA = NetContants.CREDITLOAD_HOST + "clerkService.getUserData";
    //保存客户资料
    public static final String SAVE_CUSTOMER_DATA = NetContants.CREDITLOAD_HOST + "clerkService.saveUserData";
    //获取客户工作资料
    public static final String GET_CUSTOMER_JOB = NetContants.CREDITLOAD_HOST + "clerkService.getUserJobByCons";
    //保存客户工作资料
    public static final String SAVE_CUSTOMER_JOB = NetContants.CREDITLOAD_HOST + "clerkService.saveUserJob";
    //获取客户工作资料
    public static final String GET_CUSTOMER_CONTACT = NetContants.CREDITLOAD_HOST + "clerkService.getUserContactList";
    //保存客户联系人资料
    public static final String SAVE_CUSTOMER_CONTACT = NetContants.CREDITLOAD_HOST + "clerkService.saveUserContact";
    //业务员申请记录查询
    public static final String QUERY_CUSTOMER_RECORD = NetContants.CREDITLOAD_HOST + "clerkService.getCustBorrowMes";

    //app日志保存
    public static final String APP_LOG = NetContants.CREDITLOAD_HOST + "zyCommonService.saveAppLog";
}
