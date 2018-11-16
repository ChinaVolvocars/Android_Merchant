package com.view.pie.data;

import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;

public interface IPieInfo {

    double getValue();

    @ColorInt
    int getColor();

    String getDesc();

    @Nullable
    PieOption getPieOption();
}
