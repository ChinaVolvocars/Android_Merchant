package com.hzxmkuar.sxmaketnew.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

import com.common.retrofit.entity.result.CheckTicketsResultEntity;
import com.common.retrofit.methods.CouponMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.widget.toast.ToastManager;
import com.google.zxing.Result;
import com.google.zxing.client.android.AutoScannerView;
import com.google.zxing.client.android.BaseCaptureActivity;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.view.dialog.CehckResultDialog;

/**
 * 模仿微信的扫描界面
 */
public class ScanActivity extends BaseCaptureActivity {

    private static final String TAG = ScanActivity.class.getSimpleName();
    private SurfaceView surfaceView;
    private AutoScannerView autoScannerView;
    private String mSss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取消标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_wechat_capture);
        surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        autoScannerView = (AutoScannerView) findViewById(R.id.autoscanner_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        autoScannerView.setCameraManager(cameraManager);
    }

    @Override
    public SurfaceView getSurfaceView() {
        return (surfaceView == null) ? (SurfaceView) findViewById(R.id.preview_view) : surfaceView;
    }

    @Override
    public void dealDecode(Result rawResult, Bitmap barcode, float scaleFactor) {
        Log.i(TAG, "dealDecode:    rawResult:     " + rawResult.getText());
        Log.i(TAG, "dealDecode:    barcode:     " + barcode);
        Log.i(TAG, "dealDecode:    scaleFactor:     " + scaleFactor);
        playBeepSoundAndVibrate(false, true);
        mSss = rawResult.getText();

        if (!rawResult.equals("")) {
            sendScanResultReq(rawResult.getText().toString());
        } else {
            ToastManager.showShortToast("扫描失败,请重试");
        }
//        finish();
//
//        Log.i(TAG, "dealDecode:     扫瞄结果：rawResult.getText():      " + rawResult.getText().toString());
//        Log.i(TAG, "dealDecode:     扫瞄结果：barcode:      " + barcode);
//        Log.i(TAG, "dealDecode:     扫瞄结果：scaleFactor:      " + scaleFactor);
    }

    /**
     * 扫一扫成功后,去登录或去注册 <br/>
     *
     * @param resultUrl 扫一扫成功返回的链接 <br/>
     */
    private void sendScanResultReq(String resultUrl) {
//        showProgressingDialog();
        CommonSubscriber<CheckTicketsResultEntity> subscriber = new CommonSubscriber<>(new SubscriberListener<CheckTicketsResultEntity>() {
            @Override
            public void onNext(CheckTicketsResultEntity result) {
//                dismissProgressDialog();
                CehckResultDialog dialog = new CehckResultDialog(ScanActivity.this, result.getStatus()+"", ScanActivity.this);
                dialog.show();
                dialog.setOnRetryClickListener(new CehckResultDialog.OnRetryClickListener() {
                    @Override
                    public void retryClick() {
                        startActivity(new Intent(ScanActivity.this,ScanActivity.class));
                        finish();
                    }
                });

            }

            @Override
            public void onError(String e, int code) {
                ToastManager.showShortToast(e);
//                dismissProgressDialog();
            }
        });
        CouponMethods.getInstance().couponVerifyCodeurl(subscriber,resultUrl);
    }


}
