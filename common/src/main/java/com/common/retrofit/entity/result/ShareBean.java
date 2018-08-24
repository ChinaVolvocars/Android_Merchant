package com.common.retrofit.entity.result;

/**
 * Created by STH on 2018/4/20.
 */

public class ShareBean {
    private String title;
    private String img;
    private String content;
    private String url;

    public ShareBean(String title, String img, String content, String url) {
        this.title = title;
        this.img = img;
        this.content = content;
        this.url = url;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
