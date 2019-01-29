package com.hzxmkuar.sxmaketnew.home;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.DataCenter;
import com.common.utils.ActivityStack;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.activity.MyBankActivity;
import com.hzxmkuar.sxmaketnew.base.BaseUrlActivity;
import com.hzxmkuar.sxmaketnew.login_register.SetPasActivity;
import com.hzxmkuar.sxmaketnew.login_register.LoginActivity;

/**
 * 设置<br/>
 * Created by STH on 2018/3/31.
 */
public class SettingsActivity extends BaseMvpActivity {

    private LinearLayout mLlSetPwd;
    private Button mBtbExit;
    private LinearLayout mBankCard;
    private LinearLayout mLlChangeManagerPhone;
    private LinearLayout mLlPrivacy;
    private LinearLayout mLlStatement;
    private ImageView iv_back;
    private TextView tv_title;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    protected void onViewCreated() {
        mLlSetPwd = (LinearLayout) findViewById(R.id.ll_set_pwd);
        mBankCard = (LinearLayout) findViewById(R.id.ll_bank_card);
        mLlChangeManagerPhone = (LinearLayout) findViewById(R.id.ll_change_manager_phone);
        mLlPrivacy = (LinearLayout) findViewById(R.id.ll_privacy);
        mLlStatement = (LinearLayout) findViewById(R.id.ll_statement);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        mBtbExit = (Button) findViewById(R.id.btn_exit);
        tv_title.setText("设置");
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(mBtbExit);
        attachClickListener(mLlSetPwd);
        attachClickListener(iv_back);
        attachClickListener(mBankCard);
        attachClickListener(mLlChangeManagerPhone);
        attachClickListener(mLlPrivacy);
        attachClickListener(mLlStatement);
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == mBtbExit.getId()) {
            DataCenter.getInstance().deleteLoginDataInfo();
            ActivityStack.getInstance().finishAllActivity();
            gotoActivity(LoginActivity.class);
            onBackPressed();
        } else if (view.getId() == mLlSetPwd.getId()) {
            gotoActivity(SetPasActivity.class);
        } else if (view.getId() == iv_back.getId()) {
            finish();
        } else if (view.getId() == mLlChangeManagerPhone.getId()) {
            gotoActivity(ChangManagerPhoneActivity.class);
        } else if (view.getId() == mBankCard.getId()) {
            startActivity(new Intent(context, MyBankActivity.class).putExtra("name", "000"));
        } else if (view.getId() == mLlPrivacy.getId()) {
            Intent urlIntent = new Intent(context, BaseUrlActivity.class);
            urlIntent.putExtra(BaseUrlActivity.MAIN_URL, "http://activity.zhongxinyingjia.com/ios/privacypolicy.html");
            startActivity(urlIntent);
        } else if (view.getId() == mLlStatement.getId()) {
            Intent urlIntent = new Intent(context, BaseUrlActivity.class);
            urlIntent.putExtra(BaseUrlActivity.MAIN_URL, "http://app.zhongxinyingjia.com/Home/Index/article/type/8.html");
            startActivity(urlIntent);
        }
    }
}
