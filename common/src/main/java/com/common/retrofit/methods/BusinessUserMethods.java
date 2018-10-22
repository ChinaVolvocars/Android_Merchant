package com.common.retrofit.methods;

import android.provider.SyncStateContract;
import android.util.Log;

import com.common.base.Constants;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.ActiveListEntity;
import com.common.retrofit.entity.result.AppVersionEntity;
import com.common.retrofit.entity.result.ChangeBean;
import com.common.retrofit.entity.result.FBean;
import com.common.retrofit.entity.result.FinanceDetailEntity;
import com.common.retrofit.entity.result.FiniBean;
import com.common.retrofit.entity.result.IndexBean;
import com.common.retrofit.entity.result.InfoBean;
import com.common.retrofit.entity.result.NewTestBean;
import com.common.retrofit.entity.result.ShopShowsEntity;
import com.common.retrofit.entity.result.WidthDrawEntity;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.service.UserService;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class BusinessUserMethods extends BaseMethods {
    private static final String TAG = "BusinessUserMethods";
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
        Observable observable = initService().scategory(System.currentTimeMillis() + "", "70ffcb2a3569065c4420776b8a81809b");
        toOtherSubscribe(observable, subscriber);
    }

//    /**
//     * 修改商铺信息提交
//     *
//     * @param subscriber
//     * @param name
//     * @param typeid
//     * @param shengid
//     * @param shiid
//     * @param quid
//     * @param adddes
//     * @param showdes
//     * @param pic
//     * @param picList
//     * @param pics
//     * @param picLists
//     */
//    public void shopInfoSubmit(Subscriber<Object> subscriber, String name, String typeid, String shengid, String shiid, String quid,
//                               String adddes, String showdes, String pic, String picList, String pics, String picLists) {
//        Observable observable = initService().shopInfoSubmit(System.currentTimeMillis() + "", "a831cff77e30288bf980cd32b4c960c5", DataCenter.UserId, DataCenter.HashId, name, typeid, shengid, shiid, quid, adddes, showdes, pic, picList
//                , pics, picLists);
//        toOtherSubscribe(observable, subscriber);
//    }

    /**
     * 修改商铺信息提交（新接口）
     * @param subscriber
     * @param shopName
     * @param shopTypeId
     * @param shengId
     * @param shiId
     * @param quId
     * @param adddes
     * @param shopDesc
     * @param pic
     * @param reqList
     */
    public void shopInfoEditedCommitt(Subscriber<Object> subscriber, String shopName, String shopTypeId, String shengId, String shiId, String quId,
                               String adddes, String shopDesc, String pic,List<String> reqList) {
        Observable observable = initService().shopInfoSubmit(System.currentTimeMillis() + "", Constants.getHash(reqList), DataCenter.UserId, DataCenter.HashId,
                shopName, shopTypeId, shengId, shiId, quId, adddes, shopDesc, pic);
        toOtherSubscribe(observable, subscriber);
    }

    /**
     * 商户上传店铺图片（新增接口）
     * @param subscriber
     * @param picLists  上传的图片及描述
     * @param reqList 请求的参数集合
     */
    public void updateShopImgs(Subscriber<Object> subscriber, String picLists,List<String> reqList) {
        Observable observable = initService().updateShopImges(System.currentTimeMillis() + "", Constants.getHash(reqList), DataCenter.UserId, DataCenter.HashId,picLists
                );
        toOtherSubscribe(observable, subscriber);
    }

    public void storeSubmit(Subscriber<Object> subscriber, String name, String typeid, String shengid ) {
        Observable observable = initService().storeSubmit(System.currentTimeMillis() + "", "23afbb21099d52421b0d5930cd870512", DataCenter.UserId, DataCenter.HashId, name, typeid, shengid);
        toOtherSubscribe(observable, subscriber);
    }

