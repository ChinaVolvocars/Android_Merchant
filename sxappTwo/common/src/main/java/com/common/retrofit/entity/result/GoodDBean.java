package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/4/25.
 */

public class GoodDBean {

    /**
     * total : 0
     * page : 1
     * limit : 10
     * remainder : 0
     * lists : [{"goods_id":0,"goods_img":"aaa","retail_price":1,"title":"aaa"}]
     * shopInfo : {"shop_face":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","shop_name":"嘻嘻实体店·杭州市","notice":"","is_fav":0,"shop_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg"}
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
         * shop_face : http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg
         * shop_name : 嘻嘻实体店·杭州市
         * notice :
         * is_fav : 0
         * shop_img : http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg
         */

        private String shop_face;
        private String shop_name;
        private String notice;
        private int is_fav;
        private String shop_img;

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

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public int getIs_fav() {
            return is_fav;
        }

        public void setIs_fav(int is_fav) {
            this.is_fav = is_fav;
        }

        public String getShop_img() {
            return shop_img;
        }

        public void setShop_img(String shop_img) {
            this.shop_img = shop_img;
        }
    }

    public static class ListsBean {
        /**
         * goods_id : 0
         * goods_img : aaa
         * retail_price : 1.0
         * title : aaa
         */

        private int goods_id;
        private String goods_img;
        private double retail_price;
        private String title;

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

        public double getRetail_price() {
            return retail_price;
        }

        public void setRetail_price(double retail_price) {
            this.retail_price = retail_price;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
