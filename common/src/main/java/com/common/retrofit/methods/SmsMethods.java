package com.common.retrofit.methods;

import com.common.base.Constants;
import com.common.retrofit.base.BaseMethods;
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
}