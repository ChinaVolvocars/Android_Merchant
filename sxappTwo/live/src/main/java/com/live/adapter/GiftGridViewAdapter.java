package com.live.adapter;

import android.view.ViewGroup;
import android.widget.GridView;

import java.util.List;

/**
 * Created by Administrator on 2016/9/16.
 */
public class GiftGridViewAdapter extends DefaultPagerAdapter<GridView> {
    public GiftGridViewAdapter(List<GridView> datas) {
        super(datas);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        GridView gridView = datas.get(position);
        container.addView(gridView);
        return gridView;
    }
}
