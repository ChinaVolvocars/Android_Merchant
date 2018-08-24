package com.live.entity;

/**
 * Created by Administrator on 2017/1/13.
 */

public class DeZhouInfo {


    /**
     * Code : 0
     * Msg : 请求成功
     * Time : 2017-01-13 11:07:44
     * ApiUrl : /Api/Game/dezhoupoker.html
     * Data : {"card1":{"poker":"heartA,diamond6","res":"两对"},"card2":{"poker":"spade6,club7,spade7,clubQ,heart4","res":""},"card3":{"poker":"club6,spade4","res":"两对"},"values":2,"mtid":"4295","zuid":8,"dtime":"30"}
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

    public static class DataBean {
        /**
         * card1 : {"poker":"heartA,diamond6","res":"两对"}
         * card2 : {"poker":"spade6,club7,spade7,clubQ,heart4","res":""}
         * card3 : {"poker":"club6,spade4","res":"两对"}
         * values : 2
         * mtid : 4295
         * zuid : 8
         * dtime : 30
         */

        private Card1Bean card1;
        private Card2Bean card2;
        private Card3Bean card3;
        private int values;
        private String mtid;
        private int zuid;
        private String dtime;

        public Card1Bean getCard1() {
            return card1;
        }

        public void setCard1(Card1Bean card1) {
            this.card1 = card1;
        }

        public Card2Bean getCard2() {
            return card2;
        }

        public void setCard2(Card2Bean card2) {
            this.card2 = card2;
        }

        public Card3Bean getCard3() {
            return card3;
        }

        public void setCard3(Card3Bean card3) {
            this.card3 = card3;
        }

        public int getValues() {
            return values;
        }

        public void setValues(int values) {
            this.values = values;
        }

        public String getMtid() {
            return mtid;
        }

        public void setMtid(String mtid) {
            this.mtid = mtid;
        }

        public int getZuid() {
            return zuid;
        }

        public void setZuid(int zuid) {
            this.zuid = zuid;
        }

        public String getDtime() {
            return dtime;
        }

        public void setDtime(String dtime) {
            this.dtime = dtime;
        }

        public static class Card1Bean {
            /**
             * poker : heartA,diamond6
             * res : 两对
             */

            private String poker;
            private String res;

            public String getPoker() {
                return poker;
            }

            public void setPoker(String poker) {
                this.poker = poker;
            }

            public String getRes() {
                return res;
            }

            public void setRes(String res) {
                this.res = res;
            }
        }

        public static class Card2Bean {
            /**
             * poker : spade6,club7,spade7,clubQ,heart4
             * res :
             */

            private String poker;
            private String res;

            public String getPoker() {
                return poker;
            }

            public void setPoker(String poker) {
                this.poker = poker;
            }

            public String getRes() {
                return res;
            }

            public void setRes(String res) {
                this.res = res;
            }
        }

        public static class Card3Bean {
            /**
             * poker : club6,spade4
             * res : 两对
             */

            private String poker;
            private String res;

            public String getPoker() {
                return poker;
            }

            public void setPoker(String poker) {
                this.poker = poker;
            }

            public String getRes() {
                return res;
            }

            public void setRes(String res) {
                this.res = res;
            }
        }
    }
}
