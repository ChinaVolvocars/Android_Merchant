package com.common.retrofit.service;

import com.common.retrofit.entity.result.AboutBean;
import com.common.retrofit.entity.result.AddressBean;
import com.common.retrofit.entity.result.AddressResBean;
import com.common.retrofit.entity.result.BankListBean;
import com.common.retrofit.entity.result.BankNameBean;
import com.common.retrofit.entity.result.CCBean;
import com.common.retrofit.entity.result.ChangeBean;
import com.common.retrofit.entity.result.ChooseFLBean;
import com.common.retrofit.entity.result.DPDABean;
import com.common.retrofit.entity.result.FBean;
import com.common.retrofit.entity.result.FLBean;
import com.common.retrofit.entity.result.FLTwoBean;
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
import com.common.retrofit.entity.result.SCListBean;
import com.common.retrofit.entity.result.SHBean;
import com.common.retrofit.entity.result.SPDetilBean;
import com.common.retrofit.entity.result.SPFLBean;
import com.common.retrofit.entity.result.SPListBean;
import com.common.retrofit.entity.result.SPPJBean;
import com.common.retrofit.entity.result.ShopCarBean;
import com.common.retrofit.entity.result.ThreeBean;
import com.common.retrofit.entity.result.TimeBean;
import com.common.retrofit.entity.result.TokenBean;
import com.common.retrofit.entity.result.TwoBean;
import com.common.retrofit.entity.result.UserBean;
import com.common.retrofit.entity.result.UserInfoBean;
import com.common.retrofit.entity.result.WallBean;
import com.common.retrofit.entity.result.YSBean;
import com.common.retrofit.entity.result.ZBDetil;
import com.common.retrofit.entity.result.ZBDetilBean;
import com.common.retrofit.entity.result.ZBListBean;
import com.common.retrofit.entity.resultImpl.HttpRespBean;

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

    @FormUrlEncoded
    @POST("addBank.html")
    Observable<Object> addBank(@Field("time") String hash, @Field("hash") String time
            , @Field("uid") int uid, @Field("hashid") String hashid, @Field("bank_id") String bank_id, @Field("card_number") String card_number
            , @Field("phone") String phone, @Field("ID_card") String ID_card);

    @FormUrlEncoded
    @POST("bankType.html")
    Observable<BankNameBean> bankType(@Field("time") String hash, @Field("hash") String time
    );

    @FormUrlEncoded
    @POST("scategory.html")
    Observable<NewTestBean> scategory(@Field("time") String hash, @Field("hash") String time);

    /**
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
    @FormUrlEncoded
    @POST("shopInfoSubmit.html")
    Observable<Object> shopInfoSubmit(@Field("time") String hash, @Field("hash") String time, @Field("uid") int uid
            , @Field("hashid") String hashid, @Field("shop_name") String shop_name, @Field("category_id") String category_id
            , @Field("province") String province, @Field("area") String area, @Field("county") String county
            , @Field("address") String address, @Field("desc") String desc, @Field("face") String face
            , @Field("shop_pic") String shop_pic, @Field("latitude") String latitude, @Field("longitude") String longitude);

    @FormUrlEncoded
    @POST("storeSubmit.html")
    Observable<Object> storeSubmit(@Field("time") String hash, @Field("hash") String time, @Field("uid") int uid
            , @Field("hashid") String hashid, @Field("id") String id, @Field("xinlidou") String xinlidou
            , @Field("xianglidou") String xianglidou);

    @FormUrlEncoded
    @POST("withdraw.html")
    Observable<Object> withdraws(@Field("time") String hash, @Field("hash") String time, @Field("uid") int uid
            , @Field("hashid") String hashid, @Field("money") String money, @Field("card_id") String card_id
            , @Field("proportion") String proportion);

    @FormUrlEncoded
    @POST("financeList.html")
    Observable<HttpRespBean<FiniBean>> financeList(@Field("time") String hash, @Field("hash") String time
            , @Field("uid") int uid, @Field("hashid") String hashid, @Field("page") int page);

    /**
     * 获取首页数据
     * @param hash
     * @param time
     * @param uid
     * @param hashid
     * @return
     */
    @FormUrlEncoded
    @POST("index.html")
    Observable<HttpRespBean<IndexBean>> index(@Field("time") String hash, @Field("hash") String time, @Field("uid") int uid
            , @Field("hashid") String hashid);

    @FormUrlEncoded
    @POST("financeDetail.html")
    Observable<HttpRespBean<FBean>> financeDetail(@Field("time") String hash, @Field("hash") String time, @Field("uid") int uid
            , @Field("hashid") String hashid);

    @FormUrlEncoded
    @POST("shopInfo.html")
    Observable<HttpRespBean<InfoBean>> shopInfo(@Field("time") String hash, @Field("hash") String time, @Field("uid") int uid
            , @Field("hashid") String hashid);

    @FormUrlEncoded
    @POST("store.html")
    Observable<HttpRespBean<ChangeBean>> store(@Field("time") String hash, @Field("hash") String time, @Field("uid") int uid
            , @Field("hashid") String hashid);

    @FormUrlEncoded
    @POST("apply.html")
    Observable<Object> apply(@Field("time") String hash, @Field("hash") String time, @Field("account_person") String account_person
            , @Field("bank_account") String bank_account, @Field("name") String name, @Field("mobile") String mobile,
                             @Field("category_id") String category_id, @Field("proportion") String proportion
            , @Field("province") String province, @Field("area") String area, @Field("county") String county, @Field("address") String address,
                             @Field("phone") String phone, @Field("password") String password, @Field("code") String code,
                             @Field("certificates_type") String certificates_type, @Field("document_name") String document_name,
                             @Field("certificates_number") String certificates_number, @Field("ID_front_img") String ID_front_img, @Field("ID_back_img") String ID_back_img,
                             @Field("license_img") String license_img, @Field("latitude") String latitude, @Field("longitude") String longitude);

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

    @FormUrlEncoded
    @POST("saveUserDetailData.html")
    Observable<Object> saveUserDetailData(@Field("hash") String hash, @Field("time") String time,
                                          @Field("apiId") String apiId, @Field("terminal") int terminal,
                                          @Field("uid") int uid, @Field("hashid") String password, @Field("update") String update);

    @FormUrlEncoded
    @POST("sendCode.html")
    Observable<Object> sendCode(@Field("hash") String hash, @Field("time") String time,
                                @Field("apiId") String apiId, @Field("terminal") int terminal,
                                @Field("mobile") String uid, @Field("type") int password);

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
    Observable<HttpRespBean<UserBean>> login(@Field("time") String hash, @Field("hash") String time,
                                             @Field("username") String uid, @Field("password") String password,
                                             @Field("jpushid") String jpushid);

    /**
     * 修改密码
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
}
