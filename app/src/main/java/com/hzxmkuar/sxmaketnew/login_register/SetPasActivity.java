package com.hzxmkuar.sxmaketnew.login_register;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.methods.UserMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.widget.editview.DeleteEditText;
import com.hzxmkuar.sxmaketnew.R;

/**
 *  修改密码
 * Created by STH on 2018/5/17.
 */
public class SetPasActivity extends BaseMvpActivity {

    private ImageView iv_back;
    private Button mBtbConfirm;
    private DeleteEditText mOldpas;
    private DeleteEditText mPas;
    private DeleteEditText mRepas;
    private TextView tv_title;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_pas;
    }

    @Override
    protected void onViewCreated() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        mOldpas = (DeleteEditText) findViewById(R.id.oldpas);
        mPas = (DeleteEditText) findViewById(R.id.pas);
        mRepas = (DeleteEditText) findViewById(R.id.repas);
        mBtbConfirm = (Button) findViewById(R.id.btn_confirm);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("密码设置");
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(iv_back);
        attachClickListener(mBtbConfirm);
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == iv_back.getId()) {
            finish();
        } else if (view.getId() == mBtbConfirm.getId()) {
            gotoReqss();
        }
    }

    private void gotoReqss() {
        CommonSubscriber<Object> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                showToastMsg("修改成功！");
                finish();
            }

            @Override
            public void onError(String e, int code) {
                showToastMsg(e);
            }
        });
        UserMethods.getInstance().updatePwd(subscriber, getEditTextStr(mOldpas), getEditTextStr(mPas), getEditTextStr(mRepas));
        rxManager.add(subscriber);
    }
}
