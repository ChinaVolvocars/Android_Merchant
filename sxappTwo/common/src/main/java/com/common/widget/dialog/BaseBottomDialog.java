package com.common.widget.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import com.common.R;
import com.common.mvp.BaseDialogFragment;
import com.common.mvp.BasePresenter;

/*******************************
* 通用底部弹窗对话框
* created at 2017/3/30 下午 3:04
********************************/
public class BaseBottomDialog extends BaseDialogFragment
{
    public static final String BOTTOM_KEY = "BOTTOM_KEY";

    private boolean outSidedismiss = true;
    private int resourceId;

    public static BaseBottomDialog newInstance(int resourceId){
        BaseBottomDialog dialog = new BaseBottomDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(BaseBottomDialog.BOTTOM_KEY, resourceId);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            resourceId = getArguments().getInt(BOTTOM_KEY);
        }
    }

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return resourceId;
    }

    @Override
    public int setThemeRes() {
        return R.style.BottomDialogFragment;
    }

    @Override
    protected void setView() {
        dialog.setCanceledOnTouchOutside(outSidedismiss);
        final Window window = dialog.getWindow();
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.BOTTOM;

        window.setAttributes(lp);
        if(!outSidedismiss){
            getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    return keyCode == KeyEvent.KEYCODE_BACK;
                }
            });
        }
    }

    @Override
    protected void initView(Dialog dialog) {
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        //这里直接调用show方法会报java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }
}
