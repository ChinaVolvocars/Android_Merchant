package com.common.retrofit.entity.result;

/**
 *  添加/修改商家店铺图片并上传的实体类
 * Created by STH on 2018/6/11.
 */
public class AddPicNewEntity {
    private String id;
    private String shop_id;
    private String shop_pic;
    private String shop_description;

    public AddPicNewEntity(String id, String shop_id, String shop_pic, String shop_description) {
        this.id = id;
        this.shop_id = shop_id;
        this.shop_pic = shop_pic;
        this.shop_description = shop_description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_pic() {
        return shop_pic;
    }

    public void setShop_pic(String shop_pic) {
        this.shop_pic = shop_pic;
    }

    public String getShop_description() {
        return shop_description;
    }

    public void setShop_description(String shop_description) {
        this.shop_description = shop_description;
    }
}
