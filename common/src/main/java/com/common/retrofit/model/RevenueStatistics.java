package com.common.retrofit.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class RevenueStatistics implements Parcelable {

    private String this_month;
    private String this_day;
    private List<Revenue> total_moneys;

    public String getThis_month() {
        return this_month;
    }

    public void setThis_month(String this_month) {
        this.this_month = this_month;
    }

    public String getThis_day() {
        return this_day;
    }

    public void setThis_day(String this_day) {
        this.this_day = this_day;
    }

    public List<Revenue> getTotal_moneys() {
        return total_moneys;
    }

    public void setTotal_moneys(List<Revenue> total_moneys) {
        this.total_moneys = total_moneys;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.this_month);
        dest.writeString(this.this_day);
        dest.writeTypedList(this.total_moneys);
    }

    public RevenueStatistics() {
    }

    protected RevenueStatistics(Parcel in) {
        this.this_month = in.readString();
        this.this_day = in.readString();
        this.total_moneys = in.createTypedArrayList(Revenue.CREATOR);
    }

    public static final Creator<RevenueStatistics> CREATOR = new Creator<RevenueStatistics>() {
        @Override
        public RevenueStatistics createFromParcel(Parcel source) {
            return new RevenueStatistics(source);
        }

        @Override
        public RevenueStatistics[] newArray(int size) {
            return new RevenueStatistics[size];
        }
    };
}
