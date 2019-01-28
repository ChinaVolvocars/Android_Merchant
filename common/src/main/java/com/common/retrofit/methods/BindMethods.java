package com.common.retrofit.methods;

import com.common.base.Constants;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.ActiveListEntity;
import com.common.retrofit.entity.result.AppVersionEntity;
import com.common.retrofit.entity.result.ApplyRecodEntity;
import com.common.retrofit.entity.result.BankListBean;
import com.common.retrofit.entity.result.BindDeviceEntity;
import com.common.retrofit.entity.result.ChangeBean;
import com.common.retrofit.entity.result.CheckTicketsResultEntity;
import com.common.retrofit.entity.result.ConsumeFunctionEntity;
import com.common.retrofit.entity.result.ConsumeRightsEntity;
import com.common.retrofit.entity.result.FBean;
import com.common.retrofit.entity.result.FinanceDetailEntity;
import com.common.retrofit.entity.result.FiniBean;
import com.common.retrofit.entity.result.IndexBean;
import com.common.retrofit.entity.result.InfoBean;
import com.common.retrofit.entity.result.NewTestBean;
import com.common.retrofit.entity.result.RelationShipListEntity;
import com.common.retrofit.entity.result.ShopShowsEntity;
import com.common.retrofit.entity.result.WidthDrawEntity;
import com.common.retrofit.entity.result.WithdrawlBillEntity;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.model.DayFlowListDto;
import com.common.retrofit.model.Home;
import com.common.retrofit.model.Pie;
import com.common.retrofit.model.RevenueStatistics;
import com.common.retrofit.model.TodayRevenue;
import com.common.retrofit.service.UserService;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class BindMethods extends BaseMethods {
    private static final String TAG = "BusinessUserMethods";
    private static BindMethods m_ins = null;

    public static BindMethods getInstance() {
        if (m_ins == null) {
            synchronized (BindMethods.class) {
                if (m_ins == null) {
                    m_ins = new BindMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "Bind/";
    }


    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }

    /***
     *  设备状态  <><br/>
     */
    public void devStatus(Subscriber<BindDeviceEntity> subscriber) {
        List<String> reqList = new ArrayList<>();
        reqList.add("time");
        reqList.add("uid");
        Observable observable = initService().devStatus(System.currentTimeMillis() + "", Constants.getHash(reqList),
                DataCenter.UserId);
        toSubscribe(observable, subscriber);
    }

    /**
     * 设备绑定或取消绑定  <br/>
     *
     * @param type   绑定类型  <br/> 1 绑定   <br/>2 解绑  <br/>
     * @param devNum 设备id  <br/>
     */
    public void isBind(Subscriber<BindDeviceEntity> subscriber, String type, String devNum) {// 1 绑定 2 解绑
        List<String> reqList = new ArrayList<>();
        reqList.add("time");
        reqList.add("type");
        reqList.add("uid");
        reqList.add("dev_num");
        Observable observable = initService().isBind(System.currentTimeMillis() + "", Constants.getHash(reqList), type,
                DataCenter.UserId, devNum);
        toSubscribe(observable, subscriber);
    }

}