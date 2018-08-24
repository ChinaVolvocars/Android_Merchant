package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/4/25.
 */

public class TwoBean {

    /**
     * total : 3
     * page : 1
     * limit : 10
     * remainder : 0
     * lists : [{"id":8,"title":"5666","shop_face":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/1523934736427_song.jpg","read_num":0},{"id":6,"title":"东华医院","shop_face":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523608309.png","read_num":0},{"id":5,"title":"刺杀","shop_face":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523607548.png","read_num":0}]
     * category : [{"id":1,"title":"女装"},{"id":3,"title":"毛呢外套"},{"id":5,"title":"男装1"}]
     * ad : [{"urls":"","img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/1523259128957_song.jpg","atype":1}]
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
         * id : 8
         * title : 5666
         * shop_face : http://p6e3vd3f7.bkt.clouddn.com/APP/File/1523934736427_song.jpg
         * read_num : 0
         */

        private int id;
        private String title;
        private String shop_face;
        private int read_num;

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

        public String getShop_face() {
            return shop_face;
        }

        public void setShop_face(String shop_face) {
            this.shop_face = shop_face;
        }

        public int getRead_num() {
            return read_num;
        }

        public void setRead_num(int read_num) {
            this.read_num = read_num;
        }
    }

    public static class CategoryBean {
        /**
         * id : 1
         * title : 女装
         */

        private int id;
        private String title;

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
    }

    public static class AdBean {
        /**
         * urls :
         * img : http://p6e3vd3f7.bkt.clouddn.com/APP/File/1523259128957_song.jpg
         * atype : 1
         */

        private String urls;
        private String img;
        private int atype;

        public String getUrls() {
            return urls;
        }

        public void setUrls(String urls) {
            this.urls = urls;
        }

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
    }
}
