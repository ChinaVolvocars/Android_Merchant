package com.view.pie.callback;

import android.view.View;
import android.view.ViewGroup;

import com.view.pie.BasePieLegendsView;
import com.view.pie.data.IPieInfo;

public interface OnPieLegendBindListener<V extends BasePieLegendsView> {

    V onCreateLegendView(int position, IPieInfo info);

    /**
     * 添加图例
     *
     * @param parent
     * @param view
     * @return if return true,pieview will not addView by using default method.{@link ViewGroup#addView(View)}
     */
    boolean onAddView(ViewGroup parent, V view);

}
