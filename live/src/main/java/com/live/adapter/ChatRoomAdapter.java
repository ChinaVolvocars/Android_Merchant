package com.live.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.live.R;
import com.live.entity.RoomChatContentInfo;
import com.live.utils.TextViewUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 * 聊天室内容适配器
 */
public class ChatRoomAdapter extends DefaultBaseAdapter<RoomChatContentInfo> {

    private SpannableStringBuilder spannableStringBuilder;

    public ChatRoomAdapter(List<RoomChatContentInfo> list, Context context) {
        super(list, context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.list_item_room_chat, null);
            holder.tv_user_chatContent = (TextView) convertView.findViewById(R.id.tv_user_chatContent);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        RoomChatContentInfo roomChatContentInfo = list.get(position);
        //type:1-普通聊天消息，2-第一次点亮，3—直播提示。
        if (list.size() > 0) {
            if (roomChatContentInfo != null) {
                Log.d("EMMessage", "ChatRoomAdapter" + list.size() + "");
                int messageTaye = roomChatContentInfo.messageTaye;
                String content = roomChatContentInfo.userName + ":" + roomChatContentInfo.sendContent.toString();
                String contents = roomChatContentInfo.userName + " " + roomChatContentInfo.sendContent.toString();
                if (1 == messageTaye) {//发送聊天室消息
                    spannableStringBuilder = TextViewUtils.chagneTextColor(context, content, 1);
                    holder.tv_user_chatContent.setText(spannableStringBuilder);
                } else if (2 == messageTaye) {//进入聊天室消息
                    holder.tv_user_chatContent.setText(contents);
                    holder.tv_user_chatContent.setTextColor(Color.parseColor("#ff0000"));
                }
            }

        }
        return convertView;
    }

    class ViewHolder {
        //        TextView tv_user_nickname;
        TextView tv_user_chatContent;
//        ImageView iv_addHeart;

    }
}
