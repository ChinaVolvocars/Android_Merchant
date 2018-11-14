package com.hzxmkuar.sxmaketnew.login_register;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.common.base.Constants;
import com.common.event.BaseEvent;
import com.common.event.EventBusConstants;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.NewTestBean;
import com.common.retrofit.entity.result.PicBean;
import com.common.retrofit.entity.result.TestBean;
import com.common.retrofit.entity.resultImpl.CityBean;
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
import com.hzxmkuar.sxmaketnew.base.BaseUrlActivity;
import com.hzxmkuar.sxmaketnew.common.PictureCheckDialogFragment;
import com.hzxmkuar.sxmaketnew.common.photoPcker.MQPhotoPickerActivity;
import com.hzxmkuar.sxmaketnew.utils.BottomPickerUtils;
import com.hzxmkuar.sxmaketnew.view.dialog.JoinSucceedDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 新商家申请入驻
 * Created by STH on 2018/5/16.
 */
public class NewZCActivity extends BaseMvpActivity {
    private static final String TAG = "NewZCActivity";
    private ImageView mBack;
    private TextView mXieyi;
    private Button mNext;
    private int type = 0;
    private String path = "";
    private PictureCheckDialogFragment dialogFragment;
    /**
     * 提拔比例集合
     */
    private List<String> mProfitsList = new ArrayList<>();
    /**
     * 证件类型集合
     */
    private List<String> credentialsTypeList = new ArrayList<>();
    /**
     * storeTypePicker  商铺类型选择器 <br/>
     * profitsPickers   提拔比例选择器 <br/>
     * credentialsTypePickers 证件类型选择器 <br/>
     */
    private OptionsPickerView storeTypePicker, profitsPickers, credentialsTypePickers;
    /**
     * 选择后的商户类型
     */
    private String mStoreType = "";
    /**
     * 选择后的提拔比例
     */
    private String mProportion = "";
    /**
     * 选择的证件类型
     */
    private String mCertificates_type = "";
    private String ID_front_img = "";
    private String ID_back_img = "";
    /**
     * 营业执照
     */
    private String license_img = "";
    /**
     * 省
     */
    private String provinceStr = "";
    /**
     * 市
     */
    private String cityStr = "";
    /**
     * 区
     */
    private String areaStr = "";
    private LQRPhotoSelectUtils mLqrPhotoSelectUtils;
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
     * 商家名称
     */
    private DeleteEditText mEdtStoreNmae;
    /**
     * 营业电话
     */
    private DeleteEditText mEdtStorePhoneNo;
    /**
     * 商户类型
     */
    private LinearLayout mLlStoreType;
    /**
     * 商户类型
     */
    private TextView mTvStoreType;
    /**
     * 让利扣点
     */
    private LinearLayout mLlProfits;
    /**
     * 让利扣点
     */
    private TextView mTvProfits;
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
     * 邀请码
     */
    private DeleteEditText mEdtInviteCode;
    /**
     * 法人姓名
     */
    private DeleteEditText mEdtLegalName;
    /**
     * 证件类型
     */
    private LinearLayout mLlCredentialsType;
    /**
     * 证件类型
     */
    private TextView mTvCredentialsType;
    /**
     * 证件照正面
     */
    private ImageView mIvCredentialFront;
    /**
     * 证件照背面
     */
    private ImageView mIvCredentialBack;
    /**
     * 营业执照
     */
    private ImageView mIvBusinessLicense;
    private CheckBox mCheckBox;
    /**
     * 法人证件号
     */
    private DeleteEditText mEdtCredentialsNo;

