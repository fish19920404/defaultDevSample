//package com.zyjr.emergencylending.base.utils;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.text.TextUtils;
//
//import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
//import com.tencent.mm.sdk.modelmsg.WXImageObject;
//import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
//import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
//import com.tencent.mm.sdk.openapi.IWXAPI;
//import com.tencent.mm.sdk.openapi.WXAPIFactory;
//import com.tencent.open.utils.ThreadManager;
//import com.tencent.tauth.IUiListener;
//import com.tencent.tauth.Tencent;
//import com.tencent.tauth.UiError;
//import com.two.emergencylending.application.IApplication;
//
//import java.io.ByteArrayOutputStream;
//
///**
// * Created by User on 2016/9/4.
// */
//public class ShareUtil {
//    public static final String APP_ID = "wx14758d54ffd39c18";
//    public static final String qq_ID = "1105705046";//ZQytMZ08hlPVdcnT
//    private Context context;
//    private IWXAPI api;
//    private Tencent mTencent;
//    private static ShareUtil instance;
//    private static final int THUMB_SIZE = 200;
//    private Handler mainHandler;
//
//    private ShareUtil(Context context) {
//        this.context = context;
//        api = WXAPIFactory.createWXAPI(context, ShareUtil.APP_ID, true);
//        // 将该app注册到微信
//        api.registerApp(ShareUtil.APP_ID);
//        mainHandler = new Handler(Looper.getMainLooper());
////        handlerThread = new HandlerThread("Share Thread");
////        handlerThread.start();
//
//    }
//
//    public static void initInstace(Context context) {
//        if (instance == null) {
//            instance = new ShareUtil(context);
//        }
//    }
//
//    public static ShareUtil getInstance() {
//        if (instance == null) {
//            instance = new ShareUtil(IApplication.getInstance().gainContext());
//        }
//
//        return instance;
//    }
//
//    public IWXAPI getApi() {
//        return api;
//    }
//
//    /**
//     * 分享图片类型
//     *
//     * @param flag   0 微信好友 1 微信朋友圈
//     * @param bitmap 图片   [res,bmp,path,url任传其一]
//     */
//    public void shareToWechatForBitmap(final int flag, final Bitmap bitmap) {
//        try {
//            if (flag != 0 && flag != 1) {
//                return;
//            }
//            if (bitmap == null) {
//                return;
//            }
//            //判断是否安装了微信App
//            if (!api.isWXAppInstalled()) {
//                mainHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        ToastAlone.showLongToast(context, "未安装微信客户端");
//                    }
//                });
//                return;
//            }
//            WXImageObject imgObj = new WXImageObject(bitmap);
//            WXMediaMessage msg = new WXMediaMessage();
//            msg.mediaObject = imgObj;
//            Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, THUMB_SIZE, THUMB_SIZE, true);
//            bitmap.recycle();
//            msg.thumbData = ToolWXUtil.bmpToByteArray(thumbBmp, true);
//            SendMessageToWX.Req req = new SendMessageToWX.Req();
//            req.transaction = buildTransaction("img");
//            req.message = msg;
//            req.scene = flag == 0 ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
//            api.sendReq(req);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // 发送有图片的微信
//    public void sendReq(String shareTxt, Bitmap bmp, boolean isfriend) {
//        sendReq(shareTxt, bmp, null, isfriend);
//    }
//
//    public void sendReq(String shareTxt, Bitmap bmp, String des, boolean isfriend) {
//        //判断是否安装了微信App
//        if (!api.isWXAppInstalled()) {
//            mainHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    ToastAlone.showLongToast(context, "未安装微信客户端");
//                }
//            });
//            return;
//        }
//        WXImageObject imgObj = new WXImageObject(bmp);
//        WXMediaMessage msg = new WXMediaMessage();
//        msg.mediaObject = imgObj;
//        // Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE,
//        // THUMB_SIZE, true);
//        // bmp.recycle();
//        msg.thumbData = compressImage(bmp, 32); // 设置缩略图
//        if (des != null)
//            msg.description = des;
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = buildTransaction("img");
//        req.message = msg;
//        req.scene = isfriend ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
//        api.sendReq(req);
//    }
//
//    /**
//     * 压缩图片
//     *
//     * @param image
//     * @param size
//     * @return
//     */
//    private byte[] compressImage(Bitmap image, int size) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        image.compress(Bitmap.CompressFormat.PNG, 0, baos);
//        BitmapFactory.Options newOpts = new BitmapFactory.Options();
//        newOpts.inJustDecodeBounds = false;
//        int min = baos.toByteArray().length;
//        int scale = (int) Math.ceil((double) min / size / 1024);
//        newOpts.inSampleSize = scale;// 设置缩放比例
//        if (min > size * 1024) {
//            Bitmap newbm = BitmapFactory.decodeByteArray(baos.toByteArray(), 0, min, newOpts);
//            image.recycle();
//            image = newbm;
//        }
//
//        baos.reset();// 重置baos即清空baos
//        image.compress(Bitmap.CompressFormat.PNG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
//        int options = 100;
//
//        while (baos.toByteArray().length > size * 1024) { // 循环判断如果压缩后图片是否大于,大于继续压缩
//            if (options < 0) {
//                break;
//            }
//            baos.reset();// 重置baos即清空baos
//            image.compress(Bitmap.CompressFormat.PNG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
//            options -= 10;// 每次都减少10
//        }
//        image.recycle();
//        return baos.toByteArray();
//    }
//
//    /**
//     * transaction字段用于唯一标识一个请求
//     *
//     * @param type
//     * @return
//     */
//    private String buildTransaction(final String type) {
//        return (type == null)
//                ? String.valueOf(System.currentTimeMillis())
//                : type + System.currentTimeMillis();
//    }
//
//
//    public void shareToWechat(int flag, String url, String title, String description, Bitmap bitmap) {
//        if (!api.isWXAppInstalled()) {
//            mainHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    ToastAlone.showLongToast(context, "未安装微信客户端");
//                }
//            });
//            return;
//        }
//        WXWebpageObject webpage = new WXWebpageObject();
//        webpage.webpageUrl = url;
//        WXMediaMessage msg = new WXMediaMessage(webpage);
//        msg.title = title;
//        msg.description = description;
//        msg.setThumbImage(bitmap);
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = String.valueOf(System.currentTimeMillis());
//        req.message = msg;
//        req.scene = flag == 0 ? SendMessageToWX.Req.WXSceneSession : SendMessageToWX.Req.WXSceneTimeline;
//        api.sendReq(req);
//    }
//
//    public void shareToQQ(final Activity context, String url, String title, String content, String imgUrl) {
//        //IContants.APPID_QQ  腾讯开放平台对应的appid
//        if (mTencent == null)
//            mTencent = Tencent.createInstance(ShareUtil.qq_ID, context);
//
//        final Bundle params = new Bundle();
//        params.putInt(com.tencent.connect.share.QQShare.SHARE_TO_QQ_KEY_TYPE, com.tencent.connect.share.QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
//        params.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_TITLE, title);           //标题
//        params.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_SUMMARY, content);       //内容
//        params.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_TARGET_URL, url); //url地址
//        if (!TextUtils.isEmpty(imgUrl)) {
//            params.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_IMAGE_URL, imgUrl);   //图片
//        }
//
//        params.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_APP_NAME, CommonalityFieldUtils.getCurrAppName());      //APP名
//        ThreadManager.getMainHandler().post(new Runnable() {
//            @Override
//            public void run() {
//                if (null != mTencent) {
//                    mTencent.shareToQQ(context, params, new IUiListener() {
//                        @Override
//                        public void onError(UiError arg0) {
//                            ToastAlone.showLongToast(context, arg0.toString());
//                        }
//
//                        @Override
//                        public void onComplete(Object arg0) {
//                            ToastAlone.showLongToast(context, "分享成功");
//                        }
//
//                        @Override
//                        public void onCancel() {
//                            ToastAlone.showLongToast(context, "分享取消");
//                        }
//                    });
//                }
//            }
//
//        });
//
//
//    }
//
//    public void sendSMS(Activity activity, String smsBody) {
//        Uri smsToUri = Uri.parse("smsto:");
//        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.putExtra("sms_body", smsBody);
//        activity.startActivity(intent);
//
//    }
//}
