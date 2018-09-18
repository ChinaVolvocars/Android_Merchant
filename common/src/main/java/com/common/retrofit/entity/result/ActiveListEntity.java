package com.common.retrofit.entity.result;

import java.util.List;

/**
 * Created by little_bug on 2018/9/18.
 */

public class ActiveListEntity {

    private List<ActiveListItemEntity> list;

    public List<ActiveListItemEntity> getList() {
        return list;
    }

    public void setList(List<ActiveListItemEntity> list) {
        this.list = list;
    }

    public static class ActiveListItemEntity{

        private String time;
        private String type;
        private String title;
        private String desc;
        private String pic;
        private String url;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        /**
         *  type活动类型 <br/>
         *  1 为未开始   <br/>
         *  2 为已开始  <br/>
         * @param type
         */
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        /**
         *  活动图片
         * @return
         */
        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        /**
         *  活动跳转链接
         * @return
         */
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "ActiveListEntity{" +
                    "time='" + time + '\'' +
                    ", type='" + type + '\'' +
                    ", title='" + title + '\'' +
                    ", desc='" + desc + '\'' +
                    ", pic='" + pic + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

}
