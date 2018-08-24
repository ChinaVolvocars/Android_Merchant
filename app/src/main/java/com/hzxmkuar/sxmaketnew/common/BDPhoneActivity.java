package com.hzxmkuar.sxmaketnew.common;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.common.listener.OnOnceClickListener;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.utils.EmptyUtils;
import com.common.utils.RegexUtils;
import com.common.widget.editview.DeleteEditText;
import com.common.widget.textview.CountdownButton;
import com.hzxmkuar.sxmaketnew.MainActivity;
import com.hzxmkuar.sxmaketnew.R;

public class BDPhoneActivity extends BaseMvpActivity
{
    public static final String TYPE = "TYPE";

    private DeleteEditText evPhone;
    private DeleteEditText evCode;
    private CountdownButton tvVerify;

    private Button btnRegister;
    private ImageView mBack;


    @Override
    protected void setStatusBar() {
    }

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bd_phone;
    }

    @Override
    protected void onViewCreated() {
        evPhone = (DeleteEditText) findViewById(R.id.ev_phone);
        evCode = (DeleteEditText) findViewById(R.id.ev_code);
        tvVerify = (CountdownButton) findViewById(R.id.tv_verify);
        btnRegister = (Button) findViewById(R.id.btn_next);
        mBack = (ImageView) findViewById(R.id.back);
    }



    @Override
    protected void doLogicFunc() {
        setVerifyView();
        attachClickListener(btnRegister);
        attachClickListener(mBack);
    }
    private void setVerifyView() {
        tvVerify.setEnabled(false);
        tvVerify.setOnClickListener(new OnOnceClickListener() {
            @Override
            public void onOnceClick(View v) {
                if (EmptyUtils.isEmpty(getEditTextStr(evPhone))) {
                    showToastMsg("请输入手机号");
                    return;
                } else if (!RegexUtils.isMobileExact(getEditTextStr(evPhone))) {
                    showToastMsg("手机号不正确");
                    return;
                }
                //gotoHttps(getEditTextStr(evPhone), 5);
            }
        });
        evPhone.addTextChangedListener(textWatcher);
    }

    /*private void gotoHttps( String editTextStr1, int i) {
        CommonSubscriber<Object> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
            }

            @Override
            public void onError(String e, int code) {
                showToastMsg(e);
            }
        });

        SysMethods.getInstance().sendcode(subscriber, editTextStr1 ,i);
        rxManager.add(subscriber);
    }*/

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (EmptyUtils.isNotEmpty(getEditTextStr(evPhone)) && RegexUtils.isMobileExact(getEditTextStr(evPhone))) {
                tvVerify.setEnabled(true);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    };

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == btnRegister.getId()) {
            /*if (EmptyUtils.isEmpty(getEditTextStr(evCode))) {
                showToastMsg("请输入验证码");
                return;
            }*/
            gotoActivity(MainActivity.class);
            //gotoHttpReqs(1,getIntent().getIntExtra("id",0),getEditTextStr(evPhone),getEditTextStr(evCode), MainApplication.mRegistrationId);
        }else if (view.getId()==mBack.getId()){
            finish();
        }
    }

    /*private void gotoHttpReqs(int i, final int id, final String phone, final String code, String mRegistrationId) {
        if (EmptyUtils.isEmpty(code)) {
            showToastMsg("请输入验证码");
            return;
        }
        showProgressingDialog();
        CommonSubscriber<UserBean> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                UserBean userBean = (UserBean) o;
                if (userBean.getUid()>0){
                    DataCenter.saveLoginDataInfo(userBean);
                    gotoActivity(MainActivity.class);
                    finish();
                }else {
                    startActivity(new Intent(context, RetrieveActivity.class).putExtra("phone",phone).putExtra("code",code).putExtra("type","3")
                    .putExtra("id",id));
                }
            }
            @Override
            public void onError(String e, int code) {
                dismissProgressDialog();
                showToastMsg(e);
            }
        });
        UserMethods.getInstance().thirdLoginCheck(subscriber, i,id,phone, code, mRegistrationId);
        rxManager.add(subscriber);
    }*/


}