package com.hzxmkuar.sxmaketnew.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hzxmkuar.sxmaketnew.R;

/**
 *  疑问弹窗
 * Created by Administrator on 2018/8/26.
 */
public class RequestionDialog extends Dialog {

    private TextView tvContent;//消息提示文本

    public RequestionDialog(@NonNull Context context) {
//        super(context, R.style.cus_dialog);
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_requestion);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(true);

    }
}
