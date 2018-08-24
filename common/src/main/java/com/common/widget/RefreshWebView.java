package com.common.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * Created by wl on 2016/12/29.
 */
//        refreshLayout = (RefreshWebView) this.findViewById(R.id.refresh_layout);
//        refreshLayout.setViewGroup(webView);//设置监听滚动的子view
//        refreshLayout.setOnRefreshListener(this);

public class RefreshWebView extends SwipeRefreshLayout {

    private ViewGroup viewGroup;

    public RefreshWebView(Context context) {
        super(context);
    }

    public RefreshWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewGroup getViewGroup() {
        return viewGroup;
    }

    public void setViewGroup(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        if (null != viewGroup) {
            if (viewGroup.getScrollY() > 1) {
                //直接截断时间传播
                return false;
            } else {
                return super.onTouchEvent(arg0);
            }
        }
        return super.onTouchEvent(arg0);
    }


}
