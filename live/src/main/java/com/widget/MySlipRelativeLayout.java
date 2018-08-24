package com.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2016/12/2.
 */

public class MySlipRelativeLayout extends RelativeLayout {
    private OnMySlipRelativeSollListener listener;
    private float downY;
    private String TAG = "MySlipRelativeLayout";

    public MySlipRelativeLayout(Context context) {
        super(context);
    }

    public MySlipRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySlipRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float dwonX = event.getX();
                downY = event.getY();
                Log.d("onTouchEvent", "downY:" + downY);
                break;
            case MotionEvent.ACTION_UP:
                float upY = event.getY();
                float descY = upY - downY;
                if (descY > 0 && Math.abs(descY) > 100) {//向下滑动

                    if (listener != null) {
                        listener.OnMySlipRelativeLayoutToDwon();
                    }
                    Log.d("onTouchEvent", "dwon>>>>descY:" + descY);
                } else if (descY < 0 && Math.abs(descY) > 100) {//向上滑动


                    if (listener != null) {
                        listener.OnMySlipRelativeLayoutToUp();
                    }

                    Log.d("onTouchEvent", "up>>>>descY:" + descY);
                }
                break;
        }
        return super.onTouchEvent(event);
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                float dwonX = event.getX();
//                downY = event.getY();
//                break;
//            case MotionEvent.ACTION_UP:
//                float upY = event.getY();
//                float descY = upY - downY;
//                if (descY > 0 && Math.abs(descY) > 200) {//向下滑动
//
//                    if (listener != null) {
//                        listener.OnMySlipRelativeLayoutToDwon();
//                    }
//
//                    return true;
//                } else if (descY < 0 && Math.abs(descY) > 200) {//向上滑动
//
//
//                    if (listener != null) {
//                        listener.OnMySlipRelativeLayoutToUp();
//                    }
//                    return true;
//                }
//                break;
//        }
//        return super.onInterceptTouchEvent(event);
//    }

    public void setOnMySlipRelativeSollListener(OnMySlipRelativeSollListener listener) {
        this.listener = listener;
    }

    public interface OnMySlipRelativeSollListener {
        void OnMySlipRelativeLayoutToUp();

        void OnMySlipRelativeLayoutToDwon();
    }
}
