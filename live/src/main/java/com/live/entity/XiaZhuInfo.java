package com.live.entity;

/**
 * Created by Administrator on 2017/1/10.
 */

public class XiaZhuInfo {


    /**
     * ApiUrl : /Api/Game/userbet.html
     * Code : 0
     * Data : {"account":"4999980.00"}
     * Msg : 请求成功
     * Time : 2017-01-10 11:08:19
     */

    private String ApiUrl;
    private int Code;
    private DataBean Data;
    private String Msg;
    private String Time;

    public String getApiUrl() {
        return ApiUrl;
    }

    public void setApiUrl(String ApiUrl) {
        this.ApiUrl = ApiUrl;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public static class DataBean {
        /**
         * account : 4999980.00
         */

        private String account;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }
    }
}
