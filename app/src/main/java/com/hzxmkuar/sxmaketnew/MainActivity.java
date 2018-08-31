package com.hzxmkuar.sxmaketnew;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.DataCenter;
import com.common.retrofit.entity.result.IndexBean;
import com.common.retrofit.entity.result.UserBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.ActivityStack;
import com.common.utils.EmptyUtils;
import com.common.utils.SPUtils;
import com.common.widget.imageview.image.ImageLoaderUtils;
import com.hzxmkuar.sxmaketnew.common.BaseUrlActivity;

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
     * 查看管理会员账号
     */
    private LinearLayout mLlManagerAccount;
    /**
     * 财务管理
     */
    private LinearLayout mLlManagerFinance;
    /**
     *
     */
    private ImageView mIvHomeBg;
    /**
     * 商家收款码id
     */
    private TextView mTvGatheringCode;
    private ImageView iv_notic_icon;
    private String shopActivityTitle = "";
    private String shopActivityUrl = "";

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
        mLlManagerAccount = (LinearLayout) findViewById(R.id.ll_manager_account);
        mLlManagerFinance = (LinearLayout) findViewById(R.id.ll_manager_finance);
        iv_notic_icon = (ImageView) findViewById(R.id.iv_notic_icon);
    }


    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == mIvSetting.getId()) {
            gotoActivity(SystemSetActivity.class);
        } else if (view.getId() == mLlEditeInfo.getId()) {
            //资料编辑
            gotoActivity(MydpActivity.class);
        } else if (view.getId() == mLlManagerAccount.getId()) {
            //查看管理会员账号
            gotoActivity(ManagerVipActivity.class);
        } else if (view.getId() == mLlManagerFinance.getId()) {
            //财务管理
            gotoActivity(CWMsgActivity.class);
        } else if (view.getId() == iv_notic_icon.getId()) {
            // 公告活动
            Intent urlIntent = new Intent(context, BaseUrlActivity.class);
            if (!EmptyUtils.isEmpty(shopActivityTitle) && !EmptyUtils.isEmpty(shopActivityUrl)) {
                Log.i(TAG, "onViewClicked:      shopActivityTitle       " + shopActivityTitle);
                urlIntent.putExtra(BaseUrlActivity.MAIN_URL, shopActivityUrl);
                urlIntent.putExtra(BaseUrlActivity.MAIN_TITLE, shopActivityTitle);
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
        attachClickListener(mLlManagerFinance);
        attachClickListener(iv_notic_icon);
        goToHttpReqs();
    }

    private void goToHttpReqs() {
        CommonSubscriber<IndexBean> subscriber = new CommonSubscriber<>(new SubscriberListener() {

            @Override
            public void onNext(Object o) {
                statusContent();
                IndexBean bean = (IndexBean) o;
//                ImageLoaderUtils.displaySmallPhoto(mIvHomeBg, bean.getAd_img());
                ImageLoaderUtils.displaySmallPhoto(mIvQRCode, bean.getPay_img());
                String proportion = bean.getProportion();
                String ratio = bean.getRatio();
                mTvGatheringCode.setText("ID" + bean.getId());
                SPUtils.setShareString("proportion", proportion);
                SPUtils.setShareString("ratio", ratio);
                SPUtils.setShareString("management_ID", bean.getManagementID());
                SPUtils.setShareString("management_Num", bean.getManagementNum());
                SPUtils.setShareString("management_Code", bean.getManagementCode());
                shopActivityTitle = bean.getNew_shop_activity_name();
                shopActivityUrl = bean.getNew_shop_activity_url();

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