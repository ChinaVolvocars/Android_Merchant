package com.common.retrofit.methods;

import com.common.base.Constants;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.PayBeansss;
import com.common.retrofit.service.UserService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class OrderMethods extends BaseMethods {

    private static OrderMethods m_ins = null;

    public static OrderMethods getInstance() {
        if (m_ins == null) {
            synchronized (OrderMethods.class) {
                if (m_ins == null) {
                    m_ins = new OrderMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "order/";
    }

    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }
    public void liveOrder(Subscriber<PayBeansss> subscriber, String id,int type) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("live_id");
        list.add("pay_type");
        Observable observable = initService().liveOrder(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              ,id,type  );
        toSubscribe(observable, subscriber);
    }
    public void rechargeorder(Subscriber<PayBeansss> subscriber, String id,int type) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("money");
        list.add("pay_type");
        Observable observable = initService().rechargeorder(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              ,id,type  );
        toSubscribe(observable, subscriber);
    }
}