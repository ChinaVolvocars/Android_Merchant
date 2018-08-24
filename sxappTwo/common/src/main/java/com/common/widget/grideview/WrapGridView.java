package com.common.widget.grideview;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.GridView;

/*******************************
* 高度自适应gradview
* created at 2017/3/16 上午 10:33
********************************/
public class WrapGridView extends GridView
{
    public WrapGridView(android.content.Context context, android.util.AttributeSet attrs)
    {
        super(context, attrs);
        //GridView去除Item触摸黄色背景
        setSelector(new ColorDrawable(Color.TRANSPARENT));
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
