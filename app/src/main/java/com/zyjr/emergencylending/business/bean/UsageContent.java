package com.zyjr.emergencylending.business.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：jijietong1.08
 * 类描述：
 * 创建人：szx
 * 创建时间：2017/1/12 14:59
 * 修改人：szx
 * 修改时间：2017/1/12 14:59
 * 修改备注：
 */
public class UsageContent {
    private static List<UsageBean> dataSource = new ArrayList<UsageBean>();
    private static UsageContent instance;

    public static UsageContent getInstance() {
        if (instance == null) {
            instance = new UsageContent();
        }
        return instance;
    }

    UsageContent() {
        initUsagetotal();
    }

    public List<UsageBean> getUsagetotal() {
        return dataSource;
    }

    public UsageBean getUsage(int id) {
        for (UsageBean bean : dataSource) {
            if (bean.getId() == id) {
                return bean;
            }
        }
        return new UsageBean();
    }
    public String getUsageNameByCode(String code) {
        for (UsageBean bean : dataSource) {
            if (bean.getUsageCode().equals(code)) {
                return bean.getUsageName();
            }
        }
        return "";
    }
    private void initUsagetotal() {
        dataSource.add(addUsage(0, "513", "资金周转"));
        dataSource.add(addUsage(1, "521", "购物"));
        dataSource.add(addUsage(2, "504", "教育"));
        dataSource.add(addUsage(3, "507", "装修"));
        dataSource.add(addUsage(4, "523", "旅游"));
        dataSource.add(addUsage(5, "524", "医疗"));
        dataSource.add(addUsage(6, "510", "扩大经营生产"));
        dataSource.add(addUsage(7, "525", "其他"));






    }

    private UsageBean addUsage(int id, String code, String name) {
        UsageBean bean = new UsageBean();
        bean.setId(id);
        bean.setUsageCode(code);
        bean.setUsageName(name);
        return bean;
    }
}
