package com.live.entity;

/**
 * Created by Administrator on 2016/10/8.
 */
public class RoomChatContentInfo {
    public String userName;//发送者的姓名
    public String sendContent;//发送内容
    public int messageTaye;//发送类型

    public RoomChatContentInfo(String userName, String sendContent, int messageTaye) {
        this.userName = userName;
        this.sendContent = sendContent;
        this.messageTaye = messageTaye;
    }
}
