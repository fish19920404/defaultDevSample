package com.zyjr.emergencylending.business.struct.basic;


/**
 * <pre>
 *     author : neil
 *     e-mail : 136687211@qq.com
 *     time   : 2017/09/21
 *     desc   : 具体实现 交给真正实现的类 (只有参数,无返回值)
 *     version: 1.0
 * </pre>
 */

public abstract class FunctionWithParamOnly<Param> extends Function {

    public FunctionWithParamOnly(String mFunctionName) {
        super(mFunctionName);
    }

    public abstract void function(Param param);
}