//    public void withdraw(Subscriber<Object> subscriber, String name, String typeid, String shengid ) {
//        Observable observable = initService().withdraws(System.currentTimeMillis() + "", "9bd814e2ffc73f7fbf48f1e36c4bce90", DataCenter.UserId, DataCenter.HashId, name, typeid, shengid);
//        toOtherSubscribe(observable, subscriber);
//    }

    public void withdraw(Subscriber<WidthDrawEntity> subscriber, String name, String typeid, String shengid ) {
        Observable observable = initService().withdraws(System.currentTimeMillis() + "", "9bd814e2ffc73f7fbf48f1e36c4bce90", DataCenter.UserId, DataCenter.HashId, name, typeid, shengid);
        toSubscribe(observable, subscriber);
    }

    /**
     * 账务管理列表信息
     *
     * @param subscriber
     * @param page
     */
    public void financeList(Subscriber<FiniBean> subscriber, int page) {
        Observable observable = initService().financeList(System.currentTimeMillis() + "", "1522546fb8e6c8b6c2d37837e99d0730", DataCenter.UserId, DataCenter.HashId, page);
//        Observable observable = initService().financeList(System.currentTimeMillis() + "", "1522546fb8e6c8b6c2d37837e99d0730", 10, DataCenter.HashId, page);
        toSubscribe(observable, subscriber);
    }

    /**
     * 获取首页数据
     *
     * @param subscriber
     */
    public void index(Subscriber<IndexBean> subscriber) {
        Observable observable = initService().index(System.currentTimeMillis() + "", "e785f07736bde4d62b77d03214d29647", DataCenter.UserId, DataCenter.HashId);
        toSubscribe(observable, subscriber);
    }

    /**
     * 忘记密码
     *
     * @param subscriber
     */
    public void forgetPwd(Subscriber<IndexBean> subscriber,
                          String userName, String shopName,
                          String documentName, String certificatesType,
                          String certificatesNumber, String phoneNo,
                          String verCode) {
        Observable observable = initService().forgetPwd(System.currentTimeMillis() + "", "f96cc776ba900d47eb7b8b9c4e2f73c1",
                userName, shopName,
                documentName, certificatesType,
                certificatesNumber, phoneNo, verCode);
        toSubscribe(observable, subscriber);
    }


    /**
     * 拉取消费抵账鑫豆记录清单
     *
     * @param subscriber
     */
    public void financeDetail(Subscriber<FBean> subscriber) {
        Observable observable = initService().financeDetail(System.currentTimeMillis() + "", "e785f07736bde4d62b77d03214d29647", DataCenter.UserId, DataCenter.HashId);
        toSubscribe(observable, subscriber);
    }

    /**
     * 拉取消费抵账鑫豆记录清单(新的返回值类型)
     *
     * @param subscriber
     */
    public void financeDetailNew(Subscriber<FinanceDetailEntity> subscriber) {
        Observable observable = initService().financeDetailNew(System.currentTimeMillis() + "", "e785f07736bde4d62b77d03214d29647", DataCenter.UserId, DataCenter.HashId);
//        Log.i("FinancialManagerActivit", "financeDetailNew:           uid:        " + DataCenter.UserId);
//        Observable observable = initService().financeDetailNew(System.currentTimeMillis() + "", "e785f07736bde4d62b77d03214d29647", 10, DataCenter.HashId);
        toSubscribe(observable, subscriber);
//        toOtherSubscribe(observable, subscriber);
    }


