package com.envent;

/**
 * Created by Administrator on 2016/10/11.
 */
public class NotifiySendGiftSucc {

    private int id;
    private String name;
    private int masonry;
    private String pic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMasonry() {
        return masonry;
    }

    public void setMasonry(int masonry) {
        this.masonry = masonry;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public NotifiySendGiftSucc(int id, String name, int masonry, String pic) {
        this.id = id;
        this.name = name;
        this.masonry = masonry;
        this.pic = pic;
    }
}
