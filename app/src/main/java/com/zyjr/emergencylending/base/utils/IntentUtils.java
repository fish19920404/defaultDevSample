package com.zyjr.emergencylending.base.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.zyjr.emergencylending.pak.R;

/**
 * Created by neil on 2017/9/29
 * 备注:
 */
public class IntentUtils {

    public static void goToActivity(Context context, Class c) {
        Intent intent = new Intent(context, c);
        context.startActivity(intent);
    }

    public static void goToActivityForResult(Activity context, Class c) {
        Intent intent = new Intent(context, c);
        context.startActivityForResult(intent, 0);
    }

    public static void goToActivityForResult(Activity context, Class c, int request) {
        Intent intent = new Intent(context, c);
        context.startActivityForResult(intent, request);
    }

    public static void goToActivity(Context context, Class c, Intent intent) {
        intent.setClass(context, c);
        context.startActivity(intent);
    }

    public static void goToActivityForResult(Activity context, Class c, Intent intent) {
        intent.setClass(context, c);
        context.startActivityForResult(intent, 0);
    }

    public static void goToActivityForResult(Activity context, Class c, Intent intent, int request) {
        intent.setClass(context, c);
        context.startActivityForResult(intent, request);
    }

    public static void goToActivity(Activity context, Class c) {
        Intent intent = new Intent(context, c);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

}
