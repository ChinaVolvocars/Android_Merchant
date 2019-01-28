package com.hzxmkuar.sxmaketnew.fragment;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.common.base.Constants;
import com.common.mvp.BaseMvpFragment;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.PicBean;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.methods.UploadMethods;
import com.common.retrofit.model.InvoiceElectronic;
import com.common.retrofit.uploadfile.FileUploadObserver;
import com.common.utils.DeviceUtils;
import com.common.utils.EmptyUtils;
import com.common.utils.FileUtils;
import com.common.utils.PhotoUtils;
import com.common.utils.SPUtils;
import com.google.gson.Gson;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.adapter.InvoiceElectronicAdapter;
import com.hzxmkuar.sxmaketnew.common.PictureCheckDialogFragment;
import com.hzxmkuar.sxmaketnew.common.photoPcker.MQPhotoPickerActivity;
import com.hzxmkuar.sxmaketnew.utils.camera.CropOptions;
import com.hzxmkuar.sxmaketnew.utils.camera.ImageFiles;
import com.hzxmkuar.sxmaketnew.utils.camera.IntentUtils;
import com.hzxmkuar.sxmaketnew.view.dialog.WithdrawDialogFragment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Subscriber;

import static com.hzxmkuar.sxmaketnew.fragment.InvoiceExpressFragment.WID;
import static com.hzxmkuar.sxmaketnew.utils.camera.UriParse.getFileProviderName;

/**
 * 提交发票 电子发票
 */
