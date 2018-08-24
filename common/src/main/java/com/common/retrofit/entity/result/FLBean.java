package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by STH on 2018/4/24.
 */

public class FLBean {

    /**
     * recommend : {"total":6,"page":1,"limit":10,"remainder":0,"lists":[{"title":"男装1","secondLists":[{"id":6,"title":"羽绒服","img":"1"},{"id":7,"title":"1111","img":"112"},{"id":8,"title":"1111","img":"113"},{"id":10,"title":"111","img":"115"}]},{"title":"女装","secondLists":[{"id":2,"title":"羽绒服","img":"1"},{"id":3,"title":"毛呢外套","img":"1"}]}]}
     * fristLists : [{"title":"男装1","id":5},{"title":"女装","id":1},{"title":"男鞋","id":9}]
     */

    private RecommendBean recommend;
    private List<FristListsBean> fristLists;

    public RecommendBean getRecommend() {
        return recommend;
    }

    public void setRecommend(RecommendBean recommend) {
        this.recommend = recommend;
    }

    public List<FristListsBean> getFristLists() {
        return fristLists;
    }

    public void setFristLists(List<FristListsBean> fristLists) {
        this.fristLists = fristLists;
    }

    public static class RecommendBean {
        /**
         * total : 6
         * page : 1
         * limit : 10
         * remainder : 0
         * lists : [{"title":"男装1","secondLists":[{"id":6,"title":"羽绒服","img":"1"},{"id":7,"title":"1111","img":"112"},{"id":8,"title":"1111","img":"113"},{"id":10,"title":"111","img":"115"}]},{"title":"女装","secondLists":[{"id":2,"title":"羽绒服","img":"1"},{"id":3,"title":"毛呢外套","img":"1"}]}]
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
             * title : 男装1
             * secondLists : [{"id":6,"title":"羽绒服","img":"1"},{"id":7,"title":"1111","img":"112"},{"id":8,"title":"1111","img":"113"},{"id":10,"title":"111","img":"115"}]
             */

            private String title;
            private List<SecondListsBean> secondLists;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<SecondListsBean> getSecondLists() {
                return secondLists;
            }

            public void setSecondLists(List<SecondListsBean> secondLists) {
                this.secondLists = secondLists;
            }

            public static class SecondListsBean {
                /**
                 * id : 6
                 * title : 羽绒服
                 * img : 1
                 */

                private int id;
                private String title;
                private String img;

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

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }
            }
        }
    }

    public static class FristListsBean {
        /**
         * title : 男装1
         * id : 5
         */

        private String title;
        private int id;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
