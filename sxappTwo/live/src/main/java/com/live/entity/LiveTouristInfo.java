package com.live.entity;

/**
 * Created by Administrator on 2016/10/17.
 */
public class LiveTouristInfo {


    /**
     * ApiUrl : /Api/My/userDetail.html
     * Code : 0
     * Data : {"age":"3","area":"330100","area_name":"杭州市","attention":"5","face":"http://139.196.214.241:8090/Uploads/Picture/2016-10-11/57fc791aede52.png","follow":"3","gender":"1","give_diamonds_num":"1000","hx_username":"5bab3e843dd09784241d483c06ca1af8","is_auth":1,"is_black":0,"is_follow":0,"level":"12","liveno":"100000006","nickname":"王远庆1","no_follow_send":"1","occupation":"it男111","receive_ticket_num":"4892","signature":"直播我呀","uid":"6"}
     * Msg : 请求成功
     * Time : 2016-10-17 12:01:36
     */

    private String ApiUrl;
    private int Code;
    /**
     * age : 3
     * area : 330100
     * area_name : 杭州市
     * attention : 5
     * face : http://139.196.214.241:8090/Uploads/Picture/2016-10-11/57fc791aede52.png
     * follow : 3
     * gender : 1
     * give_diamonds_num : 1000
     * hx_username : 5bab3e843dd09784241d483c06ca1af8
     * is_auth : 1
     * is_black : 0
     * is_follow : 0
     * level : 12
     * liveno : 100000006
     * nickname : 王远庆1
     * no_follow_send : 1
     * occupation : it男111
     * receive_ticket_num : 4892
     * signature : 直播我呀
     * uid : 6
     */

    private DataBean Data;
    private String Msg;
    private String Time;

    public String getApiUrl() {
        return ApiUrl;
    }

    public void setApiUrl(String ApiUrl) {
        this.ApiUrl = ApiUrl;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
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

    public static class DataBean {
        private String age;
        private String area;
        private String area_name;
        private String attention;
        private String face;
        private String follow;
        private String gender;
        private String give_diamonds_num;
        private String hx_username;
        private int is_auth;
        private int is_black;
        private int is_follow;
        private String level;
        private String liveno;
        private String nickname;
        private String no_follow_send;
        private String occupation;
        private String receive_ticket_num;
        private String signature;
        private String uid;

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
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

        public String getAttention() {
            return attention;
        }

        public void setAttention(String attention) {
            this.attention = attention;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getFollow() {
            return follow;
        }

        public void setFollow(String follow) {
            this.follow = follow;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getGive_diamonds_num() {
            return give_diamonds_num;
        }

        public void setGive_diamonds_num(String give_diamonds_num) {
            this.give_diamonds_num = give_diamonds_num;
        }

        public String getHx_username() {
            return hx_username;
        }

        public void setHx_username(String hx_username) {
            this.hx_username = hx_username;
        }

        public int getIs_auth() {
            return is_auth;
        }

        public void setIs_auth(int is_auth) {
            this.is_auth = is_auth;
        }

        public int getIs_black() {
            return is_black;
        }

        public void setIs_black(int is_black) {
            this.is_black = is_black;
        }

        public int getIs_follow() {
            return is_follow;
        }

        public void setIs_follow(int is_follow) {
            this.is_follow = is_follow;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLiveno() {
            return liveno;
        }

        public void setLiveno(String liveno) {
            this.liveno = liveno;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getNo_follow_send() {
            return no_follow_send;
        }

        public void setNo_follow_send(String no_follow_send) {
            this.no_follow_send = no_follow_send;
        }

        public String getOccupation() {
            return occupation;
        }

        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }

        public String getReceive_ticket_num() {
            return receive_ticket_num;
        }

        public void setReceive_ticket_num(String receive_ticket_num) {
            this.receive_ticket_num = receive_ticket_num;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}
