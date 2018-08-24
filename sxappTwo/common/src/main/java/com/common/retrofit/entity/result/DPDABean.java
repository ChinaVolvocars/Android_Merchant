package com.common.retrofit.entity.result;

/**
 * Created by STH on 2018/4/25.
 */

public class DPDABean {

    /**
     * shop_id : 5
     * shop_face : http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523607548.png
     * title : 刺杀
     * is_focus : 0
     * city_text : 北京市
     * shop_time : 1970-01-01
     * address : 北京·北京市·东城区·文源路
     * money : 200.00
     * sold_num : 0
     * month_update : 1
     * person : 李世民
     * tel : 18868861235
     */

    private int shop_id;
    private String shop_face;
    private String title;
    private int is_focus;
    private String city_text;
    private String shop_time;
    private String address;
    private String money;
    private int sold_num;
    private int month_update;
    private String person;
    private String tel;

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_face() {
        return shop_face;
    }

    public void setShop_face(String shop_face) {
        this.shop_face = shop_face;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIs_focus() {
        return is_focus;
    }

    public void setIs_focus(int is_focus) {
        this.is_focus = is_focus;
    }

    public String getCity_text() {
        return city_text;
    }

    public void setCity_text(String city_text) {
        this.city_text = city_text;
    }

    public String getShop_time() {
        return shop_time;
    }

    public void setShop_time(String shop_time) {
        this.shop_time = shop_time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getSold_num() {
        return sold_num;
    }

    public void setSold_num(int sold_num) {
        this.sold_num = sold_num;
    }

    public int getMonth_update() {
        return month_update;
    }

    public void setMonth_update(int month_update) {
        this.month_update = month_update;
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
}
