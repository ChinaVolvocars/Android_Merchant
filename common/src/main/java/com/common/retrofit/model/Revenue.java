package com.common.retrofit.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Revenue implements Parcelable {

    private String total_money;
    private int status;
    private String day;
    private String month;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTotal_money() {
        return total_money;
    }

    public void setTotal_money(String total_money) {
        this.total_money = total_money;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.total_money);
        dest.writeInt(this.status);
    }

    public Revenue() {
    }

    protected Revenue(Parcel in) {
        this.total_money = in.readString();
        this.status = in.readInt();
    }

    public static final Creator<Revenue> CREATOR = new Creator<Revenue>() {
        @Override
        public Revenue createFromParcel(Parcel source) {
            return new Revenue(source);
        }

        @Override
        public Revenue[] newArray(int size) {
            return new Revenue[size];
        }
    };
}
