package com.hzxmkuar.sxmaketnew.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.common.base.Constants;
import com.common.utils.EmptyUtils;
import com.hzxmkuar.sxmaketnew.R;

/**
 * 新商家入驻，资料成功提交审核弹窗
 * Created by Administrator on 2018/8/26.
 */
public class JoinSucceedDialog extends Dialog {

    private Button mBtnConfirm;//确定按钮
    private Button mBtnCancel;//取消按钮
    private TextView mTvTitle;//消息标题文本
    private TextView messageTv;//消息提示文本
    private String titleStr;//从外界设置的title文本
    private String messageStr;//从外界设置的消息文本
    //确定文本和取消文本的显示内容
    private String yesStr, noStr;

//    private onNoOnclickListener noOnclickListener;//取消按钮被点击了的监听器
//    private onYesOnclickListener yesOnclickListener;//确定按钮被点击了的监听器
    private Context mContext;
    private Activity mActivity;
    public JoinSucceedDialog(@NonNull Context context, Activity activity) {
//        super(context, R.style.cus_dialog);
        super(context);
        this.mContext = context;
        this.mActivity = activity;
    }

//    /**
//     * 设置取消按钮的显示内容和监听
//     *
//     * @param str
//     * @param onNoOnclickListener
//     */
//    public void setNoOnclickListener(String str, onNoOnclickListener onNoOnclickListener) {
////        if (str != null) {
//        if (!EmptyUtils.isEmpty(str)) {
//            noStr = str;
//        }
//        this.noOnclickListener = onNoOnclickListener;
//    }

//    /**
//     * 设置确定按钮的显示内容和监听
//     * @param str
//     * @param onYesOnclickListener
//     */
//    public void setYesOnclickListener(String str, onYesOnclickListener onYesOnclickListener) {
//        if (str != null) {
//            yesStr = str;
//        }
//        this.yesOnclickListener = onYesOnclickListener;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_cus);
        //按空白处不能取消动画

        setDialogProperty();
        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();

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
    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent() {
        //设置确定按钮被点击后，向外界提供监听
//        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (yesOnclickListener != null) {
//                    yesOnclickListener.onYesClick();
//                }
//            }
//        });
//        //设置取消按钮被点击后，向外界提供监听
//        mBtnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (noOnclickListener != null) {
//                    noOnclickListener.onNoClick();
//                }
//            }
//        });

        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JoinSucceedDialog.this.dismiss();
                mActivity.finish();
            }
        });

    }

    /**
     * 初始化界面控件的显示数据
     */
    private void initData() {
        //如果用户自定了title和message
        if (!EmptyUtils.isEmpty(titleStr)) {
            mTvTitle.setText(titleStr);
        }
        if (!EmptyUtils.isEmpty(messageStr)) {
            messageTv.setText(messageStr);
        }
        //如果设置按钮的文字
        if (!EmptyUtils.isEmpty(yesStr)) {
            mBtnConfirm.setText(yesStr);
        }
        if (!EmptyUtils.isEmpty(noStr)) {
            mBtnCancel.setText(noStr);
        }
    }

    /**
     * 初始化界面控件
     */
    private void initView() {
        mBtnConfirm = (Button) findViewById(R.id.btn_confirm);
        mBtnCancel = (Button) findViewById(R.id.btn_cancel);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        messageTv = (TextView) findViewById(R.id.message);
        mBtnCancel.setVisibility(View.GONE);

    }

    /**
     * 从外界Activity为Dialog设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        titleStr = title;
    }

    /**
     * 从外界Activity为Dialog设置dialog的message
     *
     * @param message
     */
    public void setMessage(String message) {
        messageStr = message;
    }
//
//    /**
//     * 设置确定按钮和取消被点击的接口
//     */
//    public interface onYesOnclickListener {
//        public void onYesClick();
//    }
//
//    public interface onNoOnclickListener {
//        public void onNoClick();
//    }
}
