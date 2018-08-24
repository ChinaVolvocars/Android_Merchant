package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/5/3.
 */

public class JFBean {

    /**
     * total : 1
     * page : 1
     * limit : 10
     * remainder : 0
     * lists : [{"title":"签到领积分","score":"2.00","income_type":1,"date_time":"2018-05-03"}]
     * score : 2
     */

    private int total;
    private int page;
    private int limit;
    private int remainder;
    private String score;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public List<ListsBean> getLists() {
        return lists;
    }

    public void setLists(List<ListsBean> lists) {
        this.lists = lists;
    }

    public static class ListsBean {
        /**
         * title : 签到领积分
         * score : 2.00
         * income_type : 1
         * date_time : 2018-05-03
         */

        private String title;
        private String score;
        private int income_type;
        private String date_time;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public int getIncome_type() {
            return income_type;
        }

        public void setIncome_type(int income_type) {
            this.income_type = income_type;
        }

        public String getDate_time() {
            return date_time;
        }

        public void setDate_time(String date_time) {
            this.date_time = date_time;
        }
    }
}
