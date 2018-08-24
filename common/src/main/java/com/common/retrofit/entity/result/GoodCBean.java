package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/4/25.
 */

public class GoodCBean {

    /**
     * total : 1
     * page : 1
     * limit : 10
     * remainder : 0
     * lists : [{"goods_id":19,"goods_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/1524640690648_song.jpg","title":"手机三脚架","retail_price":"129.00"}]
     * shopInfo : {"shop_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523607554.png","shop_name":"刺杀","shop_face":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523607548.png","is_focus":0,"face":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/1524640793034_song.jpg","nickname":"大口大口","address":"北京·北京市·东城区·文源路","shop_time":"1970-01-01","sold_num":0,"month_update":1,"notice":""}
     */

    private int total;
    private int page;
    private int limit;
    private int remainder;
    private ShopInfoBean shopInfo;
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

    public ShopInfoBean getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(ShopInfoBean shopInfo) {
        this.shopInfo = shopInfo;
    }

    public List<ListsBean> getLists() {
        return lists;
    }

    public void setLists(List<ListsBean> lists) {
        this.lists = lists;
    }

    public static class ShopInfoBean {
        /**
         * shop_img : http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523607554.png
         * shop_name : 刺杀
         * shop_face : http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523607548.png
         * is_focus : 0
         * face : http://p6e3vd3f7.bkt.clouddn.com/APP/File/1524640793034_song.jpg
         * nickname : 大口大口
         * address : 北京·北京市·东城区·文源路
         * shop_time : 1970-01-01
         * sold_num : 0
         * month_update : 1
         * notice :
         */

        private String shop_img;
        private String shop_name;
        private String shop_face;
        private int is_focus;
        private String face;
        private String nickname;
        private String address;
        private String shop_time;
        private int sold_num;
        private int month_update;
        private String notice;

        public String getShop_img() {
            return shop_img;
        }

        public void setShop_img(String shop_img) {
            this.shop_img = shop_img;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getShop_face() {
            return shop_face;
        }

        public void setShop_face(String shop_face) {
            this.shop_face = shop_face;
        }

        public int getIs_focus() {
            return is_focus;
        }

        public void setIs_focus(int is_focus) {
            this.is_focus = is_focus;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getShop_time() {
            return shop_time;
        }

        public void setShop_time(String shop_time) {
            this.shop_time = shop_time;
        }

        public int getSold_num() {
            return sold_num;
        }

        public void setSold_num(int sold_num) {
            this.sold_num = sold_num;
        }

        public int getMonth_update() {
            return month_update;
        }

        public void setMonth_update(int month_update) {
            this.month_update = month_update;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }
    }

    public static class ListsBean {
        /**
         * goods_id : 19
         * goods_img : http://p6e3vd3f7.bkt.clouddn.com/APP/File/1524640690648_song.jpg
         * title : 手机三脚架
         * retail_price : 129.00
         */

        private int goods_id;
        private String goods_img;
        private String title;
        private String retail_price;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getRetail_price() {
            return retail_price;
        }

        public void setRetail_price(String retail_price) {
            this.retail_price = retail_price;
        }
    }
}
