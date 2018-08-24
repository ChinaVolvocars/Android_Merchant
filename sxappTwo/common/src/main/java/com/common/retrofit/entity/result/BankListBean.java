package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/6/9.
 */

public class BankListBean {


    /**
     * list : [{"id":"15","bank_name":"中国建设银行","card_number":"6217000340000565980","card_logo":"ABC","card_bank_logo":"https://s3.pstatp.com/toutiao/static/img/logo.201f80d.png","card_bank_background":"https://s3.pstatp.com/toutiao/static/img/logo.201f80d.png"},{"id":"35","bank_name":"中国农业银行","card_number":"111145345","card_logo":"ABC","card_bank_logo":"https://s3.pstatp.com/toutiao/static/img/logo.201f80d.png","card_bank_background":"https://s3.pstatp.com/toutiao/static/img/logo.201f80d.png"},{"id":"36","bank_name":"中国民生银行","card_number":"111145345324234","card_logo":"CMBC","card_bank_logo":"https://s3.pstatp.com/toutiao/static/img/logo.201f80d.png","card_bank_background":"https://s3.pstatp.com/toutiao/static/img/logo.201f80d.png"}]
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
         * id : 15
         * bank_name : 中国建设银行
         * card_number : 6217000340000565980
         * card_logo : ABC
         * card_bank_logo : https://s3.pstatp.com/toutiao/static/img/logo.201f80d.png
         * card_bank_background : https://s3.pstatp.com/toutiao/static/img/logo.201f80d.png
         */

        private String id;
        private String bank_name;
        private String card_number;
        private String card_logo;
        private String card_bank_logo;
        private String card_bank_background;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
        }

        public String getCard_logo() {
            return card_logo;
        }

        public void setCard_logo(String card_logo) {
            this.card_logo = card_logo;
        }

        public String getCard_bank_logo() {
            return card_bank_logo;
        }

        public void setCard_bank_logo(String card_bank_logo) {
            this.card_bank_logo = card_bank_logo;
        }

        public String getCard_bank_background() {
            return card_bank_background;
        }

        public void setCard_bank_background(String card_bank_background) {
            this.card_bank_background = card_bank_background;
        }
    }
}
