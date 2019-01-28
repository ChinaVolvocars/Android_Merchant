package com.hzxmkuar.sxmaketnew.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hzxmkuar.sxmaketnew.R;

/**
 * 支付失败弹窗        <br/>
 * Created by Administrator on 2018/8/26.
 */
public class BindDeviceDialog extends Dialog implements View.OnClickListener {
    private Context mContext;
    private EditText edt_edit_device_id;
    private TextView tv_commit_device_id,tv_confirm_unbind,tv_cancel_unbind;
    private LinearLayout ll_bind_conetnt,ll_unbind_conetnt;
    private int dialogType = 1;
    public interface OnConfirmClickListener{
        /**
         *  输入的
         * @param inputDeviceId
         */
        void confirmClick(String inputDeviceId);
    }
    private OnConfirmClickListener clickListener;

    public interface OnUnbindDeviceClickListener{
        void unBindClick();
    }

    public void setOnConfirmClickListener(OnConfirmClickListener clickListener) {
        this.clickListener = clickListener;
    }

    private OnUnbindDeviceClickListener onUnbindDeviceClickListener;

    public void setOnUnbindDeviceClickListener(OnUnbindDeviceClickListener onUnbindDeviceClickListener) {
        this.onUnbindDeviceClickListener = onUnbindDeviceClickListener;
    }

    /**
     *  绑定 或 解绑 设备的弹窗   <br/>
     * @param showType 展示弹窗内容   <br/> 1 为 绑定的弹窗   <br/> 2 为解绑弹窗   <br/>
     */
    public BindDeviceDialog(@NonNull Context context, int showType) {
        super(context);
        this.mContext = context;
        this.dialogType = showType;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.getWindow().setBackgroundDrawable(new BitmapDrawable());
        setContentView(R.layout.dialog_bind_device);
        //按空白处不能取消动画
//        setDialogProperty();
        edt_edit_device_id = (EditText) findViewById(R.id.edt_edit_device_id);
        ll_bind_conetnt = (LinearLayout) findViewById(R.id.ll_bind_conetnt);
        ll_unbind_conetnt = (LinearLayout) findViewById(R.id.ll_unbind_conetnt);
        tv_commit_device_id = (TextView) findViewById(R.id.tv_commit_device_id);
        tv_confirm_unbind = (TextView) findViewById(R.id.tv_confirm_unbind);
        tv_cancel_unbind = (TextView) findViewById(R.id.tv_cancel_unbind);
        if (1 == dialogType){
            ll_bind_conetnt.setVisibility(View.VISIBLE);
            ll_unbind_conetnt.setVisibility(View.GONE);
        }else {
            ll_bind_conetnt.setVisibility(View.GONE);
            ll_unbind_conetnt.setVisibility(View.VISIBLE);
        }
        tv_commit_device_id.setOnClickListener(this);
        tv_confirm_unbind.setOnClickListener(this);
        tv_cancel_unbind.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == tv_commit_device_id.getId()){
            if (null != clickListener){
                clickListener.confirmClick(edt_edit_device_id.getText().toString().trim());
            }
        }else if (view.getId() == tv_confirm_unbind.getId()){
            if (null != onUnbindDeviceClickListener){
                onUnbindDeviceClickListener.unBindClick();
            }
        }else if (view.getId() == tv_cancel_unbind.getId()){
            dismiss();
            cancel();
        }
    }

    /**
     * 设置dialog外部点击，以及返回无法取消
     */
    private void setDialogProperty() {
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
