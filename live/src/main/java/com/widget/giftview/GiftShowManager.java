package com.widget.giftview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.widget.imageview.image.ImageLoaderUtils;
import com.example.live.R;
import com.live.entity.GiftVo;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zhongxf
 * @Description 礼物显示的管理类
 * @Date 2016/6/6.
 * 主要礼物逻辑：利用一个LinkedBlockingQueue来存储所有的礼物的实体类。然后利用Handler的消息机制，每隔一段时间从队列中取一次礼物出来
 * 如果取得礼物为空（队列中没有礼物），那么就延迟一段时间之后再次从队列中取出礼物
 * 如果从队列中取出的礼物不为空，则根据送礼物的人的UserId去寻找这个礼物是否正在显示，如果不在显示，则新建一个，如果正在显示，则直接修改数量
 * <p/>
 * 这个礼物View的管理类中一直存在一个定时器在沦陷礼物的容器下面的所有的礼物的View，当有礼物的View上次的更新时间超过最长显示时间，那么久就移除这个View
 * <p/>
 * 6/7实现：礼物容器中显示的礼物达到两条，并且新获取的礼物和他们两个不一样，那么需要移除一个来显示新的礼物
 * 判断所有的里面的出现的时间，然后把显示最久的先移除掉（需要考虑到线程安全）
 * <p/>
 * 6/7实现：定时器的线程会更新View，在获取礼物的时候也会更新View（增加线程安全控制）
 */
public class GiftShowManager {

    private LinkedBlockingQueue<GiftVo> queue;//礼物的队列
    private LinearLayout giftCon;//礼物的容器
    private Context cxt;//上下文
    private TranslateAnimation inAnim;//礼物View出现的动画
    private TranslateAnimation outAnim;//礼物View消失的动画
    private ScaleAnimation giftNumAnim;//修改礼物数量的动画

