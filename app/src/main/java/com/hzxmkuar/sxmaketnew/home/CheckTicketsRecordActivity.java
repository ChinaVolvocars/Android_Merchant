package com.hzxmkuar.sxmaketnew.home;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.CheckRecordEntity;
import com.common.retrofit.methods.CouponMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.DateUtils;
import com.common.utils.EmptyUtils;
import com.common.widget.recyclerview.refresh.recycleview.XRecyclerView;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.adapter.CheckRecordsAdapter;
import com.hzxmkuar.sxmaketnew.adapter.TodayRevenueAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 验券记录
 * Created by jc on 2019/1/2.
 */
public class CheckTicketsRecordActivity extends BaseMvpActivity {
    //    private static final String TAG = "CheckTicketsRecordActiv";
    private ImageView mIvBack;
    private TextView mTvTitle;

    private ImageView mIvChoseDate;
    private TimePickerView mTimePicker;
    private Date currentDate;
    private TodayRevenueAdapter dateChoseAdapter;
    private XRecyclerView recyclerViewRecord;
    private CheckRecordsAdapter checkRecordsAdapter;
    private TextView tv_current_coupon_num;
    private TextView tv_chose_date;
    private TextView tv_date_count;
    private List<CheckRecordEntity.RecordItemEntity> recordItemEntities = new ArrayList<>();
    private MyLoadingListener loadingListener;
    private TextView tv_nodata_tips;
    private LinearLayout ll_emp;
    /**
     * 悬浮的容器
     */
    private LinearLayout ll_float_content;
    private TextView tv_chose_date_float;
    private TextView tv_date_count_float;
    private View headerView;
    private int mHeaderViewHeight = 0;
    //    private OffsetLinearLayoutManager layoutManager;
    private LinearLayoutManager layoutManager;
    private int itemHeight = 0;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_check_tickets_record;
    }


    @Override
    protected void onViewCreated() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText(R.string.check_tickets);
        initHeadView();
        ll_float_content = (LinearLayout) findViewById(R.id.ll_float_content);
        tv_chose_date_float = (TextView) findViewById(R.id.tv_chose_date_float);
        tv_date_count_float = (TextView) findViewById(R.id.tv_date_count_float);
        recyclerViewRecord = (XRecyclerView) findViewById(R.id.recyclerView_record);
        layoutManager = new LinearLayoutManager(context);
        tv_nodata_tips = (TextView) findViewById(R.id.tv_nodata_tips);
        ll_emp = (LinearLayout) findViewById(R.id.ll_emp);
        tv_nodata_tips.setText("暂时没有验券记录");
        /**
         *  默认是当天的日期
         */
        currentDate = new Date();
        loadingListener = new MyLoadingListener();
        initTimePicker();
        initAdapter();
        getCheckRecord();
    }

    /**
     * 初始化头布局
     */
    private void initHeadView() {
        headerView = LayoutInflater.from(context).inflate(R.layout.layout_check_tickets_record_header, null, false);
        headerView.setFocusableInTouchMode(true);
        mIvChoseDate = (ImageView) headerView.findViewById(R.id.iv_chose_date);
        tv_current_coupon_num = (TextView) headerView.findViewById(R.id.tv_current_coupon_num);
        tv_chose_date = (TextView) headerView.findViewById(R.id.tv_chose_date);
        tv_date_count = (TextView) headerView.findViewById(R.id.tv_date_count);

    }


    @Override
    protected void doLogicFunc() {
        attachClickListener(mIvBack);
        attachClickListener(mIvChoseDate);
        attachClickListener(tv_nodata_tips);

        headerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHeaderViewHeight = headerView.getHeight();
//                Log.i(TAG, "run:  头布局的高度：       " + mHeaderViewHeight);
            }
        }, 300);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            recyclerViewRecord.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    //获取滑动距离，，通过布局管理器
                    //1.获得视图的第一条木的下标
                    //2.根据下标获得view条目,,,在获得条目的高度
                    //3.下标*条目高度-可见视图距离顶部的高度
                    int position = layoutManager.findFirstVisibleItemPosition();
                    View firstVisiableChildView = layoutManager.findViewByPosition(position);

                    itemHeight = firstVisiableChildView.getHeight();
