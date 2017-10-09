package com.zyjr.emergencylending.base.common;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zyjr.emergencylending.business.mvp.presenter.BasePresenter;
import com.zyjr.emergencylending.business.mvp.view.BaseView;

/**
 * <pre>
 *     author : neil
 *     e-mail : 136687211@qq.com
 *     time   : 2017/09/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public abstract class MvpBaseActivity<V extends BaseView, P extends BasePresenter> extends Activity {
    private P presenter;
    private V view;

    public P getPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.presenter == null) {
            // 创建P层
            this.presenter = createPresenter();
        }
        if (this.view == null) {
            // 创建V层
            this.view = createView();
        }
        // 判断是否为空
        if (this.presenter == null) {
            throw new NullPointerException("Presenter不能为空");
        }
        if (this.view == null) {
            throw new NullPointerException("View不能为空");
        }
        // 视图的绑定
        this.presenter.attachView(this.view);
    }

    public abstract P createPresenter();

    public abstract V createView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.presenter.detachView();
    }

}
