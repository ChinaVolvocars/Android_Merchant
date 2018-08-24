package com.hzxmkuar.sxmaketnew.common;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.utils.EmptyUtils;
import com.common.utils.RegexUtils;
import com.common.widget.editview.DeleteEditText;
import com.common.widget.textview.CountdownButton;
import com.hzxmkuar.sxmaketnew.R;

public class ForgetPasActivity extends BaseMvpActivity
{
    public static final String TYPE = "TYPE";

    private DeleteEditText evPhone;
    private DeleteEditText evCode;
    private CountdownButton tvVerify;
    private TextView tvLogin;

    private Button btnRegister;

    private int type;     // 1 注册  2 找回密码


    @Override
    protected void setNavigation() {
        type = getIntent().getIntExtra(TYPE, 1);
        String title = type == 1 ? "注册" : "忘记密码";
        setNavigationBack(title);
    }

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_pas;
    }

    @Override
    protected void onViewCreated() {
        evPhone = (DeleteEditText) findViewById(R.id.ev_phone);
        evCode = (DeleteEditText) findViewById(R.id.ev_code);
        tvVerify = (CountdownButton) findViewById(R.id.tv_verify);
        btnRegister = (Button) findViewById(R.id.btn_next);
        tvLogin = (TextView) findViewById(R.id.tv_login);
    }

    @Override
    protected void doLogicFunc() {
        /*tvLogin.setText(SpannableStringUtils.getBuilder("已有账号？")
                .append("立即登录").setForegroundColor(ResourceUtils.getColor(context, R.color.c_FE6F4E)).create());*/
        //setVerifyView();
        attachClickListener(btnRegister);
        attachClickListener(tvLogin);
    }

   /* private void setVerifyView() {
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
                presenter.getSmsCode(getEditTextStr(evPhone), type);
            }
        });

        evPhone.addTextChangedListener(textWatcher);
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
            if (type == 2) {
                if (EmptyUtils.isEmpty(getEditTextStr(evCode))) {
                    showToastMsg("请输入验证码");
                    return;
                }
                /*Intent intent = new Intent(context, SetPasActivity.class);
                startActivity(intent);
                finish();*/
            } else {
                //presenter.checkSmsCode(getEditTextStr(evPhone), type, getEditTextStr(evCode));
            }
        } else if (view.getId() == tvLogin.getId()) {
            onBackPressed();
        }
    }

}