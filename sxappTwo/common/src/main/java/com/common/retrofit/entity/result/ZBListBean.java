package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/4/12.
 */

public class ZBListBean {

    /**
     * total : 3
     * page : 1
     * limit : 10
     * remainder : 0
     * lists : [{"id":1,"title":"1","status":1,"watch_num":0,"live_price":"1.00","live_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","shop_face":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","shop_name":"嘿嘿网批店","play_hdl_url":"","play_rtmp_url":"","play_hls_url":"","status_t":"04-11 18时"},{"id":2,"title":"1","status":1,"watch_num":0,"live_price":"1.00","live_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","shop_face":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","shop_name":"嘿嘿网批店","play_hdl_url":"","play_rtmp_url":"","play_hls_url":"","status_t":"04-11 18时"},{"id":3,"title":"1","status":1,"watch_num":0,"live_price":"1.00","live_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","shop_face":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","shop_name":"嘿嘿网批店","play_hdl_url":"","play_rtmp_url":"","play_hls_url":"","status_t":"04-11 18时"}]
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
         * status : 1
         * watch_num : 0
         * live_price : 1.00
         * live_img : http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg
         * shop_face : http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg
         * shop_name : 嘿嘿网批店
         * play_hdl_url :
         * play_rtmp_url :
         * play_hls_url :
         * status_t : 04-11 18时
         */

        private int id;
        private int is_pay;
        private String hx_room_id;

        public String getHx_room_id() {
            return hx_room_id;
        }

        public void setHx_room_id(String hx_room_id) {
            this.hx_room_id = hx_room_id;
        }

        public int getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(int is_pay) {
            this.is_pay = is_pay;
        }

        private String title;
        private int status;
        private int watch_num;
        private String live_price;
        private String live_img;
        private String shop_face;
        private String shop_name;
        private String play_hdl_url;
        private String play_rtmp_url;
        private String play_hls_url;
        private String status_t;

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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getWatch_num() {
            return watch_num;
        }

        public void setWatch_num(int watch_num) {
            this.watch_num = watch_num;
        }

        public String getLive_price() {
            return live_price;
        }

        public void setLive_price(String live_price) {
            this.live_price = live_price;
        }

        public String getLive_img() {
            return live_img;
        }

        public void setLive_img(String live_img) {
            this.live_img = live_img;
        }

        public String getShop_face() {
            return shop_face;
        }

        public void setShop_face(String shop_face) {
            this.shop_face = shop_face;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getPlay_hdl_url() {
            return play_hdl_url;
        }

        public void setPlay_hdl_url(String play_hdl_url) {
            this.play_hdl_url = play_hdl_url;
        }

        public String getPlay_rtmp_url() {
            return play_rtmp_url;
        }

        public void setPlay_rtmp_url(String play_rtmp_url) {
            this.play_rtmp_url = play_rtmp_url;
        }

        public String getPlay_hls_url() {
            return play_hls_url;
        }

        public void setPlay_hls_url(String play_hls_url) {
            this.play_hls_url = play_hls_url;
        }

        public String getStatus_t() {
            return status_t;
        }

        public void setStatus_t(String status_t) {
            this.status_t = status_t;
        }
    }
}
