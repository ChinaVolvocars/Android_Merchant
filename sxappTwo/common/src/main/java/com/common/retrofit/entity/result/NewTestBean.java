package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/5/28.
 */

public class NewTestBean {

    /**
     * Code : 0
     * Msg : 请求成功
     * Time : 2018-05-28 11:59:26
     * ApiUrl : /Api/BusinessUser/scategory.html
     * Data : []
     */

    private int Code;
    private String Msg;
    private String Time;
    private String ApiUrl;
    private List<TestBean> Data;

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

    public List<TestBean> getData() {
        return Data;
    }

    public void setData(List<TestBean> Data) {
        this.Data = Data;
    }
}
