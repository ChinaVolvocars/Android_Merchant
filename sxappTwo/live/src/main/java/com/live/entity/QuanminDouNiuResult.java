package com.live.entity;

/**
 * Created by Administrator on 2016/12/12.
 */

public class QuanminDouNiuResult {


    /**
     * Code : 0
     * Msg : 请求成功
     * Time : 2017-01-21 16:29:16
     * ApiUrl : /Api/Game/douniu.html
     * Data : {"card1":{"poker":"spade4,diamond10,spadeK,club5,heartJ","res":{"niu":10,"val":9,"wuhuaniu":0}},"card2":{"poker":"heart7,club9,club4,spade7,heart2","res":{"niu":10,"val":9,"wuhuaniu":0}},"card3":{"poker":"diamond4,diamond3,diamondQ,spadeQ,diamondJ","res":{"niu":10,"val":7,"wuhuaniu":0}},"values":1,"mtid":"8236","zuid":8,"dtime":"30"}
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
         * card1 : {"poker":"spade4,diamond10,spadeK,club5,heartJ","res":{"niu":10,"val":9,"wuhuaniu":0}}
         * card2 : {"poker":"heart7,club9,club4,spade7,heart2","res":{"niu":10,"val":9,"wuhuaniu":0}}
         * card3 : {"poker":"diamond4,diamond3,diamondQ,spadeQ,diamondJ","res":{"niu":10,"val":7,"wuhuaniu":0}}
         * values : 1
         * mtid : 8236
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
             * poker : spade4,diamond10,spadeK,club5,heartJ
             * res : {"niu":10,"val":9,"wuhuaniu":0}
             */

            private String poker;
            private ResBean res;

            public String getPoker() {
                return poker;
            }

            public void setPoker(String poker) {
                this.poker = poker;
            }

            public ResBean getRes() {
                return res;
            }

            public void setRes(ResBean res) {
                this.res = res;
            }

            public static class ResBean {
                /**
                 * niu : 10
                 * val : 9
                 * wuhuaniu : 0
                 */

                private int niu;
                private int val;
                private int wuhuaniu;

                public int getNiu() {
                    return niu;
                }

                public void setNiu(int niu) {
                    this.niu = niu;
                }

                public int getVal() {
                    return val;
                }

                public void setVal(int val) {
                    this.val = val;
                }

                public int getWuhuaniu() {
                    return wuhuaniu;
                }

                public void setWuhuaniu(int wuhuaniu) {
                    this.wuhuaniu = wuhuaniu;
                }
            }
        }

        public static class Card2Bean {
            /**
             * poker : heart7,club9,club4,spade7,heart2
             * res : {"niu":10,"val":9,"wuhuaniu":0}
             */

            private String poker;
            private ResBeanX res;

            public String getPoker() {
                return poker;
            }

            public void setPoker(String poker) {
                this.poker = poker;
            }

            public ResBeanX getRes() {
                return res;
            }

            public void setRes(ResBeanX res) {
                this.res = res;
            }

            public static class ResBeanX {
                /**
                 * niu : 10
                 * val : 9
                 * wuhuaniu : 0
                 */

                private int niu;
                private int val;
                private int wuhuaniu;

                public int getNiu() {
                    return niu;
                }

                public void setNiu(int niu) {
                    this.niu = niu;
                }

                public int getVal() {
                    return val;
                }

                public void setVal(int val) {
                    this.val = val;
                }

                public int getWuhuaniu() {
                    return wuhuaniu;
                }

                public void setWuhuaniu(int wuhuaniu) {
                    this.wuhuaniu = wuhuaniu;
                }
            }
        }

        public static class Card3Bean {
            /**
             * poker : diamond4,diamond3,diamondQ,spadeQ,diamondJ
             * res : {"niu":10,"val":7,"wuhuaniu":0}
             */

            private String poker;
            private ResBeanXX res;

            public String getPoker() {
                return poker;
            }

            public void setPoker(String poker) {
                this.poker = poker;
            }

            public ResBeanXX getRes() {
                return res;
            }

            public void setRes(ResBeanXX res) {
                this.res = res;
            }

            public static class ResBeanXX {
                /**
                 * niu : 10
                 * val : 7
                 * wuhuaniu : 0
                 */

                private int niu;
                private int val;
                private int wuhuaniu;

                public int getNiu() {
                    return niu;
                }

                public void setNiu(int niu) {
                    this.niu = niu;
                }

                public int getVal() {
                    return val;
                }

                public void setVal(int val) {
                    this.val = val;
                }

                public int getWuhuaniu() {
                    return wuhuaniu;
                }

                public void setWuhuaniu(int wuhuaniu) {
                    this.wuhuaniu = wuhuaniu;
                }
            }
        }
    }
}
