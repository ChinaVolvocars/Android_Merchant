package com.widget.pager;

import android.view.View;
import android.view.ViewGroup;

import com.live.adapter.DefaultPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/9/25.
 * 直播播放界面 上层控制界面和视频播放界面
 */
public class MyPagerAdapter extends DefaultPagerAdapter {
    private List list;
    public MyPagerAdapter(List list) {
        super(list);
        this.list=list;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BasePager currentPager = (BasePager) list.get(position);
        View view = currentPager.initView();
        currentPager.initData();
        container.addView(view);
        return view;
    }
}
