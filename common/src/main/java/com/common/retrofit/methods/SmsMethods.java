package com.common.retrofit.methods;

import com.common.base.Constants;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.service.UserService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class SmsMethods extends BaseMethods {

    private static SmsMethods m_ins = null;

    public static SmsMethods getInstance() {
        if (m_ins == null) {
            synchronized (SmsMethods.class) {
                if (m_ins == null) {
                    m_ins = new SmsMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "Sms/";
    }

    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }
//
//    // 用户登录
//    public void sendCode(Subscriber<Object> subscriber, String phone, int type) {
//        List<String> list = new ArrayList();
//        list.add("mobile");
//        list.add("type");
//        Observable observable = initService().sendCode(System.currentTimeMillis() + "",Constants.getHash(list), "e5f743c219cc437d4942471c0acff98d",3, phone,type);
//        toOtherSubscribe(observable, subscriber);
//    }

    /**
     * 发送手机短信验证码  <br/>
     *
     * @param mobilePhone 手机号码  <br/>
     * @param checkType   checkType类型  <br/>
     *                    checkType类型  <br/>1 为注册。  <br/>2 为找回密码。  <br/>3 手机短信登录。  <br/>4 解除绑定。  <br/>5 短信绑定（手机绑定）  <br/>
     *                    6 绑定第三方。  <br/>
     *                    8 忘记账号找回。  <br/>
     *                    9 更换手机号码 原手机号码。  <br/>
     *                    10 更换手机号码 新手机号码。  <br/>
     * @return
     */
    public void sendVerCode(Subscriber<HttpRespBean> subscriber, String mobilePhone, String checkType) {
        Observable observable = initService().sendCode(System.currentTimeMillis() + "", "c5ea4773f8c6122731ef99bcf1b960a8", mobilePhone, checkType);
        toSubscribe(observable, subscriber);
    }
}