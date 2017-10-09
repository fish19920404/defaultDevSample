package com.zyjr.emergencylending.base.common;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.zyjr.emergencylending.business.activities.MainActivity;
import com.zyjr.emergencylending.business.struct.basic.FunctionsManager;


/**
 * <pre>
 *     author : neil
 *     e-mail : 136687211@qq.com
 *     time   : 2017/09/21
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class BaseStructFragment extends Fragment {

    // 定义一个接口管理类，持有引用
    protected FunctionsManager mFunctionManager;

    private MainActivity mBaseActivity;

    public void setFunctionManager(FunctionsManager mFunctionManager) {
        this.mFunctionManager = mFunctionManager;
    }

    // 进行绑定,是一个逆向的过程
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mBaseActivity = (MainActivity) context;
            mBaseActivity.setFunctionsForFragment(getTag());
        }
    }
}
