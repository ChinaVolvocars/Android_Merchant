package com.common.retrofit.entity.result;

import java.util.List;

/**
 *  商家验券记录实体类  <br/>
 * Created by jc on 2019/1/8.
 */
public class CheckRecordEntity {

    private String current_coupon_num;
    private String time;
    private String num;
    private String count_price;
    private List<RecordItemEntity> list;
    /**
     *  券码累计可提现
     * @return
     */
    public String getCurrent_coupon_num() {
        return current_coupon_num;
    }

    public void setCurrent_coupon_num(String current_coupon_num) {
        this.current_coupon_num = current_coupon_num;
    }

    /**
     *  日期
     * @return
     */
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    /**
     *  共几笔
     * @return
     */
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    /**
     *  合计
     * @return
     */
    public String getCount_price() {
        return count_price;
    }

    public void setCount_price(String count_price) {
        this.count_price = count_price;
    }

    public List<RecordItemEntity> getList() {
        return list;
    }

    public void setList(List<RecordItemEntity> list) {
        this.list = list;
    }

    public static class RecordItemEntity{
        private String id;
        private String name;
        private String protocol_price;
        private String verify_time;
        private String ticket_number;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        /**
         *  券名称
         * @return
         */
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        /**
         *  券价格
         * @return
         */
        public String getProtocol_price() {
            return protocol_price;
        }

        public void setProtocol_price(String protocol_price) {
            this.protocol_price = protocol_price;
        }

        /**
         *  验券时间
         * @return
         */
        public String getVerify_time() {
            return verify_time;
        }

        public void setVerify_time(String verify_time) {
            this.verify_time = verify_time;
        }

        /**
         *  券码
         * @return
         */
        public String getTicket_number() {
            return ticket_number;
        }

        public void setTicket_number(String ticket_number) {
            this.ticket_number = ticket_number;
        }

        @Override
        public String toString() {
            return "RecordItemEntity{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", protocol_price='" + protocol_price + '\'' +
                    ", verify_time='" + verify_time + '\'' +
                    ", ticket_number='" + ticket_number + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CheckRecordEntity{" +
                "current_coupon_num='" + current_coupon_num + '\'' +
                ", time='" + time + '\'' +
                ", num='" + num + '\'' +
                ", count_price='" + count_price + '\'' +
                ", list=" + list +
                '}';
    }
}
