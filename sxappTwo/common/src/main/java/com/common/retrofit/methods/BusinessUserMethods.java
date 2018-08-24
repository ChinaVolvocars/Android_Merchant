package com.common.retrofit.methods;

import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.ChangeBean;
import com.common.retrofit.entity.result.FBean;
import com.common.retrofit.entity.result.FiniBean;
import com.common.retrofit.entity.result.IndexBean;
import com.common.retrofit.entity.result.InfoBean;
import com.common.retrofit.entity.result.NewTestBean;
import com.common.retrofit.service.UserService;

import rx.Observable;
import rx.Subscriber;

public class BusinessUserMethods extends BaseMethods {

    private static BusinessUserMethods m_ins = null;

    public static BusinessUserMethods getInstance() {
        if (m_ins == null) {
            synchronized (BusinessUserMethods.class) {
                if (m_ins == null) {
                    m_ins = new BusinessUserMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "BusinessUser/";
    }

    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }
    // 用户登录
    public void scategory(Subscriber<NewTestBean> subscriber) {
        Observable observable = initService().scategory( System.currentTimeMillis() + "","70ffcb2a3569065c4420776b8a81809b");
        toOtherSubscribe(observable, subscriber);
    }

    /**
     *  修改商铺信息提交
     * @param subscriber
     * @param name
     * @param typeid
     * @param shengid
     * @param shiid
     * @param quid
     * @param adddes
     * @param showdes
     * @param pic
     * @param picList
     * @param pics
     * @param picLists
     */
    public void shopInfoSubmit(Subscriber<Object> subscriber,String name, String typeid, String shengid, String shiid, String quid,
                               String adddes, String showdes, String pic, String picList, String pics, String picLists) {
        Observable observable = initService().shopInfoSubmit( System.currentTimeMillis() + "","a831cff77e30288bf980cd32b4c960c5",DataCenter.UserId,DataCenter.HashId,name,typeid,shengid,shiid,quid,adddes,showdes,pic,picList
        ,pics,picLists);
        toOtherSubscribe(observable, subscriber);
    }
    public void storeSubmit(Subscriber<Object> subscriber,String name, String typeid, String shengid
                               ) {
        Observable observable = initService().storeSubmit( System.currentTimeMillis() + "","23afbb21099d52421b0d5930cd870512",DataCenter.UserId,DataCenter.HashId,name,typeid,shengid);
        toOtherSubscribe(observable, subscriber);
    }
    public void withdraw(Subscriber<Object> subscriber,String name, String typeid, String shengid
                               ) {
        Observable observable = initService().withdraws( System.currentTimeMillis() + "","9bd814e2ffc73f7fbf48f1e36c4bce90",DataCenter.UserId,DataCenter.HashId,name,typeid,shengid);
        toOtherSubscribe(observable, subscriber);
    }
    public void financeList(Subscriber<FiniBean> subscriber, int page) {
        Observable observable = initService().financeList( System.currentTimeMillis() + "","1522546fb8e6c8b6c2d37837e99d0730", DataCenter.UserId,DataCenter.HashId,page);
        toSubscribe(observable, subscriber);
    }

    /**
     * 获取首页数据
     * @param subscriber
     */
    public void index(Subscriber<IndexBean> subscriber) {
        Observable observable = initService().index( System.currentTimeMillis() + "","e785f07736bde4d62b77d03214d29647", DataCenter.UserId,DataCenter.HashId);
        toSubscribe(observable, subscriber);
    }
    public void financeDetail(Subscriber<FBean> subscriber) {
        Observable observable = initService().financeDetail( System.currentTimeMillis() + "","e785f07736bde4d62b77d03214d29647", DataCenter.UserId,DataCenter.HashId);
        toSubscribe(observable, subscriber);
    }

    /**
     *  获取店铺信息
     * @param subscriber
     */
    public void shopInfo(Subscriber<InfoBean> subscriber) {
        Observable observable = initService().shopInfo( System.currentTimeMillis() + "","e785f07736bde4d62b77d03214d29647", DataCenter.UserId,DataCenter.HashId);
        toSubscribe(observable, subscriber);
    }
    public void store(Subscriber<ChangeBean> subscriber) {
        Observable observable = initService().store( System.currentTimeMillis() + "","e785f07736bde4d62b77d03214d29647", DataCenter.UserId,DataCenter.HashId);
        toSubscribe(observable, subscriber);
    }
    public void apply(Subscriber<Object> subscriber,String editTextStr, String textStr, String str, String s, String category_id,
                      String proportion, String sheng, String shi, String qu, String editTextStr1,
                      String textStr1, String str1, String s1, String certificates_type, String editTextStr2,
                      String textStr2, String ID_front_img, String id_front_img, String license_img,String t,String t1) {

        Observable observable = initService().apply( System.currentTimeMillis() + "","47dc24053e61ca7bcf2bae17f472a8b2",editTextStr,textStr,str,s,category_id,proportion,sheng
                ,shi,qu,editTextStr1,textStr1,str1,s1,certificates_type,editTextStr2,textStr2,ID_front_img,id_front_img,license_img,t,t1);
        toOtherSubscribe(observable, subscriber);
    }
}