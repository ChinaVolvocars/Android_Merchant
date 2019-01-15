package com.view.pie.render;

import android.graphics.Canvas;
import android.support.annotation.Nullable;

import com.view.pie.IPieView;
import com.view.pie.manager.PieManager;

public abstract class BaseRender {
    protected String TAG = this.getClass().getSimpleName();

    IPieView mIPieView;
    PieManager mPieManager;
    private volatile boolean isPrepared;

    public BaseRender(IPieView iPieView) {
        mIPieView = iPieView;
        mPieManager = iPieView.getManager();
        mPieManager.registerRender(this);
    }

    public void draw(Canvas canvas) {
        if (!isPrepared) return;
        onDraw(canvas);
    }

    public final void prepare() {
        prepare(null);
    }

    public final void prepare(@Nullable final OnPrepareFinishListener l) {
        isPrepared = false;
        reset();
        mIPieView.getPieView().post(new Runnable() {
            @Override
            public void run() {
                isPrepared = onPrepare();
                if (isPrepared) {
                    handlePrepareFinish(l);
                }
            }
        });
    }

    public void destroy() {
        onDestroy();
        mPieManager.unRegisterRender(this);
    }

    public abstract void reset();

    public abstract boolean onPrepare();

    public abstract void onSizeChanged(int width, int height, int paddingLeft, int paddingTop, int paddingRight, int paddingBottom);

    public abstract void onDraw(Canvas canvas);

    public abstract void onDestroy();

    public void callInvalidate() {
        mIPieView.onCallInvalidate();
    }

    protected void handlePrepareFinish(OnPrepareFinishListener l) {
        if (l != null) {
            boolean handled = l.onPrepareFin();
            if (handled) return;
        }
        callInvalidate();
    }

    public interface OnPrepareFinishListener {
        boolean onPrepareFin();
    }

}
