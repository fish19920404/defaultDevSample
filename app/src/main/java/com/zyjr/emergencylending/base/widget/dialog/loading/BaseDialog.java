package com.zyjr.emergencylending.base.widget.dialog.loading;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import com.zyjr.emergencylending.base.utils.third.ButterKnifeUtils;
import com.zyjr.emergencylending.pak.R;


public abstract class BaseDialog extends Dialog {

    protected Context mContext;

    protected View rootView;

    public BaseDialog(Context context) {
        super(context, R.style.BaseDialog);
        mContext = context;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCanceledOnTouchOutside(true);
        rootView = getLayoutInflater().inflate(getDailogLayout(), null);
        setContentView(rootView);
        ButterKnifeUtils.bind(this);
        initDailogView();
    }

    public abstract int getDailogLayout();

    public abstract void initDailogView();

}
