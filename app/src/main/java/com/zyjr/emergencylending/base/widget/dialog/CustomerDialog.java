package com.zyjr.emergencylending.base.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zyjr.emergencylending.base.config.AppConfig;
import com.zyjr.emergencylending.base.utils.LangUtil;
import com.zyjr.emergencylending.base.utils.ToastUtils;
import com.zyjr.emergencylending.base.utils.third.GlideUtils;
import com.zyjr.emergencylending.base.widget.dialog.flowTag.FlowTagLayout;
import com.zyjr.emergencylending.base.widget.dialog.flowTag.OnTagSelectListener;
import com.zyjr.emergencylending.base.widget.dialog.flowTag.TagAdapter;
import com.zyjr.emergencylending.business.bean.BorrowInfoBean;
import com.zyjr.emergencylending.business.bean.PeriodBean;
import com.zyjr.emergencylending.business.bean.PeriodContent;
import com.zyjr.emergencylending.business.bean.UsageBean;
import com.zyjr.emergencylending.business.bean.UsageContent;
import com.zyjr.emergencylending.pak.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 作者：User on 2015/9/15 14:54
 */
public class CustomerDialog extends Dialog {

    public Activity mContext;
    public LinearLayout ll_cancel, ll_sure;
    public Window window;
    public boolean periodIsFill = false;
    public boolean isChecked = true;
    public BorrowInfoBean borrowConfirmInfo = new BorrowInfoBean();
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public CustomerDialog(Activity context) {
        super(context, R.style.transparentFrameWindowStyle);
        this.mContext = context;
        window = getWindow();
    }

    /**
     * 用户拒绝dialog
     *
     * @param onClickListener
     */
    public void showHotLineDialog(View.OnClickListener onClickListener) {
        TextView cancel, tv_title;
        setContentView(R.layout.widget_dialog_hot_line);
        cancel = (TextView) findViewById(R.id.cancel);
        tv_title = (TextView) findViewById(R.id.tv_title);
        window.setGravity(Gravity.BOTTOM);
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置点击外围解散
        CustomerDialog.this.setCanceledOnTouchOutside(false);
        cancel.setOnClickListener(onClickListener);
        tv_title.setOnClickListener(onClickListener);
    }

    /**
     * 借款失败返回dialog
     */
    public void showOrderFailed(Context context, View.OnClickListener onClickListener) {
        TextView cancel;
        setContentView(R.layout.widget_dialog_loan_usage);
        cancel = (TextView) findViewById(R.id.cancel);
        window.setGravity(Gravity.CENTER);
        // 设置显示动画
        window.setWindowAnimations(R.style.dialog_loan_usage);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置点击外围解散
        CustomerDialog.this.setCanceledOnTouchOutside(false);
        cancel.setOnClickListener(onClickListener);
    }


    /**
     * 借款用途dialog
     */
    public void showUsageDialog(Context context, OnTagSelectListener onTagSelectListener) {
        FlowTagLayout flowlayout;
        TagAdapter<UsageBean> mTagAdapter;
        TextView cancel, tv_title;
        setContentView(R.layout.widget_dialog_loan_usage);
        flowlayout = (FlowTagLayout) findViewById(R.id.flowlayout);
        flowlayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        flowlayout.setSelectDefault(-1);
        mTagAdapter = new TagAdapter<>(context);
        flowlayout.setAdapter(mTagAdapter);
        mTagAdapter.onlyAddAll(UsageContent.getInstance().getUsagetotal());
        cancel = (TextView) findViewById(R.id.cancel);
        tv_title = (TextView) findViewById(R.id.tv_title);
        window.setGravity(Gravity.CENTER);
        // 设置显示动画
        window.setWindowAnimations(R.style.dialog_loan_usage);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置点击外围解散
        CustomerDialog.this.setCanceledOnTouchOutside(false);
//        cancel.setOnClickListener(onClickListener);
//        tv_title.setOnClickListener(onClickListener);
        flowlayout.setOnTagSelectListener(onTagSelectListener);
    }

