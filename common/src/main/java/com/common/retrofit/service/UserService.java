package com.common.retrofit.service;

import com.common.retrofit.entity.result.AboutBean;
import com.common.retrofit.entity.result.ActiveListEntity;
import com.common.retrofit.entity.result.AddressBean;
import com.common.retrofit.entity.result.AddressResBean;
import com.common.retrofit.entity.result.AppVersionEntity;
import com.common.retrofit.entity.result.ApplyRecodEntity;
import com.common.retrofit.entity.result.BankListBean;
import com.common.retrofit.entity.result.BankNameBean;
import com.common.retrofit.entity.result.CCBean;
import com.common.retrofit.entity.result.ChangeBean;
import com.common.retrofit.entity.result.CheckRecordEntity;
import com.common.retrofit.entity.result.CheckTicketsDetailsEntity;
import com.common.retrofit.entity.result.CheckTicketsResultEntity;
import com.common.retrofit.entity.result.ChooseFLBean;
import com.common.retrofit.entity.result.ConsumeFunctionEntity;
import com.common.retrofit.entity.result.ConsumeRightsEntity;
import com.common.retrofit.entity.result.DPDABean;
import com.common.retrofit.entity.result.FBean;
import com.common.retrofit.entity.result.FLBean;
import com.common.retrofit.entity.result.FLTwoBean;
import com.common.retrofit.entity.result.FinanceDetailEntity;
import com.common.retrofit.entity.result.FiniBean;
import com.common.retrofit.entity.result.GGBean;
import com.common.retrofit.entity.result.GoodCBean;
import com.common.retrofit.entity.result.GoodCTwoBean;
import com.common.retrofit.entity.result.GoodDBean;
import com.common.retrofit.entity.result.HomeOneBean;
import com.common.retrofit.entity.result.IndexBean;
import com.common.retrofit.entity.result.InfoBean;
import com.common.retrofit.entity.result.JFBean;
import com.common.retrofit.entity.result.MsgBena;
import com.common.retrofit.entity.result.MyOrderBean;
import com.common.retrofit.entity.result.MyZBList;
import com.common.retrofit.entity.result.MyzjBean;
import com.common.retrofit.entity.result.NewTestBean;
import com.common.retrofit.entity.result.PayBeansss;
import com.common.retrofit.entity.result.PaySnBean;
import com.common.retrofit.entity.result.PicBean;
import com.common.retrofit.entity.result.QRDDBean;
import com.common.retrofit.entity.result.RelationShipListEntity;
import com.common.retrofit.entity.result.SCListBean;
import com.common.retrofit.entity.result.SHBean;
import com.common.retrofit.entity.result.SPDetilBean;
import com.common.retrofit.entity.result.SPFLBean;
import com.common.retrofit.entity.result.SPListBean;
import com.common.retrofit.entity.result.SPPJBean;
import com.common.retrofit.entity.result.ShopCarBean;
import com.common.retrofit.entity.result.ShopShowsEntity;
import com.common.retrofit.entity.result.ThreeBean;
import com.common.retrofit.entity.result.TimeBean;
import com.common.retrofit.entity.result.TokenBean;
import com.common.retrofit.entity.result.TwoBean;
import com.common.retrofit.entity.result.UserBean;
import com.common.retrofit.entity.result.UserInfoBean;
import com.common.retrofit.entity.result.WallBean;
import com.common.retrofit.entity.result.WidthDrawEntity;
import com.common.retrofit.entity.result.WithdrawlBillEntity;
import com.common.retrofit.entity.result.YSBean;
import com.common.retrofit.entity.result.ZBDetil;
import com.common.retrofit.entity.result.ZBDetilBean;
import com.common.retrofit.entity.result.ZBListBean;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.model.DayFlowListDto;
import com.common.retrofit.model.Home;
import com.common.retrofit.model.Pie;
import com.common.retrofit.model.RevenueStatistics;
import com.common.retrofit.model.TodayRevenue;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import rx.Observable;

public interface UserService {


    @Multipart
    @POST("uploadPicture.html")
    Observable<PicBean> uploadFace(@PartMap Map<String, RequestBody> params, @Part List<MultipartBody.Part> file);

    @FormUrlEncoded
    @POST("getqiniutoken.html")
    Observable<HttpRespBean<TokenBean>> getqiniutoken(@Field("hash") String hash, @Field("time") String time,
                                                      @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                      @Field("uid") int uid, @Field("hashid") String password);

    @FormUrlEncoded
    @POST("userDetail.html")
    Observable<HttpRespBean<UserInfoBean>> userDetail(@Field("hash") String hash, @Field("time") String time,
                                                      @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                      @Field("uid") int uid, @Field("hashid") String password);

    @FormUrlEncoded
    @POST("bankList.html")
    Observable<HttpRespBean<BankListBean>> bankList(@Field("time") String hash, @Field("hash") String time
            , @Field("uid") int uid, @Field("hashid") String hashid, @Field("page") int page);

    @FormUrlEncoded
    @POST("delBank.html")
    Observable<Object> delBank(@Field("time") String hash, @Field("hash") String time
            , @Field("uid") int uid, @Field("hashid") String hashid, @Field("id") String page);


//    /**
//     *  添加对私银行
//     * @param branch_bank  支行名称  <br/>
//     * @param card_number  银行卡账号  <br/>
//     * @param phone      手机<br/>
//     * @param ID_card   身份证<br/>
//     * @param user_name   持卡人名称<br/>
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("addBank.html")
//    Observable<Object> addBank(@Field("time") String time, @Field("hash") String hash, @Field("uid") int uid,
//                                @Field("branch_bank") String branch_bank, @Field("card_number") String card_number,
//                               @Field("phone") String phone, @Field("ID_card") String ID_card, @Field("user_name") String user_name);

    /**
     * 添加对私银行
     *
     * @param branch_bank 支行名称  <br/>
     * @param card_number 银行卡账号  <br/>
     * @param phone       手机<br/>
     * @param ID_card     身份证<br/>
     * @param user_name   持卡人名称<br/>
     * @return
     */
    @FormUrlEncoded
    @POST("addBank_new.html")
    Observable<Object> addBank(@Field("time") String time, @Field("hash") String hash, @Field("uid") int uid,
                               @Field("branch_bank") String branch_bank, @Field("card_number") String card_number,
                               @Field("phone") String phone, @Field("ID_card") String ID_card, @Field("user_name") String user_name);


