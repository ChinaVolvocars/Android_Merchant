package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/5/3.
 */

public class MsgBena {

    /**
     * total : 18
     * page : 1
     * limit : 10
     * remainder : 8
     * lists : [{"id":20,"title":"发货提醒","msg":"客户催促发货了，请尽快到订单中处理","create_time":"05/02"},{"id":19,"title":"发货提醒","msg":"客户催促发货了，请尽快到订单中处理","create_time":"05/02"},{"id":18,"title":"发货提醒","msg":"客户催促发货了，请尽快到订单中处理","create_time":"05/02"},{"id":17,"title":"发货提醒","msg":"客户催促发货了，请尽快到订单中处理","create_time":"05/02"},{"id":16,"title":"发货提醒","msg":"客户催促发货了，请尽快到订单中处理","create_time":"05/02"},{"id":15,"title":"发货提醒","msg":"客户催促发货了，请尽快到订单中处理","create_time":"05/02"},{"id":14,"title":"发货提醒","msg":"客户催促发货了，请尽快到订单中处理","create_time":"05/02"},{"id":13,"title":"发货提醒","msg":"客户催促发货了，请尽快到订单中处理","create_time":"05/02"},{"id":12,"title":"发货提醒","msg":"客户催促发货了，请尽快到订单中处理","create_time":"05/02"},{"id":11,"title":"发货提醒","msg":"客户催促发货了，请尽快到订单中处理","create_time":"05/02"}]
     */

    private int total;
    private int page;
    private int limit;
    private int remainder;
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

    public List<ListsBean> getLists() {
        return lists;
    }

    public void setLists(List<ListsBean> lists) {
        this.lists = lists;
    }

    public static class ListsBean {
        /**
         * id : 20
         * title : 发货提醒
         * msg : 客户催促发货了，请尽快到订单中处理
         * create_time : 05/02
         */

        private int id;
        private String title;
        private String msg;
        private String create_time;

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

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
