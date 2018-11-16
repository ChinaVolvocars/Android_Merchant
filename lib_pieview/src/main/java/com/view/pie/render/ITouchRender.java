package com.view.pie.render;

import android.view.MotionEvent;

public interface ITouchRender {

    boolean onTouchEvent(MotionEvent event);

    void forceAbortTouch();
}
