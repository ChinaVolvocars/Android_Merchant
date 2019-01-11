package com.common.retrofit.methods;

import com.common.base.Constants;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.CheckRecordEntity;
import com.common.retrofit.entity.result.CheckTicketsDetailsEntity;
import com.common.retrofit.entity.result.CheckTicketsResultEntity;
import com.common.retrofit.entity.result.UserBean;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.service.UserService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * 福利券、鑫利券相关接口操作
 */
public class CouponMethods extends BaseMethods {

    private static CouponMethods m_ins = null;

    public static CouponMethods getInstance() {
        if (m_ins == null) {
            synchronized (CouponMethods.class) {
                if (m_ins == null) {
                    m_ins = new CouponMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "Coupon/";
    }

    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }

    /**
     *  扫一扫验券 <br/>
     * @param subscriber
     * @param codeUrl  扫一扫获得的信息 <br/>
     */
    public void couponVerifyCodeurl(Subscriber<CheckTicketsResultEntity> subscriber, String codeUrl) {
        List<String> reqList = new ArrayList<>();
        reqList.add("time");
        reqList.add("uid");
        reqList.add("code_url");
        Observable observable = initService().couponVerifyCodeurl(System.currentTimeMillis() + "", Constants.getHash(reqList)
                , DataCenter.UserId, codeUrl);
        toSubscribe(observable, subscriber);
    }

    /**
     *  手动验券 <br/>
     * @param subscriber
     * @param ticketNumber  输入的券码信息 <br/>
     */
    public void couponVerifyCodehand(Subscriber<CheckTicketsResultEntity> subscriber, String ticketNumber) {
        List<String> reqList = new ArrayList<>();
        reqList.add("time");
        reqList.add("uid");
        reqList.add("ticket_number");
        Observable observable = initService().couponVerifyCodehand(System.currentTimeMillis() + "", Constants.getHash(reqList)
                , DataCenter.UserId, ticketNumber);
        toSubscribe(observable, subscriber);
    }


    /**
     *  商家验券记录    <br/>
     * @param subscriber    <br/>
     * @param checkDate  验券日期 格式： 2019-01-04<br/>
     * @param page    <br/>
     */
    public void couponVerifyRecord(Subscriber<CheckRecordEntity> subscriber, String checkDate, int page) {
        List<String> reqList = new ArrayList<>();
        reqList.add("time");
        reqList.add("uid");
        reqList.add("date");
        reqList.add("page");
        Observable observable = initService().couponVerifyRecord(System.currentTimeMillis() + "", Constants.getHash(reqList)
                , DataCenter.UserId, checkDate,page);
        toSubscribe(observable, subscriber);
    }


    /**
     *  验券详情   <br/>
     * @param subscriber    <br/>
     * @param under_id  券idbr/>
     * @param page    <br/>
     */
    public void couponInfo(Subscriber<CheckTicketsDetailsEntity> subscriber, String under_id , int page) {
        List<String> reqList = new ArrayList<>();
        reqList.add("time");
        reqList.add("uid");
        reqList.add("under_id");
        reqList.add("page");
        Observable observable = initService().couponInfo(System.currentTimeMillis() + "", Constants.getHash(reqList)
                , DataCenter.UserId, under_id,page);
        toSubscribe(observable, subscriber);
    }



}