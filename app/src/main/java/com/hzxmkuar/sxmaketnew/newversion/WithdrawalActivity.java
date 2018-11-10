package com.hzxmkuar.sxmaketnew.newversion;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.view.dialog.DialogHomeWay;
import com.hzxmkuar.sxmaketnew.view.dialog.WithdrawDialogFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 代收代付，发票提现
 */
public class WithdrawalActivity extends BaseMvpActivity {

    @BindView(R.id.t_name)
    TextView tName;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_add_bank)
    TextView tvAddBank;
    @BindView(R.id.iv_bank)
    ImageView ivBank;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.tv_bank_num)
    TextView tvBankNum;
    @BindView(R.id.ll_bank_info)
    LinearLayout llBankInfo;
    @BindView(R.id.tv_service_fee)
    TextView tvServiceFee;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.tv_view_record)
    TextView tvViewRecord;

    private boolean flag = true;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_withdraw_new;
    }


    @Override
    protected void onViewCreated() {
        Bundle bundle = getIntent().getExtras();
        int collectionValue = bundle.getInt(DialogHomeWay.COLLECTION_KEY, 0);
        flag = collectionValue == 0 ? true : false;

        tName.setText(flag ? "代收代付" : "发票提现");
        tvRight.setText("查看规则");
        tvRight.setTextColor(getResources().getColor(R.color.white));
        tvRight.setBackgroundResource(flag ? R.drawable.shape_rectangle_rule_yellow : R.drawable.shape_rectangle_rule_blue);
        tvConfirm.setBackgroundResource(flag ? R.drawable.selector_button : R.drawable.selector_button_invoice);
        tvViewRecord.setBackgroundResource(flag ? R.drawable.shape_rectangle_stroke : R.drawable.shape_rectangle_stroke_blue);
        tvViewRecord.setTextColor(flag ? getResources().getColor(R.color.color_ffae05) : getResources().getColor(R.color.color_60aeff));

        if (true) {//有卡
            tvAddBank.setVisibility(View.GONE);
            llBankInfo.setVisibility(View.VISIBLE);

        } else {
            tvAddBank.setVisibility(View.VISIBLE);
            llBankInfo.setVisibility(View.GONE);
        }
    }


    @OnClick(R.id.tv_add_bank)
    public void onAddBankClicked() {


    }

    @OnClick(R.id.tv_confirm)
    public void onConfirmClicked() {
        if (flag) {
            collectionPayment();
        } else {
            invoiceWithdrawal();
        }
    }

    @OnClick(R.id.tv_view_record)
    public void onViewRecordClicked() {
        if (flag) {
            viewRecord(true);
        } else {
            viewRecord(false);
        }
    }

    private void viewRecord(boolean flag) {

    }


    //代收代付
    private void collectionPayment() {
        WithdrawDialogFragment dialog = WithdrawDialogFragment.newInstance(new Bundle());
        dialog.show(getSupportFragmentManager(), "WithdrawDialogFragment");

    }

    //发票提现
    private void invoiceWithdrawal() {
        WithdrawDialogFragment dialog = WithdrawDialogFragment.newInstance(new Bundle());
        dialog.show(getSupportFragmentManager(), "WithdrawDialogFragment");
    }


    @Override
    protected void doLogicFunc() {

    }
}
