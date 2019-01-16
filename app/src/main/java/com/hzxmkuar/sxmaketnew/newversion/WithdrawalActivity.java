package com.hzxmkuar.sxmaketnew.newversion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.BankListBean;
import com.common.retrofit.entity.result.CheckTicketsResultEntity;
import com.common.retrofit.entity.result.ShopShowsEntity;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.SPUtils;
import com.common.utils.ScreenUtils;
import com.common.utils.SizeUtils;
import com.common.utils.UIUtils;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.activity.MyBankActivity;
import com.hzxmkuar.sxmaketnew.view.dialog.DialogHomeWay;
import com.hzxmkuar.sxmaketnew.view.dialog.WithdrawDialogFragment;
import com.view.pie.utils.UIUtil;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

import static com.common.event.SpConstants.CLOSED_PAY_URL;
import static com.common.event.SpConstants.INVOICE_URL;
import static com.hzxmkuar.sxmaketnew.newversion.NewMainActivity.KEY_WEEK;

/**
 * 代收代付，发票提现
 */
public class WithdrawalActivity extends BaseMvpActivity {
    private static final String TAG = "WithdrawalActivity";
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
//    @BindView(R.id.tv_service_fee)
//    TextView tvServiceFee;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.tv_view_record)
    TextView tvViewRecord;

