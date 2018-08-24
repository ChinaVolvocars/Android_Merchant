package com.GameControlManager.TimeCount;

import android.os.CountDownTimer;

/**
 * Created by Administrator on 2016/12/8.
 */

public class GameTimeCount extends CountDownTimer {

    private GameTimeCountListener listener;

    public GameTimeCount(long millisInFuture, long countDownInterval, GameTimeCountListener listener) {
        super(millisInFuture, countDownInterval);
        this.listener=listener;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if(listener!=null){
            listener.OnTimeCountTick(millisUntilFinished / 1000);
        }

    }

    @Override
    public void onFinish() {

        if(listener!=null){
            listener.OnTimeCountFinish();
        }
    }

    public interface GameTimeCountListener {

        void OnTimeCountTick(long currentTime);

        void OnTimeCountFinish();
    }
}
