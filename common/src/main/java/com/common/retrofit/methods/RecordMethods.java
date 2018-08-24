package com.common.retrofit.methods;

import com.common.base.Constants;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.MyzjBean;
import com.common.retrofit.entity.result.WallBean;
import com.common.retrofit.service.UserService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class RecordMethods extends BaseMethods {

    private static RecordMethods m_ins = null;

    public static RecordMethods getInstance() {
        if (m_ins == null) {
            synchronized (RecordMethods.class) {
                if (m_ins == null) {
                    m_ins = new RecordMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "record/";
    }

    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }
    public void browsehistory(Subscriber<MyzjBean> subscriber, int id) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("page");
        Observable observable = initService().browsehistory(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              ,id  );
        toSubscribe(observable, subscriber);
    }
    public void caserecord(Subscriber<WallBean> subscriber, int id, String is) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("page");
        list.add("is_shop");
        Observable observable = initService().caserecord(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              ,id , is);
        toSubscribe(observable, subscriber);
    }
}