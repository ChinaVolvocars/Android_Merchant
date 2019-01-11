package com.common.adapter.helper;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;


/**
 * IRecyclerView 帮助类  简单的设置布局
 */
public class IRecyclerViewHelper {
    //      设置分隔线
//      mRecy.addItemDecoration(new RecyclerViewItemDecoration(RecyclerViewItemDecoration.MODE_HORIZONTAL,"#FC423C",5,20,10));
//      或
//      mRecy.addItemDecoration(new SpacesItemDecoration(1));
    static IRecyclerViewHelper ourInstance;

    public IRecyclerViewHelper() {
    }

    public static IRecyclerViewHelper init() {
        if (ourInstance == null)
            ourInstance = new IRecyclerViewHelper();
        return ourInstance;
    }

    public void setRecycleLineLayout(Context context, int orientation, RecyclerView mRecy) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        mRecy.setLayoutManager(layoutManager);
    }

    public void setRecycleGridLayout(Context context, RecyclerView mRecy, int num) {
        GridLayoutManager layoutManager = new GridLayoutManager(context, num);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecy.setLayoutManager(layoutManager);
    }

    public void setStaggeredGridLayout(RecyclerView mRecy, int num, int orientation) {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(num, orientation);
        mRecy.setLayoutManager(layoutManager);
        mRecy.setItemAnimator(new DefaultItemAnimator());
    }
}
