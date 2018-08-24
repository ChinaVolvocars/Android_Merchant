package com.hzxmkuar.sxmaketnew.mvpfunc.presenter;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.retrofit.entity.DataCenter;
import com.common.utils.EmptyUtils;
import com.common.utils.SPUtils;
import com.hzxmkuar.sxmaketnew.mvpfunc.contract.SplashContract;

/**
 * @desc: 启动页逻辑
 * @date: 2016/12/23
 */
public class SplashPresenterImpl extends SplashContract.Presenter {
    @Override
    public void checkIsFirstIn(TextView textView, ImageView imageView, Button button) {
    }

    @Override
    public void checkIsFirstIn() {
        boolean isFirstIn = SPUtils.getShareBoolean(DataCenter.ISFIRST);
        if (isFirstIn) {
            SPUtils.setShareBoolean(DataCenter.ISFIRST, false);
//            mView.readyToGuide();
            mView.readyToLogin();
        } else {
            if (EmptyUtils.isEmpty(SPUtils.getShareString(DataCenter.TOKEN))) {
                mView.readyToLogin();
            } else {
                mView.readyToMain();
            }
        }
    }

    @Override
    public void setSplashPic(ImageView imageView) {
        if (EmptyUtils.isEmpty(imageView)) {
            return;
        }
    }
}