package com.zyjr.emergencylending.base;

import com.zyjr.emergencylending.base.bean.RequestBean;

/**
 * Created by neil on 2017/10/9
 * 备注:
 */

public class test {

    public static void main(String[] args){
        RequestBean re = new RequestBean();
        re.setApp_no("1111111");
        System.out.println(re.hashCode());
        RequestBean requestBean11 = updateBean(re);
        System.out.println(requestBean11.hashCode());
    }



    private static RequestBean updateBean(RequestBean requestBean){
        requestBean.setApp_no("222222222");
        return requestBean;
    }
}
