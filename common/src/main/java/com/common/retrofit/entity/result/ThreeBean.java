package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/4/25.
 */

public class ThreeBean {

    /**
     * total : 3
     * page : 1
     * limit : 10
     * remainder : 0
     * lists : [{"shop_id":8,"shop_face":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/1523934736427_song.jpg","title":"5666","new_time1":"未上新","sold_num":0,"month_update":0,"address":"北京·北京市·东城区·就看看","is_fcous":0,"goods":[]},{"shop_id":6,"shop_face":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523608309.png","title":"东华医院","new_time1":"未上新","sold_num":0,"month_update":0,"address":"北京·北京市·东城区·文化路","is_fcous":0,"goods":[]},{"shop_id":5,"shop_face":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523607548.png","title":"刺杀","new_time1":"未上新","sold_num":0,"month_update":0,"address":"北京·北京市·东城区·文源路","is_fcous":0,"goods":[]}]
     * category : [{"category_id":1,"title":"女装"},{"category_id":3,"title":"毛呢外套"},{"category_id":5,"title":"男装1"}]
     * ad : [{"img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/1523259151524_song.jpg","atype":1,"urls":""}]
     */

    private int total;
    private int page;
    private int limit;
    private int remainder;
    private List<ListsBean> lists;
    private List<CategoryBean> category;
    private List<AdBean> ad;

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

    public List<CategoryBean> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryBean> category) {
        this.category = category;
    }

    public List<AdBean> getAd() {
        return ad;
    }

    public void setAd(List<AdBean> ad) {
        this.ad = ad;
    }

    public static class ListsBean {
        /**
         * shop_id : 8
         * shop_face : http://p6e3vd3f7.bkt.clouddn.com/APP/File/1523934736427_song.jpg
         * title : 5666
         * new_time1 : 未上新
         * sold_num : 0
         * month_update : 0
         * address : 北京·北京市·东城区·就看看
         * is_fcous : 0
         * goods : []
         */

        private int shop_id;
        private String shop_face;
        private String title;
        private String new_time1;
        private int sold_num;
        private int month_update;
        private String address;
        private int is_fcous;
        private List<GoodsBean> goods;

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public String getShop_face() {
            return shop_face;
        }

        public void setShop_face(String shop_face) {
            this.shop_face = shop_face;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNew_time1() {
            return new_time1;
        }

        public void setNew_time1(String new_time1) {
            this.new_time1 = new_time1;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getIs_fcous() {
            return is_fcous;
        }

        public void setIs_fcous(int is_fcous) {
            this.is_fcous = is_fcous;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }
        public static  class GoodsBean{
            private int goods_id;
            private String title;
            private String goods_img;
            private double retail_price;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
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

            public double getRetail_price() {
                return retail_price;
            }

            public void setRetail_price(double retail_price) {
                this.retail_price = retail_price;
            }
        }
    }
    public static class CategoryBean {
        /**
         * category_id : 1
         * title : 女装
         */

        private int category_id;
        private String title;

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class AdBean {
        /**
         * img : http://p6e3vd3f7.bkt.clouddn.com/APP/File/1523259151524_song.jpg
         * atype : 1
         * urls :
         */

        private String img;
        private int atype;
        private String urls;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getAtype() {
            return atype;
        }

        public void setAtype(int atype) {
            this.atype = atype;
        }

        public String getUrls() {
            return urls;
        }

        public void setUrls(String urls) {
            this.urls = urls;
        }
    }
}
