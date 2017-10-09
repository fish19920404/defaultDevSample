package com.zyjr.emergencylending.base.common;

/**
 * Created by wangyaping 
 */
public interface IBaseActivity {
    /**
     * 设置xml文件
     */
    int setContent();
    
    int setStatusColor();

    /**
     * 初始化子view
     */
    void init();

    /**
     * 初始化数据设置
     */
    void setData();

    /**
     * 设置view监听器
     */
    void setListener();

    /**
     * onResume
     */
    void resume();

    /**
     * onDestroy
     */
    void destroy();
}
