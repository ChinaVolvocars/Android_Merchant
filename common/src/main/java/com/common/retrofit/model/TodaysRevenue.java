package com.common.retrofit.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TodaysRevenue implements Parcelable {


    /**
     * id : 310
     * xinlidou : 0.000
     * xianglidou : 0.000
     * pay_money : 0.16
     * pay_time : 2018-09-28 09:48:30
     * xinlijuan : 0
     * xindou : 0.00
     */
    private String id;
    private String xinlidou;
    private String xianglidou;
    private String pay_money;
    private String pay_time;
    private String xinlijuan;
    private String xindou;
    private String subtotal;

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXinlidou() {
        return xinlidou;
    }

    public void setXinlidou(String xinlidou) {
        this.xinlidou = xinlidou;
    }

    public String getXianglidou() {
        return xianglidou;
    }

    public void setXianglidou(String xianglidou) {
        this.xianglidou = xianglidou;
    }

    public String getPay_money() {
        return pay_money;
    }

    public void setPay_money(String pay_money) {
        this.pay_money = pay_money;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getXinlijuan() {
        return xinlijuan;
    }

    public void setXinlijuan(String xinlijuan) {
        this.xinlijuan = xinlijuan;
    }

    public String getXindou() {
        return xindou;
    }

    public void setXindou(String xindou) {
        this.xindou = xindou;
    }

    public TodaysRevenue() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.xinlidou);
        dest.writeString(this.xianglidou);
        dest.writeString(this.pay_money);
        dest.writeString(this.pay_time);
        dest.writeString(this.xinlijuan);
        dest.writeString(this.xindou);
        dest.writeString(this.subtotal);
    }

    protected TodaysRevenue(Parcel in) {
        this.id = in.readString();
        this.xinlidou = in.readString();
        this.xianglidou = in.readString();
        this.pay_money = in.readString();
        this.pay_time = in.readString();
        this.xinlijuan = in.readString();
        this.xindou = in.readString();
        this.subtotal = in.readString();
    }

    public static final Creator<TodaysRevenue> CREATOR = new Creator<TodaysRevenue>() {
        @Override
        public TodaysRevenue createFromParcel(Parcel source) {
            return new TodaysRevenue(source);
        }

        @Override
        public TodaysRevenue[] newArray(int size) {
            return new TodaysRevenue[size];
        }
    };
}
