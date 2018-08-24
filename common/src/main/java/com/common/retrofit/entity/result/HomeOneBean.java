package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/4/24.
 */

public class HomeOneBean {

    /**
     * nav : [{"tag":"recommend","title":"推荐"},{"tag":"new","title":"上新"},{"tag":"video","title":"视频"},{"tag":"heigh","title":"高价"},{"tag":"low","title":"低价"}]
     * live : [{"id":1,"title":"1","status":4,"live_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","shop_id":3,"shop_face":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","shop_name":"嘿嘿网批店","play_hdl_url":"","play_rtmp_url":"","play_hls_url":"","status_t":"正在直播","hx_room_id":"46182437027842","is_pay":2},{"id":2,"title":"1","status":4,"live_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","shop_id":3,"shop_face":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","shop_name":"嘿嘿网批店","play_hdl_url":"","play_rtmp_url":"","play_hls_url":"","status_t":"正在直播","hx_room_id":"46182437027842","is_pay":2},{"id":4,"title":"宋文腾","status":2,"live_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","shop_id":3,"shop_face":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","shop_name":"嘿嘿网批店","play_hdl_url":"http://pili-live-hdl.xinmiaokeji.net/xinmiaozhibo2/20180411181004-5.flv","play_rtmp_url":"rtmp://pili-live-rtmp.xinmiaokeji.net/xinmiaozhibo2/20180411181004-5","play_hls_url":"http://pili-live-hls.xinmiaokeji.net/xinmiaozhibo2/20180411181004-5.m3u8","status_t":"正在直播","hx_room_id":"46182437027842","is_pay":1},{"id":3,"title":"1","status":1,"live_img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","shop_id":3,"shop_face":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","shop_name":"嘿嘿网批店","play_hdl_url":"","play_rtmp_url":"","play_hls_url":"","status_t":"04-11 18时","hx_room_id":"46182437027842","is_pay":2},{"id":5,"title":"七匹狼","status":1,"live_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1524455546.png","shop_id":4,"shop_face":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523525219.png","shop_name":"吉祥如意","play_hdl_url":"","play_rtmp_url":"","play_hls_url":"","status_t":"04-23 13时","hx_room_id":"47245846183937","is_pay":2},{"id":6,"title":"雪中飞","status":1,"live_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1524455613.png","shop_id":4,"shop_face":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523525219.png","shop_name":"吉祥如意","play_hdl_url":"","play_rtmp_url":"","play_hls_url":"","status_t":"04-23 16时","hx_room_id":"47245846183937","is_pay":1}]
     * ad : {"top":[{"img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/33aa48e4e87d94b0098f4484b9884937.jpg","atype":1,"urls":""},{"img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","atype":1,"urls":""}],"center":[{"urls":"","atype":"1","img":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/33aa48e4e87d94b0098f4484b9884937.jpg"}]}
     * category : [{"id":4,"title":"毛衣","goods":[{"id":7,"title":"七匹狼","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523700362.png"}]},{"id":6,"title":"羽绒服","goods":[{"id":7,"title":"七匹狼","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523700362.png"}]},{"id":10,"title":"111","goods":[{"id":7,"title":"七匹狼","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523700362.png"}]}]
     */

    private AdBean ad;
    private List<NavBean> nav;
    private List<LiveBean> live;
    private List<CategoryBean> category;

    public AdBean getAd() {
        return ad;
    }

    public void setAd(AdBean ad) {
        this.ad = ad;
    }

    public List<NavBean> getNav() {
        return nav;
    }

    public void setNav(List<NavBean> nav) {
        this.nav = nav;
    }

    public List<LiveBean> getLive() {
        return live;
    }

    public void setLive(List<LiveBean> live) {
        this.live = live;
    }

