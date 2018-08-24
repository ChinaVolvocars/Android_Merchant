package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/4/20.
 */

public class ShopCarBean {

    /**
     * total : 6
     * page : 1
     * limit : 10
     * remainder : 0
     * lists : [{"shop_name":"吉祥如意","goodsList":[{"car_id":6,"goods_name":"南极人","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523697255.png","color":"黑色","size":"S码","num":1,"car_price":"566.00","retail_price":"566.00","whole_price":"224.00","goods_id":6},{"car_id":4,"goods_name":"七匹狼","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523700362.png","color":"红色","size":"S码","num":3,"car_price":"369.00","retail_price":"123.00","whole_price":"100.00","goods_id":7}],"shop_id":4},{"shop_name":"嘿嘿网批店","goodsList":[{"car_id":5,"goods_name":"宋文腾","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","color":"红色","size":"S码","num":1,"car_price":"200.00","retail_price":"200.00","whole_price":"200.00","goods_id":8}],"shop_id":3},{"shop_name":"哈哈工厂","goodsList":[{"car_id":3,"goods_name":"新款卫衣","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","color":"红色","size":"S码","num":1,"car_price":"120.00","retail_price":"120.00","whole_price":"100.00","goods_id":3},{"car_id":2,"goods_name":"新款卫衣","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","color":"红色","size":"S码","num":1,"car_price":"120.00","retail_price":"120.00","whole_price":"100.00","goods_id":2},{"car_id":1,"goods_name":"新款卫衣","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","color":"红色","size":"S码","num":9,"car_price":"900.00","retail_price":"120.00","whole_price":"100.00","goods_id":1}],"shop_id":2}]
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
         * shop_name : 吉祥如意
         * goodsList : [{"car_id":6,"goods_name":"南极人","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523697255.png","color":"黑色","size":"S码","num":1,"car_price":"566.00","retail_price":"566.00","whole_price":"224.00","goods_id":6},{"car_id":4,"goods_name":"七匹狼","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523700362.png","color":"红色","size":"S码","num":3,"car_price":"369.00","retail_price":"123.00","whole_price":"100.00","goods_id":7}]
         * shop_id : 4
         */

        private String shop_name;
        private int shop_id;
        private List<GoodsListBean> goodsList;

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public List<GoodsListBean> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsListBean> goodsList) {
            this.goodsList = goodsList;
        }

        public static class GoodsListBean {
            /**
             * car_id : 6
             * goods_name : 南极人
             * goods_img : http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523697255.png
             * color : 黑色
             * size : S码
             * num : 1
             * car_price : 566.00
             * retail_price : 566.00
             * whole_price : 224.00
             * goods_id : 6
             */

            private int car_id;
            private String goods_name;
            private String goods_img;
            private String color;
            private String size;
            private int num;
            private String car_price;
            private String retail_price;
            private String whole_price;
            private int goods_id;

            public int getCar_id() {
                return car_id;
            }

            public void setCar_id(int car_id) {
                this.car_id = car_id;
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

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getCar_price() {
                return car_price;
            }

            public void setCar_price(String car_price) {
                this.car_price = car_price;
            }

            public String getRetail_price() {
                return retail_price;
            }

            public void setRetail_price(String retail_price) {
                this.retail_price = retail_price;
            }

            public String getWhole_price() {
                return whole_price;
            }

            public void setWhole_price(String whole_price) {
                this.whole_price = whole_price;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }
        }
    }
}
