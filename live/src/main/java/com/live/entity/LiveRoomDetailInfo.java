package com.live.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/10/15.
 * <p/>
 * 直播室详情
 */
public class LiveRoomDetailInfo {


    /**
     * Code : 0
     * Msg : 请求成功
     * Time : 2016-10-28 11:19:35
     * ApiUrl : /Api/Stream/roomInfo.html
     * Data : {"uid":18,"sums":"2","livestatus":"1","ticket":"0","my":{"level":"32","diamonds_num":"99842590"},"view":{"list":[{"uid":"21","face":""},{"uid":"29","face":""},{"uid":"28","face":"http://139.196.214.241:8090/Uploads/Picture/2016-10-26/58107e163d42d.png"},{"uid":"27","face":"http://139.196.214.241:8090/Uploads/Picture/2016-10-21/58097ba3c5bc3.jpg"},{"uid":"26","face":""}],"total":14,"remainder":9,"page":1}}
     */

    private int Code;
    private String Msg;
    private String Time;
    private String ApiUrl;
    /**
     * uid : 18
     * sums : 2
     * livestatus : 1
     * ticket : 0
     * my : {"level":"32","diamonds_num":"99842590"}
     * view : {"list":[{"uid":"21","face":""},{"uid":"29","face":""},{"uid":"28","face":"http://139.196.214.241:8090/Uploads/Picture/2016-10-26/58107e163d42d.png"},{"uid":"27","face":"http://139.196.214.241:8090/Uploads/Picture/2016-10-21/58097ba3c5bc3.jpg"},{"uid":"26","face":""}],"total":14,"remainder":9,"page":1}
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
        private String sums;
        private String livestatus;
        private String ticket;
        /**
         * level : 32
         * diamonds_num : 99842590
         */

        private MyBean my;
        /**
         * list : [{"uid":"21","face":""},{"uid":"29","face":""},{"uid":"28","face":"http://139.196.214.241:8090/Uploads/Picture/2016-10-26/58107e163d42d.png"},{"uid":"27","face":"http://139.196.214.241:8090/Uploads/Picture/2016-10-21/58097ba3c5bc3.jpg"},{"uid":"26","face":""}]
         * total : 14
         * remainder : 9
         * page : 1
         */

        private ViewBean view;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getSums() {
            return sums;
        }

        public void setSums(String sums) {
            this.sums = sums;
        }

        public String getLivestatus() {
            return livestatus;
        }

        public void setLivestatus(String livestatus) {
            this.livestatus = livestatus;
        }

        public String getTicket() {
            return ticket;
        }

        public void setTicket(String ticket) {
            this.ticket = ticket;
        }

        public MyBean getMy() {
            return my;
        }

        public void setMy(MyBean my) {
            this.my = my;
        }

        public ViewBean getView() {
            return view;
        }

        public void setView(ViewBean view) {
            this.view = view;
        }

        public static class MyBean {
            private String level;
            private String diamonds_num;

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getDiamonds_num() {
                return diamonds_num;
            }

            public void setDiamonds_num(String diamonds_num) {
                this.diamonds_num = diamonds_num;
            }
        }

        public static class ViewBean {
            private int total;
            private int remainder;
            private int page;
            /**
             * uid : 21
             * face :
             */

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

            public static class ListBean {
                private String uid;
                private String face;

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public String getFace() {
                    return face;
                }

                public void setFace(String face) {
                    this.face = face;
                }
            }
        }
    }
}
