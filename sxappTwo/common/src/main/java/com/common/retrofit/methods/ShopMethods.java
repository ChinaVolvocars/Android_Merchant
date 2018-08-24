package com.common.retrofit.methods;

import com.common.base.Constants;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.DPDABean;
import com.common.retrofit.entity.result.GGBean;
import com.common.retrofit.entity.result.GoodCBean;
import com.common.retrofit.entity.result.GoodCTwoBean;
import com.common.retrofit.entity.result.GoodDBean;
import com.common.retrofit.entity.result.SHBean;
import com.common.retrofit.entity.result.SPFLBean;
import com.common.retrofit.entity.result.SPListBean;
import com.common.retrofit.service.UserService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class ShopMethods extends BaseMethods {

    private static ShopMethods m_ins = null;

    public static ShopMethods getInstance() {
        if (m_ins == null) {
            synchronized (ShopMethods.class) {
                if (m_ins == null) {
                    m_ins = new ShopMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "Shop/";
    }

    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }
    // 用户登录
    public void saveData(Subscriber<Object> subscriber, String shop_face, String shop_img, String editTextStr,
                         String textStr, String str, String province_id, String city_id,
                         String area_id, String s, String editTextStr1, String textStr1,
                         String arbitration_name, String arbitration_sn, String arbitration_img,
                         String main_type, String id_cart_1, String id_cart_2, String s1,String s2) {
        /**
         *   shop_face ,shop_img,title,person,tel,province_id,city_id,area_id,address,floor,sell_sn,arbitration_name,
         arbitration_sn,arbitration_img,main_type,id_cart_1,id_cart_2,shop_type,id;
         * **/
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("shop_face");
        list.add("shop_img");
        list.add("title");
        list.add("person");
        list.add("tel");
        list.add("province_id");
        list.add("city_id");
        list.add("area_id");
        list.add("address");
        list.add("floor");
        list.add("sell_sn");
        list.add("arbitration_name");
        list.add("arbitration_sn");
        list.add("arbitration_img");
        list.add("main_type");
        list.add("id_cart_1");
        list.add("id_cart_2");
        list.add("shop_type");
        list.add("id");
        Observable observable = initService().saveData(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
                ,shop_face,shop_img,editTextStr,textStr,str,province_id,
                city_id,area_id,s,editTextStr1,textStr1,arbitration_name,arbitration_sn,
                arbitration_img,main_type,id_cart_1,id_cart_2,s1,s2);
        toOtherSubscribe(observable, subscriber);
    }
    public void details(Subscriber<SHBean> subscriber, String id) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("id");
        Observable observable = initService().details(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              ,id  );
        toSubscribe(observable, subscriber);
    }
    public void noticeSave (Subscriber<Object> subscriber, String id) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("notice");
        Observable observable = initService().noticeSave(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              ,id  );
        toOtherSubscribe(observable, subscriber);
    }
    public void noticedetail(Subscriber<GGBean> subscriber) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        Observable observable = initService().noticedetail(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
               );
        toSubscribe(observable, subscriber);
    }
    public void shoparchive(Subscriber<DPDABean> subscriber, String id) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("shop_id");
        Observable observable = initService().shoparchive(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId
              ,id  );
        toSubscribe(observable, subscriber);
    }
    public void shopDetail(Subscriber<GoodDBean> subscriber, String id, int page) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("page");
        list.add("search");
        list.add("id");
        Observable observable = initService().shopDetail(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,""
              ,id , page);
        toSubscribe(observable, subscriber);
    }
    public void factorydetail(Subscriber<GoodCBean> subscriber, String type , String id, int page) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("page");
        list.add("search");
        list.add("id");
        list.add("type");
        Observable observable = initService().factorydetail(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,""
              ,type,id , page);
        toSubscribe(observable, subscriber);
    }
    public void factorynew(Subscriber<GoodCTwoBean> subscriber, String id, int page) {
        List<String> list = new ArrayList();
        list.add("page");
        list.add("search");
        list.add("id");
        Observable observable = initService().factorynew(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, ""
              ,id , page);
        toSubscribe(observable, subscriber);
    }
    public void shopgoods(Subscriber<SPListBean> subscriber, String shopid, String id, int page, String search) {
        List<String> list = new ArrayList();
        list.add("page");
        list.add("search");
        list.add("category_id");
        list.add("shop_id");
        Observable observable = initService().shopgoods(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3,shopid
              ,id , page,search);
        toSubscribe(observable, subscriber);
    }
    public void shopcategory(Subscriber<SPFLBean> subscriber, String id, int page) {
        List<String> list = new ArrayList();
        list.add("page");
        list.add("shop_id");
        Observable observable = initService().shopcategory(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3
              ,id , page);
        toSubscribe(observable, subscriber);
    }
}