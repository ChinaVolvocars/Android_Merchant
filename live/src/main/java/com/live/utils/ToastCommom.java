package com.live.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.live.R;

/**
 * 作者：LMZ on 2016/8/27 0027 18:13
 */
public class ToastCommom {
    private static ToastCommom toastCommom;

    private Toast toast;

    private ToastCommom() {
    }

    public static ToastCommom createToastConfig() {
        if (toastCommom == null) {
            toastCommom = new ToastCommom();
        }
        return toastCommom;
    }

    /**
     * 显示Toast
     *
     * @param context
     * @param tvString
     */

    public void ToastShow(Context context, String tvString, int color) {
        View layout = LayoutInflater.from(context).inflate(R.layout.dialog_base, null);
        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        TextView text = (TextView) layout.findViewById(R.id.tv_gift);
        if (color != 0) {
            text.setBackgroundColor(color);
        }
        if (toast == null) {
            toast = new Toast(context);
            text.setText(tvString);
            toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.TOP, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
        } else {
            text.setText(tvString);
            toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.TOP, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
        }
        toast.show();
    }


}
