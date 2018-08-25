package com.hzxmkuar.sxmaketnew;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.EmptyUtils;
import com.common.widget.editview.DeleteEditText;

/**
 * 忘记密码——输入新密码
 * Created by Administrator on 2018/8/22.
 */
public class NewPwdActivity extends BaseMvpActivity {
    private static final String TAG = "NewPwdActivity";
    private DeleteEditText mEdtInputNewPwd;
    private DeleteEditText mEdtInputNewPwdConfirm;
    private Button mBtnConfirmPwd;
    private ImageView mIvNewpwdBack;
    private String mUserName;
    //    private CountdownButton mBtnConfirmPwdSendMsg;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_pwd;
    }

    @Override
    protected void onViewCreated() {
        mUserName = getIntent().getStringExtra("userName");
        mEdtInputNewPwd = (DeleteEditText) findViewById(R.id.edt_input_new_pwd);
        mEdtInputNewPwdConfirm = (DeleteEditText) findViewById(R.id.edt_input_new_pwd_confirm);
        mBtnConfirmPwd = (Button) findViewById(R.id.btn_confirm_pwd);
        mIvNewpwdBack = (ImageView) findViewById(R.id.iv_newpwd_back);
//        mBtnConfirmPwdSendMsg = (CountdownButton) findViewById(R.id.btn_confirm_pwd_send_msg);


    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(mBtnConfirmPwd);
        attachClickListener(mIvNewpwdBack);
    }

    @Override
    protected void setStatusBar() {
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == mBtnConfirmPwd.getId()) {
            verifyInput();
        } else if (view.getId() == mIvNewpwdBack.getId()) {
            finish();
        }
    }

    /**
     * 验证输入的合法性
     */
    private void verifyInput() {
        if (EmptyUtils.isEmpty(getEditTextStr(mEdtInputNewPwd))) {
            showToastMsg("新密码不能为空");
            return;
        } else if (getEditTextStr(mEdtInputNewPwd).length() < 6) {
            // TODO: 2018/8/22  需要确认密码的设定的规则
            showToastMsg("新密码长度必须大于6位");
            return;
        } else if (EmptyUtils.isEmpty(getEditTextStr(mEdtInputNewPwdConfirm))) {
            showToastMsg("确认新密码不能为空");
            return;
        } else if (getEditTextStr(mEdtInputNewPwdConfirm).length() < 6) {
            showToastMsg("确认新密码的长度必须大于6位");
            return;
        }
        sendResetPwdRequest();
    }

    /**
     * 发送变更密码的请求
     */
    private void sendResetPwdRequest() {
//        resetPwd(mUserName, getEditTextStr(mEdtInputNewPwdConfirm));
        showProgressingDialog();
        if (!EmptyUtils.isEmpty(mUserName) && getEditTextStr(mEdtInputNewPwd).equals(getEditTextStr(mEdtInputNewPwdConfirm))){
            CommonSubscriber<Object> checkSub = new CommonSubscriber<>(new SubscriberListener() {
                @Override
                public void onNext(Object o) {
                    showToastMsg("密码重置成功");
                    dismissProgressDialog();
                }

                @Override
                public void onError(String e, int code) {
                    dismissProgressDialog();
                    showToastMsg(e);
                }
            });
            BusinessUserMethods.getInstance().resetUserPwd(checkSub, mUserName, getEditTextStr(mEdtInputNewPwdConfirm));
            rxManager.add(checkSub);
        }

    }
}
