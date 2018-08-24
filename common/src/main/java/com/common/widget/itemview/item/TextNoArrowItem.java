package com.common.widget.itemview.item;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.R;
import com.common.utils.EmptyUtils;
import com.common.utils.SizeUtils;

/**
 * Created by maimingliang on 2016/12/5.
 */
public class TextNoArrowItem extends AbstractItem {

    private TextView rightTextView;
    private LayoutParams rightTextViewlp;

    private TextView centerTextView;
    private LayoutParams centerTextViewlp;

    public TextNoArrowItem(Context context) {
        super(context);
    }

    public TextNoArrowItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextNoArrowItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void createWidget() {
        rightTextView = new TextView(mContext);
        rightTextView.setId(R.id.right_text_id);

        centerTextView = new TextView(mContext);
        centerTextView.setId(R.id.center_text_id);
    }

    public TextView getRightTextView() {
        return rightTextView;
    }

    public TextView getCenterTextView() {
        return centerTextView;
    }

    @Override
    public void createWidgetLayoutParams() {

        rightTextViewlp = new LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightTextViewlp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        rightTextViewlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);

        centerTextViewlp = new LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        centerTextViewlp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        centerTextViewlp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
    }

    @Override
    public void addWidget() {
        super.addWidget();
        addView(centerTextView, centerTextViewlp);
        addView(rightTextView, rightTextViewlp);
        setRightTextStyle();
        setCenterTextStyle();
    }

    /** 设置文字的样式 */
    public void setRightTextStyle(){

        if(configAttrs == null){
            throw  new RuntimeException("config attrs is null");
        }

        if(configAttrs.getRightTextArray() == null){
            throw  new RuntimeException("right text array is null");
        }

        String text = configAttrs.getRightTextArray().get(configAttrs.getPosition()) == null
                ? "" : configAttrs.getRightTextArray().get(configAttrs.getPosition());

        rightTextViewlp.rightMargin = (int) SizeUtils.dp2px(mContext,configAttrs.getMarginRight());

        rightTextView.setText(text);
        rightTextView.setGravity(Gravity.CENTER_VERTICAL);
        rightTextView.setTextColor(configAttrs.getRightTextColor());
        rightTextView.setTextSize(configAttrs.getRightTextSize());
    }

    /** 设置文字的样式 */
    public void setCenterTextStyle(){

        if(configAttrs == null){
            throw  new RuntimeException("config attrs is null");
        }

        centerTextViewlp.leftMargin = (int) SizeUtils.dp2px(mContext,configAttrs.getMarginLeft());

        if (EmptyUtils.isNotEmpty(configAttrs.getValueCenterList())) {
            centerTextView.setText(configAttrs.getValueCenterList().get(configAttrs.getPosition()));
        }
        centerTextView.setGravity(Gravity.CENTER_VERTICAL);
        centerTextView.setSingleLine(true);
        centerTextView.setTextColor(configAttrs.getCenterTextColor());
        centerTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, configAttrs.getCenterTextSize());
    }
}
