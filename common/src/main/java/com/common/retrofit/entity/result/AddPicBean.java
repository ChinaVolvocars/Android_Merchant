package com.common.retrofit.entity.result;

/**
 * 添加或者修改图片的实体类
 * Created by STH on 2018/6/11.
 */
public class AddPicBean {
    private String shop_pic;
    private String shop_description;

    public AddPicBean(String shop_pic, String shop_description) {
        this.shop_pic = shop_pic;
        this.shop_description = shop_description;
    }

    public String getShop_pic() {
        return shop_pic;
    }

    public String getShop_description() {
        return shop_description;
    }
}
