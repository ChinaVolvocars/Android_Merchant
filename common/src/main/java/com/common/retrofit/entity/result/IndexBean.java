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
}
