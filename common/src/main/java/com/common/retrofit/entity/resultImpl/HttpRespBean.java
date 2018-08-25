package com.common.retrofit.entity.resultImpl;

public class HttpRespBean<T> {
    private int Code;
    private String Msg;
    private String Time;
    private String ApiUrl;
    private T Data;

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getApiUrl() {
        return ApiUrl;
    }

    public void setApiUrl(String apiUrl) {
        ApiUrl = apiUrl;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
