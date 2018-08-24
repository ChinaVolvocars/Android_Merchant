package com.xmkj.payandlogin.share;

/**
 * Created by STH on 2017/12/6.
 */

import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;

import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xgr.easypay.base.IPayStrategy;
import com.xgr.easypay.callback.IPayCallback;
import com.xgr.easypay.wxpay.WXPayInfoImpli;

/**
 * 文 件 名: WXPay
 * 创 建 人: King
 * 创建日期: 2017/2/13 19:03
 * 邮   箱: mikey1101@163.com
 * 博   客: www.smilevenus.com
 * @see <a href="https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=1417751808&token=&lang=zh_CN">Des</a>
 */
public class WXPay implements IPayStrategy<WXPayInfoImpli>{

    private static WXPay mWXPay;
    private final Activity context;
    private WXPayInfoImpli payInfoImpli;
    private static IPayCallback sPayCallback;
    private IWXAPI mWXApi;

    private WXPay(Activity mActivity,String wxAppId) {
        this.context = mActivity;
        mWXApi = WXAPIFactory.createWXAPI(mActivity.getApplicationContext(), null);
        Toast.makeText(mActivity,"aaaaaaaaaaaaaaa1",Toast.LENGTH_SHORT).show();
        mWXApi.registerApp(wxAppId);
    }

    public static WXPay getInstance(Activity mActivity,String wxAppId){
        if(mWXPay == null){
            synchronized (WXPay.class){
                if(mWXPay == null) {
                    mWXPay = new WXPay(mActivity,wxAppId);
                    Toast.makeText(mActivity,"aaaaaaaaaaaaaaa2",Toast.LENGTH_SHORT).show();
                }
            }
        }
        return mWXPay;
    }

    public IWXAPI getWXApi() {
        return mWXApi;
    }

    @Override
    public void pay(Activity activity, WXPayInfoImpli payInfo, IPayCallback payCallback) {
        this.payInfoImpli = payInfo;
        sPayCallback = payCallback;
        Toast.makeText(activity,"aaaaaaaaaaaaaaa3",Toast.LENGTH_SHORT).show();
        if(!check()) {
            if(payCallback != null) {
                payCallback.failed();
                Toast.makeText(activity,"aaaaaaaaaaaaaaa8",Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if(payInfoImpli == null || TextUtils.isEmpty(payInfoImpli.getAppid()) || TextUtils.isEmpty(payInfoImpli.getPartnerid())
                || TextUtils.isEmpty(payInfoImpli.getPrepayId()) || TextUtils.isEmpty(payInfoImpli.getPackageValue()) ||
                TextUtils.isEmpty(payInfoImpli.getNonceStr()) || TextUtils.isEmpty(payInfoImpli.getTimestamp()) ||
                TextUtils.isEmpty(payInfoImpli.getSign())) {
            if(payCallback != null) {
                payCallback.failed();
            }
            return;
        }

        PayReq req = new PayReq();
        req.appId = payInfoImpli.getAppid();
        req.partnerId = payInfoImpli.getPartnerid();
        req.prepayId = payInfoImpli.getPrepayId();
        req.packageValue = payInfoImpli.getPackageValue();
        req.nonceStr = payInfoImpli.getNonceStr();
        req.timeStamp = payInfoImpli.getTimestamp();
        req.sign = payInfoImpli.getSign();
        mWXApi.sendReq(req);
        Toast.makeText(activity,"aaaaaaaaaaaaaaa4",Toast.LENGTH_SHORT).show();
    }

    //支付回调响应
    public void onResp(int error_code) {
        Toast.makeText(context,"bbbbbbbbbbbbb"+error_code,Toast.LENGTH_SHORT).show();
        if(sPayCallback == null) {
            return;
        }
        if(error_code == 0) {   //成功
            sPayCallback.success();
        } else if(error_code == -1) {   //错误
            sPayCallback.failed();
        } else if(error_code == -2) {   //取消
            sPayCallback.cancel();
        }

        sPayCallback = null;
    }

    //检测是否支持微信支付
    private boolean check() {
        return mWXApi.isWXAppInstalled() && mWXApi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
    }
}

