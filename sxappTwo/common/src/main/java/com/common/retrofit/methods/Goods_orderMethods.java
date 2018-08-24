package com.common.retrofit.methods;

import com.common.base.Constants;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.MyOrderBean;
import com.common.retrofit.entity.result.PayBeansss;
import com.common.retrofit.entity.result.PaySnBean;
import com.common.retrofit.entity.result.QRDDBean;
import com.common.retrofit.entity.result.SPPJBean;
import com.common.retrofit.service.UserService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class Goods_orderMethods extends BaseMethods {

    private static Goods_orderMethods m_ins = null;

    public static Goods_orderMethods getInstance() {
        if (m_ins == null) {
            synchronized (Goods_orderMethods.class) {
                if (m_ins == null) {
                    m_ins = new Goods_orderMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "goods_order/";
    }

    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }
    public void confirmOrder(Subscriber<QRDDBean> subscriber, String data) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("data");
        Observable observable = initService().confirmOrder(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId,data);
        toSubscribe(observable, subscriber);
    }
    public void goodsOrder(Subscriber<PaySnBean> subscriber, String data, String name) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("address_id");
        list.add("data");
        Observable observable = initService().goodsOrder(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId,data,name);
        toSubscribe(observable, subscriber);
    }
    public void reply(Subscriber<Object> subscriber, String data, String name) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("id");
        list.add("reply");
        Observable observable = initService().reply(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId,data,name);
        toOtherSubscribe(observable, subscriber);
    }
    public void userList(Subscriber<MyOrderBean> subscriber, String type,String types, int page) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("type");
        list.add("page");
        list.add("is_shop");
        Observable observable = initService().userList(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId,type,types,page);
        toSubscribe(observable, subscriber);
    }
    public void pay(Subscriber<PayBeansss> subscriber, String type, String types, int page) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("is_sn");
        list.add("data");
        list.add("pay_type");
        Observable observable = initService().pay(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId,type,types,page);
        toSubscribe(observable, subscriber);
    }
    public void getOrderComments(Subscriber<SPPJBean> subscriber, String type, String types, int page) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("goods_id");
        list.add("search");
        list.add("page");
        list.add("is_shop");
        Observable observable = initService().getOrderComments(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, "",type,DataCenter.UserId,types,page);
        toSubscribe(observable, subscriber);
    }
    public void cancelOrder(Subscriber<Object> subscriber, String type,String types, String page) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("id");
        list.add("field");
        list.add("value");
        Observable observable = initService().cancelOrder(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId,type,types,page);
        toOtherSubscribe(observable, subscriber);
    }
    public void logistics(Subscriber<Object> subscriber, String type,String types, String page) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("order_id");
        list.add("com");
        list.add("code");
        Observable observable = initService().logistics(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId,type,types,page);
        toOtherSubscribe(observable, subscriber);
    }
}