//                    int i4 = (position) * itemHeight - firstVisiableChildView.getTop();
                    Log.i("jibbb", "===" + itemHeight);
                    if (itemHeight < mHeaderViewHeight) {
                        ll_float_content.setVisibility(View.VISIBLE);
                    } else {
                        ll_float_content.setVisibility(View.GONE);
                    }
                }
            });
        }
    }


    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == mIvBack.getId()) {
            finish();
        } else if (view.getId() == mIvChoseDate.getId()) {
            mTimePicker.show();
        } else if (view.getId() == tv_nodata_tips.getId()) {
            if (null != loadingListener) {
                loadingListener.onRefresh();
            }
        }
    }

    private void initTimePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        final Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(2001, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(new Date());
        //时间选择器
        mTimePicker = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                /*btn_Time.setText(getTime(date));*/
                currentDate = date;
                if (null != dateChoseAdapter) {
                    dateChoseAdapter.clearData();
                }
                if (null != checkRecordsAdapter) {
                    checkRecordsAdapter.clear();
                }
                tv_chose_date.setText(DateUtils.format(currentDate, "yyyy-MM-dd"));
                mPageIndex = 1;
                recordItemEntities.clear();
                if (null != loadingListener) {
                    loadingListener.onRefresh();
                }

            }
        }).setDate(selectedDate).setRangDate(startDate, selectedDate).setLayoutRes(R.layout.today_revenue_pickerview_custom_time, new CustomListener() {
            @Override
            public void customLayout(View v) {
                final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                TextView ivCancel = (TextView) v.findViewById(R.id.tv_cancel);
                tvSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTimePicker.returnData();
                        mTimePicker.dismiss();
                    }
                });
                ivCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTimePicker.dismiss();
                    }
                });
            }
        }).setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setDividerColor(Color.DKGRAY)
                .setLineSpacingMultiplier(1.2f)
                .build();
//        pvTime.setKeyBackCancelable(false);//系统返回键监听屏蔽掉
    }


    private void initAdapter() {
        checkRecordsAdapter = new CheckRecordsAdapter(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewRecord.setLayoutManager(layoutManager);
        checkRecordsAdapter.addHeaderView(headerView);
        recyclerViewRecord.setAdapter(checkRecordsAdapter);
        recyclerViewRecord.setLoadingListener(loadingListener);
    }

    /**
     * 获取验券记录
     */

    private void getCheckRecord() {
        showProgressingDialog();
        CommonSubscriber<CheckRecordEntity> subscriber = new CommonSubscriber<>(new SubscriberListener<CheckRecordEntity>() {
            @Override
            public void onNext(CheckRecordEntity result) {
                recyclerViewRecord.loadMoreComplete();
                dismissProgressDialog();
                tv_current_coupon_num.setText("￥" + result.getCurrent_coupon_num());
                tv_chose_date.setText(DateUtils.format(currentDate, "yyyy-MM-dd"));
                tv_date_count.setText("共" + result.getNum() + "笔 合计 ￥" + result.getCount_price() + "元");
                tv_chose_date_float.setText(DateUtils.format(currentDate, "yyyy-MM-dd"));
                tv_date_count_float.setText("共" + result.getNum() + "笔 合计 ￥" + result.getCount_price() + "元");

                List<CheckRecordEntity.RecordItemEntity> beanList = result.getList();

                // 下拉刷新
                if (mIsRefreshOrLoadMore == 0) {
                    recyclerViewRecord.refreshComplete();
                    checkRecordsAdapter.clear();
                }

                if (EmptyUtils.isNotEmpty(beanList)) {
                    recordItemEntities = beanList;
                    checkRecordsAdapter.addAll(recordItemEntities);
                }

                if (recordItemEntities.size() <= 0) {
                    ll_emp.setVisibility(View.VISIBLE);
                } else {
                    ll_emp.setVisibility(View.GONE);
                }

                if (EmptyUtils.isEmpty(beanList)) {
                    recyclerViewRecord.setNoMore(true);
                } else {
                    mIsHasNoData = beanList.size() < mPageSize;
                    recyclerViewRecord.setNoMore(mIsHasNoData);
                }
            }


            @Override
            public void onError(String e, int code) {
                showToastMsg(e);
                dismissProgressDialog();
                recyclerViewRecord.refreshComplete();
            }
        });
        CouponMethods.getInstance().couponVerifyRecord(subscriber, DateUtils.format(currentDate, "yyyy-MM-dd"), mPageIndex);
        rxManager.add(subscriber);
    }

    /**
     * 加载监听
     */
    private class MyLoadingListener implements XRecyclerView.LoadingListener {
        @Override
        public void onRefresh() {
            mPageIndex = 1;
            mIsRefreshOrLoadMore = 0;
            getCheckRecord();
            recordItemEntities.clear();
        }

        @Override
        public void onLoadMore() {
            if (mIsHasNoData) {
                recyclerViewRecord.loadMoreComplete();
                recyclerViewRecord.setNoMore(true);
                return;
            }
            mPageIndex++;
            mIsRefreshOrLoadMore = 1;
            getCheckRecord();
        }
    }
}
