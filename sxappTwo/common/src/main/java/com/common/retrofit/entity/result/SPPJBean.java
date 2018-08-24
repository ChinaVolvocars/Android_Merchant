package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/5/2.
 */

public class SPPJBean {

    /**
     * total : 1
     * page : 1
     * limit : 10
     * remainder : 0
     * lists : [{"id":1,"nickname":"还好","face":"89","create_time":"2018-05-02","order_time":"1970-01-01","attr":"[\"\\u9ed1\\u8272X\\u7801*1\",\"\\u9ec4\\u8272X\\u7801*1\"]","comment":"几点到几点就是","reply":""}]
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
         * nickname : 还好
         * face : 89
         * create_time : 2018-05-02
         * order_time : 1970-01-01
         * attr : ["\u9ed1\u8272X\u7801*1","\u9ec4\u8272X\u7801*1"]
         * comment : 几点到几点就是
         * reply :
         */

        private int id;
        private String nickname;
        private String face;
        private String create_time;
        private String order_time;
        private String attr;
        private String comment;
        private String reply;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getOrder_time() {
            return order_time;
        }

        public void setOrder_time(String order_time) {
            this.order_time = order_time;
        }

        public String getAttr() {
            return attr;
        }

        public void setAttr(String attr) {
            this.attr = attr;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }
    }
}
