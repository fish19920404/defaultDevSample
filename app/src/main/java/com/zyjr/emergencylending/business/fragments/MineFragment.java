package com.zyjr.emergencylending.business.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zyjr.emergencylending.base.common.BaseStructFragment;
import com.zyjr.emergencylending.pak.R;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * <pre>
 *     author : neil
 *     e-mail : 136687211@qq.com
 *     time   : 2017/09/29
 *     desc   : 我的
 *     version: 1.0
 * </pre>
 */

public class MineFragment extends BaseStructFragment {

    private Unbinder unbinder;

    public static final String INTERFACE_FRAGMENT_THREE = RepaymentFragment.class.getName() + "withParamOnly";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_test3, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_test3)
    public void onClick(View view) {
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("name", "张三");
        paramMap.put("age", "27");
        mFunctionManager.invokeFunctionWithParamAndResult(INTERFACE_FRAGMENT_THREE, paramMap, String.class);
    }

}
