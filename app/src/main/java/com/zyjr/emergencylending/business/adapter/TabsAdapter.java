package com.zyjr.emergencylending.business.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 主页面
 * Created by wangyaping
 */
public class TabsAdapter extends FragmentStatePagerAdapter {
    private final Context mContext;
    private final ArrayList<String> mTabs = new ArrayList<String>();
    private final SparseArray<Fragment> mFragments = new SparseArray<Fragment>();

    public TabsAdapter(FragmentActivity activity) {
        super(activity.getSupportFragmentManager());
        mContext = activity;
    }

    public void addTab(Class<?> clazz) {
        mTabs.add(clazz.getName());
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = Fragment.instantiate(mContext, mTabs.get(position));
        mFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    public Fragment getFragment(int position) {
        return mFragments.get(position);
    }

}
