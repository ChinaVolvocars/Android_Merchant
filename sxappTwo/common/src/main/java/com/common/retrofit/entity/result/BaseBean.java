package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/4/17.
 */

public class BaseBean {
    public BaseBean(int id, String title) {
        this.id = id;
        this.title = title;
    }

    private int id;
    private String title;
    private List<BaseBean> sub;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
