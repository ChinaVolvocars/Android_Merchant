package com.common.utils;

/**
 * Created by STH on 2018/6/9.
 */

public class BankFormBean {
    private long color;
    private int ima;

    public BankFormBean(long color, int ima) {
        this.color = color;
        this.ima = ima;
    }


    public BankFormBean() {
    }

    public long getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getIma() {
        return ima;
    }

    public void setIma(int ima) {
        this.ima = ima;
    }
}
