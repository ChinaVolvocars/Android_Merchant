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
import com.hzxmkuar.sxmaketnew.view.dialog.DialogHomeWay;
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
    private TextView mNewShopJoin;
    private DeleteEditText evPhone;
    private DeleteEditText evCode;
    private CheckBox mCheckBox;
    private TextView xieyi;
    private TextView tv_faq;


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
        evCode = (DeleteEditText) findViewById(R.id.ev_code);
        mNewShopJoin = (TextView) findViewById(R.id.tv_new_shop_join);
        mCheckBox = (CheckBox) findViewById(R.id.checkbox);
        xieyi = (TextView) findViewById(R.id.xieyi);
        tv_faq = (TextView) findViewById(R.id.tv_faq);
    }

    @Override
    protected void setStatusBar() {
    }

    @Override
    protected void doLogicFunc() {
        initLogin();
        attachClickListener(btnLogin);
        attachClickListener(mNewShopJoin);
        attachClickListener(xieyi);
        attachClickListener(tv_faq);
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
                    String qqauthGender = user.getSex() + "";
                    //gotoReq(2,qqauthId,qqauthName,qqauthHead,qqauthGender);
                    break;
                case LoginPlatform.WX:
                    WxUser wxUser = (WxUser) result.getUserInfo();
                    String wxauthId = wxUser.getOpenId();
                    String wxauthName = wxUser.getNickname();
                    String wxauthHead = wxUser.getHeadImageUrl();
                    String wxauthGender = wxUser.getSex() + "";
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

    private void gotoReqss(String qqauthId, String qqauthName) {
        if (!mCheckBox.isChecked()) {
            showToastMsg("请同意入驻说明！");
            return;
        }
        CommonSubscriber<UserBean> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                UserBean userBean = (UserBean) o;
                if (userBean != null) {
                    if (userBean.getCheck_status().equals("1")) {
                        showToastMsg("账号正在审核中");
                    } else if (userBean.getCheck_status().equals("2")) {
                        DataCenter.saveLoginDataInfo(userBean);
                        gotoActivity(MainActivity.class);
                        finish();
                    } else {
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
        UserMethods.getInstance().login(subscriber, reqList, qqauthId, qqauthName, "");
        rxManager.add(subscriber);
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == btnLogin.getId()) {
            requestCodePermission();
        }else if (view.getId() == mNewShopJoin.getId()) {
            gotoActivity(NewZCActivity.class);
        }else if (view.getId() == xieyi.getId()) {
            Intent urlIntent = new Intent(context, BaseUrlActivity.class);
            urlIntent.putExtra(BaseUrlActivity.MAIN_URL, "http://app.zhongxinyingjia.com/Home/Index/article/type/8.html");
            startActivity(urlIntent);
        }else if (view.getId() == tv_faq.getId()){
            gotoActivity(FAQActivity.class);
        }
    }

    private void requestCodePermission() {
        HiPermission.create(context)
                .checkSinglePermission(Manifest.permission.READ_PHONE_STATE, new PermissionCallback() {
                    @Override
                    public void onClose() {
                    }

                    @Override
                    public void onFinish() {
                        gotoReqss(getEditTextStr(evPhone), getEditTextStr(evCode));
                    }

                    @Override
                    public void onDeny(String permisson, int position) {
                        LogUtils.i("ondeny");
                    }

                    @Override
                    public void onGuarantee(String permisson, int position) {
                        gotoReqss(getEditTextStr(evPhone), getEditTextStr(evCode));
                    }
                });
    }


}
