package com.zyjr.emergencylending.base.widget.dialog.loading;

import android.content.Context;
import android.view.View;

import com.mingle.widget.ImageLoadingView;
import com.zyjr.emergencylending.pak.R;


public class CustomerProgressDialog extends BaseDialog {

    private View loading;
    private ImageLoadingView imageLoadingView;

    public CustomerProgressDialog(Context context) {
        super(context);
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }


    @Override
    public int getDailogLayout() {
        return R.layout.widget_custom_progress_dialog_layout;
    }

    @Override
    public void initDailogView() {
        loading = rootView.findViewById(R.id.v_loading);

        loading.setVisibility(View.VISIBLE);
        setCanceledOnTouchOutside(false);

    }

    public View setTextInfo() {
        imageLoadingView = (ImageLoadingView) loading.findViewById(R.id.loadingView);
        return imageLoadingView;

    }
}
