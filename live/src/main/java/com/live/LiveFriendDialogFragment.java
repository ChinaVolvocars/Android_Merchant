package com.live;


import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.live.R;
import com.hyphenate.chat.EMClient;
import com.live.adapter.MyOrderFragmentsAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
public class LiveFriendDialogFragment extends DialogFragment {
    private List<String> titleList = new ArrayList();
    private List<Fragment> fragemntList = new ArrayList();
    private TabLayout tab_layout_title;
    private ViewPager viewPager;
    private FragmentActivity activity;
    private View messageView;
    private Window window;
    private TextView tv_do_ignore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //去出标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        //放置位置
        window = getDialog().getWindow();
        window.setGravity(Gravity.BOTTOM | Gravity.BOTTOM);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.CustomDialog;
        messageView = inflater.inflate(R.layout.dialog_live_message, ((ViewGroup) window.findViewById(android.R.id.content)), false);
        //忽略
        tv_do_ignore = (TextView) messageView.findViewById(R.id.tv_do_ignore);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//注意此处
        tab_layout_title = (TabLayout) messageView.findViewById(R.id.tab_layout_title);
        viewPager = (ViewPager) messageView.findViewById(R.id.viewPager);

        messageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        tv_do_ignore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EMClient.getInstance().chatManager().markAllConversationsAsRead();
                EventBus.getDefault().post("1");
            }
        });

            initData();

        return messageView;
    }


//    public LiveFriendDialogFragment(FragmentActivity activity) {
//        this.activity = activity;
//    }

    private void initData() {
//        fragemntList.add(new FriendMessageFragment());
//        fragemntList.add(new IgnoreFriendMessageFragment());
        titleList.add("好友");
        titleList.add("未关注");

//        设置样式
        tab_layout_title.setTabMode(TabLayout.MODE_FIXED);
//        预加载fragment的个数
        viewPager.setOffscreenPageLimit(fragemntList.size());
//        设置标题
        tab_layout_title.addTab(tab_layout_title.newTab().setText(titleList.get(0)));
        tab_layout_title.addTab(tab_layout_title.newTab().setText(titleList.get(1)));
        MyOrderFragmentsAdapter myOrderFragmentsAdapter = new MyOrderFragmentsAdapter(getChildFragmentManager(), fragemntList, titleList);
        viewPager.setAdapter(myOrderFragmentsAdapter);
        tab_layout_title.setupWithViewPager(viewPager);
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        fragemntList.clear();
        titleList.clear();
    }
}