    /**
     * 添加对公银行卡  <br/>
     *
     * @param time        <br/>
     * @param hash        <br/>
     * @param uid         <br/>
     * @param card_number 银行卡账号       <br/>
     * @param branch_bank 支行名称      <br/>
     * @param bank_id     发卡银行    <br/>
     * @param user_name   公司名称   <br/>
     * @return
     */
    @FormUrlEncoded
    @POST("addPublicBank.html")
    Observable<Object> addBankCardToCompany(@Field("time") String time, @Field("hash") String hash, @Field("uid") int uid,
                                            @Field("card_number") String card_number, @Field("branch_bank") String branch_bank,
                                            @Field("bank_id") String bank_id, @Field("user_name") String user_name);

    @FormUrlEncoded
    @POST("bankType.html")
    Observable<BankNameBean> bankType(@Field("time") String hash, @Field("hash") String time
    );

    @FormUrlEncoded
    @POST("scategory.html")
    Observable<NewTestBean> scategory(@Field("time") String time, @Field("hash") String hash);

//    /**
//     * shop_name (请输入门店名称 String类型 必填)
//     * category_id (请选择门店类型 Int类型 必填)
//     * province (请选择省 String类型 必填)
//     * area (请选择市 String类型 必填)
//     * county (请选择区 String类型 必填)
//     * address (请输入详细地址 String类型 必填)
//     * desc (请输入店家详情介绍 String类型 必填)
//     * face (请上传店头照片 String类型 必填)
//     * shop_pic (请上传店家照片 String类型 必填)
//     */
//    @FormUrlEncoded
//    @POST("shopInfoSubmit.html")
//    Observable<Object> shopInfoSubmit(@Field("time") String time, @Field("hash") String hash, @Field("uid") int uid
//            , @Field("hashid") String hashid, @Field("shop_name") String shop_name, @Field("category_id") String category_id
//            , @Field("province") String province, @Field("area") String area, @Field("county") String county
//            , @Field("address") String address, @Field("desc") String desc, @Field("face") String face

    /**
     * 提交编辑后的店铺信息（新接口）
     * shop_name (请输入门店名称 String类型 必填)
     * category_id (请选择门店类型 Int类型 必填)
     * province (请选择省 String类型 必填)
     * area (请选择市 String类型 必填)
     * county (请选择区 String类型 必填)
     * address (请输入详细地址 String类型 必填)
     * desc (请输入店家详情介绍 String类型 必填)
     * face (请上传店头照片 String类型 必填)
     * shop_pic (请上传店家照片 String类型 必填)
     */
//    @POST("shopInfoSubmitNew.html")
    @FormUrlEncoded
    @POST("shopInfoSubmitNewest.html")
    Observable<Object> shopInfoSubmit(@Field("time") String time, @Field("hash") String hash, @Field("uid") int uid
            , @Field("hashid") String hashid, @Field("shop_name") String shop_name, @Field("category_id") String category_id
            , @Field("province") String province, @Field("area") String area, @Field("county") String county
            , @Field("address") String address, @Field("desc") String desc, @Field("face") String face, @Field("mobile") String mobile
    );

//            , @Field("shop_pic") String shop_pic, @Field("latitude") String latitude, @Field("longitude") String longitude);


    /**
     * 商户图片上传
     *
     * @param time      time
     * @param hash      hash
     * @param uid       uid
     * @param hashid    hasid
     * @param shop_name 上传的图片集合
     * @return
     */
    @FormUrlEncoded
    @POST("shopPicUpdate.html")
    Observable<Object> updateShopImges(@Field("time") String time, @Field("hash") String hash, @Field("uid") int uid
            , @Field("hashid") String hashid, @Field("shop_pic") String shop_name);

    @FormUrlEncoded
    @POST("storeSubmit.html")
    Observable<Object> storeSubmit(@Field("time") String hash, @Field("hash") String time, @Field("uid") int uid
            , @Field("hashid") String hashid, @Field("id") String id, @Field("xinlidou") String xinlidou
            , @Field("xianglidou") String xianglidou);

    @FormUrlEncoded
    @POST("withdraw.html")
    Observable<HttpRespBean<WidthDrawEntity>> withdraws(@Field("time") String time, @Field("hash") String hash, @Field("uid") int uid
            , @Field("hashid") String hashid, @Field("money") String money, @Field("card_id") String card_id
            , @Field("proportion") String proportion);


    /**
     * 获取财务信息--列表信息
     *
     * @param hash
     * @param time
     * @param uid
     * @param hashid
     * @param page
     * @return
     */
    @FormUrlEncoded
    @POST("financeList.html")
    Observable<HttpRespBean<FiniBean>> financeList(@Field("time") String hash, @Field("hash") String time
            , @Field("uid") int uid, @Field("hashid") String hashid, @Field("page") int page);

    /**
     * 获取首页数据
     *
     * @param hash
     * @param time
     * @param uid
     * @param hashid
     * @return
     */
    @FormUrlEncoded
    @POST("index.html")
    Observable<HttpRespBean<IndexBean>> index(@Field("time") String time,
                                              @Field("hash") String hash,
                                              @Field("uid") int uid,
                                              @Field("hashid") String hashid);


    /**
     * 忘记密码
     *
     * @param hash                hash
     * @param time                time
     * @param username            商家账号
     * @param shop_name           商家名称
     * @param document_name       法人姓名
     * @param certificates_type   证件类型
     * @param certificates_number 证件号
     * @param phone               手机号
     * @param pcode               手机验证码
     * @return
     */
    @FormUrlEncoded
    @POST("shopRetrievePwd.html")
//    Observable<HttpRespBean<IndexBean>> forgetPwd(@Field("time") String hash, @Field("hash") String time,
    Observable<HttpRespBean> forgetPwd(@Field("time") String time, @Field("hash") String hash,
                                       @Field("username") String username, @Field("shop_name") String shop_name,
                                       @Field("document_name") String document_name, @Field("certificates_type") String certificates_type,
                                       @Field("certificates_number") String certificates_number, @Field("phone") String phone, @Field("pcode") String pcode);

    /**
     * 重置密码
     *
     * @param username 商家用户名
     * @return
     */
    @FormUrlEncoded
    @POST("shopResetPwd.html")
    Observable<HttpRespBean> resetPwd(@Field("time") String time, @Field("hash") String hash, @Field("username") String username, @Field("password") String password);


    /**
     * 消费账单详情
     *
     * @param hash
     * @param time
     * @param uid
     * @param hashid
     * @return
     */
    @FormUrlEncoded
    @POST("financeDetail.html")
    Observable<HttpRespBean<FBean>> financeDetail(@Field("time") String time, @Field("hash") String hash, @Field("uid") int uid
            , @Field("hashid") String hashid);

