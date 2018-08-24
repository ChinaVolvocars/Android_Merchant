package com.common.widget.itemview.factory;

import android.content.Context;

import com.common.widget.itemview.config.ConfigAttrs;
import com.common.widget.itemview.config.Mode;
import com.common.widget.itemview.item.AbstractItem;

/**
 * Created by maimingliang on 2016/12/4.
 * 创建Item 的工厂类
 */
public abstract class AbstractItemFactory {

    protected Context mContext;

    public AbstractItemFactory(Context context){
        this.mContext = context;
    }

    public abstract  <T extends AbstractItem> T createItem(Mode mode, ConfigAttrs attrs);
}
