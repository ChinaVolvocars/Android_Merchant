package com.common.widget.webview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

import com.common.R;

public class MaxHeightWebView extends WebView {
    private int mMaxHeight;// 最大高度
    private int mDefaultMaxHeight = -1;//默认最大高度，-1即不限制

    public MaxHeightWebView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

   public MaxHeightWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttr(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;//不处理触摸事件，解决问题二
    }

    private void getAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaxHeightWebView);
        mMaxHeight = typedArray.getDimensionPixelSize(R.styleable.MaxHeightWebView_vMaxHeight, mDefaultMaxHeight);//读取xml里的最大高度
    }

    public MaxHeightWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public MaxHeightWebView(Context context, AttributeSet attrs, int defStyle,
                            boolean privateBrowsing) {
        super(context, attrs, defStyle, privateBrowsing);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //如果xml里设置的最大高度有效，则View的高度不会超过最大高度
        if (mMaxHeight > -1 && getMeasuredHeight() > mMaxHeight) {
            setMeasuredDimension(getMeasuredWidth(), mMaxHeight);
        }
    }

    //告诉View不会和别的View重叠，提高渲染效率  | tangentLu
    @Override
    public boolean hasOverlappingRendering() {
        return false;
    }
}