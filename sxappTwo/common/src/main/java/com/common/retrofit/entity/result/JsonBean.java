package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/4/23.
 */

public class JsonBean
{

    private List<Integer> id;
    private List<CommentsBean> comments;

    public List<Integer> getId() {
        return id;
    }

    public void setId(List<Integer> id) {
        this.id = id;
    }

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public static class CommentsBean {
        /**
         * shop_id : 4
         * comment : 44444
         */

        private int shop_id;
        private String comment;

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }
}
