package com.hzxmkuar.sxmaketnew.home;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.common.adapter.BaseFragmentAdapter;
import com.common.adapter.helper.IRecyclerViewHelper;
import com.common.event.WidthDrawConstants;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.FBean;
import com.common.retrofit.entity.result.FinanceDetailEntity;
import com.common.retrofit.entity.result.FiniBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.EmptyUtils;
import com.common.utils.ResourceUtils;
import com.common.utils.SizeUtils;
import com.common.widget.indicator.ColorTransitionPagerTitleView;
import com.common.widget.indicator.CommonNavigator;
import com.common.widget.indicator.CommonNavigatorAdapter;
import com.common.widget.indicator.IPagerIndicator;
import com.common.widget.indicator.IPagerTitleView;
import com.common.widget.indicator.LinePagerIndicator;
import com.common.widget.indicator.MagicIndicator;
import com.common.widget.indicator.SimplePagerTitleView;
import com.common.widget.indicator.ViewPagerHelper;
import com.common.widget.listview.NoPreloadViewPager;
import com.common.widget.recyclerview.refresh.recycleview.XRecyclerView;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.activity.WithdrawActivity;
import com.hzxmkuar.sxmaketnew.adapter.JEAdapter;
import com.hzxmkuar.sxmaketnew.fragment.JFOneFragment;
import java.util.ArrayList;
import java.util.List;


/**
 * 财务管理 <br/>
 * Created by STH on 2017/8/1.
 */
public class FinancialManagerActivity extends BaseMvpActivity {
    private static final String TAG = "FinancialManagerActivit";
    /**
     * 消费交易抵扣额
     */
    private TextView mTvMonetaryDeduction;
    /**
     * <br/>
     * 消费交易抵扣额（容器）
     */
    private LinearLayout mLlMonetaryDeduction;
    /**
     * <br/>
     * 鑫豆总数
     */
    private TextView mTvXdAount;
    /**
     * 申请提现
     */
    private TextView mTvApplyTakeCash;
    /**
     * 可提现金额
     */
    private TextView mTvCanTakeCash;
    /**
     * 目前账户可用余额
     */
    private TextView mTvAccountBalance;
    /**
     * <br/>
     * 消费记录ViewPager
     */
    private NoPreloadViewPager mVpConsumptionRecord;
    /**
     * <br/>
     * 消费记录ViewPager的指示器
     */
    private MagicIndicator mIndicatorConsumptionRecord;
    private BaseFragmentAdapter fragmentAdapter;
    /**
     * <br/>
     * 可提现标签的容器
     */
    private LinearLayout mLlCanTakeCashContent;
    private JEAdapter canWithdrawAdapter;
    private XRecyclerView recyclerView;
    private List<FiniBean.ListBean> beansList = new ArrayList<>();
    private ImageView back;
    private TextView tv_conversion;
    private String takeCashState = "暂时不能提现";
    SparseArray<String> indicatorTitles = new SparseArray<>();
    /**
     * 可提现鑫豆
     */
    private String canTakeXindous ="";
    /**
     *  提现让利比例
     */
    private String widthDrawRation = "";