    private final static int SHOW_GIFT_FLAG = 1;//显示礼物
    private final static int GET_QUEUE_GIFT = 0;//从队列中获取礼物
    private final static int REMOVE_GIFT_VIEW = 2;//当礼物的View显示超时，删除礼物View
    private String lastGiftId = "-1";
    private String lastUserid = "-1";
    private boolean isFromDifGift = false;//不通的礼物
    private Timer timer;//轮询礼物容器的所有的子View判断是否超过显示的最长时间


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_GIFT_FLAG://如果是处理显示礼物的消息
                    final GiftVo showVo = (GiftVo) msg.obj;
                    String userId = showVo.getUserId();
                    giftId = showVo.getGiftId();
                    isFromDifGift = !giftId.equals(lastGiftId);
                    lastGiftId = giftId;
                    int num = showVo.getNum();
                    View giftView = giftCon.findViewWithTag(userId);
                    Log.d("User", "giftView:拿到礼物" + giftView + ",giftId:" + giftId + ",lastGiftId:" + lastGiftId + ",isFromDifGift:" + isFromDifGift);
                    giftNumAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            handler.sendEmptyMessageDelayed(GET_QUEUE_GIFT, 2000);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });

                    if (giftView == null) {//获取的礼物的实体，判断送的人不在显示，说明是最新的用户发送的礼物
                        Log.d("User", "giftView:创建 新的礼物" + giftView + ",giftId:" + giftId + ",lastGiftId:" + lastGiftId + ",isFromDifGift:" + isFromDifGift);
                        //首先需要判断下Gift ViewGroup下面的子View是否超过两个
                        int count = giftCon.getChildCount();
                        if (count >= 2) {//如果正在显示的礼物的个数超过两个，那么就移除最后一次更新时间比较长的
                            View giftView1 = giftCon.getChildAt(0);
                            TextView nameTv1 = (TextView) giftView1.findViewById(R.id.name);
                            long lastTime1 = (long) nameTv1.getTag();
                            View giftView2 = giftCon.getChildAt(1);
                            TextView nameTv2 = (TextView) giftView2.findViewById(R.id.name);
                            long lastTime2 = (long) nameTv2.getTag();
                            Message rmMsg = new Message();
                            if (lastTime1 > lastTime2) {//如果第二个View显示的时间比较长
                                rmMsg.obj = 1;

                            } else {//如果第一个View显示的时间长
                                rmMsg.obj = 0;
                            }
                            rmMsg.what = REMOVE_GIFT_VIEW;
                            handler.sendMessage(rmMsg);
                        }


                        //获取礼物的View的布局
                        giftView = LayoutInflater.from(cxt).inflate(R.layout.gift_item, null);
                        giftView.setTag(userId);
//                        giftView.setTag(giftId);
//                        lastGiftId=showVo.getGiftId();
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        lp.topMargin = 10;
                        giftView.setLayoutParams(lp);
                        //显示礼物的数量
                        //礼物的数量
                        giftNum = (MagicTextView) giftView.findViewById(R.id.gift_num);
                        giftNum.setTag(num);
                        giftNum.setText("X" + num);

                        //礼物的名字
                        tv_gift_name = (TextView) giftView.findViewById(R.id.name);
                        tv_gift_name.setText("送了一个" + showVo.getGiftName());
                        tv_gift_name.setTag(System.currentTimeMillis());

                        //发送者的头像
                        sendUserFace = (ImageView) giftView.findViewById(R.id.iv_gift_visitors_image);

                        if (!TextUtils.isEmpty(showVo.getSendUserPic())) {
                            ImageLoaderUtils.displayCircle(sendUserFace, showVo.getSendUserPic());
                        }
                        TextView sendUserName = (TextView) giftView.findViewById(R.id.tv_visitors_name);//发送者的名字
                        sendUserName.setText(showVo.getSendUserName());
                        //礼物图片

                        giftPic = (ImageView) giftView.findViewById(R.id.iv_visitor_gift_image);

                        ImageLoaderUtils.displayCircle(giftPic, showVo.getGiftPic());
                        Log.d("giftInfo", "userId:" + showVo.getUserId() + ",userName:" + showVo.getSendUserName() +
                                ",userPic:" + showVo.getSendUserPic() + ",giftId:" + showVo.getGiftId() + ",giftPic:" + showVo.getGiftPic() + "" +
                                ",giftName:" + showVo.getGiftName() + ",giftNum:" + showVo.getNum());

                        //将礼物的View添加到礼物的ViewGroup中
                        giftCon.addView(giftView);
                        giftView.startAnimation(inAnim);//播放礼物View出现的动
                        if (giftView != null) {
                            giftView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (listener != null) {

                                        listener.OnClickGift(showVo.getUserId());
                                    }
                                }
                            });
                        }

                        inAnim.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {
                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                giftNum.startAnimation(giftNumAnim);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }
                        });
                    } else {

                        if (isFromDifGift) {//同用户发送不同的礼物
                            if (tv_gift_name == null) {
                                tv_gift_name = (TextView) giftView.findViewById(R.id.name);
                            }
                            tv_gift_name.setText("送了一个" + showVo.getGiftName());
                            tv_gift_name.setTag(System.currentTimeMillis());

                            if (giftPic == null) {
                                giftPic = (ImageView) giftView.findViewById(R.id.iv_visitor_gift_image);
                            }

                            ImageLoaderUtils.display(giftPic, showVo.getGiftPic());

                            if (giftNum == null) {
                                giftNum = (MagicTextView) giftView.findViewById(R.id.gift_num);
                            }
                            final MagicTextView giftNum = (MagicTextView) giftView.findViewById(R.id.gift_num);
                            giftNum.setTag(num);
                            giftNum.setText("X" + num);
                            TextView tv = (TextView) giftView.findViewById(R.id.name);
                            tv.setTag(System.currentTimeMillis());
                            giftNum.startAnimation(giftNumAnim);
                            Log.d("User", "giftView:同人不同物" + giftView + ",giftId:" + giftId + ",lastGiftId:" + lastGiftId + ",isFromDifGift:" + isFromDifGift);
                        } else {
                            Log.d("User", "giftView:同人同物" + giftView + ",giftId:" + giftId + ",lastGiftId:" + lastGiftId + ",isFromDifGift:" + isFromDifGift);
                            //如果送的礼物正在显示（只是修改下数量）
                            //显示礼物的数量
                            final MagicTextView giftNum = (MagicTextView) giftView.findViewById(R.id.gift_num);
                            int showNum = (int) giftNum.getTag() + num;
                            giftNum.setText("X" + (showNum));
                            giftNum.setTag(showNum);
                            TextView tv = (TextView) giftView.findViewById(R.id.name);
                            tv.setTag(System.currentTimeMillis());
                            giftNum.startAnimation(giftNumAnim);
                        }

                    }
                    break;
                case GET_QUEUE_GIFT://如果是从队列中获取礼物实体的消息
                    GiftVo vo = queue.poll();
                    if (vo != null) {//如果从队列中获取的礼物不为空，那么就将礼物展示在界面上
                        Message giftMsg = new Message();
                        giftMsg.obj = vo;
                        giftMsg.what = SHOW_GIFT_FLAG;
                        handler.sendMessage(giftMsg);
                    } else {
                        handler.sendEmptyMessageDelayed(GET_QUEUE_GIFT, 1000);//如果这次从队列中获取的消息是礼物是空的，则一秒之后重新获取
                    }
                    break;

                case REMOVE_GIFT_VIEW:
                    final int index = (int) msg.obj;
                    View removeView = giftCon.getChildAt(index);
                    outAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            giftCon.removeViewAt(index);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    removeView.startAnimation(outAnim);

                    break;
                default:
                    break;
            }
        }
    };
    private String giftId;
    private MagicTextView giftNum;
    private TextView tv_gift_name;
    private ImageView sendUserFace;
    private ImageView giftPic;

    public GiftShowManager(final Context cxt, final LinearLayout giftCon, OnGiftViewOnClickListener listener) {
        this.cxt = cxt;
        this.giftCon = giftCon;
        this.listener = listener;
        queue = new LinkedBlockingQueue<>(1000);
        inAnim = (TranslateAnimation) AnimationUtils.loadAnimation(cxt, R.anim.gift_in);
        outAnim = (TranslateAnimation) AnimationUtils.loadAnimation(cxt, R.anim.gift_out);
        giftNumAnim = (ScaleAnimation) AnimationUtils.loadAnimation(cxt, R.anim.gift_num);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                int count = giftCon.getChildCount();
                for (int i = 0; i < count; i++) {
                    View view = giftCon.getChildAt(i);
                    TextView name = (TextView) view.findViewById(R.id.name);
                    long nowtime = System.currentTimeMillis();
                    long upTime = (long) name.getTag();
                    if ((nowtime - upTime) >= 10000) {
                        Message msg = new Message();
                        msg.obj = i;
                        msg.what = REMOVE_GIFT_VIEW;
                        handler.sendMessage(msg);
                    }
                }
            }
        };
        timer = new Timer();
        timer.schedule(task, 2000, 2000);
    }

    //开始显示礼物
    public void showGift() {
        handler.sendEmptyMessageDelayed(GET_QUEUE_GIFT, 1000);//轮询队列获取礼物

    }

    //放入礼物到队列
    public boolean addGift(GiftVo vo) {
        Log.d("testGift", "列队添加礼物");
        return queue.add(vo);
    }

    public void clearGift() {
        queue.clear();
    }

    OnGiftViewOnClickListener listener;

    public interface OnGiftViewOnClickListener {

        void OnClickGift(String uid);
    }

}