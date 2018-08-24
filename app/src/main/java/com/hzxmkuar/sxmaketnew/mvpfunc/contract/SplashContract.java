package com.hzxmkuar.sxmaketnew.mvpfunc.contract;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.mvp.BaseMView;
import com.common.mvp.BasePresenter;

public interface SplashContract {
    /**
     * 首次启动进入引导页
     * 非首次且已经登录进入主页
     * 非首次且未登录进入登录页
     */
    interface View extends BaseMView {
        void readyToMain();

        void readyToLogin();

        void readyToGuide();
    }

    abstract class Presenter extends BasePresenter<View> {
        /**
         * 检查 是否首次启动
         */
        public abstract void checkIsFirstIn(TextView textView, ImageView imageView, Button button);

        public abstract void checkIsFirstIn();

        /**
         * 根据渠道号修改闪屏图
         */
        public abstract void setSplashPic(ImageView imageView);
    }
}