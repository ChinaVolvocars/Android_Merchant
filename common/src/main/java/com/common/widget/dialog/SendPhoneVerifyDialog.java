package com.common.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.common.R;
import com.common.widget.toast.ToastManager;

/**
 * 发送语音验证码的弹窗
 * Created by Administrator on 2018/8/26.
 */
public class SendPhoneVerifyDialog extends Dialog implements View.OnClickListener {
    private Activity mActivity;
    private Context mContext;
    private TextView tv_dialog_title;
    private TextView tv_cancel_get;
    private TextView tv_get_phone_verify;
    private String mTitleStr = "";
    private String mCancelStr = "";
    private String mConfirmStr = "";

    public SendPhoneVerifyDialog(@NonNull Context context, Activity activity) {
        super(context, R.style.custom_dialog);
        this.mActivity = activity;
        this.mContext = context;
    }

    /**
     *
     * @param context  上下文  <br/>
     * @param activity activity  <br/>
     * @param dialogTitle  弹窗的自定义标题  <br/>
     * @param dialogCancel 弹窗取消按钮  <br/>
     * @param dialogConfirm 弹窗确定按钮  <br/>
     */
    public SendPhoneVerifyDialog(@NonNull Context context, Activity activity, String dialogTitle, String dialogCancel, String dialogConfirm) {
        super(context, R.style.custom_dialog);
        this.mActivity = activity;
        this.mContext = context;
        this.mTitleStr = dialogTitle;
        this.mCancelStr = dialogCancel;
        this.mConfirmStr = dialogConfirm;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_phone_verify);
//        setDialogProperty();
        //初始化界面控件
        initView();
        if (!TextUtils.isEmpty(mTitleStr) && !TextUtils.isEmpty(mCancelStr) && !TextUtils.isEmpty(mConfirmStr)) {
            tv_dialog_title.setText(mTitleStr);
            tv_cancel_get.setText(mCancelStr);
            tv_get_phone_verify.setText(mConfirmStr);
        }
    }


    /**
     * 初始化界面控件
     */
    private void initView() {
        tv_dialog_title = (TextView) findViewById(R.id.tv_dialog_title);
        tv_cancel_get = (TextView) findViewById(R.id.tv_cancel_get);
        tv_get_phone_verify = (TextView) findViewById(R.id.tv_get_phone_verify);
        tv_cancel_get.setOnClickListener(this);
        tv_get_phone_verify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == tv_cancel_get.getId()) {
            ToastManager.showShortToast(tv_cancel_get.getText().toString());
            this.dismiss();
            this.cancel();
        } else if (v.getId() == tv_get_phone_verify.getId()) {
            ToastManager.showShortToast(tv_get_phone_verify.getText().toString());
            this.dismiss();
            this.cancel();
        }
    }

    /**
     * 设置dialog外部点击，以及返回无法取消
     */
    private void setDialogProperty() {
        //按空白处不能取消动画
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
        this.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return (keyCode == KeyEvent.KEYCODE_SEARCH);
            }
        });
    }

}
