package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/5/3.
 */

public class AboutBean {

    /**
     * total : 1
     * page : 1
     * limit : 10
     * remainder : 0
     * lists : [{"id":1,"shop_name":"嘻嘻实体店","shop_face":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","shop_id":1,"address":""}]
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
         * id : 1
         * shop_name : 嘻嘻实体店
         * shop_face : http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg
         * shop_id : 1
         * address :
         */

        private int id;
        private String shop_name;
        private String shop_face;
        private int shop_id;
        private String address;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getShop_face() {
            return shop_face;
        }

        public void setShop_face(String shop_face) {
            this.shop_face = shop_face;
        }

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
