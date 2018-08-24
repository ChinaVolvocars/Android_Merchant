package com.common.retrofit.methods;

import com.common.base.Constants;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.MyZBList;
import com.common.retrofit.entity.result.TimeBean;
import com.common.retrofit.entity.result.ZBDetil;
import com.common.retrofit.entity.result.ZBDetilBean;
import com.common.retrofit.entity.result.ZBListBean;
import com.common.retrofit.service.UserService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class Live_roomMethods extends BaseMethods {

    private static Live_roomMethods m_ins = null;

    public static Live_roomMethods getInstance() {
        if (m_ins == null) {
            synchronized (Live_roomMethods.class) {
                if (m_ins == null) {
                    m_ins = new Live_roomMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "live_room/";
    }

    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }
    public void liveList(Subscriber<ZBListBean> subscriber, int type, int page) {
        List<String> list = new ArrayList();
        list.add("type");
        list.add("page");
        Observable observable = initService().liveList(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3
              ,type,page  );
        toSubscribe(observable, subscriber);
    }
    public void listdata(Subscriber<MyZBList> subscriber, int type, int page) {
        List<String> list = new ArrayList();
        list.add("type");
        list.add("uid");
        list.add("hashid");
        list.add("page");
        Observable observable = initService().listdata(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              ,type,page  );
        toSubscribe(observable, subscriber);
    }
    public void dolive(Subscriber<ZBDetil> subscriber, String type) {
        List<String> list = new ArrayList();
        list.add("live_id");
        list.add("uid");
        list.add("hashid");
        Observable observable = initService().dolive(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              ,type );
        toSubscribe(observable, subscriber);
    }
    public void detail(Subscriber<ZBDetilBean> subscriber, String type) {
        List<String> list = new ArrayList();
        list.add("live_id");
        list.add("uid");
        list.add("hashid");
        list.add("page");
        Observable observable = initService().detail(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              ,type,1 );
        toSubscribe(observable, subscriber);
    }
    public void details(Subscriber<ZBDetilBean> subscriber, String type,int page) {
        List<String> list = new ArrayList();
        list.add("live_id");
        list.add("uid");
        list.add("hashid");
        list.add("page");
        Observable observable = initService().detail(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
                ,type,page );
        toSubscribe(observable, subscriber);
    }
    public void getlivetime(Subscriber<TimeBean> subscriber) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        Observable observable = initService().getlivetime(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
               );
        toSubscribe(observable, subscriber);
    }
    public void addLive(Subscriber<Object> subscriber,String editTextStr, String time, String textStr, String ids, String shop_img) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("title");
        list.add("start_time");
        list.add("live_price");
        list.add("live_goods");
        list.add("live_img");
        /*
        * 直播标题(title) 直播时间(start_time) 直播价格(live_price) 直播商品(live_goods) 直播封面(live_img)
        * */
        Observable observable = initService().addLive(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
               ,editTextStr,time,textStr,ids,shop_img);
        toOtherSubscribe(observable, subscriber);
    }
}