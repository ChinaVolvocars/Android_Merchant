package com.common.retrofit.methods;

import com.common.base.Constants;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.service.UserService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class CertificationMethods extends BaseMethods {

    private static CertificationMethods m_ins = null;

    public static CertificationMethods getInstance() {
        if (m_ins == null) {
            synchronized (CertificationMethods.class) {
                if (m_ins == null) {
                    m_ins = new CertificationMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "certification/";
    }

    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }
    public void auth(Subscriber<Object> subscriber, int type,String id) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("type");
        list.add("img");
        Observable observable = initService().auth(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              ,type,id  );
        toOtherSubscribe(observable, subscriber);
    }
}