    /**
     * 拉取消费抵账鑫豆记录清单(新的返回值类型)
     *
     * @param hash
     * @param time
     * @param uid
     * @param hashid
     * @return
     */
    @FormUrlEncoded
    @POST("financeDetail.html")
    Observable<HttpRespBean<FinanceDetailEntity>> financeDetailNew(@Field("time") String time, @Field("hash") String hash, @Field("uid") int uid
//    Observable<Object> financeDetailNew(@Field("time") String time, @Field("hash") String hash, @Field("uid") int uid
            , @Field("hashid") String hashid);


//    @FormUrlEncoded
//    @POST("shopInfo.html")
//    Observable<HttpRespBean<InfoBean>> shopInfo(@Field("time") String hash, @Field("hash") String time, @Field("uid") int uid
//            , @Field("hashid") String hashid);


    /**
     * 获取商家信息新接口
     *
     * @param hash
     * @param time
     * @param uid
     * @param hashid
     * @return
     */
    @FormUrlEncoded
    @POST("shopInfoNew.html")
    Observable<HttpRespBean<InfoBean>> shopInfo(@Field("time") String time, @Field("hash") String hash, @Field("uid") int uid
            , @Field("hashid") String hashid);


    @FormUrlEncoded
    @POST("store.html")
    Observable<HttpRespBean<ChangeBean>> store(@Field("time") String hash, @Field("hash") String time, @Field("uid") int uid
            , @Field("hashid") String hashid);


    /**
     * 检测商户账户名是否唯一的
     *
     * @param username 商家用户名
     * @return
     */
    @FormUrlEncoded
    @POST("shopUsername.html")
//    Observable<String> checkUsrnameIsUnique(@Field("username") String username);
    Observable<HttpRespBean> checkUsrnameIsUnique(@Field("time") String time, @Field("hash") String hash, @Field("username") String username);


    /**
     * 发送手机短信验证码  <br/>
     *
     * @param time      time  <br/>
     * @param hash      hash  <br/>
     * @param mobile    手机号码  <br/>
     * @param checktype type类型  <br/>
     *                  type类型：  <br/>1 为注册。  <br/>2 为找回密码。  <br/>3 手机短信登录。  <br/>4 解除绑定。  <br/>5 短信绑定（手机绑定）  <br/>6 绑定第三方。
     * @return
     */
    @FormUrlEncoded
    @POST("sendcode.html")
    Observable<HttpRespBean> sendCode(@Field("time") String time, @Field("hash") String hash,
                                      @Field("mobile") String mobile, @Field("checktype") String checktype);


    // 原新商家入驻会员接口
//    @FormUrlEncoded
//    @POST("apply.html")
//    Observable<Object> apply(@Field("time") String hash, @Field("hash") String time, @Field("account_person") String account_person
//            , @Field("bank_account") String bank_account, @Field("name") String name, @Field("mobile") String mobile,
//                             @Field("category_id") String category_id, @Field("proportion") String proportion
//            , @Field("province") String province, @Field("area") String area, @Field("county") String county, @Field("address") String address,
//                             @Field("phone") String phone, @Field("password") String password, @Field("code") String code,
//                             @Field("certificates_type") String certificates_type, @Field("document_name") String document_name,
//                             @Field("certificates_number") String certificates_number, @Field("ID_front_img") String ID_front_img, @Field("ID_back_img") String ID_back_img,
//                             @Field("license_img") String license_img, @Field("latitude") String latitude, @Field("longitude") String longitude);


    /**
     * 新商家会员入驻接口   <br/>
     *
     * @param time                时间   <br/>
     * @param hash                hashId   <br/>
     * @param username            商家账号   <br/>
     * @param password            密码   <br/>
     * @param shop_name           商家名称   <br/>
     * @param mobile              营业电话   <br/>
     * @param category_id         商户类型   <br/>
     * @param proportion          提拨比例   <br/>
     * @param province            省   <br/>
     * @param area                市   <br/>
     * @param county              区   <br/>
     * @param address             详细地址   <br/>
     * @param code                邀请码   <br/>
     * @param document_name       法人姓名   <br/>
     * @param certificates_type   证件类型   <br/>
     * @param certificates_number 证件号   <br/>
     * @param ID_front_img        正面照片   <br/>
     * @param ID_back_img         反面照片   <br/>
     * @param license_img         营业执照   <br/>
     *                            //     * @param name          法人授权代理人姓名   <br/>
     * @param phone               手机号   <br/>
     * @param pcode               手机验证码   <br/>
     * @return
     */
//    @FormUrlEncoded
//    @POST("apply.html")
    @FormUrlEncoded
    @POST("apply.html")
    Observable<Object> newMerhcantApplyJoin(@Field("time") String time, @Field("hash") String hash
            , @Field("username") String username, @Field("password") String password
            , @Field("shop_name") String shop_name, @Field("mobile") String mobile
            , @Field("category_id") String category_id, @Field("proportion") String proportion
            , @Field("province") String province, @Field("area") String area
            , @Field("county") String county, @Field("address") String address
            , @Field("code") String code, @Field("document_name") String document_name
            , @Field("certificates_type") String certificates_type, @Field("certificates_number") String certificates_number
            , @Field("ID_front_img") String ID_front_img, @Field("ID_back_img") String ID_back_img
//            , @Field("license_img") String license_img, @Field("name") String name
            , @Field("license_img") String license_img
            , @Field("phone") String phone, @Field("pcode") String pcode);
//            ,@Field("latitude") String latitude, @Field("longitude") String longitude);
//     * @param latitude
//     * @param longitude


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
    @FormUrlEncoded
    @POST("retrieveUsername.html")
    Observable<Object> forgetAccountGetBack(@Field("time") String time, @Field("hash") String hash
            , @Field("shop_name") String shop_name, @Field("document_name") String document_name
            , @Field("certificates_type") String certificates_type, @Field("certificates_number") String certificates_number
            , @Field("ID_front_img") String ID_front_img, @Field("ID_back_img") String ID_back_img
            , @Field("license_img") String license_img
            , @Field("phone") String phone, @Field("checkcode") String checkcode);


    /**
     * 更换管理员手机号码   <br/>
     *
     * @param old_phone     旧手机号 <br/>
     * @param old_checkcode 旧手机号验证码 <br/>
     * @param new_phone     新手机号 <br/>
     * @param new_checkcode 新手机号验证码 <br/>
     */
    @FormUrlEncoded
    @POST("changePhone.html")
    Observable<Object> changManagerPhone(@Field("time") String time, @Field("hash") String hash, @Field("uid") int uid
            , @Field("old_phone") String old_phone, @Field("old_checkcode") String old_checkcode
            , @Field("new_phone") String new_phone, @Field("new_checkcode") String new_checkcode);


    @FormUrlEncoded
    @POST("delData.html")
    Observable<Object> delDatam(@Field("hash") String hash, @Field("time") String time,
                                @Field("apiId") String apiId, @Field("terminal") int terminal,
                                @Field("uid") int uid, @Field("hashid") String password, @Field("id") String id);

