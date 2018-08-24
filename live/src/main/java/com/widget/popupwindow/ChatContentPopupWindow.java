package com.widget.popupwindow;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.common.widget.editview.DeleteEditText;
import com.example.live.R;
import com.live.adapter.GiftGridViewAdapter;
import com.live.entity.GiftInfo;
import com.live.utils.PopAnimUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/14.
 */
public class ChatContentPopupWindow extends PopupWindow implements View.OnClickListener {
    private Context context;
    private View parent;
    private final View chatContentView;
    private final TextView tv_chatFriend_name;
    private final ImageView iv_back;
    private final ImageView iv_close;
    private final ListView iv_chat_message_content;
    private final ImageView iv_input_emoji;
    private final ImageView iv_input_more;
    private final Button btn_send_content;
    private final DeleteEditText ed;
    private ChatMessagePopupWindow chatMessagePopupWindow;
    private final Animation animationSetShow;
    private final Animation animationSetDismiss;
    private final FrameLayout fl_chat_more;
    private final View chatGiftView;
    private final ImageView iv_send_giftMessage;
    private final ViewPager vp_gift;
    private final LinearLayout ll_point_group;
    private List<GridView> giftGritViewList = new ArrayList();
    private int lastPositioin;
    private List<ImageView> points = new ArrayList();
    private List<GiftInfo> giftList = new ArrayList();
    private final View giftContentView;
    private final RelativeLayout rl_send_giftMessage;

    public ChatContentPopupWindow(Context context, View parent) {
        this.context = context;
        this.parent = parent;
        chatContentView = View.inflate(context, R.layout.pop_chat_content, null);
        iv_back = (ImageView) chatContentView.findViewById(R.id.iv_back);//返回
        tv_chatFriend_name = (TextView) chatContentView.findViewById(R.id.tv_chatFriend_name);//聊天好友名字
        iv_close = (ImageView) chatContentView.findViewById(R.id.iv_close);//关闭
        iv_chat_message_content = (ListView) chatContentView.findViewById(R.id.iv_chat_message_content);
        ed = (DeleteEditText) chatContentView.findViewById(R.id.ed);//聊天输入框
        iv_input_emoji = (ImageView) chatContentView.findViewById(R.id.iv_input_emoji);//发送表情
        iv_input_more = (ImageView) chatContentView.findViewById(R.id.iv_input_more);//更多
        btn_send_content = (Button) chatContentView.findViewById(R.id.btn_send_content);//发送
        fl_chat_more = (FrameLayout) chatContentView.findViewById(R.id.fl_chat_more);//更多父容器
        chatGiftView =  View.inflate(context, R.layout.imageview_chat_send_gift, null);//私聊发送礼物
        iv_send_giftMessage = (ImageView) chatGiftView.findViewById(R.id.iv_send_giftMessage);
        rl_send_giftMessage = (RelativeLayout) chatGiftView.findViewById(R.id.rl_send_giftMessage);
        giftContentView = View.inflate(context, R.layout.pop_gift, null);
        RelativeLayout rl_send_pay_gift = (RelativeLayout) giftContentView.findViewById(R.id.rl_send_pay_gift);
        rl_send_pay_gift.setBackgroundColor(context.getResources().getColor(R.color.white));
        vp_gift = (ViewPager) giftContentView.findViewById(R.id.vp_gift);
        vp_gift.setBackgroundColor(context.getResources().getColor(R.color.white));
        ll_point_group = (LinearLayout) giftContentView.findViewById(R.id.ll_point);
        ll_point_group.setBackgroundColor(context.getResources().getColor(R.color.white));
        initGiftGridData();
        animationSetShow = AnimationUtils.loadAnimation(context, R.anim.popshow_anim);
        animationSetDismiss = AnimationUtils.loadAnimation(context, R.anim.pophidden_anim);
        chatContentView.startAnimation(animationSetShow);
        setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(ContextCompat.getDrawable(context, R.color.transparent));
        setContentView(chatContentView);
        showAtLocation(parent, Gravity.CENTER | Gravity.BOTTOM, 10, 10);
        update();
        initListener();
        PopAnimUtil.dopPpDissmissAinm(context,chatContentView,this);
}

    private void initGiftGridData() {
        initPoint();
    }

    private void initPoint() {
        points.clear();
        ll_point_group.removeAllViews();
        for (int i = 0; i < 4; i++) {
            ImageView imagePoint = new ImageView(context);
            imagePoint.setImageResource(R.drawable.point_gray_bg);
            points.add(imagePoint);
            if (i == 0) {
                imagePoint.setEnabled(true);
            } else {
                imagePoint.setEnabled(false);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, -2);
            layoutParams.leftMargin = 20;
            ll_point_group.addView(imagePoint, layoutParams);
        }
        //加载礼物
        GiftGridViewAdapter giftGridViewAdapter = new GiftGridViewAdapter(giftGritViewList);
        vp_gift.setAdapter(giftGridViewAdapter);
    }

    private void initListener() {
        iv_back.setOnClickListener(this);
        iv_close.setOnClickListener(this);
        iv_input_more.setOnClickListener(this);
        iv_send_giftMessage.setOnClickListener(this);
        iv_input_emoji.setOnClickListener(this);
        animationSetDismiss.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                if(this!=null&&isShowing()){
                    dismiss();
                }

            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence content, int start, int before, int count) {//改变中
            }

            @Override
            public void afterTextChanged(Editable s) {
                String content = s.toString();
                if (content != null && !"".equals(content)) {
                    btn_send_content.setVisibility(View.VISIBLE);
                    iv_input_more.setVisibility(View.GONE);
                } else {
                    btn_send_content.setVisibility(View.GONE);
                    iv_input_more.setVisibility(View.VISIBLE);
                }
            }
        });


        vp_gift.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
            @Override
            public void onPageSelected(int position) {
                ll_point_group.getChildAt(lastPositioin).setEnabled(false);
                ll_point_group.getChildAt(position).setEnabled(true);
                lastPositioin = position;
            }

        });

        this.setOnDismissListener(new OnDismissListener() {//此pop消失的监听
            @Override
            public void onDismiss() {
                fl_chat_more.removeAllViews();//移除所有子view并隐藏
                fl_chat_more.setVisibility(View.GONE);
            }
        });
    }

    public void showChatContentWindow() {
        PopAnimUtil.dopPpDissmissAinm(context,chatContentView,this);
        chatContentView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.popshow_anim));
        showAtLocation(parent, Gravity.CENTER | Gravity.BOTTOM, 10, 10);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_back) {
            if (this != null && isShowing()) {
                chatContentView.startAnimation(animationSetDismiss);
//                    dismiss();
                if (chatMessagePopupWindow == null) {
//                        chatMessagePopupWindow = new ChatMessagePopupWindow(context, parent);
                } else {
                    chatMessagePopupWindow.showMessageDialog();
                }
            }

        } else if (i == R.id.iv_close) {
            if (this != null && isShowing()) {
                chatContentView.startAnimation(animationSetDismiss);
//                    dismiss();
            }

        } else if (i == R.id.iv_input_more) {
            fl_chat_more.setVisibility(View.VISIBLE);
            fl_chat_more.removeAllViews();
            fl_chat_more.addView(rl_send_giftMessage);

        } else if (i == R.id.iv_send_giftMessage) {
            fl_chat_more.setVisibility(View.VISIBLE);
            fl_chat_more.removeAllViews();
            fl_chat_more.addView(giftContentView);

        } else if (i == R.id.iv_input_emoji) {
            Toast.makeText(context, "发送表情！", Toast.LENGTH_SHORT).show();

        }
    }
}
