package com.common.retrofit.entity.result;

/**
 * Created by STH on 2018/5/28.
 */

public class UserBean {

    /**
     * uid : 22
     * hashid : 42d101d79954ad5268cf6b7edd9ef603
     * face :
     * nickname : NM13720216420
     * phone : 13720216420
     */

    private int uid;
    private int status;
    private String hashid;

    public String getCheck_status() {
        return check_status;
    }

    public void setCheck_status(String check_status) {
        this.check_status = check_status;
    }

    private String check_status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOauth_id() {
        return oauth_id;
    }

    public void setOauth_id(String oauth_id) {
        this.oauth_id = oauth_id;
    }

    private String oauth_id;
    private String face;
    private String nickname;
    private String phone;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getHashid() {
        return hashid;
    }

    public void setHashid(String hashid) {
        this.hashid = hashid;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
