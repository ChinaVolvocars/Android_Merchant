package com.live.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/9/28.
 */
public class RVPagerAdapter extends DefaultPagerAdapter<RecyclerView> {
    private List<RecyclerView> datas;

    public RVPagerAdapter(List<RecyclerView> datas) {
        super(datas);
        this.datas = datas;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(datas.get(position));
        return datas.get(position);
    }
}
