package com.zyjr.emergencylending.business.struct.basic;

import com.zyjr.emergencylending.base.utils.LangUtil;

import java.util.HashMap;


/**
 * <pre>
 *     author : neil
 *     e-mail : 136687211@qq.com
 *     time   : 2017/09/21
 *     desc   : 管理所有的接口(单例模式)
 *              通过容器保存需要实现的接口
 *     version: 1.0
 * </pre>
 */

public class FunctionsManager {

    // 通过结合容器保存 key函数名称,value:接口实体
    private HashMap<String, FunctionNoParamNoResult> mFunctionNoParamNoResult;
    private HashMap<String, FunctionWithParamOnly> mFunctionWithParamOnly;
    private HashMap<String, FunctionWithResultOnly> mFunctionWithResultOnly;
    private HashMap<String, FunctionWithParamAndResult> mFunctionWithParamAndResult;

    private FunctionsManager() {
        mFunctionNoParamNoResult = new HashMap<>();
        mFunctionWithParamOnly = new HashMap<>();
        mFunctionWithResultOnly = new HashMap<>();
        mFunctionWithParamAndResult = new HashMap<>();
    }

    private static class FunctionsManagerHolder {
        public static final FunctionsManager INSTANCE = new FunctionsManager();
    }

    public static FunctionsManager getInstance() {
        return FunctionsManagerHolder.INSTANCE;
    }

    // 需要往map里面添加 接口 无参无返回值
    public FunctionsManager addFunction(FunctionNoParamNoResult function) {
        mFunctionNoParamNoResult.put(function.mFunctionName, function);
        return this; // 采用链式调用
    }

    /**
     * 无参无返回 接口方法调用
     * @param functionName
     */
    public void invokeFunction(String functionName) {
        if (LangUtil.isBlank(functionName)) {
            return;
        }
        if (mFunctionNoParamNoResult != null) {
            FunctionNoParamNoResult f = mFunctionNoParamNoResult.get(functionName);
            if (!LangUtil.isBlank(f)) {
                f.function();
            } else {
                try {
                    throw new FunctionException("has no this Functon:" + functionName);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 添加有返回值 接口
    public FunctionsManager addFunctionWithResultOnly(FunctionWithResultOnly function) {
        mFunctionWithResultOnly.put(function.mFunctionName, function);
        return this;
    }

    /**
     * 调用有返回值 接口
     *
     * @param functionName
     * @param c
     * @param <Result>     返回值类型
     * @return
     */
    public <Result> Result invokeFunctionWithResultOnly(String functionName, Class<Result> c) {
        if (LangUtil.isBlank(functionName)) {
            return null;
        }
        if (mFunctionWithResultOnly != null) {
            FunctionWithResultOnly f = mFunctionWithResultOnly.get(functionName);
            if (f != null) {
                if (c != null) {
                    return c.cast(f.function());
                } else {
                    return (Result) f.function();
                }
            } else {
                try {
                    throw new FunctionException("has no this Functon:" + functionName);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    // 添加有参,无返回值 接口
    public FunctionsManager addFunctionWithParamOnly(FunctionWithParamOnly function) {
        mFunctionWithParamOnly.put(function.mFunctionName, function);
        return this;
    }

    public <Param> void invokeFunctionWithParamOnly(String functionName, Param data) {
        if (LangUtil.isBlank(functionName)) {
            return;
        }
        if (mFunctionWithParamOnly != null) {
            FunctionWithParamOnly f = mFunctionWithParamOnly.get(functionName);
            if (f != null) {
                f.function(data);
            } else {
                try {
                    throw new FunctionException("has no this Functon:" + functionName);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    // 添加有参有返回值 接口
    public FunctionsManager addFunctionWithParamAndResult(FunctionWithParamAndResult function) {
        mFunctionWithParamAndResult.put(function.mFunctionName, function);
        return this;
    }

    /**
     * @param functionName
     * @param data
     * @param c
     * @param <Param>
     * @param <Result>
     * @return
     */
    public <Param, Result> Result invokeFunctionWithParamAndResult(String functionName, Param data, Class<Result> c) {
        if (LangUtil.isBlank(functionName)) {
            return null;
        }
        if (mFunctionWithParamAndResult != null) {
            FunctionWithParamAndResult f = mFunctionWithParamAndResult.get(functionName);
            if (f != null) {
                if (c != null) {
                    return c.cast(f.function(data));
                }
                return (Result) f.function(data);
            } else {
                try {
                    throw new FunctionException("has no this Functon:" + functionName);
                } catch (FunctionException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
