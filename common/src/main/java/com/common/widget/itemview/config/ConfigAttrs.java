package com.common.widget.itemview.config;

import android.util.SparseArray;

import java.util.List;

/**
 * Created by maimingliang on 2016/12/4.
 */
public  class ConfigAttrs {

    private int position;

    /** item 的 高度 */
     int itemHeight;

    /** 分割线的颜色 */
    private int lineColor;

    /** 图标的 高宽 */
    private int iconWidth;
    private int iconHeight;

    /** 字体的大小 */
    private int textSize;

    /** 字体的颜色 */
    private int textColor;

    /** 图标距离左边的 marginLeft 大小 */
    private int iconMarginLeft;

    /** 文字 距离 图标的 marginLeft 大小 */
    private int iconTextMargin;

    /** TextView 的显示文字 按顺序 插入 */
    private List<String> valueList;

    /** 中间文本 的显示文字 按顺序 插入 */
    private List<String> valueCenterList;

    /** icon 图标的 resId 按顺序插入 */
    private List<Integer> resIdList;

    /** 箭头距离 最右边 的 marginRight 大小 */
    private int marginRight;

    /** 中间文本 距离最左边 的 marginLeft 大小 */
    private int marginLeft;
    private int centerTextSize;
    private int centerTextColor;
    private int centerTextHintColor;

    /** 箭头的资源Id */
    private int arrowResId;

    private int textArrowResId;

    /** 字体的大小 */
    private int rightTextSize;
    /** 字体的颜色 */
    private int rightTextColor;

    /** 整体背景资源 */
    private int backGroundColor;

    /** button图标的资源 */
    private int turnOnResId;
    private int turnOffResId ;
    /** 右边字体和箭头的间距 */
    private int rightTextMagin;

    /**  每一个 item 与 item 之间的 marginTop 的大小 */
    private SparseArray marginArray=new SparseArray<Integer>();

    /** item 的模式 */
    private SparseArray<Mode> modeArray = new SparseArray<>();

    /** 保存右边的text */
    private SparseArray<String> rightTextArray = new SparseArray<>();

    /** 保存右边的图片 */
    private SparseArray<String> rightPicArray = new SparseArray<>();

    /** 右边图片的大小 */
    private int rightPictureSize;

    public int getTextArrowResId() {
        return textArrowResId;
    }

    public ConfigAttrs setTextArrowResId(int textArrowResId) {
        this.textArrowResId = textArrowResId;
        return this;
    }

    public int getBackGroundColor() {
        return backGroundColor;
    }

