package com.common.retrofit.methods;

import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.ChangeBean;
import com.common.retrofit.entity.result.FBean;
import com.common.retrofit.entity.result.FiniBean;
import com.common.retrofit.entity.result.IndexBean;
import com.common.retrofit.entity.result.InfoBean;
import com.common.retrofit.entity.result.NewTestBean;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.service.UserService;
import com.google.gson.internal.ObjectConstructor;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
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

    /**
     * 忘记密码
     * @param subscriber
     */
    public void forgetPwd(Subscriber<IndexBean> subscriber,
                          String userName,String shopName,
                          String documentName,String certificatesType,
                          String certificatesNumber,String phoneNo,
                          String verCode) {
        Observable observable = initService().forgetPwd(System.currentTimeMillis() + "","e785f07736bde4d62b77d03214d29647",
                            userName,shopName,
                            documentName,certificatesType,
                            certificatesNumber,phoneNo,verCode);
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

//    // 原有新商家申请入驻接口
//    public void apply(Subscriber<Object> subscriber,String editTextStr, String textStr, String str, String s, String category_id,
//                      String proportion, String sheng, String shi, String qu, String editTextStr1,
//                      String textStr1, String str1, String s1, String certificates_type, String editTextStr2,
//                      String textStr2, String ID_front_img, String id_front_img, String license_img,String t,String t1) {
//
//        Observable observable = initService().apply( System.currentTimeMillis() + "","47dc24053e61ca7bcf2bae17f472a8b2",editTextStr,textStr,str,s,category_id,proportion,sheng
//                ,shi,qu,editTextStr1,textStr1,str1,s1,certificates_type,editTextStr2,textStr2,ID_front_img,id_front_img,license_img,t,t1);
//        toOtherSubscribe(observable, subscriber);
//    }

    /**
     * 新商家会员入驻接口   <br/>
     * @param subscriber 观察者
     * @param storeAccount 商家账号   <br/>
     * @param pwd 密码   <br/>
     * @param storeName 商家名称   <br/>
     * @param storePhoneNo 营业电话   <br/>
     * @param storeType 商户类型   <br/>
     * @param proportion 提拨比例   <br/>
     * @param provice 省   <br/>
     * @param city 市   <br/>
     * @param area 区   <br/>
     * @param addressDetail 详细地址   <br/>
     * @param inviteCode 邀请码   <br/>
     * @param legalName  法人姓名   <br/>
     * @param certificatesType  证件类型   <br/>
     * @param certificatesNo 证件号   <br/>
     * @param imgFront 正面照片   <br/>
     * @param imgBack   反面照片   <br/>
     * @param imgLicense    营业执照   <br/>
     * @param managerName   法人授权代理人姓名   <br/>
     * @param managerPhoneNo     手机号   <br/>
     * @param verCode   手机验证码   <br/>
     */
    public void newMerhcantApplyJoin(Subscriber<Object> subscriber,
                      String storeAccount, String pwd,
                      String storeName, String storePhoneNo,
                      String storeType, String proportion,
                      String provice, String city,
                      String area, String addressDetail,
                      String inviteCode, String legalName,
                      String certificatesType, String certificatesNo,
                      String imgFront, String imgBack,
                      String imgLicense, String managerName,
                      String managerPhoneNo, String verCode) {

        Observable observable = initService()
                .newMerhcantApplyJoin(System.currentTimeMillis() + "","47dc24053e61ca7bcf2bae17f472a8b2"
                ,storeAccount,pwd
                ,storeName,storePhoneNo
                ,storeType,proportion
                ,provice,city
                ,area,addressDetail
                ,inviteCode,legalName
                ,certificatesType,certificatesNo
                ,imgFront,imgBack
                ,imgLicense,managerName
                ,managerPhoneNo,verCode);
        toOtherSubscribe(observable, subscriber);
    }

    /**
     *  检测商户名称是是否唯一
     * @param subscriber 观察者
     */
    public void checkUsrName(Subscriber<HttpRespBean> subscriber, String userNmae) {
        Observable observable = initService().checkUsrnameIsUnique(System.currentTimeMillis()+"","35503c34c1bd394162d8f7e1df257b41" ,userNmae);
        toSubscribe(observable, subscriber);
    }


    /**
     *  发送手机短信验证码  <br/>
     * @param mobilePhone  手机号码  <br/>
     * @param checkType  checkType类型  <br/>
     *                   checkType类型  <br/>1 为注册。  <br/>2 为找回密码。  <br/>3 手机短信登录。  <br/>4 解除绑定。  <br/>5 短信绑定（手机绑定）  <br/>6 绑定第三方。
     * @return
     */
    public void sendVerCode(Subscriber<HttpRespBean> subscriber, String mobilePhone,String checkType) {
        Observable observable = initService().sendCode(System.currentTimeMillis()+"","c5ea4773f8c6122731ef99bcf1b960a8" ,mobilePhone,checkType);
        toSubscribe(observable, subscriber);
    }


//    @FormUrlEncoded
//    @POST("sendcode.html")
//    Observable<Object> sendcode(@Field("time") String hash, @Field("hash") String time,
//                                @Field("mobile") String uid, @Field("checktype") int password
//    );}
    public void resetUserPwd(Subscriber<Object> subscriber,String userNmae,String pwd){
        Observable observable = initService().resetPwd(System.currentTimeMillis() + "","3c437a1b469dc67c1e1a804b3a00270b",userNmae,pwd);
        toSubscribe(observable, subscriber);
    }




}