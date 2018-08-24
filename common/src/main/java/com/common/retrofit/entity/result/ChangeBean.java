package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/6/11.
 */

public class ChangeBean {

    /**
     * shop_price : [{"id":"1","num":"4","price":"4.00"},{"id":"2","num":"8","price":"8.00"},{"id":"3","num":"12","price":"12.00"},{"id":"4","num":"16","price":"16.00"},{"id":"5","num":"20","price":"20.00"},{"id":"6","num":"24","price":"24.00"},{"id":"7","num":"28","price":"28.00"},{"id":"8","num":"32","price":"32.00"}]
     * xinlidou : 500.000
     * xianglidou : 600.000
     */

    private String xinlidou;
    private String xianglidou;
    private List<ShopPriceBean> shop_price;

    public String getXinlidou() {
        return xinlidou;
    }

    public void setXinlidou(String xinlidou) {
        this.xinlidou = xinlidou;
    }

    public String getXianglidou() {
        return xianglidou;
    }

    public void setXianglidou(String xianglidou) {
        this.xianglidou = xianglidou;
    }

    public List<ShopPriceBean> getShop_price() {
        return shop_price;
    }

    public void setShop_price(List<ShopPriceBean> shop_price) {
        this.shop_price = shop_price;
    }

    public static class ShopPriceBean {
        /**
         * id : 1
         * num : 4
         * price : 4.00
         */

        private String id;
        private String num;
        private String price;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
