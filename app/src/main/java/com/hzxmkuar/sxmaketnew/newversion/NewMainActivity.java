package com.hzxmkuar.sxmaketnew.newversion;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.IndexBean;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.model.Home;
import com.common.retrofit.model.TodayRevenue;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.EmptyUtils;
import com.common.utils.ListUtils;
import com.common.utils.SPUtils;
import com.common.widget.imageview.image.ImageLoaderUtils;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.activity.MyBankActivity;
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

    public static final String KEY_MONEY = "money";
    @BindView(R.id.recycler_view)
    RecyclerView rv;
    private MainAdapter adapter;
    private HttpRespBean<Home> result;

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
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setHasFixedSize(true);

        adapter = new MainAdapter(this);
        rv.setAdapter(adapter);
        getHomeInfo();

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
                    if (result != null) {
                        String pay_img = result.getData().getPay_img();
                        Intent intent = new Intent(context, QRCodeActivity.class);
                        intent.putExtra("qr_img", pay_img);
                        startActivity(intent);
                    }
                } else if (tag.equals(MainAdapter.ScanCheck)) {

                } else if (tag.equals(MainAdapter.Bank)) {
                    startActivity(new Intent(context, MyBankActivity.class).putExtra("name", "000"));
                } else if (tag.equals(MainAdapter.WithdrawalApplication)) {
                    Bundle bundle = new Bundle();
                    String money = result.getData().getMoney();
                    bundle.putString(KEY_MONEY, money);
                    DialogHomeWay dialog = DialogHomeWay.newInstance(bundle);
                    dialog.show(getSupportFragmentManager(), "DialogHomeWay");
                } else if (tag.equals(MainAdapter.WithdrawalAccounts)) {

                } else if (tag.equals(MainAdapter.RevenueStatistics)) {

                } else if (tag.equals(MainAdapter.InvoiceInformation)) {

                } else if (tag.equals(MainAdapter.ConsumptionAuthority)) {

                } else if (tag.equals(MainAdapter.TodayRevenue)) {
                    startActivity(new Intent(context, TodayRevenueActivity.class));

                }
            }
        });

    }

    private void getHomeInfo() {
//        CommonSubscriber<HttpRespBean<Home>> subscriber = new CommonSubscriber<>(new SubscriberListener<HttpRespBean<Home>>() {
//            @Override
//            public void onNext(HttpRespBean<Home> o) {
//                if (o.getCode() == 0) {
//                    if (null != o.getData()) {
//                        adapter.setData(o.getData());
//                    }
//                }
//            }
//
//            @Override
//            public void onError(String e, int code) {
//
//            }
//        });


        List<String> reqLis = new ArrayList<>();
        reqLis.add("time");
        reqLis.add("uid");

        BusinessUserMethods.getInstance().newIndex(new Subscriber<HttpRespBean<Home>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(HttpRespBean<Home> o) {
                result = o;
                if (o.getCode() == 0) {
                    if (null != o.getData()) {
                        adapter.setData(o.getData());
                    }
                }
            }
        }, reqLis);
//        rxManager.add(subscriber);

    }


    @OnClick(R.id.iv_setting)
    public void onSettingClicked() {
        gotoActivity(SettingsActivity.class);
    }


    @Override
    protected void doLogicFunc() {

    }
}
