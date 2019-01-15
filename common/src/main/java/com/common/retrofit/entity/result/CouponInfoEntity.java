package com.common.retrofit.entity.result;

/**
 *  验券详情  券头信息
 * Created by jc on 2019/1/11.
 */

public class CouponInfoEntity {

    /**
     *" id": "16",
     * "name"（券名字）: "总自取",
     * "protocol_price"（协议价）: "10.00",
     * "start_time"（开始日期）: "2019.01.07",
     * "end_time"（结束日期）: "2019.01.31",
     * "shop_id": "39"
     */

    private String id;
    private String name;
    private String protocol_price;
    private String start_time;
    private String end_time;
    private String shop_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProtocol_price() {
        return protocol_price;
    }

    public void setProtocol_price(String protocol_price) {
        this.protocol_price = protocol_price;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }
}
