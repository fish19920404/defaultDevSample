package com.zyjr.emergencylending.base.utils.third;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import com.zyjr.emergencylending.base.config.AppConfig;

import butterknife.ButterKnife;

/**
 * Created by l3 on 17/2/21.
 * <p>
 * 该类是辅助ButterKnife框架的初始化
 */
public class ButterKnifeUtils {

    public static void setDebug() {
        ButterKnife.setDebug(AppConfig.DEBUG);
    }

    public static void bind(Activity activity) {
        ButterKnife.bind(activity);
    }

    public static void bind(Object obj, View view) {
        ButterKnife.bind(obj, view);
    }

    public static void bind(Dialog dialog) {
        ButterKnife.bind(dialog);
    }

}
