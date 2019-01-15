package com.common.retrofit.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Home implements Parcelable {

    /**
     * xindou : 4.00
     * money : 3.20
     * shop_banner : [{"id":"1","picture":"http://sxpic.oss-cn-hangzhou.aliyuncs.com/file/2018-11-10/shangjia.png","link":"http://activity.zhongxinyingjia.com/individual/newgift.html"},{"id":"2","picture":"http://sxpic.oss-cn-hangzhou.aliyuncs.com/file/2018-11-10/shangjia.png","link":"http://activity.zhongxinyingjia.com/individual/guidelines.html"}]
     * service_function : [{"id":"3","picture":"http://sxpic.oss-cn-hangzhou.aliyuncs.com/file/2018-11-10/shangjiaban_shouye_woyaozhanshi%403x.png"},{"id":"4","picture":"http://sxpic.oss-cn-hangzhou.aliyuncs.com/file/2018-11-10/shangjiaban_shouye_shangjiaziliao%403x.png"},{"id":"5","picture":"http://sxpic.oss-cn-hangzhou.aliyuncs.com/file/2018-11-10/shangjiaban_shouye_kaixinchuandan%403x.png"}]
     * managementLimit : 1
     * pay_xindou : 0.00
     * pay_money : 0.00
     * accumulative : 0.00
     * list : [{"time":19,"type":2,"title":"商家推广活动","desc":"商家推广活动","pic":"http://sxpic.oss-cn-hangzhou.aliyuncs.com/file/2018-11-10/shangjiaban_shouye_kaixinchuandan%403x.png","url":"http://cc.com/Admin/Index/index.html"},{"time":1168,"type":2,"title":"新入驻商户推广奖励","desc":"注册90天内直荐会员首次满20送商家5鑫利豆","pic":"http://sxpic.oss-cn-hangzhou.aliyuncs.com/file/2018-09-20/%E5%95%86%E5%AE%B6%E7%89%88%E9%A6%96%E9%A1%B5banner-2.png","url":"http://activity.zhongxinyingjia.com/merchant/newgift.html"}]
     */
    private String username;
    private String pay_img;
    private String xindou;
    private String money;
    private String closed_pay_url;
    private String invoice_url;
    private String copy_invoice_url;
    private String managementLimit;
    private String pay_xindou;
    private String pay_money;
    private String accumulative;
    private int week;
    private List<ShopBanner> shop_banner;
    private List<ServiceFunction> service_function;
    private List<ActivityList> list;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPay_img() {
        return pay_img;
    }

    public void setPay_img(String pay_img) {
        this.pay_img = pay_img;
    }

    public String getClosed_pay_url() {
        return closed_pay_url;
    }

    public void setClosed_pay_url(String closed_pay_url) {
        this.closed_pay_url = closed_pay_url;
    }

    public String getInvoice_url() {
        return invoice_url;
    }

    public void setInvoice_url(String invoice_url) {
        this.invoice_url = invoice_url;
    }

    public String getXindou() {
        return xindou;
    }

    public void setXindou(String xindou) {
        this.xindou = xindou;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getManagementLimit() {
        return managementLimit;
    }

    public void setManagementLimit(String managementLimit) {
        this.managementLimit = managementLimit;
    }

    public String getPay_xindou() {
        return pay_xindou;
    }

    public void setPay_xindou(String pay_xindou) {
        this.pay_xindou = pay_xindou;
    }

    public String getPay_money() {
        return pay_money;
    }

    public void setPay_money(String pay_money) {
        this.pay_money = pay_money;
    }

    public String getAccumulative() {
        return accumulative;
    }

    public void setAccumulative(String accumulative) {
        this.accumulative = accumulative;
    }

    public List<ShopBanner> getShop_banner() {
        return shop_banner;
    }

    public void setShop_banner(List<ShopBanner> shop_banner) {
        this.shop_banner = shop_banner;
    }

    public List<ServiceFunction> getService_function() {
        return service_function;
    }

    public void setService_function(List<ServiceFunction> service_function) {
        this.service_function = service_function;
    }

    public List<ActivityList> getList() {
        return list;
    }

    public void setList(List<ActivityList> list) {
        this.list = list;
    }

    public String getCopy_invoice_url() {
        return copy_invoice_url;
    }

    /**
     * 是否可提现   <br/>
     * 0不可提现，  <br/> 1 可以提现  <br/>
     *
     * @return
     */
    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public void setCopy_invoice_url(String copy_invoice_url) {
        this.copy_invoice_url = copy_invoice_url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.pay_img);
        dest.writeString(this.xindou);
        dest.writeString(this.money);
        dest.writeString(this.closed_pay_url);
        dest.writeString(this.invoice_url);
        dest.writeString(this.copy_invoice_url);
        dest.writeString(this.managementLimit);
        dest.writeString(this.pay_xindou);
        dest.writeString(this.pay_money);
        dest.writeString(this.accumulative);
        dest.writeInt(this.week);
        dest.writeTypedList(this.shop_banner);
        dest.writeTypedList(this.service_function);
        dest.writeTypedList(this.list);
    }

    public Home() {
    }

    protected Home(Parcel in) {
        this.pay_img = in.readString();
        this.xindou = in.readString();
        this.money = in.readString();
        this.closed_pay_url = in.readString();
        this.invoice_url = in.readString();
        this.copy_invoice_url = in.readString();
        this.managementLimit = in.readString();
        this.pay_xindou = in.readString();
        this.pay_money = in.readString();
        this.accumulative = in.readString();
        this.week = in.readInt();
        this.shop_banner = in.createTypedArrayList(ShopBanner.CREATOR);
        this.service_function = in.createTypedArrayList(ServiceFunction.CREATOR);
        this.list = in.createTypedArrayList(ActivityList.CREATOR);
    }

    public static final Creator<Home> CREATOR = new Creator<Home>() {
        @Override
        public Home createFromParcel(Parcel source) {
            return new Home(source);
        }

        @Override
        public Home[] newArray(int size) {
            return new Home[size];
        }
    };
}
