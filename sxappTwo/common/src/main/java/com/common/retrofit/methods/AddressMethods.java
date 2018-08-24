package com.common.retrofit.methods;

import com.common.base.Constants;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.AddressBean;
import com.common.retrofit.entity.result.AddressResBean;
import com.common.retrofit.service.UserService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class AddressMethods extends BaseMethods {

    private static AddressMethods m_ins = null;

    public static AddressMethods getInstance() {
        if (m_ins == null) {
            synchronized (AddressMethods.class) {
                if (m_ins == null) {
                    m_ins = new AddressMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "address/";
    }
    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }
    public void listData(Subscriber<AddressBean> subscriber, String id, int page) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("search");
        list.add("page");
        Observable observable = initService().listData(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              ,id ,page);
        toSubscribe(observable, subscriber);
    }
    public void detaildata(Subscriber<AddressResBean> subscriber, String id) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("id");
        Observable observable = initService().detaildata(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              ,id );
        toSubscribe(observable, subscriber);
    }
    public void quickEditData (Subscriber<Object> subscriber,String idsss, String ids,String id) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("fieldName");
        list.add("updata");
        list.add("id");
        Observable observable = initService().quickEditData(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              ,idsss,ids,id );
        toOtherSubscribe(observable, subscriber);
    }
    public void saveData(Subscriber<Object> subscriber,String ids,String textStr, String editTextStr, String myAddress, String id) {
        List<String> list = new ArrayList();
        list.add("uid");
        list.add("hashid");
        list.add("person");
        list.add("tel");
        list.add("area");
        list.add("address");
        list.add("id");
        //收货人(person) 联系电话(tel) 所在地区(area) 详细地址(address)
        Observable observable = initService().saveData(Constants.getHash(list), System.currentTimeMillis() + "","e5f743c219cc437d4942471c0acff98d",3, DataCenter.UserId,DataCenter.HashId
              ,ids,textStr,editTextStr,myAddress,id );
        toOtherSubscribe(observable, subscriber);
    }
}