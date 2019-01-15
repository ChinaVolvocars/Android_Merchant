package com.common.retrofit.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *  商家服务功能
 */
public class ServiceFunction implements Parcelable {

    private String id;
    private String picture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.picture);
    }

    public ServiceFunction() {
    }

    protected ServiceFunction(Parcel in) {
        this.id = in.readString();
        this.picture = in.readString();
    }

    public static final Creator<ServiceFunction> CREATOR = new Creator<ServiceFunction>() {
        @Override
        public ServiceFunction createFromParcel(Parcel source) {
            return new ServiceFunction(source);
        }

        @Override
        public ServiceFunction[] newArray(int size) {
            return new ServiceFunction[size];
        }
    };

    @Override
    public String toString() {
        return "ServiceFunction{" +
                "id='" + id + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
