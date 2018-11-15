package com.hzxmkuar.sxmaketnew.login_register;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.hzxmkuar.sxmaketnew.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 常见问题
 * Created by jc on 2018/11/15.
 */
public class FAQActivity extends BaseMvpActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ll_forget_pwd)
    LinearLayout llForgetPwd;
    @BindView(R.id.ll_forget_accout)
    LinearLayout llForgetAccout;
    @BindView(R.id.ll_account_lost)
    LinearLayout llAccountLost;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_faq;
    }

    @Override
    protected void onViewCreated() {
        tvTitle.setText("常见问题");
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(ivBack);
        attachClickListener(llForgetPwd);
        attachClickListener(llForgetAccout);
        attachClickListener(llAccountLost);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == ivBack.getId()){
            finish();
        }else if (view.getId() == llForgetPwd.getId()){
            gotoActivity(ForgetPwdActivity.class);
            finish();
        }else if (view.getId() == llAccountLost.getId()){
            gotoActivity(GetBackAccountActivity.class);
            finish();
        }else if (view.getId() == llAccountLost.getId()){
            showToastMsg("该功能暂未开放，敬请期待！");
        }
    }
}
