package com.common.retrofit.entity.result;

/**
 * Created by STH on 2018/4/9.
 */

public class UserInfoBean {

    /**
     * id : 6
     * nickname : 还好
     * face : 89
     * face_path : http://p6e3vd3f7.bkt.clouddn.com/APP/File/1523930245431_song.jpg
     * account : 0.00
     * score : 0.00
     * user_type : 1
     * shop_status : 1
     * shop_comments : 审核通过
     * shop_id : 7
     * is_pay : 1
     * money : 100
     * shop_name : 刚回家
     * shop_face : http://p6e3vd3f7.bkt.clouddn.com/APP/File/1523932867227_song.jpg
     */

    private int id;
    private String nickname;
    private int face;
    private String face_path;
    private String account;
    private String score;
    private int user_type;

    public int getIs_auth() {
        return is_auth;
    }

    public void setIs_auth(int is_auth) {
        this.is_auth = is_auth;
    }

    private int is_auth;
    private int shop_status;
    private String shop_comments;
    private int shop_id;
    private int is_pay;
    private String money;
    private String shop_name;
    private String shop_face;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public String getFace_path() {
        return face_path;
    }

    public void setFace_path(String face_path) {
        this.face_path = face_path;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public int getShop_status() {
        return shop_status;
    }

    public void setShop_status(int shop_status) {
        this.shop_status = shop_status;
    }

    public String getShop_comments() {
        return shop_comments;
    }

    public void setShop_comments(String shop_comments) {
        this.shop_comments = shop_comments;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public int getIs_pay() {
        return is_pay;
    }

    public void setIs_pay(int is_pay) {
        this.is_pay = is_pay;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_face() {
        return shop_face;
    }

    public void setShop_face(String shop_face) {
        this.shop_face = shop_face;
    }
}