    /**
     * 法人授权代理人姓名
     */
//    private DeleteEditText mEdtInputManagerName;
    /**
     * 法人授权代理人手机号
     */
    private DeleteEditText mEdtManagerPhoneNo;
    /**
     * 验证码
     */
    private DeleteEditText mEdtInputVerCode;
    /**
     * 发送验证码
     */
    private CountdownButton mCdBtnSendMsg;

//    private String mLng;
//    private String mLat;
    private TextView tv_phone_verify_new_add;

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
    protected void setStatusBar() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_newzhuce;
    }

    @Override
    protected void onViewCreated() {
        registerEvent();
        mEdtAccountSetting = (DeleteEditText) findViewById(R.id.edt_account_setting);
        mEdtPwdSetting = (DeleteEditText) findViewById(R.id.edt_pwd_setting);
        mEdtPwdConfirm = (DeleteEditText) findViewById(R.id.edt_pwd_confirm);
        mEdtStoreNmae = (DeleteEditText) findViewById(R.id.edt_strore_name);
        mEdtStorePhoneNo = (DeleteEditText) findViewById(R.id.edt_store_phone_no);
        mTvStoreType = (TextView) findViewById(R.id.tv_store_type);
        mLlStoreType = (LinearLayout) findViewById(R.id.ll_store_type);
        mLlProfits = (LinearLayout) findViewById(R.id.ll_profits);
        mTvProfits = (TextView) findViewById(R.id.tv_profits);
        mLlStoreInCity = (LinearLayout) findViewById(R.id.ll_store_in_city);
        mTvStoreInCity = (TextView) findViewById(R.id.tv_store_in_city);
        mEdtAddresDetail = (DeleteEditText) findViewById(R.id.edt_address_detil);
        mEdtInviteCode = (DeleteEditText) findViewById(R.id.edt_invite_code);
        mLlCredentialsType = (LinearLayout) findViewById(R.id.ll_credentials_type);
        mEdtLegalName = (DeleteEditText) findViewById(R.id.edt_legal_name);
        mTvCredentialsType = (TextView) findViewById(R.id.tv_credentials_type);

        mEdtCredentialsNo = (DeleteEditText) findViewById(R.id.edt_credentials_no);
        mIvCredentialFront = (ImageView) findViewById(R.id.iv_credential_front);
        mIvCredentialBack = (ImageView) findViewById(R.id.iv_credential_back);
        mIvBusinessLicense = (ImageView) findViewById(R.id.iv_business_license);


//        mEdtInputManagerName = (DeleteEditText) findViewById(R.id.edt_input_manager_name);
        mEdtManagerPhoneNo = (DeleteEditText) findViewById(R.id.edt_manager_phone_no);
        mEdtInputVerCode = (DeleteEditText) findViewById(R.id.edt_input_ver_code);
        mCdBtnSendMsg = (CountdownButton) findViewById(R.id.cd_btn_send_msg);


        mCheckBox = (CheckBox) findViewById(R.id.checkbox);
        mBack = (ImageView) findViewById(R.id.back);
        mXieyi = (TextView) findViewById(R.id.xieyi);
        mNext = (Button) findViewById(R.id.next);
        tv_phone_verify_new_add = (TextView) findViewById(R.id.tv_phone_verify_new_add);

        initImgPicker();
        if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{"android.permission.CAMERA"}, 1);
        }

        tv_phone_verify_new_add.setVisibility(View.INVISIBLE);
        phoneVerifyDialog = new SendPhoneVerifyDialog(context, NewZCActivity.this);
        phoneVerifyDialog.setOnDialogButtonClickListener(new SendPhoneVerifyDialog.OnDialogButtonClickListener() {
            @Override
            public void cancelLick() {
                canClickAble = true;
            }

            @Override
            public void confirmClick() {
                canClickAble = false;
                mCdBtnSendMsg.restart();
                if (!EmptyUtils.isEmpty(getEditTextStr(mEdtManagerPhoneNo))){
                    sendVoiceVerifyCodeReq();
                }else {
                    showToastMsg("手机号码格式不正确");
                }
            }
        });
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
    protected void doLogicFunc() {
        attachClickListener(mBack);
        attachClickListener(mIvCredentialFront);
        attachClickListener(mIvCredentialBack);
        attachClickListener(mIvBusinessLicense);
        attachClickListener(mTvStoreType);
        attachClickListener(mTvProfits);
        attachClickListener(mTvStoreInCity);
        attachClickListener(mTvCredentialsType);
        attachClickListener(mXieyi);
        attachClickListener(mLlStoreType);
        attachClickListener(mLlProfits);
        attachClickListener(mLlStoreInCity);
        attachClickListener(mLlCredentialsType);
        attachClickListener(mNext);
        attachClickListener(mCdBtnSendMsg);
        attachClickListener(tv_phone_verify_new_add);
        goToHttpReqs();
        initProfitsPickers();
        initCredentialsTypePickers();
    }

    private void goToHttpReqs() {
        CommonSubscriber<NewTestBean> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                statusContent();
                NewTestBean newTestBean = (NewTestBean) o;
                List<TestBean> data = newTestBean.getData();
                List<List<TestBean>> datas = new ArrayList<>();
                List<String> secondDatas = new ArrayList<>();
                List<List<String>> thirdDatas = new ArrayList<>();

                for (TestBean testBean : data) {
                    datas.add(testBean.getList());
                    secondDatas.add(testBean.getName());
                    List<String> namea = new ArrayList<>();
                    for (TestBean b : testBean.getList()) {
                        String name1 = b.getName();
                        namea.add(name1);
                    }
                    thirdDatas.add(namea);
                }
                initStoreTypePicker(datas, secondDatas, thirdDatas);
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

    /**
     * 初始化商铺类型选择器
     *
     * @param datas       数据源
     * @param secondTypes 二级分类集合
     * @param thirdTypes  三级分类集合
     */
    private void initStoreTypePicker(final List<List<TestBean>> datas, List<String> secondTypes, List<List<String>> thirdTypes) {
        storeTypePicker = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mTvStoreType.setText(datas.get(options1).get(options2).getName());
                mStoreType = datas.get(options1).get(options2).getId();
            }
        }).setSubmitText(getString(R.string.app_confirm)).
                setCancelText(getString(R.string.app_cancel)).
                setSubmitColor(getResources().getColor(R.color.base_color)).
                setCancelColor(getResources().getColor(R.color.normal_text_color)).
                build();
        storeTypePicker.setPicker(secondTypes, thirdTypes);
    }

    /**
     * 初始化提拔比例选择器
     */
    private void initProfitsPickers() {
        mProfitsList.add("5%");
        mProfitsList.add("10%");
        mProfitsList.add("15%");
        mProfitsList.add("20%");
        mProfitsList.add("25%");
        mProfitsList.add("30%");
        mProfitsList.add("35%");
        mProfitsList.add("40%");
        profitsPickers = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mTvProfits.setText(mProfitsList.get(options1));
                mProportion = mProfitsList.get(options1);
            }
        }).setSubmitText(getString(R.string.app_confirm)).
                setCancelText(getString(R.string.app_cancel)).
                setSubmitColor(getResources().getColor(R.color.base_color)).
                setCancelColor(getResources().getColor(R.color.normal_text_color)).
                build();
        profitsPickers.setPicker(mProfitsList);
    }

    /**
     * 初始化证件类型选择器
     */
    private void initCredentialsTypePickers() {
        credentialsTypeList.add("护照");
        credentialsTypeList.add("法人身份证");
        credentialsTypeList.add("港澳台证件");
        // 选择证件类型
        credentialsTypePickers = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mTvCredentialsType.setText(credentialsTypeList.get(options1));
                mCertificates_type = credentialsTypeList.get(options1);

            }
        }).setSubmitText(getString(R.string.app_confirm)).
                setCancelText(getString(R.string.app_cancel)).
                setSubmitColor(getResources().getColor(R.color.base_color)).
                setCancelColor(getResources().getColor(R.color.normal_text_color)).
                build();
        credentialsTypePickers.setPicker(credentialsTypeList);
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
        } else if (view.getId() == mLlStoreType.getId() || view.getId() == mTvStoreType.getId()) {
//            if (null == storeTypePicker){
//
//            }
            storeTypePicker.show();
        } else if (view.getId() == mTvProfits.getId()) {
            profitsPickers.show();
        } else if (view.getId() == mTvStoreInCity.getId() || view.getId() == mLlStoreInCity.getId()) {
            BottomPickerUtils.showCityPicker(context, new BottomPickerUtils.CityOptionPickerCallback() {
                @Override
                public void onOptionSelect(CityBean province, CityBean city, CityBean area) {
                    provinceStr = province.getArea();
                    cityStr = city.getArea();
                    areaStr = area.getArea();
                    mTvStoreInCity.setText(province.getArea() + "" + city.getArea() + "" + area.getArea());
//                    mLng = area.getLng();
//                    mLat = area.getLat();
                }

            });
        } else if (view.getId() == mTvCredentialsType.getId()) {
            credentialsTypePickers.show();
        } else if (view.getId() == mXieyi.getId()) {
            Intent urlIntent = new Intent(context, BaseUrlActivity.class);
            urlIntent.putExtra(BaseUrlActivity.MAIN_URL, "http://xmap1802009.php.hzxmnet.com/Home/Index/article/type/10.html");
            startActivity(urlIntent);
        } else if (view.getId() == mLlCredentialsType.getId()) {
            credentialsTypePickers.show();
        } else if (view.getId() == mCdBtnSendMsg.getId()) {
            checkInputInfo(mCdBtnSendMsg);
        } else if (view.getId() == mNext.getId()) {
            // 提交审核
            checkInputInfo(mNext);
        } else if (view.getId() == tv_phone_verify_new_add.getId()){
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
        SmsMethods.getInstance().sendVoiceVerifyCode(subscriber,getEditTextStr(mEdtManagerPhoneNo),1, paramaList);
        rxManager.add(subscriber);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void newAdd(BaseEvent event) {
        switch (event.getTag()) {
            case EventBusConstants.TIME_OUT:
                tv_phone_verify_new_add.setVisibility(View.VISIBLE);
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
     * 校验输入信息的合法性
     */
    private void checkInputInfo(View view) {
        if (EmptyUtils.isEmpty(getEditTextStr(mEdtAccountSetting))) {
            showToastMsg("账号不能为空");
            return;
        } else if (getEditTextStr(mEdtAccountSetting).length() < 8) {
            showToastMsg("账号格式不正确");
            return;
        } else if (EmptyUtils.isEmpty(getEditTextStr(mEdtPwdSetting))) {
            showToastMsg("登陆密码不能为空");
            return;
        } else if (getEditTextStr(mEdtPwdSetting).length() < 8) {
            showToastMsg("登陆密码格式不正确");
            return;
        } else if (EmptyUtils.isEmpty(getEditTextStr(mEdtPwdConfirm))) {
            showToastMsg("确认密码不能为空");
            return;
        } else if (getEditTextStr(mEdtPwdConfirm).length() < 8) {
            showToastMsg("确认密码格式不正确");
            return;
        } else if (!(getEditTextStr(mEdtPwdSetting).equals(getEditTextStr(mEdtPwdConfirm)))) {
            showToastMsg("登陆密码两次输入不一致，请重新输入");
            return;
        } else if (EmptyUtils.isEmpty(getEditTextStr(mEdtStoreNmae))) {
            showToastMsg("商家名称不能为空");
            return;
        } else if (getEditTextStr(mEdtStoreNmae).length() < 2) {
            showToastMsg("商家名称格式不正确");
            return;
        } else if (EmptyUtils.isEmpty(getEditTextStr(mEdtStorePhoneNo))) {
            showToastMsg("商家营业电话不能为空");
            return;
        } else if (getEditTextStr(mEdtStorePhoneNo).length() < 8) {
            showToastMsg("商家营业电话格式不正确");
            return;
        } else if (EmptyUtils.isEmpty(getTextViewStr(mTvStoreType))) {
            showToastMsg("商户类型不能为空");
            return;
        } else if (EmptyUtils.isEmpty(getTextViewStr(mTvProfits))) {
            showToastMsg("请选择提拔比例");
            return;
        } else if (EmptyUtils.isEmpty(getTextViewStr(mTvStoreInCity))) {
            showToastMsg("请选择商铺所在省、市");
            return;
        } else if (EmptyUtils.isEmpty(getEditTextStr(mEdtAddresDetail))) {
            showToastMsg("商铺详细地址不能为空");
            return;
        } else if (getEditTextStr(mEdtAddresDetail).length() < 4) {
            showToastMsg("商铺详细地址格式不正确");
            return;
        } else if (EmptyUtils.isEmpty(getEditTextStr(mEdtInviteCode))) {
            showToastMsg("请输入邀请码");
            return;
        } else if (getEditTextStr(mEdtInviteCode).length() < 6) {
            showToastMsg("邀请码格式不正确");
            return;
        } else if (EmptyUtils.isEmpty(getEditTextStr(mEdtLegalName))) {
            showToastMsg("法人姓名不能为空");
            return;
        } else if (getEditTextStr(mEdtLegalName).length() < 2) {
            showToastMsg("法人姓名格式不正确");
            return;
        } else if (EmptyUtils.isEmpty(getTextViewStr(mTvCredentialsType))) {
            showToastMsg("请选择法人证件类型");
            return;
        } else if (EmptyUtils.isEmpty(getEditTextStr(mEdtCredentialsNo))) {
            showToastMsg("法人证件号不能为空");
            return;
        } else if (EmptyUtils.isEmpty(ID_front_img)) {
            showToastMsg("请上传身份证正面照");
            return;
        } else if (EmptyUtils.isEmpty(ID_back_img)) {
            showToastMsg("请上传身份证反面照");
            return;
        } else if (EmptyUtils.isEmpty(license_img)) {
            showToastMsg("请上传营业执照");
            return;
        } else if (getEditTextStr(mEdtCredentialsNo).length() < 6) {
            showToastMsg("法人证件号格式不正确");
            return;
        } else if (EmptyUtils.isEmpty(getEditTextStr(mEdtManagerPhoneNo))) {
            showToastMsg("法人授权代理人手机号不能为空");
            return;
        } else if (getEditTextStr(mEdtManagerPhoneNo).length() < 11) {
            showToastMsg("法人授权代理人手机号格式不正确");
            return;
        }
    /* 如果当前按钮发送验证码，则不需要走后面的判断。如果当前点击的按钮为提交按钮，则需要判断前面所有的逻辑都需要判断，包括后面的逻辑判断。 */
        if (view.getId() == mCdBtnSendMsg.getId()) {
            mCdBtnSendMsg.getInputContent(getEditTextStr(mEdtManagerPhoneNo));
            sendVerifyCodeMsg();
        } else if (view.getId() == mNext.getId()) {
            if (EmptyUtils.isEmpty(getEditTextStr(mEdtInputVerCode))) {
                showToastMsg("验证码不能为空");
                return;
            } else if (getEditTextStr(mEdtInputVerCode).length() < 6) {
                showToastMsg("验证码格式不对，请重新输入");
                return;
            } else if (!mCheckBox.isChecked()) {
                showToastMsg("请先勾选平台协议");
                return;
            }
            commitInputInfo();
        }

//        else if (EmptyUtils.isEmpty(getEditTextStr(mEdtInputManagerName))) {
//            showToastMsg("法人授权代理人姓名不能为空");
//            return;
//        } else if (getEditTextStr(mEdtInputManagerName).length() < 2) {
//            showToastMsg("法人授权代理人姓名格式不对");
//            return;
//        }
    }


    /**
     * 发送验证码
     */
    private void sendVerifyCodeMsg() {
        checkUserNameIsUnique(getEditTextStr(mEdtAccountSetting));
    }

    /**
     * 检测商户账户是否是唯一的
     */
    private void checkUserNameIsUnique(String accountNmaeInput) {
        showProgressingDialog();
        CommonSubscriber<HttpRespBean> userNmaeSub = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                sendVerifyCodeRequest();
            }

            @Override
            public void onError(String e, int code) {
                dismissProgressDialog();
                showToastMsg(e);
            }
        });
        BusinessUserMethods.getInstance().checkUsrName(userNmaeSub, accountNmaeInput);
        rxManager.add(userNmaeSub);

    }


    /**
     * 发送手机验证码的请求
     */
    private void sendVerifyCodeRequest() {
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
        SmsMethods.getInstance().sendVerCode(sendVerCodeSub, getEditTextStr(mEdtManagerPhoneNo), "1");
        rxManager.add(sendVerCodeSub);
    }


    /**
     * 输入信息提交审核
     */
    private void commitInputInfo() {
        newMerchantApplyJoinReuqest(getEditTextStr(mEdtAccountSetting), getEditTextStr(mEdtPwdSetting),
                getEditTextStr(mEdtStoreNmae), getEditTextStr(mEdtStorePhoneNo),
                mStoreType, mProportion,
                provinceStr, cityStr,
                areaStr, getEditTextStr(mEdtAddresDetail),
                getEditTextStr(mEdtInviteCode), getEditTextStr(mEdtLegalName),
                mCertificates_type, getEditTextStr(mEdtCredentialsNo),
                ID_front_img, ID_back_img,
//                license_img, getEditTextStr(mEdtInputManagerName),
                license_img,
                getEditTextStr(mEdtManagerPhoneNo), getEditTextStr(mEdtInputVerCode));
    }

