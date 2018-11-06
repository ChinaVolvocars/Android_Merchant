package com.hzxmkuar.sxmaketnew.login_register;

import android.Manifest;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.common.base.Constants;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.UserBean;
import com.common.retrofit.methods.UserMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.LogUtils;
import com.common.widget.editview.DeleteEditText;
import com.hzxmkuar.sxmaketnew.base.BaseUrlActivity;
import com.hzxmkuar.sxmaketnew.home.MainActivity;
import com.hzxmkuar.sxmaketnew.R;
import com.xmkj.payandlogin.ShareConfig;
import com.xmkj.payandlogin.ShareManager;
import com.xmkj.payandlogin.login.LoginListener;
import com.xmkj.payandlogin.login.LoginPlatform;
import com.xmkj.payandlogin.login.LoginResult;
import com.xmkj.payandlogin.login.result.QQUser;
import com.xmkj.payandlogin.login.result.WxUser;

import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import retrofit2.http.Field;

public class LoginActivity extends BaseMvpActivity {
    private static final String TAG = "LoginActivity";
    private Button btnLogin;
    private Button btnQQ;
    private Button btnWechat;
    private TextView mNewShopJoin;
    private DeleteEditText evPhone;
    private DeleteEditText evCode;
    private Button btnWebo;
    private Button btnali;
    private CheckBox mCheckBox;
    private TextView xieyi;
    private TextView mTvForgetPwd;
    private TextView mTvGetBackAccount;


    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onViewCreated() {
        evPhone = (DeleteEditText) findViewById(R.id.ev_phone);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnQQ = (Button) findViewById(R.id.iv_qq);
        btnWechat = (Button) findViewById(R.id.iv_wechat);
        btnWebo = (Button) findViewById(R.id.iv_webo);
        btnali = (Button) findViewById(R.id.iv_ali);
        evCode = (DeleteEditText) findViewById(R.id.ev_code);
        mNewShopJoin = (TextView) findViewById(R.id.tv_new_shop_join);
        mCheckBox = (CheckBox) findViewById(R.id.checkbox);
        xieyi = (TextView) findViewById(R.id.xieyi);
        mTvForgetPwd = (TextView) findViewById(R.id.tv_forget_pwd);
        mTvGetBackAccount = (TextView) findViewById(R.id.tv_get_back_account);
    }

    @Override
    protected void setStatusBar() {
    }

    @Override
    protected void doLogicFunc() {
        initLogin();
        attachClickListener(btnLogin);
        attachClickListener(btnQQ);
        attachClickListener(btnWechat);
        attachClickListener(btnWebo);
        attachClickListener(btnali);
        attachClickListener(mNewShopJoin);
        attachClickListener(xieyi);
        attachClickListener(mTvForgetPwd);
        attachClickListener(mTvGetBackAccount);


    }

    private void initLogin() {
        ShareConfig config = ShareConfig.instance()
                .qqId("101431881")
                .wxId("wx2d05bd07ca7000ee")
                .wxSecret("3ea99022ef9a1313ec364d521097509e");
        ShareManager.init(config);
    }



