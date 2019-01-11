package com.common.retrofit.entity.result;

/**
 * 关联会员、关联商家条目实体类  <br/>
 *  此处将关联商家与关联用户的条目写在一起  <br/>
 *   <br/>
 * Created by jc on 2019/1/11.
 */
public class RelationShipListItemEntity {

    /**
     * "id": "1703",
     * "avatar（用户头像）": "http://sxpic.oss-cn-hangzhou.aliyuncs.com/file/2017-08-08/%E9%BB%98%E8%AE%A4.png",
     * "face"（店面图）: "http://sxpic.oss-cn-hangzhou.aliyuncs.com/file/2018-11-20/1542693375cnptext.jpg",
     * "nickname"（昵称）: "张兆伦",
     * "shop_name"（店名）: "闽台风味小吃",
     * "fx_count"（分享会员人数）: 14,
     * "num"（门店为我赚取）: "2.55",
     * "fx_num"（分享为我赚取）: "2.84",
     * "count_num": 5.39
     */
    private String id;
    private String avatar;
    private String face;
    private String nickname;
    private String shop_name;
    private String fx_count;
    private String num;
    private String fx_num;
    private String count_num;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getFx_count() {
        return fx_count;
    }

    public void setFx_count(String fx_count) {
        this.fx_count = fx_count;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getFx_num() {
        return fx_num;
    }

    public void setFx_num(String fx_num) {
        this.fx_num = fx_num;
    }

    public String getCount_num() {
        return count_num;
    }

    public void setCount_num(String count_num) {
        this.count_num = count_num;
    }
}
