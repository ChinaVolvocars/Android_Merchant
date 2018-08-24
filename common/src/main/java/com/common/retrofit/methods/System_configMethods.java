package com.common.retrofit.methods;

import com.common.base.Constants;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.CCBean;
import com.common.retrofit.entity.result.YSBean;
import com.common.retrofit.service.UserService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class System_configMethods extends BaseMethods {

    private static System_configMethods m_ins = null;

    public static System_configMethods getInstance() {
        if (m_ins == null) {
            synchronized (System_configMethods.class) {
                if (m_ins == null) {
                    m_ins = new System_configMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "system_config/";
    }

    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }
    public void getClothesSize(Subscriber<CCBean> subscriber) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        Observable observable = initService().getClothesSize(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId);
        toSubscribe(observable, subscriber);
    }
    public void getclothescolor(Subscriber<YSBean> subscriber) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        Observable observable = initService().getclothescolor(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId);
        toSubscribe(observable, subscriber);
    }
}