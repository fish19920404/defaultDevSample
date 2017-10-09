package com.zyjr.emergencylending.business.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.zyjr.emergencylending.base.common.BaseStructFragment;
import com.zyjr.emergencylending.base.utils.DensityUtils;
import com.zyjr.emergencylending.base.utils.LogUtils;
import com.zyjr.emergencylending.base.utils.third.GlideUtils;
import com.zyjr.emergencylending.business.adapter.BannerAdapter;
import com.zyjr.emergencylending.business.bean.BannerBean;
import com.zyjr.emergencylending.business.callBack.BannerEventClick;
import com.zyjr.emergencylending.pak.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <pre>
 *     author : neil
 *     e-mail : 136687211@qq.com
 *     time   : 2017/09/29
 *     desc   : 主页
 *     Fragment中定义接口
 *     Activity 中实现接口
 *     将接口与Fragment和Activity绑定,暴露
 *     version: 1.0
 * </pre>
 */
public class HomeFragment extends BaseStructFragment {

    private static final String TAG = "HomeFragment";

    private Unbinder unbinder;

    @BindView(R.id.viewPager_banner)
    ViewPager viewPagerBanner;
    @BindView(R.id.layout_bottom_point)
    LinearLayout layoutBottomPoint;
    @BindView(R.id.home_refresh)
    PullToRefreshScrollView pullToRefreshScrollView;

    public static final String INTERFACE_FRAGMENT_ONE = HomeFragment.class.getName() + "NPNR"; // 定义接口key
    private String param1;
    private List<ImageView> imageViewList = new ArrayList<>();
    private List<BannerBean> bannerBeanList = new ArrayList<>();

    public static HomeFragment newInstance(String str) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("param1", str);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            param1 = getArguments().getString("param1");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_test1, container, false);
        unbinder = ButterKnife.bind(this, view);
        initBanner();

        pullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {

            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                pullToRefreshScrollView.onRefreshComplete();
            }
        });


        return view;
    }

    private void initBanner() {
        BannerBean b1 = new BannerBean();
        b1.setAd_pic("http://m.jijietong.com:8680/h5-static/homepic/2017_070_18_head2.png");
        bannerBeanList.add(b1);
        bannerBeanList.add(b1);
        bannerBeanList.add(b1);
        bannerBeanList.add(b1);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageViewList.clear();
        for (int i = 0; i < bannerBeanList.size(); i++) {
            if (getActivity() == null) {
                LogUtils.e(TAG, "getActivity is null");
            }
            if (getContext() != null) {
                ImageView imageView = new ImageView(getContext());
                imageView.setLayoutParams(params);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                imageView.setBackgroundResource(R.drawable.default_banner);
                GlideUtils.displayImageWithPre(getActivity(), bannerBeanList.get(i).getAd_pic(), R.drawable.default_banner, imageView);
                imageViewList.add(imageView);
            }
        }
        if (layoutBottomPoint != null) {
            layoutBottomPoint.removeAllViews();
            for (int i = 0; i < bannerBeanList.size(); i++) {
                if (i == 0) {
                    layoutBottomPoint.addView(setDaoHangText(R.drawable.dot_highlight));
                } else {
                    layoutBottomPoint.addView(setDaoHangText(R.drawable.dot_default));
                }
            }
        }
        initAdapter();
        myTask();
    }

    private View setDaoHangText(int id) {
        View text = new View(getActivity());
        LinearLayout.LayoutParams Viewpar = new LinearLayout.LayoutParams(DensityUtils.dp2px(getActivity(), 8), DensityUtils.dp2px(getActivity(), 8));
        Viewpar.setMargins(5, 5, 5, 5);
        text.setLayoutParams(Viewpar);
        text.setBackgroundResource(id);
        return text;
    }

    private int j;
    private int oldPosition;
    private boolean isResume = true;
    private Timer timer;
    private TimerTask timerTask;

    private void initAdapter() {
        viewPagerBanner.setAdapter(new BannerAdapter(bannerBeanList, imageViewList, new BannerEventClick() {
            @Override
            public void onClick(String url) {

            }
        }));

        viewPagerBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                j = arg0;
                layoutBottomPoint.getChildAt(oldPosition).setBackgroundResource(R.drawable.dot_default);
                layoutBottomPoint.getChildAt(arg0).setBackgroundResource(R.drawable.dot_highlight);
                oldPosition = arg0;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
    }

    private void myTask() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (isResume) {
                    handler.sendEmptyMessage(0);
                }
            }
        };
        timer.schedule(timerTask, 1000, 2000);
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (isResume) {
                        j++;
                        if (j < bannerBeanList.size()) {
                            viewPagerBanner.setCurrentItem(j);
                        } else {
                            j = 0;
                            viewPagerBanner.setCurrentItem(j);
                        }
                    }
                    break;

                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

//    @OnClick(R.id.btn_test1)
//    public void onClick(View view) {
////        mFunctionManager.invokeFunction(INTERFACE_FRAGMENT_ONE);
//
//    }





}
