package com.hzxmkuar.sxmaketnew;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bigkoo.pickerview.OptionsPickerView;
import com.common.base.Constants;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.NewTestBean;
import com.common.retrofit.entity.result.PicBean;
import com.common.retrofit.entity.result.TestBean;
import com.common.retrofit.entity.resultImpl.CityBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.methods.UploadMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.retrofit.uploadfile.FileUploadObserver;
import com.common.utils.DeviceUtils;
import com.common.utils.EmptyUtils;
import com.common.utils.FileUtils;
import com.common.utils.LQRPhotoSelectUtils;
import com.common.utils.PhotoUtils;
import com.common.widget.editview.DeleteEditText;
import com.common.widget.imageview.image.ImageLoaderUtils;
import com.hzxmkuar.sxmaketnew.common.BaseUrlActivity;
import com.hzxmkuar.sxmaketnew.common.PictureCheckDialogFragment;
import com.hzxmkuar.sxmaketnew.common.photoPcker.MQPhotoPickerActivity;
import com.hzxmkuar.sxmaketnew.utils.BottomPickerUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 新商家申请入驻
 * Created by STH on 2018/5/16.
 */
public class NewZCActivity extends BaseMvpActivity {

    private ImageView mBack;
    private TextView mXieyi;
    private Button mNext;
    private int type = 0;
    private String path = "";
    private PictureCheckDialogFragment dialogFragment;
    private List<String> mList2 = new ArrayList<>();
    private List<String> mList5 = new ArrayList<>();
    private OptionsPickerView OnePickers, TwoPickers, ThreePickers;
    private String mCategory_id = "";
    private String mProportion = "";
    private String mCertificates_type = "";
    private String ID_front_img = "";
    private String license_img = "";
    private String ID_back_img = "";
    private String sheng = "";
    private String shi = "";
    private String qu = "";
    private LQRPhotoSelectUtils mLqrPhotoSelectUtils;
    /**
     * 商家名称
     */
    private DeleteEditText mEdtStoreNmae;
    /**
     * 银行账号
     */
    private DeleteEditText mEdtBankNo;
    /**
     * 代表人
     */
    private DeleteEditText mEdtRepresenter;
    /**
     * 营业电话
     */
    private DeleteEditText mEdtStorePhoneNo;
    /**
     *  商户类型
     */
    private LinearLayout mLlStoreType;
    /**
     *  商户类型
     */
    private TextView mTvStoreType;
    /**
     *  让利扣点
     */
    private LinearLayout mLlGenlis;
    /**
     *  让利扣点
     */
    private TextView mTvGenlis;
    /**
     * 省市
     */
    private LinearLayout mLlStoreInCity;
    /**
     * 商家所在省市
     */
    private TextView mTvStoreInCity;
    /**
     * 详细地址
     */
    private DeleteEditText mEdtAddresDetail;
    /**
     * 账号设置
     */
    private DeleteEditText mEdtAccountSetting;
    /**
     * 密码设置
     */
    private DeleteEditText mEdtPwdSetting;
    /**
     * 密码确定
     */
    private DeleteEditText mEdtPwdConfirm;
    /**
     * 邀请码
     */
    private DeleteEditText mEdtInviteCode;
    /**
     * 证件类型
     */
    private LinearLayout mLlCredentialsType;
    /**
     * 证件类型
     */
    private TextView mTvCredentialsType;
    /**
     *  姓名
     */
    private DeleteEditText mEdtManagerName;
    /**
     * 证件号
     */
    private DeleteEditText mEdtCredentialsNo;
    /**
     * 证件照正面
     */
    private ImageView mIvCredentialFront;
    /**
     *  证件照背面
     */
    private ImageView mIvCredentialBack;
    /**
     * 营业执照
     */
    private ImageView mIvBusinessLicense;



