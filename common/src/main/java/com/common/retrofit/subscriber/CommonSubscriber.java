package com.common.retrofit.subscriber;

import android.util.Log;

import com.common.retrofit.jsoncoverter.ApiException;
import com.common.utils.LogUtils;

import rx.Subscriber;

public class CommonSubscriber<T> extends Subscriber<T> implements CancelSubscriberListener {
    private static final String TAG = "CommonSubscriber";

    private SubscriberListener mSubscriberListener;

    public CommonSubscriber(SubscriberListener mSubscriberListener) {
        this.mSubscriberListener = mSubscriberListener;
    }

    @Override
    public void onCompleted() {
    }

    /**
     * 对错误进行统一处理
     *
     * @param e Throwable
     */
    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        ApiException exception = (ApiException) e;
        if (exception.isTokenExpried()) {
            // TODO: 2018/8/25  处理token失效对应的逻辑    强制下线，，，
//            DataCenter.deleteLoginDataInfo();
//            RxBus.getDefault().post(new RxKeyEvent(RxKeyEvent.FORCE_LOGOUT, true));
        } else {
            LogUtils.e(e.getMessage());
            Log.i(TAG, "onError: "+e.getMessage());
        }

        if (mSubscriberListener != null) {
            mSubscriberListener.onError(exception.getDisplayMessage(), exception.getCode());
        }
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberListener != null) {
//            if (EmptyUtils.isNotNull(t)) {
            mSubscriberListener.onNext(t);
//            }
        }
    }

    @Override
    public void onCancelSubscriber() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}