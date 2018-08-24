package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/4/16.
 */

public class MyZBList {

    /**
     * total : 3
     * page : 1
     * limit : 10
     * remainder : 0
     * lists : [{"id":1,"title":"1","live_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","start_time":"04-11 18时","live_price":"1.00"},{"id":2,"title":"1","live_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","start_time":"04-11 18时","live_price":"1.00"},{"id":3,"title":"1","live_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","start_time":"04-11 18时","live_price":"1.00"}]
     */

    private int total;
    private int page;
    private int limit;
    private int remainder;
    private List<ListsBean> lists;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getRemainder() {
        return remainder;
    }

    public void setRemainder(int remainder) {
        this.remainder = remainder;
    }

    public List<ListsBean> getLists() {
        return lists;
    }

    public void setLists(List<ListsBean> lists) {
        this.lists = lists;
    }

    public static class ListsBean {
        /**
         * id : 1
         * title : 1
         * live_img : http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg
         * start_time : 04-11 18时
         * live_price : 1.00
         */

        private int id;
        private String title;
        private String live_img;
        private String start_time;
        private String live_price;

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

        public String getLive_img() {
            return live_img;
        }

        public void setLive_img(String live_img) {
            this.live_img = live_img;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getLive_price() {
            return live_price;
        }

        public void setLive_price(String live_price) {
            this.live_price = live_price;
        }
    }
}