public class InvoiceElectronicFragment extends BaseMvpFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.ll_add)
    LinearLayout llAdd;
    private Unbinder bind;
    private String wId;
    private InvoiceElectronicAdapter adapter;
    private PictureCheckDialogFragment dialogFragment;

    private ArrayList<String> permissions = new ArrayList<>();
    /**
     * requestCode 请求权限
     **/
    public final static int PERMISSION_REQUEST_TAKE_PHOTO = 2000;
    /**
     * request Code 从相机获取照片并裁剪
     **/
    public final static int RC_PICK_PICTURE_FROM_CAPTURE_CROP = 1002;
    /**
     * request Code 从相机获取照片不裁剪
     **/
    public final static int RC_PICK_PICTURE_FROM_CAPTURE = 1003;
    /**
     * request Code 裁剪照片
     **/
    public final static int RC_CROP = 1001;
    private CropOptions cropOptions;

    private Uri tempUri;
    private String imageAbsolutePath;
    /**
     * 上传发票的图片位置
     */
    private int uploadInvoicePosition = -1;

    public static InvoiceElectronicFragment newInstance(Bundle bundle) {
        InvoiceElectronicFragment fragment = new InvoiceElectronicFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invoice_electronic;
    }

    @Override
    protected void onViewCreated(View view) {
        bind = ButterKnife.bind(this, view);
       /* Bundle bundle = getArguments();
        wId = bundle.getString(WID, "0");*/

        wId = SPUtils.getShareString(WID);
        System.out.println("--11-获取到的值：" + wId);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        adapter = new InvoiceElectronicAdapter(getContext());
        recyclerView.setAdapter(adapter);

        InvoiceElectronic item = new InvoiceElectronic();
        adapter.add(item);

        adapter.setOnItemClickListener(new InvoiceElectronicAdapter.OnDateChangeListener() {
            @Override
            public void onDateChange(int position, List<InvoiceElectronic> list) {
                for (int i = 0; i < list.size(); i++) {
                    String invoice_code = list.get(i).getInvoice_code();
                    String invoice_num = list.get(i).getInvoice_num();
                    String img_url = list.get(i).getImg_url();
                    if (i == 0 && !TextUtils.isEmpty(invoice_code) && !TextUtils.isEmpty(invoice_num)) {
                        llAdd.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onAddImgClick(int position, List<InvoiceElectronic> list) {
                uploadInvoicePosition = position;
                uploadInvoice();
            }
        });
    }

    @OnClick(R.id.ll_add)
    public void onAddClicked() {
        List<InvoiceElectronic> list = adapter.getDate();
        for (int i = 0; i < list.size(); i++) {
            String invoice_num = list.get(i).getInvoice_num();
            String invoice_code = list.get(i).getInvoice_code();
            String img_url = list.get(i).getImg_url();
            if (TextUtils.isEmpty(invoice_num)
                    || TextUtils.isEmpty(invoice_code)) {
                showToastMsg("请填写完毕再添加");
                return;
            }
        }

        InvoiceElectronic item = new InvoiceElectronic();
        adapter.add(item);
    }


    @OnClick(R.id.tv_confirm)
    public void onConfirmClicked() {
        List<InvoiceElectronic> list = adapter.getDate();
        for (int i = 0; i < list.size(); i++) {
            InvoiceElectronic electronic = list.get(i);
            String invoice_code = electronic.getInvoice_code();
            String invoice_num = electronic.getInvoice_num();
            if (TextUtils.isEmpty(invoice_code)) {
                showToastMsg("请填写发票代码");
                return;
            }
            if (TextUtils.isEmpty(invoice_num)) {
                showToastMsg("请填写发票号码");
                return;
            }
        }

        Gson gson = new Gson();
        String listJson = gson.toJson(list);

        Log.e("发票", "发票: " + listJson);
        BusinessUserMethods.getInstance().invoiceSubmit(new Subscriber<HttpRespBean>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                showToastMsg(e.getMessage());
            }

            @Override
            public void onNext(HttpRespBean httpRespBean) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("flag", false);
                bundle.putString("invoice", "invoice");
                WithdrawDialogFragment dialogFragment = WithdrawDialogFragment.newInstance(bundle);
                dialogFragment.show(getFragmentManager(), "WithdrawDialogFragment");
            }
        }, wId, 1, listJson);

    }


    @Override
    protected void doLogicFunc() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != bind) bind.unbind();
    }

    //上传发票
    private void uploadInvoice() {

        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        imageAbsolutePath = file.getAbsolutePath();
        Log.e("图片", "图片: " + imageAbsolutePath);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.tempUri = FileProvider.getUriForFile(context, getFileProviderName(context), file);

        } else {
            //否则就用老系统的默认模式
            this.tempUri = Uri.fromFile(file);
        }

        Log.e("图片", "图片: " + tempUri);

        boolean cameraGranted = true, storageGranted = true, storageReadGranted = true;
        //Android 8.0 行为变更 https://developer.android.com/about/versions/oreo/android-8.0-changes#rmp
        storageGranted = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        storageReadGranted = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        cameraGranted = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        if (!storageGranted) permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (!storageReadGranted) permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        if (!cameraGranted) permissions.add(Manifest.permission.CAMERA);

        if (storageGranted && cameraGranted) {
            //检查是否有相机
            final Intent captureIntent = IntentUtils.getCaptureIntent(tempUri);
            List result = getActivity().getPackageManager().queryIntentActivities(captureIntent, PackageManager.MATCH_ALL);
            if (result.isEmpty()) {
                showToastMsg(getString(R.string.no_camera));
            } else {
                uploadInvoiceDialog(captureIntent);
            }
        } else {
            //请求权限
            if (Build.VERSION.SDK_INT >= 23) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), PERMISSION_REQUEST_TAKE_PHOTO);
            } else {
                ActivityCompat.requestPermissions(getActivity(), permissions.toArray(new String[permissions.size()]), PERMISSION_REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void uploadInvoiceDialog(final Intent captureIntent) {
        dialogFragment = new PictureCheckDialogFragment();
        dialogFragment.show(getFragmentManager(), "PictureCheckDialogFragment");
        getFragmentManager().executePendingTransactions();
        dialogFragment.setTakePhotoListener(new PictureCheckDialogFragment.onTakePhotoListener() {
            @Override
            public void takePhoto() {
                capture(captureIntent);
            }

            @Override
            public void takePicker() {
                selectPic();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_TAKE_PHOTO) {
            boolean cameraGranted = true;
            boolean storageGranted = true;
            for (int i = 0, j = permissions.length; i < j; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    if (TextUtils.equals(Manifest.permission_group.STORAGE, permissions[i])) {
                        storageGranted = false;
                    } else if (TextUtils.equals(Manifest.permission.CAMERA, permissions[i])) {
                        cameraGranted = false;
                    }
                }
            }
            if (cameraGranted && storageGranted) {
                showToastMsg(getString(R.string.authorized));
            }
            if (!cameraGranted && storageGranted) {
                showToastMsg(getString(R.string.no_photo_permission));
            }
            if (!storageGranted && cameraGranted) {
                showToastMsg(getString(R.string.no_sd_card_permissions));
            }
            if (!storageGranted && !cameraGranted) {
                showToastMsg(getString(R.string.no_sd_card_and_photo_permissions));
            }
        }
    }

    /**
     * 拍照
     */
    public void capture(Intent intent) {
        startActivityForResult(intent, RC_PICK_PICTURE_FROM_CAPTURE);
    }

    /**
     * 选取相册里面的图片
     */
    public void selectPic() {
        try {
            startActivityForResult(MQPhotoPickerActivity.newIntent(context, null, 1, null,
                    getString(R.string.mq_send)), Constants.REQUEST_CODE_PHOTO);
        } catch (Exception e) {
            showToastMsg("当前设备不支持发送图片");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RC_PICK_PICTURE_FROM_CAPTURE:
                //拍照
                if (resultCode == Activity.RESULT_OK) {
                    //裁剪的输出 Uri 必须使用 Uri.fromFile(File file) ,否则会系统会提示无法保存经过裁剪的照片
//                    Uri outPutUriFromFile = Uri.fromFile(new File(UriParse.parseOwnUri(getActivity(), outPutUri)));
                    try {
                        uploadFile(imageAbsolutePath);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case Constants.REQUEST_CODE_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    // 从 相册 获取的图片
                    ArrayList<String> selectedPhotos = MQPhotoPickerActivity.getSelectedImages(data);
                    if (EmptyUtils.isEmpty(selectedPhotos)) {
                        showToastMsg("尚未选择图片");
                        return;
                    }
                    String path = selectedPhotos.get(0);
//                /storage/emulated/0/HuXiaobai/cache/hxb_15433683184973c545990289b4818a4fc317914746dfd.jpg
                    System.out.println("头像路径：" + path);
                    uploadFile(path);
                }
                break;
        }
    }

    private void uploadFile(final String imageUrl) {
        if (EmptyUtils.isEmpty(imageUrl)) {
            showToastMsg("请选择图片");
            return;
        }
        showProgressingDialog();

        final Bitmap bitmap;
        try {
            bitmap = PhotoUtils.revitionImageSize(imageUrl);
            FileUploadObserver<PicBean> subscriber = new FileUploadObserver<PicBean>() {
                @Override
                public void onUpLoadSuccess(PicBean picBean) {
                    dismissProgressDialog();
                    String pic = picBean.getData();
                    Log.e("图片上传成功", "图片上传成功: " + pic);
                    //发票图片
                    adapter.getDate().get(uploadInvoicePosition).setImg_url(pic);
                    adapter.notifyDataSetChanged();
                    showToastMsg("图片上传成功");

                }

                @Override
                public void onUpLoadFail(Throwable e) {
                    dismissProgressDialog();
                }

                @Override
                public void onProgress(int progress) {
                }
            };
            UploadMethods.getInstance().photoUpload(subscriber, FileUtils.saveBitmap(bitmap, DeviceUtils.getUUID()));
            rxManager.add(subscriber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onCrop(Uri imageUri, Uri outPutUri, CropOptions options) {
        if (!ImageFiles.checkMimeType(getContext(), ImageFiles.getMimeType(getActivity(), imageUri))) {
            Toast.makeText(context, getString(R.string.selected_is_not_an_image), Toast.LENGTH_SHORT).show();
            return;
        }
        cropWithApp(imageUri, outPutUri, options);
    }

    private void cropWithApp(Uri imageUri, Uri outPutUri, CropOptions options) {
        Intent nativeCropIntent = IntentUtils.getCropIntentWithOtherApp(imageUri, outPutUri, options);
        List result = getActivity().getPackageManager().queryIntentActivities(nativeCropIntent, PackageManager.MATCH_ALL);
        if (!result.isEmpty()) {
            startActivityForResult(IntentUtils.getCropIntentWithOtherApp(imageUri, outPutUri, options), RC_CROP);
        }
    }

    private CropOptions getCropOptions() {
        CropOptions.Builder options = new CropOptions.Builder();
        options.setWithOwnCrop(false)
                .setAspectX(0)
                .setAspectY(0)
                .setOutputX(800)
                .setOutputY(800);
        return options.create();
    }

}
