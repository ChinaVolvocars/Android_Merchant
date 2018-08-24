package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/4/17.
 */

public class SPListBean {

    /**
     * total : 1
     * page : 1
     * limit : 10
     * remainder : 0
     * lists : [{"id":8,"title":"宋文腾","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","retail_price":"200.00"}]
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
         * id : 8
         * title : 宋文腾
         * goods_img : http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg
         * retail_price : 200.00
         */

        private int id;
        private String title;
        private String goods_img;
        private String retail_price;

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
