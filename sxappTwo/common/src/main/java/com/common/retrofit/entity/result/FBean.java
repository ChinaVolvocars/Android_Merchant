package com.common.retrofit.entity.result;

/**
 * Created by STH on 2018/6/11.
 */

public class FBean {
    private String money;
    private String time;
    private String xindou;

    public String getXindou() {
        return xindou;
    }


    public FBean(String money, String time) {
        this.money = money;
        this.time = time;
    }

    public String getMoney() {

        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
