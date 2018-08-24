package com.hzxmkuar.sxmaketnew.mvpfunc.contract;

import com.common.mvp.BaseMView;
import com.common.mvp.BasePresenter;

/*******************************
* 找回密码接口逻辑
* created at 2017/4/7 下午 4:38
********************************/
public interface RetriveContract
{
    interface View extends BaseMView {
        void success();
        void findsuccess();
        void checkSuccess();
    }

    abstract class Presenter extends BasePresenter<View>
    {
        /**
         * 获取验证码
         * @param phone         电话号码
         * @param checkType     短信类型  1 注册  2 找回密码
         */
        public abstract void getSmsCode(String phone, int checkType);

        public abstract void register(String username, String password);

        // 检查验证码
        public abstract void checkSmsCode(String phone, String checkType, String code,String iswhat);

        public abstract void forgetPwd(String username, String password, String code);
    }
}