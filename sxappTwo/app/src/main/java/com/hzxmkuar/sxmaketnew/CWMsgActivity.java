package com.hzxmkuar.sxmaketnew;

import android.content.Context;
import android.support.v4.app.Fragment;
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
import com.hzxmkuar.sxmaketnew.fragment.adapter.JEAdapter;
import com.hzxmkuar.sxmaketnew.fragment.fragment.JFOneFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * 财务管理 <br/>
 * Created by STH on 2017/8/1.
 */
public class CWMsgActivity extends BaseMvpActivity {

    /**
     * 消费交易抵扣额
     */
    private TextView mTvMonetaryDeduction;
    /**
     * 消费交易抵扣额（容器）
     */
    private LinearLayout mLlMonetaryDeduction;
    /**
     *  鑫豆总数
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

    private NoPreloadViewPager viewPager;
    private MagicIndicator tabIndicator;
    private BaseFragmentAdapter fragmentAdapter;
    private TextView mText;
    private LinearLayout mMLl_two;
    private JEAdapter adapter;
    private XRecyclerView recyclerView;
    private List<FBean> bean = new ArrayList<>();
    private ImageView back;

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
        viewPager = (NoPreloadViewPager) findViewById(R.id.viewpager);
        tabIndicator = (MagicIndicator) findViewById(R.id.tab_indicator);
        mTvMonetaryDeduction = (TextView) findViewById(R.id.tv_monetary_deduction);
        mLlMonetaryDeduction = (LinearLayout) findViewById(R.id.ll_monetary_deduction);
        mTvXdAount = (TextView) findViewById(R.id.tv_xd_amount);
        mTvCanTakeCash = (TextView) findViewById(R.id.tv_can_take_cash);
        mTvApplyTakeCash = (TextView) findViewById(R.id.tv_apply_take_cash);



        mText = (TextView) findViewById(R.id.text);
        back = (ImageView) findViewById(R.id.back);
        mTvAccountBalance = (TextView) findViewById(R.id.tv_accunt_balance);
        mMLl_two = (LinearLayout) findViewById(R.id.ll_two);
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
        mMLl_two.setVisibility(View.GONE);
        initIndicator();
        setRecyclerView();
        statusLoading();
        goToHttpReq();
        goToHttpReqss();
    }

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
                goToHttpReq();
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
                goToHttpReq();
            }
        });
    }

    SparseArray<String> titles = new SparseArray<>();

    private void initIndicator() {
        titles.append(0, "消费抵账鑫豆提现申请");
        titles.append(1, "现金到账");
        titles.append(2, "今日销售");
        setFragmentList();
        initIndicatorView();
        viewPager.setCurrentItem(0);
    }

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
            mMLl_two.setVisibility(View.GONE);
        } else if (view.getId() == mTvCanTakeCash.getId()) {
            mTvMonetaryDeduction.setClickable(true);
            mTvCanTakeCash.setClickable(false);
            mTvCanTakeCash.setBackgroundResource(R.drawable.jx_10_w);
            mTvMonetaryDeduction.setBackgroundResource(R.drawable.jx_10_w_nom);
            mTvCanTakeCash.setTextColor(getResources().getColor(R.color.black));
            mTvMonetaryDeduction.setTextColor(getResources().getColor(R.color.normal_text_color));
            mLlMonetaryDeduction.setVisibility(View.GONE);
            mMLl_two.setVisibility(View.VISIBLE);
        } else if (view.getId() == back.getId()) {
            finish();
        }
    }

    private void goToHttpReqss() {
        CommonSubscriber<FBean> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                statusContent();
                FBean fBean = (FBean) o;
                mTvXdAount.setText(fBean.getXindou());
                mTvAccountBalance.setText(fBean.getMoney());
            }

            @Override
            public void onError(String e, int code) {
                statusContent();
                showToastMsg(e);
            }
        });
        BusinessUserMethods.getInstance().financeDetail(subscriber);
        rxManager.add(subscriber);
    }

    private void goToHttpReq() {
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

    private void setFragmentList() {
        List<Fragment> fragmentList = new ArrayList<>();
        for (int t = 0; t < 3; t++) {
            fragmentList.add(new JFOneFragment(t));
        }
        
        if (fragmentAdapter == null) {
            fragmentAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragmentList, titles);
        } else {
            fragmentAdapter.setFragments(getSupportFragmentManager(), fragmentList, titles);
        }
        viewPager.setAdapter(fragmentAdapter);
    }

    private void initIndicatorView() {
        CommonNavigator navigator = new CommonNavigator(context);
        navigator.setAdjustMode(true);
        navigator.setFollowTouch(true);
        navigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titles.size();
            }

            @Override
            public IPagerTitleView getTitleView(final Context context, final int index) {
                SimplePagerTitleView titleView = new ColorTransitionPagerTitleView(context);
                titleView.setText(titles.get(index));
                titleView.setNormalColor(ResourceUtils.getColor(context, R.color.normal_text_color));
                titleView.setSelectedColor(ResourceUtils.getColor(context, R.color.base_color));
                titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
                titleView.setmSelectedSize((int) SizeUtils.dp2px(context, 8));
                titleView.setmNormalSize((int) SizeUtils.dp2px(context, 8));
                titleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
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
        tabIndicator.setNavigator(navigator);
        ViewPagerHelper.bind(tabIndicator, viewPager);
    }
}
