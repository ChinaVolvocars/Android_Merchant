package com.common.retrofit.methods;

import com.common.base.Constants;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.ShopCarBean;
import com.common.retrofit.service.UserService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class Shop_carMethods extends BaseMethods {

    private static Shop_carMethods m_ins = null;

    public static Shop_carMethods getInstance() {
        if (m_ins == null) {
            synchronized (Shop_carMethods.class) {
                if (m_ins == null) {
                    m_ins = new Shop_carMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "shop_car/";
    }

    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }
    public void saveDatas(Subscriber<Object> subscriber,String id, String paycolor, String paysize, String paynum, String zbid ) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("goods_id");
        list.add("color");
        list.add("size");
        list.add("num");
        list.add("live_id");
        //商品id(goods_id) 颜色(color) 尺寸(size) 数量(num) 直播id(live_id)
        Observable observable = initService().saveDatas(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
               ,id,paycolor,paysize,paynum,zbid);
        toOtherSubscribe(observable, subscriber);
    }
    public void listdata(Subscriber<ShopCarBean> subscriber, int page, String search ) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("page");
        list.add("search");
        Observable observable = initService().listdatass(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
               ,page,search);
        toSubscribe(observable, subscriber);
    }
    public void quickEditData(Subscriber<Object> subscriber, String page, String search ) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("id");
        list.add("num");
        Observable observable = initService().quickEditDatas(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
               ,page,search);
        toOtherSubscribe(observable, subscriber);
    }
    public void delData(Subscriber<Object> subscriber, String page ) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("id");
        Observable observable = initService().delData(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
               ,page);
        toOtherSubscribe(observable, subscriber);
    }
}