//    /**
//     * 获取店铺信息
//     *
//     * @param subscriber
//     */
//    public void shopInfo(Subscriber<InfoBean> subscriber) {
//        Observable observable = initService().shopInfo(System.currentTimeMillis() + "", "e785f07736bde4d62b77d03214d29647", DataCenter.UserId, DataCenter.HashId);
//        toSubscribe(observable, subscriber);
//    }
    /**
     * 获取店铺信息(新接口)
     * @param subscriber
     */
    public void getShopInfo(Subscriber<InfoBean> subscriber, List<String> generateHashList) {
        Observable observable = initService().shopInfo(System.currentTimeMillis() + "", Constants.getHash(generateHashList), DataCenter.UserId, DataCenter.HashId);
        toSubscribe(observable, subscriber);
    }

    public void store(Subscriber<ChangeBean> subscriber) {
        Observable observable = initService().store(System.currentTimeMillis() + "", "e785f07736bde4d62b77d03214d29647", DataCenter.UserId, DataCenter.HashId);
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
     *
     * @param subscriber       观察者
     * @param storeAccount     商家账号   <br/>
     * @param pwd              密码   <br/>
     * @param storeName        商家名称   <br/>
     * @param storePhoneNo     营业电话   <br/>
     * @param storeType        商户类型   <br/>
     * @param proportion       提拨比例   <br/>
     * @param provice          省   <br/>
     * @param city             市   <br/>
     * @param area             区   <br/>
     * @param addressDetail    详细地址   <br/>
     * @param inviteCode       邀请码   <br/>
     * @param legalName        法人姓名   <br/>
     * @param certificatesType 证件类型   <br/>
     * @param certificatesNo   证件号   <br/>
     * @param imgFront         正面照片   <br/>
     * @param imgBack          反面照片   <br/>
     * @param imgLicense       营业执照   <br/>
     *                         //     * @param managerName      法人授权代理人姓名   <br/>
     * @param managerPhoneNo   手机号   <br/>
     * @param verCode          手机验证码   <br/>
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
//                                     String imgLicense, String managerName,
                                     String imgLicense,
                                     String managerPhoneNo, String verCode) {

        Observable observable = initService()
//                .newMerhcantApplyJoin(System.currentTimeMillis() + "","47dc24053e61ca7bcf2bae17f472a8b2"
                .newMerhcantApplyJoin(System.currentTimeMillis() + "", "53dc732e3bf21146a143d1d8cae38592"
                        , storeAccount, pwd
                        , storeName, storePhoneNo
                        , storeType, proportion
                        , provice, city
                        , area, addressDetail
                        , inviteCode, legalName
                        , certificatesType, certificatesNo
                        , imgFront, imgBack
//                        , imgLicense, managerName
                        , imgLicense
                        , managerPhoneNo, verCode);
        toOtherSubscribe(observable, subscriber);
    }

    /**
     * 忘记账号找回   <br/>
     *
     * @param shop_name           商家名称 <br/>
     * @param document_name       法人姓名 <br/>
     * @param certificates_type   证件类型 <br/>
     * @param certificates_number 证件号码 <br/>
     * @param ID_front_img        身份证正面照片 <br/>
     * @param ID_back_img         身份证反面照片 <br/>
     * @param license_img         营业执照 <br/>
     * @param phone               预留手机号 <br/>
     * @param checkcode           验证码 <br/>
     */
    public void forgetAccountGetBack(Subscriber<Object> subscriber,
                                     String shop_name, String document_name,
                                     String certificates_type, String certificates_number,
                                     String ID_front_img, String ID_back_img,
                                     String license_img, String phone, String checkcode) {

        Observable observable = initService()
                .forgetAccountGetBack(System.currentTimeMillis() + "", "11f61fb9463de1e6ec9c9a5bd9e146db"
                        , shop_name, document_name
                        , certificates_type, certificates_number
                        , ID_front_img, ID_back_img
                        , license_img, phone
                        , checkcode);
        toOtherSubscribe(observable, subscriber);
    }


    /**
     * 忘记账号找回   <br/>
     *
     * @param old_phone     旧手机号 <br/>
     * @param old_checkcode 旧手机号验证码 <br/>
     * @param new_phone     新手机号 <br/>
     * @param new_checkcode 新手机号验证码 <br/>
     */
    public void changManagerPhone(Subscriber<Object> subscriber,
                                  String old_phone, String old_checkcode,
                                  String new_phone, String new_checkcode) {
        Observable observable = initService()
                .changManagerPhone(System.currentTimeMillis() + "", "f5accd29f3dc15b5b9b88d07cf423bd7", DataCenter.UserId
                        , old_phone, old_checkcode
                        , new_phone, new_checkcode);
        toOtherSubscribe(observable, subscriber);
    }


    /**
     * 检测商户名称是是否唯一
     *
     * @param subscriber 观察者
     */
    public void checkUsrName(Subscriber<HttpRespBean> subscriber, String userNmae) {
        Observable observable = initService().checkUsrnameIsUnique(System.currentTimeMillis() + "", "35503c34c1bd394162d8f7e1df257b41", userNmae);
        toSubscribe(observable, subscriber);
    }


    //    @FormUrlEncoded
