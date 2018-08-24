package com.live.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Viewpager的自定义的适配器
 * @author wangdh
 *
 * @param <T>
 */
public abstract class DefaultPagerAdapter<T> extends PagerAdapter {
    /**
     * 适配器的数据源
     */
    protected List<T> datas;

    public DefaultPagerAdapter(List<T> datas){
        this.datas = datas;
    }
    
    @Override
    public int getCount() {
        return datas.size();
    }
    
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
    
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public abstract Object instantiateItem(ViewGroup container, int position) ;
    
    
}
