package com.live.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/15.
 *
 * 主播开始直播
 */
public class StratPlayVideoInfo implements Serializable{


    /**
     * Code : 0
     * Msg : 请求成功
     * Time : 2016-10-17 18:08:03
     * ApiUrl : /Api/Stream/createStream.html
     * Data : {"id":"5","liveno":"100000018","stream":"{\"id\":\"z1.xinmiao.e283009b4f920b0e9247724452ac7f52\",\"createdAt\":\"2016-10-07T05:27:34.457Z\",\"updatedAt\":\"2016-10-07T05:27:34.457Z\",\"expireAt\":\"2016-10-23T13:27:34.457684033+08:00\",\"title\":\"e283009b4f920b0e9247724452ac7f52\",\"hub\":\"xinmiao\",\"disabledTill\":0,\"disabled\":false,\"publishKey\":\"4eb70faba54ee426\",\"publishSecurity\":\"static\",\"hosts\":{\"publish\":{\"rtmp\":\"pili-publish.tuchewang.com\"},\"live\":{\"hdl\":\"pili-live-hdl.tuchewang.com\",\"hls\":\"pili-live-hls.tuchewang.com\",\"http\":\"pili-live-hls.tuchewang.com\",\"rtmp\":\"pili-live-rtmp.tuchewang.com\",\"snapshot\":\"pili-live-snapshot.tuchewang.com\"},\"playback\":{\"hls\":\"100048k.playback1.z1.pili.qiniucdn.com\",\"http\":\"100048k.playback1.z1.pili.qiniucdn.com\"},\"play\":{\"http\":\"pili-live-hls.tuchewang.com\",\"rtmp\":\"pili-live-rtmp.tuchewang.com\"}}}","status":"1","hx_username":"aefd37bac7ee0615b817cd576edb1e76","hx_password":"400f3255f1313e39291fc7c7180000f1","hx_chatroomsid":"250195825961992628"}
     */

    private int Code;
    private String Msg;
    private String Time;
    private String ApiUrl;
    /**
     * id : 5
     * liveno : 100000018
     * stream : {"id":"z1.xinmiao.e283009b4f920b0e9247724452ac7f52","createdAt":"2016-10-07T05:27:34.457Z","updatedAt":"2016-10-07T05:27:34.457Z","expireAt":"2016-10-23T13:27:34.457684033+08:00","title":"e283009b4f920b0e9247724452ac7f52","hub":"xinmiao","disabledTill":0,"disabled":false,"publishKey":"4eb70faba54ee426","publishSecurity":"static","hosts":{"publish":{"rtmp":"pili-publish.tuchewang.com"},"live":{"hdl":"pili-live-hdl.tuchewang.com","hls":"pili-live-hls.tuchewang.com","http":"pili-live-hls.tuchewang.com","rtmp":"pili-live-rtmp.tuchewang.com","snapshot":"pili-live-snapshot.tuchewang.com"},"playback":{"hls":"100048k.playback1.z1.pili.qiniucdn.com","http":"100048k.playback1.z1.pili.qiniucdn.com"},"play":{"http":"pili-live-hls.tuchewang.com","rtmp":"pili-live-rtmp.tuchewang.com"}}}
     * status : 1
     * hx_username : aefd37bac7ee0615b817cd576edb1e76
     * hx_password : 400f3255f1313e39291fc7c7180000f1
     * hx_chatroomsid : 250195825961992628
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

    public static class DataBean implements Serializable{
        private String id;
        private String liveno;
        private String stream;
        private String status;
        private String hx_username;
        private String hx_password;
        private String hx_chatroomsid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLiveno() {
            return liveno;
        }

        public void setLiveno(String liveno) {
            this.liveno = liveno;
        }

        public String getStream() {
            return stream;
        }

        public void setStream(String stream) {
            this.stream = stream;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getHx_username() {
            return hx_username;
        }

        public void setHx_username(String hx_username) {
            this.hx_username = hx_username;
        }

        public String getHx_password() {
            return hx_password;
        }

        public void setHx_password(String hx_password) {
            this.hx_password = hx_password;
        }

        public String getHx_chatroomsid() {
            return hx_chatroomsid;
        }

        public void setHx_chatroomsid(String hx_chatroomsid) {
            this.hx_chatroomsid = hx_chatroomsid;
        }
    }
}
