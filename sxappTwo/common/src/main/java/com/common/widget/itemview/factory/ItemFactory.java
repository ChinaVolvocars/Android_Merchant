package com.common.widget.itemview.factory;

import android.content.Context;

import com.common.widget.itemview.config.ConfigAttrs;
import com.common.widget.itemview.config.Mode;
import com.common.widget.itemview.item.AbstractItem;
import com.common.widget.itemview.item.ArrowItem;
import com.common.widget.itemview.item.ButtonItem;
import com.common.widget.itemview.item.CenterEditItem;
import com.common.widget.itemview.item.CenterTextItem;
import com.common.widget.itemview.item.NormalItem;
import com.common.widget.itemview.item.PictureItem;
import com.common.widget.itemview.item.RedTextItem;
import com.common.widget.itemview.item.TextItem;
import com.common.widget.itemview.item.TextNoArrowItem;

/**
 * Created by maimingliang on 2016/12/4.
 */
public class ItemFactory extends AbstractItemFactory {

    public ItemFactory(Context context) {
        super(context);
    }

    /**
     * 创建出ItemView
     * @param mode  通过传入 不同mode 来创建出不同类型的itemview
     * @param attrs
     * @param <T>
     * @return
     */
    @Override
    public  <T extends AbstractItem> T createItem(Mode mode, ConfigAttrs attrs) {

        if(mode == null){
            throw new RuntimeException("please set mode");
        }
        AbstractItem item = null;

        try {

            if(mode == Mode.NORMAL){
                item = new NormalItem(mContext);
            } else if(mode == Mode.ARROW){
                item = new ArrowItem(mContext);
            } else if(mode == Mode.BOTTON){
                item = new ButtonItem(mContext);
            } else if(mode == Mode.TEXT){
                item = new TextItem(mContext);
            } else if(mode == Mode.TEXT_NOARROW){
                item = new TextNoArrowItem(mContext);
            } else if(mode == Mode.CENTER_TEXT){
                item = new CenterTextItem(mContext);
            } else if(mode == Mode.CENTER_EDIT){
                item = new CenterEditItem(mContext);
            } else if(mode == Mode.PICTURE){
                item = new PictureItem(mContext);
            } else if(mode == Mode.RED_TEXT){
                item = new RedTextItem(mContext);
            }

            if(item != null){
                item.create(attrs);
            }
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

        return (T) item;
    }
}
