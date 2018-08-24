package com.hyphenate.easeui.widget;

import android.animation.Animator;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.common.utils.EmptyUtils;
import com.common.widget.popwindow.BasePopupWindow;
import com.easeui.R;

/*******************************
* 环信消息处理框
* created at 2017/3/21 下午 2:37
********************************/
public class EaseMsgEditPopup extends BasePopupWindow implements View.OnClickListener
{
    public EaseMsgEditPopup(Activity context) {
        super(context, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        findViewById(R.id.tv_copy).setOnClickListener(this);
        findViewById(R.id.tv_delete).setOnClickListener(this);
    }

    @Override
    protected Animation initShowAnimation() {
        return getDefaultAlphaAnimation();
    }

    @Override
    protected Animation initExitAnimation() {
        return getDefaultExitAlphaAnimation();
    }

    @Override
    protected Animator initExitAnimator() {
        return null;
    }

    @Override
    public Animator initShowAnimator() {
        return null;
    }

    @Override
    public void showPopupWindow(View v) {
        setOffsetY(-getPopupViewHeight());
        setOffsetX(-v.getWidth() * 2 / 5);
        super.showPopupWindow(v);
    }

    @Override
    public View getClickToDismissView() {
        return null;
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.ease_mesage_edit_pop);
    }

    @Override
    public View initAnimaView() {
        return getPopupWindowView().findViewById(R.id.popup_container);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_copy) {
            i = 0;
        } else if (i == R.id.tv_delete) {
            i = 1;
        }

        if (EmptyUtils.isNotEmpty(listener)) {
            listener.setPostion(i);
        }
        dismiss();
    }

    private TypeListener listener;

    public void setTypeListener(TypeListener listener) {
        this.listener = listener;
    }

    public interface TypeListener {
        void setPostion(int postion);
    }
}