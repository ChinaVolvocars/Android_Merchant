package com.common.retrofit.entity.resultImpl;

import java.util.List;

/**
 *  单纯的列表类型实体类
 * Created by jc on 2019/1/8.
 */
public class BaseListEntity<T> {
    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
