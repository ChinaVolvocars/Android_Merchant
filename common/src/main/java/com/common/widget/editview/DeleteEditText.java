package com.common.widget.editview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.common.R;

/**
 * 带删除按钮的输入框
 * 2017年1月10日12:13:22
 */
public class DeleteEditText extends EditText implements View.OnTouchListener, View.OnFocusChangeListener, TextWatcher {
    private Drawable deleteIcon;
    private OnTouchListener _t;
    private OnFocusChangeListener _f;

    public DeleteEditText(Context context) {
        super(context);
        init();
    }

    public DeleteEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DeleteEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        deleteIcon = getCompoundDrawables()[2];
        if (deleteIcon == null) {
            deleteIcon = getResources().getDrawable(R.mipmap.icon_edit_delete);
        }
        deleteIcon.setBounds(0, 0, deleteIcon.getIntrinsicWidth(), deleteIcon.getIntrinsicHeight());
//        _right.setBounds((int)getResources().getDimension(R.dimen.margin_left), 0, _right.getIntrinsicWidth(), _right.getIntrinsicHeight());
        setCompoundDrawablePadding((int) getResources().getDimension(R.dimen.margin_micro));
        super.setOnFocusChangeListener(this);
        super.setOnTouchListener(this);
        addTextChangedListener(this);
        setPadding((int) getResources().getDimension(R.dimen.margin_left), 0, (int) getResources().getDimension(R.dimen.margin_right), 0);
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener l) {
        this._f = l;
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        this._t = l;
    }

    private void setClearIconVisible(boolean visible) {
        Drawable temp = visible ? deleteIcon : null;
        Drawable[] drawables = getCompoundDrawables();
        setCompoundDrawables(drawables[0], drawables[1], temp, drawables[3]);
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        setClearIconVisible(hasFocus && !TextUtils.isEmpty(getText()));
        if (_f != null) {
            _f.onFocusChange(v, hasFocus);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (getCompoundDrawables()[2] != null) {
            boolean tapped = event.getX() > (getWidth() - getPaddingRight() - deleteIcon.getIntrinsicWidth());
            if (tapped) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    setText("");
                }
                return true;
            }
        }
        if (_t != null) {
            return _t.onTouch(v, event);
        }
        return false;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //ignore
    }

    @Override
    public void afterTextChanged(Editable s) {
        setClearIconVisible(isFocused() && !TextUtils.isEmpty(s));
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        super.onTextChanged(s, start, before, count);
    }
}