    @FormUrlEncoded
    @POST("withdraw.html")
    Observable<Object> withdraw(@Field("hash") String hash, @Field("time") String time,
                                @Field("apiId") String apiId, @Field("terminal") int terminal,
                                @Field("uid") int uid, @Field("hashid") String password, @Field("name") String name
            , @Field("code") String code, @Field("money") String money, @Field("type") int type);

    @FormUrlEncoded
    @POST("auth.html")
    Observable<Object> auth(@Field("hash") String hash, @Field("time") String time,
                            @Field("apiId") String apiId, @Field("terminal") int terminal,
                            @Field("uid") int uid, @Field("hashid") String password, @Field("type") int type, @Field("img") String img);

    @FormUrlEncoded
    @POST("browsehistory.html")
    Observable<HttpRespBean<MyzjBean>> browsehistory(@Field("hash") String hash, @Field("time") String time,
                                                     @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                     @Field("uid") int uid, @Field("hashid") String password, @Field("page") int page);

    @FormUrlEncoded
    @POST("listdata.html")
    Observable<HttpRespBean<JFBean>> listdataa(@Field("hash") String hash, @Field("time") String time,
                                               @Field("apiId") String apiId, @Field("terminal") int terminal,
                                               @Field("uid") int uid, @Field("hashid") String password, @Field("page") int page, @Field("search") String search);

    @FormUrlEncoded
    @POST("sign.html")
    Observable<Object> sign(@Field("hash") String hash, @Field("time") String time,
                            @Field("apiId") String apiId, @Field("terminal") int terminal,
                            @Field("uid") int uid, @Field("hashid") String password);

    @FormUrlEncoded
    @POST("caserecord.html")
    Observable<HttpRespBean<WallBean>> caserecord(@Field("hash") String hash, @Field("time") String time,
                                                  @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                  @Field("uid") int uid, @Field("hashid") String password, @Field("page") int page, @Field("is_shop") String is_shop);

    @FormUrlEncoded
    @POST("liveOrder.html")
    Observable<HttpRespBean<PayBeansss>> liveOrder(@Field("hash") String hash, @Field("time") String time,
                                                   @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                   @Field("uid") int uid, @Field("hashid") String password, @Field("live_id") String live_id
            , @Field("pay_type") int page);

    @FormUrlEncoded
    @POST("rechargeorder.html")
    Observable<HttpRespBean<PayBeansss>> rechargeorder(@Field("hash") String hash, @Field("time") String time,
                                                       @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                       @Field("uid") int uid, @Field("hashid") String password, @Field("money") String money
            , @Field("pay_type") int page);

    @FormUrlEncoded
    @POST("focus.html")
    Observable<Object> focus(@Field("hash") String hash, @Field("time") String time,
                             @Field("apiId") String apiId, @Field("terminal") int terminal,
                             @Field("uid") int uid, @Field("hashid") String password, @Field("shop_id") String page);

    @FormUrlEncoded
    @POST("listData.html")
    Observable<HttpRespBean<AboutBean>> listDataf(@Field("hash") String hash, @Field("time") String time,
                                                  @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                  @Field("uid") int uid, @Field("hashid") String password, @Field("page") int page);

    @FormUrlEncoded
    @POST("listData.html")
    Observable<HttpRespBean<MsgBena>> listDataM(@Field("hash") String hash, @Field("time") String time,
                                                @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                @Field("uid") int uid, @Field("hashid") String password, @Field("page") int page);

    @FormUrlEncoded
    @POST("listData.html")
    Observable<HttpRespBean<SCListBean>> listDatasss(@Field("hash") String hash, @Field("time") String time,
                                                     @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                     @Field("uid") int uid, @Field("hashid") String password, @Field("page") int page);

    @FormUrlEncoded
    @POST("saveData.html")
    Observable<Object> saveDatassss(@Field("hash") String hash, @Field("time") String time,
                                    @Field("apiId") String apiId, @Field("terminal") int terminal,
                                    @Field("uid") int uid, @Field("hashid") String password, @Field("goods_id") String goods_id);

    @FormUrlEncoded
    @POST("confirmOrder.html")
    Observable<HttpRespBean<QRDDBean>> confirmOrder(@Field("hash") String hash, @Field("time") String time,
                                                    @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                    @Field("uid") int uid, @Field("hashid") String password, @Field("data") String data);

    @FormUrlEncoded
    @POST("goodsOrder.html")
    Observable<HttpRespBean<PaySnBean>> goodsOrder(@Field("hash") String hash, @Field("time") String time,
                                                   @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                   @Field("uid") int uid, @Field("hashid") String password, @Field("address_id") String address_id,
                                                   @Field("data") String data);

    @FormUrlEncoded
    @POST("reply.html")
    Observable<Object> reply(@Field("hash") String hash, @Field("time") String time,
                             @Field("apiId") String apiId, @Field("terminal") int terminal,
                             @Field("uid") int uid, @Field("hashid") String password, @Field("id") String address_id,
                             @Field("reply") String reply);

    @FormUrlEncoded
    @POST("listData.html")
    Observable<HttpRespBean<MyOrderBean>> userList(@Field("hash") String hash, @Field("time") String time,
                                                   @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                   @Field("uid") int uid, @Field("hashid") String password, @Field("type") String address_id, @Field("is_shop") String is_shop,
                                                   @Field("page") int data);

    @FormUrlEncoded
    @POST("pay.html")
    Observable<HttpRespBean<PayBeansss>> pay(@Field("hash") String hash, @Field("time") String time,
                                             @Field("apiId") String apiId, @Field("terminal") int terminal,
                                             @Field("uid") int uid, @Field("hashid") String password, @Field("data") String address_id, @Field("is_sn") String is_shop,
                                             @Field("pay_type") int data);

    @FormUrlEncoded
    @POST("getOrderComments.html")
    Observable<HttpRespBean<SPPJBean>> getOrderComments(@Field("hash") String hash, @Field("time") String time,
                                                        @Field("apiId") String apiId, @Field("terminal") int terminal, @Field("search") String address_id, @Field("goods_id") String good_is,
                                                        @Field("uid") int data, @Field("is_shop") String is_shop, @Field("page") int page);

    @FormUrlEncoded
    @POST("cancelOrder.html")
    Observable<Object> cancelOrder(@Field("hash") String hash, @Field("time") String time,
                                   @Field("apiId") String apiId, @Field("terminal") int terminal,
                                   @Field("uid") int uid, @Field("hashid") String password, @Field("id") String address_id, @Field("field") String is_shop,
                                   @Field("value") String data);

    @FormUrlEncoded
    @POST("logistics.html")
    Observable<Object> logistics(@Field("hash") String hash, @Field("time") String time,
                                 @Field("apiId") String apiId, @Field("terminal") int terminal,
                                 @Field("uid") int uid, @Field("hashid") String password, @Field("order_id") String address_id, @Field("com") String is_shop,
                                 @Field("code") String data);

