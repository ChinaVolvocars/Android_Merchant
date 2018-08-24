package com.common.retrofit.entity.resultImpl;

import com.common.utils.StringUtils;

import java.util.List;

/**
 * Created by leo on 2017/6/10.
 * 关注列表
 */
public class HttpListBean<T> {


    /**
     * list :
     * total : 5
     * remainder : 0
     * page : 1
     */

    private T list;
    private T show;
    private int total;
    private int remainder;
    private int page;

    private String all_time;
    private String total_money;
    private List<String> img;
    private List<String> url;

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public String getAll_time() {
        return all_time;
    }

    public void setAll_time(String all_time) {
        this.all_time = all_time;
    }
    public String getTotal_money() {
        return StringUtils.nullToStr(total_money);
    }

    public void setTotal_money(String total_money) {
        this.total_money = total_money;
    }

    public T getList() {
        return list;
    }
    public T getShow() {
        return show;
    }

    public void setShow(T show) {
        this.show = show;
    }
    public void setList(T list) {
        this.list = list;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setRemainder(int remainder) {
        this.remainder = remainder;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public int getRemainder() {
        return remainder;
    }

    public int getPage() {
        return page;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }
}
