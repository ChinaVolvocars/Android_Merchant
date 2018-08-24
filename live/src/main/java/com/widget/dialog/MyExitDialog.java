package com.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.live.R;

/**
 * Created by Administrator on 2016/12/6.
 */
public class MyExitDialog extends AlertDialog implements View.OnClickListener {
    private MyExitDialogListener listener;

    public MyExitDialog(Context context, MyExitDialogListener listener) {
        super(context);
        this.listener = listener;
        View goBackView = View.inflate(context, R.layout.dialog_go_back, null);
        this.setCancelable(true);
        show();
        Window windowDeleteBlack = this.getWindow();
        windowDeleteBlack.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        windowDeleteBlack.setContentView(goBackView);
        windowDeleteBlack.setBackgroundDrawableResource(R.color.transparent);
        TextView tv_cancal = (TextView) goBackView.findViewById(R.id.tv_cancal);
        TextView tv_sure = (TextView) goBackView.findViewById(R.id.tv_sure);
        tv_cancal.setOnClickListener(this);
        tv_sure.setOnClickListener(this);
    }


    public void showExitDialog() {
        if (this != null) {
            show();
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_sure) {
            if (listener != null) {
                listener.srueExit();
            }

        } else if (i == R.id.tv_cancal) {
            if (listener != null) {
                listener.cancleExit();
            }

        }
    }

    public interface MyExitDialogListener {
        void srueExit();

        void cancleExit();
    }
}
