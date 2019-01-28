package com.hzxmkuar.sxmaketnew.home;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.BindDeviceEntity;
import com.common.retrofit.methods.BindMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.EmptyUtils;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.view.dialog.BindDeviceDialog;

/**
 * 语音播报  -- 未绑定  <br/>
 * Created by jc on 2019/1/28.
 */
public class VoiceBroadcastUnbindActivity extends BaseMvpActivity {
    private ImageView mIvBack;
    private TextView mTvTitle;
    private TextView tvHaveNotBind;
    private BindDeviceDialog bindDeviceDialog;

    @Override
    protected void setStatusBar() {

    }
    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_voice_broadcast_unbind;
    }

    @Override
    protected void onViewCreated() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        tvHaveNotBind = (TextView) findViewById(R.id.tv_have_not_bind);
        mTvTitle.setText("语音播报");
        bindDeviceDialog = new BindDeviceDialog(context,1);
    }


    @Override
    protected void doLogicFunc() {
        attachClickListener(mIvBack);
        attachClickListener(tvHaveNotBind);
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == mIvBack.getId()) {
            finish();
        } else if (view.getId() == tvHaveNotBind.getId()) {
            bindDeviceDialog.show();
            bindDeviceDialog.setOnConfirmClickListener(new BindDeviceDialog.OnConfirmClickListener() {
                @Override
                public void confirmClick(String inputDeviceId) {
                    bindDev(inputDeviceId);
                }
            });

        }
    }

    private void bindDev(String inputDeviceIdNo){
        if (EmptyUtils.isEmpty(inputDeviceIdNo)){
            showToastMsg("设备id不能为空");
            return;
        }
        showProgressingDialog();
        CommonSubscriber<BindDeviceEntity> subscriber = new CommonSubscriber<>(new SubscriberListener<BindDeviceEntity>() {
            @Override
            public void onNext(BindDeviceEntity entiy) {
                dismissProgressDialog();
                showToastMsg("设备绑定成功");
                Intent intent = new Intent(context,VoiceBroadcastBindedActivity.class);
                intent.putExtra("deviceNo", entiy.getDev_num());
                intent.putExtra("deviceState", entiy.getStatus());
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(String e, int code) {
                dismissProgressDialog();
                showToastMsg(e);
            }
        });
        BindMethods.getInstance().isBind(subscriber,"1",inputDeviceIdNo);
        rxManager.add(subscriber);
    }

}
