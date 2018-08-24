package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/4/17.
 */

public class ChooseFLBean {

    /**
     * Code : 000000
     * Msg : 请求OK
     * Time : 2018-04-17 14:15:09
     * ApiUrl : http://xmap1803013.php.hzxmnet.com/api/goods_category/category.html
     * Data : [{"id":1,"title":"女装","sub":[{"id":2,"title":"羽绒服"},{"id":3,"title":"毛呢外套"},{"id":4,"title":"毛衣"}]},{"id":5,"title":"男装1","sub":[{"id":6,"title":"羽绒服"}]}]
     */

    private String Code;
    private String Msg;
    private String Time;
    private String ApiUrl;
    private List<DataBean> Data;

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
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
         * title : 女装
         * sub : [{"id":2,"title":"羽绒服"},{"id":3,"title":"毛呢外套"},{"id":4,"title":"毛衣"}]
         */

        private int id;
        private String title;
        private List<DataBean> sub;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<DataBean> getSub() {
            return sub;
        }

        public void setSub(List<DataBean> sub) {
            this.sub = sub;
        }
    }
}
