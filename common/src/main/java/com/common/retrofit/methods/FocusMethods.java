package com.common.retrofit.methods;

import com.common.base.Constants;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.AboutBean;
import com.common.retrofit.service.UserService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class FocusMethods extends BaseMethods {

    private static FocusMethods m_ins = null;

    public static FocusMethods getInstance() {
        if (m_ins == null) {
            synchronized (FocusMethods.class) {
                if (m_ins == null) {
                    m_ins = new FocusMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "focus/";
    }

    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }
    public void focus(Subscriber<Object> subscriber, String id) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("shop_id");
        Observable observable = initService().focus(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              ,id  );
        toOtherSubscribe(observable, subscriber);
    }
    public void listDataf(Subscriber<AboutBean> subscriber, int id) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("page");
        Observable observable = initService().listDataf(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              ,id  );
        toSubscribe(observable, subscriber);
    }
}