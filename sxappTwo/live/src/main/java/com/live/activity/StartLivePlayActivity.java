package com.live.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.DataCenter;
import com.common.utils.SoftHideKeyBoardUtil;
import com.common.widget.imageview.image.ImageLoaderUtils;
import com.example.live.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMChatRoomChangeListener;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.EMMessageListener;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.live.adapter.ChatRoomAdapter;
import com.live.entity.RoomChatContentInfo;
import com.qiniu.pili.droid.rtcstreaming.RTCMediaStreamingManager;
import com.qiniu.pili.droid.streaming.AVCodecType;
import com.qiniu.pili.droid.streaming.AudioSourceCallback;
import com.qiniu.pili.droid.streaming.CameraStreamingSetting;
import com.qiniu.pili.droid.streaming.MediaStreamingManager;
import com.qiniu.pili.droid.streaming.StreamStatusCallback;
import com.qiniu.pili.droid.streaming.StreamingPreviewCallback;
import com.qiniu.pili.droid.streaming.StreamingProfile;
import com.qiniu.pili.droid.streaming.StreamingSessionListener;
import com.qiniu.pili.droid.streaming.StreamingState;
import com.qiniu.pili.droid.streaming.StreamingStateChangedListener;
import com.qiniu.pili.droid.streaming.SurfaceTextureCallback;
import com.qiniu.pili.droid.streaming.widget.AspectFrameLayout;
import com.widget.gles.FBO;
import com.widget.heartview.HeartLayout;
import com.widget.playvideoview.CameraPreviewFrameView;

import org.json.JSONObject;

import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 开启直播视频推流
 */
