package com.hzxmkuar.sxmaketnew.newversion;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragAndDropPermissions;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.model.Pie;
import com.common.retrofit.model.Revenue;
import com.common.retrofit.model.RevenueStatistics;
import com.common.utils.DateUtils;
import com.common.utils.UIUtils;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.adapter.RevenueAdapter;
import com.view.pie.AnimatedPieView;
import com.view.pie.AnimatedPieViewConfig;
import com.view.pie.data.SimplePieInfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * 营收统计
 */
public class RevenueActivity extends BaseMvpActivity {

    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.iv_calendar)
    ImageView ivCalendar;
    @BindView(R.id.tv_total_revenue)
    TextView tvTotalRevenue;
    @BindView(R.id.tv_withdrawal_settlement)
    TextView tvWithdrawalSettlement;
    @BindView(R.id.tv_cash_settlement)
    TextView tvCashSettlement;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tab_layout)
    SegmentTabLayout tabLayout;
    @BindView(R.id.pie_view)
    AnimatedPieView pieView;
    @BindView(R.id.t_name)
    TextView tName;

    private String[] mTitles = {"最近7天 ", "最近半年"};
    private TimePickerView pvTime;
    private View viewDay;
    private RevenueAdapter adapter;

    @OnClick(R.id.back)
    public void onBackClicked() {
        onBackPressed();
    }

    @OnClick(R.id.iv_calendar)
    public void onCalendarClicked() {
        pvTime.show();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_revenue;
    }

    @Override
    protected void onViewCreated() {
        tName.setText("营收统计");
        tabLayout.setTabData(mTitles);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        adapter = new RevenueAdapter(this);
        recyclerView.setAdapter(adapter);
        SimpleDateFormat simpleDateFormat = DateUtils.FORMAT_DATE;
        final String formatDate = simpleDateFormat.format(new Date());

        SimpleDateFormat simpleDateFormat7 = DateUtils.FORMAT_DATE7;
        final String formatDate7 = simpleDateFormat7.format(new Date());

        tvTime.setText(formatDate7);

        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
//                viewDay.setVisibility(position == 0 ? View.VISIBLE : View.GONE);
                System.out.println("========位置：" + position);
                if (position == 0) {
                    shopDayRevenue(formatDate);
                } else {
                    SimpleDateFormat simpleDateFormat = DateUtils.FORMAT_DATE4;
                    String formatDate = simpleDateFormat.format(new Date());
                    System.out.println("时间：" + formatDate);
                    shopMonthlyRevenue(formatDate);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }

        });

        initPieView();
        initTimePicker();

        shopDayRevenue(formatDate);

        SimpleDateFormat formatDate4 = DateUtils.FORMAT_DATE4;
        String format = formatDate4.format(new Date());
        shopColumnGraph(format);
    }

    private void shopMonthlyRevenue(String formatDate) {
        BusinessUserMethods.getInstance().shopMonthlyRevenue(new Subscriber<HttpRespBean<RevenueStatistics>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(HttpRespBean<RevenueStatistics> result) {

                RevenueStatistics data = result.getData();
                List<Revenue> total_moneys = data.getTotal_moneys();
                Revenue revenue = new Revenue();
                String this_month = data.getThis_month();//当月的金额
                String timeStr = result.getTime();

                SimpleDateFormat formatDetail = DateUtils.FORMAT_DETAIL;
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

                Date d = null;
                try {
                    d = formatDetail.parse(timeStr);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                "2018年11月22日 19时41分25秒
                String format = format1.format(d);
                System.out.println("\"---------时间444444444444：\"" + format);    // 将日期变为新的格式

                revenue.setTotal_money(this_month);
                revenue.setMonth(format.substring(2, 8));

                total_moneys.add(0, revenue);

                adapter.setData(total_moneys, 1);
            }
        }, formatDate);
    }

    private void shopDayRevenue(String formatDate) {
        BusinessUserMethods.getInstance().shopDayRevenue(new Subscriber<HttpRespBean<RevenueStatistics>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(HttpRespBean<RevenueStatistics> result) {
                RevenueStatistics data = result.getData();
                List<Revenue> total_moneys = data.getTotal_moneys();
                Revenue revenue = new Revenue();
                String this_day = data.getThis_day();//当天的金额
                String time = result.getTime();

                SimpleDateFormat formatDetail = DateUtils.FORMAT_DETAIL;
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

                Date d = null;
                try {
                    d = formatDetail.parse(time);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                "2018年11月22日 19时41分25秒
                String format = format1.format(d);
                System.out.println("\"---------时间444444444444：\"" + format);    // 将日期变为新的格式
                revenue.setTotal_money(this_day);
                revenue.setDay(format.substring(2, 11));

                total_moneys.add(0, revenue);
                adapter.setData(total_moneys, 0);
            }
        }, formatDate);
    }


    private void shopColumnGraph(String formatDate) {
        BusinessUserMethods.getInstance().shopColumnGraph(new Subscriber<HttpRespBean<Pie>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("环形图", "环形图 " + e.getMessage());

            }

            @Override
            public void onNext(HttpRespBean<Pie> result) {

                Pie pie = result.getData();
                String accumulative = pie.getAccumulative();
                String pay_money = pie.getPay_money();
                String pay_xindou = pie.getPay_xindou();
                tvWithdrawalSettlement.setText(pay_xindou);
                tvCashSettlement.setText(pay_money);
                tvTotalRevenue.setText(accumulative);

                AnimatedPieViewConfig config = new AnimatedPieViewConfig();
                config.startAngle(-90)          // 起始角度偏移
                        .pieRadius(UIUtils.dip2Px(65))         // 甜甜圈半径
                        .canTouch(false)
                        .strokeWidth(UIUtils.dip2Px(20))
//                        .pieRadiusRatio(0.8f)   // 甜甜圈半径占比
                        .addData(new SimplePieInfo(Double.valueOf(pay_xindou), Color.parseColor("#FFFEDD48"), "提现结算"))//数据（实现IPieInfo接口的bean）
                        .addData(new SimplePieInfo(Double.valueOf(pay_money), Color.parseColor("#FFFFBA00"), "现金结算"))
                        .duration(2000);        // 持续时间

                // 以下两句可以直接用 mAnimatedPieView.start(config); 解决，功能一致
                pieView.applyConfig(config);
                pieView.start();

                Log.e("环形图", "环形图 ");

            }
        }, formatDate);
    }


    private void initPieView() {

        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        config.startAngle(-90)          // 起始角度偏移
                .pieRadius(UIUtils.dip2Px(65))         // 甜甜圈半径
                .canTouch(false)
                .strokeWidth(UIUtils.dip2Px(20))
//                .pieRadiusRatio(0.8f)   // 甜甜圈半径占比
//                .addData(new SimplePieInfo(30, Color.parseColor("#FFFEDD48"), "提现结算"))//数据（实现IPieInfo接口的bean）
//                .addData(new SimplePieInfo(18.0f, Color.parseColor("#FFFFBA00"), "现金结算"))
                .duration(2000);        // 持续时间

        // 以下两句可以直接用 mAnimatedPieView.start(config); 解决，功能一致
        pieView.applyConfig(config);
        pieView.start();
    }

    private void initTimePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(2001, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(new Date());
        //时间选择器
        pvTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                Log.e("TAG", "onTimeSelect: " + DateUtils.FORMAT_DETAIL.format(date));
                int currentTab = tabLayout.getCurrentTab();
                System.out.println("tabLayout的id是：" + currentTab);
               /* if (currentTab == 0) {
                    SimpleDateFormat simpleDateFormat = DateUtils.FORMAT_DATE;
                    String formatDate = simpleDateFormat.format(date);
                    shopDayRevenue(formatDate);
                } else {
                    SimpleDateFormat simpleDateFormat = DateUtils.FORMAT_DATE4;
                    String formatDate = simpleDateFormat.format(date);
                    shopMonthlyRevenue(formatDate);
                }*/

                SimpleDateFormat simpleDateFormat7 = DateUtils.FORMAT_DATE7;
                final String formatDate7 = simpleDateFormat7.format(date);

                tvTime.setText(formatDate7);

                SimpleDateFormat simpleDateFormat = DateUtils.FORMAT_DATE4;
                String formatDate = simpleDateFormat.format(date);

                shopColumnGraph(formatDate);
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
        viewDay = pvTime.findViewById(R.id.day);
    }


    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected void doLogicFunc() {

    }
}
