package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/4/26.
 */

public class ZBDetilBean {

    /**
     * total : 1
     * page : 1
     * limit : 10
     * remainder : 0
     * lists : [{"goods_id":7,"goods_name":"万一","goods_img":"","price":"129.00"}]
     * liveInfo : {"id":7,"shop_name":"嘿嘿网批店","shop_face":"http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg","start_time":"04-26 13时"}
     * shareInfo : {"title":"嘿嘿网批店开始直播了","img":"http://p6e3vd3f7.bkt.clouddn.com/APP/File/1524721331050_song.jpg","url":"http://baidu.com","content":"赶紧过来围观吧！"}
     */

    private int total;
    private int page;
    private int limit;
    private int remainder;
    private LiveInfoBean liveInfo;
    private ShareInfoBean shareInfo;
    private List<ListsBean> lists;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getRemainder() {
        return remainder;
    }

    public void setRemainder(int remainder) {
        this.remainder = remainder;
    }

    public LiveInfoBean getLiveInfo() {
        return liveInfo;
    }

    public void setLiveInfo(LiveInfoBean liveInfo) {
        this.liveInfo = liveInfo;
    }

    public ShareInfoBean getShareInfo() {
        return shareInfo;
    }

    public void setShareInfo(ShareInfoBean shareInfo) {
        this.shareInfo = shareInfo;
    }

    public List<ListsBean> getLists() {
        return lists;
    }

    public void setLists(List<ListsBean> lists) {
        this.lists = lists;
    }

    public static class LiveInfoBean {
        /**
         * id : 7
         * shop_name : 嘿嘿网批店
         * shop_face : http://p6e3vd3f7.bkt.clouddn.com/Admin/2018-03-30/bb059b996062f7f10658d21d4929173f.jpeg
         * start_time : 04-26 13时
         */

        private int id;

        public int getIs_focus() {
            return is_focus;
        }

        public void setIs_focus(int is_focus) {
            this.is_focus = is_focus;
        }

        private int is_focus;
        private String shop_name;
        private String shop_face;
        private String start_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }
    }

    public static class ShareInfoBean {
        /**
         * title : 嘿嘿网批店开始直播了
         * img : http://p6e3vd3f7.bkt.clouddn.com/APP/File/1524721331050_song.jpg
         * url : http://baidu.com
         * content : 赶紧过来围观吧！
         */

        private String title;
        private String img;
        private String url;
        private String content;

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class ListsBean {
        /**
         * goods_id : 7
         * goods_name : 万一
         * goods_img :
         * price : 129.00
         */

        private int goods_id;
        private String goods_name;
        private String goods_img;
        private String price;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
