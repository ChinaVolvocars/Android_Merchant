package com.live.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/1/18.
 */

public class DSGiftInfo {


    /**
     * Code : 0
     * Msg : 请求成功
     * Time : 2017-01-18 15:56:22
     * ApiUrl : /Api/Game/gifts.html
     * Data : {"list":[{"id":"2","img":"http://139.196.214.241:8096/Uploads/Picture/2017-01-17/587dadcbac91a.jpg","jinbi":"20.00","status":"1","sorts":"2"},{"id":"1","img":"http://139.196.214.241:8096/Uploads/Picture/2017-01-11/5875996f6ca0e.jpg","jinbi":"10.00","status":"1","sorts":"1"},{"id":"3","img":"http://139.196.214.241:8096/Uploads/Picture/2017-01-11/5875998d81c83.jpg","jinbi":"30.00","status":"1","sorts":"0"}],"zuid":8}
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
         * list : [{"id":"2","img":"http://139.196.214.241:8096/Uploads/Picture/2017-01-17/587dadcbac91a.jpg","jinbi":"20.00","status":"1","sorts":"2"},{"id":"1","img":"http://139.196.214.241:8096/Uploads/Picture/2017-01-11/5875996f6ca0e.jpg","jinbi":"10.00","status":"1","sorts":"1"},{"id":"3","img":"http://139.196.214.241:8096/Uploads/Picture/2017-01-11/5875998d81c83.jpg","jinbi":"30.00","status":"1","sorts":"0"}]
         * zuid : 8
         */

        private int zuid;
        private List<ListBean> list;

        public int getZuid() {
            return zuid;
        }

        public void setZuid(int zuid) {
            this.zuid = zuid;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 2
             * img : http://139.196.214.241:8096/Uploads/Picture/2017-01-17/587dadcbac91a.jpg
             * jinbi : 20.00
             * status : 1
             * sorts : 2
             */

            private String id;
            private String img;
            private String jinbi;
            private String status;
            private String sorts;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getJinbi() {
                return jinbi;
            }

            public void setJinbi(String jinbi) {
                this.jinbi = jinbi;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getSorts() {
                return sorts;
            }

            public void setSorts(String sorts) {
                this.sorts = sorts;
            }
        }
    }
}
