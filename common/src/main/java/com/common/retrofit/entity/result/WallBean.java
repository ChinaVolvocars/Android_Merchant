package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/5/3.
 */

public class WallBean {

    /**
     * total : 19
     * page : 1
     * limit : 10
     * remainder : 9
     * lists : [{"title":"订单收入","money":"262.00","type":1,"create_time":"2018-05-02"},{"title":"订单收入","money":"262.00","type":1,"create_time":"2018-05-02"},{"title":"订单收入","money":"133.00","type":1,"create_time":"2018-05-02"},{"title":"订单收入","money":"133.00","type":1,"create_time":"2018-05-02"},{"title":"订单收入","money":"133.00","type":1,"create_time":"2018-05-02"},{"title":"订单收入","money":"200.00","type":1,"create_time":"2018-05-02"},{"title":"订单支出","money":"0.00","type":2,"create_time":"2018-04-28"},{"title":"订单支出","money":"0.00","type":2,"create_time":"2018-04-28"},{"title":"订单支出","money":"0.00","type":2,"create_time":"2018-04-28"},{"title":"订单支出","money":"0.00","type":2,"create_time":"2018-04-28"}]
     * money : 1323.00
     */

    private int total;
    private int page;
    private int limit;
    private int remainder;
    private String money;
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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public List<ListsBean> getLists() {
        return lists;
    }

    public void setLists(List<ListsBean> lists) {
        this.lists = lists;
    }

    public static class ListsBean {
        /**
         * title : 订单收入
         * money : 262.00
         * type : 1
         * create_time : 2018-05-02
         */

        private String title;
        private String money;
        private int type;
        private String create_time;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
