package com.common.retrofit.methods;

import com.common.base.Constants;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.SPDetilBean;
import com.common.retrofit.entity.result.SPListBean;
import com.common.retrofit.service.UserService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class GoodsMethods extends BaseMethods {

    private static GoodsMethods m_ins = null;

    public static GoodsMethods getInstance() {
        if (m_ins == null) {
            synchronized (GoodsMethods.class) {
                if (m_ins == null) {
                    m_ins = new GoodsMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "Goods/";
    }

    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }
    public void listData(Subscriber<SPListBean> subscriber, String type, int page,String key) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("type");
        list.add("page");
        list.add("keyword");
        Observable observable = initService().listDatas(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId
              ,type,page ,key);
        toSubscribe(observable, subscriber);
    }
    public void detailData(Subscriber<SPDetilBean> subscriber, String type) {
        List<String> list = new ArrayList();
        list.add("id");
        list.add("uid");
        Observable observable = initService().detailData(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3
              ,DataCenter.UserId,type);
        toSubscribe(observable, subscriber);
    }
    public void addGoods(Subscriber<Object> subscriber, String editTextStr, String textStr,
                         String str, String s, String editTextStr1, String my_fl, String my_cc,
                         String my_ys, String sptp, String spxq, String textStr1, String videoId) {
        List<String> list = new ArrayList();
        //title retail_price whole_price goods_sn goods_num category_id size color
        //goods_imgs desc_imgs desc goods_video
        list.add("uid");
        list.add("hashid");
        list.add("title");
        list.add("retail_price");
        list.add("whole_price");
        list.add("goods_sn");
        list.add("goods_num");
        list.add("category_id");
        list.add("size");
        list.add("color");
        list.add("goods_imgs");
        list.add("desc_imgs");
        list.add("desc");
        list.add("goods_video");

        Observable observable = initService().addGoods(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              ,editTextStr,textStr,str,s,editTextStr1,
                my_fl,my_cc,my_ys,sptp,spxq,textStr1,videoId);
        toOtherSubscribe(observable, subscriber);
    }
}