public class StartLivePlayActivity extends BaseMvpActivity implements View.OnClickListener, EMMessageListener
        , StreamingStateChangedListener, StreamStatusCallback, StreamingPreviewCallback, SurfaceTextureCallback, AudioSourceCallback, CameraPreviewFrameView.Listener,
        StreamingSessionListener ,EMChatRoomChangeListener {
    private static final int MESSAGE_SUCCESS = 1992;
    private ImageView iv_close_play;
    private ImageView iv_anchorLogo;
    private TextView tv_anchor_num;
    private ListView chat_listview;
    private ImageView iv_anchor_chat;
    private RelativeLayout rl_controls;
    private EditText et_input_content;
    private RelativeLayout rl_editor;
    private CameraPreviewFrameView glSurfaceView;
    private AspectFrameLayout afl;
    private MediaStreamingManager mediaStreamingManager;
    private CameraStreamingSetting streamSetting;
    private JSONObject jsonObject;
    private FBO mFBO = new FBO();
    protected boolean mIsReady = false;
    private View goBackView;
    private AlertDialog goBackDialog;
    private HeartLayout mHeartLayout;
    private ViewPager viewPager;
    private String sendContent;
    private ChatRoomAdapter chatRoomAdapter;
    private int page = 1;
    private String userNickname;
    private String userLevel = "0";
    //type:1-普通聊天消息，2-第一次点亮，3—直播提示。4-非第一次点亮
    private int type;
    private List<RoomChatContentInfo> chatRoomContentList = new ArrayList();
    // 当前会话对象
    private EMConversation mConversation;
    private String anchorNickName = "";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 113:
                    joinChatRoom();
                    break;
                case 1:
                     /* EMTextMessageBody body = (EMTextMessageBody) emMessage.getBody();
            String message = body.getMessage();*/
                    if (msg.obj != null) {
                        EMMessage message = (EMMessage) msg.obj;
                        try {
                            EMTextMessageBody body = (EMTextMessageBody) message.getBody();
                            String sendContent = body.getMessage();
                            String userName = message.getStringAttribute("userName");
                            int messageType = Integer.parseInt(message.getStringAttribute("messageType"));
                            if (messageType == 1) {
                                roomChatContentInfo = new RoomChatContentInfo(userName, sendContent, messageType);
                                setChatListMessage(roomChatContentInfo);
                            } else if (messageType == 2) {
                                roomChatContentInfo = new RoomChatContentInfo(userName, "进入了房间", messageType);
                                setChatListMessage(roomChatContentInfo);
                            }else if (messageType == 3){
                                mHeartLayout.addFavor();
                            }else if (messageType == 4){
                               tv_anchor_num.setText("交易额：￥"+sendContent);
                            }else if (messageType == 5){
                                mediaStreamingManager.stopStreaming(); // 停止推流，调用成功后，SDK 会停止推送本地音视频流到"流媒体服务器"
                                finish();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    };
    private EMMessage mMessage;
    private RoomChatContentInfo roomChatContentInfo;
    private String hx_chatroomsid;
    private TextView btn_send_content;
    private TextView mName;
    private TextView mNum;


    @Override
    protected void setStatusBar() {
        SoftHideKeyBoardUtil.assistActivity((Activity) context);
    }

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_start_live_play;
    }

    @Override
    protected void onViewCreated() {
        EMClient.getInstance().addConnectionListener(new MyConnectionListener());
        EMClient.getInstance().chatroomManager().addChatRoomChangeListener(this);
        initView();
    }

    @Override
    protected void doLogicFunc() {
        EMClient.getInstance().chatManager().addMessageListener(this);
        RTCMediaStreamingManager.init(context);
        initStreamConfig();
        initData();
        initListener();
        boolean connected = EMClient.getInstance().isConnected();
        Log.d("EMMessage", "connected:" + connected);
    }


    private void initView() {
        afl = (AspectFrameLayout) findViewById(R.id.cameraPreview_afl);
        afl.setShowMode(AspectFrameLayout.SHOW_MODE.FULL);//屏幕大小模式
        glSurfaceView = (CameraPreviewFrameView) findViewById(R.id.cameraPreview_surfaceView);//七牛采集视频view
        iv_close_play = (ImageView) findViewById(R.id.iv_close_play);//关闭直播
        iv_anchorLogo = (ImageView) findViewById(R.id.iv_anchorLogo);//主播头像
        mName = (TextView) findViewById(R.id.name);
        mNum = (TextView) findViewById(R.id.name);
        ImageLoaderUtils.displayCircle(iv_anchorLogo,getIntent().getStringExtra("face"));
        hx_chatroomsid = getIntent().getStringExtra("roomid");
        anchorNickName = getIntent().getStringExtra("name");
        mName.setText(anchorNickName);
        tv_anchor_num = (TextView) findViewById(R.id.tv_anchor_num);//money
        mHeartLayout = (HeartLayout) findViewById(R.id.rl_heartLayout);//飘心布局
        chat_listview = (ListView) findViewById(R.id.chat_listview);//聊天列表
        iv_anchor_chat = (ImageView) findViewById(R.id.iv_anchor_chat);//发送信息
        rl_controls = (RelativeLayout) findViewById(R.id.rl_controls);//底部总体控制布局
        et_input_content = (EditText) findViewById(R.id.et_input_content);//发送文字信息输入框
        btn_send_content = (TextView) findViewById(R.id.btn_send_content);//发送信息
        rl_editor = (RelativeLayout) findViewById(R.id.rl_editor);//整体的文字编辑布局
        boolean loggedInBefore = EMClient.getInstance().isLoggedInBefore();
        Log.d("EMMessage", "loggedInBefore:" + loggedInBefore);
        if (chatRoomContentList.size() > 0) {
            chatRoomContentList.clear();
        }
    }

    private void initData() {
        StreamingProfile mProfile = new StreamingProfile();
        mProfile.setVideoQuality(StreamingProfile.AUDIO_QUALITY_MEDIUM2)//音频质量相关参数的一个抽象，暂定中间值
                .setAudioQuality(StreamingProfile.AUDIO_QUALITY_MEDIUM2)
                .setEncodingSizeLevel(StreamingProfile.VIDEO_ENCODING_HEIGHT_480)//编码时候的size
                .setEncoderRCMode(StreamingProfile.EncoderRCModes.QUALITY_PRIORITY);//质量优先
        try {
            mProfile.setPublishUrl(getIntent().getStringExtra("url"));
        } catch (URISyntaxException e) {
        }
        mediaStreamingManager = new MediaStreamingManager(this, afl, glSurfaceView, AVCodecType.SW_VIDEO_WITH_SW_AUDIO_CODEC);//视频软编、音频软编
        mediaStreamingManager.prepare(streamSetting, mProfile);
        mediaStreamingManager.setStreamingProfile(mProfile);
        mediaStreamingManager.startStreaming();
        if (!TextUtils.isEmpty(hx_chatroomsid) && !TextUtils.isEmpty(DataCenter.HXuser) && !TextUtils.isEmpty(DataCenter.HXpas)) {
            doLoginHX(DataCenter.HXuser, DataCenter.HXpas);
        }
        }
    private void doLoginHX(String hx_userName, String hx_password) {
        EMClient.getInstance().login(hx_userName, hx_password, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                Log.d("EMMessage", "登录聊天服务器成功！");
                joinChatRoom();
            }

            @Override
            public void onProgress(int progress, String status) {
            }

            @Override
            public void onError(int code, String message) {
                Log.d("EMMessage", "登录聊天服务器失败！");
            }
        });
    }
    private void initListener() {
        iv_anchor_chat.setOnClickListener(this);
        btn_send_content.setOnClickListener(this);

        if (mediaStreamingManager != null) {
            mediaStreamingManager.setStreamingStateListener(this);
            mediaStreamingManager.setSurfaceTextureCallback(this);
            mediaStreamingManager.setStreamingSessionListener(this);
            mediaStreamingManager.setStreamStatusCallback(this);
            mediaStreamingManager.setStreamingPreviewCallback(this);
            mediaStreamingManager.setAudioSourceCallback(this);
            mediaStreamingManager.setSurfaceTextureCallback(this);
            mediaStreamingManager.setStreamingPreviewCallback(this);
        }
        iv_close_play.setOnClickListener(this);//关闭直播界面
        //注册一个监听连接状态的listener
    }


    private void initStreamConfig() {
        //createRTCMediaStream();
        streamSetting = new CameraStreamingSetting();
        streamSetting.setCameraId(Camera.CameraInfo.CAMERA_FACING_FRONT)
                .setContinuousFocusModeEnabled(true)//关闭自动对焦功能
                .setRecordingHint(false)//以此来提升数据源的帧率，有画面卡顿的风险
                .setBuiltInFaceBeautyEnabled(true)
//                .setResetTouchFocusDelayInMs(3000)//自动对焦的间隔时间
//                .setFocusMode(CameraStreamingSetting.FOCUS_MODE_CONTINUOUS_PICTURE)
                .setCameraPrvSizeLevel(CameraStreamingSetting.PREVIEW_SIZE_LEVEL.SMALL)
                .setCameraPrvSizeRatio(CameraStreamingSetting.PREVIEW_SIZE_RATIO.RATIO_16_9)
                .setFaceBeautySetting(new CameraStreamingSetting.FaceBeautySetting(1.0f, 1.0f, 0.8f))
                .setVideoFilter(CameraStreamingSetting.VIDEO_FILTER_TYPE.VIDEO_FILTER_BEAUTY);
    }

    private RTCMediaStreamingManager mMediaStreamingManager;


    @Override
    protected void onPause() {
        super.onPause();
        mIsReady = false;
        mediaStreamingManager.pause();
        if (mMediaStreamingManager == null) {
            return;
        }
        //mMediaStreamingManager.stopCapture();
    }


    @Override
    protected void onResume() {
        //mIsReady = true;
        super.onResume();
        mediaStreamingManager.resume();
        if (mMediaStreamingManager == null) {
            return;
        }
        mMediaStreamingManager.startCapture();
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mMediaStreamingManager == null) {
            return;
        }
        mMediaStreamingManager.stopConference();
    }
    @Override
    protected void onViewClicked(View view) {
    }
    @Override
    public void onClick(View v) {
        int i = v.getId();
         if (i == R.id.iv_anchor_chat) {
            rl_editor.setVisibility(View.VISIBLE);
            rl_controls.setVisibility(View.GONE);
        }    else if (i == R.id.iv_close_play) {
            showgoBackDialog();
        } else if (i == R.id.btn_send_content) {
            sendContent = et_input_content.getText().toString().trim();
            if (TextUtils.isEmpty(sendContent)) {
                showToastMsg("发送消息不能为空");
                return;
            }
                type = 1;
                sendChatMessage(sendContent);
        } else if (i == R.id.tv_cancal) {
            if (goBackDialog != null && goBackDialog.isShowing()) {
                goBackDialog.dismiss();
            }
        } else if (i == R.id.tv_sure) {
            if (goBackDialog != null && goBackDialog.isShowing()) {
                goBackDialog.dismiss();
            }
            mediaStreamingManager.stopStreaming(); // 停止推流，调用成功后，SDK 会停止推送本地音视频流到"流媒体服务器"
            finish();
        }
    }

    private void LeaveBean() {//下播
       /* showProgressingDialog();
        CommonSubscriber<LeaveBean> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                dismissProgressDialog();
                final LeaveBean lea = (LeaveBean) o;
                if (goBackDialog != null && goBackDialog.isShowing()) {
                    goBackDialog.dismiss();
                }
                mHandler.removeMessages(REFRESH_ROOM_DATA);
                danmuShowManager.clearDanmu();
                giftShowManager.clearGift();
                sendChatMessage("主播下播");
                mHandler.removeCallbacksAndMessages(null);//停止定时轮循
                finish();
                Intent intent = new Intent(context, VideoOverOfAudience.class);
                intent.putExtra("totalNum", lea.sums);
                intent.putExtra("totalml", lea.ticket);
                intent.putExtra("anchorFid", DataCenter.UserId + "");
                startActivity(intent);
            }

            @Override
            public void onError(String e, int code) {
                finish();
            }
        });
        StreamMethods.getInstance().leaveStream(subscriber);*/
    }
    private void sendChatMessage(final String sendContent) {//发送聊天室信息
        /*StringBuilder sendContentBuilder = new StringBuilder();
        sendContentBuilder.append("{");
        sendContentBuilder.append("\"" + "userName" + "\"" + ":" + "\"" + anchorNickName + "\"" + ",");
        sendContentBuilder.append("\"" + "sendContent" + "\"" + ":" + "\"" + sendContent + "\"" + ",");
        sendContentBuilder.append("\"" + "messageType" + "\"" + ":" + "\"" + type + "\"");
        sendContentBuilder.append("}");
        Log.d("stringBuilder", "stringBuilder:" + sendContentBuilder.toString());*/
        mMessage = EMMessage.createTxtSendMessage(sendContent, hx_chatroomsid);
        mMessage.setAttribute("userName",anchorNickName);
        mMessage.setAttribute("messageType",type+"");
        mMessage.setChatType(EMMessage.ChatType.ChatRoom);
        EMClient.getInstance().chatManager().sendMessage(mMessage);
//        mConversation.appendMessage(message);
        mMessage.setMessageStatusCallback(new EMCallBack() {
            @Override
            public void onSuccess() {
                Log.d("EMMessage", "发送信息成功");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        roomChatContentInfo = new RoomChatContentInfo(anchorNickName, et_input_content.getText().toString(), type);
                        setChatListMessage(roomChatContentInfo);
                        et_input_content.setText("");
                        rl_editor.setVisibility(View.GONE);
                        rl_controls.setVisibility(View.VISIBLE);
                    }
                });
            }

            @Override
            public void onError(final int i,final String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("aaaaaa"+i+s);
                        showToastMsg("发送信息失败,请重试！");
                    }
                });

            }

            @Override
            public void onProgress(int i, String s) {
            }
        });
    }


    private void joinChatRoom() {//加入由主播创建的聊天室roomId为聊天室ID
        EMClient.getInstance().chatroomManager().joinChatRoom(hx_chatroomsid, new EMValueCallBack<EMChatRoom>() {
            @Override
            public void onSuccess(EMChatRoom value) {
                //加入聊天室成功
                Log.d("EMMessage", "加入聊天室成功");
                handler.removeMessages(113);
            }

            @Override
            public void onError(final int error, String chatroom) {
                //加入聊天室失败
                Log.d("EMMessage", "加入聊天室失败:" + chatroom.toString());
                handler.sendEmptyMessageDelayed(113, 5000);
            }
        });
        EMChatRoom emChatRoom = new EMChatRoom();
    }

    @Override
    protected void onDestroy() {
        mediaStreamingManager.pause();
        mediaStreamingManager.stopStreaming();
        mediaStreamingManager.destroy();
        EMClient.getInstance().chatroomManager().leaveChatRoom(hx_chatroomsid);//退出聊天室
        EMClient.getInstance().chatManager().removeMessageListener(this);//移除接收消息的监听
        super.onDestroy();
    }

    //    开始推流，调用成功后，SDK 开始推送本地音视频流到"流媒体服务器"
    @Override
    public void onStateChanged(StreamingState streamingState, Object o) {//推流状态发生改变的回调
        if (streamingState.equals(StreamingState.READY) || streamingState.equals(StreamingState.TORCH_INFO) || streamingState.equals(StreamingState.SHUTDOWN)) {//准备好播放
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (mediaStreamingManager != null) {
                        mediaStreamingManager.startStreaming();
                        handler.sendEmptyMessage(2232);
                    }
                }
            }).start();

        } else if (streamingState.equals(StreamingState.CONNECTING)) {
            Log.d("EMMessage", "CONNECTING");

        } else if (streamingState.equals(StreamingState.STREAMING)) {
            Log.d("EMMessage", "STREAMING");
        }
        /*else if (streamingState.equals(StreamingState.SHUTDOWN)) {
            Log.d("EMMessage", "SHUTDOWN");
        } */
        else if (streamingState.equals(StreamingState.IOERROR)) {
            Log.d("EMMessage", "IOERROR");
//            handler.sendEmptyMessage(STREAING_ERROR);

        } else if (streamingState.equals(StreamingState.SENDING_BUFFER_EMPTY)) {
            Log.d("EMMessage", "SENDING_BUFFER_EMPTY");
        } else if (streamingState.equals(StreamingState.SENDING_BUFFER_FULL)) {
            Log.d("EMMessage", "SENDING_BUFFER_FULL");
        } else if (streamingState.equals(StreamingState.AUDIO_RECORDING_FAIL)) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mediaStreamingManager != null) {
                        showToastMsg("麦克风打开失败,请检查设置！");
                    }
                }
            });

        } else if (streamingState.equals(StreamingState.OPEN_CAMERA_FAIL)) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mediaStreamingManager != null) {
                        showToastMsg("摄像头打开失败,请检查设置！");
                    }
                }
            });
        } else if (streamingState.equals(StreamingState.DISCONNECTED)) {
            Log.d("EMMessage", "DISCONNECTED");
//            handler.sendEmptyMessage(STREAING_ERROR);
        } else if (streamingState.equals(StreamingState.INVALID_STREAMING_URL)) {
        }
    }

    @Override
    public void onSurfaceCreated() {
        Log.e("TAG", "onSurfaceCreated");
        mFBO.initialize(this);
    }

    @Override
    public void onSurfaceChanged(int width, int height) {
        Log.e("TAG", "onSurfaceChanged width:" + width + ",height:" + height);
        mFBO.updateSurfaceSize(width, height);
    }

    @Override
    public void onSurfaceDestroyed() {
        Log.e("TAG", "onSurfaceDestroyed");
        mFBO.release();
    }

    @Override
    public int onDrawFrame(int texId, int texWidth, int texHeight, float[] transformMatrix) {
        int newTexId = mFBO.drawFrame(texId, texWidth, texHeight);
        return newTexId;
    }

    @Override
    public void onAudioSourceAvailable(ByteBuffer byteBuffer, int i, long l, boolean b) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        if (mIsReady) {
            mediaStreamingManager.doSingleTapUp((int) e.getX(), (int) e.getY());
            return true;
        }
        return false;
    }

    @Override
    public boolean onZoomValueChanged(float factor) {
        return false;
    }

    @Override
    public void notifyStreamStatusChanged(StreamingProfile.StreamStatus streamStatus) {

    }

    @Override
    public boolean onRecordAudioFailedHandled(int i) {
        return false;
    }

    @Override
    public boolean onRestartStreamingHandled(int i) {
        return false;
    }

    @Override
    public Camera.Size onPreviewSizeSelected(List<Camera.Size> list) {
        return null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (rl_editor.getVisibility() == View.VISIBLE) {
                rl_editor.setVisibility(View.GONE);
                rl_controls.setVisibility(View.VISIBLE);
            } else {
                showgoBackDialog();
            }
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void showgoBackDialog() {//提示用户是否确认退出直播
        if (goBackDialog == null) {
            goBackDialog = new AlertDialog.Builder(this).create();
            goBackView = View.inflate(context, R.layout.dialog_go_back, null);
            goBackDialog.setCancelable(true);
            goBackDialog.show();
        } else {
            goBackDialog.show();
        }
        Window windowDeleteBlack = goBackDialog.getWindow();
        windowDeleteBlack.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        windowDeleteBlack.setContentView(goBackView);
        windowDeleteBlack.setBackgroundDrawableResource(R.color.transparent);
        TextView tv_cancal = (TextView) goBackView.findViewById(R.id.tv_cancal);
        TextView tv_sure = (TextView) goBackView.findViewById(R.id.tv_sure);
        tv_cancal.setOnClickListener(this);
        tv_sure.setOnClickListener(this);
    }

    @Override
    public void onMessageReceived(final List<EMMessage> list) {//接收的新消息
        EMMessage emMessage = list.get(list.size() - 1);
        System.out.println("aaaaaaaaaa"+emMessage.toString());
        try {
            Message msg = handler.obtainMessage();
            msg.what = 1;
            msg.obj = emMessage;
            handler.sendMessage(msg);
        } catch (Exception e) {
        }
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> list) {
        EMMessage emMessage = list.get(list.size() - 1);
    }

    @Override
    public void onMessageRead(List<EMMessage> list) {
    }

    @Override
    public void onMessageDelivered(List<EMMessage> list) {
    }


    @Override
    public void onMessageChanged(EMMessage emMessage, Object o) {
    }


    public void setChatListMessage(RoomChatContentInfo chatListMessage) {
        chatRoomContentList.add(chatListMessage);
        if (chatRoomAdapter == null) {
            chatRoomAdapter = new ChatRoomAdapter(chatRoomContentList, context);
            chat_listview.setAdapter(chatRoomAdapter);
        } else {
            chatRoomAdapter.notifyDataSetChanged();
        }
        chat_listview.setSelection(chatRoomAdapter.getCount());

    }

    @Override
    public boolean onPreviewFrame(byte[] bytes, int i, int i1, int i2, int i3, long l) {
        return false;
    }

    @Override
    public void onChatRoomDestroyed(String s, String s1) {

    }

    @Override
    public void onMemberJoined(String s, String s1) {
        //有人进来
        EMChatRoom chatRoom = EMClient.getInstance().chatroomManager().getChatRoom(hx_chatroomsid);
        int memberCount = chatRoom.getMemberList().size();
        mNum.setText(memberCount+"人观看");
    }

    @Override
    public void onMemberExited(String s, String s1, String s2) {
        //有人退出
        EMChatRoom chatRoom = EMClient.getInstance().chatroomManager().getChatRoom(hx_chatroomsid);
        int memberCount = chatRoom.getMemberList().size();
        mNum.setText(memberCount+"人观看");
    }

    @Override
    public void onRemovedFromChatRoom(String s, String s1, String s2) {

    }

    @Override
    public void onMuteListAdded(String s, List<String> list, long l) {

    }

    @Override
    public void onMuteListRemoved(String s, List<String> list) {

    }

    @Override
    public void onAdminAdded(String s, String s1) {

    }

    @Override
    public void onAdminRemoved(String s, String s1) {

    }

    @Override
    public void onOwnerChanged(String s, String s1, String s2) {

    }

    private class MyConnectionListener implements EMConnectionListener {
        @Override
        public void onConnected() {
            //已连接到服务器
        }

        @Override
        public void onDisconnected(final int error) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (error == EMError.USER_REMOVED) {
                        // 显示帐号已经被移除
                    } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                        showToastMsg("该账号已在其他设备登录登录");
                        finish();
                    }
                }
            });
        }
    }
}