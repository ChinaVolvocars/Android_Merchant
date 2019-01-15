package com.common.retrofit.entity.result;

import java.util.List;

/**
 * 消费权限实体类
 * Created by jc on 2019/1/8.
 */
public class ConsumeRightsEntity {
    private ManagerUser user;
    private List<ConsumeRecordItemEntity> list;

    public ManagerUser getUser() {
        return user;
    }

    public void setUser(ManagerUser user) {
        this.user = user;
    }

    public List<ConsumeRecordItemEntity> getList() {
        return list;
    }

    public void setList(List<ConsumeRecordItemEntity> list) {
        this.list = list;
    }


    /**
     * "user": {
     * "id": "155",
     * "username"（管理员账号）: "msg13603605118",
     * "code"（邀请码）: "26PA61",
     * "current_xinlidou": "23.67",
     * "current_xianglidou": "0.00",
     * "xindou"（鑫豆总数）: 23.67
     * },
     */
    public static class ManagerUser {
        private String id;
        private String username;
        private String code;
        private String current_xinlidou;
        private String current_xianglidou;
        private String xindou;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCurrent_xinlidou() {
            return current_xinlidou;
        }

        public void setCurrent_xinlidou(String current_xinlidou) {
            this.current_xinlidou = current_xinlidou;
        }

        public String getCurrent_xianglidou() {
            return current_xianglidou;
        }

        public void setCurrent_xianglidou(String current_xianglidou) {
            this.current_xianglidou = current_xianglidou;
        }

        public String getXindou() {
            return xindou;
        }

        public void setXindou(String xindou) {
            this.xindou = xindou;
        }
    }

    /**
     * "id": "89",
     * "xindou "（鑫豆）: "20",
     * "create_time"（消费时间）: "2019年01月09日 20:22",
     * "type": 1（1.传单 2.线下消费 3.线上消费）,
     * "name"（消费商品名称）: "兑换开鑫传单"
     * "xinlidou": "0.100",
     * "xianglidou": "0.000",
     * "fulidou": "0.000",
     * "total_money"（现金）: "10.10",
     * "buy_type"（购买方式 1.0元秒杀 2.折上再返）: "1",
     * "shop_id": "39",
     * "pay_time": "1537863482",
     * "pay_money"（现金）: "0.00",
     */
    public static class ConsumeRecordItemEntity {
        private String id;
        private String xindou;
        private String create_time;
        private String type;
        private String name;
        private String xinlidou;
        private String xianglidou;
        private String fulidou;
        private String total_money;
        private String buy_type;
        private String shop_id;
        private String pay_time;
        private String pay_money;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getXindou() {
            return xindou;
        }

        public void setXindou(String xindou) {
            this.xindou = xindou;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        /**
         * 消费类型   <br/>
         * 1 为开鑫传单   <br/>
         * 2 为线下消费   <br/>
         * 3 线上消费   <br/>
         *
         * @return
         */
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

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

        public String getFulidou() {
            return fulidou;
        }

        public void setFulidou(String fulidou) {
            this.fulidou = fulidou;
        }

        public String getTotal_money() {
            return total_money;
        }

        public void setTotal_money(String total_money) {
            this.total_money = total_money;
        }

        /**
         * 线上消费类型  <br/>
         * 1为0元秒杀 <br/>
         * 1为折上再返 <br/>
         * @return
         */
        public String getBuy_type() {
            return buy_type;
        }

        public void setBuy_type(String buy_type) {
            this.buy_type = buy_type;
        }

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getPay_money() {
            return pay_money;
        }

        public void setPay_money(String pay_money) {
            this.pay_money = pay_money;
        }

        @Override
        public String toString() {
            return "ConsumeRecordItemEntity{" +
                    "id='" + id + '\'' +
                    ", xindou='" + xindou + '\'' +
                    ", create_time='" + create_time + '\'' +
                    ", type='" + type + '\'' +
                    ", name='" + name + '\'' +
                    ", xinlidou='" + xinlidou + '\'' +
                    ", xianglidou='" + xianglidou + '\'' +
                    ", fulidou='" + fulidou + '\'' +
                    ", total_money='" + total_money + '\'' +
                    ", buy_type='" + buy_type + '\'' +
                    ", shop_id='" + shop_id + '\'' +
                    ", pay_time='" + pay_time + '\'' +
                    ", pay_money='" + pay_money + '\'' +
                    '}';
        }
    }
}
