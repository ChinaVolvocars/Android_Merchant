package com.common.retrofit.model;

import android.os.Parcel;
import android.os.Parcelable;

public class DayFlowDto implements Parcelable {

    private String id;
    private String xinlidou;
    private String xianglidou;
    private String pay_money;
    private String pay_time;
    private String xinlijuan;
    private String create_time;
    private String xindou;
    private String subsidy;
    private String subtotal;

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

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getXindou() {
        return xindou;
    }

    public void setXindou(String xindou) {
        this.xindou = xindou;
    }

    public String getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(String subsidy) {
        this.subsidy = subsidy;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
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
        dest.writeString(this.create_time);
        dest.writeString(this.xindou);
        dest.writeString(this.subsidy);
        dest.writeString(this.subtotal);
    }

    public DayFlowDto() {
    }

    protected DayFlowDto(Parcel in) {
        this.id = in.readString();
        this.xinlidou = in.readString();
        this.xianglidou = in.readString();
        this.pay_money = in.readString();
        this.pay_time = in.readString();
        this.xinlijuan = in.readString();
        this.create_time = in.readString();
        this.xindou = in.readString();
        this.subsidy = in.readString();
        this.subtotal = in.readString();
    }

    public static final Creator<DayFlowDto> CREATOR = new Creator<DayFlowDto>() {
        @Override
        public DayFlowDto createFromParcel(Parcel source) {
            return new DayFlowDto(source);
        }

        @Override
        public DayFlowDto[] newArray(int size) {
            return new DayFlowDto[size];
        }
    };

}
