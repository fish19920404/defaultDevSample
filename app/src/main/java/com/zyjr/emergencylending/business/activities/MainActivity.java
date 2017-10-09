package com.zyjr.emergencylending.business.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zyjr.emergencylending.base.common.BaseActivity;
import com.zyjr.emergencylending.base.common.BaseStructFragment;
import com.zyjr.emergencylending.base.utils.LangUtil;
import com.zyjr.emergencylending.base.utils.SharedPreferencesUtil;
import com.zyjr.emergencylending.base.utils.ToastUtils;
import com.zyjr.emergencylending.base.widget.tab.UITabWidget;
import com.zyjr.emergencylending.business.adapter.TabsAdapter;
import com.zyjr.emergencylending.business.fragments.HomeFragment;
import com.zyjr.emergencylending.business.fragments.MineFragment;
import com.zyjr.emergencylending.business.fragments.RepaymentFragment;
import com.zyjr.emergencylending.business.struct.basic.FunctionNoParamNoResult;
import com.zyjr.emergencylending.business.struct.basic.FunctionWithParamAndResult;
import com.zyjr.emergencylending.business.struct.basic.FunctionsManager;
import com.zyjr.emergencylending.pak.R;

import java.lang.ref.WeakReference;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by neil on 2017/9/29
 * 备注:
 */
public class MainActivity extends FragmentActivity implements UITabWidget.OnItemChangedListener {

    @BindView(R.id.maintab_view1)
    UITabWidget mTabWidget;

    private TabsAdapter mTabsAdapter;
    private FragmentTransaction transaction;
    private FragmentManager manager;
    private ImageView ivStatus;
    private View mContentView;
    protected WeakReference<Activity> content = null;
    private int tabPosition;
    private int curPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        ivStatus = new ImageView(this);
        ivStatus.setImageResource(R.color.circle_color);
        LinearLayout.LayoutParams statusParams;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            statusParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
        } else {
            statusParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
        }
        layout.addView(ivStatus, statusParams);
        mContentView = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        LinearLayout.LayoutParams contentParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layout.addView(mContentView, contentParams);
        setContentView(layout);
        content = new WeakReference<Activity>(this);
        mTabWidget = (UITabWidget) findViewById(R.id.maintab_view1);
        mTabWidget.setOnItemChangedListener(this);
        manager = getSupportFragmentManager();
        mTabsAdapter = new TabsAdapter(this);
        createTabView(); // 加载Tab界面
        if (savedInstanceState == null) { // 默认选中第一项Tab
            mTabWidget.setChecked(0, true);
        } else {
            tabPosition = savedInstanceState.getInt("tabPosition", 0);
            mTabWidget.setChecked(tabPosition, true);
        }

    }

    @Override
    public void onChanged(int position) {
        switchFrament(position);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tabPosition", curPosition);
    }

    /*
    * 动态初始化内容界面
    */
    private void createTabView() {
        int position = 0;
        addTabSpec(UITabWidget.WIDGET_WHAT_HOME, position++);
        addTabSpec(UITabWidget.WIDGET_WHAT_REPAYMENT, position++);
        addTabSpec(UITabWidget.WIDGET_WHAT_MINE, position++);
        mTabsAdapter.notifyDataSetChanged();
    }

    /*
    * 添加选项卡界面
    */
    private void addTabSpec(String key, int position) {
        int textId = 0, resId = 0;
        if (key.equals(UITabWidget.WIDGET_WHAT_HOME)) { // 首页
            resId = R.drawable.btn_home_selector;
            textId = R.string.tab_home_text;
            mTabsAdapter.addTab(HomeFragment.class);
        } else if (key.equals(UITabWidget.WIDGET_WHAT_REPAYMENT)) {   //还款
            resId = R.drawable.btn_repayment;
            textId = R.string.tab_repayment_text;
            mTabsAdapter.addTab(RepaymentFragment.class);
        } else if (key.equals(UITabWidget.WIDGET_WHAT_MINE)) { // 我的
            resId = R.drawable.btn_mine_selector;
            textId = R.string.tab_mine_text;
            mTabsAdapter.addTab(MineFragment.class);
        }
        mTabWidget.addTabView(resId, textId, position);
    }

    public void switchFrament(int position) {
        transaction = manager.beginTransaction();
        transaction.replace(R.id.main_pager, mTabsAdapter.getItem(position), mTabsAdapter.getItem(position).getClass().getName());
        transaction.commitAllowingStateLoss();
    }

    // 创建一个方法 用于初始化所有需要使用的接口
    public void setFunctionsForFragment(String tag) {
        FragmentManager fm = getSupportFragmentManager();
        // 获取基类baseStructFrament
        BaseStructFragment baseStructFragment = (BaseStructFragment) fm.findFragmentByTag(tag);
        FunctionsManager fManager = FunctionsManager.getInstance();
        baseStructFragment.setFunctionManager(fManager.addFunctionWithParamAndResult(new FunctionWithParamAndResult<Map<String, String>,String>(MineFragment.INTERFACE_FRAGMENT_THREE) {
            @Override
            public String function(Map<String, String> o) {
                Map<String, String> paramMap = null;
                if (!LangUtil.isBlank(o)) {
                    paramMap = o;
                }
                ToastUtils.showShortToast("成功-----------" + paramMap.toString());
                return paramMap.toString();
            }
        }));
    }

}
