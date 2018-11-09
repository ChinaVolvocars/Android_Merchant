package com.hzxmkuar.sxmaketnew.home;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.common.base.Constants;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.AppVersionEntity;
import com.common.retrofit.entity.result.IndexBean;
import com.common.retrofit.entity.result.UserBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.APPUtil;
import com.common.utils.ActivityStack;
import com.common.utils.ContextUtils;
import com.common.utils.EmptyUtils;
import com.common.utils.SPUtils;
import com.common.widget.imageview.image.ImageLoaderUtils;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.base.BaseUrlActivity;
import com.hzxmkuar.sxmaketnew.event.AccountConstants;
import com.hzxmkuar.sxmaketnew.view.dialog.VersionCompareDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页  添加描述   test
 */
public class MainActivity extends BaseMvpActivity {
    private static final String TAG = "MainActivity";
    private ImageView mIvSetting;
    /**
     * 二维码
     */
    private ImageView mIvQRCode;
    /**
     * 资料编辑
     */
    private LinearLayout mLlEditeInfo;
    /**
     * 活动列表
     */
    private LinearLayout mLlActiveList;
    /**
     * 查看管理会员账号
     */
    private LinearLayout mLlManagerAccount;
    /**
     * 财务管理
     */
    private LinearLayout mLlManagerFinance;
    /**
     * 我要展示
     */
    private LinearLayout mLlWantShow;
    /**
     *
     */
    private ImageView mIvHomeBg;
    /**
     * 商家收款码id
     */
    private TextView mTvGatheringCode;
    /**
     * 商家活动图片
     */
    private String shopActiveImg = "";
    private String shopActivityUrl = "";
    private TextView tvIsTest;
    private VersionCompareDialog versionCompareDialog;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void onViewCreated() {
        UserBean bean = DataCenter.getInstance().getUserBean();
        if (bean != null) {
            DataCenter.UserId = bean.getUid();
            DataCenter.HashId = bean.getHashid();
        }
        mIvSetting = (ImageView) findViewById(R.id.iv_setting);
        mIvQRCode = (ImageView) findViewById(R.id.iv_qr_code);
        mTvGatheringCode = (TextView) findViewById(R.id.tv_gathering_code);
        mIvHomeBg = (ImageView) findViewById(R.id.iv_home_bg);
        mLlEditeInfo = (LinearLayout) findViewById(R.id.ll_edite_info);
        mLlActiveList = (LinearLayout) findViewById(R.id.ll_active_list);
        mLlManagerAccount = (LinearLayout) findViewById(R.id.ll_manager_account);
        mLlManagerFinance = (LinearLayout) findViewById(R.id.ll_manager_finance);
        mLlWantShow = (LinearLayout) findViewById(R.id.ll_want_show);
        tvIsTest = (TextView) findViewById(R.id.tv_is_test);
        if (Constants.BaseUrl.equals("http://test.zhongxinyingjia.com/Api/")) {
            tvIsTest.setVisibility(View.VISIBLE);
        } else {
            tvIsTest.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == mIvSetting.getId()) {
            gotoActivity(SettingsActivity.class);
        } else if (view.getId() == mLlEditeInfo.getId()) {
            //资料编辑
            gotoActivity(ShopInfoActivity.class);
        } else if (view.getId() == mLlActiveList.getId()) {
            // 活动列表
            gotoActivity(ActiveListActivity.class);
        } else if (view.getId() == mLlManagerAccount.getId()) {
            //查看管理会员账号
            gotoActivity(ManagerVipActivity.class);
        } else if (view.getId() == mLlManagerFinance.getId()) {
            //财务管理
            gotoActivity(FinancialManagerActivity.class);
        } else if (view.getId() == mLlWantShow.getId()) {
            //我要展示
            gotoActivity(ShopShowActivity.class);
        } else if (view.getId() == mIvHomeBg.getId()) {
            // 公告活动
            if (!EmptyUtils.isEmpty(shopActiveImg) && !EmptyUtils.isEmpty(shopActivityUrl)) {
                Intent urlIntent = new Intent(context, BaseUrlActivity.class);
                urlIntent.putExtra(BaseUrlActivity.MAIN_URL, shopActivityUrl);
//                urlIntent.putExtra(BaseUrlActivity.MAIN_TITLE, shopActivityTitle);
                startActivity(urlIntent);
            } else {
                showToastMsg("暂时没有新活动！");
            }
        }
    }

    @Override
    protected void setStatusBar() {
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(mIvSetting);
        attachClickListener(mLlEditeInfo);
        attachClickListener(mLlManagerAccount);
        attachClickListener(mLlActiveList);
        attachClickListener(mLlManagerFinance);
        attachClickListener(mLlWantShow);
        attachClickListener(mIvHomeBg);
        goToHttpReqs();
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
                    versionCompareDialog = new VersionCompareDialog(context, getActivity(), appVersionEntity.getDescription(),
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

    private void goToHttpReqs() {
        CommonSubscriber<IndexBean> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                statusContent();
                IndexBean bean = (IndexBean) o;
                ImageLoaderUtils.displaySmallPhoto(mIvQRCode, bean.getPay_img());
                String proportion = bean.getProportion();
                String ratio = bean.getRatio();
                mTvGatheringCode.setText("ID" + bean.getId());
                SPUtils.setShareString("proportion", proportion);
                SPUtils.setShareString("ratio", ratio);
                SPUtils.setShareString("management_ID", bean.getManagementID());
                SPUtils.setShareString("management_Num", bean.getManagementNum());
                SPUtils.setShareString("management_Code", bean.getManagementCode());
                if ("1".equals(bean.getManagementLimit())) {
                    SPUtils.setShareBoolean(AccountConstants.ACCOUNT_IS_LIMIT, true);
                } else {
                    SPUtils.setShareBoolean(AccountConstants.ACCOUNT_IS_LIMIT, false);
                }


                shopActiveImg = bean.getNew_shop_activity_img();
                shopActivityUrl = bean.getNew_shop_activity_url();
                if (!EmptyUtils.isEmpty(shopActiveImg)) {
//                    ImageLoaderUtils.displaySmallPhoto(mIvHomeBg, bean.getNew_shop_activity_url());
                    ImageLoaderUtils.displaySmallPhoto(mIvHomeBg, bean.getNew_shop_activity_img());
                } else {
                    ImageLoaderUtils.displaySmallPhoto(mIvHomeBg, R.mipmap.main_bg);
                }

            }

            @Override
            public void onError(String e, int code) {
                statusContent();
                showToastMsg(e);
            }
        });
        BusinessUserMethods.getInstance().index(subscriber);
        rxManager.add(subscriber);
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
                Toast.makeText(MainActivity.this, "再按一次退出" + getString(R.string.app_name), Toast.LENGTH_SHORT).show();
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