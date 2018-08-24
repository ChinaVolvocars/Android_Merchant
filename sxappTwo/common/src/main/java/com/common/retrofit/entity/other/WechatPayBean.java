package com.common.retrofit.entity.other;

import com.google.gson.annotations.SerializedName;

/**
 * Created by leo on 2017/6/16.
 */

public class WechatPayBean {

    /**
     * appid : 1111
     * noncestr : 222222222
     * package : 222222222
     * partnerid : 222222222
     * prepayid : 222222222
     * timestamp : 222222222
     */

    private String appid;
    private String noncestr;
    private String partnerid;
    private String prepayid;
    private String timestamp;
    @SerializedName("package")
    private String packageX;

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    private String sign;

    public String getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }

    private String payInfo;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAppid() {
        return appid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
