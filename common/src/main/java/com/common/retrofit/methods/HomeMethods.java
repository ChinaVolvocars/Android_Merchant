package com.common.retrofit.methods;

import com.common.base.Constants;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.HomeOneBean;
import com.common.retrofit.entity.result.ThreeBean;
import com.common.retrofit.entity.result.TwoBean;
import com.common.retrofit.service.UserService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class HomeMethods extends BaseMethods {

    private static HomeMethods m_ins = null;

    public static HomeMethods getInstance() {
        if (m_ins == null) {
            synchronized (HomeMethods.class) {
                if (m_ins == null) {
                    m_ins = new HomeMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "Home/";
    }

    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }
    public void index (Subscriber<HomeOneBean> subscriber) {
        List<String> list = new ArrayList();
        list.add("no");
        Observable observable = initService().index(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3
              ,""  );
        toSubscribe(observable, subscriber);
    }
    public void shop (Subscriber<TwoBean> subscriber, int page, String key, String ca) {
        List<String> list = new ArrayList();
        list.add("page");
        list.add("keyword");
        list.add("category");
        Observable observable = initService().shop(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3
              ,page,key,ca  );
        toSubscribe(observable, subscriber);
    }
    public void factory (Subscriber<ThreeBean> subscriber, int page, String key, String ca) {
        List<String> list = new ArrayList();
        list.add("page");
        list.add("keyword");
        list.add("category");
        list.add("uid");
        Observable observable = initService().factory(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3
              ,page,key,ca , DataCenter.UserId);
        toSubscribe(observable, subscriber);
    }
}