package com.live.entity;

/**
 * Created by Administrator on 2017/1/17.
 */

public class ZPkJResultInfo {

    /**
     * Code : 0
     * Msg : 请求成功
     * Time : 2017-01-17 16:59:14
     * ApiUrl : /Api/Game/opentruntable.html
     * Data : {"ret":6,"money":0}
     */

    private int Code;
    private String Msg;
    private String Time;
    private String ApiUrl;
    private DataBean Data;

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
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

    public String getApiUrl() {
        return ApiUrl;
    }

    public void setApiUrl(String ApiUrl) {
        this.ApiUrl = ApiUrl;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * ret : 6
         * money : 0
         */

        private int ret;
        private int money;

        public int getRet() {
            return ret;
        }

        public void setRet(int ret) {
            this.ret = ret;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }
    }
}
