package com.common.widget.itemview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.common.R;
import com.common.utils.SizeUtils;
import com.common.widget.itemview.config.CommonCons;
import com.common.widget.itemview.config.ConfigAttrs;
import com.common.widget.itemview.config.Mode;
import com.common.widget.itemview.factory.AbstractItemFactory;
import com.common.widget.itemview.factory.ItemFactory;
import com.common.widget.itemview.item.AbstractItem;
import com.common.widget.itemview.item.ButtonItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maimingliang on 2016/10/27.
 */
public class BaseItemLayout extends LinearLayout {

    private Context mContext;

    private AbstractItemFactory factory;
    private ConfigAttrs configAttrs;

    private List<View> viewList = new ArrayList<>();
    private int lineColor;
    private int backgroundColor;
    private int textSize;
    private int textColor;
    private int iconMarginLeft;
    private int iconTextMargin;
    private int arrowMarginRight;
    private int itemHeight;
    private int rightTextSize;
    private int rightTextColor;
    private int rightTextMagin;

    public BaseItemLayout(Context context) {
        this(context,null);
    }

    public BaseItemLayout(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public BaseItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCostomerArrts(context, attrs);
        init(context);
    }

    private void initCostomerArrts(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ItemAttrs);

        lineColor = a.getColor(R.styleable.ItemAttrs_line_color, CommonCons.DF_LINE_COLOR);
        backgroundColor = a.getResourceId(R.styleable.ItemAttrs_background_color, CommonCons.DF_BACKGROUND_COLOR);
        textSize = a.getInt(R.styleable.ItemAttrs_text_size, CommonCons.DF_LINE_COLOR);
        textColor = a.getColor(R.styleable.ItemAttrs_text_color, CommonCons.DF_TEXT_COLOR);
        iconMarginLeft = a.getInt(R.styleable.ItemAttrs_icon_margin_left, CommonCons.DF_ICON_MARGIN_LEFT);
        iconTextMargin = a.getInt(R.styleable.ItemAttrs_icon_text_margin, CommonCons.DF_ICON_TEXT_MARGIN);
        arrowMarginRight = a.getInt(R.styleable.ItemAttrs_margin_right, CommonCons.DF_ARROW_MARGIN_RIGHT);
        itemHeight = a.getInt(R.styleable.ItemAttrs_item_height, CommonCons.DF_ICON_HEIGHT);
        rightTextSize = a.getInt(R.styleable.ItemAttrs_right_text_size, CommonCons.DF_RIGHT_TEXT_SIZE);
        rightTextColor = a.getColor(R.styleable.ItemAttrs_right_text_color, CommonCons.DF_RIGHT_TEXT_COLOR);
        rightTextMagin = a.getInt(R.styleable.ItemAttrs_right_text_margin, CommonCons.DF_RIGHT_TEXT_MAGIN);

        a.recycle();
    }

    private void init(Context context){
        mContext = context;
        setOrientation(VERTICAL);
        factory = new ItemFactory(context);
     }

    public void create(){

        if(configAttrs == null){
            throw new RuntimeException("config attrs  is null");
        }

        if(configAttrs.getValueList() == null){
            throw new RuntimeException("valueList  is null");
        }

        for( int i = 0 ;i < configAttrs.getValueList().size();i++){
            configAttrs.setPosition(i);
            SparseArray<Mode> modeArray = configAttrs.getModeArray();
            Mode mode = modeArray.get(i);
            AbstractItem itemView = factory.createItem(mode,configAttrs);
            addItem(itemView,i);
        }
    }

    /**
     * 添加 item
     * @param view
     * @param pos
     */
    private void addItem(AbstractItem view, final int pos){

        if (configAttrs.getMarginArray() != null ) {

            if(configAttrs.getMarginArray().get(pos) != null){
                addView(createLineView((Integer) configAttrs.getMarginArray().get(pos)));
            }
        } else {
            addView(createLineView(CommonCons.DEFAULT_HEIGHT));
        }

        addView(view);
        addView(createLineView(CommonCons.ZERO_HEIGHT));

        if(onBaseItemClick != null){
            setListener(view,pos);
        }

        if(onSwitchClickListener != null){
            setButtonClick();
        }

        viewList.add(view);
    }

    public List<View> getViewList() {
        return viewList;
    }

    /**
     * 创建线
     * @param margin
     * @return
     */
    private View createLineView(int margin){

        View view = new View(mContext);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        lp.topMargin = (int) SizeUtils.dp2px(mContext,margin);

        view.setLayoutParams(lp);
        view.setBackgroundColor(lineColor);
        return view;
    }

    private void setOnClick() {
        if(onBaseItemClick != null){
            for(int i = 0 ;i <viewList.size();i++){
                View view = viewList.get(i);
                setListener(view,i);
            }
        }
    }

    /**
     * 设置 item 的监听回调
     * @param view
     * @param position
     */
    private void setListener(View view, final int position) {
        if(view == null){
            return;
        }

        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBaseItemClick.onItemClick(position);
            }
        });
    }

    /**
     * 设置 button的点击事件
     */
    private void setButtonClick() {

        if(onSwitchClickListener != null){

            for(int i = 0;i<viewList.size();i++){

                SparseArray<Mode> modeArray = configAttrs.getModeArray();

                Mode mode = modeArray.get(i);

                if(mode == Mode.BOTTON){

                    ButtonItem view = (ButtonItem) viewList.get(i);
                    SwitchImageView switchImageView = view.getSwitchImageView();
                    onButtonClick(i,switchImageView);
                }
            }
        }
    }

    private void onButtonClick(final int pos, final SwitchImageView switchImageView) {

        switchImageView.setOnSwitchClickListener(new SwitchImageView.OnSwitchClickListener() {
            @Override
            public void onClick(boolean isCheck) {


                if(isCheck){
                    switchImageView.setImageResource(R.mipmap.img_up);
                }else{
                    switchImageView.setImageResource(R.mipmap.img_turn_down);
                }
                 onSwitchClickListener.onClick(pos,isCheck);
            }
        });
    }


    public BaseItemLayout setConfigAttrs(ConfigAttrs attrs){


        if(attrs == null){
            throw new RuntimeException("attrs is null");
        }
        this.configAttrs = attrs;


        configAttrs.setLineColor(lineColor);
        configAttrs.setBackGroundColor(backgroundColor);
        configAttrs.setTextSize(textSize);
        configAttrs.setTextColor(textColor);
        configAttrs.setIconMarginLeft(iconMarginLeft);
        configAttrs.setRightTextColor(rightTextColor);
        configAttrs.setRightTextSize(rightTextSize);
        configAttrs.setIconTextMargin(iconTextMargin);
        configAttrs.setMarginRight(arrowMarginRight);
        configAttrs.setItemHeight(itemHeight);
        configAttrs.setRightTextMagin(rightTextMagin);
        return this;
    }

    //=================================监听事件====================================









    private OnBaseItemClick onBaseItemClick;

    public void setOnBaseItemClick(OnBaseItemClick onBaseItemClick) {
        this.onBaseItemClick = onBaseItemClick;
        setOnClick();
    }

    public interface OnBaseItemClick{

        void onItemClick(int position);
    }


    private OnSwitchClickListener onSwitchClickListener;

    public void setOnSwitchClickListener(OnSwitchClickListener onSwitchClickListener) {
        this.onSwitchClickListener = onSwitchClickListener;
        setButtonClick();
    }


    public interface OnSwitchClickListener{

        void onClick(int position, boolean isCheck);
    }



}
