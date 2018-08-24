package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/6/9.
 */

public class BankNameBean {

    /**
     * Code : 0
     * Msg : 请求成功
     * Time : 2018-06-01 17:10:39
     * ApiUrl : /Api/Center/bankType.html
     * Data : [{"id":"1","name":"中国建设银行"},{"id":"2","name":"中国银行"},{"id":"3","name":"中国农业银行"},{"id":"4","name":"中国工商银行"},{"id":"5","name":"中国民生银行"},{"id":"6","name":"招商银行"},{"id":"7","name":"兴业银行"},{"id":"8","name":"国家开发银行"},{"id":"9","name":"北京银行"},{"id":"10","name":"汇丰银行"},{"id":"11","name":"中国人民银行"},{"id":"12","name":"中国光大银行"},{"id":"13","name":"中信银行"},{"id":"14","name":"上海浦东发展银行"},{"id":"15","name":"深圳发展银行"},{"id":"16","name":"中国邮政储蓄银行"},{"id":"17","name":"华夏银行"},{"id":"18","name":"恒丰银行"},{"id":"19","name":"交通银行"},{"id":"20","name":"广东发展银行"},{"id":"21","name":"农村信用社"},{"id":"22","name":"上海农商银行"},{"id":"23","name":"平安银行"},{"id":"24","name":"东亚银行"}]
     */

    private int Code;
    private String Msg;
    private String Time;
    private String ApiUrl;
    private List<DataBean> Data;

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

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 中国建设银行
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
