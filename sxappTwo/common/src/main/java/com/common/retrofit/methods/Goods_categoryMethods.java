package com.common.retrofit.methods;

import com.common.base.Constants;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.ChooseFLBean;
import com.common.retrofit.entity.result.FLBean;
import com.common.retrofit.entity.result.FLTwoBean;
import com.common.retrofit.service.UserService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class Goods_categoryMethods extends BaseMethods {

    private static Goods_categoryMethods m_ins = null;

    public static Goods_categoryMethods getInstance() {
        if (m_ins == null) {
            synchronized (Goods_categoryMethods.class) {
                if (m_ins == null) {
                    m_ins = new Goods_categoryMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "goods_category/";
    }

    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }
    public void category(Subscriber<ChooseFLBean> subscriber) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        Observable observable = initService().category(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId);
        toOtherSubscribe(observable, subscriber);
    }
    public void categoryPage(Subscriber<FLBean> subscriber, int page , String search) {
        List<String> list = new ArrayList();
        list.add("page");
        list.add("search");
        Observable observable = initService().categoryPage(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3,page,search);
        toSubscribe(observable, subscriber);
    }
    public void getSecondCate(Subscriber<FLTwoBean> subscriber, int page , String search, String id) {
        List<String> list = new ArrayList();
        list.add("page");
        list.add("search");
        list.add("pid");
        Observable observable = initService().getSecondCate(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3,page,search,id);
        toSubscribe(observable, subscriber);
    }
}