    /**
     * 申请失败确认dialog
     */
    public void confirmFailDialog() {
        setContentView(R.layout.dialog_confirm_fail);
        Button confirm = (Button) findViewById(R.id.confirm);
        window.setGravity(Gravity.CENTER);
        // 设置显示动画
        window.setWindowAnimations(R.style.dialog_loan_usage);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置点击外围解散
        CustomerDialog.this.setCanceledOnTouchOutside(false);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerDialog.this.dismiss();
            }
        });
    }

    /**
     * 申请失败确认dialog
     */
    public void appleyCheckFailDialog() {
        setContentView(R.layout.dialog_confirm_fail);
        Button confirm = (Button) findViewById(R.id.confirm);
        window.setGravity(Gravity.CENTER);
        // 设置显示动画
        window.setWindowAnimations(R.style.dialog_loan_usage);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置点击外围解散
        CustomerDialog.this.setCanceledOnTouchOutside(false);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerDialog.this.dismiss();
            }
        });
    }


    /**
     * 运营商电话
     */
    public void mobileDialog(View.OnClickListener onClickListener) {
        TextView cancel, tv_move, tv_unicom, tv_telecommunication;
        setContentView(R.layout.layout_moible_popuwindow);
        cancel = (TextView) findViewById(R.id.cancel);
        tv_move = (TextView) findViewById(R.id.tv_move);
        tv_unicom = (TextView) findViewById(R.id.tv_unicom);
        tv_telecommunication = (TextView) findViewById(R.id.tv_telecommunication);
        window.setGravity(Gravity.BOTTOM);
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置点击外围解散
        CustomerDialog.this.setCanceledOnTouchOutside(false);
        cancel.setOnClickListener(onClickListener);
        tv_move.setOnClickListener(onClickListener);
        tv_unicom.setOnClickListener(onClickListener);
        tv_telecommunication.setOnClickListener(onClickListener);
    }

    /**
     * 版本更新提示
     *
     * @param onClickListener
     */
    public void alertMessage(View.OnClickListener onClickListener, String[] msgs, final Boolean isForbid) {
        setContentView(R.layout.activity_updateversion);
        TextView tvTopTitle = (TextView) findViewById(R.id.tv_topTitle);
        TextView tvMsg = (TextView) findViewById(R.id.tv_msg);
        TextView tvCancel = (TextView) findViewById(R.id.cancel);
        TextView nextAction = (TextView) findViewById(R.id.next_action);
        LinearLayout confirm = (LinearLayout) findViewById(R.id.ll_sure);
        LinearLayout cancel = (LinearLayout) findViewById(R.id.ll_cancel);
        confirm.setOnClickListener(onClickListener);
        cancel.setOnClickListener(onClickListener);
        CustomerDialog.this.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    if (isForbid) {
                        return true;
                    }
                    return false;
                } else {
                    return false;
                }
            }
        });
        if (msgs.length == 4) {
            tvTopTitle.setText(msgs[0]);
            tvMsg.setText(msgs[1]);
            tvCancel.setText(msgs[2]);
            nextAction.setText(msgs[3]);
            nextAction.setVisibility(View.VISIBLE);
        }
        window.setGravity(Gravity.CENTER);
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置点击外围解散
        CustomerDialog.this.setCanceledOnTouchOutside(false);
        CustomerDialog.this.show();
    }

    public void advatarDialog(View.OnClickListener onClickListener) {
        TextView tv_picture, tv_album, cancel;
        setContentView(R.layout.adavater_layout);
        tv_picture = (TextView) findViewById(R.id.tv_picture);
        tv_album = (TextView) findViewById(R.id.tv_album);
        cancel = (TextView) findViewById(R.id.cancel);
        window.setGravity(Gravity.BOTTOM);
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置点击外围解散
        CustomerDialog.this.setCanceledOnTouchOutside(false);
        cancel.setOnClickListener(onClickListener);
        tv_picture.setOnClickListener(onClickListener);
        tv_album.setOnClickListener(onClickListener);
    }

    /**
     * 用户信息提示
     *
     * @param onClickListener
     * @param msgs
     */
    public void showChoiceDialog(View.OnClickListener onClickListener, String[] msgs) {
        TextView cancel, next_action, msg;
        LinearLayout choice_layout;
        setContentView(R.layout.activity_refusedialog);
        ll_cancel = (LinearLayout) findViewById(R.id.ll_cancel);
        ll_sure = (LinearLayout) findViewById(R.id.ll_sure);
        TextView tv_message = (TextView) findViewById(R.id.tv_message);
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        cancel = (TextView) findViewById(R.id.cancel);
        next_action = (TextView) findViewById(R.id.next_action);
        msg = (TextView) findViewById(R.id.msg);
        choice_layout = (LinearLayout) findViewById(R.id.choice_layout);
        if (msgs.length == 3) {
            tv_message.setText(msgs[0]);
            tv_title.setText(msgs[1]);
            msg.setText(msgs[2]);
            msg.setVisibility(View.VISIBLE);
            msg.setOnClickListener(onClickListener);
            choice_layout.setVisibility(View.GONE);
        } else if (msgs.length == 4) {
            tv_message.setText(msgs[0]);
            if (TextUtils.isEmpty(msgs[1])) {
                tv_title.setVisibility(View.GONE);
            } else {
                tv_title.setText(msgs[1]);
            }

            cancel.setText(msgs[2]);
            next_action.setText(msgs[3]);
            msg.setVisibility(View.GONE);
            choice_layout.setVisibility(View.VISIBLE);
        }

        window.setGravity(Gravity.CENTER);
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置点击外围解散
        CustomerDialog.this.setCanceledOnTouchOutside(false);
        ll_sure.setOnClickListener(onClickListener);
        ll_cancel.setOnClickListener(onClickListener);
    }

    /**
     * 提交成功
     *
     * @param onClickListener
     */
    public void showCommitSuccess(View.OnClickListener onClickListener) {
        setContentView(R.layout.dialog_commit_success);
        Button submit = (Button) findViewById(R.id.submit);
        window.setGravity(Gravity.CENTER);
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置点击外围解散
        CustomerDialog.this.setCanceledOnTouchOutside(false);
        submit.setOnClickListener(onClickListener);
    }

    /**
     * 删除消息
     *
     * @param onClickListener
     */
    public void messageDialog(View.OnClickListener onClickListener, String message, String confirm) {
        setContentView(R.layout.activity_refusedialog);
        ll_cancel = (LinearLayout) findViewById(R.id.ll_cancel);
        ll_sure = (LinearLayout) findViewById(R.id.ll_sure);
        TextView next = (TextView) findViewById(R.id.next_action);
        TextView tv_message = (TextView) findViewById(R.id.tv_message);
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("");
        next.setText(confirm);
        tv_message.setText(message);
        window.setGravity(Gravity.CENTER);
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置点击外围解散
        CustomerDialog.this.setCanceledOnTouchOutside(false);
        ll_sure.setOnClickListener(onClickListener);
        ll_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerDialog.this.dismiss();
            }
        });
    }

    /*分享邀请弹窗*/
    public void showShareInviteDialog(final int shareType) {
        showShareInviteDialog(shareType, "", false);
    }

    /*分享邀请弹窗*/
    public void showShareInviteDialog(final int shareType, final String shareCode, boolean needShare) {
        TextView invite_title, invite_code, small_title;
        RelativeLayout share_weixin_friendster_layout, share_weixin_friends_layout, share_qq_friends_layout, share_sim_layout;
        ImageView share_weixin_friendster_pic, share_weixin_friends_pic, share_qq_friends_pic, share_sim_pic;
        TextView share_weixin_friendster_value, share_weixin_friends_value, share_qq_friends_value, share_sim_value;
        Button button_cancel;
        setContentView(R.layout.share_invite_popupmenu);
        invite_title = (TextView) findViewById(R.id.invite_title);
        invite_code = (TextView) findViewById(R.id.invite_code);
        small_title = (TextView) findViewById(R.id.small_title);
        if (needShare && (shareType == AppConfig.SHARE_BONUS || shareType == AppConfig.SHARE_WITHDRAW)) {
            invite_code.setText(shareCode);
            invite_title.setText("我的邀请码 :");
            small_title.setText("马上分享给好友赚取金币");
        } else if (needShare && shareType == AppConfig.SHARE_AD) {
            invite_code.setText("");
            invite_title.setText("一起赚钱吧！");
            small_title.setText("");
        }
        share_weixin_friendster_layout = (RelativeLayout) findViewById(R.id.share_weixin_friendster_layout);
        share_weixin_friends_layout = (RelativeLayout) findViewById(R.id.share_weixin_friends_layout);
        share_qq_friends_layout = (RelativeLayout) findViewById(R.id.share_qq_friends_layout);
        share_sim_layout = (RelativeLayout) findViewById(R.id.share_sim_layout);
        share_weixin_friendster_pic = (ImageView) findViewById(R.id.share_weixin_friendster_pic);
        share_weixin_friends_pic = (ImageView) findViewById(R.id.share_weixin_friends_pic);
        share_qq_friends_pic = (ImageView) findViewById(R.id.share_qq_friends_pic);
        share_sim_pic = (ImageView) findViewById(R.id.share_sim_pic);
        share_weixin_friendster_value = (TextView) findViewById(R.id.share_weixin_friendster_value);
        share_weixin_friends_value = (TextView) findViewById(R.id.share_weixin_friends_value);
        share_qq_friends_value = (TextView) findViewById(R.id.share_qq_friends_value);
        share_sim_value = (TextView) findViewById(R.id.share_sim_value);
        button_cancel = (Button) findViewById(R.id.button_cancel);
        window.setGravity(Gravity.BOTTOM);
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置点击外围解散
        CustomerDialog.this.setCanceledOnTouchOutside(true);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
//                    case R.id.share_weixin_friendster_layout:
//                    case R.id.share_weixin_friendster_pic:
//                    case R.id.share_weixin_friendster_value:
//                        if (shareType == AppConfig.SHARE_AD) {
//                            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_sharecontent);
//                            ShareUtil.getInstance().shareToWechat(1, IApplication.globleResource.getString(R.string.share_ad_url), IApplication.globleResource.getString(R.string.share_ad_title), IApplication.globleResource.getString(R.string.share_ad_content), bitmap);
//                        } else if (shareType == AppConfig.SHARE_BONUS || shareType == AppConfig.SHARE_WITHDRAW) {
//                            //赚钱奖金
//                            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_sharecontent);
//                            ShareUtil.getInstance().shareToWechat(1, IApplication.globleResource.getString(R.string.share_url)
//                                    + "?act_code=0&chan_code=" + "W_FXZQ" + "&inviteCode="
//                                    + shareCode, "缺钱吗？快找" + CommonalityFieldUtils.getCurrAppName() + "！光速借钱！借钱不求人", "金额自由选择,期限由您做主，借款神器，舍我其谁!", bitmap);
//                        }
//                        break;
//                    case R.id.share_weixin_friends_layout:
//                    case R.id.share_weixin_friends_pic:
//                    case R.id.share_weixin_friends_value:
//                        if (shareType == AppConfig.SHARE_AD) {
//                            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_sharecontent);
//                            ShareUtil.getInstance().shareToWechat(0, IApplication.globleResource.getString(R.string.share_ad_url), IApplication.globleResource.getString(R.string.share_ad_title), IApplication.globleResource.getString(R.string.share_ad_content), bitmap);
//                        } else if (shareType == AppConfig.SHARE_BONUS || shareType == AppConfig.SHARE_WITHDRAW) {
//                            Bitmap friends_pic = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_sharecontent);
//                            ShareUtil.getInstance().shareToWechat(0, IApplication.globleResource.getString(R.string.share_url)
//                                    + "?act_code=0&chan_code=" + "W_FXZQ" + "&inviteCode="
//                                    + shareCode, "缺钱吗？快找" + CommonalityFieldUtils.getCurrAppName() + "！光速借钱！借钱不求人", "金额自由选择,期限由您做主，借款神器，舍我其谁!", friends_pic);
//                        }
//                        break;
//                    case R.id.share_qq_friends_layout:
//                    case R.id.share_qq_friends_pic:
//                    case R.id.share_qq_friends_value:
//                        if (shareType == AppConfig.SHARE_AD) {
//                            ShareUtil.getInstance().shareToQQ(mContext, IApplication.globleResource.getString(R.string.share_ad_url), IApplication.globleResource.getString(R.string.share_ad_title), IApplication.globleResource.getString(R.string.share_ad_content), "http://m.jijietong.com:8680/h5-static/icon_share.png");
//                        } else if (shareType == AppConfig.SHARE_BONUS || shareType == AppConfig.SHARE_WITHDRAW) {
//                            ShareUtil.getInstance().shareToQQ(mContext, IApplication.globleResource.getString(R.string.share_url)
//                                    + "?act_code=0&chan_code=" + "W_FXZQ" + "&inviteCode="
//                                    + shareCode, "缺钱吗？快找" + CommonalityFieldUtils.getCurrAppName() + "！光速借钱！借钱不求人", "金额自由选择,期限由您做主，借款神器，舍我其谁!", "http://m.jijietong.com:8680/h5-static/icon_share.png");
//                        }
//                        break;
//                    case R.id.share_sim_layout:
//                    case R.id.share_sim_pic:
//                    case R.id.share_sim_value:
//                        if (shareType == AppConfig.SHARE_AD) {
//                            ShareUtil.getInstance().sendSMS(mContext, IApplication.globleResource.getString(R.string.share_ad_title) + IApplication.globleResource.getString(R.string.share_ad_content) + "下载地址：" + IApplication.globleResource.getString(R.string.share_ad_url));
//                        } else if (shareType == AppConfig.SHARE_BONUS || shareType == AppConfig.SHARE_WITHDRAW) {
//                            ShareUtil.getInstance().sendSMS(mContext, "缺钱吗？快找" + CommonalityFieldUtils.getCurrAppName() + "！光速借钱、借钱不求人。金额自由选择，期限由您做主，借款神器，舍我其谁!" + IApplication.globleResource.getString(R.string.dx_share_url)
//                                    + "?act_code=0"
//                                    + "&inviteCode=" + shareCode);
//                        }
//                        break;
                }
                dismiss();
            }
        };
        share_weixin_friendster_layout.setOnClickListener(onClickListener);
        share_weixin_friends_layout.setOnClickListener(onClickListener);
        share_qq_friends_layout.setOnClickListener(onClickListener);
        share_sim_layout.setOnClickListener(onClickListener);
        share_weixin_friendster_pic.setOnClickListener(onClickListener);
        share_weixin_friends_pic.setOnClickListener(onClickListener);
        share_qq_friends_pic.setOnClickListener(onClickListener);
        share_sim_pic.setOnClickListener(onClickListener);
        share_weixin_friendster_value.setOnClickListener(onClickListener);
        share_weixin_friends_value.setOnClickListener(onClickListener);
        share_qq_friends_value.setOnClickListener(onClickListener);
        share_sim_value.setOnClickListener(onClickListener);
        button_cancel.setOnClickListener(onClickListener);

    }

    private String picUrl = "";
    Bitmap bitmap;

    /*首页二维码*/
    public void qrCode(final String url, String code) {
        setContentView(R.layout.home_code);
        ImageView iv_code, iv_dismisspop;
        TextView tv_savepic, tv_friend, tv_circle_friends, tv_code;
        iv_code = (ImageView) findViewById(R.id.iv_code);
        iv_dismisspop = (ImageView) findViewById(R.id.iv_dismisspop);
        tv_savepic = (TextView) findViewById(R.id.tv_savepic);
        tv_friend = (TextView) findViewById(R.id.tv_friend);
        tv_circle_friends = (TextView) findViewById(R.id.tv_circle_friends);
        tv_code = (TextView) findViewById(R.id.invitation_code);
        if (!LangUtil.isBlank(code)) {
            tv_code.setText("我的邀请码：" + code);
        } else {
            tv_code.setText("");
        }

        window.setGravity(Gravity.CENTER);
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置点击外围解散
        picUrl = url;
        CustomerDialog.this.setCanceledOnTouchOutside(true);
        CustomerDialog.this.setCancelable(false);
        GlideUtils.displayImageWithPre(mContext, picUrl, R.drawable.popup_loading, iv_code);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv_savepic:
                        cachedThreadPool.submit(new Runnable() {
                            @Override
                            public void run() {
                                if (bitmap == null || bitmap.isRecycled())
//                                    bitmap = ImageLoader.getInstance().loadImageSync(picUrl);
                                    saveImageToGallery(mContext, bitmap);
                            }
                        });
                        break;
                    case R.id.tv_friend:
                        cachedThreadPool.submit(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    if (bitmap == null || bitmap.isRecycled())
                                        bitmap = BitmapFactory.decodeStream(new URL(picUrl).openStream());
//                                    ShareUtil.getInstance().sendReq("", bitmap, false);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        break;
                    case R.id.tv_circle_friends:
                        cachedThreadPool.submit(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    if (bitmap == null || bitmap.isRecycled())
                                        bitmap = BitmapFactory.decodeStream(new URL(picUrl).openStream());
//                                    ShareUtil.getInstance().sendReq("", bitmap, true);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        break;
                    case R.id.iv_code:
                        break;
                    case R.id.iv_dismisspop:
                        dismiss();
                        break;
                }

            }
        };
        tv_savepic.setOnClickListener(onClickListener);
        iv_code.setOnClickListener(onClickListener);
        iv_dismisspop.setOnClickListener(onClickListener);
        tv_friend.setOnClickListener(onClickListener);
        tv_circle_friends.setOnClickListener(onClickListener);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (bitmap != null && !bitmap.isRecycled()) {
                    bitmap.recycle();
                    bitmap = null;
                }
            }
        });
    }

    private boolean flag = false;
    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    ToastUtils.showShortToast("保存成功");
                    break;
                case 2:
                    ToastUtils.showShortToast("已保存");
                    break;
            }
        }
    };

    public void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        if (flag) {
            handler.sendEmptyMessage(2);
            return;

        }
        File appDir = new File(Environment.getExternalStorageDirectory(), "jijietong");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".png";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            flag = bmp.compress(Bitmap.CompressFormat.PNG, 80, fos);
            fos.flush();
            fos.close();
            handler.sendEmptyMessage(1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getPath())));
    }

    public void bankDialog(View.OnClickListener onClickListener) {
        setContentView(R.layout.layout_bank_question);
        TextView tv_know;
        tv_know = (TextView) findViewById(R.id.tv_know);
        tv_know.setOnClickListener(onClickListener);
        window.setGravity(Gravity.CENTER);
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置点击外围解散
        CustomerDialog.this.setCanceledOnTouchOutside(false);
    }


    /**
     * 拍照提示
     */
    public void showPhotoTip(View.OnClickListener onClickListener) {
        LinearLayout tips;
        setContentView(R.layout.dialog_photo_tips);
        tips = (LinearLayout) findViewById(R.id.tips);
        window.setGravity(Gravity.FILL);
        // 设置显示动画
        window.setWindowAnimations(R.style.dialog_loan_usage);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置点击外围解散
        CustomerDialog.this.setCanceledOnTouchOutside(false);
        tips.setOnClickListener(onClickListener);
    }
}
