package com.common.widget.itemview.item;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.common.R;
import com.common.utils.EmptyUtils;
import com.common.utils.SizeUtils;
import com.common.widget.imageview.image.ImageLoaderUtils;

/*******************************
* 右侧添加imageview

* created at 2017/3/18 下午 1:37
********************************/
public class PictureItem extends AbstractItem
{
    private ImageView avatorImg;
    private LayoutParams avatorLp;
    private ImageView arrowImg;
    private LayoutParams arrowLp;

    public PictureItem(Context context) {
        super(context);
    }

    public PictureItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PictureItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ImageView getAvatorImg() {
        return avatorImg;
    }

    @Override
    public void createWidget() {
        arrowImg = new ImageView(mContext);
        arrowImg.setId(R.id.arrow_id);
        avatorImg = new ImageView(mContext);
        avatorImg.setId(R.id.picture_id);
    }

    @Override
    public void createWidgetLayoutParams() {
        arrowLp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        arrowLp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        arrowLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);

        avatorLp = new LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        avatorLp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        avatorLp.addRule(RelativeLayout.LEFT_OF, R.id.arrow_id);
    }

    @Override
    public void addWidget() {
        super.addWidget();
        addView(avatorImg, avatorLp);
        addView(arrowImg, arrowLp);
        setPictureStyle();
    }

    /** 图片大小 */
    public void setPictureStyle(){

        if(configAttrs == null){
            throw new RuntimeException("config attrs is null");
        }

        if(EmptyUtils.isEmpty(configAttrs.getRightPicArray())) {
            return;
        }

        if(configAttrs.getArrowResId() == 0){
            throw new RuntimeException("arrow res id is null");
        }

        if(configAttrs.getRightPictureSize() == 0){
            throw new RuntimeException("right picture size is null");
        }

        String picture = configAttrs.getRightPicArray().get(configAttrs.getPosition()) == null
                ? "" : configAttrs.getRightPicArray().get(configAttrs.getPosition());

        avatorLp.width = (int) SizeUtils.dp2px(mContext, configAttrs.getRightPictureSize());
        avatorLp.height = (int) SizeUtils.dp2px(mContext, configAttrs.getRightPictureSize());
        avatorLp.setMargins((int)SizeUtils.dp2px(mContext, 5), (int)SizeUtils.dp2px(mContext, 5),
                (int)SizeUtils.dp2px(mContext, 5), (int)SizeUtils.dp2px(mContext, 5));
        avatorImg.setLayoutParams(avatorLp);
        avatorImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        avatorLp.rightMargin = (int) SizeUtils.dp2px(mContext, configAttrs.getMarginRight());

        // 无图片时不显示占位符
        if (EmptyUtils.isNotEmpty(picture)) {
            ImageLoaderUtils.display(avatorImg, picture);
        }

        arrowLp.rightMargin = (int) SizeUtils.dp2px(mContext,configAttrs.getMarginRight());
        arrowImg.setImageResource(configAttrs.getArrowResId());
    }
}
