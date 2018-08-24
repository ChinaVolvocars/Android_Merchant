package com.widget.danmuView;

/**
 * Created by Administrator on 2016/10/29.
 */
public class DanmuVo {

    public String userId;//送弹幕人的UserId
    public String sendUserPic;//发送者的头像
    public String sendUserName;//发送者的昵称
    public String sendContent;//发送内容

    public DanmuVo(String userId, String sendUserPic, String sendUserName,String sendContent) {
        this.userId = userId;
        this.sendUserPic = sendUserPic;
        this.sendUserName = sendUserName;
        this.sendContent=sendContent;
    }
}
