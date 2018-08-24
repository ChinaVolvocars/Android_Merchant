package com.common.widget.itemview.item;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.R;
import com.common.utils.SizeUtils;

/*******************************
* 文本框展示在item中间

* created at 2017/3/22 下午 4:35
********************************/
public class CenterTextItem extends AbstractItem {

    private TextView centerTextView;
    private LayoutParams centerTextViewlp;

    public CenterTextItem(Context context) {
        super(context);
    }

    public CenterTextItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CenterTextItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void createWidget() {
        centerTextView = new TextView(mContext);
        centerTextView.setId(R.id.center_text_id);
    }

    @Override
    public void createWidgetLayoutParams() {

        centerTextViewlp = new LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        centerTextViewlp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        centerTextViewlp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
    }

    @Override
    public void addWidget() {
        super.addWidget();
        addView(centerTextView, centerTextViewlp);
        setRightTextStyle();
    }

    /** 设置文字的样式 */
    public void setRightTextStyle(){

        if(configAttrs == null){
            throw  new RuntimeException("config attrs is null");
        }

        centerTextViewlp.leftMargin = (int) SizeUtils.dp2px(mContext,configAttrs.getMarginLeft());

        centerTextView.setGravity(Gravity.CENTER_VERTICAL);
        centerTextView.setTextColor(configAttrs.getCenterTextColor());
        centerTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, configAttrs.getCenterTextSize());
    }
}
