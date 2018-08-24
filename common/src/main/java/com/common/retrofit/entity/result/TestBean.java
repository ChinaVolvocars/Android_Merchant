package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/5/8.
 */

public class TestBean {

    /**
     * id : 102
     * name : 美食
     * pid : 0
     * list : [{"id":"103","name":"西餐","pid":"102"},{"id":"104","name":"中餐","pid":"102"},{"id":"105","name":"面食","pid":"102"},{"id":"109","name":"快餐","pid":"102"}]
     */

    private String id;
    private String name;
    private String pid;
    private List<TestBean> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<TestBean> getList() {
        return list;
    }

    public void setList(List<TestBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 103
         * name : 西餐
         * pid : 102
         */

        private String id;
        private String name;
        private String pid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }
    }
}
