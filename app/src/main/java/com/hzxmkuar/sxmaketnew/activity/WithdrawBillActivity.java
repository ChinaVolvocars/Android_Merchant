package com.hzxmkuar.sxmaketnew.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.adapter.helper.IRecyclerViewHelper;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.WithdrawlBillEntity;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.EmptyUtils;
import com.common.utils.UIUtils;
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
    private static final String TAG = "WithdrawBillActivity";
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

    /**
     * 类型  <br/>
     * 1 为发票提现  <br/>
     * 2 为代收代付  <br/>
     */
    private int mClickType = 1;
    private List<WithdrawlBillEntity.WithdrawlBillItemEntity> billEntities = new ArrayList<>();
    private String withdrawMoneny = "0.00";

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
        mClickType = 2;
        getWithdrawalBillList(mClickType);
        initAdapter();

    }

    @Override
    protected void doLogicFunc() {
        changeViewState(true);
        attachClickListener(rlPayForOhter);
        attachClickListener(rlInvoiceWithdraw);
        attachClickListener(ivBack);
    }

    @Override
    protected void setStatusBar() {

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
            mClickType = 2;
            withdrawBillAdapter.clearData();
            getWithdrawalBillList(mClickType);

        } else if (view.getId() == rlInvoiceWithdraw.getId()) {
            changeViewState(false);
            mClickType = 1;
            withdrawBillAdapter.clearData();
            getWithdrawalBillList(mClickType);

        } else if (view.getId() == ivBack.getId()) {
            finish();
        }
        withdrawBillAdapter.changeClickType(mClickType);
    }

    /**
     * 初始化数据
     */
    private void getWithdrawalBillList(final int clickType) {
        CommonSubscriber<WithdrawlBillEntity> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object obj) {
                statusContent();
                xRecyclerviewPayforother.loadMoreComplete();
                WithdrawlBillEntity datasEntity = (WithdrawlBillEntity) obj;
                if (mClickType == 2) {
                    withdrawMoneny = datasEntity.getAgent_business_count();
                } else {
                    withdrawMoneny = datasEntity.getInvoice_count();
                }

                //43 61
                SpannableStringBuilder stringWithdrawMoneny = new SpannableStringBuilder();
                stringWithdrawMoneny.append(getString(R.string.format_total_money, withdrawMoneny));
                RelativeSizeSpan sizeSpan01 = new RelativeSizeSpan(1.0f);
                RelativeSizeSpan sizeSpan02 = new RelativeSizeSpan(1.418f);
                stringWithdrawMoneny.setSpan(sizeSpan01, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                stringWithdrawMoneny.setSpan(sizeSpan02, 1, stringWithdrawMoneny.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

                tvPayforOtherBeforThePayment.setText(stringWithdrawMoneny);


                List<WithdrawlBillEntity.WithdrawlBillItemEntity> datasEntityList = datasEntity.getList();
                if (null != datasEntityList) {
                    billEntities.addAll(datasEntityList);
                }
                // 下拉刷新
                if (mIsRefreshOrLoadMore == 0) {
                    xRecyclerviewPayforother.refreshComplete();
                    withdrawBillAdapter.clearData();
                }

                if (EmptyUtils.isNotEmpty(datasEntityList)) {
                    billEntities = datasEntityList;
                    withdrawBillAdapter.addAll(billEntities);
                    statusContent();
                }
                if (EmptyUtils.isEmpty(datasEntityList)) {
                    xRecyclerviewPayforother.setNoMore(true);
                } else {
                    mIsHasNoData = datasEntityList.size() < mPageSize;
                    xRecyclerviewPayforother.setNoMore(mIsHasNoData);
                }
            }

            @Override
            public void onError(String e, int code) {
                statusError();
                showToastMsg(e);
                xRecyclerviewPayforother.setNoMore(true);
                xRecyclerviewPayforother.refreshComplete();
                xRecyclerviewPayforother.loadMoreComplete();
            }
        });
        BusinessUserMethods.getInstance().withdrawCredit(subscriber, String.valueOf(mClickType), mPageIndex);
        rxManager.add(subscriber);

    }


    /**
     * 初始化适配器
     */
    private void initAdapter() {
        Log.i(TAG, "initAdapter:        条目总数：       " + billEntities.size());
        if (null == withdrawBillAdapter) {
            withdrawBillAdapter = new WithdrawBillAdapter(context, mClickType, billEntities);
        }
        IRecyclerViewHelper.init().setRecycleLineLayout(context, LinearLayoutManager.VERTICAL, xRecyclerviewPayforother);
        xRecyclerviewPayforother.setHasFixedSize(true);
        xRecyclerviewPayforother.setAdapter(withdrawBillAdapter);
        xRecyclerviewPayforother.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPageIndex = 1;
                mIsRefreshOrLoadMore = 0;
                getWithdrawalBillList(mClickType);
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
                getWithdrawalBillList(mClickType);

            }
        });
    }

    private void changeViewState(boolean isDefult) {
        if (isDefult) {
            viewPoint01.setBackgroundResource(R.drawable.circle_color_yellow_99fcc80a);
            viewPoint02.setBackgroundResource(R.drawable.circle_color_yellow_fcc80a);
            viewPoint03.setBackgroundResource(R.drawable.circle_color_yellow_fcc80a);
            viewPoint04.setBackgroundResource(R.drawable.circle_color_yellow_99fcc80a);
            tvText.setTextColor(UIUtils.getColor(R.color.color_fcc80a));
            viewBottomlinePayForOhter.setVisibility(View.VISIBLE);

            viewPoint05.setBackgroundResource(R.drawable.circle_color_black_994c4c4c);
            viewPoint06.setBackgroundResource(R.drawable.circle_color_black_4c4c4c);
            viewPoint07.setBackgroundResource(R.drawable.circle_color_black_4c4c4c);
            viewPoint08.setBackgroundResource(R.drawable.circle_color_black_994c4c4c);
            tv.setTextColor(UIUtils.getColor(R.color.color_1e1e1e));
            viewBottomlineWithdraw.setVisibility(View.INVISIBLE);

            tvBeforThePayment.setBackgroundResource(R.mipmap.before_the_payment_bg);
            tvValueContent.setBackgroundResource(R.mipmap.payfor_other_befor_the_paymentbg);
            tvHaveToAccount.setBackgroundResource(R.mipmap.payfor_other_have_to_account_bg);


        } else {
            viewPoint01.setBackgroundResource(R.drawable.circle_color_black_994c4c4c);
            viewPoint02.setBackgroundResource(R.drawable.circle_color_black_4c4c4c);
            viewPoint03.setBackgroundResource(R.drawable.circle_color_black_4c4c4c);
            viewPoint04.setBackgroundResource(R.drawable.circle_color_black_994c4c4c);
            tvText.setTextColor(UIUtils.getColor(R.color.color_1e1e1e));
            viewBottomlinePayForOhter.setVisibility(View.INVISIBLE);

            viewPoint05.setBackgroundResource(R.drawable.circle_color_blue_9955a7fb);
            viewPoint06.setBackgroundResource(R.drawable.circle_color_blue_55a7fb);
            viewPoint07.setBackgroundResource(R.drawable.circle_color_blue_55a7fb);
            viewPoint08.setBackgroundResource(R.drawable.circle_color_blue_9955a7fb);
            tv.setTextColor(UIUtils.getColor(R.color.color_55a7fb));
            viewBottomlineWithdraw.setVisibility(View.VISIBLE);

            tvBeforThePayment.setBackgroundResource(R.mipmap.invoice_withdrawal_befor_bg);
            tvValueContent.setBackgroundResource(R.mipmap.invoice_withdrawawl_bg);
            tvHaveToAccount.setBackgroundResource(R.mipmap.invoice_withdrawal_have_to_account_bg);

        }

    }
}
