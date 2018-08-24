package com.common.retrofit.entity.result;

/**
 * Created by STH on 2018/4/16.
 */

public class ZBDetil {

    /**
     * hx_room_id : 46182437027842
     * push_url : rtmp://pili-publish.xinmiaokeji.net/xinmiaozhibo2/20180411181004-5?expire=1523942709&token=GqLRXGfuV7_4AF2_eM2XzkTwoAw=
     * shop_name : 嘿嘿网批店
     * shop_face : http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg
     * shop_id : 3
     */

    private String hx_room_id;
    private String push_url;
    private String shop_name;
    private String shop_face;
    private String shop_id;

    public String getHx_room_id() {
        return hx_room_id;
    }

    public void setHx_room_id(String hx_room_id) {
        this.hx_room_id = hx_room_id;
    }

    public String getPush_url() {
        return push_url;
    }

    public void setPush_url(String push_url) {
        this.push_url = push_url;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_face() {
        return shop_face;
    }

    public void setShop_face(String shop_face) {
        this.shop_face = shop_face;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }
}
