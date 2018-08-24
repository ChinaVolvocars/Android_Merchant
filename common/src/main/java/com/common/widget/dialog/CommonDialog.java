package com.common.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.R;

/*******************************
 * 通用对话框
 * @author syc
 * created at 2017/4/17 上午 9:03
 ********************************/
public class CommonDialog extends DialogFragment
{
    private View rootView;
    private boolean outSidedismiss = true;

    private TextView tvContent;
    private LinearLayout llClose;
    private LinearLayout llButton;
    private Button btnCancel;
    private Button btnSure;
    private Button btnFunc;

    public enum TYPE {
        CLOSE, SURE, CANCELANDSURE
    }

    public static CommonDialog newInstance(Context context) {
        CommonDialog adf = new CommonDialog();
        adf.rootView = LayoutInflater.from(context).inflate(R.layout.view_common_dialog, null);
        adf.init(adf.rootView);
        adf.setDialogType(TYPE.CANCELANDSURE);
        return adf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.dialog_contact);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(outSidedismiss);
        if(!outSidedismiss){
            getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        return true;
                    }
                    return false;
                }
            });
        }
        return rootView;
    }

    /**  对话框初始化 */
    public void init(View rootView) {
        tvContent = (TextView) rootView.findViewById(R.id.tv_content);
        btnCancel = (Button) rootView.findViewById(R.id.btn_cancel);
        btnSure = (Button) rootView.findViewById(R.id.btn_sure);
        btnFunc = (Button) rootView.findViewById(R.id.btn_gofunc);
        llClose = (LinearLayout) rootView.findViewById(R.id.ll_close);
        llButton = (LinearLayout) rootView.findViewById(R.id.ll_button);

        if (null != llClose) {
            llClose.setOnClickListener(cancelClick);
        }

        if (null != btnCancel) {
            btnCancel.setOnClickListener(cancelClick);
        }

        if (null != btnFunc) {
            btnFunc.setOnClickListener(cancelClick);
        }
    }

    /**
     * diaog提示语
     * @param text    text
     */
    public void setText(String text) {
        if (null != tvContent) {
            tvContent.setText(text);
        }
    }

    /**
     * dialog提示语
     * @param text  text
     * @param size 字体大小
     */
    public void setText(String text, int size) {
        if (null != tvContent) {
            tvContent.setText(text);
            tvContent.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
        }
    }

    public void setSubmitText(String str) {
        btnSure.setText(str);
    }

    public void setCancelText(String str) {
        btnCancel.setText(str);
    }

    public void setFuncText(String str) {
        btnFunc.setText(str);
    }

    /**
     * 设置对话康类型
     * @param type   type
     */
    public void setDialogType(TYPE type) {
        switch (type) {
            case CLOSE :
                llButton.setVisibility(View.GONE);
                btnFunc.setVisibility(View.GONE);
                llClose.setVisibility(View.VISIBLE);
                break;
            case CANCELANDSURE:
                llButton.setVisibility(View.VISIBLE);
                llClose.setVisibility(View.GONE);
                btnFunc.setVisibility(View.GONE);
                break;
            case SURE:
                llButton.setVisibility(View.GONE);
                llClose.setVisibility(View.GONE);
                btnFunc.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 取消监听事件
     * @param cancel     cancel
     */
    public void setCancel(View.OnClickListener cancel) {
        if (null == cancel) {
            if (null != btnCancel) {
                btnCancel.setOnClickListener(cancelClick);
            }
        } else if (null != btnCancel) {
            btnCancel.setOnClickListener(cancel);
        }
    }

    /**
     * 确认监听事件
     * @param submit     submit
     */
    public void setSubmit(View.OnClickListener submit) {
        if (null == submit) {
            if (null != btnSure) {
                btnSure.setOnClickListener(cancelClick);
            }
        } else if (null != btnSure) {
            btnSure.setOnClickListener(submit);
        }
    }

    /**
     * 单个按钮监听事件
     * @param listener     listener
     */
    public void setBtnFunc(View.OnClickListener listener) {
        if (null == listener) {
            if (null != btnFunc) {
                btnFunc.setOnClickListener(cancelClick);
            }
        } else if (null != btnFunc) {
            btnFunc.setOnClickListener(listener);
        }
    }

    /**
     * 设置弹框外消失
     * @param bool    bool
     */
    public void setCanceledOnTouchOutside(Boolean bool) {
        outSidedismiss = bool;
    }

    private View.OnClickListener cancelClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CommonDialog.this.dismiss();
        }
    };

    @Override
    public void show(FragmentManager manager, String tag) {
        //这里直接调用show方法会报java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }
}