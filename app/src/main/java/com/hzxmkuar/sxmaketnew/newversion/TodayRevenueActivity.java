package com.hzxmkuar.sxmaketnew.newversion;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.model.TodayRevenue;
import com.common.utils.DateUtils;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.adapter.MainAdapter;
import com.hzxmkuar.sxmaketnew.adapter.TodayRevenueAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;

//今日营收
public class TodayRevenueActivity extends BaseMvpActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.t_name)
    TextView tName;
    @BindView(R.id.tv_right)
    TextView tvRight;
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
    RecyclerView recyclerView;
    @BindView(R.id.tv_transactions)
    TextView tvTransactions;
    private TodayRevenueAdapter adapter;

    @OnClick(R.id.back)
    public void onFinishClicked() {
        finish();
    }


    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_today_revenue;
    }

    @Override
    protected void onViewCreated() {
        tName.setText("当日营收");

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        adapter = new TodayRevenueAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView. setNestedScrollingEnabled(false);
        List<String> reqLis = new ArrayList<>();
        reqLis.add("time");
        reqLis.add("uid");
        reqLis.add("page");
        reqLis.add("dates");
        SimpleDateFormat formatDate = DateUtils.FORMAT_DATE;
        String date = formatDate.format(new Date());
        tvTime.setText(date);
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
                tvTotal.setText(getString(R.string.total, todayRevenue.getTotal_money()));
                tvTransactions.setText(getString(R.string.transactions, todayRevenue.getTransaction()));
                adapter.setData(result.getData().getToday_list());
            }
        }, reqLis, date);
    }

    @Override
    protected void doLogicFunc() {

    }
}
