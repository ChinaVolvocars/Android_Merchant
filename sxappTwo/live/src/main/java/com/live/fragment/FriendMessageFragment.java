//package com.live.fragment;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Message;
//import android.support.annotation.Nullable;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//
//import com.hyphenate.easeui.utils.HandlerHelper;
//import com.hyphenate.easeui.utils.MyCallBack;
//import com.hzxmkuer.zhibogameplay.R;
//import com.hzxmkuer.zhibogameplay.activities.ChatActivity;
//import com.hzxmkuer.zhibogameplay.adapters.FriendMessageListAdapter;
//import com.hzxmkuer.zhibogameplay.base.BaseFragment;
//import com.hzxmkuer.zhibogameplay.dialog.MyDialog;
//import com.hzxmkuer.zhibogameplay.entitys.ChatListEntity;
//import com.hzxmkuer.zhibogameplay.helper.Constant;
//import com.hzxmkuer.zhibogameplay.http.HttpParameters;
//import com.hzxmkuer.zhibogameplay.http.HttpUrl;
//import com.hzxmkuer.zhibogameplay.info.LiveTouristInfo;
//import com.hzxmkuer.zhibogameplay.uitls.LogUtil;
//import com.hzxmkuer.zhibogameplay.uitls.SharedPreferencesUtil;
//import com.hzxmkuer.zhibogameplay.uitls.XutilsHttpClient;
//import com.hzxmkuer.zhibogameplay.weights.NoScrollListView;
//import com.hzxmkuer.zhibogameplay.weights.RefreshScrollviewLayout;
//import com.lidroid.xutils.http.client.HttpRequest;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Administrator on 2016/8/26.
// * 消息—好友
// */
//public class FriendMessageFragment extends BaseFragment implements
//        SwipeRefreshLayout.OnRefreshListener,
//        RefreshScrollviewLayout.OnLoadListener {
//    private NoScrollListView listView;
//    private RefreshScrollviewLayout refresh;
//    private List<ChatListEntity.DataEntity.ListEntity> list = new ArrayList();
//    private String uid, hashId;
//    private int page = 1;
//    private FriendMessageListAdapter adapter;
//    //底部布局
//    private RelativeLayout mfooterLayout;
//    private ImageView mNoDataIv;
//    private ChatListEntity chatListEntity = new ChatListEntity();
//    private HandlerHelper handlerHelper = new HandlerHelper(this) {
//        @Override
//        protected void handleMessage(Object reference, Message msg) {
//            if (((FragmentActivity) context).isFinishing()) {
//                return;
//            }
//            switch (msg.what) {
//                case 2:
//                    mfooterLayout.setVisibility(View.GONE);
//                    refresh.setRefreshing(false);
//                    refresh.setLoading(false);
//                    break;
//                case 100:
//                    MyDialog.cancleDialog();
//                    chatListEntity = (ChatListEntity) msg.obj;
//                    int remainder = chatListEntity.getData().getRemainder();
//                    if (remainder > 0) {
//                        refresh.setOnLoadListener(FriendMessageFragment.this);
//                    } else {
//                        refresh.setOnLoadListener(null);
//                    }
//                    if (page == 1) {
//                        list = chatListEntity.getData().getList();
//                        adapter = new FriendMessageListAdapter(list, getActivity());
//                        listView.setAdapter(adapter);
//                    } else {
//                        List<ChatListEntity.DataEntity.ListEntity> listEntities = chatListEntity.getData().getList();
//                        list.addAll(listEntities);
//                        adapter.notifyDataSetChanged();
//                    }
//                    break;
//                case 101:
//                    MyDialog.cancleDialog();
//                    break;
//                case 600:
//                    if (msg.obj != null) {
//                        LiveTouristInfo touristInfo = (LiveTouristInfo) msg.obj;
//                        if (0 == touristInfo.getCode()) {
//                            MyDialog.cancleDialog();
//                            String no_follow_send = touristInfo.getData().getNo_follow_send();//私信限制
//                            //是否已经关注过此观众
//                            int is_follow = touristInfo.getData().getIs_follow();
//                            if ("0".equals(no_follow_send)) {
//                                if (0 == is_follow) {
//                                    //有私信限制
//                                    showToast("您当前没有权限给对方发送私信");
//
//                                } else {
//                                    //可以发送私信
//                                    sendMessageToFriend();
//                                }
//
//                            } else {
//                                //可以发送私信
//                                sendMessageToFriend();
//                            }
//
//                        }
//                    }
//
//                    break;
//                case 601:
//                    MyDialog.cancleDialog();
//                    break;
//            }
//        }
//    };
//    private String face;
//    private String nickname;
//    private String hx_username;
//    private String fid;
//    private void sendMessageToFriend() {
//        Intent intent = new Intent(context, ChatActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("hx_username", hx_username);
//        bundle.putString("nick_name", nickname);
//        bundle.putString("face", face);
//        bundle.putString("fid", fid);
//        bundle.putBoolean("from_notify", false);
//        intent.putExtras(bundle);
//        startActivity(intent);
//
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = View.inflate(context, R.layout.fragment_friend_message, null);
//        uid = SharedPreferencesUtil.getInstance(getActivity().getApplicationContext()).getString(Constant.KEY_UID, "");
//        hashId = SharedPreferencesUtil.getInstance(getActivity().getApplicationContext()).getString(Constant.KEY_HASHID, "");
//        initView(view);
//        initData();
//        initListener();
//        return view;
//    }
//
//    private void initView(View view) {
//        listView = (NoScrollListView) view.findViewById(R.id.listView);
//        refresh = (RefreshScrollviewLayout) view.findViewById(R.id.refresh);
//        refresh.setColorSchemeColors(context.getResources().getColor(R.color.home_yellow));
//        mNoDataIv = (ImageView) view.findViewById(R.id.iv_no_data);
//        //给listVIew加脚
//        View moreView = getActivity().getLayoutInflater().inflate(R.layout.xlistview_footer, null);
//        mfooterLayout = (RelativeLayout) moreView.findViewById(R.id.xlistview_footer_content);
//        mfooterLayout.setVisibility(View.GONE);
//        listView.addFooterView(moreView);
//        listView.setEmptyView(mNoDataIv);
//        EventBus.getDefault().register(this);
//        listView.setFocusable(false);
//        refresh.setFocusable(false);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        chatList();
//    }
//
//    private void initData() {
//    }
//
//    private void initListener() {
//        refresh.setOnRefreshListener(this);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                fid = list.get(position).getUid();
//                face = list.get(position).getFace();
//                nickname = list.get(position).getNickname();
//                hx_username = list.get(position).getHx_username();
//                getUserMessage();
//
//            }
//        });
//    }
//
//    private void getUserMessage() {
//        //获取用户信息，查看当前状态是否权限给对方发私信
//        MyDialog.createLoadingDialog(context).show();
//        XutilsHttpClient.getInstence(context.getApplicationContext()).send(HttpRequest.HttpMethod.POST, HttpUrl.getUserDetail,
//                HttpParameters.getInstence(context.getApplicationContext()).getUserDetial(uid, hashId, fid), new com.hzxmkuer.zhibogameplay.callback.MyCallBack(new LiveTouristInfo(), handlerHelper, 600));
//    }
//
//    @Override
//    public void onLoad() {
//        page++;
//        chatList();
//        mfooterLayout.setVisibility(View.VISIBLE);
//        handlerHelper.sendEmptyMessageDelayed(2, 1500);
//    }
//
//    @Override
//    public void onRefresh() {
//        page = 1;
//        chatList();
//        handlerHelper.sendEmptyMessageDelayed(2, 1500);
//    }
//
//    private void chatList() {
//        MyDialog.createLoadingDialog(getActivity()).show();
//        XutilsHttpClient.getInstence(getActivity().getApplicationContext()).send(HttpRequest.HttpMethod.POST,
//                HttpUrl.chatList,
//                HttpParameters.getInstence(getActivity().getApplicationContext()).chatList(uid, hashId, "1", page + ""),
//                new MyCallBack(chatListEntity, handlerHelper, 100));
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void hiEvenBus(String message) {
//        LogUtil.e("Friend11111---");
//        adapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//    }
//}
