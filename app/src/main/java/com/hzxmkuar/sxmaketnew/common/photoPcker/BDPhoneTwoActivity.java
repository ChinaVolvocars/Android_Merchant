package com.hzxmkuar.sxmaketnew.common.photoPcker;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.listener.OnOnceClickListener;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.utils.EmptyUtils;
import com.common.utils.RegexUtils;
import com.common.widget.editview.DeleteEditText;
import com.common.widget.textview.CountdownButton;
import com.hzxmkuar.sxmaketnew.R;

public class BDPhoneTwoActivity extends BaseMvpActivity
{
    public static final String TYPE = "TYPE";

    private DeleteEditText evPhone;
    private DeleteEditText evCode;
    private CountdownButton tvVerify;

    private Button btnRegister;
    private LinearLayout mLl;
    private ImageView mOne_i;
    private TextView mOne_t;
    private ImageView mTwo_i;
    private TextView mTwo_t;
    private int type = 1;


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
        mLl = (LinearLayout) findViewById(R.id.ll);
        mLl.setVisibility(View.VISIBLE);
        mOne_i = (ImageView) findViewById(R.id.one_i);
        mOne_t = (TextView) findViewById(R.id.one_t);
        mTwo_i = (ImageView) findViewById(R.id.two_i);
        mTwo_t = (TextView) findViewById(R.id.two_t);
        mOne_i.setImageResource(R.color.base_color);
        mTwo_i.setImageResource(R.color.normal_text_color);
        mOne_t.setTextColor(getResources().getColor(R.color.base_color));
        mTwo_t.setTextColor(getResources().getColor(R.color.normal_text_color));
    }

    @Override
    protected void setNavigation() {
        setNavigationBack("绑定手机号");
    }

    @Override
    protected void doLogicFunc() {
        setVerifyView();
        attachClickListener(btnRegister);
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
            if (type==1){
                mTwo_i.setImageResource(R.color.base_color);
                mOne_i.setImageResource(R.color.normal_text_color);
                mTwo_t.setTextColor(getResources().getColor(R.color.base_color));
                mOne_t.setTextColor(getResources().getColor(R.color.normal_text_color));
                type = 2;
                evPhone.setHint("请输入手机号");
                evCode.setHint("请输入验证码");
            }else {
                finish();
            }
            //gotoHttpReqs(1,getIntent().getIntExtra("id",0),getEditTextStr(evPhone),getEditTextStr(evCode), MainApplication.mRegistrationId);
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