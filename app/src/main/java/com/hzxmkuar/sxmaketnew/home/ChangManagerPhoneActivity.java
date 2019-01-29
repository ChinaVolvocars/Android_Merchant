package com.hzxmkuar.sxmaketnew.home;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.event.BaseEvent;
import com.common.event.EventBusConstants;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.methods.SmsMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.EmptyUtils;
import com.common.widget.editview.DeleteEditText;
import com.common.widget.textview.CountdownButton;
import com.hzxmkuar.sxmaketnew.R;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 更换绑定手机号码
 * Created by little_bug on 2018/9/16.
 */

public class ChangManagerPhoneActivity extends BaseMvpActivity {
    private DeleteEditText edt_original_phone;
    private DeleteEditText edt_orignal_verify_code;
    private CountdownButton cd_original_phone_verify_code;
    private DeleteEditText edt_new_phone;
    private DeleteEditText edt_new_phone_verify_code;
    private CountdownButton cd_new_phone_send_verifyt_code;
    private Button btn_confirm_input;
    private TextView tv_phone_verify_old_phone_no;
    private TextView tv_phone_verify_new_phone_no;
    private TextView tv_title;
    private ImageView iv_back;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_manager_phone;
    }

    @Override
    protected void onViewCreated() {
        edt_original_phone = (DeleteEditText) findViewById(R.id.edt_original_phone);
        edt_orignal_verify_code = (DeleteEditText) findViewById(R.id.edt_orignal_verify_code);
        cd_original_phone_verify_code = (CountdownButton) findViewById(R.id.cd_original_phone_verify_code);
        edt_new_phone = (DeleteEditText) findViewById(R.id.edt_new_phone);
        edt_new_phone_verify_code = (DeleteEditText) findViewById(R.id.edt_new_phone_verify_code);
        cd_new_phone_send_verifyt_code = (CountdownButton) findViewById(R.id.cd_new_phone_send_verifyt_code);
        btn_confirm_input = (Button) findViewById(R.id.btn_confirm_input);
        tv_phone_verify_old_phone_no = (TextView) findViewById(R.id.tv_phone_verify_old_phone_no);
        tv_phone_verify_new_phone_no = (TextView) findViewById(R.id.tv_phone_verify_new_phone_no);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("更换绑定手机号码");
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_phone_verify_old_phone_no.setVisibility(View.GONE);
        tv_phone_verify_new_phone_no.setVisibility(View.GONE);

    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(cd_original_phone_verify_code);
        attachClickListener(cd_new_phone_send_verifyt_code);
        attachClickListener(btn_confirm_input);
        attachClickListener(tv_phone_verify_old_phone_no);
        attachClickListener(tv_phone_verify_new_phone_no);
        attachClickListener(iv_back);
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == cd_original_phone_verify_code.getId()) {
            if (EmptyUtils.isEmpty(getEditTextStr(edt_original_phone))) {
                showToastMsg("手机号码不能为空");
                return;
            }
            if (getEditTextStr(edt_original_phone).length() != 11) {
                showToastMsg("手机号码格式不正确，请重新输入");
                return;
            }
            sendMsgVerifyCode(edt_original_phone);
        } else if (view.getId() == cd_new_phone_send_verifyt_code.getId()) {
            if (EmptyUtils.isEmpty(getEditTextStr(edt_new_phone))) {
                showToastMsg("手机号码不能为空");
                return;
            }
            if (getEditTextStr(edt_new_phone).length() != 11) {
                showToastMsg("手机号码格式不正确，请重新输入");
                return;
            }
            sendMsgVerifyCode(edt_new_phone);
        } else if (view.getId() == btn_confirm_input.getId()) {
            verifyAllInputInfo();
        } else if (view.getId() == tv_phone_verify_old_phone_no.getId()) {
//            SendPhoneVerifyDialog dialog = new SendPhoneVerifyDialog(context,RegisterActivity.this);
//            dialog.show();

        } else if (view.getId() == tv_phone_verify_new_phone_no.getId()) {
//            SendPhoneVerifyDialog dialog = new SendPhoneVerifyDialog(context,RegisterActivity.this);
//            dialog.show();

        } else if (view.getId() == iv_back.getId()) {
            finish();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeManager(BaseEvent event) {
        switch (event.getTag()) {
            case EventBusConstants.TIME_OUT:
//                tv_phone_verify_old_phone_no.setVisibility(View.VISIBLE);
                break;
        }
    }


    /**
     * 校验输入的合法性
     */
    private void verifyAllInputInfo() {
        if (EmptyUtils.isEmpty(getEditTextStr(edt_original_phone))) {
            showToastMsg("手机号码不能为空");
            return;
        }
        if (getEditTextStr(edt_original_phone).length() != 11) {
            showToastMsg("手机号码格式不正确，请重新输入");
            return;
        }
        if (EmptyUtils.isEmpty(getEditTextStr(edt_orignal_verify_code))) {
            showToastMsg("手机验证码不能为空");
            return;
        }
        if (getEditTextStr(edt_orignal_verify_code).length() != 6) {
            showToastMsg("手机验证码格式不正确，请重新输入");
            return;
        }
        if (EmptyUtils.isEmpty(getEditTextStr(edt_new_phone))) {
            showToastMsg("手机验证码不能为空");
            return;
        }
        if (getEditTextStr(edt_new_phone).length() != 11) {
            showToastMsg("手机号码格式不正确，请重新输入");
            return;
        }
        if (EmptyUtils.isEmpty(getEditTextStr(edt_new_phone_verify_code))) {
            showToastMsg("手机验证码不能为空");
            return;
        }
        if (getEditTextStr(edt_new_phone_verify_code).length() != 6) {
            showToastMsg("手机验证码格式不正确，请重新输入");
            return;
        }
        commitInputInfo();
    }

    /**
     * 原手机发送验证码
     */
    private void sendMsgVerifyCode(View view) {
        if (view.getId() == edt_original_phone.getId()) {
            cd_original_phone_verify_code.getInputContent(getEditTextStr(edt_original_phone));
        } else if (view.getId() == edt_new_phone.getId()) {
            cd_new_phone_send_verifyt_code.getInputContent(getEditTextStr(edt_new_phone));
        }
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
        if (view.getId() == edt_original_phone.getId()) {
            SmsMethods.getInstance().sendVerCode(sendVerCodeSub, getEditTextStr(edt_original_phone), "9");
        } else if (view.getId() == edt_new_phone.getId()) {
            SmsMethods.getInstance().sendVerCode(sendVerCodeSub, getEditTextStr(edt_new_phone), "10");
        }
        rxManager.add(sendVerCodeSub);
    }

    /**
     * 提交输入的信息
     */
    private void commitInputInfo() {
        CommonSubscriber<Object> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                dismissProgressDialog();
                showToastMsg("修性成功！");
                finish();
            }

            @Override
            public void onError(String e, int code) {
                dismissProgressDialog();
                showToastMsg(e);
            }
        });
        /**
         *  忘记账号找回   <br/>
         * @param oldPhone 旧手机号 <br/>
         * @param oldCheckcode 旧手机号验证码 <br/>
         * @param newPhone 新手机号 <br/>
         * @param newCheckcode 新手机号验证码 <br/>
         */
        BusinessUserMethods.getInstance().changManagerPhone(subscriber, getEditTextStr(edt_original_phone), getEditTextStr(edt_orignal_verify_code),
                getEditTextStr(edt_new_phone), getEditTextStr(edt_new_phone_verify_code));
        rxManager.add(subscriber);
    }
}
