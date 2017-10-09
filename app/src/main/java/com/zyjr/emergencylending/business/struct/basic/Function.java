package com.zyjr.emergencylending.business.struct.basic;

/**
 * <pre>
 *     author : neil
 *     e-mail : 136687211@qq.com
 *     time   : 2017/09/21
 *     desc   : 功能名称 其他类继承时 必须重载方法
 *     version: 1.0
 * </pre>
 */
public abstract class Function {

    public String mFunctionName;

    public Function(String mFunctionName) {
        this.mFunctionName = mFunctionName;
    }
}
