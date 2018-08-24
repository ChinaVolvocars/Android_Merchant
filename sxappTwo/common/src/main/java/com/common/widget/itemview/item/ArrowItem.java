package com.common.widget.itemview.item;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.common.R;
import com.common.utils.SizeUtils;

/**
 * Created by maimingliang on 2016/12/4.
 */

public class ArrowItem extends AbstractItem
{
    private ImageView arrowImg;
    private LayoutParams arrowLp;

    public ArrowItem(Context context) {
        super(context);
    }

    public ArrowItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ArrowItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void createWidget() {
        arrowImg = new ImageView(mContext);
        arrowImg.setId(R.id.arrow_id);
    }

    @Override
    public void createWidgetLayoutParams() {
        arrowLp = new LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        arrowLp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        arrowLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
    }

    @Override
    public void addWidget() {
        super.addWidget();
        addView(arrowImg, arrowLp);
        setArrowStyle();
    }

    /** 箭头的颜色 */
    public void setArrowStyle(){

        if(configAttrs == null){
            throw new RuntimeException("config attrs is null");
        }

        if(configAttrs.getArrowResId() == 0){
            throw new RuntimeException("arrow res id is null");
        }

        arrowLp.rightMargin = (int) SizeUtils.dp2px(mContext,configAttrs.getMarginRight());
        arrowImg.setImageResource(configAttrs.getArrowResId());
    }
}
