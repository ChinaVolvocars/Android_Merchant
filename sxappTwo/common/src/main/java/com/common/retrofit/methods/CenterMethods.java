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
     *  添加银行卡
     * @param subscriber 观察者
     * @param cardNumber 银行卡号
     * @param cardPhone 绑卡手机
     * @param idCard 身份证
     * @param banksNames 开户行
     */
    public void addBank(Subscriber<Object> subscriber, String cardNumber, String cardPhone, String idCard,String banksNames) {
        Observable observable = initService().addBank( System.currentTimeMillis() + "","7d09c4a6e6b8a45cc32d1e5e48ec91cf", DataCenter.UserId, DataCenter.HashId,
                cardNumber,cardPhone,idCard,banksNames);
        toOtherSubscribe(observable, subscriber);
    }
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