    public ConfigAttrs setBackGroundColor(int backGroundColor) {
        this.backGroundColor = backGroundColor;
        return this;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getItemHeight() {
        return itemHeight;
    }

    public ConfigAttrs setItemHeight(int itemHeight) {
        this.itemHeight = itemHeight;
        return this;
    }

    public int getCenterTextHintColor() {
        return centerTextHintColor;
    }

    public ConfigAttrs setCenterTextHintColor(int centerTextHintColor) {
        this.centerTextHintColor = centerTextHintColor;
        return this;
    }

    public int getLineColor() {
        return lineColor;
    }

    public ConfigAttrs setLineColor(int lineColor) {
        this.lineColor = lineColor;
        return this;
    }

    public int getIconWidth() {
        return iconWidth <= 0 ? 20 : iconWidth;
    }

    public ConfigAttrs setIconWidth(int iconWidth) {
        this.iconWidth = iconWidth;
        return this;
    }

    public int getIconHeight() {
        return iconHeight <= 0 ? 20 : iconHeight;
    }

    public ConfigAttrs setIconHeight(int iconHeight) {
        this.iconHeight = iconHeight;
        return this;
    }

    public int getTextSize() {
        return textSize;
    }

    public ConfigAttrs setTextSize(int textSize) {
        this.textSize = textSize;
        return this;
    }

    public int getTextColor() {
        return textColor;
    }

    public ConfigAttrs setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public int getIconMarginLeft() {
        return iconMarginLeft;
    }

    public ConfigAttrs setIconMarginLeft(int iconMarginLeft) {
        this.iconMarginLeft = iconMarginLeft;
        return this;
    }

    public int getIconTextMargin() {
        return iconTextMargin;
    }

    public ConfigAttrs setIconTextMargin(int iconTextMargin) {
        this.iconTextMargin = iconTextMargin;
        return this;
    }

    public List<String> getValueList() {
        return valueList;
    }

    public List<String> getValueCenterList() {
        return valueCenterList;
    }

    public ConfigAttrs setValueCenterList(List<String> valueCenterList) {
        this.valueCenterList = valueCenterList;
        return this;
    }

    public ConfigAttrs setValueList(List<String> valueList) {
        this.valueList = valueList;
        return this;
    }

    public List<Integer> getResIdList() {
        return resIdList;
    }

    public ConfigAttrs setResIdList(List<Integer> resIdList) {
        this.resIdList = resIdList;
        return this;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public ConfigAttrs setMarginRight(int marginRight) {
        this.marginRight = marginRight;
        return this;
    }

    public int getCenterTextSize() {
        return centerTextSize;
    }

    public ConfigAttrs setCenterTextSize(int centerTextSize) {
        this.centerTextSize = centerTextSize;
        return this;
    }

    public int getCenterTextColor() {
        return centerTextColor;
    }

    public ConfigAttrs setCenterTextColor(int centerTextColor) {
        this.centerTextColor = centerTextColor;
        return this;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public ConfigAttrs setMarginLeft(int marginLeft) {
        if (marginLeft < 15) {
            marginLeft = 150;
        }
        this.marginLeft = marginLeft;
        return this;
    }


    public int getArrowResId() {
        return arrowResId;
    }

    public ConfigAttrs setArrowResId(int arrowResId) {
        this.arrowResId = arrowResId;
        return this;
    }

    public int getRightTextSize() {
        return rightTextSize;
    }

    public ConfigAttrs setRightTextSize(int rightTextSize) {
        this.rightTextSize = rightTextSize;
        return this;
    }

    public int getRightTextColor() {
        return rightTextColor;
    }

    public ConfigAttrs setRightTextColor(int rightTextColor) {
        this.rightTextColor = rightTextColor;
        return this;
    }

    public int getTurnOnResId() {
        return turnOnResId;
    }

    public ConfigAttrs setTurnOnResId(int turnOnResId) {
        this.turnOnResId = turnOnResId;
        return this;
    }

    public int getTurnOffResId() {
        return turnOffResId;
    }

    public ConfigAttrs setTurnOffResId(int turnOffResId) {
        this.turnOffResId = turnOffResId;
        return this;
    }

    public int getRightTextMagin() {
        return rightTextMagin;
    }

    public ConfigAttrs setRightTextMagin(int rightTextMagin) {
        this.rightTextMagin = rightTextMagin;
        return this;
    }

    public SparseArray getMarginArray() {
        return marginArray;
    }

    public SparseArray<Mode> getModeArray() {
        return modeArray;
    }

    public SparseArray<String> getRightTextArray() {
        return rightTextArray;
    }

    public ConfigAttrs setItemMode(Mode value){

        if(valueList.size() <= 0){
            throw  new RuntimeException("values is null");
        }

        for(int i = 0 ;i < valueList.size();i++){
            modeArray.put(i,value);
        }
        return this;
    }

    public ConfigAttrs setItemMode(int index, Mode value){
        modeArray.put(index,value);
        return this;
    }



    public ConfigAttrs setRightText(List<String> values){

        if(values == null){
            throw  new RuntimeException("values is null");
        }

        if(values.size() <= 0){
            throw  new RuntimeException("");
        }

        for(int i = 0 ;i<values.size();i++){
            rightTextArray.put(i,values.get(i));
        }

        return this;
    }

    public ConfigAttrs setRightText(int position, String text) {

        rightTextArray.put(position,text);
        return this;
    }

    public ConfigAttrs setRightPicture(int position, String text) {
        rightPicArray.put(position,text);
        return this;
    }

    public SparseArray<String> getRightPicArray() {
        return rightPicArray;
    }

    public int getRightPictureSize() {
        return rightPictureSize;
    }

    public ConfigAttrs setRightPictureSize(int rightPictureSize) {
        this.rightPictureSize = rightPictureSize;
        return this;
    }

    public ConfigAttrs setItemMarginTop(int index, int value){
        marginArray.put(index,value);
        return this;
    }

    public ConfigAttrs setItemMarginTop(int value){

        if(valueList.size() <= 0){
            throw  new RuntimeException("");
        }

        for(int i = 0 ;i<valueList.size();i++){
            marginArray.put(i,value);
        }
        return this;
    }


}
