package com.hzxmkuar.sxmaketnew.entity;

import java.util.List;

/**
 * Created by STH on 2017/8/28.
 */

public class NewJsonBean {

    private List<RECORDBean> RECORD;

    public List<RECORDBean> getRECORD() {
        return RECORD;
    }

    public void setRECORD(List<RECORDBean> RECORD) {
        this.RECORD = RECORD;
    }

    public static class RECORDBean {
        /**
         * id : 110000
         * pid : 0
         * area : 北京
         * pinyin : bei jing
         * first_code : B
         * level : 1
         * status : 1
         * isdd : 0
         */

        private String id;
        private String pid;
        private String area;
        private String pinyin;
        private String first_code;
        private String level;
        private String status;
        private String isdd;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getPinyin() {
            return pinyin;
        }

        public void setPinyin(String pinyin) {
            this.pinyin = pinyin;
        }

        public String getFirst_code() {
            return first_code;
        }

        public void setFirst_code(String first_code) {
            this.first_code = first_code;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getIsdd() {
            return isdd;
        }

        public void setIsdd(String isdd) {
            this.isdd = isdd;
        }
    }
}
