package com.common.mvp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.common.R;
import com.common.listener.OnOnceClickListener;
import com.common.rxbus.RxManager;
import com.common.utils.EmptyUtils;
import com.common.widget.dialog.CommonDialog;
import com.common.widget.imageview.image.ImageLoaderUtils;
import com.common.widget.progress.LoadDialog;
import com.common.widget.toast.ToastManager;

/**
 * 底部弹窗Fragment
 */
public abstract class BaseDialogFragment<P extends BasePresenter>
        extends DialogFragment implements BaseView, View.OnTouchListener {

    protected P presenter;
    protected Dialog dialog;
    protected Context context;
    protected RxManager rxManager;

    protected LoadDialog loadDialog;
    protected String loadText;//加载中提示文字
    protected boolean loadCancelable=true;

    private View parentView;

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        rxManager = new RxManager();
        presenter = createPresenterInstance();
        if (null != presenter) {
            presenter.attachView(this, context);
        }
    }

    protected abstract P createPresenterInstance();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        setDialog();
        initView(dialog);
        setView();
        return dialog;
    }

    // 设置基础对话框
    protected void setDialog() {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        dialog = new Dialog(context, setThemeRes());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        parentView = LayoutInflater.from(context).inflate(getLayoutId(), null);
        dialog.setContentView(parentView);
        dialog.setCanceledOnTouchOutside(false); // 外部点击取消

        setLayoutParams();
        if (EmptyUtils.isNotEmpty(parentView)) {
            parentView.setOnTouchListener(this);
        }
    }

    public View getParentView() {
        return parentView;
    }

    public int setThemeRes() {
        return R.style.DialogFragment;
    }

    // 设置窗体尺寸
    public void setLayoutParams() {
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.BOTTOM;
        window.setAttributes(lp);
    }

    /** 设置自定义对话框布局 */
    protected abstract int getLayoutId();

    protected abstract void setView();

    protected abstract void initView(Dialog dialog);

    @Override
    public void showToastMsg(String msg) {
            ToastManager.showShortToast(msg);
    }

    @Override
    public void showProgressingDialog() {
        loadText=getResources().getString(R.string.string_loadText);
        loadDialog = new LoadDialog(context, loadText, loadCancelable);
        loadDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        if (EmptyUtils.isNotEmpty(loadDialog)) {
            loadDialog.dismiss();
        }
    }

    /**
     * 创建通用对话框
     * @param text              提示文本
     * @param outSideCancel     点击窗外是否取消<默认<true>
     * @return
     */
    protected CommonDialog newCommonDialog(String text, Boolean outSideCancel)
    {
        CommonDialog dialog = CommonDialog.newInstance(context);
        dialog.setText(text);
        dialog.setCanceledOnTouchOutside(outSideCancel);
        dialog.show(getFragmentManager(), "CommonDialog");
        return dialog;
    }

    protected CommonDialog newCommonDialog(String text) {
        return newCommonDialog(text, true);
    }

    public void dismissParent() {
        dismiss();
    }

    /**
     * 获取编辑框文本
     * @param editText
     * @return
     */
    protected String getEditTextStr(EditText editText) {
        if (EmptyUtils.isNotNull(editText)) {
            return editText.getText().toString();
        }
        return "";
    }

    public void gotoActivity(Class<?> cls)
    {
        gotoActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void gotoActivity(Class<?> cls, Bundle bundle)
    {
        if (null == cls) {
            return;
        }

        Intent intent = new Intent();
        intent.setClass(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void attachClickListener(View view) {
        if (dialog != null) {
            if (view != null) {
                view.setOnClickListener(clickListener);
            }
        }
    }

    private OnOnceClickListener clickListener = new OnOnceClickListener() {
        @Override
        public void onOnceClick(View v) {
            onViewClicked(v);
        }
    };

    protected void onViewClicked(View view) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void clearMemory() {
        if (presenter != null) {
            presenter.detachView();
        }

        if (null != rxManager) {
            rxManager.clear();
        }

        // 清除图片缓存
        ImageLoaderUtils.cleanMemory(context);
        dismissParent();
    }

    @Override
    public void onDestroy() {
        clearMemory();
        super.onDestroy();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        getActivity().dispatchTouchEvent(event);
        return false;
    }
}