    private LoginListener mLoginListener = new LoginListener() {
        @Override
        public void loginSuccess(LoginResult result) {
            switch (result.getPlatform()) {
                case LoginPlatform.QQ:
                    QQUser user = (QQUser) result.getUserInfo();
                    String qqauthId = user.getOpenId();
                    String qqauthName = user.getNickname();
                    String qqauthHead = user.getHeadImageUrl();
                    String qqauthGender = user.getSex()+"";
                    //gotoReq(2,qqauthId,qqauthName,qqauthHead,qqauthGender);
                    break;
                case LoginPlatform.WX:
                    WxUser wxUser = (WxUser) result.getUserInfo();
                    String wxauthId = wxUser.getOpenId();
                    String wxauthName = wxUser.getNickname();
                    String wxauthHead = wxUser.getHeadImageUrl();
                    String wxauthGender = wxUser.getSex()+"";
                    //gotoReq(3,wxauthId,wxauthName,wxauthHead,wxauthGender);
                    break;
            }
        }

        @Override
        public void loginFailure(Exception e) {
            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void loginCancel() {
            Toast.makeText(LoginActivity.this, "登录取消", Toast.LENGTH_SHORT).show();
        }
    };

    /*private void gotoReq(int i, String qqauthId, String qqauthName,String qqauthHead, String qqauthGender) {
        CommonSubscriber<UserBean> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                UserBean userBean = (UserBean) o;
                if (userBean!=null){
                    if (userBean.getUid()>0){
                        dismissProgressDialog();
                        DataCenter.saveLoginDataInfo(userBean);
                        gotoActivity(MainActivity.class);
                        finish();
                    }else {
                        //startActivity(new Intent(context,BDPhoneActivity.class).putExtra("id",userBean.getThirdId()));
                    }
                }
            }
            @Override
            public void onError(String e, int code) {
            }
        });
            UserMethods.getInstance().thirdPartyLogin(subscriber,i, qqauthId,qqauthName,qqauthHead,qqauthGender);
            rxManager.add(subscriber);
    }*/
    private void gotoReqss( String qqauthId, String qqauthName) {
        if (!mCheckBox.isChecked()){
            showToastMsg("请同意入驻说明！");
            return;
        }
        CommonSubscriber<UserBean> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                UserBean userBean = (UserBean) o;
                if (userBean!=null){
                    if (userBean.getCheck_status().equals("1")){
                        showToastMsg("账号正在审核中");
                    }else if (userBean.getCheck_status().equals("2")){
                        DataCenter.saveLoginDataInfo(userBean);
                        gotoActivity(MainActivity.class);
                        finish();
                    }else {
                        showToastMsg("账号审核不通过");
                    }
                }
            }
            @Override
            public void onError(String e, int code) {
                showToastMsg(e);
            }
        });

        List<String> reqList = new ArrayList<>();
        reqList.add("time");
        reqList.add("username");
        reqList.add("password");
        reqList.add("jpushid");
        UserMethods.getInstance().login(subscriber,reqList,qqauthId,qqauthName, "");
        rxManager.add(subscriber);
    }
   /* private void loginEase(UserBean userBean) {
        final UserBean bean = DataCenter.getInstance().getUserBean();
        DataCenter.UserId = bean.getUid();
        DataCenter.HashId = bean.getHashid();
        DataCenter.HXuser = bean.getHx_username();
        DataCenter.HXpas = bean.getHx_password();
        if (EmptyUtils.isEmpty(userBean.getHx_username()) || EmptyUtils.isEmpty(userBean.getHx_password())) {
            return;
        }
        *//*EMClient.getInstance().login(userBean.getHx_username(), userBean.getHx_password(), new EMCallBack() {
            @Override
            public void onSuccess() {
                gotoActivity(MainActivity.class);
                finish();
            }

            @Override
            public void onProgress(int progress, String status) { }

            @Override
            public void onError(int code, final String error) {
                EMClient.getInstance().logout(false);
            }
        });*//*
    }*/

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == btnLogin.getId()) {
            requestCodePermission();
        }  else if (view.getId() == btnQQ.getId()) {
            //LoginUtil.login(this, LoginPlatform.QQ, mLoginListener);
            //showToastMsg("暂未开放");
        }
//        else if (view.getId() == btnWechat.getId()) {
//            gotoActivity(BDPhoneActivity.class);
//            //LoginUtil.login(this, LoginPlatform.WX, mLoginListener);
//            //showToastMsg("暂未开放");
//        }else if (view.getId() == btnWebo.getId()) {
//            gotoActivity(BDPhoneActivity.class);
//            //LoginUtil.login(this, LoginPlatform.WX, mLoginListener);
//            //showToastMsg("暂未开放");
//        }else if (view.getId() == btnali.getId()) {
//            //LoginUtil.login(this, LoginPlatform.WX, mLoginListener);
//            //showToastMsg("暂未开放");
//            gotoActivity(BDPhoneActivity.class);
//        }
        else if (view.getId()==mNewShopJoin.getId()){
            gotoActivity(NewZCActivity.class);
//            gotoActivity(NewPwdActivity.class);
        }else if (view.getId()==mTvForgetPwd.getId()){
            gotoActivity(ForgetPwdActivity.class);
            // 忘记账号
        }else if (view.getId()==mTvGetBackAccount.getId()){
            gotoActivity(GetBackAccountActivity.class);

        }else if (view.getId()==xieyi.getId()){
            Intent urlIntent = new Intent(context, BaseUrlActivity.class);
            urlIntent.putExtra(BaseUrlActivity.MAIN_URL, "http://app.zhongxinyingjia.com/Home/Index/article/type/8.html");
            startActivity(urlIntent);
        }
    }

    private void requestCodePermission() {
        HiPermission.create(context)
                .checkSinglePermission(Manifest.permission.READ_PHONE_STATE, new PermissionCallback() {
                    @Override
                    public void onClose() { }
                    @Override
                    public void onFinish() {
                        gotoReqss(getEditTextStr(evPhone),getEditTextStr(evCode));
                    }
                    @Override
                    public void onDeny(String permisson, int position) {
                        LogUtils.i("ondeny");
                    }
                    @Override
                    public void onGuarantee(String permisson, int position) {
                        gotoReqss(getEditTextStr(evPhone),getEditTextStr(evCode));
                    }
                });
    }
}
