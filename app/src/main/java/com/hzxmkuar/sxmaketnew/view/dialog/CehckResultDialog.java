package com.hzxmkuar.sxmaketnew.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.newversion.NewMainActivity;

/**
 * 验券结果弹窗
 * Created by jc on 2019/01/05.
 */
public class CehckResultDialog extends Dialog implements View.OnClickListener {
    private Context mContext;
    private Activity mActivity;
    private String mStates = "";
    private ImageView iv_succed;
    private ImageView iv_failed;
    private TextView tv_check_result;
    private Button btn_retry;
    private Button btn_confirm;

    public interface OnConfirmClickListener{
        void confirmClick();
    }

    public interface OnRetryClickListener{
        void retryClick();
    }

    private OnConfirmClickListener onConfirmClickListener;
    private OnRetryClickListener onRetryClickListener;

    public void setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener) {
        this.onConfirmClickListener = onConfirmClickListener;
    }

    public void setOnRetryClickListener(OnRetryClickListener onRetryClickListener) {
        this.onRetryClickListener = onRetryClickListener;
    }

    /**
     * @param state  验券结果状态： <br/> 1  验券成功 <br/> 0  验券失败 <br/>
     */
    public CehckResultDialog(@NonNull Context context, String state, Activity activity) {
//        super(context, R.style.cus_dialog);
        super(context);
        this.mContext = context;
        this.mStates = state;
        this.mActivity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setBackgroundDrawable(new BitmapDrawable());
        setContentView(R.layout.dialog_check_tickets);
        setDialogProperty();
        //按空白处不能取消动画
        //初始化界面控件
        initView();
        initEvent();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        iv_succed = (ImageView) findViewById(R.id.iv_succeed);
        iv_failed = (ImageView) findViewById(R.id.iv_failed);
        tv_check_result = (TextView) findViewById(R.id.tv_check_result);
        btn_retry = (Button) findViewById(R.id.btn_retry);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);

        if ("1".equals(mStates)) {
            tv_check_result.setText("验券成功");
            iv_succed.setVisibility(View.VISIBLE);
            iv_failed.setVisibility(View.GONE);
            btn_confirm.setVisibility(View.VISIBLE);
            btn_retry.setVisibility(View.GONE);
        } else {
            tv_check_result.setText("验券失败");
            iv_succed.setVisibility(View.GONE);
            iv_failed.setVisibility(View.VISIBLE);
            btn_confirm.setVisibility(View.GONE);
            btn_retry.setVisibility(View.VISIBLE);
        }
    }


    private void initEvent() {
        btn_retry.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btn_retry.getId()) {
            dismiss();
            if (null != onRetryClickListener){
                onRetryClickListener.retryClick();
            }
        } else if (v.getId() == btn_confirm.getId()) {
            dismiss();
            mContext.startActivity(new Intent(mContext, NewMainActivity.class));
            mActivity.finish();
            mActivity = null;
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
