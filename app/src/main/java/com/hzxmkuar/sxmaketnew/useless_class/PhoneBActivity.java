//package com.hzxmkuar.sxmaketnew.common;
//
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.common.listener.OnOnceClickListener;
//import com.common.mvp.BaseMvpActivity;
//import com.common.mvp.BasePresenter;
//import com.common.utils.EmptyUtils;
//import com.common.utils.RegexUtils;
//import com.common.widget.editview.DeleteEditText;
//import com.common.widget.textview.CountdownButton;
//import com.hzxmkuar.sxmaketnew.R;
//
//public class PhoneBActivity extends BaseMvpActivity {
//
//    private DeleteEditText evPhone;
//    private DeleteEditText evCode;
//    private CountdownButton tvVerify;
//
//    private Button btnRegister;
//    private ImageView mBack;
//    private LinearLayout mLl;
//    private ImageView mOne_i;
//    private ImageView mTwo_i;
//    private TextView mOne_t;
//    private TextView mTwo_t;
//    private int type = 1;
//
//
//    @Override
//    protected void setStatusBar() {
//    }
//
//    @Override
//    protected BasePresenter createPresenterInstance() {
//        return null;
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_bd_phone;
//    }
//
//    @Override
//    protected void onViewCreated() {
//        evPhone = (DeleteEditText) findViewById(R.id.ev_phone);
//        evCode = (DeleteEditText) findViewById(R.id.ev_code);
//        tvVerify = (CountdownButton) findViewById(R.id.tv_verify);
//        btnRegister = (Button) findViewById(R.id.btn_next);
//        mBack = (ImageView) findViewById(R.id.back);
//        mLl = (LinearLayout) findViewById(R.id.ll);
//        mLl.setVisibility(View.VISIBLE);
//        mOne_i = (ImageView) findViewById(R.id.one_i);
//        mTwo_i = (ImageView) findViewById(R.id.two_i);
//        mOne_t = (TextView) findViewById(R.id.one_t);
//        mTwo_t = (TextView) findViewById(R.id.two_t);
//
//        mOne_i.setImageResource(R.color.base_color);
//        mTwo_i.setImageResource(R.color.normal_text_color);
//        mOne_t.setTextColor(getResources().getColor(R.color.base_color));
//        mTwo_t.setTextColor(getResources().getColor(R.color.normal_text_color));
//    }
//
//    @Override
//    protected void doLogicFunc() {
//        setVerifyView();
//        attachClickListener(btnRegister);
//        attachClickListener(mBack);
//    }
//
//    private void setVerifyView() {
//        tvVerify.setEnabled(true);
//        tvVerify.setOnClickListener(new OnOnceClickListener() {
//            @Override
//            public void onOnceClick(View v) {
//                if (EmptyUtils.isEmpty(getEditTextStr(evPhone))) {
//                    showToastMsg("请输入手机号");
//                    return;
//                } else if (!RegexUtils.isMobileExact(getEditTextStr(evPhone))) {
//                    showToastMsg("手机号不正确");
//                    return;
//                }
//            }
//        });
//
//        evPhone.addTextChangedListener(textWatcher);
//    }
//
//    private TextWatcher textWatcher = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//        }
//
//        @Override
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            if (EmptyUtils.isNotEmpty(getEditTextStr(evPhone)) && RegexUtils.isMobileExact(getEditTextStr(evPhone))) {
//                tvVerify.setEnabled(true);
//            }
//        }
//
//        @Override
//        public void afterTextChanged(Editable editable) {
//        }
//    };
//
//    @Override
//    protected void onViewClicked(View view) {
//        if (view.getId() == btnRegister.getId()) {
//            /*if (EmptyUtils.isEmpty(getEditTextStr(evCode))) {
//                showToastMsg("请输入验证码");
//                return;
//            }*/
//            if (type == 1) {
//                mTwo_i.setImageResource(R.color.base_color);
//                mOne_i.setImageResource(R.color.normal_text_color);
//                mTwo_t.setTextColor(getResources().getColor(R.color.base_color));
//                mOne_t.setTextColor(getResources().getColor(R.color.normal_text_color));
//                type = 2;
//            } else {
//                finish();
//            }
//
//        } else if (view.getId() == mBack.getId()) {
//            finish();
//        }
//    }
//
//    /*private void gotoHttpReq(final String phone, String checkType, String code) {
//        if (EmptyUtils.isEmpty(code)) {
//           showToastMsg("请输入验证码");
//            return;
//        }
//        showProgressingDialog();
//        CommonSubscriber<Object> subscriber = new CommonSubscriber<>(new SubscriberListener() {
//            @Override
//            public void onNext(Object o) {
//                if (type ==1){
//                    Intent intent = new Intent(PhoneBActivity.this,SetPasActivity.class);
//                    intent.putExtra("TYPE",type);
//                    intent.putExtra("PHONE",phone);
//                    startActivity(intent);
//                    finish();
//                }else {
//                    Intent intent = new Intent(PhoneBActivity.this,RetrievesActivity.class);
//                    intent.putExtra("TYPE",type);
//                    intent.putExtra("PHONE",phone);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//            @Override
//            public void onError(String e, int code) {
//                dismissProgressDialog();
//                showToastMsg(e);
//            }
//        });
//        SysMethods.getInstance().checkMobile(subscriber, phone, checkType , code,0);
//        rxManager.add(subscriber);
//    }*/
//}