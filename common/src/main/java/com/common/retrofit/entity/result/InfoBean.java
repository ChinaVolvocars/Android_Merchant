package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/6/11.
 */

public class InfoBean {

    /**
     * "shop_name"（商家名称）: "一扫光下沙和达城店",
     * "category_id"（分类id）: "10002",
     * "category_name"（分类名称）: "零食",
     * "province"（省id）: "933",
     * "province_name"（省名称）: "浙江省",
     * "area"（市id）: "934",
     * "area_name"（市名称）: "杭州市",
     * "county"（区id）: "937",
     * "county_name"（区名称）: "江干区",
     * "address"（详细地址）: "2号大街1021号",
     * "desc"（店家介绍）: "主营休闲零食，零食礼盒装",
     * "face"（门店照片url）: "http://sxpic.oss-cn-hangzhou.aliyuncs.com/file/2018-09-10/%E9%A6%96%E9%A1%B5%E5%BA%97%E5%A4%B4.png",
     * "shop_pic_num"（照片数量）: 4,
     * "shop_pic"（照片）:
     * "mobile"（商家营业电话）:
     */
    private String shop_name;
    private String category_id;
    private String category_name;
    private String province;
    private String province_name;
    private String area;
    private String area_name;
    private String county;
    private String longitude;
    private String mobile;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    private String latitude;
    private String county_name;
    private String address;
    private String desc;
    private String face;
    private int shop_pic_num;
    private List<ShopPicBean> shop_pic;

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCounty_name() {
        return county_name;
    }

    public void setCounty_name(String county_name) {
        this.county_name = county_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public int getShop_pic_num() {
        return shop_pic_num;
    }

    public void setShop_pic_num(int shop_pic_num) {
        this.shop_pic_num = shop_pic_num;
    }

    public List<ShopPicBean> getShop_pic() {
        return shop_pic;
    }

    public void setShop_pic(List<ShopPicBean> shop_pic) {
        this.shop_pic = shop_pic;
    }

    /**
     *  商家营业电话
     * @return
     */
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public static class ShopPicBean {
        /**
         * id : 21
         * shop_id : 35
         * shop_pic : 图片一
         * shop_description : http://xmap1712132.php.hzxmnet.com/Uploads/Picture/2018-02-27/5a951e252e034.JPEG
         */

        private String id;
        private String shop_id;
        private String shop_pic;
        private String shop_description;

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

        @Override
        public String toString() {
            return "ShopPicBean{" +
                    "id='" + id + '\'' +
                    ", shop_id='" + shop_id + '\'' +
                    ", shop_pic='" + shop_pic + '\'' +
                    ", shop_description='" + shop_description + '\'' +
                    '}';
        }
    }
}
