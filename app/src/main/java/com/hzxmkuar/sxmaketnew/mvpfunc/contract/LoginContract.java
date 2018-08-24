package com.hzxmkuar.sxmaketnew.mvpfunc.contract;

import com.common.mvp.BasePresenter;
import com.common.mvp.BaseView;

public interface LoginContract
{
    interface View extends BaseView
    {
        void loginSuccess();
    }

    abstract class Presenter extends BasePresenter<View>
    {
        /**
         * 用户登录
         * @param account    账号
         * @param password   密码
         */
        public abstract void goToLogin(String account, String password);
    }
}