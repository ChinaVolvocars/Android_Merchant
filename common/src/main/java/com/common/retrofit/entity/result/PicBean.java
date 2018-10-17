package com.common.retrofit.entity.result;

/**
 * Created by STH on 2018/5/31.
 */

public class PicBean {

    /**
     * Code : 0
     * Msg : 成功
     * Time : 2018-05-31 11:09:53
     * ApiUrl : /Api/Upload/uploadPicture.html
     * Data : http://sxpic.oss-cn-hangzhou.aliyuncs.com/file/2018-05-31/0737f3fd-c0ca-4e85-a533-94967aaddf50.JPEG
     */
    private int Code;
    private String Msg;
    private String Time;
    private String ApiUrl;
    private String Data;

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

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }
}