    public List<CategoryBean> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryBean> category) {
        this.category = category;
    }

    public static class AdBean {
        private List<TopBean> top;
        private List<CenterBean> center;

        public List<TopBean> getTop() {
            return top;
        }

        public void setTop(List<TopBean> top) {
            this.top = top;
        }

        public List<CenterBean> getCenter() {
            return center;
        }

        public void setCenter(List<CenterBean> center) {
            this.center = center;
        }

        public static class TopBean {
            /**
             * img : http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/33aa48e4e87d94b0098f4484b9884937.jpg
             * atype : 1
             * urls :
             */

            private String img;
            private int atype;
            private String urls;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getAtype() {
                return atype;
            }

            public void setAtype(int atype) {
                this.atype = atype;
            }

            public String getUrls() {
                return urls;
            }

            public void setUrls(String urls) {
                this.urls = urls;
            }
        }

        public static class CenterBean {
            /**
             * urls :
             * atype : 1
             * img : http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/33aa48e4e87d94b0098f4484b9884937.jpg
             */

            private String urls;
            private String atype;
            private String img;

            public String getUrls() {
                return urls;
            }

            public void setUrls(String urls) {
                this.urls = urls;
            }

            public String getAtype() {
                return atype;
            }

            public void setAtype(String atype) {
                this.atype = atype;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }

    public static class NavBean {
        /**
         * tag : recommend
         * title : 推荐
         */

        private String tag;
        private String title;

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class LiveBean {
        /**
         * id : 1
         * title : 1
         * status : 4
         * live_img : http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg
         * shop_id : 3
         * shop_face : http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg
         * shop_name : 嘿嘿网批店
         * play_hdl_url :
         * play_rtmp_url :
         * play_hls_url :
         * status_t : 正在直播
         * hx_room_id : 46182437027842
         * is_pay : 2
         */

        private int id;
        private String title;
        private int status;
        private String live_img;
        private int shop_id;
        private String shop_face;
        private String shop_name;
        private String play_hdl_url;
        private String play_rtmp_url;
        private String play_hls_url;
        private String status_t;
        private String hx_room_id;
        private int is_pay;

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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getLive_img() {
            return live_img;
        }

        public void setLive_img(String live_img) {
            this.live_img = live_img;
        }

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public String getShop_face() {
            return shop_face;
        }

        public void setShop_face(String shop_face) {
            this.shop_face = shop_face;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getPlay_hdl_url() {
            return play_hdl_url;
        }

        public void setPlay_hdl_url(String play_hdl_url) {
            this.play_hdl_url = play_hdl_url;
        }

        public String getPlay_rtmp_url() {
            return play_rtmp_url;
        }

        public void setPlay_rtmp_url(String play_rtmp_url) {
            this.play_rtmp_url = play_rtmp_url;
        }

        public String getPlay_hls_url() {
            return play_hls_url;
        }

        public void setPlay_hls_url(String play_hls_url) {
            this.play_hls_url = play_hls_url;
        }

        public String getStatus_t() {
            return status_t;
        }

        public void setStatus_t(String status_t) {
            this.status_t = status_t;
        }

        public String getHx_room_id() {
            return hx_room_id;
        }

        public void setHx_room_id(String hx_room_id) {
            this.hx_room_id = hx_room_id;
        }

        public int getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(int is_pay) {
            this.is_pay = is_pay;
        }
    }

    public static class CategoryBean {
        /**
         * id : 4
         * title : 毛衣
         * goods : [{"id":7,"title":"七匹狼","goods_img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523700362.png"}]
         */

        private int id;
        private String title;
        private List<GoodsBean> goods;

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

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * id : 7
             * title : 七匹狼
             * goods_img : http://p6e3vd3f7.bkt.clouddn.com/APP/File/ios_1523700362.png
             */

            private int id;
            private String title;
            private String goods_img;

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

            public String getGoods_img() {
                return goods_img;
            }

            public void setGoods_img(String goods_img) {
                this.goods_img = goods_img;
            }
        }
    }
}