//    @POST("sendcode.html")
//    Observable<Object> sendcode(@Field("time") String hash, @Field("hash") String time,
//                                @Field("mobile") String uid, @Field("checktype") int password
//    );}
    public void resetUserPwd(Subscriber<HttpRespBean> subscriber, String userNmae, String pwd) {
//        Observable observable = initService().resetPwd(System.currentTimeMillis() + "","3c437a1b469dc67c1e1a804b3a00270b",userNmae,pwd);
        Observable observable = initService().resetPwd(System.currentTimeMillis() + "", "3c437a1b469dc67c1e1a804b3a00270b", userNmae, pwd);
        toSubscribe(observable, subscriber);
    }

    /**
     * 活动列表
     *
     * @param subscriber
     * @param page
     */
    public void getActiveListData(Subscriber<HttpRespBean<ActiveListEntity>> subscriber, int page) {
        Observable observable = initService().getActiveListData(System.currentTimeMillis() + "",
                "4b50512c9c732419a0d992ab9cd202bc", DataCenter.UserId);
        toSubscribe(observable, subscriber);
    }


    /**
     *  设置消费功能开或者关
     * @param subscriber
     * @param reqList
     * @param managementLimit
     */
    public void setFunctionOpenOrClose(Subscriber<Object> subscriber, List<String> reqList, String managementLimit) {
        Observable observable = initService().setFunctionOpenOrClose(System.currentTimeMillis() + "", Constants.getHash(reqList), DataCenter.UserId,managementLimit);
        toOtherSubscribe(observable, subscriber);
    }

    /**
     * 检查App版本更新   <br/>
     *
     * @param types        安卓或苹果（1、ios，2、Android）（必填）   <br/>
     * @param is_shop      商家端或客户端（1、商家端，2、客户端）（必填）   <br/>
     * @param version_code 版本号（必填）   <br/>
     * @return
     */
    public void checkAppVersion(Subscriber<AppVersionEntity> subscriber, List<String> reqList, String types, String is_shop, String version_code) {
        Observable observable = initService().checkAppVersion(System.currentTimeMillis() + "", Constants.getHash(reqList), types,
                is_shop, version_code);
        toSubscribe(observable, subscriber);
    }

    /**
     *  商家个人活动列表   <br/>
     * @param subscriber          <br/>
     * @param reqList       请求参数的集合  <br/>
     */
    public void getShopAllActivesList(Subscriber<ShopShowsEntity> subscriber, List<String> reqList) {
        Observable observable = initService().getShopActivesList(System.currentTimeMillis() + "", Constants.getHash(reqList), DataCenter.UserId+"");
        toSubscribe(observable, subscriber);
    }
    /**
     *  商家新增个人活动   <br/>
     * @param subscriber          <br/>
     * @param reqList       请求参数的集合  <br/>
     * @param activityDesc    商家活动标题       <br/>
     * @param activityInfo  商家活动内容          <br/>
     */
    public void addNewActive(Subscriber<Object> subscriber, List<String> reqList, String activityDesc, String activityInfo) {
        Observable observable = initService().addNewActive(System.currentTimeMillis() + "", Constants.getHash(reqList), DataCenter.UserId+"",
                activityDesc, activityInfo);
        toOtherSubscribe(observable, subscriber);
    }

    /**
     *  商家个人活动删除接口   <br/>
     * @param subscriber          <br/>
     * @param reqList       请求参数的集合  <br/>
     * @param aId    商家活动id       <br/>
     */
    public void deletShopActive(Subscriber<Object> subscriber, List<String> reqList, String aId) {
        Observable observable = initService().deletShopActivie(System.currentTimeMillis() + "", Constants.getHash(reqList), aId);
        toOtherSubscribe(observable, subscriber);
    }
}