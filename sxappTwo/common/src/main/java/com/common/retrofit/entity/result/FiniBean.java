package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/6/11.
 */

public class FiniBean {

    /**
     * list : [{"xindou":"100","money":"50","total_money":"200","withdraw_money":"30","create_time":"2018-05-05"},{"xindou":"80","money":"50","total_money":"90","withdraw_money":"10","create_time":"2018-05-05"}]
     * page : 1
     */

    private int page;
    private List<ListBean> list;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * xindou : 100
         * money : 50
         * total_money : 200
         * withdraw_money : 30
         * create_time : 2018-05-05
         */

        private String xindou;
        private String money;
        private String total_money;
        private String withdraw_money;
        private String create_time;

        public String getXindou() {
            return xindou;
        }

        public void setXindou(String xindou) {
            this.xindou = xindou;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getTotal_money() {
            return total_money;
        }

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }

        public String getWithdraw_money() {
            return withdraw_money;
        }

        public void setWithdraw_money(String withdraw_money) {
            this.withdraw_money = withdraw_money;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
