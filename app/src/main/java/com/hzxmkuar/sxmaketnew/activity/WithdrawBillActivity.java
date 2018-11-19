package com.hzxmkuar.sxmaketnew.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.adapter.helper.IRecyclerViewHelper;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.WithdrawlBillEntity;
import com.common.widget.recyclerview.refresh.recycleview.XRecyclerView;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.adapter.WithdrawBillAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 提现账款
 * Created by jc on 2018/11/14.
 */
public class WithdrawBillActivity extends BaseMvpActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.view_point01)
    TextView viewPoint01;
    @BindView(R.id.view_point02)
    TextView viewPoint02;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.view_point03)
    TextView viewPoint03;
    @BindView(R.id.view_point04)
    TextView viewPoint04;
    @BindView(R.id.view_bottomline_pay_for_ohter)
    TextView viewBottomlinePayForOhter;
    @BindView(R.id.rl_pay_for_ohter)
    LinearLayout rlPayForOhter;
    @BindView(R.id.view_point05)
    TextView viewPoint05;
    @BindView(R.id.view_point06)
    TextView viewPoint06;
    @BindView(R.id.tv_)
    TextView tv;
    @BindView(R.id.view_point07)
    TextView viewPoint07;
    @BindView(R.id.view_point08)
    TextView viewPoint08;
    @BindView(R.id.view_bottomline_withdraw)
    TextView viewBottomlineWithdraw;
    @BindView(R.id.rl_invoice_withdraw)
    LinearLayout rlInvoiceWithdraw;
    @BindView(R.id.tv_befor_the_payment)
    TextView tvBeforThePayment;
    @BindView(R.id.tv_payfor_other_befor_the_payment)
    TextView tvPayforOtherBeforThePayment;
    @BindView(R.id.tv_value_content)
    LinearLayout tvValueContent;
    @BindView(R.id.tv_have_to_account)
    TextView tvHaveToAccount;
    @BindView(R.id.x_recyclerview_payforother)
    XRecyclerView xRecyclerviewPayforother;

    private WithdrawBillAdapter withdrawBillAdapter;
    private List<WithdrawlBillEntity> billEntities = new ArrayList<>();

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_withdraw_bill;
    }

    @Override
    protected void onViewCreated() {
        tvTitle.setText("提现账款");
    }

    @Override
    protected void doLogicFunc() {
        changeViewState(true);
        attachClickListener(rlPayForOhter);
        attachClickListener(rlInvoiceWithdraw);
        attachClickListener(ivBack);
        getWithdrawalBillList(0);

    }

    /**
     * 初始化适配器
     */
    private void initAdapter(int clickType) {
        withdrawBillAdapter = new WithdrawBillAdapter(context, clickType, billEntities);
        IRecyclerViewHelper.init().setRecycleLineLayout(context, LinearLayoutManager.VERTICAL, xRecyclerviewPayforother);
        xRecyclerviewPayforother.setHasFixedSize(true);
        xRecyclerviewPayforother.setAdapter(withdrawBillAdapter);
        xRecyclerviewPayforother.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPageIndex = 1;
                mIsRefreshOrLoadMore = 0;
            }

            @Override
            public void onLoadMore() {
                if (mIsHasNoData) {
                    xRecyclerviewPayforother.loadMoreComplete();
                    xRecyclerviewPayforother.setNoMore(true);
                    return;
                }
                mPageIndex++;
                mIsRefreshOrLoadMore = 1;
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == rlPayForOhter.getId()) {
            changeViewState(true);
            getWithdrawalBillList(0);
        } else if (view.getId() == rlInvoiceWithdraw.getId()) {
            changeViewState(false);
            getWithdrawalBillList(1);
        } else if (view.getId() == ivBack.getId()) {
            finish();
        }
    }

    /**
     * 初始化数据
     */
    private void getWithdrawalBillList(int clickType) {

        for (int i = 0; i < 20; i++) {
            WithdrawlBillEntity entity = new WithdrawlBillEntity();
            entity.setDate("2018-08-08  " + i);
            entity.setMoney(String.valueOf(50 + i));
            billEntities.add(entity);
        }
        initAdapter(clickType);
    }

    @Override
    protected void setStatusBar() {

    }

    private void changeViewState(boolean isDefult) {
        if (isDefult) {
            viewPoint01.setBackgroundResource(R.drawable.circle_color_yellow_99fcc80a);
            viewPoint02.setBackgroundResource(R.drawable.circle_color_yellow_fcc80a);
            viewPoint03.setBackgroundResource(R.drawable.circle_color_yellow_fcc80a);
            viewPoint04.setBackgroundResource(R.drawable.circle_color_yellow_99fcc80a);
            viewBottomlinePayForOhter.setVisibility(View.VISIBLE);

            viewPoint05.setBackgroundResource(R.drawable.circle_color_black_994c4c4c);
            viewPoint06.setBackgroundResource(R.drawable.circle_color_black_4c4c4c);
            viewPoint07.setBackgroundResource(R.drawable.circle_color_black_4c4c4c);
            viewPoint08.setBackgroundResource(R.drawable.circle_color_black_994c4c4c);
            viewBottomlineWithdraw.setVisibility(View.INVISIBLE);

            tvBeforThePayment.setBackgroundResource(R.mipmap.before_the_payment_bg);
            tvPayforOtherBeforThePayment.setText("100.00");
            tvValueContent.setBackgroundResource(R.mipmap.payfor_other_befor_the_paymentbg);
            tvHaveToAccount.setBackgroundResource(R.mipmap.payfor_other_have_to_account_bg);
        } else {
            viewPoint01.setBackgroundResource(R.drawable.circle_color_black_994c4c4c);
            viewPoint02.setBackgroundResource(R.drawable.circle_color_black_4c4c4c);
            viewPoint03.setBackgroundResource(R.drawable.circle_color_black_4c4c4c);
            viewPoint04.setBackgroundResource(R.drawable.circle_color_black_994c4c4c);
            viewBottomlinePayForOhter.setVisibility(View.INVISIBLE);

            viewPoint05.setBackgroundResource(R.drawable.circle_color_blue_9955a7fb);
            viewPoint06.setBackgroundResource(R.drawable.circle_color_blue_55a7fb);
            viewPoint07.setBackgroundResource(R.drawable.circle_color_blue_55a7fb);
            viewPoint08.setBackgroundResource(R.drawable.circle_color_blue_9955a7fb);
            viewBottomlineWithdraw.setVisibility(View.VISIBLE);

            tvBeforThePayment.setBackgroundResource(R.mipmap.invoice_withdrawal_befor_bg);
            tvPayforOtherBeforThePayment.setText("200.00");
            tvValueContent.setBackgroundResource(R.mipmap.invoice_withdrawawl_bg);
            tvHaveToAccount.setBackgroundResource(R.mipmap.invoice_withdrawal_have_to_account_bg);
        }
    }
}
