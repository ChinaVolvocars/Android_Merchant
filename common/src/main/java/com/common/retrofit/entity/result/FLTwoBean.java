package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/4/24.
 */

public class FLTwoBean {

    /**
     * total : 3
     * page : 1
     * limit : 10
     * remainder : 0
     * lists : [{"id":2,"title":"羽绒服","img":"1"},{"id":3,"title":"毛呢外套","img":"1"},{"id":4,"title":"毛衣","img":"1"}]
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
         * id : 2
         * title : 羽绒服
         * img : 1
         */

        private int id;
        private String title;
        private String img;

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
