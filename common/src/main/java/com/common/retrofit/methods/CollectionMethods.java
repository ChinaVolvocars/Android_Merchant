package com.common.retrofit.methods;

import com.common.base.Constants;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.SCListBean;
import com.common.retrofit.service.UserService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class CollectionMethods extends BaseMethods {

    private static CollectionMethods m_ins = null;

    public static CollectionMethods getInstance() {
        if (m_ins == null) {
            synchronized (CollectionMethods.class) {
                if (m_ins == null) {
                    m_ins = new CollectionMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "Collection/";
    }

    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }
    public void listData(Subscriber<SCListBean> subscriber, int id) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("page");
        Observable observable = initService().listDatasss(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              ,id  );
        toSubscribe(observable, subscriber);
    }
    public void saveData(Subscriber<Object> subscriber, String id) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("goods_id");
        Observable observable = initService().saveDatassss(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              ,id  );
        toOtherSubscribe(observable, subscriber);
    }
}