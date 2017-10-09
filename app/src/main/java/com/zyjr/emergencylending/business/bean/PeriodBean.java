package com.zyjr.emergencylending.business.bean;

import java.io.Serializable;

public class PeriodBean implements Serializable {

    private int id;
    private String period;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "PeriodBean{" +
                "id=" + id +
                ", period='" + period + '\'' +
                '}';
    }
}
