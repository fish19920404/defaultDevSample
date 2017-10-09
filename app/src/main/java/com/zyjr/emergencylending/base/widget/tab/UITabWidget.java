package com.zyjr.emergencylending.base.widget.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zyjr.emergencylending.pak.R;


/**
 * Created by wangyaping
 */
public class UITabWidget extends RadioGroup implements OnCheckedChangeListener {
    public static final String WIDGET_WHAT_HOME = "home"; // 首页
    public static final String WIDGET_WHAT_REPAYMENT = "repayment"; // 还款
    public static final String WIDGET_WHAT_MINE = "mine"; // 我的
    private LayoutInflater mInflater;
    private OnItemChangedListener mOnItemChangedListener;

    public UITabWidget(Context context) {
        this(context, null);
    }

    public UITabWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    private void initialize(Context context) {
        setOrientation(HORIZONTAL);
        mInflater = LayoutInflater.from(context);
    }

    public void addTabView(int resId, int textId, int position) {
        RadioButton button = (RadioButton) mInflater.inflate(R.layout.maintab_widget_layout, this, false);
        button.setText(textId);
        button.setTag(position);
        button.setCompoundDrawablesWithIntrinsicBounds(0, resId, 0, 0);
        button.setOnCheckedChangeListener(this);
        addView(button);
    }

    /**
     * 设置选中选项
     *
     * @param position 索引
     * @param checked  是否选中
     */
    public void setChecked(int position, boolean checked) {
        RadioButton rdoButton = (RadioButton) getChildAt(position);
        rdoButton.setChecked(checked);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked && mOnItemChangedListener != null) {
            String tag = buttonView.getTag().toString();
            int position = Integer.parseInt(tag);
            mOnItemChangedListener.onChanged(position);
        }
    }

    /**
     * 设置监听事件
     */
    public void setOnItemChangedListener(
            OnItemChangedListener onItemChangedListener) {
        this.mOnItemChangedListener = onItemChangedListener;
    }

    public interface OnItemChangedListener {

        void onChanged(int position);
    }
}
