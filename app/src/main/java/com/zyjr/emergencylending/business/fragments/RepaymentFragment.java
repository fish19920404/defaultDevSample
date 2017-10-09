package com.zyjr.emergencylending.business.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zyjr.emergencylending.base.common.BaseStructFragment;
import com.zyjr.emergencylending.base.utils.ToastUtils;
import com.zyjr.emergencylending.pak.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * <pre>
 *     author : neil
 *     e-mail : 136687211@qq.com
 *     time   : 2017/09/29
 *     desc   : 还款
 *     version: 1.0
 * </pre>
 */

public class RepaymentFragment extends BaseStructFragment {

    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_test2, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public static final String INTERFACE_FRAGMENT_TWO = RepaymentFragment.class.getName() + "withResultOnly";

    @OnClick(R.id.btn_test2)
    public void onClick(View view) {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("name", "zhangsan");
        String responseResult = mFunctionManager.invokeFunctionWithParamAndResult(INTERFACE_FRAGMENT_TWO, paramMap, String.class);
        ToastUtils.showLongToast("received:" + responseResult);
    }

}
