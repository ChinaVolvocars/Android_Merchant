package com.live.entity;

/**
 * Created by Administrator on 2016/10/18.
 */
public class BaseInfo {

    /**
     * code : 0
     * msg : 发送成功
     * time : 2016-06-27 11:43:42
     * apiurl : /Api/Sms/sendcode.html
     * ApiHash : 1b3c9d5204337e8da1bfd52fda68ab10
     * data :
     */

    private int code;
    private String msg;
    private String time;
    private String apiurl;
    private String ApiHash;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getApiurl() {
        return apiurl;
    }

    public void setApiurl(String apiurl) {
        this.apiurl = apiurl;
    }

    public String getApiHash() {
        return ApiHash;
    }

    public void setApiHash(String ApiHash) {
        this.ApiHash = ApiHash;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
