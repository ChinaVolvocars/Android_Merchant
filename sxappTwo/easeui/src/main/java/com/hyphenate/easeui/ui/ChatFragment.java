package com.hyphenate.easeui.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.common.utils.EmptyUtils;
import com.common.widget.popwindow.BasePopupWindow;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment.EaseChatFragmentHelper;
import com.hyphenate.easeui.widget.EaseMsgEditPopup;
import com.hyphenate.easeui.widget.chatrow.ChatRowVoiceCall;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;

import java.util.List;

public class ChatFragment extends EaseChatFragment implements EaseChatFragmentHelper {

    private static final int ITEM_FILE = 12;

    private static final int REQUEST_CODE_SELECT_FILE = 12;
    private static final int REQUEST_CODE_CONTEXT_MENU = 14;
    private static final int REQUEST_CODE_SELECT_AT_USER = 15;

    private static final int MESSAGE_TYPE_SENT_VOICE_CALL = 1;
    private static final int MESSAGE_TYPE_RECV_VOICE_CALL = 2;
    private AlertDialog builder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void doLogicFunc() {
        super.doLogicFunc();
        setChatFragmentListener(this);
    }
    
    @Override
    protected void registerExtendMenuItem() {
        super.registerExtendMenuItem();
    }

    public static final int RESULT_CODE_COPY = 1;
    public static final int RESULT_CODE_DELETE = 2;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CONTEXT_MENU) {
            switch (resultCode) {
            case RESULT_CODE_COPY: // copy
                clipboard.setPrimaryClip(ClipData.newPlainText(null,
                        ((EMTextMessageBody) contextMenuMessage.getBody()).getMessage()));
                break;
            case RESULT_CODE_DELETE: // delete
                conversation.removeMessage(contextMenuMessage.getMsgId());
                messageList.refresh();
                break;
            default:
                break;
            }
        }
        if(resultCode == Activity.RESULT_OK){
            switch (requestCode) {
            case REQUEST_CODE_SELECT_AT_USER:
                if(data != null){
                    String username = data.getStringExtra("username");
//                    inputAtUsername(username, false);
                }
                break;
            default:
                break;
            }
        }
    }
    
    @Override
    public void onSetMessageAttributes(EMMessage message) {
        // 通过扩展属性，将userPic和userName发送出去。
        String userPic = toChatUserAvatar;
        if (!EmptyUtils.isEmpty(userPic)) {
            message.setAttribute("userphoto", userPic);
        }
        String minephoto = toChatUserAvatarMine;
        if (!EmptyUtils.isEmpty(minephoto)) {
            message.setAttribute("minephoto", minephoto);
        }
        System.out.println("aaaaaaaaaaaa"+userPic+minephoto);
        String userName = nickname;
        if (!EmptyUtils.isEmpty(userName)) {
            message.setAttribute("nickname", userName);
        }
        String minenicknames = minenickname;
        if (!EmptyUtils.isEmpty(minenicknames)) {
            message.setAttribute("minenickname", minenicknames);
        }
    }
    
    @Override
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
        return new CustomChatRowProvider();
    }
  
    @Override
    public void onEnterToChatDetails() {
    }

    @Override
    public void onAvatarClick(String username) {
    }

    @Override
    public void onAvatarLongClick(String username) {
    }
    
    @Override
    public boolean onMessageBubbleClick(EMMessage message) {
        return false;
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> messages) {
        super.onCmdMessageReceived(messages);
    }

    @Override
    public void onMessageBubbleLongClick(EMMessage message, View v) {
    	// no message forward when in chat room
        doListSort(v);
    }

    EaseMsgEditPopup menuPopup;

    // 实现排序逻辑
    private void doListSort(View v) {
        if (EmptyUtils.isEmpty(menuPopup)) {
            menuPopup = new EaseMsgEditPopup((Activity) context);
        }

        menuPopup.showPopupWindow(v);

        menuPopup.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });

        menuPopup.setTypeListener(new EaseMsgEditPopup.TypeListener() {
            @Override
            public void setPostion(int postion) {
                if (postion == 0) {
                    clipboard.setPrimaryClip(ClipData.newPlainText(null,
                            ((EMTextMessageBody) contextMenuMessage.getBody()).getMessage()));
                } else {
                    conversation.removeMessage(contextMenuMessage.getMsgId());
                    messageList.refresh();
                }
            }
        });
    }

    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {
        switch (itemId) {
        case ITEM_FILE: //file
            selectFileFromLocal();
            break;
        default:
            break;
        }
        return false;
    }
    
    /**
     * select file
     */
    protected void selectFileFromLocal() {
        Intent intent = null;
        if (Build.VERSION.SDK_INT < 19) { //api 19 and later, we can't use this way, demo just select from images
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);

        } else {
            intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        startActivityForResult(intent, REQUEST_CODE_SELECT_FILE);
    }
    
    /** chat row provider  */
    private final class CustomChatRowProvider implements EaseCustomChatRowProvider {
        @Override
        public int getCustomChatRowTypeCount() {
            //here the number is the message type in EMMessage::Type
        	//which is used to count the number of different chat row
            return 12;
        }

        @Override
        public int getCustomChatRowType(EMMessage message) {
            if(message.getType() == EMMessage.Type.TXT){
                //voice call
                if (message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_VOICE_CALL, false)){
                    return message.direct() == EMMessage.Direct.RECEIVE ? MESSAGE_TYPE_RECV_VOICE_CALL : MESSAGE_TYPE_SENT_VOICE_CALL;
                }
            }
            return 0;
        }

        @Override
        public EaseChatRow getCustomChatRow(EMMessage message, int position, BaseAdapter adapter) {
            if(message.getType() == EMMessage.Type.TXT){
                // voice call or video call
                if (message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_VOICE_CALL, false) ||
                    message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_VIDEO_CALL, false)){
                    return new ChatRowVoiceCall(context, message, position, adapter);
                }
            }
            return null;
        }
    }
}
