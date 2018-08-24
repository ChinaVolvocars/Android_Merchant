package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/5/2.
 */

public class PayBeansss {

    /**
     * alipay : app_id=2018041102540366&biz_content=%7B%22body%22%3A%22%E8%AE%A2%E5%8D%95%E6%94%AF%E4%BB%98%22%2C%22subject%22%3A%22%E8%AE%A2%E5%8D%95%E6%94%AF%E4%BB%98%22%2C%22out_trade_no%22%3A%2218050211104823064839105%22%2C%22total_amount%22%3A%220.01%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22goods_type%22%3A%221%22%2C%22passback_params%22%3A%22eyJwYXlEYXRhIjoiNSIsImlzX3NuIjoyLCJvcmRlcl90eXBlIjoxLCJwYXlfdHlwZSI6IjEifQ%22%7D&charset=UTF-8&format=JSON&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fxmap1803013.php.hzxmnet.com%2Fapi%2Fcallback%2FpaySuccess%2Fpay_type%2F1&sign_type=RSA2&timestamp=2018-05-02+11%3A10%3A48&version=1.0&sign=Au0rXZueTqmY%2BKI%2Bt%2BtO0j4ScgyvfIPMESVz3MCM0VHedZt369Vtzlrm2hxAaNF9p0TWyx%2FpSg3OLs4mI5Wc9FXkzb4EkJUpl1IIHHCuw4F9Xd6PMjBv0cUKdnYGmjyN%2Bfxj5hjZ12poIkwZdXaS9zNE8uquwZBhdpEko%2B4wsGYBo9IbwFfoQ9sW%2BL8DegQccUOca08wWlzK%2FkQGe6nJ3f8l4WrqBVaely%2Ff4WRot2ORjumyvMmqWFtnpPuyc%2FcG%2BhztaPhYhrXsvdyJGw8afIgIEup6Fh2sLTzPD%2BI8jssykC9bemY7qtmwE7kYn6D3ZWRIWh%2BaNAIxICASdUMu%2Bg%3D%3D
     * wxpay : []
     */

    private String alipay;
    private List<?> wxpay;

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public List<?> getWxpay() {
        return wxpay;
    }

    public void setWxpay(List<?> wxpay) {
        this.wxpay = wxpay;
    }
}
