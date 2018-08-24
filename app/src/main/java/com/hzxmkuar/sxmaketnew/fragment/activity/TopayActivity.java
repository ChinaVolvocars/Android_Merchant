package com.hzxmkuar.sxmaketnew.fragment.activity;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.base.Config;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.PayBeansss;
import com.common.retrofit.methods.Goods_orderMethods;
import com.common.retrofit.methods.OrderMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.hzxmkuar.sxmaketnew.R;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xgr.easypay.EasyPay;
import com.xgr.easypay.alipay.AliPay;
import com.xgr.easypay.alipay.AlipayInfoImpli;
import com.xgr.easypay.callback.IPayCallback;
import com.xmkj.payandlogin.ShareConfig;
import com.xmkj.payandlogin.ShareManager;

/**
 * Created by STH on 2018/1/8.
 */

public class TopayActivity extends BaseMvpActivity {

    private TextView mName;
    private Button mButton;
    private ImageView mIma_wx;
    private ImageView mIma_ali,mIma_ye;
    private int type =1;
    private IWXAPI api;
    private ImageView mBack;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected void setStatusBar() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_topay;
    }

    @Override
    protected void onViewCreated() {
        mName = (TextView) findViewById(R.id.name);
        mBack = (ImageView) findViewById(R.id.back);
        mButton = (Button) findViewById(R.id.tijiao);
        mIma_ali = (ImageView) findViewById(R.id.ima_ali);
        mIma_wx = (ImageView) findViewById(R.id.ima_wx);
        mIma_ye = (ImageView) findViewById(R.id.ima_ye);
        mIma_ali.setImageResource(R.drawable.gouxuan_sels);
        mIma_wx.setImageResource(R.mipmap.sel_noms);
        mIma_ye.setImageResource(R.mipmap.sel_noms);
        api = WXAPIFactory.createWXAPI(this, Config.APP_ID, false);//初始化微信
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(mIma_ali);
        attachClickListener(mIma_wx);
        attachClickListener(mIma_ye);
        attachClickListener(mButton);
        attachClickListener(mBack);
        ShareConfig config = ShareConfig.instance()
                .wxId("wx97db64e9c8a50df9")
                .wxSecret("8ee6b6c89fedf67b16a8398a0488c2b3");
        ShareManager.init(config);
    }

    @Override
    protected void onViewClicked(View view) {
        if (mIma_ali.getId()==view.getId()){
            mIma_ali.setImageResource(R.drawable.gouxuan_sels);
            mIma_wx.setImageResource(R.mipmap.sel_noms);
            mIma_ye.setImageResource(R.mipmap.sel_noms);
            type =1;
        }else if (mBack.getId()==view.getId()){
            finish();
        }else if (mIma_wx.getId()==view.getId()){
            mIma_wx.setImageResource(R.drawable.gouxuan_sels);
            mIma_ali.setImageResource(R.mipmap.sel_noms);
            mIma_ye.setImageResource(R.mipmap.sel_noms);
            type =2;
        }else if (mIma_ye.getId()==view.getId()){
            mIma_wx.setImageResource(R.mipmap.sel_noms);
            mIma_ali.setImageResource(R.mipmap.sel_noms);
            mIma_ye.setImageResource(R.drawable.gouxuan_sels);
            type = 3;
        }else if (mButton.getId()==view.getId()){
            //gotoActivity(TopaySuccessActivity.class);
        }
        super.onViewClicked(view);
    }
    private void goToHttpReqsPayZB() {
        CommonSubscriber<PayBeansss> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                dismissProgressDialog();
                PayBeansss payBeansss = (PayBeansss) o;
                if (type==1){
                    String aliPayInfo = payBeansss.getAlipay();
                    alipay(aliPayInfo);
                }else if (type==2){
                    //wxpay(payBeansss.getWxPayInfo());
                }else {
                    showToastMsg("支付成功");
                    finish();
                }
            }
            @Override
            public void onError(String e, int code) {
                dismissProgressDialog();
                showToastMsg(e);
            }
        });
        OrderMethods.getInstance().liveOrder(subscriber,getIntent().getStringExtra("name"),type);
        rxManager.add(subscriber);
    }
    private void goToHttpReqs() {
        CommonSubscriber<PayBeansss> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                dismissProgressDialog();
                PayBeansss payBeansss = (PayBeansss) o;
                if (type==1){
                    String aliPayInfo = payBeansss.getAlipay();
                    alipay(aliPayInfo);
                }else if (type==2){
                    //wxpay(payBeansss.getWxPayInfo());
                }else {
                    showToastMsg("支付成功");
                    finish();
                }
            }
            @Override
            public void onError(String e, int code) {
                dismissProgressDialog();
                showToastMsg(e);
            }
        });
        Goods_orderMethods.getInstance().pay(subscriber,getIntent().getStringExtra("data"),getIntent().getStringExtra("is_sn"),type);
        rxManager.add(subscriber);
    }
    private void alipay(String aliStr){
        //实例化支付宝支付策略
        AliPay aliPay = new AliPay();
        //构造支付宝订单实体。一般都是由服务端直接返回。
        AlipayInfoImpli alipayInfoImpli = new AlipayInfoImpli();
        alipayInfoImpli.setOrderInfo(aliStr);
        //策略场景类调起支付方法开始支付，以及接收回调。
        EasyPay.pay(aliPay, (Activity) context, alipayInfoImpli, new IPayCallback() {
            @Override
            public void success() {
                finish();
            }

            @Override
            public void failed() {
            }

            @Override
            public void cancel() {
            }
        });
    }
   /* private void wxpay(PayBeansss.WxPayInfoBean bean) {
        PayReq req = new PayReq();
        req.appId = bean.getAppid();
        req.nonceStr = bean.getNoncestr();
        req.packageValue = bean.getPackageX();
        req.partnerId = bean.getPartnerid();
        req.prepayId = bean.getPrepayid();
        req.timeStamp = bean.getTimestamp() + "";
        req.sign = bean.getSign();
        req.extData = "app data";
        api.sendReq(req);
    }*/
}
