package com.live.entity;

/**
 * @author
 * @Description 礼物的实体类
 * @Date
 */
public class GiftVo {

    private String userId;//送礼物人的UserId
    private String sendUserPic;//发送者的头像
    private String sendUserName;//发送者的头像
    private String giftName;//发送礼物的名字
    private String giftId;//发送礼物的id
    private String giftPic;//发送礼物的图片
    private int num;//发送礼物的数量

    public GiftVo(String userId, String sendUserPic, String sendUserName, String giftName, String giftId, String giftPic, int num) {
        this.userId = userId;
        this.sendUserPic = sendUserPic;
        this.sendUserName = sendUserName;
        this.giftName = giftName;
        this.giftId = giftId;
        this.giftPic = giftPic;
        this.num = num;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSendUserPic() {
        return sendUserPic;
    }

    public void setSendUserPic(String sendUserPic) {
        this.sendUserPic = sendUserPic;
    }

    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public String getGiftPic() {
        return giftPic;
    }

    public void setGiftPic(String giftPic) {
        this.giftPic = giftPic;
    }

    public int getNum() {
        return num;
    }

    public void setNum(String sendGiftNum) {
        this.num = num;
    }
}
