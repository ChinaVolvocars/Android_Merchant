package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/5/3.
 */

public class SCListBean {

    /**
     * total : 1
     * page : 1
     * limit : 10
     * remainder : 0
     * lists : [{"goods_id":21,"goods_name":"睡觉就睡觉","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/1524731008961_song.jpg","retail_price":"133.00"}]
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
         * goods_id : 21
         * goods_name : 睡觉就睡觉
         * goods_img : http://p6e3vd3f7.bkt.clouddn.com/APP/File/1524731008961_song.jpg
         * retail_price : 133.00
         */

        private int goods_id;
        private String goods_name;
        private String goods_img;
        private String retail_price;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public String getRetail_price() {
            return retail_price;
        }

        public void setRetail_price(String retail_price) {
            this.retail_price = retail_price;
        }
    }
}
