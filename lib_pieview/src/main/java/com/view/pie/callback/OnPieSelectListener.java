package com.view.pie.callback;

import android.support.annotation.NonNull;

import com.view.pie.data.IPieInfo;


public interface OnPieSelectListener<T extends IPieInfo> {
    /**
     * 选中的回调
     *
     * @param pieInfo   数据实体
     * @param isFloatUp 是否浮起
     */
    void onSelectPie(@NonNull T pieInfo, boolean isFloatUp);
}