//    原请求接口
//    private void goToHttpReqss(String editTextStr, String textStr, String str, String s, String category_id,
//                               String proportion, String sheng, String shi, String qu, String editTextStr1,
//                               String textStr1, String str1, String s1, String certificates_type, String editTextStr2,
//                               String textStr2, String ID_front_img, String id_front_img, String license_img, String t, String t1) {


    private void newMerchantApplyJoinReuqest(String storeAccount, String pwd,
                                             String storeName, String storePhoneNo,
                                             String storeType, String proportion,
                                             String provice, String city,
                                             String area, String addressDetail,
                                             String inviteCode, String legalName,
                                             String certificatesType, String certificatesNo,
                                             String imgFront, String imgBack,
//                                             String imgLicense, String managerName,
                                             String imgLicense,
                                             String managerPhoneNo, String verCode) {
        showProgressingDialog();
        CommonSubscriber<Object> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                dismissProgressDialog();
                new JoinSucceedDialog(context,NewZCActivity.this).show();
            }

            @Override
            public void onError(String e, int code) {
                dismissProgressDialog();
                showToastMsg(e);
                Log.i(TAG, "onError:    e:  " + e);
                Log.i(TAG, "onError:    code:   " + code);


            }
        });
        // 新会员入驻审核接口
//        BusinessUserMethods.getInstance().apply(subscriber, editTextStr, textStr, str, s, category_id, proportion, sheng
//                , shi, qu, editTextStr1, textStr1, str1, s1, certificates_type, editTextStr2, textStr2, ID_front_img, id_front_img, license_img);

        BusinessUserMethods.getInstance().newMerhcantApplyJoin(subscriber,
                storeAccount, pwd,
                storeName, storePhoneNo,
                storeType, proportion,
                provice, city,
                area, addressDetail,
                inviteCode, legalName,
                certificatesType, certificatesNo,
                imgFront, imgBack,
//                imgLicense, managerName,
                imgLicense,
                managerPhoneNo, verCode);

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
                        ID_front_img = s.getData();
                        ImageLoaderUtils.displaySmallPhoto(mIvCredentialFront, ID_front_img);
                    } else if (type == 2) {
                        ID_back_img = s.getData();
                        ImageLoaderUtils.displaySmallPhoto(mIvCredentialBack, ID_back_img);
                    } else {
                        license_img = s.getData();
                        ImageLoaderUtils.displaySmallPhoto(mIvBusinessLicense, license_img);
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
}
