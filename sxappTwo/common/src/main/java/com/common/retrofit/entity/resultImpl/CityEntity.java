package com.common.retrofit.entity.resultImpl;

import android.os.Parcel;
import android.os.Parcelable;

import com.common.utils.StringUtils;

import java.util.List;

public class CityEntity implements Parcelable {
    /**
     * id : 110000
     * pid : 0
     * area : 北京
     * pinyin : bei jing
     * first_code : B
     * level : 1
     * status : 1
     * isdd : 0
     */
    private String id;
    private String pid;
    private String area;
    private String pinyin;
    private String first_code;
    private String level;
    private String status;
    private String isdd;
    private List<CityEntity> sub;
    private boolean isSelected;

    public CityEntity() {  }

    public List<CityEntity> getSub() {
        return sub;
    }

    public void setSub(List<CityEntity> sub) {
        this.sub = sub;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public void setFirst_code(String first_code) {
        this.first_code = first_code;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIsdd(String isdd) {
        this.isdd = isdd;
    }

    public String getId() {
        return StringUtils.nullToStr(id);
    }

    public String getPid() {
        return StringUtils.nullToStr(pid);
    }

    public String getArea() {
        return StringUtils.nullToStr(area);
    }

    public String getPinyin() {
        return pinyin;
    }

    public String getFirst_code() {
        return first_code;
    }

    public String getLevel() {
        return level;
    }

    public String getStatus() {
        return status;
    }

    public String getIsdd() {
        return isdd;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.pid);
        dest.writeString(this.area);
        dest.writeString(this.pinyin);
        dest.writeString(this.first_code);
        dest.writeString(this.level);
        dest.writeString(this.status);
        dest.writeString(this.isdd);
        dest.writeTypedList(sub);
        dest.writeByte(isSelected ? (byte) 1 : (byte) 0);
    }

    protected CityEntity(Parcel in) {
        this.id = in.readString();
        this.pid = in.readString();
        this.area = in.readString();
        this.pinyin = in.readString();
        this.first_code = in.readString();
        this.level = in.readString();
        this.status = in.readString();
        this.isdd = in.readString();
        this.sub = in.createTypedArrayList(CityEntity.CREATOR);
        this.isSelected = in.readByte() != 0;
    }

    public static final Creator<CityEntity> CREATOR = new Creator<CityEntity>() {
        public CityEntity createFromParcel(Parcel source) {
            return new CityEntity(source);
        }

        public CityEntity[] newArray(int size) {
            return new CityEntity[size];
        }
    };
}