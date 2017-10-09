package com.zyjr.emergencylending.base.utils;

import java.text.DecimalFormat;

/**
 * Created by wangyaping
 * 计算的类
 */
public class Arithmetic {
    public static double expenseByWeek(int money, int week) {
        DecimalFormat df = new DecimalFormat("#####0.0");
        Double amount = Double.valueOf(df.format(money * week * 2 * 0.003));
        return amount;
    }

    public static double expenseByDayDouble(int money, int day) {
        DecimalFormat df = new DecimalFormat("#####0.00");
        Double amount = Double.valueOf(df.format(money * day * 0.003));
        return amount;
    }

    public static Integer expenseByDayInt(int money, int day) {
        DecimalFormat df = new DecimalFormat("#####0");
        Integer amount = Integer.valueOf(df.format(money * day * 0.003));
        return amount;
    }

    //减掉费用
    public static double subtraction(int money, double factorage) {
        DecimalFormat df = new DecimalFormat("#####0.00");
        Double amount = Double.valueOf(df.format(money - factorage));
        return amount;
    }

    //本金
    public static double expenseByPrincipal(int money, int week) {
        DecimalFormat df = new DecimalFormat("#####0.0");
        Double amount = Double.valueOf(df.format(Double.valueOf(money) / week));
        return amount;
    }

    //管理费
    public static double expenseByManager(int money, int week) {
        DecimalFormat df = new DecimalFormat("#####0.0");
        Double amount = Double.valueOf(df.format(money * 7 * 0.003));
        return amount;
    }

    //金额加逗号
    public static String addComma(String str) {
        String reverseStr = new StringBuilder(str).reverse().toString();
        String strTemp = "";
        for (int i = 0; i < reverseStr.length(); i++) {
            if (i * 3 + 3 > reverseStr.length()) {
                strTemp += reverseStr.substring(i * 3, reverseStr.length());
                break;
            }
            strTemp += reverseStr.substring(i * 3, i * 3 + 3) + ",";
        }
        if (strTemp.endsWith(",")) {
            strTemp = strTemp.substring(0, strTemp.length() - 1);
        }
        String resultStr = new StringBuilder(strTemp).reverse().toString();
        return resultStr;
    }


    public static int progressToMoney(int progress) {
        int money = 0;
        if (progress < 0) {
            money = 2000;
        } else if (progress >= 0 && progress <= 30) {
            money = 2000 + 100 * progress;
        } else if (progress > 30 && progress <= 55) {
            money = 5000 + 1000 * (progress - 30);
        } else {
            money = 30000;
        }
        return money;
//        return (2000 + 200 * progress);
    }

    public static int progressToWeek(int progress) {
        int week = 0;
        if (progress >= 0 && progress < 10) {
            week = 5;
        } else if (progress >= 10 && progress < 20) {
            week = 10;
        } else if (progress >= 20 && progress < 30) {
            week = 15;
        } else if (progress >= 30 && progress < 40) {
            week = 20;
        } else if (progress >= 40 && progress < 50) {
            week = 30;
        } else if (progress >= 50) {
            week = 50;
        }
        return week;
    }
}
