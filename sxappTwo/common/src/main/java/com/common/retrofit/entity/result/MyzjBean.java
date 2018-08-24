package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/5/3.
 */

public class MyzjBean {

    /**
     * total : 6
     * page : 1
     * limit : 10
     * remainder : 0
     * lists : [{"title":"2018-05-02","goods":[{"goods_id":20,"goods_name":"万一","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/1524721017137_song.jpg","retail_price":"129.00"},{"goods_id":23,"goods_name":"Djdjdjj","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/1525250180913_song.jpg","retail_price":"129.00"},{"goods_id":21,"goods_name":"睡觉就睡觉","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/1524731026131_song.jpg","retail_price":"133.00"},{"goods_id":3,"goods_name":"男鞋","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","retail_price":"120.00"},{"goods_id":2,"goods_name":"毛衣","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","retail_price":"80.00"},{"goods_id":1,"goods_name":"羽绒服","goods_img":"","retail_price":"150.00"}]}]
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
         * title : 2018-05-02
         * goods : [{"goods_id":20,"goods_name":"万一","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/1524721017137_song.jpg","retail_price":"129.00"},{"goods_id":23,"goods_name":"Djdjdjj","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/1525250180913_song.jpg","retail_price":"129.00"},{"goods_id":21,"goods_name":"睡觉就睡觉","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/1524731026131_song.jpg","retail_price":"133.00"},{"goods_id":3,"goods_name":"男鞋","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","retail_price":"120.00"},{"goods_id":2,"goods_name":"毛衣","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","retail_price":"80.00"},{"goods_id":1,"goods_name":"羽绒服","goods_img":"","retail_price":"150.00"}]
         */

        private String title;
        private List<GoodsBean> goods;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * goods_id : 20
             * goods_name : 万一
             * goods_img : http://p6e3vd3f7.bkt.clouddn.com/APP/File/1524721017137_song.jpg
             * retail_price : 129.00
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
}
