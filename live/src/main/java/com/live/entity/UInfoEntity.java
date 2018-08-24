package com.live.entity;

/**
 * 作者：LMZ on 2016/9/29 0029 10:19
 * 个人信息
 */
public class UInfoEntity {


    /**
     * Code : 0
     * Msg : ok
     * Time : 2016-10-17 11:00:55
     * ApiUrl : /Api/User/uInfo.html
     * Data : {"uid":18,"nickname":"吃饭用大碗","face":"http://139.196.214.241:8090/Uploads/Picture/2016-10-07/57f7326f7dd8e.jpg","account":0,"age":47,"gender":"男","isgender":1,"birthday":"1970-01-01","signature":"哎哟，不错哦？","emotion":"保密","occupation":"","address":"","province":370000,"area":370100,"county":0,"province_name":"山东省","area_name":"济南市","county_name":"","zodiac":"狗","constellation":"摩羯座","level":32,"diamonds_num":"99854650","ticket_num":291180,"give_diamonds_num":"7680","empirical_value":"45201","liveno":100000018,"follow":"4","attention":"3","record_num":"103"}
     */

    private int Code;
    private String Msg;
    private String Time;
    private String ApiUrl;
    /**
     * uid : 18
     * nickname : 吃饭用大碗
     * face : http://139.196.214.241:8090/Uploads/Picture/2016-10-07/57f7326f7dd8e.jpg
     * account : 0
     * age : 47
     * gender : 男
     * isgender : 1
     * birthday : 1970-01-01
     * signature : 哎哟，不错哦？
     * emotion : 保密
     * occupation :
     * address :
     * province : 370000
     * area : 370100
     * county : 0
     * province_name : 山东省
     * area_name : 济南市
     * county_name :
     * zodiac : 狗
     * constellation : 摩羯座
     * level : 32
     * diamonds_num : 99854650
     * ticket_num : 291180
     * give_diamonds_num : 7680
     * empirical_value : 45201
     * liveno : 100000018
     * follow : 4
     * attention : 3
     * record_num : 103
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
        private int uid;
        private String nickname;
        private String face;
        private int account;
        private int age;
        private String gender;
        private int isgender;
        private String birthday;
        private String signature;
        private String emotion;
        private String occupation;
        private String address;
        private int province;
        private int area;
        private int county;
        private String province_name;
        private String area_name;
        private String county_name;
        private String zodiac;
        private String constellation;
        private int level;
        private String diamonds_num;
        private int ticket_num;
        private String give_diamonds_num;
        private String empirical_value;
        private int liveno;
        private String follow;
        private String attention;
        private String record_num;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
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

        public int getAccount() {
            return account;
        }

        public void setAccount(int account) {
            this.account = account;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getIsgender() {
            return isgender;
        }

        public void setIsgender(int isgender) {
            this.isgender = isgender;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getEmotion() {
            return emotion;
        }

        public void setEmotion(String emotion) {
            this.emotion = emotion;
        }

        public String getOccupation() {
            return occupation;
        }

        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getProvince() {
            return province;
        }

        public void setProvince(int province) {
            this.province = province;
        }

        public int getArea() {
            return area;
        }

        public void setArea(int area) {
            this.area = area;
        }

        public int getCounty() {
            return county;
        }

        public void setCounty(int county) {
            this.county = county;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public String getCounty_name() {
            return county_name;
        }

        public void setCounty_name(String county_name) {
            this.county_name = county_name;
        }

        public String getZodiac() {
            return zodiac;
        }

        public void setZodiac(String zodiac) {
            this.zodiac = zodiac;
        }

        public String getConstellation() {
            return constellation;
        }

        public void setConstellation(String constellation) {
            this.constellation = constellation;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getDiamonds_num() {
            return diamonds_num;
        }

        public void setDiamonds_num(String diamonds_num) {
            this.diamonds_num = diamonds_num;
        }

        public int getTicket_num() {
            return ticket_num;
        }

        public void setTicket_num(int ticket_num) {
            this.ticket_num = ticket_num;
        }

        public String getGive_diamonds_num() {
            return give_diamonds_num;
        }

        public void setGive_diamonds_num(String give_diamonds_num) {
            this.give_diamonds_num = give_diamonds_num;
        }

        public String getEmpirical_value() {
            return empirical_value;
        }

        public void setEmpirical_value(String empirical_value) {
            this.empirical_value = empirical_value;
        }

        public int getLiveno() {
            return liveno;
        }

        public void setLiveno(int liveno) {
            this.liveno = liveno;
        }

        public String getFollow() {
            return follow;
        }

        public void setFollow(String follow) {
            this.follow = follow;
        }

        public String getAttention() {
            return attention;
        }

        public void setAttention(String attention) {
            this.attention = attention;
        }

        public String getRecord_num() {
            return record_num;
        }

        public void setRecord_num(String record_num) {
            this.record_num = record_num;
        }
    }
}
