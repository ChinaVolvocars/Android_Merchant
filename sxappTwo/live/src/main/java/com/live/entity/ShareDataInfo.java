package com.live.entity;

/**
 * Created by Administrator on 2016/10/27.
 */
public class ShareDataInfo {


    /**
     * Code : 0
     * Msg : 请求成功
     * Time : 2016-10-27 16:18:52
     * ApiUrl : /Api/Share/share.html
     * Data : {"url":"139.196.214.241:8090/Home/Video/index/liveno/100000018.html","img":"http://139.196.214.241:8090/Uploads/Picture/2016-10-26/581024e99757d.jpg","title":"吃饭用大碗的直播","content":"「吃饭用大碗」正在直播，快来围观吧！"}
     */

    private int Code;
    private String Msg;
    private String Time;
    private String ApiUrl;
    /**
     * url : 139.196.214.241:8090/Home/Video/index/liveno/100000018.html
     * img : http://139.196.214.241:8090/Uploads/Picture/2016-10-26/581024e99757d.jpg
     * title : 吃饭用大碗的直播
     * content : 「吃饭用大碗」正在直播，快来围观吧！
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
        private String url;
        private String img;
        private String title;
        private String content;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
