package com.common.retrofit.entity.result;

/**
 * 关联会员、关联商家头布局实体类
 * Created by jc on 2019/1/11.
 */
public class RelationShipListHeaderEntity {
    /**
     * "zj_user_count"（关联用户总人数）: 2,
     * "user_num_count"（累计为我赚取鑫豆）: "1.59",
     * "zj_shop_count"（关联商家数）: 27,
     * "shop_num_count"（累计为我赚取）: "12.35",
     */
    private String zj_user_count;
    private String user_num_count;
    private String zj_shop_count;
    private String shop_num_count;

    public String getZj_user_count() {
        return zj_user_count;
    }

    public void setZj_user_count(String zj_user_count) {
        this.zj_user_count = zj_user_count;
    }

    public String getUser_num_count() {
        return user_num_count;
    }

    public void setUser_num_count(String user_num_count) {
        this.user_num_count = user_num_count;
    }

    public String getZj_shop_count() {
        return zj_shop_count;
    }

    public void setZj_shop_count(String zj_shop_count) {
        this.zj_shop_count = zj_shop_count;
    }

    public String getShop_num_count() {
        return shop_num_count;
    }

    public void setShop_num_count(String shop_num_count) {
        this.shop_num_count = shop_num_count;
    }
}