    @FormUrlEncoded
    @POST("category.html")
    Observable<ChooseFLBean> category(@Field("hash") String hash, @Field("time") String time,
                                      @Field("apiId") String apiId, @Field("terminal") int terminal,
                                      @Field("uid") int uid, @Field("hashid") String password);

    @FormUrlEncoded
    @POST("categoryPage.html")
    Observable<HttpRespBean<FLBean>> categoryPage(@Field("hash") String hash, @Field("time") String time,
                                                  @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                  @Field("page") int uid, @Field("search") String password);

    @FormUrlEncoded
    @POST("getSecondCate.html")
    Observable<HttpRespBean<FLTwoBean>> getSecondCate(@Field("hash") String hash, @Field("time") String time,
                                                      @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                      @Field("page") int uid, @Field("search") String password, @Field("pid") String id);

    @FormUrlEncoded
    @POST("getClothesSize.html")
    Observable<HttpRespBean<CCBean>> getClothesSize(@Field("hash") String hash, @Field("time") String time,
                                                    @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                    @Field("uid") int uid, @Field("hashid") String password);

    @FormUrlEncoded
    @POST("getclothescolor.html")
    Observable<HttpRespBean<YSBean>> getclothescolor(@Field("hash") String hash, @Field("time") String time,
                                                     @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                     @Field("uid") int uid, @Field("hashid") String password);
//
//    @FormUrlEncoded
//    @POST("saveUserDetailData.html")
//    Observable<Object> saveUserDetailData(@Field("hash") String hash, @Field("time") String time,
//                                          @Field("apiId") String apiId, @Field("terminal") int terminal,
//                                          @Field("uid") int uid, @Field("hashid") String password, @Field("update") String update);
//
//    @FormUrlEncoded
//    @POST("sendCode.html")
//    Observable<Object> sendCode(@Field("time") String time,@Field("hash") String hash,
//                                @Field("apiId") String apiId, @Field("terminal") int terminal,
//                                @Field("mobile") String mobile, @Field("type") int type);


    /**
     * shop_face ,shop_img,title,person,tel,province_id,city_id,area_id,address,floor,sell_sn,arbitration_name,
     * arbitration_sn,arbitration_img,main_type,id_cart_1,id_cart_2,shop_type,id;
     **/
    @FormUrlEncoded
    @POST("saveData.html")
    Observable<Object> saveData(@Field("hash") String hash, @Field("time") String time,
                                @Field("apiId") String apiId, @Field("terminal") int terminal,
                                @Field("uid") int uid, @Field("hashid") String password, @Field("shop_face") String shop_face
            , @Field("shop_img") String shop_img, @Field("title") String title, @Field("person") String person
            , @Field("tel") String tel, @Field("province_id") String province_id, @Field("city_id") String city_id
            , @Field("area_id") String area_id, @Field("address") String address, @Field("floor") String floor
            , @Field("sell_sn") String sell_sn, @Field("arbitration_name") String arbitration_name, @Field("arbitration_sn") String arbitration_sn
            , @Field("arbitration_img") String arbitration_img, @Field("main_type") String main_type, @Field("id_cart_1") String id_cart_1
            , @Field("id_cart_2") String id_cart_2, @Field("shop_type") String shop_type, @Field("id") String id);

    @FormUrlEncoded
    @POST("index.html")
    Observable<HttpRespBean<HomeOneBean>> index(@Field("hash") String hash, @Field("time") String time,
                                                @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                @Field("no") String id);

    @FormUrlEncoded
    @POST("shop.html")
    Observable<HttpRespBean<TwoBean>> shop(@Field("hash") String hash, @Field("time") String time,
                                           @Field("apiId") String apiId, @Field("terminal") int terminal,
                                           @Field("page") int id, @Field("keyword") String keyword, @Field("category") String category);

    @FormUrlEncoded
    @POST("factory.html")
    Observable<HttpRespBean<ThreeBean>> factory(@Field("hash") String hash, @Field("time") String time,
                                                @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                @Field("page") int id, @Field("keyword") String keyword, @Field("category") String category
            , @Field("uid") int uid);

    @FormUrlEncoded
    @POST("details.html")
    Observable<HttpRespBean<SHBean>> details(@Field("hash") String hash, @Field("time") String time,
                                             @Field("apiId") String apiId, @Field("terminal") int terminal,
                                             @Field("uid") int uid, @Field("hashid") String password, @Field("id") String id);

    @FormUrlEncoded
    @POST("noticeSave.html")
    Observable<Object> noticeSave(@Field("hash") String hash, @Field("time") String time,
                                  @Field("apiId") String apiId, @Field("terminal") int terminal,
                                  @Field("uid") int uid, @Field("hashid") String password, @Field("notice") String id);

    @FormUrlEncoded
    @POST("noticedetail.html")
    Observable<HttpRespBean<GGBean>> noticedetail(@Field("hash") String hash, @Field("time") String time,
                                                  @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                  @Field("uid") int uid, @Field("hashid") String password);

    @FormUrlEncoded
    @POST("shoparchive.html")
    Observable<HttpRespBean<DPDABean>> shoparchive(@Field("hash") String hash, @Field("time") String time,
                                                   @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                   @Field("uid") int uid, @Field("shop_id") String id);

    @FormUrlEncoded
    @POST("shopDetail.html")
    Observable<HttpRespBean<GoodDBean>> shopDetail(@Field("hash") String hash, @Field("time") String time,
                                                   @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                   @Field("uid") int uid, @Field("search") String password, @Field("id") String id, @Field("page") int page);

    @FormUrlEncoded
    @POST("factorydetail.html")
    Observable<HttpRespBean<GoodCBean>> factorydetail(@Field("hash") String hash, @Field("time") String time,
                                                      @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                      @Field("uid") int uid, @Field("search") String password
            , @Field("type") String type, @Field("id") String id, @Field("page") int page);

    @FormUrlEncoded
    @POST("factorynew.html")
    Observable<HttpRespBean<GoodCTwoBean>> factorynew(@Field("hash") String hash, @Field("time") String time,
                                                      @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                      @Field("search") String password
            , @Field("id") String id, @Field("page") int page);

    @FormUrlEncoded
    @POST("shopgoods.html")
    Observable<HttpRespBean<SPListBean>> shopgoods(@Field("hash") String hash, @Field("time") String time,
                                                   @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                   @Field("shop_id") String shopid
            , @Field("category_id") String id, @Field("page") int page, @Field("search") String password);

