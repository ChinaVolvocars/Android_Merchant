package com.hyphenate.easeui.widget.chatrow;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.utils.ScreenUtils;
import com.common.utils.SizeUtils;
import com.easeui.R;
import com.hyphenate.chat.EMFileMessageBody;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMVoiceMessageBody;
import com.hyphenate.easeui.widget.BubbleLinearLayout;
import com.hyphenate.util.EMLog;

public class EaseChatRowVoice extends EaseChatRowFile{

    private ImageView voiceImageView;
    private TextView voiceLengthView;
    private ImageView readStatusView;
    private BubbleLinearLayout contentLayout;

    public EaseChatRowVoice(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflateView() {
        inflater.inflate(message.direct() == EMMessage.Direct.RECEIVE ?
                R.layout.ease_row_received_voice : R.layout.ease_row_sent_voice, this);
    }

    @Override
    protected void onFindViewById() {
        contentLayout = (BubbleLinearLayout) findViewById(R.id.bubble);
        voiceImageView = ((ImageView) findViewById(R.id.iv_voice));
        voiceLengthView = (TextView) findViewById(R.id.tv_length);
        readStatusView = (ImageView) findViewById(R.id.iv_unread_voice);
    }

    @Override
    protected void onSetUpView() {
        EMVoiceMessageBody voiceBody = (EMVoiceMessageBody) message.getBody();
        int len = voiceBody.getLength();
        if(len>0){
            voiceLengthView.setText(voiceBody.getLength() + "\"");
            voiceLengthView.setVisibility(View.VISIBLE);
        }else{
            voiceLengthView.setVisibility(View.INVISIBLE);
        }

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) contentLayout.getLayoutParams();

        /**
         * 设置语音框尺寸，分三种情况
         * 1.语音小于5秒，设置成最小尺寸
         * 2.语音大于5秒小于60秒，平分屏幕宽度的3/4
         * 3.语音大于屏60秒，显示最大宽度为屏幕3/4
         */
        int lenRange = (ScreenUtils.getScreenWidth(context) * 3 / 4) / 70;

        if (len < 5){
            layoutParams.width = lenRange * 15;
            layoutParams.height = LayoutParams.WRAP_CONTENT;
        } else if (len < 60) {
            layoutParams.width = lenRange * (len + 15);
            layoutParams.height = LayoutParams.WRAP_CONTENT;
        } else {
            layoutParams.width = lenRange;
            layoutParams.height = LayoutParams.WRAP_CONTENT;
        }
        contentLayout.setLayoutParams(layoutParams);

        if (EaseChatRowVoicePlayClickListener.playMsgId != null
                && EaseChatRowVoicePlayClickListener.playMsgId.equals(message.getMsgId()) && EaseChatRowVoicePlayClickListener.isPlaying) {
            AnimationDrawable voiceAnimation;
            if (message.direct() == EMMessage.Direct.RECEIVE) {
                voiceImageView.setImageResource(R.drawable.voice_left);
            } else {
                voiceImageView.setImageResource(R.drawable.voice_right);
            }

            voiceAnimation = (AnimationDrawable) voiceImageView.getDrawable();
            voiceAnimation.start();
        } else {
            if (message.direct() == EMMessage.Direct.RECEIVE) {
                voiceImageView.setImageResource(R.drawable.ease_chat_from_voice_play);
            } else {
                voiceImageView.setImageResource(R.drawable.ease_chat_to_voice_play);
            }
        }
        
        if (message.direct() == EMMessage.Direct.RECEIVE) {
            if (message.isListened()) {
                // hide the unread icon
                readStatusView.setVisibility(View.INVISIBLE);
            } else {
                readStatusView.setVisibility(View.VISIBLE);
            }
            EMLog.d(TAG, "it is receive msg");
            if (voiceBody.downloadStatus() == EMFileMessageBody.EMDownloadStatus.DOWNLOADING ||
                    voiceBody.downloadStatus() == EMFileMessageBody.EMDownloadStatus.PENDING) {
                progressBar.setVisibility(View.VISIBLE);
                setMessageReceiveCallback();
            } else {
                progressBar.setVisibility(View.INVISIBLE);

            }
            return;
        }

        // until here, handle sending voice message
        handleSendMessage();
    }

    @Override
    protected void onUpdateView() {
        super.onUpdateView();
    }

    @Override
    protected void onBubbleClick() {
        new EaseChatRowVoicePlayClickListener(message, voiceImageView, readStatusView, adapter, activity).onClick(bubbleLayout);
    }
    
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (EaseChatRowVoicePlayClickListener.currentPlayListener != null && EaseChatRowVoicePlayClickListener.isPlaying) {
            EaseChatRowVoicePlayClickListener.currentPlayListener.stopPlayVoice();
        }
    }
    
}
