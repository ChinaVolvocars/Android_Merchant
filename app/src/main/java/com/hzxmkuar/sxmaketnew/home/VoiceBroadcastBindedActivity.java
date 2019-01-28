package com.hzxmkuar.sxmaketnew.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.BindDeviceEntity;
import com.common.retrofit.methods.BindMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.UIUtils;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.view.dialog.BindDeviceDialog;

/**
 * 语音播报  -- 已绑定  <br/>
 * Created by jc on 2019/1/28.
 */
public class VoiceBroadcastBindedActivity extends BaseMvpActivity {
    private ImageView mIvBack;
    private TextView mTvTitle;
    private TextView tvUnbind;
    private TextView tv_device_id;
    private TextView tv_device_state;
    private BindDeviceDialog bindDeviceDialog;
    private String deviceNo = "";

    @Override
    protected void setStatusBar() {

    }
    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_voice_broadcast_binded;
    }

    @Override
    protected void onViewCreated() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("语音播报");
        tvUnbind = (TextView) findViewById(R.id.tv_unbind);
        tv_device_id = (TextView) findViewById(R.id.tv_device_id);
        tv_device_state = (TextView) findViewById(R.id.tv_device_state);
        deviceNo = getIntent().getStringExtra("deviceNo");
        String deviceState = getIntent().getStringExtra("deviceState");
        tv_device_id.setText(deviceNo);
        if ("1".equals(deviceState)){
            tv_device_state.setTextColor(UIUtils.getColor(R.color.color_23bc00));
            tv_device_state.setText("已联网");
        }else if ("2".equals(deviceState)){
            tv_device_state.setTextColor(UIUtils.getColor(R.color.color_eb0000));
            tv_device_state.setText("已联网");
        }else {
            tv_device_state.setTextColor(UIUtils.getColor(R.color.color_eb0000));
            tv_device_state.setText("未知联接错误");
        }
        bindDeviceDialog = new BindDeviceDialog(context,2);
    }


    @Override
    protected void doLogicFunc() {
        attachClickListener(mIvBack);
        attachClickListener(tvUnbind);
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == mIvBack.getId()) {
            finish();
        } else if (view.getId() == tvUnbind.getId()) {
            bindDeviceDialog.show();
            bindDeviceDialog.setOnUnbindDeviceClickListener(new BindDeviceDialog.OnUnbindDeviceClickListener() {
                @Override
                public void unBindClick() {
                    bindDev(deviceNo);
                }
            });
        }
    }

    private void bindDev(String inputDeviceIdNo){
        showProgressingDialog();
        CommonSubscriber<BindDeviceEntity> subscriber = new CommonSubscriber<>(new SubscriberListener<BindDeviceEntity>() {
            @Override
            public void onNext(BindDeviceEntity entiy) {
                dismissProgressDialog();
                showToastMsg("解除绑定成功");
                finish();
            }

            @Override
            public void onError(String e, int code) {
                dismissProgressDialog();
                showToastMsg(e);
            }
        });
        BindMethods.getInstance().isBind(subscriber,"2",inputDeviceIdNo);
        rxManager.add(subscriber);
    }

}
