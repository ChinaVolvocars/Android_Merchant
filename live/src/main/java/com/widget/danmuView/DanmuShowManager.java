package com.widget.danmuView;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.widget.imageview.image.ImageLoaderUtils;
import com.example.live.R;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2016/10/29.
 */
public class DanmuShowManager {

    private final int SCREEN_WIDTH;
    private Context context;
    private LinearLayout item1;
    private LinearLayout item2;
    private final LinkedBlockingQueue queue;
    private int dmFlag1 = 0;//弹幕可以的标志(0,可以运动  1.不可以运动)
    private int dmFlag2 = 0;//弹幕可以的标志（0,可以运动  1.不可以运动）
    private TranslateAnimation animation1;//平移动画
    private TranslateAnimation animation2;//平移动画
    private Timer timer;//从队列中获取消息的定时器

    private final static int GET_MSG = 1001;//处理消息的标志
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GET_MSG:
                    danmuVo = (DanmuVo) msg.obj;
                    if (danmuVo != null) {
                        if (dmFlag1 == 0) {
                            Log.d("418", "huoquxiaoxi");
                            item1.startAnimation(animation1);
                            item1.setVisibility(View.VISIBLE);
                            showDanmuView(item1);
                            break;
                        }

//                        if (dmFlag2 == 0) {
//                            Log.d("418", "huoquxiaoxi2");
//                            item2.startAnimation(animation2);
//                            item2.setVisibility(View.VISIBLE);
//                            showDanmuView(item2);
//                            break;
//                        }

                    }
                    break;
                default:
                    break;
            }
        }
    };
    private DanmuVo danmuVo;

    private void showDanmuView(LinearLayout item) {
        View danmu_view = View.inflate(context, R.layout.danmu_view, null);
        ImageView iv_user_img = (ImageView) danmu_view.findViewById(R.id.iv_user_img);
        TextView tv_user_name = (TextView) danmu_view.findViewById(R.id.tv_user_name);
        TextView tv_send_content = (TextView) danmu_view.findViewById(R.id.tv_send_content);
        String sendUserPic = danmuVo.sendUserPic;
        if (!TextUtils.isEmpty(sendUserPic)) {
            ImageLoaderUtils.displayCircle(iv_user_img, sendUserPic);
        }

        tv_user_name.setText(danmuVo.sendUserName);
        tv_send_content.setText(danmuVo.sendContent);

        item.addView(danmu_view);

        danmu_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null) {
                    listener.OnDanMuClick(danmuVo.userId);
                }
            }
        });

//        danmu_view.startAnimation(animation);
    }


    public DanmuShowManager(Context context, final LinearLayout item1, OnDanMuOnClickListener listener) {
        this.context = context;
        this.item1 = item1;
        this.listener = listener;
//        this.item2 = item2;
        SCREEN_WIDTH = context.getResources().getDisplayMetrics().widthPixels;
        queue = new LinkedBlockingQueue<DanmuVo>(1000);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.e("unfind", "定时器执行,消息队列中的消息个数：" + queue.size());
                if (dmFlag1 == 0) {//有动画空闲的时候，才从队列获取消息
                    DanmuVo view = (DanmuVo) queue.poll();
                    Log.d("218", "huoquxiaoxi");
                    if (view != null) {
                        Message msg = new Message();
                        msg.what = GET_MSG;
                        msg.obj = view;
                        mHandler.sendMessage(msg);
                        Log.d("318", "huoquxiaoxi");
                    }

                }

            }
        }, 2000, 2000);//每秒从队列中获取一条消息


        animation1 = new TranslateAnimation(SCREEN_WIDTH, -SCREEN_WIDTH, 0, 0);
        animation1.setDuration(4000);
        animation1.setInterpolator(new LinearInterpolator());
        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                dmFlag1 = 1;
                item1.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dmFlag1 = 0;
                item1.setVisibility(View.GONE);
                item1.removeAllViews();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

//        animation2 = new TranslateAnimation(400, -SCREEN_WIDTH, 0, 0);
//        animation2.setDuration(8000);
//        animation2.setInterpolator(new LinearInterpolator());
//        animation2.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                dmFlag2 = 1;
//                item2.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                dmFlag2 = 0;
//                item2.setVisibility(View.GONE);
//                item2.removeAllViews();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });

    }

    //放入弹幕到队列
    public boolean addDanmu(DanmuVo vo) {
        return queue.add(vo);
    }

    public void start() {
        mHandler.sendEmptyMessageDelayed(GET_MSG, 1000);
    }

    public void clearDanmu() {
        queue.clear();
    }

    OnDanMuOnClickListener listener;

    public interface OnDanMuOnClickListener {

        void OnDanMuClick(String uid);
    }
}
