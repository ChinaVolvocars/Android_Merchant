package com.common.retrofit.methods;

import com.common.base.Constants;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.JFBean;
import com.common.retrofit.service.UserService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class SignMethods extends BaseMethods {

    private static SignMethods m_ins = null;

    public static SignMethods getInstance() {
        if (m_ins == null) {
            synchronized (SignMethods.class) {
                if (m_ins == null) {
                    m_ins = new SignMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "sign/";
    }

    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }
    public void listdata(Subscriber<JFBean> subscriber, int id) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("page");
        list.add("search");
        Observable observable = initService().listdataa(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              ,id  ,"");
        toSubscribe(observable, subscriber);
    }
    public void sign(Subscriber<Object> subscriber) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        Observable observable = initService().sign(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              );
        toOtherSubscribe(observable, subscriber);
    }
}