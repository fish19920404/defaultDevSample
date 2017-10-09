package com.zyjr.emergencylending.business.mvp.presenter;

import com.zyjr.emergencylending.business.mvp.view.BaseView;

/**
 * Created by neil on 2017/9/29
 * 备注:
 */
public class BasePresenter<V extends BaseView> {

    private V view;

    public V getMvpView() {
        return view;
    }

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }


}
