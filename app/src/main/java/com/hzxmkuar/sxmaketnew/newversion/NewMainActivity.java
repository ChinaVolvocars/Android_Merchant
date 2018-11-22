package com.hzxmkuar.sxmaketnew.newversion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.common.base.Constants;
import com.common.event.SpConstants;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.AppVersionEntity;
import com.common.retrofit.entity.result.UserBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.model.Home;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.APPUtil;
import com.common.utils.ActivityStack;
import com.common.utils.ContextUtils;
import com.common.utils.EmptyUtils;
import com.common.utils.SPUtils;
import com.common.utils.UIUtils;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.activity.MyBankActivity;
import com.hzxmkuar.sxmaketnew.activity.WithdrawBillActivity;
import com.hzxmkuar.sxmaketnew.adapter.MainAdapter;
import com.hzxmkuar.sxmaketnew.base.BaseUrlActivity;
import com.hzxmkuar.sxmaketnew.home.SettingsActivity;
import com.hzxmkuar.sxmaketnew.home.ShopInfoActivity;
import com.hzxmkuar.sxmaketnew.home.ShopShowActivity;
import com.hzxmkuar.sxmaketnew.view.dialog.DialogHomeWay;
import com.hzxmkuar.sxmaketnew.view.dialog.VersionCompareDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.common.event.SpConstants.CLOSED_PAY_URL;
import static com.common.event.SpConstants.INVOICE_URL;
import static com.hzxmkuar.sxmaketnew.view.dialog.WithdrawDialogFragment.REFRESH;

public class NewMainActivity extends BaseMvpActivity {
    private static final String TAG = "NewMainActivity";
    public static final String KEY_MONEY = "money";
    public static final String KEY_WEEK = "week";

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.recycler_view)
    RecyclerView rv;
    private MainAdapter adapter;
    private Home result;
    /**
     * 发票信息
     */
    private String invoiceUrl = "";
    private TextView tvUserName;

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
        tvUserName = (TextView) findViewById(R.id.tv_is_test);
        UserBean bean = DataCenter.getInstance().getUserBean();
        if (bean != null) {
            DataCenter.UserId = bean.getUid();
            DataCenter.HashId = bean.getHashid();
        }

        if (Constants.BaseUrl.equals("http://test.zhongxinyingjia.com/Api/")) {
            TextView viewById = (TextView) findViewById(R.id.tv_test);
            viewById.setVisibility(View.VISIBLE);
            viewById.setText("测试环境");
        }

        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setHasFixedSize(true);

        adapter = new MainAdapter(this);
        rv.setAdapter(adapter);


        swipeRefresh.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light));


        getHomeInfo();


        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getHomeInfo();
            }
        });

        adapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void onViewPagerItemClick(View view, int position) {
                Log.e("广告的点击事件", "onViewPagerItemClick: " + position);

                if (null != result && null != result.getShop_banner()
                        && !EmptyUtils.isEmpty(result.getShop_banner().get(position).getLink())) {
                    Intent urlIntent = new Intent(context, BaseUrlActivity.class);
                    urlIntent.putExtra(BaseUrlActivity.MAIN_URL, result.getShop_banner().get(position).getLink());
                    startActivity(urlIntent);
                }
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
                if (null != result && null != result.getList() && !EmptyUtils.isEmpty(result.getList().get(position).getUrl())) {
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
                    showToastMsg("该功能暂未开放，敬请期待！");
                } else if (tag.equals(MainAdapter.Bank)) {
                    startActivity(new Intent(context, MyBankActivity.class).putExtra("name", "000"));
                } else if (tag.equals(MainAdapter.WithdrawalApplication)) {
                    Bundle bundle = new Bundle();
                    String money = result.getMoney();
                    bundle.putString(KEY_MONEY, money);
                    bundle.putInt(KEY_WEEK, result.getWeek());
                    Log.e("", "主界面的week: " + result.getWeek());
                    DialogHomeWay dialog = DialogHomeWay.newInstance(bundle);
                    dialog.show(getSupportFragmentManager(), "DialogHomeWay");
                } else if (tag.equals(MainAdapter.WithdrawalAccounts)) {
                    gotoActivity(WithdrawBillActivity.class);
                } else if (tag.equals(MainAdapter.RevenueStatistics)) {
                    Intent intent = new Intent(context, RevenueActivity.class);
                    startActivity(intent);
                } else if (tag.equals(MainAdapter.InvoiceInformation)) {
                    if (!EmptyUtils.isEmpty(invoiceUrl)) {
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

    private void cancelRefreshing() {
        if (swipeRefresh != null) swipeRefresh.setRefreshing(false);
    }

    private void getHomeInfo() {
        CommonSubscriber<Home> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                cancelRefreshing();
                Home homeEntitiy = (Home) o;
                Log.i(TAG, "onNext: " + homeEntitiy.toString());
                if (null != homeEntitiy) {
                    result = homeEntitiy;
                    tvUserName.setText(result.getUsername());

                    String closed_pay_url = result.getClosed_pay_url();
                    String invoice_url = result.getInvoice_url();
                    SPUtils.setShareString(CLOSED_PAY_URL, closed_pay_url);
                    SPUtils.setShareString(INVOICE_URL, invoice_url);
                    adapter.setData(homeEntitiy);
                    invoiceUrl = homeEntitiy.getCopy_invoice_url();
                }
            }

            @Override
            public void onError(String e, int code) {
                cancelRefreshing();

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
        checkAppVerion();
    }

    private void checkAppVerion() {
        List<String> paramaList = new ArrayList<>();
        CommonSubscriber<AppVersionEntity> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                statusContent();
                AppVersionEntity appVersionEntity = (AppVersionEntity) o;
                Log.i(TAG, "onNext: " + appVersionEntity.toString());
                Log.i(TAG, "onNext:  下载链接：      " + appVersionEntity.getDownloadUrl());
                // 需要更新
                if (!("0".equals(appVersionEntity.getForce())) && !(EmptyUtils.isEmpty(appVersionEntity.getDownloadUrl()))) {
                    VersionCompareDialog versionCompareDialog = new VersionCompareDialog(context, getActivity(), appVersionEntity.getDescription(),
                            appVersionEntity.getForce(), appVersionEntity.getDownloadUrl().trim(), appVersionEntity.getVersioncode());
                    versionCompareDialog.show();
                }
            }

            @Override
            public void onError(String e, int code) {
                statusContent();
                showToastMsg(e);
            }
        });
        paramaList.add("time");
        paramaList.add("types");
        paramaList.add("is_shop");
        paramaList.add("version_code");
        BusinessUserMethods.getInstance().checkAppVersion(subscriber, paramaList, "2", "1", String.valueOf(APPUtil.getVersionName(ContextUtils.getAppContext())));
        rxManager.add(subscriber);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
        if (SPUtils.getShareBoolean(REFRESH)) {
            getHomeInfo();
            SPUtils.setShareBoolean(REFRESH, false);
        }


    }

    private long exitTime = 0;

    /**
     * 按两次返回键退出
     */
    @Override
    public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (keyCode == android.view.KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
               /* showToastMsg("再按一次退出" + getString(R.string.app_name));*/
                Toast.makeText(NewMainActivity.this, "再按一次退出" + getString(R.string.app_name), Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
                return true;
            } else {
                ActivityStack.getInstance().appExit();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
