package com.hzxmkuar.sxmaketnew.newversion;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.IndexBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.EmptyUtils;
import com.common.utils.ListUtils;
import com.common.utils.SPUtils;
import com.common.widget.imageview.image.ImageLoaderUtils;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.adapter.MainAdapter;
import com.hzxmkuar.sxmaketnew.event.AccountConstants;
import com.hzxmkuar.sxmaketnew.home.SettingsActivity;
import com.hzxmkuar.sxmaketnew.view.dialog.DialogHomeWay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewMainActivity extends BaseMvpActivity {

    @BindView(R.id.recycler_view)
    RecyclerView rv;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_new;
    }


    @Override
    protected void onViewCreated() {


        getHomeInfo();

        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setHasFixedSize(true);

        MainAdapter adapter = new MainAdapter(this);
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void onViewPagerItemClick(View view, int position) {
                Log.e("广告的点击事件", "onViewPagerItemClick: " + position);
            }

            @Override
            public void onServiceItemClick(View view, int position) {
                Log.e("服务的点击事件", "onServiceItemClick: " + position);
            }

            @Override
            public void onActivityItemClick(View view, int position) {
                Log.e("活动的点击事件", "onActivityItemClick: " + position);
            }

            @Override
            public void onOtherItemClick(View view, String tag) {
                Log.e("其他的点击事件", "onOtherItemClick: " + tag);
                if (tag.equals(MainAdapter.Conversion)) {
                } else if (tag.equals(MainAdapter.CollectionCode)) {
                } else if (tag.equals(MainAdapter.ScanCheck)) {
                } else if (tag.equals(MainAdapter.Bank)) {
                } else if (tag.equals(MainAdapter.WithdrawalApplication)) {
                    DialogHomeWay dialog = DialogHomeWay.newInstance();
                    dialog.show(getSupportFragmentManager(), "DialogHomeWay");
                } else if (tag.equals(MainAdapter.WithdrawalAccounts)) {
                } else if (tag.equals(MainAdapter.RevenueStatistics)) {
                } else if (tag.equals(MainAdapter.InvoiceInformation)) {
                } else if (tag.equals(MainAdapter.ConsumptionAuthority)) {
                } else if (tag.equals(MainAdapter.TodayRevenue)) {
                }
            }
        });

    }

    private void getHomeInfo() {


        CommonSubscriber<Object> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                statusContent();
                Log.e("onNext", "onNext: "+o);

            }

            @Override
            public void onError(String e, int code) {
                statusContent();
                showToastMsg(e);
            }
        });
        List<String> reqLis = new ArrayList<>();
        reqLis.add("time");
        reqLis.add("uid");

        BusinessUserMethods.getInstance().newIndex(subscriber,reqLis);
        rxManager.add(subscriber);

    }


    @OnClick(R.id.iv_setting)
    public void onSettingClicked() {
        gotoActivity(SettingsActivity.class);
    }


    @Override
    protected void doLogicFunc() {

    }
}