    @FormUrlEncoded
    @POST("shopcategory.html")
    Observable<HttpRespBean<SPFLBean>> shopcategory(@Field("hash") String hash, @Field("time") String time,
                                                    @Field("apiId") String apiId, @Field("terminal") int terminal,

                                                    @Field("shop_id") String id, @Field("page") int page);

    @FormUrlEncoded
    @POST("listData.html")
    Observable<HttpRespBean<SPListBean>> listDatas(@Field("hash") String hash, @Field("time") String time,
                                                   @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                   @Field("uid") int uid, @Field("type") String type
            , @Field("page") int page, @Field("keyword") String keyword);

    @FormUrlEncoded
    @POST("detailData.html")
    Observable<HttpRespBean<SPDetilBean>> detailData(@Field("hash") String hash, @Field("time") String time,
                                                     @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                     @Field("uid") int uid, @Field("id") String id);

    @FormUrlEncoded
    @POST("listData.html")
    Observable<HttpRespBean<AddressBean>> listData(@Field("hash") String hash, @Field("time") String time,
                                                   @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                   @Field("uid") int uid, @Field("hashid") String password, @Field("search") String type
            , @Field("page") int page);

    @FormUrlEncoded
    @POST("detaildata.html")
    Observable<HttpRespBean<AddressResBean>> detaildata(@Field("hash") String hash, @Field("time") String time,
                                                        @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                        @Field("uid") int uid, @Field("hashid") String password, @Field("id") String type
    );

    @FormUrlEncoded
    @POST("quickEditData.html")
    Observable<Object> quickEditData(@Field("hash") String hash, @Field("time") String time,
                                     @Field("apiId") String apiId, @Field("terminal") int terminal,
                                     @Field("uid") int uid, @Field("hashid") String password, @Field("id") String id, @Field("fieldName") String fieldName
            , @Field("updata") String updata);

    @FormUrlEncoded
    @POST("quickEditData.html")
    Observable<Object> quickEditDatas(@Field("hash") String hash, @Field("time") String time,
                                      @Field("apiId") String apiId, @Field("terminal") int terminal,
                                      @Field("uid") int uid, @Field("hashid") String password, @Field("id") String id, @Field("num") String fieldName
    );

    @FormUrlEncoded
    @POST("delData.html")
    Observable<Object> delData(@Field("hash") String hash, @Field("time") String time,
                               @Field("apiId") String apiId, @Field("terminal") int terminal,
                               @Field("uid") int uid, @Field("hashid") String password, @Field("id") String id);

    //收货人(person) 联系电话(tel) 所在地区(area) 详细地址(address)
    @FormUrlEncoded
    @POST("saveData.html")
    Observable<Object> saveData(@Field("hash") String hash, @Field("time") String time,
                                @Field("apiId") String apiId, @Field("terminal") int terminal,
                                @Field("uid") int uid, @Field("hashid") String password, @Field("id") String ids, @Field("person") String person
            , @Field("tel") String tel, @Field("area") String area, @Field("address") String address);

    @FormUrlEncoded
    @POST("saveData.html")
        //商品id(goods_id) 颜色(color) 尺寸(size) 数量(num) 直播id(live_id)
    Observable<Object> saveDatas(@Field("hash") String hash, @Field("time") String time,
                                 @Field("apiId") String apiId, @Field("terminal") int terminal,
                                 @Field("uid") int uid, @Field("hashid") String password, @Field("goods_id") String goods_id, @Field("color") String color
            , @Field("size") String size, @Field("num") String num, @Field("live_id") String live_id);

    //title retail_price whole_price goods_sn goods_num category_id size color
    //goods_imgs desc_imgs desc goods_video
    @FormUrlEncoded
    @POST("addGoods.html")
    Observable<Object> addGoods(@Field("hash") String hash, @Field("time") String time,
                                @Field("apiId") String apiId, @Field("terminal") int terminal,
                                @Field("uid") int uid, @Field("hashid") String password, @Field("title") String title,
                                @Field("retail_price") String retail_price, @Field("whole_price") String whole_price, @Field("goods_sn") String goods_sn,
                                @Field("goods_num") String goods_num, @Field("category_id") String category_id, @Field("size") String size
            , @Field("color") String color, @Field("goods_imgs") String goods_imgs, @Field("desc_imgs") String desc_imgs, @Field("desc") String desc,
                                @Field("goods_video") String goods_video);

    @FormUrlEncoded
    @POST("liveList.html")
    Observable<HttpRespBean<ZBListBean>> liveList(@Field("hash") String hash, @Field("time") String time,
                                                  @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                  @Field("type") int id, @Field("page") int page);

    @FormUrlEncoded
    @POST("listdata.html")
    Observable<HttpRespBean<MyZBList>> listdata(@Field("hash") String hash, @Field("time") String time,
                                                @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                @Field("uid") int uid, @Field("hashid") String password, @Field("type") int id, @Field("page") int page);

    @FormUrlEncoded
    @POST("listdata.html")
    Observable<HttpRespBean<ShopCarBean>> listdatass(@Field("hash") String hash, @Field("time") String time,
                                                     @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                     @Field("uid") int uid, @Field("hashid") String password, @Field("page") int id, @Field("search") String page);

    @FormUrlEncoded
    @POST("dolive.html")
    Observable<HttpRespBean<ZBDetil>> dolive(@Field("hash") String hash, @Field("time") String time,
                                             @Field("apiId") String apiId, @Field("terminal") int terminal,
                                             @Field("uid") int uid, @Field("hashid") String password, @Field("live_id") String id);

    @FormUrlEncoded
    @POST("detail.html")
    Observable<HttpRespBean<ZBDetilBean>> detail(@Field("hash") String hash, @Field("time") String time,
                                                 @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                 @Field("uid") int uid, @Field("hashid") String password, @Field("live_id") String id
            , @Field("page") int page);

    @FormUrlEncoded
    @POST("getlivetime.html")
    Observable<HttpRespBean<TimeBean>> getlivetime(@Field("hash") String hash, @Field("time") String time,
                                                   @Field("apiId") String apiId, @Field("terminal") int terminal,
                                                   @Field("uid") int uid, @Field("hashid") String password);

    /*
       * 直播标题(title) 直播时间(start_time) 直播价格(live_price) 直播商品(live_goods) 直播封面(live_img)
       * */
    @FormUrlEncoded
    @POST("addLive.html")
    Observable<HttpRespBean<TimeBean>> addLive(@Field("hash") String hash, @Field("time") String time,
                                               @Field("apiId") String apiId, @Field("terminal") int terminal,
                                               @Field("uid") int uid, @Field("hashid") String password, @Field("title") String title
            , @Field("start_time") String start_time, @Field("live_price") String live_price, @Field("live_goods") String live_goods,
                                               @Field("live_img") String live_img);

