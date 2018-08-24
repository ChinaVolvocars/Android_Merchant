package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/4/25.
 */

public class GoodCTwoBean {

    /**
     * total : 1
     * page : 1
     * limit : 10
     * remainder : 0
     * lists : [{"title":"2018-04-25","goodsList":[{"goods_id":19,"goods_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/1524640690648_song.jpg","title":"手机三脚架","retail_price":"129.00"}]}]
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
         * title : 2018-04-25
         * goodsList : [{"goods_id":19,"goods_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/1524640690648_song.jpg","title":"手机三脚架","retail_price":"129.00"}]
         */

        private String title;
        private List<GoodsListBean> goodsList;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<GoodsListBean> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsListBean> goodsList) {
            this.goodsList = goodsList;
        }

        public static class GoodsListBean {
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
}
