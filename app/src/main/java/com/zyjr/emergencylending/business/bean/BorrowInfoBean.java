package com.zyjr.emergencylending.business.bean;

import java.io.Serializable;

/**
 * 项目名称：急借通
 * 类描述：借款提交
 * 创建人：szx
 * 创建时间：2016/8/29 14:25
 * 修改人：szx
 * 修改时间：2016/8/29 14:25
 * 修改备注：
 */
public class BorrowInfoBean implements Serializable {
    private String product_id;
    private String borrow_limit;
    private String borrow_periods;
    private String management_cost;
    private String borrow_use_code;
    private String borrow_use;
    private String refund_way;
    private String store;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getBorrow_limit() {
        return borrow_limit;
    }

    public void setBorrow_limit(String borrow_limit) {
        this.borrow_limit = borrow_limit;
    }

    public String getBorrow_periods() {
        return borrow_periods;
    }

    public void setBorrow_periods(String borrow_periods) {
        this.borrow_periods = borrow_periods;
    }

    public String getManagement_cost() {
        return management_cost;
    }

    public void setManagement_cost(String management_cost) {
        this.management_cost = management_cost;
    }

    public String getBorrow_use_code() {
        return borrow_use_code;
    }

    public void setBorrow_use_code(String borrow_use_code) {
        this.borrow_use_code = borrow_use_code;
    }

    public String getBorrow_use() {
        return borrow_use;
    }

    public void setBorrow_use(String borrow_use) {
        this.borrow_use = borrow_use;
    }

    public String getRefund_way() {
        return refund_way;
    }

    public void setRefund_way(String refund_way) {
        this.refund_way = refund_way;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "BorrowInfoBean{" +
                "product_id='" + product_id + '\'' +
                ", borrow_limit='" + borrow_limit + '\'' +
                ", borrow_periods='" + borrow_periods + '\'' +
                ", management_cost='" + management_cost + '\'' +
                ", borrow_use_code='" + borrow_use_code + '\'' +
                ", borrow_use='" + borrow_use + '\'' +
                ", refund_way='" + refund_way + '\'' +
                ", store='" + store + '\'' +
                '}';
    }
}
