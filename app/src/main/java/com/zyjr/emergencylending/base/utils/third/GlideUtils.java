package com.zyjr.emergencylending.base.utils.third;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zyjr.emergencylending.pak.R;


/**
 * Created by neil on 2017/9/30
 * Glide 工具类
 */
public class GlideUtils {

    public static void displayImage(Context context, String uri, ImageView imageView) {
        displayImage(context, uri, -1, imageView, -1);
    }

    public static void displayImage(Context context, String uri, int preResourceId, ImageView imageView, int resId) {
        displayImage(context, uri, preResourceId, imageView, resId, false);
    }

    public static void displayImageWithPre(Context context, String uri, int preResourceId, ImageView imageView) {
        displayImage(context, uri, preResourceId, imageView, -1);
    }


    public static void displayImage(Context context, String uri, int preResourceId, ImageView imageView, int resId, boolean needCircle) {
        if (TextUtils.isEmpty(uri)) {
            if (resId != -1) {
                if (needCircle) {
//                    Glide.with(context).load(resId).transform(new GlideCircleBitmap(context)).into(imageView);
                } else {
                    Glide.with(context).load(resId).into(imageView);
                }
            }
        } else {
            // 需要圆形切角图
            if (needCircle) {
                if (resId == -1) {
//                    Glide.with(context).load(uri).transform(new GlideCircleBitmap(context)).into(imageView);
                } else {
//                    Glide.with(context).load(uri).placeholder(resId).transform(new GlideCircleBitmap(context)).into(imageView);
                }
            } else {
                if (resId == -1) {
                    if (preResourceId != -1) {
                        Glide.with(context).load(uri).placeholder(preResourceId).into(imageView);
                    } else {
                        Glide.with(context).load(uri).into(imageView);
                    }
                } else {
                    Glide.with(context).load(uri).placeholder(resId).into(imageView);
                }
            }
        }
    }


    public static void clearMemoryCache(final Context context) {
        Glide.get(context).clearMemory();

    }

    public static void clearDiskCache(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(context).clearDiskCache();
            }
        }).start();
    }


    public static void clear(final Context context) {
        clearMemoryCache(context);
        clearDiskCache(context);
    }

}
