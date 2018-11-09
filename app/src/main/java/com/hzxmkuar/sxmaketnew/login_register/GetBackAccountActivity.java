package com.hzxmkuar.sxmaketnew.login_register;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.common.base.Constants;
import com.common.event.BaseEvent;
import com.common.event.EventBusConstants;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.PicBean;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.methods.SmsMethods;
import com.common.retrofit.methods.UploadMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.retrofit.uploadfile.FileUploadObserver;
import com.common.utils.DeviceUtils;
import com.common.utils.EmptyUtils;
import com.common.utils.FileUtils;
import com.common.utils.LQRPhotoSelectUtils;
import com.common.utils.PhotoUtils;
import com.common.widget.dialog.SendPhoneVerifyDialog;
import com.common.widget.editview.DeleteEditText;
import com.common.widget.imageview.image.ImageLoaderUtils;
import com.common.widget.textview.CountdownButton;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.common.PictureCheckDialogFragment;
import com.hzxmkuar.sxmaketnew.common.photoPcker.MQPhotoPickerActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 忘记账号找回
 * Created by Administrator on 2018/8/22.
 */
public class GetBackAccountActivity extends BaseMvpActivity {
    private static final String TAG = "GetBackAccountActivity";
    private ImageView iv_getback_account_back;
    private DeleteEditText edt_input_shop_name;
    private DeleteEditText edt_legal_name_getback;
    private TextView tv_credentials_type;
    /**
     * 选择后的证件类型
     */
    private String mCertificatesType = "";
    private DeleteEditText edt_credentials_no_getback;
    private ImageView iv_credential_front_getback;
    private ImageView iv_credential_back_getback;
    private ImageView iv_business_license_getback;
    private int type = 0;
    private String id_front_img_getBack="";
    private String id_back_img_getBack="";
    private String license_img="";
    private String path ="";
    private DeleteEditText edt_reserved_phone_no_getback;
    private DeleteEditText edt_input_ver_code_getback;
    private CountdownButton cd_btn_send_msg_getback;
    private Button btn_commit_getback;
    private OptionsPickerView mCertificatesTypePicker;
    private List<String> idTypeList = new ArrayList<>();
    private PictureCheckDialogFragment pickerPhotoDialog;
    private LQRPhotoSelectUtils mLqrPhotoSelectUtils;
    private TextView tv_phone_verify_getback_account;

