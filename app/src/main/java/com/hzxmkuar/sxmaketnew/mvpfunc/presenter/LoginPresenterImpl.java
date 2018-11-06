package com.hzxmkuar.sxmaketnew.mvpfunc.presenter;

import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.UserBean;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.EmptyUtils;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.mvpfunc.contract.LoginContract;

/*******************************
 * 登录逻辑
 * created at 2017/3/17 下午 2:27
 ********************************/
public class LoginPresenterImpl extends LoginContract.Presenter {
    @Override
    public void goToLogin(String account, String password) {
        if (verify(account, password)) {
            return;
        }

        mView.showProgressingDialog();
        CommonSubscriber<UserBean> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                UserBean userBean = (UserBean) o;
                if (EmptyUtils.isNotEmpty(userBean)) {
                    DataCenter.saveLoginDataInfo(userBean);
                    mView.loginSuccess();
                } else {
                    mView.showToastMsg("登录失败");
                }
            }

            @Override
            public void onError(String e, int code) {
                mView.dismissProgressDialog();
                mView.showToastMsg(e);
            }
        });

//        SysMethods.getInstance().login(subscriber, account, password);
        rxManager.add(subscriber);
    }

    /**
     * 表单验证
     *
     * @param account  账号名
     * @param password 密码
     * @return true为不通过
     */
    public boolean verify(String account, String password) {
        if (EmptyUtils.isEmpty(account)) {
            mView.showToastMsg(context.getString(R.string.string_login_t_input));
            return true;
        } else if (EmptyUtils.isEmpty(password)) {
            mView.showToastMsg(context.getString(R.string.string_login_t_pwd));
            return true;
        }
        return false;
    }
}