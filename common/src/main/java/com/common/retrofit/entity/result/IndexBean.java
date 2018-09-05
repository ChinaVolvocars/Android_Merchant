package com.common.retrofit.entity.result;

/**
 * Created by STH on 2018/6/9.
 */

public class IndexBean {

    /**
     * id : 1
     * ad_img :
     * pay_img :
     * proportion : 0.05
     */
    private String id;
    /**
     * 商铺图片url
     */
    private String ad_img;
    /**
     * 收款码
     */
    private String pay_img;
    private String proportion;
    private String ratio;
    private String managementID;
    private String managementNum;
    private String managementCode;
    private String new_shop_activity_name;
    private String new_shop_activity_url;

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAd_img() {
        return ad_img;
    }

    public void setAd_img(String ad_img) {
        this.ad_img = ad_img;
    }

    public String getPay_img() {
        return pay_img;
    }

    public void setPay_img(String pay_img) {
        this.pay_img = pay_img;
    }

    /**
     * 获取让利扣点
     */
    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getManagementID() {
        return managementID;
    }

    public void setManagementID(String managementID) {
        this.managementID = managementID;
    }

    public String getManagementNum() {
        return managementNum;
    }

    public void setManagementNum(String managementNum) {
        this.managementNum = managementNum;
    }

    public String getManagementCode() {
        return managementCode;
    }

    public void setManagementCode(String managementCode) {
        this.managementCode = managementCode;
    }

    /**
     *  商家新活动标题
     * @return
     */
    public String getNew_shop_activity_name() {
        return new_shop_activity_name;
    }

    public void setNew_shop_activity_name(String new_shop_activity_name) {
        this.new_shop_activity_name = new_shop_activity_name;
    }

    /**
     *  商家新活动链接
     * @return
     */
    public String getNew_shop_activity_url() {
        return new_shop_activity_url;
    }

    public void setNew_shop_activity_url(String new_shop_activity_url) {
        this.new_shop_activity_url = new_shop_activity_url;
    }
}
