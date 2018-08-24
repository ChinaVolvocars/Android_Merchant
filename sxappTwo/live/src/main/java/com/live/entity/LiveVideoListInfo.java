package com.live.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 */
public class LiveVideoListInfo implements Serializable{


    /**
     * Code : 0
     * Msg : 请求成功
     * Time : 2017-01-21 10:17:26
     * ApiUrl : /Api/DirectSeedingRoom/directSeedingList.html
     * Data : {"list":[{"fid":8,"liveno":"1888888","streamid":"z1.xinmiao.dd2d87fc30160f441fbc7cac63fee04f","livestatus":1,"hx_chatroomsid":"263977839001338284","view_count":100,"nickname":"新苗网络","face":"http://139.196.214.241:8090/Uploads/Picture/2016-11-16/582c2b3c34d02.png","county":0,"county_name":"","area":"0","area_name":"","gender":1,"level":1,"position":"","gamename":"炸金花","gameid":"1","video":"rtmp://live.hkstv.hk.lxdns.com/live/hks"}],"total":1,"remainder":0,"page":1}
     */

    private int Code;
    private String Msg;
    private String Time;
    private String ApiUrl;
    private DataBean Data;

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String Msg) {
        this.Msg = Msg;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getApiUrl() {
        return ApiUrl;
    }

    public void setApiUrl(String ApiUrl) {
        this.ApiUrl = ApiUrl;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean implements Serializable{
        /**
         * list : [{"fid":8,"liveno":"1888888","streamid":"z1.xinmiao.dd2d87fc30160f441fbc7cac63fee04f","livestatus":1,"hx_chatroomsid":"263977839001338284","view_count":100,"nickname":"新苗网络","face":"http://139.196.214.241:8090/Uploads/Picture/2016-11-16/582c2b3c34d02.png","county":0,"county_name":"","area":"0","area_name":"","gender":1,"level":1,"position":"","gamename":"炸金花","gameid":"1","video":"rtmp://live.hkstv.hk.lxdns.com/live/hks"}]
         * total : 1
         * remainder : 0
         * page : 1
         */

        private int total;
        private int remainder;
        private int page;
        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getRemainder() {
            return remainder;
        }

        public void setRemainder(int remainder) {
            this.remainder = remainder;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{
            /**
             * fid : 8
             * liveno : 1888888
             * streamid : z1.xinmiao.dd2d87fc30160f441fbc7cac63fee04f
             * livestatus : 1
             * hx_chatroomsid : 263977839001338284
             * view_count : 100
             * nickname : 新苗网络
             * face : http://139.196.214.241:8090/Uploads/Picture/2016-11-16/582c2b3c34d02.png
             * county : 0
             * county_name :
             * area : 0
             * area_name :
             * gender : 1
             * level : 1
             * position :
             * gamename : 炸金花
             * gameid : 1
             * video : rtmp://live.hkstv.hk.lxdns.com/live/hks
             */

            private int fid;
            private String liveno;
            private String streamid;
            private int livestatus;
            private String hx_chatroomsid;
            private int view_count;
            private String nickname;
            private String face;
            private int county;
            private String county_name;
            private String area;
            private String area_name;
            private int gender;
            private int level;
            private String position;
            private String gamename;
            private String gameid;
            private String video;

            public int getFid() {
                return fid;
            }

            public void setFid(int fid) {
                this.fid = fid;
            }

            public String getLiveno() {
                return liveno;
            }

            public void setLiveno(String liveno) {
                this.liveno = liveno;
            }

            public String getStreamid() {
                return streamid;
            }

            public void setStreamid(String streamid) {
                this.streamid = streamid;
            }

            public int getLivestatus() {
                return livestatus;
            }

            public void setLivestatus(int livestatus) {
                this.livestatus = livestatus;
            }

            public String getHx_chatroomsid() {
                return hx_chatroomsid;
            }

            public void setHx_chatroomsid(String hx_chatroomsid) {
                this.hx_chatroomsid = hx_chatroomsid;
            }

            public int getView_count() {
                return view_count;
            }

            public void setView_count(int view_count) {
                this.view_count = view_count;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }

            public int getCounty() {
                return county;
            }

            public void setCounty(int county) {
                this.county = county;
            }

            public String getCounty_name() {
                return county_name;
            }

            public void setCounty_name(String county_name) {
                this.county_name = county_name;
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

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getGamename() {
                return gamename;
            }

            public void setGamename(String gamename) {
                this.gamename = gamename;
            }

            public String getGameid() {
                return gameid;
            }

            public void setGameid(String gameid) {
                this.gameid = gameid;
            }

            public String getVideo() {
                return video;
            }

            public void setVideo(String video) {
                this.video = video;
            }
        }
    }
}
