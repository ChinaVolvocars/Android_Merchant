package com.hzxmkuar.sxmaketnew.newversion;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.common.base.Constants;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.result.BankListBean;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.activity.MyBankActivity;
import com.hzxmkuar.sxmaketnew.view.dialog.DialogHomeWay;
import com.hzxmkuar.sxmaketnew.view.dialog.WithdrawDialogFragment;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

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
    private BankListBean.ListBean itemBank;
    private String money;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @OnClick(R.id.back)
    public void onFinishClicked() {
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_withdraw_new;
    }

    @Override
    protected void onViewCreated() {
        Bundle bundle = getIntent().getExtras();
        int collectionValue = bundle.getInt(DialogHomeWay.COLLECTION_KEY, 0);
        money = bundle.getString(NewMainActivity.KEY_MONEY, "0.00");
        flag = collectionValue == 0 ? true : false;

        tName.setText(flag ? "代收代付" : "发票提现");
        tvRight.setText("查看规则");
        tvRight.setTextColor(getResources().getColor(R.color.white));
        tvRight.setBackgroundResource(flag ? R.drawable.shape_rectangle_rule_yellow : R.drawable.shape_rectangle_rule_blue);
        tvConfirm.setBackgroundResource(flag ? R.drawable.selector_button : R.drawable.selector_button_invoice);
        tvViewRecord.setBackgroundResource(flag ? R.drawable.shape_rectangle_stroke : R.drawable.shape_rectangle_stroke_blue);
        tvViewRecord.setTextColor(flag ? getResources().getColor(R.color.color_ffae05) : getResources().getColor(R.color.color_60aeff));

        tvAmount.setText(money);

        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = Constants.BaseUrl.replace("Api", "Home");
                if (flag) {
//                   "http://test.zhongxinyingjia.com/Api/";                   //  高兴超线下测试环境用
//                    http://test.zhongxinyingjia.com/Home/Financial/dsdfRules.html
                    WebViewActivity.runWebActivity(WithdrawalActivity.this, "代收代付提现规则", url + "/Financial/dsdfRules.html");
                } else {
//                    http://test.zhongxinyingjia.com/Home/Financial/invoiceRules.html
                    WebViewActivity.runWebActivity(WithdrawalActivity.this, "发票提现规则", url + "/Financial/invoiceRules.html");

                }
            }
        });


        BusinessUserMethods.getInstance().withdrawNew(new Subscriber<HttpRespBean<BankListBean.ListBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(HttpRespBean<BankListBean.ListBean> bank) {
                if (null != bank.getData()) {
                    tvServiceFee.setText(getString(R.string.service_fee, bank.getData().getGrade()));
                    itemBank = bank.getData();
                    tvAddBank.setVisibility(View.GONE);
                    llBankInfo.setVisibility(View.VISIBLE);
                    Glide.with(WithdrawalActivity.this)
                            .load(bank.getData().getCard_logo())
                            .into(ivBank);
                    tvBankName.setText(itemBank.getBank_name());
                    tvBankNum.setText(getString(R.string.bank_num, itemBank.getCard_number().substring(itemBank.getCard_number().length() - 4, itemBank.getCard_number().length())));
                } else {
                    tvAddBank.setVisibility(View.VISIBLE);
                    llBankInfo.setVisibility(View.GONE);
                }
            }
        });

    }


    @OnClick({R.id.tv_add_bank, R.id.ll_bank_info})
    public void onAddBankClicked() {
        startActivityForResult(new Intent(context, MyBankActivity.class)
                .putExtra("name", "111"), 50);
    }

    @OnClick(R.id.tv_confirm)
    public void onConfirmClicked() {
        applyWithdrawal(money);
    }

    @OnClick(R.id.tv_view_record)
    public void onViewRecordClicked() {
        viewRecord(flag);
    }

    private void viewRecord(boolean flag) {
        Intent intent = new Intent(this, RecordActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean("flag", flag);//true 为代收代付； false 发票提现
        intent.putExtras(bundle);
        startActivity(intent);

    }


    //代收代付
    private void applyWithdrawal(String money) {
        //type提现类型值为1或2（1为代收代付，2为发票提现）
        if (null == itemBank) {
            showToastMsg("请选择银行卡");
            return;
        }
        BusinessUserMethods.getInstance().applyWithdrawal(new Subscriber<HttpRespBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(HttpRespBean httpRespBean) {
                successDialog();
            }
        }, itemBank.getId(), flag ? 2 : 1, money);


    }

    private void successDialog() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("flag", flag);
        WithdrawDialogFragment dialog = WithdrawDialogFragment.newInstance(bundle);
        dialog.show(getSupportFragmentManager(), "WithdrawDialogFragment");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 50 && resultCode == 100) {
            tvAddBank.setVisibility(View.GONE);
            llBankInfo.setVisibility(View.VISIBLE);
            itemBank = data.getParcelableExtra(MyBankActivity.ITEM);
            Glide.with(WithdrawalActivity.this).load(itemBank.getCard_bank_logo()).into(ivBank);
            tvBankName.setText(itemBank.getBank_name());
            tvBankNum.setText(getString(R.string.bank_num, itemBank.getCard_number().substring(itemBank.getCard_number().length() - 4, itemBank.getCard_number().length())));
        }
    }

    @Override
    protected void doLogicFunc() {

    }


}