    private boolean flag = true;
    private BankListBean.ListBean itemBank;
    private String money = "0";
    /**
     * 可提现优惠券
     */
    private String counponWithdrawl = "0";
    private int week;
    private CheckBox rdbt_normal_withdrawl;
    private CheckBox rdbt_coupon_withdrawl;
    private TextView tv_can_withdrawl_coupon;
    private TextView tv_can_withdrawl_count;
    private MyOnCheckedChangedListener checkedChangedListener;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @OnClick(R.id.back)
    public void onFinishClicked() {
        onBackPressed();
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
        counponWithdrawl = bundle.getString(NewMainActivity.COUPON_WITHDRAWL, "0.00");
        week = bundle.getInt(KEY_WEEK, 0); //  0不可提现，  <br/> 1 可以提现  <br/>
        flag = collectionValue == 0 ? true : false;
        checkedChangedListener = new MyOnCheckedChangedListener();
        rdbt_normal_withdrawl = (CheckBox) findViewById(R.id.rdbt_normal_withdrawl);
        rdbt_coupon_withdrawl = (CheckBox) findViewById(R.id.rdbt_coupon_withdrawl);
        rdbt_normal_withdrawl.setButtonDrawable(null);
        rdbt_coupon_withdrawl.setButtonDrawable(null);


        if (flag) {
            rdbt_normal_withdrawl.setButtonDrawable(R.drawable.checkbox_selector_color_ffaf04);
            setCheckBoxPading(rdbt_normal_withdrawl,64,R.drawable.checkbox_selector_color_ffaf04);
            rdbt_coupon_withdrawl.setButtonDrawable(R.drawable.checkbox_selector_color_ffaf04);
            setCheckBoxPading(rdbt_coupon_withdrawl,64,R.drawable.checkbox_selector_color_ffaf04);
        } else {
            rdbt_normal_withdrawl.setButtonDrawable(R.drawable.goxuan_02);
            setCheckBoxPading(rdbt_normal_withdrawl,64,R.drawable.goxuan_02);
            rdbt_coupon_withdrawl.setButtonDrawable(R.drawable.goxuan_02);
            setCheckBoxPading(rdbt_coupon_withdrawl,64,R.drawable.goxuan_02);
        }
        tv_can_withdrawl_coupon = (TextView) findViewById(R.id.tv_can_withdrawl_coupon);
        tv_can_withdrawl_count = (TextView) findViewById(R.id.tv_can_withdrawl_count);

//        Log.e("", "可以提现: " + week);
        rdbt_coupon_withdrawl.setOnCheckedChangeListener(checkedChangedListener);
        rdbt_normal_withdrawl.setOnCheckedChangeListener(checkedChangedListener);
        rdbt_coupon_withdrawl.setText("券码提现金额");

        tName.setText(flag ? "代收代付" : "发票提现");
        tvRight.setText("查看规则");
        tvRight.setTextColor(getResources().getColor(R.color.white));
        tvRight.setBackgroundResource(flag ? R.drawable.shape_rectangle_rule_yellow : R.drawable.shape_rectangle_rule_blue);
//        tvConfirm.setBackgroundResource(flag ? R.drawable.selector_button : R.drawable.selector_button_invoice);
        tvViewRecord.setBackgroundResource(flag ? R.drawable.shape_rectangle_stroke : R.drawable.shape_rectangle_stroke_blue);
        tvViewRecord.setTextColor(flag ? getResources().getColor(R.color.color_ffae05) : getResources().getColor(R.color.color_60aeff));
        //   可提现发票（代收代付）余额  、 可提现券码余额赋值
        tvAmount.setText(money);
        tv_can_withdrawl_coupon.setText(counponWithdrawl);

        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebViewActivity.runWebActivity(WithdrawalActivity.this,
                        flag ? "代收代付提现规则" : "发票提现规则",
                        flag ? SPUtils.getShareString(CLOSED_PAY_URL) : SPUtils.getShareString(INVOICE_URL));
            }
        });


        CommonSubscriber<BankListBean.ListBean> subscriber = new CommonSubscriber<BankListBean.ListBean>(new SubscriberListener<BankListBean.ListBean>() {
            @Override
            public void onNext(BankListBean.ListBean bank) {
                //有银行卡 ，week 不是0的时候才能提现
                //  0不可提现，  <br/> 1 可以提现  <br/>

//                tvServiceFee.setText(getString(R.string.service_fee, bank.getGrade()));
                rdbt_normal_withdrawl.setText(getString(R.string.service_fee, bank.getGrade()));
//                if (null != bank) {
//                    itemBank = bank;
//                    tvAddBank.setVisibility(View.GONE);
//                    llBankInfo.setVisibility(View.VISIBLE);
//                    Glide.with(WithdrawalActivity.this)
//                            .load(bank.getCard_logo())
//                            .into(ivBank);
//                    tvBankName.setText(itemBank.getBank_name());
//                    tvBankNum.setText(getString(R.string.bank_num, itemBank.getCard_number().substring(itemBank.getCard_number().length() - 4, itemBank.getCard_number().length())));
//                } else {
//                    tvAddBank.setVisibility(View.VISIBLE);
//                    llBankInfo.setVisibility(View.GONE);
//                }
            }

            @Override
            public void onError(String e, int code) {
                Log.e(TAG, "=====onError: " + e);
            }
        });

        BusinessUserMethods.getInstance().withdrawNew(subscriber);
        rxManager.add(subscriber);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkConfirm();
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
        //  0不可提现，  <br/> 1 可以提现  <br/>
        if (week == 0) {
            showToastMsg("周一才能提现哦");
            return;
        }

        if (Double.valueOf(money) <= 0) {
            showToastMsg("可提取金额为0");
            return;
        }

        if (null == itemBank) {
            showToastMsg("请选择银行卡");
            return;
        }

        // 此处为最初的提现接口，加入优惠券码可提现功能以后，更换了新接口
        /*
        BusinessUserMethods.getInstance().applyWithdrawal(new Subscriber<HttpRespBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(HttpRespBean httpRespBean) {
                int code = httpRespBean.getCode();
                if (code == 0) {
                    successDialog();
                } else {
                    showToastMsg(httpRespBean.getMsg());
                }
            }
        }, itemBank.getId(), flag ? 2 : 1, money);
        */
        applyWithdrawalNew();
    }


    /**
     * 提现新接口
     */
    private void applyWithdrawalNew() {
        showProgressingDialog();
        CommonSubscriber<HttpRespBean> subscriber = new CommonSubscriber<>(new SubscriberListener<HttpRespBean>() {
            @Override
            public void onNext(HttpRespBean result) {
                dismissProgressDialog();
                if (0 == result.getCode()) {
                    successDialog();
                }
            }

            @Override
            public void onError(String e, int code) {
                showToastMsg(e);
                dismissProgressDialog();
            }
        });
//        BusinessUserMethods.getInstance().applyWithdrawalNew(subscriber, itemBank.getId(), flag ? 2 : 1, money, counponWithdrawl);
        BusinessUserMethods.getInstance().applyWithdrawalNew(subscriber, itemBank.getId(), flag ? 2 : 1
                , rdbt_normal_withdrawl.isChecked()?money:"0"
                ,rdbt_coupon_withdrawl.isChecked()?counponWithdrawl:"0");
        rxManager.add(subscriber);
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
//            checkConfirm();
        }
    }

    @Override
    protected void doLogicFunc() {

    }

    class MyOnCheckedChangedListener implements CompoundButton.OnCheckedChangeListener {
        double canWithdrawlmoney = Double.parseDouble(money);
        double coupon = Double.parseDouble(counponWithdrawl);
        double canWithdrawlTotal = canWithdrawlmoney + coupon;
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (rdbt_coupon_withdrawl.isChecked() && rdbt_normal_withdrawl.isChecked()) {
                tv_can_withdrawl_count.setText("合计￥ " + canWithdrawlTotal);
            } else {
                if (rdbt_normal_withdrawl.isChecked() && !rdbt_coupon_withdrawl.isChecked()) {
                    tv_can_withdrawl_count.setText("合计￥ " + money);
                } else if (!rdbt_normal_withdrawl.isChecked() && rdbt_coupon_withdrawl.isChecked()) {
                    tv_can_withdrawl_count.setText("合计￥ " + counponWithdrawl);
                } else {
                    tv_can_withdrawl_count.setText("合计￥ 0.00");
                }
            }
            checkConfirm();
        }
    }

    /**
     * 校验可提现按钮是否可点击        <br/>
     * 业务逻辑：      <br/>
     * 当可提现发票或代收代付的余额大于0或可提现券余额大于0，且选择了银行卡，且在可提现日期内时        <br/>
     * 提现按钮可点击       <br/>
     * 当可提现的两项目有其中一项不大于0，两个选项同时选，提现可点击。       <br/>
     * 当两个选项都不大于0时，提现按钮不可点击。       <br/>
     * 当单独选一个选项时，可提现余额不大于0时，提现按钮不可点击。       <br/>
     *
     *     //       //  原来的逻辑
     *     //        if ((null != itemBank && week == 1 && Double.valueOf(money) > 0)) {
     *     //            tvConfirm.setClickable(true);
     *     //            tvConfirm.setBackgroundResource(flag ? R.drawable.selector_button : R.drawable.selector_button_invoice);
     *     //        } else {
     *     //            tvConfirm.setClickable(false);
     *     //            tvConfirm.setBackgroundResource(R.drawable.shape_rectangle_pressed);
     *     //        }
     */
    private void checkConfirm() {
        if (null != itemBank && 1 == week) {  // 提现要求： 必须选择了银行卡且在可提现期内
            if (rdbt_normal_withdrawl.isChecked() && rdbt_coupon_withdrawl.isChecked()){  // 两个选项都选择了
                setConfirmBtState( Double.parseDouble(money) > 0 || Double.parseDouble(counponWithdrawl) > 0);
            }else if (rdbt_normal_withdrawl.isChecked() && !rdbt_coupon_withdrawl.isChecked()){ // 只选择了发票（代收代付）提现  未选择券码提现
                setConfirmBtState( Double.parseDouble(money) > 0 );
            }else if (!rdbt_normal_withdrawl.isChecked() && rdbt_coupon_withdrawl.isChecked()){ // 只选择了券码提现  未选择发票（代收代付）提现
                setConfirmBtState( Double.parseDouble(counponWithdrawl) > 0 );
            }else {
                setConfirmBtState(false);
            }
        } else {
            tvConfirm.setClickable(false);
            tvConfirm.setBackgroundResource(R.drawable.shape_rectangle_pressed);
        }
    }

    /**
     *  设置确认提现按钮的状态     <br/>
     * @param canWithdraw  是否可提现。     <br/>  true :  可以。     <br/>  false  :  不可以。     <br/>
     */
    private void setConfirmBtState(boolean canWithdraw){
        tvConfirm.setClickable(canWithdraw);
        tvConfirm.setBackgroundResource(canWithdraw ? (flag ? R.drawable.selector_button : R.drawable.selector_button_invoice) : R.drawable.shape_rectangle_pressed);
    }

    /**
     * 设置CheckBox图片与文字的距离       <br/>
     * @param checkBox   checkBox    <br/>
     * @param paddingLeft  距离    <br/>
     * @param resId   要设置的图片   <br/>
     */
    private void setCheckBoxPading(CheckBox checkBox,int paddingLeft,int resId) {
        checkBox.setCompoundDrawables(getResources().getDrawable(resId), null, null, null); //设置左图标
        checkBox.setCompoundDrawablePadding((int)SizeUtils.px2dp(context,paddingLeft));
    }


}
