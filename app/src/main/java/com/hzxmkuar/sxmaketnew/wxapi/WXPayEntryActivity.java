package com.hzxmkuar.sxmaketnew.wxapi;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.common.base.Config;
import com.hzxmkuar.sxmaketnew.R;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;
    private TextView mPayResultTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        mPayResultTv = (TextView) findViewById(R.id.tv_pay_result);
        api = WXAPIFactory.createWXAPI(this, Config.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onResp(BaseResp resp) {

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            Log.d(TAG, "onPayFinish, errCode = " + resp.errStr + resp.errCode);
            switch (resp.errCode) {
                case 0:
                    // 微信支付成功
                    // ToastCommom.createToastConfig().ToastShow(getApplicationContext(), "支付成功", 0);
                    mPayResultTv.setText("支付成功，2秒钟后返回");
                    break;
                case -1:
                    //  ToastCommom.createToastConfig().ToastShow(getApplicationContext(), "Appid错误", 0);
                    mPayResultTv.setText("支付失败，2秒钟后返回");
                    break;
                case -2:
                    //  ToastCommom.createToastConfig().ToastShow(getApplicationContext(), "订单取消", 0);
                    mPayResultTv.setText("订单取消，支付失败，2秒钟后返回");
                    break;
                default:
                    //   ToastCommom.createToastConfig().ToastShow(getApplicationContext(), resp.errCode + "", 0);
                    mPayResultTv.setText("支付失败，2秒钟后返回");
                    break;
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            }).start();
        }
    }
}