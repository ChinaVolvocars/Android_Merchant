package com.common.retrofit.methods;

import com.common.base.Constants;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.MsgBena;
import com.common.retrofit.service.UserService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class MessageMethods extends BaseMethods {

    private static MessageMethods m_ins = null;

    public static MessageMethods getInstance() {
        if (m_ins == null) {
            synchronized (MessageMethods.class) {
                if (m_ins == null) {
                    m_ins = new MessageMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "message/";
    }

    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }
    public void delData(Subscriber<Object> subscriber, String id) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("id");
        Observable observable = initService().delDatam(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              ,id  );
        toOtherSubscribe(observable, subscriber);
    }
    public void listData (Subscriber<MsgBena> subscriber, int id) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("page");
        Observable observable = initService().listDataM(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              ,id  );
        toSubscribe(observable, subscriber);
    }
}