package com.common.retrofit.entity.result;

/**
 *  集合类实体类
 * Created by jc on 2018/11/19.
 */
public class ListDataEntity<T> {
    private T list;

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }
}
