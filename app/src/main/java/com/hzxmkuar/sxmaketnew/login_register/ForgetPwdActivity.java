package com.hzxmkuar.sxmaketnew.login_register;

import android.content.Intent;
import android.media.tv.TvTrackInfo;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.common.event.BaseEvent;
import com.common.event.EventBusConstants;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.IndexBean;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.methods.SmsMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.EmptyUtils;
import com.common.widget.dialog.SendPhoneVerifyDialog;
import com.common.widget.editview.DeleteEditText;
import com.common.widget.textview.CountdownButton;
import com.hzxmkuar.sxmaketnew.R;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 忘记密码——找回密码第一步：输入相关信息
 * Created by Administrator on 2018/8/22.
 */
public class ForgetPwdActivity extends BaseMvpActivity {
    private static final String TAG = "ForgetPwdActivity";
    private DeleteEditText mEdtInputAccount;
    /**
     * 商铺名称
     */
    private DeleteEditText mEdtInputStroeName;
    private DeleteEditText mEdtInputLegalName;
    /**
     * 证件类型
     */
    private TextView mTvChoseCertificateType;
    private DeleteEditText mEdtInputLegalIdNo;
    private DeleteEditText mEdtInputPhoneNo;
    /**
     * 手机验证码
     */
    private DeleteEditText mEdtInputVerificationCode;
    private CountdownButton mBtnSendMsg;
    private Button mBtnCommit;
    private ImageView iv_back;
    private OptionsPickerView mCertificatesTypePicker;
    private String mCertificatesType = "";
    private List<String> typeList = new ArrayList<>();
    private TextView tv_phone_verify_forget;
    private SendPhoneVerifyDialog sendPhoneVerifyDialog;
    private boolean canClickAble = true;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    protected void onViewCreated() {
        registerEvent();
        mEdtInputAccount = (DeleteEditText) findViewById(R.id.edt_input_account);
        mEdtInputStroeName = (DeleteEditText) findViewById(R.id.edt_input_stroe_name);
        mEdtInputLegalName = (DeleteEditText) findViewById(R.id.edt_input_legal_name);
        mTvChoseCertificateType = (TextView) findViewById(R.id.tv_chose_certificate_type);
        mEdtInputLegalIdNo = (DeleteEditText) findViewById(R.id.edt_input_legal_id_no);
        mEdtInputPhoneNo = (DeleteEditText) findViewById(R.id.edt_input_phone_no);
        mEdtInputVerificationCode = (DeleteEditText) findViewById(R.id.edt_input_verification_code);
        mBtnSendMsg = (CountdownButton) findViewById(R.id.btn_send_msg);
        mBtnCommit = (Button) findViewById(R.id.btn_commit);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_phone_verify_forget = (TextView) findViewById(R.id.tv_phone_verify_forget);
        tv_phone_verify_forget.setVisibility(View.INVISIBLE);
        TextView tvTtile = (TextView) findViewById(R.id.tv_title);
        tvTtile.setText("忘记密码");

        sendPhoneVerifyDialog = new SendPhoneVerifyDialog(context,ForgetPwdActivity.this);
        sendPhoneVerifyDialog.setOnDialogButtonClickListener(new SendPhoneVerifyDialog.OnDialogButtonClickListener() {
            @Override
            public void cancelLick() {
                canClickAble = true;

            }

            @Override
            public void confirmClick() {
                canClickAble = false;
                mBtnSendMsg.restart();
                if (!EmptyUtils.isEmpty(getEditTextStr(mEdtInputPhoneNo))){
//                    Log.i(TAG, "sendVoiceVerifyCodeReq:   手机号码 ：         "+getEditTextStr(mEdtInputPhoneNo));
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
        attachClickListener(mBtnSendMsg);
        attachClickListener(mBtnCommit);
        attachClickListener(mTvChoseCertificateType);
        attachClickListener(iv_back);
        attachClickListener(tv_phone_verify_forget);
        initCertificateTypePicker();
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == mBtnSendMsg.getId()) {
            verifyInput(mBtnSendMsg);
        } else if (view.getId() == mBtnCommit.getId()) {
            verifyInput(mBtnCommit);
        } else if (view.getId() == iv_back.getId()) {
            finish();
        } else if (view.getId() == mTvChoseCertificateType.getId()) {
            mCertificatesTypePicker.show();
        } else if (view.getId() == tv_phone_verify_forget.getId()) {
            if (canClickAble){
                sendPhoneVerifyDialog.show();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void forgetPwdTimeOut(BaseEvent event) {
        switch (event.getTag()) {
            case EventBusConstants.TIME_OUT:
                tv_phone_verify_forget.setVisibility(View.VISIBLE);
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
        if (EmptyUtils.isEmpty(getEditTextStr(mEdtInputAccount))) {
            showToastMsg("账号不能为空，请输入您的商户号");
            return;
        } else if (EmptyUtils.isEmpty(getEditTextStr(mEdtInputStroeName))) {
            showToastMsg("商铺名称不能为空，请输入您的商铺名称");
            return;
        } else if (EmptyUtils.isEmpty(getEditTextStr(mEdtInputLegalName))) {
            showToastMsg("法人姓名不能为空，请输入您的法人姓名");
            return;
        } else if (getEditTextStr(mEdtInputLegalName).length() <= 1) {
            showToastMsg("法人姓名长度必须大于2");
            return;
        } else if (EmptyUtils.isEmpty(getTextViewStr(mTvChoseCertificateType))) {
            showToastMsg("证件类型不能为空，请输入您的证件类型");
            return;
        } else if (EmptyUtils.isEmpty(getEditTextStr(mEdtInputLegalIdNo))) {
            showToastMsg("证件号码不能为空");
            return;
        } else if (getEditTextStr(mEdtInputLegalIdNo).length() < 6) {
            showToastMsg("证件号码格式不正确，请重新输入");
            return;
        } else if (getTextViewStr(mTvChoseCertificateType).length() < 2) {
            showToastMsg("证件号码格式不正确，请重新输入");
            return;
        } else if (EmptyUtils.isEmpty(getEditTextStr(mEdtInputPhoneNo))) {
            showToastMsg("手机号码不能为空");
            return;
        } else if (getEditTextStr(mEdtInputPhoneNo).length() < 11) {
            showToastMsg("手机号码格式不对，请重新输入");
            return;
        }
        /* 如果当前按钮发送验证码，则不需要走后面的判断。如果当前点击的按钮为提交按钮，则需要判断前面所有的逻辑都需要判断，包括后面的逻辑判断。 */
        if (view.getId() == mBtnSendMsg.getId()) {
            sendVerifyCodeMsg();
        } else if (view.getId() == mBtnCommit.getId()) {
            if (EmptyUtils.isEmpty(getEditTextStr(mEdtInputVerificationCode))) {
                showToastMsg("验证码不能为空");
                return;
            } else if (getEditTextStr(mEdtInputVerificationCode).length() < 6) {
                showToastMsg("验证码格式不对，请重新输入");
                return;
            }
            commitInputInfo(getEditTextStr(mEdtInputAccount),getEditTextStr(mEdtInputStroeName),
                    getEditTextStr(mEdtInputLegalName),getTextViewStr(mTvChoseCertificateType),
                    getEditTextStr(mEdtInputLegalIdNo),getEditTextStr(mEdtInputPhoneNo),
                    getEditTextStr(mEdtInputVerificationCode));
        }
    }

    /**
     * 提交商户输入内容、请求找回密码
     */
    private void commitInputInfo(String userName, String storeName,
                                 String legalNmae, String certificateType,
                                 String legalIdNo, String phoneNo, String inputVerCode) {

        CommonSubscriber<IndexBean> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                statusContent();
                Intent resetPwdIntent = new Intent(ForgetPwdActivity.this,NewPwdActivity.class);
                resetPwdIntent.putExtra("userName",getEditTextStr(mEdtInputAccount));
                startActivity(resetPwdIntent);
                finish();
            }

            @Override
            public void onError(String e, int code) {
                statusContent();
                showToastMsg(e);
            }
        });
        BusinessUserMethods.getInstance().forgetPwd(subscriber,userName,storeName,
                legalNmae,certificateType,
                legalIdNo,phoneNo,inputVerCode);
        rxManager.add(subscriber);

    }

    /**
     * 发送验证码
     */
    private void sendVerifyCodeMsg() {
        sendVerifyCodeRequest();
        mBtnSendMsg.getInputContent(getEditTextStr(mEdtInputPhoneNo));
    }

    /**
     * 初始化证件类型拾取器
     */
    private void initCertificateTypePicker() {
        typeList.add("护照");
        typeList.add("法人身份证");
        typeList.add("港澳台证件");
        mCertificatesTypePicker = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                mTvChoseCertificateType.setText(typeList.get(options1));
                mCertificatesType = typeList.get(options1);
            }
        }).setSubmitText(getString(R.string.app_confirm)).
                setCancelText(getString(R.string.app_cancel)).
                setSubmitColor(getResources().getColor(R.color.base_color)).
                setCancelColor(getResources().getColor(R.color.normal_text_color)).
                build();
        mCertificatesTypePicker.setPicker(typeList);
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
        SmsMethods.getInstance().sendVerCode(sendVerCodeSub,getEditTextStr(mEdtInputPhoneNo),"2");
        rxManager.add(sendVerCodeSub);
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
        SmsMethods.getInstance().sendVoiceVerifyCode(subscriber,getEditTextStr(mEdtInputPhoneNo),2, paramaList);
        rxManager.add(subscriber);
    }

}
