package com.live.entity;

/**
 * Created by Administrator on 2016/10/17.
 */
public class WatchLiveVideoInfo {


    /**
     * Code : 0
     * Msg : 请求成功
     * Time : 2016-10-17 17:26:19
     * ApiUrl : /Api/Stream/watch.html
     * Data : {"uid":"19","liveno":"100000019","livestatus":"1","no_follow_send":"0","is_folllow":0}
     */

    private int Code;
    private String Msg;
    private String Time;
    private String ApiUrl;
    /**
     * uid : 19
     * liveno : 100000019
     * livestatus : 1
     * no_follow_send : 0
     * is_folllow : 0
     */

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

    public static class DataBean {
        private String uid;
        private String liveno;
        private String livestatus;
        private String no_follow_send;
        private String roomToken;
        private String token;
        private int is_folllow;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getRoomToken() {
            return roomToken;
        }

        public void setRoomToken(String roomToken) {
            this.roomToken = roomToken;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getLiveno() {
            return liveno;
        }

        public void setLiveno(String liveno) {
            this.liveno = liveno;
        }

        public String getLivestatus() {
            return livestatus;
        }

        public void setLivestatus(String livestatus) {
            this.livestatus = livestatus;
        }

        public String getNo_follow_send() {
            return no_follow_send;
        }

        public void setNo_follow_send(String no_follow_send) {
            this.no_follow_send = no_follow_send;
        }

        public int getIs_folllow() {
            return is_folllow;
        }

        public void setIs_folllow(int is_folllow) {
            this.is_folllow = is_folllow;
        }
    }
}
