package com.common.retrofit.entity.result;

import java.util.List;

/**
 * 验券详情
 * Created by jc on 2019/1/11.
 */
public class CheckTicketsDetailsEntity {
    /**
     * "count"（总笔数）: "4",
     * "sum"（总价格）: "40.00",
     */
    private String count;
    private String sum;
    private CouponInfoEntity coupon_info;
    private List<CouponListItemEntity> coupon_list;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public CouponInfoEntity getCoupon_info() {
        return coupon_info;
    }

    public void setCoupon_info(CouponInfoEntity coupon_info) {
        this.coupon_info = coupon_info;
    }

    public List<CouponListItemEntity> getCoupon_list() {
        return coupon_list;
    }

    public void setCoupon_list(List<CouponListItemEntity> coupon_list) {
        this.coupon_list = coupon_list;
    }
}
