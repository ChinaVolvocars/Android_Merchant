package com.hzxmkuar.sxmaketnew.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by STH on 2018/8/10.
 */

public class YQBean implements Parcelable {

    /**
     * code : 4TT779
     * img : http://zxyj.com/Uploads/Attachment/code_4TT779.png
     */

    private String code;
    private String img;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.img);
    }

    public YQBean() {
    }

    protected YQBean(Parcel in) {
        this.code = in.readString();
        this.img = in.readString();
    }

    public static final Creator<YQBean> CREATOR = new Creator<YQBean>() {
        @Override
        public YQBean createFromParcel(Parcel source) {
            return new YQBean(source);
        }

        @Override
        public YQBean[] newArray(int size) {
            return new YQBean[size];
        }
    };
}
