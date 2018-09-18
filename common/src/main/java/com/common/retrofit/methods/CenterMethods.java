package com.common.retrofit.methods;

import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.BankListBean;
import com.common.retrofit.entity.result.BankNameBean;
import com.common.retrofit.service.UserService;

import rx.Observable;
import rx.Subscriber;

public class CenterMethods extends BaseMethods {

    private static CenterMethods m_ins = null;

    public static CenterMethods getInstance() {
        if (m_ins == null) {
            synchronized (CenterMethods.class) {
                if (m_ins == null) {
                    m_ins = new CenterMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "Center/";
    }

    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }
    public void bankList(Subscriber<BankListBean> subscriber, int page) {
        Observable observable = initService().bankList( System.currentTimeMillis() + "","1522546fb8e6c8b6c2d37837e99d0730", DataCenter.UserId, DataCenter.HashId,page);
        toSubscribe(observable, subscriber);
    }


    /**
     *  添加对私银行
     * @param branch_bank  支行名称  <br/>
     * @param card_number  银行卡账号  <br/>
     * @param phone      手机<br/>
     * @param idCard   身份证<br/>
     * @param userName   持卡人名称<br/>
     * @return
     */
    public void addBank(Subscriber<Object> subscriber, String branch_bank, String card_number, String phone, String idCard,String userName) {
        Observable observable = initService().addBank( System.currentTimeMillis() + "","4ddd2bf4b16a5899646c115949c5e5f3", DataCenter.UserId,
                branch_bank,card_number,phone,idCard,userName);
        toOtherSubscribe(observable, subscriber);
    }


    /**
     * 添加对公银行卡  <br/>
     * @param card_number    银行卡账号       <br/>
     * @param branch_bank   支行名称      <br/>
     * @param bank_id     发卡银行    <br/>
     * @param user_name    公司名称   <br/>
     * @return
     */
    public void addBankCardToCompany(Subscriber<Object> subscriber, String card_number, String branch_bank, String bank_id,String user_name) {
        Observable observable = initService().addBankCardToCompany( System.currentTimeMillis() + "","7d096c0756e5a60d727f37b345fbc4c7", DataCenter.UserId,
                card_number,branch_bank,bank_id,user_name);
        toOtherSubscribe(observable, subscriber);
    }

    /**
     *  删除银行卡
     * @param subscriber
     * @param page
     */
    public void delBank(Subscriber<Object> subscriber, String page) {
        Observable observable = initService().delBank( System.currentTimeMillis() + "","3435103f11f21993476fe72ff7a20b8f", DataCenter.UserId, DataCenter.HashId,page);
        toOtherSubscribe(observable, subscriber);
    }

    /**
     * 获取银行卡列表
     * @param subscriber
     */
    public void bankType(Subscriber<BankNameBean> subscriber) {
        Observable observable = initService().bankType( System.currentTimeMillis() + "","70ffcb2a3569065c4420776b8a81809b");
        toOtherSubscribe(observable, subscriber);
    }
}