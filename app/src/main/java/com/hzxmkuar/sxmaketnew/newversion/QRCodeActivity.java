package com.hzxmkuar.sxmaketnew.newversion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.view.dialog.DialogQR;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QRCodeActivity extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.t_name)
    TextView tName;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_voice)
    ImageView ivVoice;
    @BindView(R.id.iv_qr)
    ImageView ivQr;
    @BindView(R.id.tv_save_qr)
    TextView tvSaveQr;
    @BindView(R.id.tv_book)
    TextView tvBook;

    @OnClick(R.id.back)
    public void onBackClicked() {
        onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        ButterKnife.bind(this);
        tName.setText("二维码收款");


    }

    @OnClick(R.id.iv_voice)
    public void onVoiceClicked() {
        DialogQR dialogQR = DialogQR.newInstance(new Bundle());
        dialogQR.show(getSupportFragmentManager(), "DialogQR");
    }

    @OnClick(R.id.tv_save_qr)
    public void onSaveQRClicked() {

    }

    @OnClick(R.id.tv_book)
    public void onBookClicked() {

    }
}
