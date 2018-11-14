package com.hzxmkuar.sxmaketnew.view.dialog;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.common.utils.SPUtils;
import com.hzxmkuar.sxmaketnew.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DialogQR extends BottomSheetDialogFragment {


    @BindView(R.id.tv_voice)
    TextView tvVoice;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    Unbinder unbinder;
    private boolean voiceStatus;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置背景透明，才能显示出layout中诸如圆角的布局，否则会有白色底（框）
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    public static DialogQR newInstance(Bundle bundle) {
        DialogQR dialogQR = new DialogQR();
        dialogQR.setArguments(bundle);
        return dialogQR;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_qr_voice, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        voiceStatus = SPUtils.getShareBoolean("voice_status");
        if (voiceStatus) {
            tvVoice.setText("关闭收款到账语音提醒");
        } else {
            tvVoice.setText("开启收款到账语音提醒");
        }
    }

    @OnClick(R.id.tv_cancel)
    public void onCancelClicked() {
        dismiss();
    }

    @OnClick(R.id.tv_voice)
    public void onVoiceClicked() {
        dismiss();
        if (SPUtils.getShareBoolean("voice_status")) {
            SPUtils.setShareBoolean("voice_status", false);
            tvVoice.setText("关闭收款到账语音提醒");
            Toast.makeText(getContext(), "关闭语音提醒成功！", Toast.LENGTH_SHORT).show();
        } else {
            SPUtils.setShareBoolean("voice_status", true);
            tvVoice.setText("开启收款到账语音提醒");
            Toast.makeText(getContext(), "开启语音提醒成功！", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