    private boolean canClickAble = true;
    /**
     *  是否发送语音验证码的弹窗
     */
    private SendPhoneVerifyDialog phoneVerifyDialog;
    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_get_back_account;
    }

    @Override
    protected void onViewCreated() {
        registerEvent();
        iv_getback_account_back = (ImageView) findViewById(R.id.iv_getback_account_back);
        edt_input_shop_name = (DeleteEditText) findViewById(R.id.edt_input_shop_name);
        edt_legal_name_getback = (DeleteEditText) findViewById(R.id.edt_legal_name_getback);
        tv_credentials_type = (TextView) findViewById(R.id.tv_credentials_type);
        edt_credentials_no_getback = (DeleteEditText) findViewById(R.id.edt_credentials_no_getback);
        iv_credential_front_getback = (ImageView) findViewById(R.id.iv_credential_front_getback);
        iv_credential_back_getback = (ImageView) findViewById(R.id.iv_credential_back_getback);
        iv_business_license_getback = (ImageView) findViewById(R.id.iv_business_license_getback);
        edt_reserved_phone_no_getback = (DeleteEditText) findViewById(R.id.edt_reserved_phone_no_getback);
        edt_input_ver_code_getback = (DeleteEditText) findViewById(R.id.edt_input_ver_code_getback);
        cd_btn_send_msg_getback = (CountdownButton) findViewById(R.id.cd_btn_send_msg_getback);
        btn_commit_getback = (Button) findViewById(R.id.btn_commit_getback);
        tv_phone_verify_getback_account = (TextView) findViewById(R.id.tv_phone_verify_getback_account);
        tv_phone_verify_getback_account.setVisibility(View.INVISIBLE);


        phoneVerifyDialog = new SendPhoneVerifyDialog(context, GetBackAccountActivity.this);
        phoneVerifyDialog.setOnDialogButtonClickListener(new SendPhoneVerifyDialog.OnDialogButtonClickListener() {
            @Override
            public void cancelLick() {
                canClickAble = true;
            }

            @Override
            public void confirmClick() {
                canClickAble = false;
                cd_btn_send_msg_getback.restart();
                if (!EmptyUtils.isEmpty(getEditTextStr(edt_reserved_phone_no_getback))){
                    sendVoiceVerifyCodeReq();
                }else {
                    showToastMsg("手机号码格式不正确");
                }
            }
        });

    }

    @Override
    protected void setStatusBar() {
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(iv_getback_account_back);
        attachClickListener(iv_credential_front_getback);
        attachClickListener(iv_credential_back_getback);
        attachClickListener(iv_business_license_getback);
        attachClickListener(tv_credentials_type);
        attachClickListener(cd_btn_send_msg_getback);
        attachClickListener(btn_commit_getback);
        attachClickListener(tv_phone_verify_getback_account);
        initCertificateTypePicker();
        initImgPicker();
        if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{"android.permission.CAMERA"}, 1);
        }
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == iv_getback_account_back.getId()) {
            finish();
        } else if (view.getId() == btn_commit_getback.getId()) {
            verifyInput(btn_commit_getback);
        }  else if (view.getId() == cd_btn_send_msg_getback.getId()) {
            verifyInput(cd_btn_send_msg_getback);
        } else if (view.getId() == iv_getback_account_back.getId()) {
            finish();
        } else if (view.getId() == tv_credentials_type.getId()) {
            mCertificatesTypePicker.show();
        } else if (view.getId() == iv_credential_front_getback.getId()) {
            type = 1;
            showPicCheck();
        }else if ( view.getId() == iv_credential_back_getback.getId()){
            type = 2;
            showPicCheck();
        }else if ( view.getId() == iv_business_license_getback.getId()){
            type = 3;
            showPicCheck();
        }else if ( view.getId() == tv_phone_verify_getback_account.getId()){
            if (canClickAble){
                phoneVerifyDialog.show();
            }
        }
    }

    /**
     * 发送语音短信验证码
     */
    private void sendVoiceVerifyCodeReq() {
        CommonSubscriber<Object> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                showToastMsg("发送成功");
            }

            @Override
            public void onError(String e, int code) {
                showToastMsg(e);
            }
        });
        List<String> paramaList = new ArrayList<>();
        paramaList.add("time");
        paramaList.add("mobile");
        paramaList.add("checktype");
        SmsMethods.getInstance().sendVoiceVerifyCode(subscriber,getEditTextStr(edt_reserved_phone_no_getback),8, paramaList);
        rxManager.add(subscriber);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void newAdd(BaseEvent event) {
        switch (event.getTag()) {
            case EventBusConstants.TIME_OUT:
                tv_phone_verify_getback_account.setVisibility(View.VISIBLE);
                String eventStr = (String)event.getS();
                if ("show".equals(eventStr)){
//                    canClickAble = false;
                }else if ("canRestart".equals(eventStr)){
                    canClickAble = true;
                }
                break;
        }
    }
    /**
     * 验证商户输入的合法性
     */
    private void verifyInput(View view) {
       if (EmptyUtils.isEmpty(getEditTextStr(edt_input_shop_name))) {
            showToastMsg("商家名称不能为空，请输入您的商铺名称");
            return;
        } else if (getEditTextStr(edt_input_shop_name).length() < 2 ) {
            showToastMsg("法商家名称格式不正确");
            return;
        }  else if (EmptyUtils.isEmpty(getEditTextStr(edt_legal_name_getback))) {
            showToastMsg("法人姓名不能为空，请输入法人姓名");
            return;
        } else if (getEditTextStr(edt_legal_name_getback).length() < 2) {
            showToastMsg("法人姓名格式不正确");
            return;
        } else if (EmptyUtils.isEmpty(getTextViewStr(tv_credentials_type))) {
            showToastMsg("证件类型不能为空，请输入您的证件类型");
            return;
        } else if (EmptyUtils.isEmpty(getEditTextStr(edt_credentials_no_getback))) {
            showToastMsg("证件号码不能为空");
            return;
        } else if (getEditTextStr(edt_credentials_no_getback).length() < 6) {
            showToastMsg("证件号码格式不正确，请重新输入");
            return;
        } else if (EmptyUtils.isEmpty(id_front_img_getBack)) {
           showToastMsg("请上传身份证正面照");
           return;
       } else if (EmptyUtils.isEmpty(id_back_img_getBack)) {
           showToastMsg("请上传身份证反面照");
           return;
       } else if (EmptyUtils.isEmpty(license_img)) {
           showToastMsg("请上传营业执照");
           return;
       }else if (EmptyUtils.isEmpty(getEditTextStr(edt_reserved_phone_no_getback))) {
            showToastMsg("预留手机号码不能为空");
            return;
        } else if (getEditTextStr(edt_reserved_phone_no_getback).length() < 11) {
            showToastMsg("预留手机号码格式不对，请重新输入");
            return;
        }
        /* 如果当前按钮发送验证码，则不需要走后面的判断。如果当前点击的按钮为提交按钮，则需要判断前面所有的逻辑都需要判断，包括后面的逻辑判断。 */
        if (view.getId() == cd_btn_send_msg_getback.getId()) {
            cd_btn_send_msg_getback.getInputContent(getEditTextStr(edt_reserved_phone_no_getback));
            sendVerifyCodeMsg();
        } else if (view.getId() == btn_commit_getback.getId()) {
            if (EmptyUtils.isEmpty(getEditTextStr(edt_input_ver_code_getback))) {
                showToastMsg("验证码不能为空");
                return;
            } else if (getEditTextStr(edt_input_ver_code_getback).length() < 6) {
                showToastMsg("验证码格式不对，请重新输入");
                return;
            }
            commitInputInfo(getEditTextStr(edt_input_shop_name), getEditTextStr(edt_legal_name_getback),
                            getTextViewStr(tv_credentials_type), getTextViewStr(edt_credentials_no_getback),
                            id_front_img_getBack, id_back_img_getBack,
                            license_img,getEditTextStr(edt_reserved_phone_no_getback),
                            getEditTextStr(edt_input_ver_code_getback));
        }
    }

    /**
     *  提交用户输入的信息
     * @param shopName 商家名称 <br/>
     * @param documentName 法人姓名 <br/>
     * @param certificatesType 证件类型 <br/>
     * @param certificatesNumber 证件号码 <br/>
     * @param ID_front_img 身份证正面照片 <br/>
     * @param ID_back_img 身份证反面照片 <br/>
     * @param license_img 营业执照 <br/>
     * @param phone 预留手机号 <br/>
     * @param checkcode 验证码 <br/>
     */
    private void commitInputInfo(String shopName, String documentName,
                                 String certificatesType, String certificatesNumber,
                                 String ID_front_img, String ID_back_img,
                                 String license_img,
                                 String phone,String checkcode) {
        CommonSubscriber<Object> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                dismissProgressDialog();
                showToastMsg("申请已提交");
                finish();
            }

            @Override
            public void onError(String e, int code) {
                dismissProgressDialog();
                showToastMsg(e);
            }
        });
        BusinessUserMethods.getInstance().forgetAccountGetBack(subscriber, shopName, documentName,
                certificatesType, certificatesNumber,
                ID_front_img, ID_back_img, license_img,phone,checkcode);
        rxManager.add(subscriber);

    }

    /**
     * 发送验证码
     */
    private void sendVerifyCodeMsg() {
        CommonSubscriber<HttpRespBean> sendVerCodeSub = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                dismissProgressDialog();
                showToastMsg("短信验证码已发送成功");
            }

            @Override
            public void onError(String e, int code) {
                dismissProgressDialog();
                showToastMsg(e);
            }
        });
        SmsMethods.getInstance().sendVerCode(sendVerCodeSub, getEditTextStr(edt_reserved_phone_no_getback), "8");
        rxManager.add(sendVerCodeSub);
    }

    /**
     * 初始化证件类型拾取器
     */
    private void initCertificateTypePicker() {
        idTypeList.add("护照");
        idTypeList.add("法人身份证");
        idTypeList.add("港澳台证件");
        mCertificatesTypePicker = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tv_credentials_type.setText(idTypeList.get(options1));
                mCertificatesType = idTypeList.get(options1);
            }
        }).setSubmitText(getString(R.string.app_confirm)).
                setCancelText(getString(R.string.app_cancel)).
                setSubmitColor(getResources().getColor(R.color.base_color)).
                setCancelColor(getResources().getColor(R.color.normal_text_color)).
                build();
        mCertificatesTypePicker.setPicker(idTypeList);
    }


    /**
     * 初始化图片选择器
     */
    private void initImgPicker() {
        // 1、创建LQRPhotoSelectUtils（一个Activity对应一个LQRPhotoSelectUtils）
        mLqrPhotoSelectUtils = new LQRPhotoSelectUtils(this, new LQRPhotoSelectUtils.PhotoSelectListener() {
            @Override
            public void onFinish(File outputFile, Uri outputUri) {
                // 4、当拍照或从图库选取图片成功后回调
                uploadFile(outputFile.getPath());
            }
        }, false);//true裁剪，false不裁剪
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 10001) {
                mLqrPhotoSelectUtils.attachToActivityForResult(requestCode, resultCode, data);
            } else if (requestCode == Constants.REQUEST_CODE_PHOTO) {
                // 从 相册 获取的图片
                ArrayList<String> selectedPhotos = MQPhotoPickerActivity.getSelectedImages(data);
                if (EmptyUtils.isEmpty(selectedPhotos)) {
                    showToastMsg("尚未选择图片");
                    return;
                }
                path = selectedPhotos.get(0);
                uploadFile(path);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 选择、上传图片
     *
     * @param imageUrl
     */
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
                public void onUpLoadSuccess(PicBean s) {
                    dismissProgressDialog();
                    if (type == 1) {
                        id_front_img_getBack = s.getData();
                        ImageLoaderUtils.displaySmallPhoto(iv_credential_front_getback, id_front_img_getBack);
                    } else if (type == 2) {
                        id_back_img_getBack = s.getData();
                        ImageLoaderUtils.displaySmallPhoto(iv_credential_back_getback, id_back_img_getBack);
                    } else {
                        license_img = s.getData();
                        ImageLoaderUtils.displaySmallPhoto(iv_business_license_getback, license_img);
                    }
                    initImgPicker();
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

    // 图片选择界面
    private void showPicCheck() {
        pickerPhotoDialog = new PictureCheckDialogFragment();
        pickerPhotoDialog.show(getSupportFragmentManager(), "PictureCheckDialogFragment");
        getFragmentManager().executePendingTransactions();
        pickerPhotoDialog.setTakePhotoListener(new PictureCheckDialogFragment.onTakePhotoListener() {
            @Override
            public void takePhoto() {
                selectpicfromsys();
            }

            @Override
            public void takePicker() {
                selectpic();
            }
        });
    }

    public void selectpicfromsys() {
        mLqrPhotoSelectUtils.takePhoto();
    }
    public void selectpic() {
        try {
            startActivityForResult(MQPhotoPickerActivity.newIntent(context, null, 1, null,
                    getString(R.string.mq_send)), Constants.REQUEST_CODE_PHOTO);
        } catch (Exception e) {
            showToastMsg("当前设备不支持发送图片");
        }
    }

}
