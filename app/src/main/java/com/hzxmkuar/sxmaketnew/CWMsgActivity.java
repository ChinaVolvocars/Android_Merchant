package com.hzxmkuar.sxmaketnew;

import android.content.Context;
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
import com.common.utils.StringUtils;
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
import com.hzxmkuar.sxmaketnew.fragment.adapter.JEAdapter;
import com.hzxmkuar.sxmaketnew.fragment.fragment.JFOneFragment;
import java.util.ArrayList;
import java.util.List;


/**
 * 财务管理 <br/>
 * Created by STH on 2017/8/1.
 */
public class CWMsgActivity extends BaseMvpActivity {
    private static final String TAG = "CWMsgActivity";

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
     *
     * <br/>
     * 消费记录ViewPager的指示器
     */
    private MagicIndicator mIndicatorConsumptionRecord;
    private BaseFragmentAdapter fragmentAdapter;
//    private TextView mText;
    /**
     * <br/>
     * 可提现标签的容器
     */
    private LinearLayout mLlCanTakeCashContent;
    private JEAdapter adapter;
    private XRecyclerView recyclerView;
    private List<FBean> bean = new ArrayList<>();
    private ImageView back;
    private TextView tv_conversion;

    @Override
    protected void setStatusBar() {
    }

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cw_msg;
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
        mLlMonetaryDeduction.setVisibility(View.VISIBLE);
        mLlCanTakeCashContent.setVisibility(View.GONE);
        initIndicator();
        setRecyclerView();
        statusLoading();
        getFinanceList();
        getConsumptionRecord();
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
     *  初始化RecyclerView
     */
    private void setRecyclerView() {
        adapter = new JEAdapter(context, bean);
        IRecyclerViewHelper.init().setRecycleGridLayout(context, recyclerView, 1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPageIndex = 1;
                mIsRefreshOrLoadMore = 0;
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

    SparseArray<String> indicatorTitles = new SparseArray<>();


    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == mTvApplyTakeCash.getId()) {
            //提现
            gotoActivity(TXActivity.class);
        } else if (view.getId() == mTvMonetaryDeduction.getId()) {
            mTvMonetaryDeduction.setClickable(false);
            mTvCanTakeCash.setClickable(true);
            mTvMonetaryDeduction.setBackgroundResource(R.drawable.jx_10_w);
            mTvMonetaryDeduction.setTextColor(getResources().getColor(R.color.black));
            mTvCanTakeCash.setTextColor(getResources().getColor(R.color.normal_text_color));
            mTvCanTakeCash.setBackgroundResource(R.drawable.jx_10_w_nom);
            mLlMonetaryDeduction.setVisibility(View.VISIBLE);
            mLlCanTakeCashContent.setVisibility(View.GONE);
        } else if (view.getId() == mTvCanTakeCash.getId()) {
            mTvMonetaryDeduction.setClickable(true);
            mTvCanTakeCash.setClickable(false);
            mTvCanTakeCash.setBackgroundResource(R.drawable.jx_10_w);
            mTvMonetaryDeduction.setBackgroundResource(R.drawable.jx_10_w_nom);
            mTvCanTakeCash.setTextColor(getResources().getColor(R.color.black));
            mTvMonetaryDeduction.setTextColor(getResources().getColor(R.color.normal_text_color));
            mLlMonetaryDeduction.setVisibility(View.GONE);
            mLlCanTakeCashContent.setVisibility(View.VISIBLE);
        } else if (view.getId() == back.getId()) {
            finish();
        }
    }

    /**
     * 拉取消费抵账鑫豆提现申请 获取消费记录清单
     */
    private void getConsumptionRecord() {
        CommonSubscriber<FBean> subscriber = new CommonSubscriber<>(new SubscriberListener() {
//        CommonSubscriber<FinanceDetailEntity> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                statusContent();
                FBean entity = (FBean) o;
//                FinanceDetailEntity entity = (FinanceDetailEntity) o;
                mTvXdAount.setText(entity.getXindou());
                Log.i(TAG, "onNext:       fBean:      "+entity.toString());
                if (!EmptyUtils.isEmpty(entity.getAppreciation()) && !EmptyUtils.isEmpty(entity.getProportion())){
                    double profits = 1 - Double.valueOf(entity.getProportion());
                    double profitsProfit = profits * 100;
                    String profitsProfitShow = String.valueOf(profitsProfit);
                    tv_conversion.setText("提现换算公式：1豆x" + entity.getAppreciation() + "x" + profitsProfitShow.substring(0,2)+"%" + "=" + entity.getRatio());
                }
                mTvAccountBalance.setText(entity.getMoney());
            }

            @Override
            public void onError(String e, int code) {
                statusContent();
                showToastMsg(e);
            }
        });
        BusinessUserMethods.getInstance().financeDetail(subscriber);
//        BusinessUserMethods.getInstance().financeDetailNew(subscriber);
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
     *  获取财务信息
     */
    private void getFinanceList() {
        CommonSubscriber<FiniBean> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                statusContent();
                recyclerView.loadMoreComplete();
                FiniBean beans = (FiniBean) o;
                List<FiniBean.ListBean> list = beans.getList();
                List<FBean> beanList = new ArrayList<>();
                for (FiniBean.ListBean beanss : list) {
                    beanList.add(new FBean(beanss.getWithdraw_money(), beanss.getCreate_time()));
                }
                // 下拉刷新
                if (mIsRefreshOrLoadMore == 0) {
                    recyclerView.refreshComplete();
                    adapter.clear();
                }

                if (EmptyUtils.isNotEmpty(beanList)) {
                    bean = beanList;
                    adapter.addAll(bean);
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
