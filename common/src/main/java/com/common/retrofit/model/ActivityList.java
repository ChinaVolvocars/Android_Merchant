package com.common.retrofit.model;


public class ActivityList {

    /**
     * time : 19
     * type : 2
     * title : 商家推广活动
     * desc : 商家推广活动
     * pic : http://sxpic.oss-cn-hangzhou.aliyuncs.com/file/2018-11-10/shangjiaban_shouye_kaixinchuandan%403x.png
     * url : http://cc.com/Admin/Index/index.html
     */
    private int time;
    private int type;
    private String title;
    private String desc;
    private String pic;
    private String url;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
