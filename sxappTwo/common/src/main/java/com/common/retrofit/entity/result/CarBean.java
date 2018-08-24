package com.common.retrofit.entity.result;

/**
 * Created by STH on 2018/4/20.
 */

public class CarBean {
    private String id;
    private String num;

    public CarBean(String id, String num) {
        this.id = id;
        this.num = num;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
