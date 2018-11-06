package com.hzxmkuar.sxmaketnew.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.EmptyUtils;
import com.common.utils.SPUtils;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.event.AccountConstants;
import com.hzxmkuar.sxmaketnew.view.dialog.RequestionDialog;
import com.hzxmkuar.sxmaketnew.widget.SwitchButton;
import java.util.ArrayList;
import java.util.List;

/**
 * 申请管理会员账号
 * Created by Administrator on 2018/8/24.
 */
public class ManagerVipActivity extends BaseMvpActivity {
    private static final String TAG = "ManagerVipActivity";
    private ImageView mIvManagerVipBack;
    private TextView mTvVipAccount;
    private TextView mTvFansCount;
    private ImageView mIvQrImg;
    private TextView mTvIdcode;
    private TextView btn_req;
    private SwitchButton switchBtn;
    /**
     * 是否限制   <br/>
     * true 为限制    <br/>
     * false 为不限制    <br/>
     */
    private boolean mIsLimite ;
    @Override
    protected void setStatusBar() {

    }

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_manager_vip;
    }

    @Override
    protected void onViewCreated() {
        mIvManagerVipBack = (ImageView) findViewById(R.id.iv_manager_vip_back);
        mTvVipAccount = (TextView) findViewById(R.id.tv_vip_account);
        mTvFansCount = (TextView) findViewById(R.id.tv_fans_count);
        mIvQrImg = (ImageView) findViewById(R.id.iv_qr_img);
        mTvIdcode = (TextView) findViewById(R.id.tv_id_code);
        btn_req = (TextView) findViewById(R.id.btn_req);
        switchBtn = (SwitchButton) findViewById(R.id.switch_bt);


        String managementId = SPUtils.getShareString("management_ID");
        String managementNum = SPUtils.getShareString("management_Num");
        String managementCode = SPUtils.getShareString("management_Code");
        if (!EmptyUtils.isEmpty(managementId) && !EmptyUtils.isEmpty(managementNum) && !EmptyUtils.isEmpty(managementCode)) {
            mTvVipAccount.setText(managementId);
            mTvFansCount.setText(managementNum);
            mTvIdcode.setText(managementCode);
        }

        mIsLimite = SPUtils.getShareBoolean(AccountConstants.ACCOUNT_IS_LIMIT);
        switchBtn.setOpened(mIsLimite);
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(mIvManagerVipBack);
        attachClickListener(btn_req);
        attachClickListener(switchBtn);
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == mIvManagerVipBack.getId()) {
            finish();
        }else if (view.getId() == btn_req.getId()) {
            RequestionDialog dialog = new RequestionDialog(context);
            dialog.show();
        } else if (view.getId() == switchBtn.getId()) {
            mIsLimite = !mIsLimite;
            if (mIsLimite){
                setFunctionOpenOrClose("1", 1);
                SPUtils.setShareBoolean(AccountConstants.ACCOUNT_IS_LIMIT, true);
            }else {
                setFunctionOpenOrClose("2", 2);
                SPUtils.setShareBoolean(AccountConstants.ACCOUNT_IS_LIMIT, false);
            }
        }

    }

    /**
     * 设置消费功能的打开与关闭 <br/>
     * 1（true）为限制 <br/>
     * 2 (false) 为不限制 <br/>
     * 默认为 1（true）限制
     *
     * @param switchType
     */
    private void setFunctionOpenOrClose(final String switchType, final int clickType) {
        List<String> paramaList = new ArrayList<>();
        paramaList.add("time");
        paramaList.add("uid");
        paramaList.add("managementLimit");
        CommonSubscriber<Object> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                statusContent();
                showToastMsg("修改状态成功");
            }
            @Override
            public void onError(String e, int code) {
                statusContent();
                showToastMsg(e);
            }
        });
        BusinessUserMethods.getInstance().setFunctionOpenOrClose(subscriber, paramaList, switchType);
        rxManager.add(subscriber);
    }

}
