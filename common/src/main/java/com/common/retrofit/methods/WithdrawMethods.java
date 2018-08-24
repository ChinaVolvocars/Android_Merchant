package com.common.retrofit.methods;

import com.common.base.Constants;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.service.UserService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class WithdrawMethods extends BaseMethods {

    private static WithdrawMethods m_ins = null;

    public static WithdrawMethods getInstance() {
        if (m_ins == null) {
            synchronized (WithdrawMethods.class) {
                if (m_ins == null) {
                    m_ins = new WithdrawMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "withdraw/";
    }

    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }
    public void withdraw(Subscriber<Object> subscriber, String name,String code,String money,int type) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("name");
        list.add("code");
        list.add("money");
        list.add("type");
        Observable observable = initService().withdraw(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              ,name,code,money,type  );
        toOtherSubscribe(observable, subscriber);
    }
}