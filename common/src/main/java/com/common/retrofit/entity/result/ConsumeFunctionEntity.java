package com.common.retrofit.entity.result;

/**
 * 消费功能列表条目实体类
 * Created by jc on 2019/1/8.
 */
public class ConsumeFunctionEntity {

    /**
     *  "our_shop（本店消费）": "1（1.开启 2.关闭）",
     *  "other_shop（他店消费）": "1（1.开启 2.关闭）",
     *  "mall（商城消费）": "1（1.开启 2.关闭）",
     *  "leaflet（开鑫传单）": "1（1.开启 2.关闭）"
     */
    private String our_shop;
    private String other_shop;
    private String mall;
    private String leaflet;

    /**
     * 本店消费  <br/>
     * 1.开启  <br/>2.关闭   <br/>
     * @return
     */
    public String getOur_shop() {
        return our_shop;
    }

    public void setOur_shop(String our_shop) {
        this.our_shop = our_shop;
    }

    /**
     * 他店消费  <br/>
     * 1.开启  <br/>2.关闭   <br/>
     * @return
     */
    public String getOther_shop() {
        return other_shop;
    }

    public void setOther_shop(String other_shop) {
        this.other_shop = other_shop;
    }

    /**
     * 商城消费  <br/>
     * 1.开启  <br/>2.关闭   <br/>
     * @return
     */
    public String getMall() {
        return mall;
    }

    public void setMall(String mall) {
        this.mall = mall;
    }

    /**
     * 开鑫传单  <br/>
     * 1. 开启  <br/>2. 关闭   <br/>
     * @return
     */
    public String getLeaflet() {
        return leaflet;
    }

    public void setLeaflet(String leaflet) {
        this.leaflet = leaflet;
    }

    @Override
    public String toString() {
        return "ConsumeFunctionEntity{" +
                "our_shop='" + our_shop + '\'' +
                ", other_shop='" + other_shop + '\'' +
                ", mall='" + mall + '\'' +
                ", leaflet='" + leaflet + '\'' +
                '}';
    }
}