    @FormUrlEncoded
    @POST("login.html")
    Observable<HttpRespBean<UserBean>> login(@Field("time") String time, @Field("hash") String hash,
                                             @Field("username") String uid, @Field("password") String password,
                                             @Field("jpushid") String jpushid);

    /**
     * 修改密码
     *
     * @param hash
     * @param time
     * @param uid
     * @param password
     * @param oldpwd
     * @param newpwd
     * @param repeatpwd
     * @return
     */
    @FormUrlEncoded
    @POST("updatePwd.html")
    Observable<Object> updatePwd(@Field("time") String hash, @Field("hash") String time, @Field("uid") int uid, @Field("hashid") String password,
                                 @Field("oldpwd") String oldpwd, @Field("newpwd") String newpwd,
                                 @Field("repeatpwd") String repeatpwd);

    @FormUrlEncoded
    @POST("shopActivity.html")
    Observable<HttpRespBean<ActiveListEntity>> getActiveListData(@Field("time") String time, @Field("hash") String hash
            , @Field("uid") int uid);


    /**
     * 设置消费功能的开或者是关
     *
     * @param time
     * @param hash
     * @param uid
     * @param managementLimit
     * @return
     */
    @FormUrlEncoded
    @POST("managementLimit.html")
    Observable<Object> setFunctionOpenOrClose(@Field("time") String time, @Field("hash") String hash, @Field("uid") int uid, @Field("managementLimit") String managementLimit);


    /**
     * 检查App版本更新
     *
     * @param time         time    <br/>
     * @param hash         hash    <br/>
     * @param types        安卓或苹果（1、ios，2、Android）（必填）   <br/>
     * @param is_shop      商家端或客户端（1、商家端，2、客户端）（必填）   <br/>
     * @param is_shop      商家端或客户端（1、商家端，2、客户端）（必填）   <br/>
     * @param version_code 版本号（必填）   <br/>
     * @return
     */
    @FormUrlEncoded
    @POST("versionControl.html")
    Observable<HttpRespBean<AppVersionEntity>> checkAppVersion(@Field("time") String time, @Field("hash") String hash, @Field("types") String types,
                                                               @Field("is_shop") String is_shop, @Field("version_code") String version_code);


    /**
     * 商家个人活动列表接口文档    <br/>
     *
     * @param time time    <br/>
     * @param hash hash    <br/>
     * @param uid  用户id<br/>
     * @return
     */
    @FormUrlEncoded
    @POST("shopPersonalActivity.html")
    Observable<HttpRespBean<ShopShowsEntity>> getShopActivesList(@Field("time") String time, @Field("hash") String hash, @Field("uid") String uid);

    /**
     * 商家新增个人活动    <br/>
     *
     * @param time          time    <br/>
     * @param hash          hash    <br/>
     * @param uid           用户id<br/>
     * @param activity_desc 商家活动标题   <br/>
     * @param activity_info 商家活动内容   <br/>
     * @return
     */
    @FormUrlEncoded
    @POST("shopPersonalActivityAdd.html")
    Observable<Object> addNewActive(@Field("time") String time, @Field("hash") String hash, @Field("uid") String uid,
                                    @Field("activity_desc") String activity_desc, @Field("activity_info") String activity_info);

    /**
     * 商家个人活动删除接口    <br/>
     *
     * @param time time    <br/>
     * @param hash hash    <br/>
     * @param aid  活动id<br/>
     * @return
     */
    @FormUrlEncoded
    @POST("shopPersonalActivityDel.html")
    Observable<Object> deletShopActivie(@Field("time") String time, @Field("hash") String hash, @Field("aid") String aid);

    /**
     * 新版首页接口
     *
     * @param time
     * @param hash
     * @param uid
     * @return
     */
    @FormUrlEncoded
    @POST("newindex.html")
    Observable<HttpRespBean<Home>> newIndex(@Field("time") String time,
                                            @Field("hash") String hash,
                                            @Field("uid") int uid);

    //今日营收
    @FormUrlEncoded
    @POST("ShopTodayRevenue")
    Observable<HttpRespBean<TodayRevenue>> todayRevenue(@Field("time") String time,
                                                        @Field("hash") String hash,
                                                        @Field("uid") int uid,
                                                        @Field("page") int page,
                                                        @Field("dates") String data);  //今日营收

    //每日流水
    @FormUrlEncoded
    @POST("CurrentAccount")
    Observable<HttpRespBean<DayFlowListDto>> currentAccount(@Field("time") String time,
                                                            @Field("hash") String hash,
                                                            @Field("uid") int uid,
                                                            @Field("page") int page,
                                                            @Field("dates") String data);


    //申请提现  type提现类型值为1或2（1为代收代付，2为发票提现）
    @FormUrlEncoded
    @POST("applyWithdrawal")
    Observable<HttpRespBean> applyWithdrawal(@Field("time") String time,
                                             @Field("hash") String hash,
                                             @Field("uid") int uid,
                                             @Field("bank_id") String bank_id,
                                             @Field("type") int type,
                                             @Field("money") String money);


    /**
     *  申请提现新接口         <br/>
     * @param bank_id  银行卡      <br/>
     * @param type 提现类型      <br/>
     *             1为发票。     <br/> 2为代收代付 。    <br/>
     * @param money 鑫豆提现金额      <br/>
     * @param cash_ticket 卷码提现金额    <br/>
     */
    @FormUrlEncoded
    @POST("applyWithdrawalNew")
    Observable<HttpRespBean> applyWithdrawalNew(@Field("time") String time,
                                             @Field("hash") String hash,
                                             @Field("uid") int uid,
                                             @Field("bank_id") String bank_id,
                                             @Field("type") int type,
                                             @Field("money") String money,
                                             @Field("cash_ticket") String cash_ticket);

    //申请提现  界面数据
    @FormUrlEncoded
    @POST("withdrawNew")
//    Observable<HttpRespBean<BankListBean.ListBean>> withdrawNew(@Field("time") String time,
    Observable<HttpRespBean<BankListBean.ListBean>> withdrawNew(@Field("time") String time,
                                                                @Field("hash") String hash,
                                                                @Field("uid") int uid);

    //七天营业额
    @FormUrlEncoded
    @POST("ShopDayRevenue")
    Observable<HttpRespBean<RevenueStatistics>> shopDayRevenue(@Field("time") String time,
                                                               @Field("hash") String hash,
                                                               @Field("uid") int uid);

    //半年月度营业额
    @FormUrlEncoded
    @POST("ShopMonthlyRevenue")
    Observable<HttpRespBean<RevenueStatistics>> shopMonthlyRevenue(@Field("time") String time,
                                                                   @Field("hash") String hash,
                                                                   @Field("uid") int uid);

