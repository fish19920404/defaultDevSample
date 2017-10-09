package com.zyjr.emergencylending.business.bean;

import java.io.Serializable;

public class UsageBean implements Serializable {
    private int id = -1;
    private String usageCode;
    private String usageName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsageCode() {
        return usageCode;
    }

    public void setUsageCode(String usageCode) {
        this.usageCode = usageCode;
    }

    public String getUsageName() {
        return usageName;
    }

    public void setUsageName(String usageName) {
        this.usageName = usageName;
    }


    @Override
    public String toString() {
        return "UsageBean{" +
                "id=" + id +
                ", usageCode='" + usageCode + '\'' +
                ", usageName='" + usageName + '\'' +
                '}';
    }
}
