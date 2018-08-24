package com.hzxmkuar.sxmaketnew.common;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.common.base.Constants;
import com.common.mvp.BaseDialogFragment;
import com.common.mvp.BasePresenter;
import com.common.rxbus.RxBus;
import com.common.rxbus.postevent.RxKeyEvent;
import com.common.utils.EmptyUtils;
import com.common.utils.FileUtils;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.common.photoPcker.MQPhotoPickerActivity;

import java.io.File;
import java.util.ArrayList;

/*******************************
* 图片选择弹窗
* @author syc
* created at 2017/4/14 下午 12:52
********************************/
public class PictureCheckDialogFragment extends BaseDialogFragment
{
    private TextView tvTakePic;
    private TextView tvAlbum;
    private TextView tvCancel;

    private String mCameraPicPath;
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1;       // 媒体访问权限

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.base_bottom_avater_view;
    }

    @Override
    protected void initView(Dialog dialog)
    {
        tvTakePic = (TextView) dialog.findViewById(R.id.tv_takepic);
        tvAlbum = (TextView) dialog.findViewById(R.id.tv_album);
        tvCancel = (TextView) dialog.findViewById(R.id.tv_cancel);
    }

    @Override
    public int setThemeRes() {
        return R.style.BottomDialogFragment;
    }

    private boolean outSidedismiss = true;

    @Override
    protected void setView()
    {
        dialog.setCanceledOnTouchOutside(outSidedismiss);
        final Window window = dialog.getWindow();
        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.BOTTOM;

        window.setAttributes(lp);
        if(!outSidedismiss){
            getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    return keyCode == KeyEvent.KEYCODE_BACK;
                }
            });
        }

        attachClickListener(tvTakePic);
        attachClickListener(tvAlbum);
        attachClickListener(tvCancel);
    }

    @Override
    protected void onViewClicked(View view)
    {
        if (view.getId() == tvTakePic.getId()) {
            if (checkStoragePermission()) {
                // 打开相机
                if (EmptyUtils.isNotEmpty(takePhotoListener)) {
                    takePhotoListener.takePhoto();
                } else {
                    choosePhotoFromCamera();
                }
            }
            dismissParent();
        } else if (view.getId() == tvAlbum.getId()) {
            if (checkStoragePermission()) {
                if (EmptyUtils.isNotEmpty(takePhotoListener)) {
                    takePhotoListener.takePicker();
                } else {
                    chooseFromPhotoPicker();
                }
            }
            dismissParent();
        } else if (view.getId() == tvCancel.getId()) {
            dismissParent();
        }
    }

    /**
     * 检查存储权限
     * @return true, 已经获取权限;false,没有权限,尝试获取
     */
    private boolean checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
            return false;
        } else {
            return true;
        }
    }

    public File getCameraPicFile() {
        String sdState = Environment.getExternalStorageState();
        if (!sdState.equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        File imageFile = new File(mCameraPicPath);
        if (imageFile.exists()) {
            return imageFile;
        } else {
            return null;
        }
    }

    /**
     * 打开相机
     */
    private void choosePhotoFromCamera() {

        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(FileUtils.getPicStorePath(context));
        file.mkdirs();
        String path = FileUtils.getPicStorePath(context) + "/" + System.currentTimeMillis() + ".jpg";
        File imageFile = new File(path);
        camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
        mCameraPicPath = path;
        try {
            getActivity().startActivityForResult(camera, Constants.REQUEST_CODE_CAMERA);
        } catch (Exception e) {
            showToastMsg("当前设备不支持发送图片");
        }
    }

    /**
     * 从本地选择图片
     */
    private void chooseFromPhotoPicker() {
        try {
            getActivity().startActivityForResult(MQPhotoPickerActivity.newIntent(context, null, 1, null, getString(R.string.mq_send)), Constants.REQUEST_CODE_PHOTO);
        } catch (Exception e) {
            showToastMsg("当前设备不支持发送图片");
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case WRITE_EXTERNAL_STORAGE_REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // nothing
                } else {
                    showToastMsg("未获取读取文件权限");
                }
                break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == Constants.REQUEST_CODE_CAMERA) {
                // 从 相机 获取的图片
                File cameraPicFile = getCameraPicFile();
                if (cameraPicFile != null) {
                    RxBus.getDefault().post(new RxKeyEvent(
                            RxKeyEvent.PICTURE_IS_RESULT, cameraPicFile.getPath()));
                }
            } else if (requestCode == Constants.REQUEST_CODE_PHOTO) {
                // 从 相册 获取的图片
                ArrayList<String> selectedPhotos = MQPhotoPickerActivity.getSelectedImages(data);
                for (String photoPath : selectedPhotos) {
                    RxBus.getDefault().post(new RxKeyEvent(
                            RxKeyEvent.PICTURE_IS_RESULT, photoPath));
                }
            }
        }
    }

    private onTakePhotoListener takePhotoListener;

    public void setTakePhotoListener(onTakePhotoListener takePhotoListener) {
        this.takePhotoListener = takePhotoListener;
    }

    public interface onTakePhotoListener {
        void takePhoto();
        void takePicker();
    }
}
