package com.zyjr.emergencylending.business.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：jijietong1.08
 * 类描述：借款周期选择
 * 创建人：szx
 * 创建时间：2017/1/12 14:59
 * 修改人：szx
 * 修改时间：2017/1/12 14:59
 * 修改备注：
 */
public class PeriodContent {
    private static List<PeriodBean> dataSource = new ArrayList<PeriodBean>();
    private static PeriodContent instance;

    public static PeriodContent getInstance() {
        if (instance == null) {
            instance = new PeriodContent();
        }
        return instance;
    }

    PeriodContent() {
        initPeriodtotal();
    }

    public List<PeriodBean> getPeriodtotal() {
        return dataSource;
    }

    private void initPeriodtotal() {
        dataSource.add(addPeriod(0, "5"));
        dataSource.add(addPeriod(1, "10"));
        dataSource.add(addPeriod(2, "15"));
    }

    public PeriodBean getPeriod(int id) {
        for (PeriodBean bean : dataSource) {
            if (bean.getId() == id) {
                return bean;
            }
        }
        return new PeriodBean();
    }


    private PeriodBean addPeriod(int id, String period) {
        PeriodBean bean = new PeriodBean();
        bean.setId(id);
        bean.setPeriod(period);
        return bean;
    }
}
