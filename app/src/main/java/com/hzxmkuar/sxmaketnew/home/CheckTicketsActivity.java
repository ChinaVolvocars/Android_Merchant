package com.hzxmkuar.sxmaketnew.home;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.CheckTicketsResultEntity;
import com.common.retrofit.methods.CouponMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.EmptyUtils;
import com.common.widget.editview.DeleteEditText;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.view.dialog.CehckResultDialog;

/**
 * 手动验券
 * Created by jc on 2019/1/5.
 */
public class CheckTicketsActivity extends BaseMvpActivity {
    private static final String TAG = "CheckTicketsActivity";
    private ImageView mIvBack;
    private TextView mTvTitle;
    private Button btn_confir_commit;
    private DeleteEditText edt_input_tickets_code;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_check_tickets;
    }

    @Override
    protected void onViewCreated() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("手动验券");
        btn_confir_commit = (Button) findViewById(R.id.btn_confir_commit);
        edt_input_tickets_code = (DeleteEditText) findViewById(R.id.edt_input_tickets_code);

    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(mIvBack);
        attachClickListener(btn_confir_commit);
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == mIvBack.getId()) {
            finish();
        } else if (view.getId() == btn_confir_commit.getId()) {
            if (EmptyUtils.isEmpty(getEditTextStr(edt_input_tickets_code))) {
                showToastMsg("券码不能为空");
                return;
            }
            if (getEditTextStr(edt_input_tickets_code).length() < 8) {
                showToastMsg("券码格式不正确");
                return;
            }
            verityTicketsCode();
        }
    }

    private void verityTicketsCode() {
        showProgressingDialog();
        CommonSubscriber<CheckTicketsResultEntity> subscriber = new CommonSubscriber<>(new SubscriberListener<CheckTicketsResultEntity>() {
            @Override
            public void onNext(CheckTicketsResultEntity result) {
                dismissProgressDialog();
                final CehckResultDialog dialog = new CehckResultDialog(context, result.getStatus()+"", CheckTicketsActivity.this);
                dialog.show();
                dialog.setOnRetryClickListener(new CehckResultDialog.OnRetryClickListener() {
                    @Override
                    public void retryClick() {
                        edt_input_tickets_code.setText("");
                    }
                });
            }

            @Override
            public void onError(String e, int code) {
                showToastMsg(e);
                dismissProgressDialog();
            }
        });
        CouponMethods.getInstance().couponVerifyCodehand(subscriber, getEditTextStr(edt_input_tickets_code));
        rxManager.add(subscriber);
    }
}
