package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/4/26.
 */

public class MyOrderBean {

    /**
     * total : 5
     * page : 1
     * limit : 10
     * remainder : 0
     * lists : [{"shop_id":3,"shop_name":"嘿嘿网批店","status":1,"order_id":5,"status_t":"待付款","goodsList":[{"goods_name":"宋文腾","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","color":"红色","size":"S码","num":1,"total_price":"200.00","goods_id":9}],"total_price":"200.00"},{"shop_id":4,"shop_name":"吉祥如意","status":1,"order_id":4,"status_t":"待付款","goodsList":[{"goods_name":"七匹狼","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523700362.png","color":"红色","size":"S码","num":3,"total_price":"369.00","goods_id":7},{"goods_name":"南极人","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523697255.png","color":"黑色","size":"S码","num":2,"total_price":"1132.00","goods_id":8}],"total_price":"1501.00"},{"shop_id":3,"shop_name":"嘿嘿网批店","status":1,"order_id":3,"status_t":"待付款","goodsList":[{"goods_name":"宋文腾","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","color":"红色","size":"S码","num":1,"total_price":"200.00","goods_id":6}],"total_price":"200.00"},{"shop_id":4,"shop_name":"吉祥如意","status":1,"order_id":2,"status_t":"待付款","goodsList":[{"goods_name":"七匹狼","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523700362.png","color":"红色","size":"S码","num":3,"total_price":"369.00","goods_id":4},{"goods_name":"南极人","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523697255.png","color":"黑色","size":"S码","num":1,"total_price":"566.00","goods_id":5}],"total_price":"935.00"},{"shop_id":2,"shop_name":"哈哈工厂","status":1,"order_id":1,"status_t":"待付款","goodsList":[{"goods_name":"新款卫衣","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","color":"红色","size":"S码","num":60,"total_price":"6000.00","goods_id":1},{"goods_name":"新款卫衣","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","color":"红色","size":"S码","num":1,"total_price":"120.00","goods_id":2},{"goods_name":"新款卫衣","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","color":"红色","size":"S码","num":1,"total_price":"120.00","goods_id":3}],"total_price":"6240.00"}]
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
         * shop_id : 3
         * shop_name : 嘿嘿网批店
         * status : 1
         * order_id : 5
         * status_t : 待付款
         * goodsList : [{"goods_name":"宋文腾","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","color":"红色","size":"S码","num":1,"total_price":"200.00","goods_id":9}]
         * total_price : 200.00
         */

        private int shop_id;
        private String shop_name;
        private int status;
        private int order_id;
        private String status_t;
        private String total_price;
        private List<GoodsListBean> goodsList;

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getStatus_t() {
            return status_t;
        }

        public void setStatus_t(String status_t) {
            this.status_t = status_t;
        }

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public List<GoodsListBean> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsListBean> goodsList) {
            this.goodsList = goodsList;
        }

        public static class GoodsListBean {
            /**
             * goods_name : 宋文腾
             * goods_img : http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg
             * color : 红色
             * size : S码
             * num : 1
             * total_price : 200.00
             * goods_id : 9
             */

            private String goods_name;
            private String goods_img;
            private String color;
            private String size;
            private int num;
            private String total_price;
            private int goods_id;

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

            public String getTotal_price() {
                return total_price;
            }

            public void setTotal_price(String total_price) {
                this.total_price = total_price;
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
