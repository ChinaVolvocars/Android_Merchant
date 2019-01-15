package com.view.pie;

import android.content.Context;
import android.view.View;

import com.view.pie.manager.PieManager;

public interface IPieView {

    PieManager getManager();

    Context getViewContext();

    public AnimatedPieViewConfig getConfig();

    View getPieView();

    void onCallInvalidate();
}
