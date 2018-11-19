package com.common.retrofit.entity.result;

import java.util.List;

/**
 * 提现账款
 * Created by jc on 2018/5/28.
 */
public class WithdrawlBillEntity {
    private List<WithdrawlBillItemEntity> list;
    private String agent_business_count;
    private String invoice_count;

    /**
     *  代收代付代收账款  <br/>
     *  当clickType为 2 时取这个值  <br/>
     * @return
     */
    public String getAgent_business_count() {
        return agent_business_count;
    }

    public void setAgent_business_count(String agent_business_count) {
        this.agent_business_count = agent_business_count;
    }

    /**
     *  发票代收账款  <br/>
     *  当clickType为 1 时取这个值  <br/>
     * @return
     */
    public String getInvoice_count() {
        return invoice_count;
    }

    public void setInvoice_count(String invoice_count) {
        this.invoice_count = invoice_count;
    }

    public List<WithdrawlBillItemEntity> getList() {
        return list;
    }

    public void setList(List<WithdrawlBillItemEntity> list) {
        this.list = list;
    }

    public static class WithdrawlBillItemEntity {
        private String create_time;
        private String money;

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

        @Override
        public String toString() {
            return "WithdrawlBillEntity{" +
                    "create_time='" + create_time + '\'' +
                    ", money='" + money + '\'' +
                    '}';
        }
    }

}
