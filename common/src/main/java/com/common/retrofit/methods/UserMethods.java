package com.common.retrofit.methods;

import com.common.base.Constants;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.UserBean;
import com.common.retrofit.service.UserService;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * 用户相关接口操作
 */
public class UserMethods extends BaseMethods {

    private static UserMethods m_ins = null;

    public static UserMethods getInstance() {
        if (m_ins == null) {
            synchronized (UserMethods.class) {
                if (m_ins == null) {
                    m_ins = new UserMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "User/";
    }

    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }

    /**
     * 修改密码
     *
     * @param subscriber 观察者对象
     * @param mobole     手机号码
     * @param code
     * @param jpushid    极光id
     */
    public void updatePwd(Subscriber<Object> subscriber, String mobole, String code, String jpushid) {
        Observable observable = initService().updatePwd(System.currentTimeMillis() + "", "d889fb1feccacc88634963c636d402e6"
                , DataCenter.UserId, DataCenter.HashId, mobole, code, jpushid);
        toOtherSubscribe(observable, subscriber);
    }

    /**
     * 用户登录
     *
     * @param subscriber
     * @param mobole
     * @param code
     * @param jpushid
     */
//    public void login(Subscriber<UserBean> subscriber, String mobole, String code, String jpushid) {
//        Observable observable = initService().login(System.currentTimeMillis() + "", "682efd91c36fdd686556651625a7556e", mobole, code, jpushid);
//    toSubscribe(observable, subscriber);
//}                                                                                     a9046fe532eb66ccde13b37cd39f4666
    public void login(Subscriber<UserBean> subscriber, List<String> reqList,String mobole, String code, String jpushid) {
        Observable observable = initService().login(System.currentTimeMillis() + "", Constants.getHash(reqList), mobole, code, jpushid);
        toSubscribe(observable, subscriber);
    }
}