package com.common.retrofit.model;


import android.os.Parcel;
import android.os.Parcelable;

public class ShopBanner implements Parcelable{


    /**
     * id : 1
     * picture : http://sxpic.oss-cn-hangzhou.aliyuncs.com/file/2018-11-10/shangjia.png
     * link : http://activity.zhongxinyingjia.com/individual/newgift.html
     */

    private String id;
    private String picture;
    private String link;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.picture);
        dest.writeString(this.link);
    }

    public ShopBanner() {
    }

    protected ShopBanner(Parcel in) {
        this.id = in.readString();
        this.picture = in.readString();
        this.link = in.readString();
    }

    public static final Creator<ShopBanner> CREATOR = new Creator<ShopBanner>() {
        @Override
        public ShopBanner createFromParcel(Parcel source) {
            return new ShopBanner(source);
        }

        @Override
        public ShopBanner[] newArray(int size) {
            return new ShopBanner[size];
        }
    };
}
