package com.hzxmkuar.sxmaketnew.home;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.common.adapter.helper.IRecyclerViewHelper;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.model.TodayRevenue;
import com.common.retrofit.model.TodaysRevenue;
import com.common.utils.DateUtils;
import com.common.utils.EmptyUtils;
import com.common.utils.UIUtils;
import com.common.widget.recyclerview.refresh.recycleview.XRecyclerView;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.adapter.TodayRevenueAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * 废弃
 * 当日流水
 */
@Deprecated
public class TodayBillsActivity extends BaseMvpActivity {
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
    @BindView(R.id.tv_withdrawal_settlement)
    TextView tvWithdrawalSettlement;
    @BindView(R.id.tv_cash_settlement)
    TextView tvCashSettlement;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.tv_transactions)
    TextView tvTransactions;
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
        tName.setText("当日流水");
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
        tvTime.setTextColor(UIUtils.getColor(R.color.color_68cafd));
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
                tvWithdrawalSettlement.setText(getString(R.string.withdrawal_settlement, todayRevenue.getTotal_xindou()));
                tvCashSettlement.setText(getString(R.string.cash_settlement, todayRevenue.getPay_moneys()));
                //12 14 12
                //#747373 #fdc70a #747373
                SpannableStringBuilder stringTotal = new SpannableStringBuilder();
                stringTotal.append(getString(R.string.bills_total, todayRevenue.getTotal_money()));

                ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#68cafd"));
                stringTotal.setSpan(colorSpan, 2, stringTotal.length() - 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                RelativeSizeSpan sizeSpan01 = new RelativeSizeSpan(1.0f);
                RelativeSizeSpan sizeSpan02 = new RelativeSizeSpan(1.167f);
                RelativeSizeSpan sizeSpan03 = new RelativeSizeSpan(1.0f);
                stringTotal.setSpan(sizeSpan01, 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                stringTotal.setSpan(sizeSpan02, 2, stringTotal.length() - 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                stringTotal.setSpan(sizeSpan03, stringTotal.length() - 1, stringTotal.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                tvTotal.setText(stringTotal);

                tvTransactions.setText(getString(R.string.transactions, todayRevenue.getTransaction()));

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
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                /*btn_Time.setText(getTime(date));*/
//                Button btn = (Button) v;
//                btn.setText(getTime(date));
//                Log.e("TAG", "onTimeSelect: " + DateUtils.FORMAT_DETAIL.format(date));
                // TODO: 2018/11/22     选取日期
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
