package com.common.retrofit.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class TodayRevenue implements Parcelable{


    /**
     * transaction : 2
     * total_xindou : 0
     * pay_moneys : 0.96
     * total_money : 0.96
     */

    private String time;
    private int transaction;
    private double total_xindou;
    private double pay_moneys;
    private double total_money;
    private List<TodaysRevenue> today_list;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTransaction() {
        return transaction;
    }

    public void setTransaction(int transaction) {
        this.transaction = transaction;
    }

    public double getTotal_xindou() {
        return total_xindou;
    }

    public void setTotal_xindou(double total_xindou) {
        this.total_xindou = total_xindou;
    }

    public double getPay_moneys() {
        return pay_moneys;
    }

    public void setPay_moneys(double pay_moneys) {
        this.pay_moneys = pay_moneys;
    }

    public double getTotal_money() {
        return total_money;
    }

    public void setTotal_money(double total_money) {
        this.total_money = total_money;
    }

    public List<TodaysRevenue> getToday_list() {
        return today_list;
    }

    public void setToday_list(List<TodaysRevenue> today_list) {
        this.today_list = today_list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.time);
        dest.writeInt(this.transaction);
        dest.writeDouble(this.total_xindou);
        dest.writeDouble(this.pay_moneys);
        dest.writeDouble(this.total_money);
        dest.writeTypedList(this.today_list);
    }

    public TodayRevenue() {
    }

    protected TodayRevenue(Parcel in) {
        this.time = in.readString();
        this.transaction = in.readInt();
        this.total_xindou = in.readDouble();
        this.pay_moneys = in.readDouble();
        this.total_money = in.readDouble();
        this.today_list = in.createTypedArrayList(TodaysRevenue.CREATOR);
    }

    public static final Creator<TodayRevenue> CREATOR = new Creator<TodayRevenue>() {
        @Override
        public TodayRevenue createFromParcel(Parcel source) {
            return new TodayRevenue(source);
        }

        @Override
        public TodayRevenue[] newArray(int size) {
            return new TodayRevenue[size];
        }
    };
}
