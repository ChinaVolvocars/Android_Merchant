package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/4/23.
 */

public class QRDDBean {

    /**
     * address : {"id":1,"person":"田立龙","tel":"13735488245","address":"浙江省杭州市江干区下沙路和达高科生命科技园14F"}
     * goods_list : [{"shop_id":2,"shop_name":"哈哈工厂","lists":[{"goods_id":1,"goods_name":"新款卫衣","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","color":"红色","size":"S码","num":60,"price":"100.00","money":6000},{"goods_id":2,"goods_name":"新款卫衣","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","color":"红色","size":"S码","num":1,"price":"120.00","money":120},{"goods_id":3,"goods_name":"新款卫衣","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","color":"红色","size":"S码","num":1,"price":"120.00","money":120}]}]
     * goods_num : 62
     * total_price : 6240
     */

    private AddressBean address;
    private int goods_num;
    private String total_price;
    private List<GoodsListBean> goods_list;

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public int getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(int goods_num) {
        this.goods_num = goods_num;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class AddressBean {
        /**
         * id : 1
         * person : 田立龙
         * tel : 13735488245
         * address : 浙江省杭州市江干区下沙路和达高科生命科技园14F
         */

        private int id;
        private String person;
        private String tel;
        private String address;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPerson() {
            return person;
        }

        public void setPerson(String person) {
            this.person = person;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public static class GoodsListBean {
        /**
         * shop_id : 2
         * shop_name : 哈哈工厂
         * lists : [{"goods_id":1,"goods_name":"新款卫衣","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","color":"红色","size":"S码","num":60,"price":"100.00","money":6000},{"goods_id":2,"goods_name":"新款卫衣","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","color":"红色","size":"S码","num":1,"price":"120.00","money":120},{"goods_id":3,"goods_name":"新款卫衣","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","color":"红色","size":"S码","num":1,"price":"120.00","money":120}]
         */

        private int shop_id;
        private String shop_name;
        private List<ListsBean> lists;

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public List<ListsBean> getLists() {
            return lists;
        }

        public void setLists(List<ListsBean> lists) {
            this.lists = lists;
        }

        public static class ListsBean {
            /**
             * goods_id : 1
             * goods_name : 新款卫衣
             * goods_img : http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg
             * color : 红色
             * size : S码
             * num : 60
             * price : 100.00
             * money : 6000
             */

            private int goods_id;
            private String goods_name;
            private String goods_img;
            private String color;
            private String size;
            private int num;
            private String price;
            private double money;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_img() {
                return goods_img;
            }

            public void setGoods_img(String goods_img) {
                this.goods_img = goods_img;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }
        }
    }
}