    //月度柱形图统计接口 月份（选填 不填显示当月 例：2018-11）
    @FormUrlEncoded
    @POST("ShopColumnGraph")
    Observable<HttpRespBean<Pie>> shopColumnGraph(@Field("time") String time,
                                                  @Field("hash") String hash,
                                                  @Field("uid") int uid,
                                                  @Field("month") String month);

    /**
     * 提交发票
     *
     * @param time 时间（必填）
     * @param hash 加密值（必填）
     * @param wId  提现列表id（必填）
     * @param type 类型（必填）（1为电子发票 2为快递发票）
     * @param info 发票信息（必填）（json字符串格式）
     * @return
     */
    @FormUrlEncoded
    @POST("InvoiceSubmit")
    Observable<HttpRespBean> invoiceSubmit(@Field("time") String time,
                                           @Field("hash") String hash,
                                           @Field("wid") String wId,
                                           @Field("type") int type,
                                           @Field("info") String info);

    /**
     * 代收代付/发票提现申请 记录  <br/>
     *
     * @param type 记录类型  <br/>
     *             1 为发票提现  <br/> 2 为代付代收提现  <br/>
     * @return
     */
    @FormUrlEncoded
    @POST("WithdrawRecord.html")
    Observable<HttpRespBean<ApplyRecodEntity>> getApplyRecord(@Field("time") String time, @Field("hash") String hash
            , @Field("uid") int uid, @Field("type") String type, @Field("page") int page);

    /**
     * 提现账款 <br/>
     *
     * @param type 记录类型  <br/>
     *             1 为发票提现  <br/> 2 为代付代收提现  <br/>
     * @return
     */
    @FormUrlEncoded
    @POST("accountSuccess.html")
    Observable<HttpRespBean<WithdrawlBillEntity>> withdrawCredit(@Field("time") String time, @Field("hash") String hash
            , @Field("uid") int uid, @Field("type") String type, @Field("page") int page);

    /**
     * 用户端登录发送语音短信验证码
     * 请求类型（//1商家管理员注册短信 2商家密码找回 3 个人短信登录 7个人版用户注册短信 8商家找回账号 11支付宝扫一扫快速登录）
     *
     * @param time
     * @param hash
     * @param mobile    手机号码 <br/>
     * @param checktype 发送短信的类型：   <br/>
     *                  checkTypeCode 为 1 ： 登录、注册短信        <br/>
     *                  checkTypeCode 为 2 ：  商家密码找回              <br/>
     *                  checkTypeCode 为 3 ： 个人短信登录           <br/>
     *                  checkTypeCode 为 7 ： 个人版用户注册短信            <br/>
     *                  checkTypeCode 为 8 ： 商家找回账号           <br/>
     *                  checkTypeCode 为 11 ： 支付宝扫一扫快速登录            <br/>
     */
    @FormUrlEncoded
    @POST("singleSendcode.html")
    Observable<Object> sendVoiceVerifyCode(@Field("time") String time, @Field("hash") String hash,
                                           @Field("mobile") String mobile, @Field("checktype") int checktype
    );


    /**
     *  扫一扫验券 <br/>
     * @param time
     * @param hash
     * @param uid
     * @param code_url  扫一扫获得的结果信息 <br/>
     * @return
     */
    @FormUrlEncoded
    @POST("couponVerifyCodeurl.html")
    Observable<HttpRespBean<CheckTicketsResultEntity>> couponVerifyCodeurl(@Field("time") String time, @Field("hash") String hash,
                                                                            @Field("uid") int uid, @Field("code_url") String code_url);


    /**
     *  手动验券 <br/>
     * @param time
     * @param hash
     * @param uid
     * @param ticket_number  输入的券码信息 <br/>
     * @return
     */
    @FormUrlEncoded
    @POST("couponVerifyCodehand.html")
    Observable<HttpRespBean<CheckTicketsResultEntity>> couponVerifyCodehand(@Field("time") String time, @Field("hash") String hash,
                                                                            @Field("uid") int uid, @Field("ticket_number") String ticket_number);


    /**
     *   商家验券记录   <br/>
     * @param time <br/>
     * @param hash <br/>
     * @param uid <br/>
     * @param date  验券日期 格式： 2019-01-04<br/>
     * @param page <br/>
     * @return
     */
    @FormUrlEncoded
    @POST("couponVerifyRecord.html")
    Observable<HttpRespBean<CheckRecordEntity>> couponVerifyRecord(@Field("time") String time, @Field("hash") String hash,
                                                                   @Field("uid") int uid, @Field("date") String date, @Field("page") int page);


    /**
     * 消费权限<br/>
     * @return
     */
    @FormUrlEncoded
    @POST("ConsumePower.html")
    Observable<HttpRespBean<ConsumeRightsEntity>> consumePower(@Field("time") String time, @Field("hash") String hash
            , @Field("uid") int uid, @Field("page") int page);

    /**
     * 消费功能<br/>
     * @return
     */
    @FormUrlEncoded
    @POST("Ability.html")
    Observable<HttpRespBean<ConsumeFunctionEntity>> ability(@Field("time") String time, @Field("hash") String hash
            , @Field("uid") int uid);

    /**
     * 消费功能打开或者关闭   <br/>
     * @return
     */


    /**
     * 消费功能打开或者关闭接口  <br/>
     * @param button      被点击的按钮    <br/>
     *                        our_shop   表示本店消费  <br/>
     *                        ohter_shop   表示他店消费  <br/>
     *                        mall   表示网上商城  <br/>
     *                        leaflet   开鑫传单  <br/>
     * @param value  当前被点击按钮的状态   <br/>
     *                          当前按钮状态为true时，传的是1  <br/>
     *                          当前按钮状态为false时，传的是2  <br/>
     */
    @FormUrlEncoded
    @POST("AbilityButton.html")
    Observable<HttpRespBean<CheckTicketsResultEntity>> abilityButton(@Field("time") String time, @Field("hash") String hash
            , @Field("uid") int uid, @Field("button") String button, @Field("value") String value);


    /***
     *  关联会员、关联商家  <><br/>
     * @param type  1.关联用户  <br/>2.商家  <br/>
     */
    @FormUrlEncoded
    @POST("relation.html")
    Observable<HttpRespBean<RelationShipListEntity>> relation(@Field("time") String time, @Field("hash") String hash
            , @Field("uid") int uid, @Field("type") String type, @Field("page") int page);


    /***
     *  关联会员、关联商家  <><br/>
     * @param type  1.关联用户  <br/>2.商家  <br/>
     */
    @FormUrlEncoded
    @POST("couponInfo.html")
    Observable<HttpRespBean<CheckTicketsDetailsEntity>> couponInfo(@Field("time") String time, @Field("hash") String hash
            , @Field("uid") int uid, @Field("under_id") String type, @Field("page") int page);


}
