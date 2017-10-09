package com.zyjr.emergencylending.business.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.zyjr.emergencylending.business.bean.BannerBean;
import com.zyjr.emergencylending.business.callBack.BannerEventClick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neil on 2017/9/30
 * 备注: 广告轮播图适配器
 */
public class BannerAdapter extends PagerAdapter {
    private List<BannerBean> bannerBeanList = new ArrayList<>();
    private List<ImageView> ivList = new ArrayList<>();
    private BannerEventClick bannerEventClick;

    public BannerAdapter(List<BannerBean> bannerBeanList, List<ImageView> ivList, BannerEventClick bannerEventClick) {
        this.bannerBeanList = bannerBeanList;
        this.ivList = ivList;
        this.bannerEventClick = bannerEventClick;
    }

    // 获取要滑动的控件的数量，在这里我们以滑动的广告栏为例，那么这里就应该是展示的广告图片的ImageView数量
    @Override
    public int getCount() {
        return bannerBeanList.size();
    }

    // 来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    // 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
    @Override
    public Object instantiateItem(View container, final int position) {
        ((ViewPager) container).addView(ivList.get(position));
        final int index = position;
        ivList.get(position).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (bannerEventClick != null) {
                    bannerEventClick.onClick(bannerBeanList.get(index).getAd_url());
                }
            }
        });
        return ivList.get(position);
    }

    // PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }

}
