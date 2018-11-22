package com.hzxmkuar.sxmaketnew.newversion;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.utils.ImgUtils;
import com.hzxmkuar.sxmaketnew.view.dialog.DialogQR;

import butterknife.BindView;
import butterknife.OnClick;

import static com.common.utils.UIUtils.getContext;

public class QRCodeActivity extends BaseMvpActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.t_name)
    TextView tName;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_voice)
    TextView tvVoice;
    @BindView(R.id.tv_save_qr)
    TextView tvSaveQr;
    @BindView(R.id.tv_book)
    TextView tvBook;
    @BindView(R.id.iv_qr)
    ImageView ivQr;
    public static final int REQUEST_WRITE_EXTERNAL_STORAGE = 0;

    @OnClick(R.id.back)
    public void onBackClicked() {
        onBackPressed();
    }

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qrcode;
    }

    @Override
    protected void onViewCreated() {
        String qrImg = getIntent().getStringExtra("qr_img");
        tName.setText("二维码收款");
        Glide.with(this).load(qrImg).into(ivQr);

        //检查权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
            System.out.println("请求权限");
        }
    }

    @OnClick(R.id.tv_voice)
    public void onVoiceClicked() {
//        showToastMsg("该功能暂未开放，敬请期待！");
//        DialogQR dialogQR = DialogQR.newInstance(null);
//        dialogQR.show(getSupportFragmentManager(), "DialogQR");
    }

    @OnClick(R.id.tv_save_qr)
    public void onSaveQRClicked() {
        ivQr.buildDrawingCache(true);
        ivQr.buildDrawingCache();
        Bitmap bitmap = ivQr.getDrawingCache();
        saveImage(bitmap);
        ivQr.setDrawingCacheEnabled(false);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length <= 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    showToastMsg("存储权限已禁止，如需保存二维码请开启存储权限后重试");
                }
                return;
            }
        }
    }

    @OnClick(R.id.tv_book)
    public void onBookClicked() {
        showToastMsg("该功能暂未开放，敬请期待！");

    }

    @Override
    protected void doLogicFunc() {

    }


    //保存图片
    private void saveImage(Bitmap bitmap) {
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_wechat);
        boolean isSaveSuccess = ImgUtils.saveImageToGallery(getContext(), bitmap);
        if (isSaveSuccess) {
            showToastMsg("保存图片成功");
        } else {
            showToastMsg("保存图片失败，请开启存储权限后重试");
        }
    }
}
