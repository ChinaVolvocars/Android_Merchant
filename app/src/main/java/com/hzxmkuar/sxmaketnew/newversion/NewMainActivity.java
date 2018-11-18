package com.hzxmkuar.sxmaketnew.newversion;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.model.Home;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.EmptyUtils;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.activity.MyBankActivity;
import com.hzxmkuar.sxmaketnew.activity.WithdrawBillActivity;
import com.hzxmkuar.sxmaketnew.adapter.MainAdapter;
import com.hzxmkuar.sxmaketnew.base.BaseUrlActivity;
import com.hzxmkuar.sxmaketnew.home.SettingsActivity;
import com.hzxmkuar.sxmaketnew.home.ShopInfoActivity;
import com.hzxmkuar.sxmaketnew.home.ShopShowActivity;
import com.hzxmkuar.sxmaketnew.view.dialog.DialogHomeWay;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NewMainActivity extends BaseMvpActivity {
    private static final String TAG = "NewMainActivity";

    public static final String KEY_MONEY = "money";
    @BindView(R.id.recycler_view)
    RecyclerView rv;
    private MainAdapter adapter;
    private Home result;
    /**
     *  发票信息
     */
    private String invoiceUrl = "";
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
                if (0 == position) {
                    //我要展示
                    gotoActivity(ShopShowActivity.class);
                } else if (1 == position) {
                    // 商家资料
                    gotoActivity(ShopInfoActivity.class);
                }
            }

            @Override
            public void onActivityItemClick(View view, int position) {
                Log.e("活动列表的点击事件", "onActivityItemClick: " + position);
                if (null != result && null != result.getList() && !EmptyUtils.isEmpty(result.getList().get(position).getUrl())){
                    Intent urlIntent = new Intent(context, BaseUrlActivity.class);
                    urlIntent.putExtra(BaseUrlActivity.MAIN_URL, result.getList().get(position).getUrl());
                    startActivity(urlIntent);
                }
            }

            @Override
            public void onOtherItemClick(View view, String tag) {
                Log.e("其他的点击事件", "onOtherItemClick: " + tag);
                if (tag.equals(MainAdapter.Conversion)) {
                } else if (tag.equals(MainAdapter.CollectionCode)) {
                    if (result != null) {
                        String pay_img = result.getPay_img();
                        Intent intent = new Intent(context, QRCodeActivity.class);
                        intent.putExtra("qr_img", pay_img);
                        startActivity(intent);
                    }
                } else if (tag.equals(MainAdapter.ScanCheck)) {

                } else if (tag.equals(MainAdapter.Bank)) {
                    startActivity(new Intent(context, MyBankActivity.class).putExtra("name", "000"));
                } else if (tag.equals(MainAdapter.WithdrawalApplication)) {
                    Bundle bundle = new Bundle();
                    String money = result.getMoney();
                    bundle.putString(KEY_MONEY, money);
                    DialogHomeWay dialog = DialogHomeWay.newInstance(bundle);
                    dialog.show(getSupportFragmentManager(), "DialogHomeWay");
                } else if (tag.equals(MainAdapter.WithdrawalAccounts)) {
                    showToastMsg("提现账款");
                    gotoActivity(WithdrawBillActivity.class);
                } else if (tag.equals(MainAdapter.RevenueStatistics)) {
                    showToastMsg("营收统计");
                    Intent intent = new Intent(context, RevenueActivity.class);
                    startActivity(intent);
                } else if (tag.equals(MainAdapter.InvoiceInformation)) {
                    if (!EmptyUtils.isEmpty(invoiceUrl)){
                        Intent urlIntent = new Intent(context, BaseUrlActivity.class);
                        urlIntent.putExtra(BaseUrlActivity.MAIN_URL, invoiceUrl);
                        startActivity(urlIntent);
                    }

                } else if (tag.equals(MainAdapter.ConsumptionAuthority)) {
                    showToastMsg("该功能暂未开放，敬请期待！");
                } else if (tag.equals(MainAdapter.TodayRevenue)) {
                    startActivity(new Intent(context, TodayRevenueActivity.class));

                }
            }
        });

    }

    private void getHomeInfo() {
            CommonSubscriber<Home> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                Home homeEntitiy = (Home) o;
                Log.i(TAG, "onNext: " + homeEntitiy.toString());
                if (null != homeEntitiy){
                    result = homeEntitiy;
                    adapter.setData(homeEntitiy);
                    invoiceUrl = homeEntitiy.getCopy_invoice_url();
                }
            }

            @Override
            public void onError(String e, int code) {

            }
        });

        List<String> reqLis = new ArrayList<>();
        reqLis.add("time");
        reqLis.add("uid");
        BusinessUserMethods.getInstance().newIndex(subscriber, reqLis);
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
