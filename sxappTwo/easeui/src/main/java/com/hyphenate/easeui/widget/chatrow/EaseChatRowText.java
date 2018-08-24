package com.hyphenate.easeui.widget.chatrow;

import android.content.Context;
import android.text.Spannable;
import android.text.TextPaint;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.utils.ScreenUtils;
import com.common.utils.SizeUtils;
import com.easeui.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessage.ChatType;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.easeui.utils.EaseSmileUtils;
import com.hyphenate.easeui.widget.BubbleLinearLayout;
import com.hyphenate.exceptions.HyphenateException;

public class EaseChatRowText extends EaseChatRow {

	private TextView contentView;
	private BubbleLinearLayout contentLayout;

    public EaseChatRowText(Context context, EMMessage message, int position, BaseAdapter adapter) {
		super(context, message, position, adapter);
	}

	@Override
	protected void onInflateView() {
		inflater.inflate(message.direct() == EMMessage.Direct.RECEIVE ?
				R.layout.ease_row_received_message : R.layout.ease_row_sent_message, this);
	}

	@Override
	protected void onFindViewById() {
		contentView = (TextView) findViewById(R.id.tv_chatcontent);
        contentLayout = (BubbleLinearLayout) findViewById(R.id.bubble);
	}

    @Override
    public void onSetUpView() {
        EMTextMessageBody txtBody = (EMTextMessageBody) message.getBody();

        Spannable span = EaseSmileUtils.getSmiledText(context, txtBody.getMessage());
        // 设置内容
        contentView.setText(span, TextView.BufferType.SPANNABLE);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) contentLayout.getLayoutParams();
        TextPaint paint = contentView.getPaint();

        /**
         * 设置文本框尺寸，分三种情况
         * 1.文本小于最小尺寸，设置成最小尺寸
         * 2.文本大于最小尺寸小于屏幕宽度的3/4，自适应显示
         * 3.文本大于屏幕宽度，显示最大宽度为屏幕3/4
         */
        int len = (int) paint.measureText(contentView.getText().toString());
        if (len < SizeUtils.dp2px(context, 60)){
            layoutParams.width = (int) (len + SizeUtils.dp2px(getContext(), 50));
            layoutParams.height = LayoutParams.WRAP_CONTENT;
        } else if (len < ScreenUtils.getScreenWidth(context) * 3 / 4) {
            layoutParams.width = LayoutParams.WRAP_CONTENT;
            layoutParams.height = LayoutParams.WRAP_CONTENT;
        } else {
            layoutParams.width = ScreenUtils.getScreenWidth(context) * 3 / 4;
            layoutParams.height = LayoutParams.WRAP_CONTENT;
        }
        contentLayout.setLayoutParams(layoutParams);
        handleTextMessage();
    }

    protected void handleTextMessage() {
        if (message.direct() == EMMessage.Direct.SEND) {
            setMessageSendCallback();
            switch (message.status()) {
            case CREATE: 
                progressBar.setVisibility(View.GONE);
                statusView.setVisibility(View.VISIBLE);
                break;
            case SUCCESS:
                progressBar.setVisibility(View.GONE);
                statusView.setVisibility(View.GONE);
                break;
            case FAIL:
                progressBar.setVisibility(View.GONE);
                statusView.setVisibility(View.VISIBLE);
                break;
            case INPROGRESS:
                progressBar.setVisibility(View.VISIBLE);
                statusView.setVisibility(View.GONE);
                break;
            default:
               break;
            }
        }else{
            if(!message.isAcked() && message.getChatType() == ChatType.Chat){
                try {
                    EMClient.getInstance().chatManager().ackMessageRead(message.getFrom(), message.getMsgId());
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onUpdateView() {
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onBubbleClick() {
        // TODO Auto-generated method stub
    }
}