    @Override
    protected void setStatusBar() {
    }

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_financial_manager;
    }

    @Override
    protected void onViewCreated() {
        back = (ImageView) findViewById(R.id.back);
        mVpConsumptionRecord = (NoPreloadViewPager) findViewById(R.id.viewpager);
        mIndicatorConsumptionRecord = (MagicIndicator) findViewById(R.id.indicator_consumption_record);
        mTvMonetaryDeduction = (TextView) findViewById(R.id.tv_monetary_deduction);
        mLlMonetaryDeduction = (LinearLayout) findViewById(R.id.ll_monetary_deduction);
        mTvXdAount = (TextView) findViewById(R.id.tv_xd_amount);
        mTvCanTakeCash = (TextView) findViewById(R.id.tv_can_take_cash);
        mTvApplyTakeCash = (TextView) findViewById(R.id.tv_apply_take_cash);
        tv_conversion = (TextView) findViewById(R.id.tv_conversion);
//        mText = (TextView) findViewById(R.id.text);
        mTvAccountBalance = (TextView) findViewById(R.id.tv_accunt_balance);
        mLlCanTakeCashContent = (LinearLayout) findViewById(R.id.ll_can_take_cash_content);
        recyclerView = (XRecyclerView) findViewById(R.id.recyclerview);
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(mTvMonetaryDeduction);
        attachClickListener(mTvCanTakeCash);
        attachClickListener(mTvApplyTakeCash);
        attachClickListener(back);
        mTvMonetaryDeduction.setClickable(false);
        mTvCanTakeCash.setClickable(true);
//        mLlMonetaryDeduction.setVisibility(View.VISIBLE);
//        mLlCanTakeCashContent.setVisibility(View.GONE);
        mLlMonetaryDeduction.setVisibility(View.GONE);
        mLlCanTakeCashContent.setVisibility(View.VISIBLE);
        initIndicator();
        setRecyclerView();
        statusLoading();
        getConsumptionRecord();
        getFinanceList();
    }

    /**
     * <br/>
     * 初始化指示器
     */
    private void initIndicator() {
        indicatorTitles.append(0, "消费抵账鑫豆提现申请");
        indicatorTitles.append(1, "现金到账");
        indicatorTitles.append(2, "今日销售");
        setFragmentList();
        initIndicatorView();
        mVpConsumptionRecord.setCurrentItem(0);
    }
    /**
     * 初始化RecyclerView
     */
    private void setRecyclerView() {
        canWithdrawAdapter = new JEAdapter(context, beansList);
        IRecyclerViewHelper.init().setRecycleGridLayout(context, recyclerView, 1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(canWithdrawAdapter);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPageIndex = 1;
                mIsRefreshOrLoadMore = 0;
                getConsumptionRecord();
                getFinanceList();
            }

            @Override
            public void onLoadMore() {
                if (mIsHasNoData) {
                    recyclerView.loadMoreComplete();
                    recyclerView.setNoMore(true);
                    return;
                }
                mPageIndex++;
                mIsRefreshOrLoadMore = 1;
                getFinanceList();
            }
        });
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == mTvApplyTakeCash.getId()) {
            if (!EmptyUtils.isEmpty(takeCashState)) {
                showToastMsg(takeCashState);
            }else {
                //提现
//                gotoActivity(WithdrawActivity.class);
                Intent intent = new Intent(FinancialManagerActivity.this,WithdrawActivity.class);
                intent.putExtra(WidthDrawConstants.CAN_TAKE_XINDOUS,canTakeXindous);
                intent.putExtra(WidthDrawConstants.WIDTHDRAW_RATION,widthDrawRation);
                startActivity(intent);

            }
        } else if (view.getId() == mTvMonetaryDeduction.getId()) {
            mTvMonetaryDeduction.setClickable(false);
            mTvCanTakeCash.setClickable(true);
            mTvMonetaryDeduction.setBackgroundResource(R.drawable.jx_10_w);
            mTvMonetaryDeduction.setTextColor(getResources().getColor(R.color.black));
            mTvCanTakeCash.setTextColor(getResources().getColor(R.color.normal_text_color));
            mTvCanTakeCash.setBackgroundResource(R.drawable.jx_10_w_nom);
//            mLlMonetaryDeduction.setVisibility(View.VISIBLE);
//            mLlCanTakeCashContent.setVisibility(View.GONE);
            mLlMonetaryDeduction.setVisibility(View.GONE);
            mLlCanTakeCashContent.setVisibility(View.VISIBLE);
        } else if (view.getId() == mTvCanTakeCash.getId()) {
            mTvMonetaryDeduction.setClickable(true);
            mTvCanTakeCash.setClickable(false);
            mTvCanTakeCash.setBackgroundResource(R.drawable.jx_10_w);
            mTvMonetaryDeduction.setBackgroundResource(R.drawable.jx_10_w_nom);
            mTvCanTakeCash.setTextColor(getResources().getColor(R.color.black));
            mTvMonetaryDeduction.setTextColor(getResources().getColor(R.color.normal_text_color));
//            mLlMonetaryDeduction.setVisibility(View.GONE);
//            mLlCanTakeCashContent.setVisibility(View.VISIBLE);
            mLlMonetaryDeduction.setVisibility(View.VISIBLE);
            mLlCanTakeCashContent.setVisibility(View.GONE);
        } else if (view.getId() == back.getId()) {
            finish();
        }
    }

    /**
     * 拉取消费抵账鑫豆提现申请 获取消费记录清单
     */
    private void getConsumptionRecord() {
        CommonSubscriber<FinanceDetailEntity> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object obj) {
                statusContent();
                FinanceDetailEntity entity = (FinanceDetailEntity) obj;
//                Log.i(TAG, "onNext: " + entity.toString());
                if (null != entity){
                    mTvXdAount.setText(entity.getXindou());

                    mTvAccountBalance.setText(entity.getMoney());
                    takeCashState = entity.getWeek();
                    if (!EmptyUtils.isEmpty(entity.getAppreciation()) && !EmptyUtils.isEmpty(entity.getProportion())){
                        double profits = 1 - Double.valueOf(entity.getProportion());
                        double profitsProfit = profits * 100;
                        String profitsProfitShow = String.valueOf(profitsProfit);
                        tv_conversion.setText("提现换算公式：1豆x" + entity.getAppreciation() + "x" + profitsProfitShow.substring(0,2)+"%" + "=" + entity.getRatio());
                        canTakeXindous = entity.getXindou();
                        widthDrawRation = entity.getRatio();
                    }
                }
            }

            @Override
            public void onError(String e, int code) {
                statusContent();
                showToastMsg(e);
            }
        });
        BusinessUserMethods.getInstance().financeDetailNew(subscriber);
        rxManager.add(subscriber);
    }


    /**
     * 获取财务信息列表信息
     */
    private void getFinanceList() {
        CommonSubscriber<FiniBean> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                statusContent();
                recyclerView.loadMoreComplete();
                FiniBean beans = (FiniBean) o;
                List<FiniBean.ListBean> beanList = beans.getList();
                if (EmptyUtils.isNotEmpty(beanList)){
                    beansList = beanList;
                }
                // 下拉刷新
                if (mIsRefreshOrLoadMore == 0) {
                    recyclerView.refreshComplete();
                    canWithdrawAdapter.clear();
                }

                if (EmptyUtils.isNotEmpty(beanList)) {
                    beansList = beanList;
                    canWithdrawAdapter.addAll(beansList);
                    statusContent();
                }

                // 接口未做分页直接调用设置列表无更多
                //recyclerView.setNoMore(true);

                // 接口未做分页 因此注释
                if (EmptyUtils.isEmpty(beanList)) {
                    recyclerView.setNoMore(true);
                } else {
                    mIsHasNoData = beanList.size() < mPageSize;
                    recyclerView.setNoMore(mIsHasNoData);
                }
            }

            @Override
            public void onError(String e, int code) {
                statusError();
                showToastMsg(e);
                recyclerView.setNoMore(true);
                recyclerView.refreshComplete();
                recyclerView.loadMoreComplete();
            }
        });
        BusinessUserMethods.getInstance().financeList(subscriber, mPageIndex);
        rxManager.add(subscriber);
    }

    /**
     * 获取可提现的总金额
     *
     * @param appreciation 鑫豆的升值比例
     * @param proportion   商家的让利比例
     */
    private String getTotalMoney(String appreciation, String proportion) {
        double amountDouble = Double.valueOf(appreciation) * Double.valueOf(proportion);
        return String.valueOf(amountDouble);
    }

    /**
     * 初始化fragment
     */
    private void setFragmentList() {
        List<Fragment> fragmentList = new ArrayList<>();
        for (int t = 0; t < 3; t++) {
            fragmentList.add(new JFOneFragment(t));
        }
        if (fragmentAdapter == null) {
            fragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragmentList, indicatorTitles);
        } else {
            fragmentAdapter.setFragments(getSupportFragmentManager(), fragmentList, indicatorTitles);
        }
        mVpConsumptionRecord.setAdapter(fragmentAdapter);
    }

    private void initIndicatorView() {
        CommonNavigator navigator = new CommonNavigator(context);
        navigator.setAdjustMode(true);
        navigator.setFollowTouch(true);
        navigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return indicatorTitles.size();
            }

            @Override
            public IPagerTitleView getTitleView(final Context context, final int index) {
                SimplePagerTitleView titleView = new ColorTransitionPagerTitleView(context);
                titleView.setText(indicatorTitles.get(index));
                titleView.setNormalColor(ResourceUtils.getColor(context, R.color.normal_text_color));
                titleView.setSelectedColor(ResourceUtils.getColor(context, R.color.base_color));
                titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
                titleView.setmSelectedSize((int) SizeUtils.dp2px(context, 8));
                titleView.setmNormalSize((int) SizeUtils.dp2px(context, 8));
                titleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mVpConsumptionRecord.setCurrentItem(index);
                    }
                });
                return titleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setLineHeight(SizeUtils.dp2px(context, 2));
                indicator.setLineWidth(SizeUtils.dp2px(context, 40));
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setColors(ResourceUtils.getColor(context, R.color.base_color));
                return indicator;
            }
        });
        mIndicatorConsumptionRecord.setNavigator(navigator);
        ViewPagerHelper.bind(mIndicatorConsumptionRecord, mVpConsumptionRecord);
    }
}
