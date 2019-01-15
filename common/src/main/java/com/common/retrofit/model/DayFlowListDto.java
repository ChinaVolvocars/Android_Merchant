package com.common.retrofit.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class DayFlowListDto implements Parcelable{

    private String time;
    private int transaction;
    private String total_xindou;
    private String total_money;
    private String subsidys;
    private String subtotales;
    private List<DayFlowDto> list;

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

    public String getTotal_xindou() {
        return total_xindou;
    }

    public void setTotal_xindou(String total_xindou) {
        this.total_xindou = total_xindou;
    }

    public String getTotal_money() {
        return total_money;
    }

    public void setTotal_money(String total_money) {
        this.total_money = total_money;
    }

    public String getSubsidys() {
        return subsidys;
    }

    public void setSubsidys(String subsidys) {
        this.subsidys = subsidys;
    }

    public String getSubtotales() {
        return subtotales;
    }

    public void setSubtotales(String subtotales) {
        this.subtotales = subtotales;
    }

    public List<DayFlowDto> getList() {
        return list;
    }

    public void setList(List<DayFlowDto> list) {
        this.list = list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.time);
        dest.writeInt(this.transaction);
        dest.writeString(this.total_xindou);
        dest.writeString(this.total_money);
        dest.writeString(this.subsidys);
        dest.writeString(this.subtotales);
        dest.writeTypedList(this.list);
    }

    public DayFlowListDto() {
    }

    protected DayFlowListDto(Parcel in) {
        this.time = in.readString();
        this.transaction = in.readInt();
        this.total_xindou = in.readString();
        this.total_money = in.readString();
        this.subsidys = in.readString();
        this.subtotales = in.readString();
        this.list = in.createTypedArrayList(DayFlowDto.CREATOR);
    }

    public static final Creator<DayFlowListDto> CREATOR = new Creator<DayFlowListDto>() {
        @Override
        public DayFlowListDto createFromParcel(Parcel source) {
            return new DayFlowListDto(source);
        }

        @Override
        public DayFlowListDto[] newArray(int size) {
            return new DayFlowListDto[size];
        }
    };
}
