package com.widget.popupwindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.live.R;

/**
 * Created by Administrator on 2016/12/22.
 */

public class LiveVideoToolsPopWindow extends PopupWindow {

    private Context context;
    private View view;
    public LiveVideoToolsPopWindow(Context context, View view){
        View popView = View.inflate(context, R.layout.window_voice_view, null);
        setAnimationStyle(R.style.CustomDialog);
        setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setContentView(popView);
        showAtLocation(view, Gravity.CENTER | Gravity.CENTER, 0, 0);
    }
}
