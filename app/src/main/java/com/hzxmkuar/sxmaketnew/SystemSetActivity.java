package com.hzxmkuar.sxmaketnew;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.DataCenter;
import com.common.utils.ActivityStack;
import com.hzxmkuar.sxmaketnew.common.LoginActivity;

/**
 * 设置<br/>
 * Created by STH on 2018/3/31.
 */
public class SystemSetActivity extends BaseMvpActivity {

    private LinearLayout mLlSetPwd;
    private Button mBtbExit;
    private LinearLayout mBankCard;
    private ImageView mBack;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }


    @Override
    protected void setStatusBar() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    protected void onViewCreated() {
        mLlSetPwd = (LinearLayout) findViewById(R.id.ll_set_pwd);
        mBankCard = (LinearLayout) findViewById(R.id.ll_bank_card);
        mBack = (ImageView) findViewById(R.id.back);
        mBtbExit = (Button) findViewById(R.id.btn_exit);
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(mBtbExit);
        attachClickListener(mLlSetPwd);
        attachClickListener(mBack);
        attachClickListener(mBankCard);
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
        } else if (view.getId() == mBack.getId()) {
            finish();
        } else if (view.getId() == mBankCard.getId()) {
            startActivity(new Intent(context, MyBankActivity.class).putExtra("name", "000"));
        }
    }
}
