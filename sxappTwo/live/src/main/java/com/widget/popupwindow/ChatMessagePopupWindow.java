package com.widget.popupwindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.live.R;
import com.live.adapter.MyOrderFragmentsAdapter;
import com.live.entity.FriendMessageInfo;
import com.live.utils.PopAnimUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 * 直播中—好友私信消息
 */
public class ChatMessagePopupWindow extends PopupWindow {
    private Context context;
    private ViewPager viewPager;
    private TextView tv_friend;
    private View line_friend;
    private TextView tv_no_concern;
    private View line_no_concern;
    private final View messageView;
    private View parent;
    private List<FriendMessageInfo> chatList = new ArrayList();
    private List<ListView> list = new ArrayList();
    private ChatContentPopupWindow chatContentPopupWindow;
    private List<String> titleList = new ArrayList();
    private List<Fragment> fragemntList = new ArrayList();
    private FragmentActivity activity;
    private final TabLayout tab_layout_title;


    public ChatMessagePopupWindow(Context context, View parent, FragmentActivity activity) {
        this.context = context;
        this.parent = parent;
        this.activity = activity;
        messageView = View.inflate(context, R.layout.dialog_live_message, null);
        tab_layout_title = (TabLayout)messageView. findViewById(R.id.tab_layout_title);
        viewPager = (ViewPager) messageView.findViewById(R.id.viewPager);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        messageView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.popshow_anim));
        setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(messageView);
        showAtLocation(parent, Gravity.CENTER | Gravity.BOTTOM, 10, 10);
        update();
        PopAnimUtil.dopPpDissmissAinm(context, messageView, this);
        initData();
    }

    public void showMessageDialog() {
        PopAnimUtil.dopPpDissmissAinm(context, messageView, this);
    messageView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.popshow_anim));
    showAtLocation(parent, Gravity.CENTER | Gravity.BOTTOM, 10, 10);
}

    private void initData() {

//        fragemntList.add(new FriendMessageFragment());
//        fragemntList.add(new IgnoreFriendMessageFragment());
        titleList.add("好友");
        titleList.add("未关注");

        //设置样式
        tab_layout_title.setTabMode(TabLayout.MODE_FIXED);
        //预加载fragment的个数
        viewPager.setOffscreenPageLimit(1);
        //设置标题
        tab_layout_title.addTab(tab_layout_title.newTab().setText(titleList.get(0)));
        tab_layout_title.addTab(tab_layout_title.newTab().setText(titleList.get(1)));

        MyOrderFragmentsAdapter myOrderFragmentsAdapter = new MyOrderFragmentsAdapter(activity.getSupportFragmentManager(), fragemntList, titleList);
        viewPager.setAdapter(myOrderFragmentsAdapter);
        tab_layout_title.setupWithViewPager(viewPager);
    }
}
