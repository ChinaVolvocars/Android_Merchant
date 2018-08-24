package com.common.retrofit.entity.result;

/**
 * Created by STH on 2018/4/19.
 */

public class AddressResBean {

    /**
     * id : 4
     * person : 宋文腾
     * tel : 13720216420
     * area : 湖北省孝感市孝昌县
     * address : 建筑村
     */

    private int id;
    private String person;
    private String tel;
    private String area;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
