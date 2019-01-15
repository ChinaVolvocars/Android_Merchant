package com.common.retrofit.entity.result;

import java.util.List;

/**
 * 申请记录 （代收代付/发票提现）实体类 <br/>
 * Created by jc on 2018/11/18.
 */
public class ApplyRecodEntity {

    private String img_url;
    private List<RecordEntity> list;

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public List<RecordEntity> getList() {
        return list;
    }

    public void setList(List<RecordEntity> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ApplyRecodEntity{" +
                "img_url='" + img_url + '\'' +
                ", list=" + list +
                '}';
    }

    /**
     *   单条记录实体
     */
    public static class RecordEntity {

        /**
         *   "id"（提现记录id）: "21",
         *   "type"（类型，不用取）: "1",
         *   "create_time"（时间）: "11-14 17:26",
         *   "money"（提现金额）: "80.00",
         *   "status"（状态）（1为对账中 2为对账成功（当为发票提现记录时2为提交发票） 3为对账失败 4 为发票审核中 5为发票审核成功 6为发票审核失败）: "1",
         *   "bill_check"（不用取）: "1"
         */

        private String id;
        private String type;
        private String create_time;
        private String money;
        private String status;
        private String bill_check;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getBill_check() {
            return bill_check;
        }

        public void setBill_check(String bill_check) {
            this.bill_check = bill_check;
        }

        @Override
        public String toString() {
            return "RecordEntity{" +
                    "id='" + id + '\'' +
                    ", type='" + type + '\'' +
                    ", create_time='" + create_time + '\'' +
                    ", money='" + money + '\'' +
                    ", status='" + status + '\'' +
                    ", bill_check='" + bill_check + '\'' +
                    '}';
        }
    }

}
