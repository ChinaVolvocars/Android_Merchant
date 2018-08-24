package com.live.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 * 礼物bean
 */
public class GiftInfo {


    /**
     * Code : 0
     * Msg : 请求成功
     * Time : 2016-10-12 09:20:19
     * ApiUrl : /Api/Goods/giftList.html
     * Data : {"list":[{"id":1,"name":"房子","masonry":1000,"pic":"http://139.196.214.241:8090/Uploads/Picture/2016-10-11/57fc62c658034.png","empirical_value":10000},{"id":5,"name":"蛋糕","masonry":100,"pic":"http://139.196.214.241:8090/Uploads/Picture/2016-10-11/57fc627f5125e.png","empirical_value":1000},{"id":15,"name":"披萨","masonry":300,"pic":"http://139.196.214.241:8090/Uploads/Picture/2016-10-11/57fc6401996f1.png","empirical_value":3000},{"id":14,"name":"腊肉","masonry":800,"pic":"http://139.196.214.241:8090/Uploads/Picture/2016-10-11/57fc63efb158a.png","empirical_value":8000},{"id":13,"name":"鸡腿","masonry":700,"pic":"http://139.196.214.241:8090/Uploads/Picture/2016-10-11/57fc63db18169.png","empirical_value":7000},{"id":12,"name":"红酒","masonry":600,"pic":"http://139.196.214.241:8090/Uploads/Picture/2016-10-11/57fc63ca3133c.png","empirical_value":6000},{"id":11,"name":"雨伞","masonry":400,"pic":"http://139.196.214.241:8090/Uploads/Picture/2016-10-11/57fc63b757a63.png","empirical_value":4000},{"id":10,"name":"寿司","masonry":500,"pic":"http://139.196.214.241:8090/Uploads/Picture/2016-10-11/57fc63a490b1a.png","empirical_value":5000}],"total":16,"remainder":8,"page":1}
     */

    private int Code;
    private String Msg;
    private String Time;
    private String ApiUrl;
    /**
     * list : [{"id":1,"name":"房子","masonry":1000,"pic":"http://139.196.214.241:8090/Uploads/Picture/2016-10-11/57fc62c658034.png","empirical_value":10000},{"id":5,"name":"蛋糕","masonry":100,"pic":"http://139.196.214.241:8090/Uploads/Picture/2016-10-11/57fc627f5125e.png","empirical_value":1000},{"id":15,"name":"披萨","masonry":300,"pic":"http://139.196.214.241:8090/Uploads/Picture/2016-10-11/57fc6401996f1.png","empirical_value":3000},{"id":14,"name":"腊肉","masonry":800,"pic":"http://139.196.214.241:8090/Uploads/Picture/2016-10-11/57fc63efb158a.png","empirical_value":8000},{"id":13,"name":"鸡腿","masonry":700,"pic":"http://139.196.214.241:8090/Uploads/Picture/2016-10-11/57fc63db18169.png","empirical_value":7000},{"id":12,"name":"红酒","masonry":600,"pic":"http://139.196.214.241:8090/Uploads/Picture/2016-10-11/57fc63ca3133c.png","empirical_value":6000},{"id":11,"name":"雨伞","masonry":400,"pic":"http://139.196.214.241:8090/Uploads/Picture/2016-10-11/57fc63b757a63.png","empirical_value":4000},{"id":10,"name":"寿司","masonry":500,"pic":"http://139.196.214.241:8090/Uploads/Picture/2016-10-11/57fc63a490b1a.png","empirical_value":5000}]
     * total : 16
     * remainder : 8
     * page : 1
     */

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
        private int total;
        private int remainder;
        private int page;
        /**
         * id : 1
         * name : 房子
         * masonry : 1000
         * pic : http://139.196.214.241:8090/Uploads/Picture/2016-10-11/57fc62c658034.png
         * empirical_value : 10000
         */

        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getRemainder() {
            return remainder;
        }

        public void setRemainder(int remainder) {
            this.remainder = remainder;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int id;
            private String name;
            private int masonry;
            private String pic;
            private int empirical_value;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getMasonry() {
                return masonry;
            }

            public void setMasonry(int masonry) {
                this.masonry = masonry;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getEmpirical_value() {
                return empirical_value;
            }

            public void setEmpirical_value(int empirical_value) {
                this.empirical_value = empirical_value;
            }
        }
    }
}
