package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/4/20.
 */

public class SPDetilBean {

    /**
     * id : 15
     * title : 数码相机
     * goods_imgs : [{"img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1524221244.mp4?vframe/jpg/offset/1","src":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1524221244.mp4"},{"img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-04-25/bd363ab88c02534c1ff2b7ec080fded0.jpg","src":""}]
     * goods_sn : 1234
     * retail_price : 4555.00
     * whole_price : 4000.00
     * fav_num : 0
     * read_num : 0
     * sold_num : 0
     * desc : 数码相机
     * desc_imgs : [{"id":214,"img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1524221263.png"}]
     * shopInfo : {"shop_id":4,"shop_name":"吉祥如意","shop_face":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523525219.png","address":"北京·北京市·吉祥话","shop_time":"2018-04-12","new_time":"2018-04-28","hx_username":"bfde55e59012dcc706287544654e9f87"}
     * comments : {"comments_num":0,"uid":2,"face":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","comments":"这件衣服很好","nickname":"田立龙"}
     * color : {"red":"\u7ea2\u8272","yellow":"\u9ec4\u8272"}
     * size : {"M":"M\u7801","L":"L\u7801"}
     * shareInfo : {"title":"数码相机","img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-04-25/bd363ab88c02534c1ff2b7ec080fded0.jpg","content":"赶紧购买吧","url":"http://baidu.com"}
     * update_time : 2018-04-20 18:47:45
     * goods_num : 552
     * is_fav : 0
     */

    private int id;
    private String title;
    private String goods_sn;
    private String retail_price;
    private String whole_price;
    private int fav_num;
    private int read_num;
    private int sold_num;
    private String desc;
    private ShopInfoBean shopInfo;
    private CommentsBean comments;
    private String color;
    private String size;
    private ShareInfoBean shareInfo;
    private String update_time;
    private int goods_num;
    private int is_fav;
    private List<GoodsImgsBean> goods_imgs;
    private List<DescImgsBean> desc_imgs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGoods_sn() {
        return goods_sn;
    }

    public void setGoods_sn(String goods_sn) {
        this.goods_sn = goods_sn;
    }

    public String getRetail_price() {
        return retail_price;
    }

    public void setRetail_price(String retail_price) {
        this.retail_price = retail_price;
    }

    public String getWhole_price() {
        return whole_price;
    }

    public void setWhole_price(String whole_price) {
        this.whole_price = whole_price;
    }

    public int getFav_num() {
        return fav_num;
    }

    public void setFav_num(int fav_num) {
        this.fav_num = fav_num;
    }

    public int getRead_num() {
        return read_num;
    }

    public void setRead_num(int read_num) {
        this.read_num = read_num;
    }

    public int getSold_num() {
        return sold_num;
    }

    public void setSold_num(int sold_num) {
        this.sold_num = sold_num;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ShopInfoBean getShopInfo() {
        return shopInfo;
    }

    public void setShopInfo(ShopInfoBean shopInfo) {
        this.shopInfo = shopInfo;
    }

    public CommentsBean getComments() {
        return comments;
    }

    public void setComments(CommentsBean comments) {
        this.comments = comments;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public ShareInfoBean getShareInfo() {
        return shareInfo;
    }

    public void setShareInfo(ShareInfoBean shareInfo) {
        this.shareInfo = shareInfo;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public int getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(int goods_num) {
        this.goods_num = goods_num;
    }

    public int getIs_fav() {
        return is_fav;
    }

    public void setIs_fav(int is_fav) {
        this.is_fav = is_fav;
    }

    public List<GoodsImgsBean> getGoods_imgs() {
        return goods_imgs;
    }

    public void setGoods_imgs(List<GoodsImgsBean> goods_imgs) {
        this.goods_imgs = goods_imgs;
    }

    public List<DescImgsBean> getDesc_imgs() {
        return desc_imgs;
    }

    public void setDesc_imgs(List<DescImgsBean> desc_imgs) {
        this.desc_imgs = desc_imgs;
    }

    public static class ShopInfoBean {
        /**
         * shop_id : 4
         * shop_name : 吉祥如意
         * shop_face : http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523525219.png
         * address : 北京·北京市·吉祥话
         * shop_time : 2018-04-12
         * new_time : 2018-04-28
         * hx_username : bfde55e59012dcc706287544654e9f87
         */

        private int shop_id;

        public int getShop_type() {
            return shop_type;
        }

        public void setShop_type(int shop_type) {
            this.shop_type = shop_type;
        }

        private int shop_type;
        private String shop_name;
        private String shop_face;
        private String address;
        private String shop_time;
        private String new_time;
        private String hx_username;

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getShop_time() {
            return shop_time;
        }

        public void setShop_time(String shop_time) {
            this.shop_time = shop_time;
        }

        public String getNew_time() {
            return new_time;
        }

        public void setNew_time(String new_time) {
            this.new_time = new_time;
        }

        public String getHx_username() {
            return hx_username;
        }

        public void setHx_username(String hx_username) {
            this.hx_username = hx_username;
        }
    }

    public static class CommentsBean {
        /**
         * comments_num : 0
         * uid : 2
         * face : http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg
         * comments : 这件衣服很好
         * nickname : 田立龙
         */

        private int comments_num;
        private int uid;
        private String face;
        private String comments;
        private String nickname;

        public int getComments_num() {
            return comments_num;
        }

        public void setComments_num(int comments_num) {
            this.comments_num = comments_num;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }

    public static class ShareInfoBean {
        /**
         * title : 数码相机
         * img : http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-04-25/bd363ab88c02534c1ff2b7ec080fded0.jpg
         * content : 赶紧购买吧
         * url : http://baidu.com
         */

        private String title;
        private String img;
        private String content;
        private String url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class GoodsImgsBean {
        /**
         * img : http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1524221244.mp4?vframe/jpg/offset/1
         * src : http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1524221244.mp4
         */

        private String img;
        private String src;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }
    }

    public static class DescImgsBean {
        /**
         * id : 214
         * img : http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1524221263.png
         */

        private int id;
        private String img;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
