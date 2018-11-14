package com.common.widget.textview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.common.event.BaseEvent;
import com.common.event.EventBusConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @desc: 自定义倒计时按钮
 * @author: Leo
 * @date: 2016/12/7
 */
@SuppressLint("AppCompatCustomView")
public class CountdownButton extends Button implements View.OnClickListener {
    /**
     * 倒计时时长，默认倒计时时间60秒；
     */
    private long length = 60 * 1000;
    /**
     * 开始执行计时的类，可以在每秒实行间隔任务
     */
    private Timer timer;
    /**
     * 每秒时间到了之后所执行的任务
     */
    private TimerTask timerTask;
    /**
     * 在点击按钮之前按钮所显示的文字，默认是获取验证码
     */
    private String beforeText = "获取验证码";
    /**
     * 在开始倒计时之后那个秒数数字之后所要显示的字，默认是秒
     */
    private String afterText = "秒重新获取";
    /**
     * 按钮点击事件
     */
    private OnClickListener onClickListener;

    private String inputContent;

    /**
     * 获取输入内容以确定倒计时是否运行
     *
     * @param strCotent  输入的内容
     */
    public void getInputContent(String strCotent) {
        this.inputContent = strCotent;
    }

    public CountdownButton(Context context) {
        super(context);
        initView();
    }

    public CountdownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CountdownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化操作
     */
    private void initView() {
        if (!TextUtils.isEmpty(getText())) {
            beforeText = getText().toString().trim();
        }
        this.setText(beforeText);
        countDownHandler = new CountDownHandler();

        setOnClickListener(this);
    }

    /**
     * 初始化时间
     */
    private void initTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                countDownHandler.sendEmptyMessage(1);
            }
        };
    }

    /**
     * 设置倒计时时长
     *
     * @param length 默认毫秒
     */
    public void setLength(long length) {
        this.length = length;
    }

    /**
     * 设置未点击时显示的文字
     */
    public void setBeforeText(String beforeText) {
        this.beforeText = beforeText;
    }

    /**
     * 设置未点击后显示的文字
     */
    public void setAfterText(String beforeText) {
        this.afterText = afterText;
    }

    /**
     * 设置监听按钮点击事件
     */
    @Override
    public void setOnClickListener(OnClickListener onclickListener) {
        if (onclickListener instanceof CountdownButton) {
            super.setOnClickListener(onclickListener);
        } else {
            this.onClickListener = onclickListener;
        }
    }

    /**
     * 点击按钮后的操作
     */
    @Override
    public void onClick(View v) {

        if (onClickListener != null) {
            onClickListener.onClick(v);
        }
        if (!TextUtils.isEmpty(inputContent)) {
            start();
        }
    }

    /**
     *  重置时间
     */
    public void restart(){
        clearTimer();
        length = 60 * 1000;
        start();
    }
    /**
     * 开始倒计时
     */
    public void start() {
        initTimer();
        this.setText(length / 1000 + afterText);
        this.setEnabled(false);
        timer.schedule(timerTask, 0, 1000);
    }

    private CountDownHandler countDownHandler = new CountDownHandler();

    private class CountDownHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            CountdownButton.this.setText(length / 1000 + afterText);
            length -= 1000;
            if (length == 30000){
                EventBus.getDefault().post(new BaseEvent(EventBusConstants.TIME_OUT,"show"));
            }
            if (length < 0) {
                CountdownButton.this.setEnabled(true);
                CountdownButton.this.setText(beforeText);
                clearTimer();
                length = 60 * 1000;
                EventBus.getDefault().post(new BaseEvent(EventBusConstants.TIME_OUT,"canRestart"));
            }
        }
    }

    /**
     * 清除倒计时
     */
    public void clearTimer() {
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        length = 0;
    }

    /**
     * 记得一定要在activity或者fragment消亡的时候清除倒计时，
     * 因为如果倒计时没有完的话子线程还在跑，
     * 这样的话就会引起内存溢出
     */
    @Override
    protected void onDetachedFromWindow() {
        clearTimer();
        super.onDetachedFromWindow();
    }
}