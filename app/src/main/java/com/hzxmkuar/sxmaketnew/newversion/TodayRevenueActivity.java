package com.hzxmkuar.sxmaketnew.newversion;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.common.adapter.helper.IRecyclerViewHelper;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.ApplyRecodEntity;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.model.DayFlowListDto;
import com.common.retrofit.model.TodayRevenue;
import com.common.retrofit.model.TodaysRevenue;
import com.common.utils.DateUtils;
import com.common.utils.EmptyUtils;
import com.common.widget.recyclerview.refresh.recycleview.XRecyclerView;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.adapter.ApplyRecordAdapter;
import com.hzxmkuar.sxmaketnew.adapter.TodayRevenueAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

//今日营收
public class TodayRevenueActivity extends BaseMvpActivity {
    private static final String TAG = "TodayRevenueActivity";
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.t_name)
    TextView tName;
    //    @BindView(R.id.tv_right)
//    TextView tvRight;
    @BindView(R.id.iv_adv)
    ImageView ivAdv;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.iv_calendar)
    ImageView ivCalendar;
    @BindView(R.id.tv_total_withdraw)
    TextView tvTotalWithdraw;
    @BindView(R.id.tv_total_cash)
    TextView tvTotalCash;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    private TodayRevenueAdapter adapter;
    private TimePickerView pvTime;
    private Date currentDate;

    @OnClick(R.id.back)
    public void onFinishClicked() {
        finish();
    }

    @OnClick(R.id.iv_calendar)
    public void onCalendarClicked() {
        pvTime.show();
    }

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_today_revenue_collapsing;
    }

    @Override
    protected void onViewCreated() {
        ButterKnife.bind(this);

        tName.setText("当日营收");
        adapter = new TodayRevenueAdapter(this);
        IRecyclerViewHelper.init().setRecycleGridLayout(context, recyclerView, 1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        /**
         *  默认是当天的日期
         */
        currentDate = new Date();
        initTimePicker();
        requestTodayRevenue(currentDate);

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPageIndex = 1;
                mIsRefreshOrLoadMore = 0;
                requestTodayRevenue(currentDate);
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
                requestTodayRevenue(currentDate);
            }
        });
    }

    private void requestTodayRevenue(final Date date) {
        SimpleDateFormat simpleDateFormat = DateUtils.FORMAT_DATE;
        String formatDate = simpleDateFormat.format(date);
        tvTime.setText(formatDate);
        BusinessUserMethods.getInstance().todayRevenue(new Subscriber<HttpRespBean<TodayRevenue>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(HttpRespBean<TodayRevenue> result) {
                TodayRevenue todayRevenue = result.getData();
                tvTotal.setText(getSpannableStringBuilder(todayRevenue));
                tvTotalWithdraw.setText(getString(R.string.format_point_two, todayRevenue.getTotal_xindou()));
                tvTotalCash.setText(getString(R.string.format_point_two, todayRevenue.getPay_moneys()));
                tvSum.setText(getString(R.string.format_rmb, todayRevenue.getTotal_money()));
                List<TodaysRevenue> dataListFromNet = result.getData().getToday_list();
                List<TodaysRevenue> cacheDataList = new ArrayList<>();
                if (null != dataListFromNet) {
                    cacheDataList.addAll(dataListFromNet);
                }
                // 下拉刷新
                if (mIsRefreshOrLoadMore == 0) {
                    recyclerView.refreshComplete();
                    adapter.clearData();
                }
                if (EmptyUtils.isNotEmpty(cacheDataList)) {
                    adapter.addAllData(cacheDataList);
                    statusContent();
                }

                if (EmptyUtils.isEmpty(cacheDataList)) {
                    recyclerView.setNoMore(true);
                } else {
                    mIsHasNoData = cacheDataList.size() < mPageSize;
                    recyclerView.setNoMore(mIsHasNoData);
                }

            }
        }, formatDate, mPageIndex);
    }

    @Override
    protected void doLogicFunc() {

    }

    @NonNull
    private SpannableStringBuilder getSpannableStringBuilder(TodayRevenue dayFlowListDto) {
        SpannableStringBuilder stringTotal = new SpannableStringBuilder();
        stringTotal.append(getString(R.string.total_day_revenue, dayFlowListDto.getTransaction(), dayFlowListDto.getTotal_money()));
        //共14笔      合计￥228.00 4+6空格
        int transaction = dayFlowListDto.getTransaction();
        ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(Color.parseColor("#747373"));
        ForegroundColorSpan colorSpan2 = new ForegroundColorSpan(Color.parseColor("#fdc70a"));
        stringTotal.setSpan(colorSpan1, 0, 10 + String.valueOf(transaction).length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        stringTotal.setSpan(colorSpan2, 10 + String.valueOf(transaction).length(), stringTotal.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return stringTotal;
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
        pvTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                currentDate = date;
                if (null != adapter) {
                    adapter.clearData();
                }
                requestTodayRevenue(currentDate);
            }
        }).setDate(selectedDate).setRangDate(startDate, selectedDate).setLayoutRes(R.layout.today_revenue_pickerview_custom_time, new CustomListener() {
            @Override
            public void customLayout(View v) {
                final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                TextView ivCancel = (TextView) v.findViewById(R.id.tv_cancel);
                tvSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pvTime.returnData();
                        pvTime.dismiss();
                    }
                });
                ivCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pvTime.dismiss();
                    }
                });
            }
        }).setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setDividerColor(Color.DKGRAY)
                .setLineSpacingMultiplier(1.2f)
                .build();

//        pvTime.setKeyBackCancelable(false);//系统返回键监听屏蔽掉
    }

}
