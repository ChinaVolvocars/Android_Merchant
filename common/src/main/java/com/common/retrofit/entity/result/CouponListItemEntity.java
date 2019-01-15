package com.common.retrofit.entity.result;

/**
 * 验券详情  券头信息
 * Created by jc on 2019/1/11.
 */

public class CouponListItemEntity {
    /**
     * "ticket_number"（券码）: "8Rkc5B5akG2x",
     * "verify_time"（验券时间）: "2019-01-10 15:28",
     * "withdraw_status"（状态 1为待提现 2为提现中 3为已提现）: "3"
     */

    private String ticket_number;
    private String verify_time;
    private String withdraw_status;

    public String getTicket_number() {
        return ticket_number;
    }

    public void setTicket_number(String ticket_number) {
        this.ticket_number = ticket_number;
    }

    public String getVerify_time() {
        return verify_time;
    }

    public void setVerify_time(String verify_time) {
        this.verify_time = verify_time;
    }

    /**
     *  验券状态     <br/>
     *  1 为 待提现    <br/> 2 为 提现中    <br/> 3 为 已提现    <br/>
     * @return
     */
    public String getWithdraw_status() {
        return withdraw_status;
    }

    public void setWithdraw_status(String withdraw_status) {
        this.withdraw_status = withdraw_status;
    }
}
