package com.common.retrofit.entity.resultImpl;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by STH on 2018/4/11.
 */

public class CityBean implements Parcelable {

    /**
     * id : 1
     * pid : 0
     * area : 北京
     * level : 1
     * status : 1
     * pinyin : beijing
     * first_code : B
     * shortname : 北京
     * merger_name : 中国,北京
     * code :
     * zip_code :
     * lng : 116.405285
     * lat : 39.904989
     * sub : [{"id":2,"pid":1,"area":"北京市","level":2,"status":1,"pinyin":"beijing","first_code":"B","shortname":"北京","merger_name":"中国,北京,北京市","code":"010","zip_code":"100000","lng":"116.405285","lat":"39.904989","sub":[{"id":3,"pid":2,"area":"东城区","level":3,"status":1,"pinyin":"dongcheng","first_code":"D","shortname":"东城","merger_name":"中国,北京,北京市,东城区","code":"010","zip_code":"100010","lng":"116.41005","lat":"39.93157"},{"id":4,"pid":2,"area":"西城区","level":3,"status":1,"pinyin":"xicheng","first_code":"X","shortname":"西城","merger_name":"中国,北京,北京市,西城区","code":"010","zip_code":"100032","lng":"116.36003","lat":"39.9305"},{"id":5,"pid":2,"area":"朝阳区","level":3,"status":1,"pinyin":"chaoyang","first_code":"C","shortname":"朝阳","merger_name":"中国,北京,北京市,朝阳区","code":"010","zip_code":"100020","lng":"116.48548","lat":"39.9484"},{"id":6,"pid":2,"area":"丰台区","level":3,"status":1,"pinyin":"fengtai","first_code":"F","shortname":"丰台","merger_name":"中国,北京,北京市,丰台区","code":"010","zip_code":"100071","lng":"116.28625","lat":"39.8585"},{"id":7,"pid":2,"area":"石景山区","level":3,"status":1,"pinyin":"shijingshan","first_code":"S","shortname":"石景山","merger_name":"中国,北京,北京市,石景山区","code":"010","zip_code":"100043","lng":"116.2229","lat":"39.90564"},{"id":8,"pid":2,"area":"海淀区","level":3,"status":1,"pinyin":"haidian","first_code":"H","shortname":"海淀","merger_name":"中国,北京,北京市,海淀区","code":"010","zip_code":"100089","lng":"116.29812","lat":"39.95931"},{"id":9,"pid":2,"area":"门头沟区","level":3,"status":1,"pinyin":"mentougou","first_code":"M","shortname":"门头沟","merger_name":"中国,北京,北京市,门头沟区","code":"010","zip_code":"102300","lng":"116.10137","lat":"39.94043"},{"id":10,"pid":2,"area":"房山区","level":3,"status":1,"pinyin":"fangshan","first_code":"F","shortname":"房山","merger_name":"中国,北京,北京市,房山区","code":"010","zip_code":"102488","lng":"116.14257","lat":"39.74786"},{"id":11,"pid":2,"area":"通州区","level":3,"status":1,"pinyin":"tongzhou","first_code":"T","shortname":"通州","merger_name":"中国,北京,北京市,通州区","code":"010","zip_code":"101149","lng":"116.65716","lat":"39.90966"},{"id":12,"pid":2,"area":"顺义区","level":3,"status":1,"pinyin":"shunyi","first_code":"S","shortname":"顺义","merger_name":"中国,北京,北京市,顺义区","code":"010","zip_code":"101300","lng":"116.65417","lat":"40.1302"},{"id":13,"pid":2,"area":"昌平区","level":3,"status":1,"pinyin":"changping","first_code":"C","shortname":"昌平","merger_name":"中国,北京,北京市,昌平区","code":"010","zip_code":"102200","lng":"116.2312","lat":"40.22072"},{"id":14,"pid":2,"area":"大兴区","level":3,"status":1,"pinyin":"daxing","first_code":"D","shortname":"大兴","merger_name":"中国,北京,北京市,大兴区","code":"010","zip_code":"102600","lng":"116.34149","lat":"39.72668"},{"id":15,"pid":2,"area":"怀柔区","level":3,"status":1,"pinyin":"huairou","first_code":"H","shortname":"怀柔","merger_name":"中国,北京,北京市,怀柔区","code":"010","zip_code":"101400","lng":"116.63168","lat":"40.31602"},{"id":16,"pid":2,"area":"平谷区","level":3,"status":1,"pinyin":"pinggu","first_code":"P","shortname":"平谷","merger_name":"中国,北京,北京市,平谷区","code":"010","zip_code":"101200","lng":"117.12133","lat":"40.14056"},{"id":17,"pid":2,"area":"密云县","level":3,"status":1,"pinyin":"miyun","first_code":"M","shortname":"密云","merger_name":"中国,北京,北京市,密云县","code":"010","zip_code":"101500","lng":"116.84295","lat":"40.37618"},{"id":18,"pid":2,"area":"延庆县","level":3,"status":1,"pinyin":"yanqing","first_code":"Y","shortname":"延庆","merger_name":"中国,北京,北京市,延庆县","code":"010","zip_code":"102100","lng":"115.97494","lat":"40.45672"}]}]
     */
    private int id;
    private int pid;
    private String area;
    private int level;
    private int status;
    private String pinyin;
    private String first_code;
    private String shortname;
    private String merger_name;
    private String code;
    private String zip_code;
    private String lng;
    private String lat;
    private List<CityBean> sub;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getFirst_code() {
        return first_code;
    }

    public void setFirst_code(String first_code) {
        this.first_code = first_code;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getMerger_name() {
        return merger_name;
    }

    public void setMerger_name(String merger_name) {
        this.merger_name = merger_name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public List<CityBean> getSub() {
        return sub;
    }

    public void setSub(List<CityBean> sub) {
        this.sub = sub;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.pid);
        dest.writeString(this.area);
        dest.writeInt(this.level);
        dest.writeInt(this.status);
        dest.writeString(this.pinyin);
        dest.writeString(this.first_code);
        dest.writeString(this.shortname);
        dest.writeString(this.merger_name);
        dest.writeString(this.code);
        dest.writeString(this.zip_code);
        dest.writeString(this.lng);
        dest.writeString(this.lat);
        dest.writeTypedList(sub);
    }

    protected CityBean(Parcel in) {
        this.id = in.readInt();
        this.pid = in.readInt();
        this.area = in.readString();
        this.level = in.readInt();
        this.status = in.readInt();
        this.pinyin = in.readString();
        this.first_code = in.readString();
        this.shortname = in.readString();
        this.merger_name = in.readString();
        this.code = in.readString();
        this.zip_code = in.readString();
        this.lng = in.readString();
        this.lat = in.readString();
        this.sub = in.createTypedArrayList(CityBean.CREATOR);
    }

    public static final Creator<CityBean> CREATOR = new Creator<CityBean>() {
        public CityBean createFromParcel(Parcel source) {
            return new CityBean(source);
        }

        public CityBean[] newArray(int size) {
            return new CityBean[size];
        }
    };
}
