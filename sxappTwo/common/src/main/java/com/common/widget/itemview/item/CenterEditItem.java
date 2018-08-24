package com.common.widget.itemview.item;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.common.R;
import com.common.utils.EmptyUtils;
import com.common.utils.ResourceUtils;
import com.common.utils.SizeUtils;
import com.common.widget.editview.DeleteEditText;

/*******************************
* 输入框展示在item中间

* created at 2017/3/22 下午 4:35
********************************/
public class CenterEditItem extends AbstractItem {

    private DeleteEditText centerEditView;
    private LayoutParams centerEditViewlp;

    public CenterEditItem(Context context) {
        super(context);
    }

    public CenterEditItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CenterEditItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DeleteEditText getCenterEditView() {
        return centerEditView;
    }

    @Override
    public void createWidget() {
        centerEditView = new DeleteEditText(mContext);
        centerEditView.setId(R.id.center_edit_id);
    }

    @Override
    public void createWidgetLayoutParams() {

        centerEditViewlp = new LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        centerEditViewlp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        centerEditViewlp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
    }

    @Override
    public void addWidget() {
        super.addWidget();
        addView(centerEditView, centerEditViewlp);
        setRightTextStyle();
    }

    /** 设置文字的样式 */
    public void setRightTextStyle(){

        if(configAttrs == null){
            throw  new RuntimeException("config attrs is null");
        }

        centerEditViewlp.leftMargin = (int) SizeUtils.dp2px(mContext,configAttrs.getMarginLeft() - 10);
        centerEditViewlp.rightMargin = (int) SizeUtils.dp2px(mContext,configAttrs.getMarginRight() + 10);

        if (EmptyUtils.isNotEmpty(configAttrs.getValueCenterList())) {
            centerEditView.setHint(configAttrs.getValueCenterList().get(configAttrs.getPosition()));
        }
        centerEditView.setMaxLines(1);
        centerEditView.setSingleLine(true);
        centerEditView.setBackground(ResourceUtils.getDrawble(mContext, R.color.transparent));
        centerEditView.setGravity(Gravity.CENTER_VERTICAL);
        centerEditView.setTextColor(configAttrs.getCenterTextColor());
        centerEditView.setHintTextColor(configAttrs.getCenterTextHintColor());
        centerEditView.setTextSize(TypedValue.COMPLEX_UNIT_SP, configAttrs.getCenterTextSize());
    }
}