    private CheckBox mCheckBox;
    private String mLng;
    private String mLat;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected void setStatusBar() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_newzhuce;
    }

    @Override
    protected void onViewCreated() {
        mEdtStoreNmae = (DeleteEditText) findViewById(R.id.edt_strore_name);
        mEdtBankNo = (DeleteEditText) findViewById(R.id.edt_bank_no);
        mEdtRepresenter = (DeleteEditText) findViewById(R.id.edt_representer);
        mEdtStorePhoneNo = (DeleteEditText) findViewById(R.id.edt_store_phone_no);
        mTvStoreType = (TextView) findViewById(R.id.tv_store_type);
        mLlStoreType = (LinearLayout) findViewById(R.id.ll_store_type);
        mLlGenlis = (LinearLayout) findViewById(R.id.ll_genlis);
        mTvGenlis = (TextView) findViewById(R.id.tv_genlis);
        mLlStoreInCity = (LinearLayout) findViewById(R.id.ll_store_in_city);
        mTvStoreInCity = (TextView) findViewById(R.id.tv_store_in_city);
        mEdtAddresDetail = (DeleteEditText) findViewById(R.id.edt_address_detil);
        mEdtAccountSetting = (DeleteEditText) findViewById(R.id.edt_account_setting);
        mEdtPwdSetting = (DeleteEditText) findViewById(R.id.edt_pwd_setting);
        mEdtPwdConfirm = (DeleteEditText) findViewById(R.id.edt_pwd_confirm);
        mEdtInviteCode = (DeleteEditText) findViewById(R.id.edt_invite_code);
        mLlCredentialsType = (LinearLayout) findViewById(R.id.ll_credentials_type);
        mTvCredentialsType = (TextView) findViewById(R.id.tv_credentials_type);
        mEdtManagerName = (DeleteEditText) findViewById(R.id.edt_manager_name);
        mEdtCredentialsNo = (DeleteEditText) findViewById(R.id.edt_credentials_no);
        mIvCredentialFront = (ImageView) findViewById(R.id.iv_credential_front);
        mIvCredentialBack = (ImageView) findViewById(R.id.iv_credential_back);
        mIvBusinessLicense = (ImageView) findViewById(R.id.iv_business_license);




        mCheckBox = (CheckBox) findViewById(R.id.checkbox);
        mBack = (ImageView) findViewById(R.id.back);
        mXieyi = (TextView) findViewById(R.id.xieyi);
        mNext = (Button) findViewById(R.id.next);

        init();
        if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{"android.permission.CAMERA"},
                    1);
        }
    }

    private void init() {
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
    protected void doLogicFunc() {
        attachClickListener(mBack);
        attachClickListener(mIvCredentialFront);
        attachClickListener(mIvCredentialBack);
        attachClickListener(mIvBusinessLicense);
        attachClickListener(mTvStoreType);
        attachClickListener(mTvGenlis);
        attachClickListener(mTvStoreInCity);
        attachClickListener(mTvCredentialsType);
        attachClickListener(mXieyi);
        attachClickListener(mLlStoreType);
        attachClickListener(mLlGenlis);
        attachClickListener(mLlStoreInCity);
        attachClickListener(mLlCredentialsType);
        attachClickListener(mNext);
        goToHttpReqs();
        mList2.add("5%");
        mList2.add("10%");
        mList2.add("15%");
        mList2.add("20%");
        mList2.add("25%");
        mList2.add("30%");
        mList2.add("35%");
        mList2.add("40%");
        mList5.add("护照");
        mList5.add("法人身份证");
        mList5.add("港澳台证件");
        initOptionsPicker_two();
        initOptionsPicker_three();
    }

    private void goToHttpReqs() {
        CommonSubscriber<NewTestBean> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                statusContent();
                NewTestBean newTestBean = (NewTestBean) o;
                List<TestBean> data = newTestBean.getData();
                List<List<TestBean>> datas = new ArrayList<>();
                List<String> name = new ArrayList<>();
                List<List<String>> names = new ArrayList<>();

                for (TestBean testBean : data) {
                    datas.add(testBean.getList());
                    name.add(testBean.getName());
                    List<String> namea = new ArrayList<>();
                    for (TestBean b : testBean.getList()) {
                        String name1 = b.getName();
                        namea.add(name1);
                    }
                    names.add(namea);
                }
                initOptionsPicker_one(datas, name, names);
            }

            @Override
            public void onError(String e, int code) {
                statusContent();
                showToastMsg(e);
            }
        });
        BusinessUserMethods.getInstance().scategory(subscriber);
        rxManager.add(subscriber);
    }

    private void initOptionsPicker_one(final List<List<TestBean>> datas, List<String> name, List<List<String>> names) {
        OnePickers = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mTvStoreType.setText(datas.get(options1).get(options2).getName());
                mCategory_id = datas.get(options1).get(options2).getId();
            }
        }).setSubmitText(getString(R.string.app_confirm)).
                setCancelText(getString(R.string.app_cancel)).
                setSubmitColor(getResources().getColor(R.color.base_color)).
                setCancelColor(getResources().getColor(R.color.normal_text_color)).
                build();
        OnePickers.setPicker(name, names);
    }

    private void initOptionsPicker_two() {
        TwoPickers = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mTvGenlis.setText(mList2.get(options1));
                mProportion = mList2.get(options1);
            }
        }).setSubmitText(getString(R.string.app_confirm)).
                setCancelText(getString(R.string.app_cancel)).
                setSubmitColor(getResources().getColor(R.color.base_color)).
                setCancelColor(getResources().getColor(R.color.normal_text_color)).
                build();
        TwoPickers.setPicker(mList2);
    }

    private void initOptionsPicker_three() {
        ThreePickers = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mTvCredentialsType.setText(mList5.get(options1));
                mCertificates_type = mList5.get(options1);
            }
        }).setSubmitText(getString(R.string.app_confirm)).
                setCancelText(getString(R.string.app_cancel)).
                setSubmitColor(getResources().getColor(R.color.base_color)).
                setCancelColor(getResources().getColor(R.color.normal_text_color)).
                build();
        ThreePickers.setPicker(mList5);
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == mBack.getId()) {
            finish();
        } else if (view.getId() == mIvCredentialFront.getId()) {
            type = 1;
            showPicCheck();
        } else if (view.getId() == mIvCredentialBack.getId()) {
            type = 2;
            showPicCheck();
        } else if (view.getId() == mIvBusinessLicense.getId()) {
            type = 3;
            showPicCheck();
        } else if (view.getId() == mTvStoreType.getId()) {
            OnePickers.show();
        } else if (view.getId() == mTvGenlis.getId()) {
            TwoPickers.show();
        } else if (view.getId() == mTvStoreInCity.getId()) {
            BottomPickerUtils.showCityPicker(context, new BottomPickerUtils.CityOptionPickerCallback() {
                @Override
                public void onOptionSelect(CityBean option1, CityBean option2, CityBean option3) {
                    sheng = option1.getArea();
                    shi = option2.getArea();
                    qu = option3.getArea();
                    mTvStoreInCity.setText(option1.getArea() + "" + option2.getArea() + "" + option3.getArea());
                    mLng = option3.getLng();
                    mLat = option3.getLat();
                }

            });
        } else if (view.getId() == mTvCredentialsType.getId()) {
            ThreePickers.show();
        } else if (view.getId() == mXieyi.getId()) {
            Intent urlIntent = new Intent(context, BaseUrlActivity.class);
            urlIntent.putExtra(BaseUrlActivity.MAIN_URL, "http://xmap1802009.php.hzxmnet.com/Home/Index/article/type/10.html");
            startActivity(urlIntent);
        } else if (view.getId() == mLlStoreType.getId()) {
            OnePickers.show();
        } else if (view.getId() == mLlGenlis.getId()) {
            TwoPickers.show();
        } else if (view.getId() == mLlStoreInCity.getId()) {
            BottomPickerUtils.showCityPicker(context, new BottomPickerUtils.CityOptionPickerCallback() {
                @Override
                public void onOptionSelect(CityBean option1, CityBean option2, CityBean option3) {
                    sheng = option1.getArea();
                    shi = option2.getArea();
                    qu = option3.getArea();
                    mTvStoreInCity.setText(option1.getArea() + "" + option2.getArea() + "" + option3.getArea());
                    mLng = option3.getLng();
                    mLat = option3.getLat();
                }

            });
        } else if (view.getId() == mLlCredentialsType.getId()) {
            ThreePickers.show();
        } else if (view.getId() == mNext.getId()) {
            if (!mCheckBox.isChecked()) {
                showToastMsg("请先勾选平台协议");
                return;
            }
            if (getEditTextStr(mEdtStoreNmae).equals("") || getEditTextStr(mEdtBankNo).equals("") || getEditTextStr(mEdtRepresenter).equals("")
                    || getEditTextStr(mEdtStorePhoneNo).equals("") || mCategory_id.equals("") || mProportion.equals("")
                    || sheng.equals("") || getEditTextStr(mEdtAddresDetail).equals("") || getEditTextStr(mEdtAccountSetting).equals("")
                    || getEditTextStr(mEdtPwdSetting).equals("") || getEditTextStr(mEdtInviteCode).equals("") || mCertificates_type.equals("")
                    || getEditTextStr(mEdtManagerName).equals("") || getEditTextStr(mEdtCredentialsNo).equals("") || ID_front_img.equals("")
                    || ID_front_img.equals("") || license_img.equals("")) {
                showToastMsg("请填写完整的信息！");
            } else {
                if (!getEditTextStr(mEdtPwdSetting).equals(getEditTextStr(mEdtPwdConfirm))) {
                    showToastMsg("两次密码不一致！");
                    return;
                }
                goToHttpReqss(getEditTextStr(mEdtStoreNmae), getEditTextStr(mEdtBankNo), getEditTextStr(mEdtRepresenter), getEditTextStr(mEdtStorePhoneNo)
                        , mCategory_id, mProportion, sheng, shi, qu, getEditTextStr(mEdtAddresDetail), getEditTextStr(mEdtAccountSetting),
                        getEditTextStr(mEdtPwdSetting), getEditTextStr(mEdtInviteCode), mCertificates_type, getEditTextStr(mEdtManagerName),
                        getEditTextStr(mEdtCredentialsNo), ID_front_img, ID_back_img, license_img, mLat, mLng);
            }
        }
    }

    private void goToHttpReqss(String editTextStr, String textStr, String str, String s, String category_id,
                               String proportion, String sheng, String shi, String qu, String editTextStr1,
                               String textStr1, String str1, String s1, String certificates_type, String editTextStr2,
                               String textStr2, String ID_front_img, String id_front_img, String license_img, String t, String t1) {
        showProgressingDialog();
        CommonSubscriber<Object> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                dismissProgressDialog();
                showToastMsg("提交成功！");
                finish();
            }

            @Override
            public void onError(String e, int code) {
                dismissProgressDialog();
                showToastMsg(e);
            }
        });
        BusinessUserMethods.getInstance().apply(subscriber, editTextStr, textStr, str, s, category_id, proportion, sheng
                , shi, qu, editTextStr1, textStr1, str1, s1, certificates_type, editTextStr2, textStr2, ID_front_img, id_front_img, license_img, t, t1);
        rxManager.add(subscriber);
    }

    // 图片选择界面
    private void showPicCheck() {
        dialogFragment = new PictureCheckDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "PictureCheckDialogFragment");
        getFragmentManager().executePendingTransactions();
        dialogFragment.setTakePhotoListener(new PictureCheckDialogFragment.onTakePhotoListener() {
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

    public void selectpic() {
        try {
            startActivityForResult(MQPhotoPickerActivity.newIntent(context, null, 1, null,
                    getString(R.string.mq_send)), Constants.REQUEST_CODE_PHOTO);
        } catch (Exception e) {
            showToastMsg("当前设备不支持发送图片");
        }
    }

    public void selectpicfromsys() {
        mLqrPhotoSelectUtils.takePhoto();
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
                        ID_front_img = s.getData();
                        ImageLoaderUtils.displaySmallPhoto(mIvCredentialFront, ID_front_img);
                    } else if (type == 2) {
                        ID_back_img = s.getData();
                        ImageLoaderUtils.displaySmallPhoto(mIvCredentialBack, ID_back_img);
                    } else {
                        license_img = s.getData();
                        ImageLoaderUtils.displaySmallPhoto(mIvBusinessLicense, license_img);
                    }
                    init();
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
}
