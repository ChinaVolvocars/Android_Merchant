package com.live.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/2/7.
 */

public class LuckPanInfo {


    /**
     * Code : 0
     * Msg : 请求成功
     * Time : 2017-02-07 11:37:15
     * ApiUrl : /Api/Game/turntable.html
     * Data : {"list":[{"id":"1","img":"http://139.196.214.241:8096/Uploads/Picture/2017-01-11/587599a8a83d1.jpg","multiple":"5","create_time":"1483755991","status":"1","sorts":"1"},{"id":"4","img":"http://139.196.214.241:8096/Uploads/Picture/2017-01-11/5875998d81c83.jpg","multiple":"15","create_time":"1484100328","status":"1","sorts":"0"},{"id":"3","img":"","multiple":"10","create_time":"1483769408","status":"1","sorts":"0"},{"id":"5","img":"http://139.196.214.241:8096/Uploads/Picture/2017-01-11/5875998336772.jpg","multiple":"20","create_time":"1484100341","status":"1","sorts":"0"},{"id":"6","img":"http://139.196.214.241:8096/Uploads/Picture/2017-01-11/587599785e470.jpg","multiple":"25","create_time":"1484100353","status":"1","sorts":"0"},{"id":"7","img":"http://139.196.214.241:8096/Uploads/Picture/2017-01-11/5875996f6ca0e.jpg","multiple":"30","create_time":"1484100369","status":"1","sorts":"0"}],"zuid":8,"backimg":"http://139.196.214.241:8096/Uploads/Picture/2017-01-09/58735dd3ceb52.jpg","dtime":"21","mtid":"10430"}
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
         * list : [{"id":"1","img":"http://139.196.214.241:8096/Uploads/Picture/2017-01-11/587599a8a83d1.jpg","multiple":"5","create_time":"1483755991","status":"1","sorts":"1"},{"id":"4","img":"http://139.196.214.241:8096/Uploads/Picture/2017-01-11/5875998d81c83.jpg","multiple":"15","create_time":"1484100328","status":"1","sorts":"0"},{"id":"3","img":"","multiple":"10","create_time":"1483769408","status":"1","sorts":"0"},{"id":"5","img":"http://139.196.214.241:8096/Uploads/Picture/2017-01-11/5875998336772.jpg","multiple":"20","create_time":"1484100341","status":"1","sorts":"0"},{"id":"6","img":"http://139.196.214.241:8096/Uploads/Picture/2017-01-11/587599785e470.jpg","multiple":"25","create_time":"1484100353","status":"1","sorts":"0"},{"id":"7","img":"http://139.196.214.241:8096/Uploads/Picture/2017-01-11/5875996f6ca0e.jpg","multiple":"30","create_time":"1484100369","status":"1","sorts":"0"}]
         * zuid : 8
         * backimg : http://139.196.214.241:8096/Uploads/Picture/2017-01-09/58735dd3ceb52.jpg
         * dtime : 21
         * mtid : 10430
         */

        private int zuid;
        private String backimg;
        private String dtime;
        private String mtid;
        private List<ListBean> list;

        public int getZuid() {
            return zuid;
        }

        public void setZuid(int zuid) {
            this.zuid = zuid;
        }

        public String getBackimg() {
            return backimg;
        }

        public void setBackimg(String backimg) {
            this.backimg = backimg;
        }

        public String getDtime() {
            return dtime;
        }

        public void setDtime(String dtime) {
            this.dtime = dtime;
        }

        public String getMtid() {
            return mtid;
        }

        public void setMtid(String mtid) {
            this.mtid = mtid;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * img : http://139.196.214.241:8096/Uploads/Picture/2017-01-11/587599a8a83d1.jpg
             * multiple : 5
             * create_time : 1483755991
             * status : 1
             * sorts : 1
             */

            private String id;
            private String img;
            private String multiple;
            private String create_time;
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

            public String getMultiple() {
                return multiple;
            }

            public void setMultiple(String multiple) {
                this.multiple = multiple;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
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
