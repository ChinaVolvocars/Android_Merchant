package com.common.retrofit.entity.result;

import java.util.List;

/**
 *  商家个人活动列表
 * Created by jc on 2018/10/22.
 */
public class ShopShowsEntity {
    private List<ShopShowsItemEntity> list;

    public List<ShopShowsItemEntity> getList() {
        return list;
    }

    public void setList(List<ShopShowsItemEntity> list) {
        this.list = list;
    }

    /**
     * 商家个人活动列表条目
     */
    public static class ShopShowsItemEntity{
        /**
         *
         * "id"（活动id）: "2",
         * "activity_desc"（活动简介）: "测试活动简介",
         * "activity_info"（活动详细信息）: "测试测试测试测试测试活动详细信息",
         * "check_status"（审核状态1审核中，2通过，3不通过）: "2"
         *
         */

        private String id;
        private String activity_desc;
        private String activity_info;
        private String check_status;

        /**
         * 活动id
         * @return
         */
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        /**
         * 活动简介
         * @return
         */
        public String getActivity_desc() {
            return activity_desc;
        }

        public void setActivity_desc(String activity_desc) {
            this.activity_desc = activity_desc;
        }

        /**
         * 活动详细信息
         * @return
         */
        public String getActivity_info() {
            return activity_info;
        }

        public void setActivity_info(String activity_info) {
            this.activity_info = activity_info;
        }

        /**
         * 审核状态   <br/>
         * 1 审核中，  <br/>
         * 2 通过，  <br/>
         * 3 不通过  <br/>
         * @return
         */
        public String getCheck_status() {
            return check_status;
        }

        public void setCheck_status(String check_status) {
            this.check_status = check_status;
        }
    }

}
