package com.hzxmkuar.sxmaketnew;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.utils.EmptyUtils;
import com.common.utils.SPUtils;

/**
 * 申请管理会员账号
 * Created by Administrator on 2018/8/24.
 */
public class ManagerVipActivity extends BaseMvpActivity {
    private ImageView mIvManagerVipBack;
    private TextView mTvVipAccount;
    private TextView mTvFansCount;
    private ImageView mIvQrImg;
    private TextView mTvIdcode;

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


        String managementId = SPUtils.getShareString("management_ID");
        String managementNum = SPUtils.getShareString("management_Num");
        String managementCode = SPUtils.getShareString("management_Code");
        if (!EmptyUtils.isEmpty(managementId) && !EmptyUtils.isEmpty(managementNum) && !EmptyUtils.isEmpty(managementCode)){
            mTvVipAccount.setText(managementId);
            mTvFansCount.setText(managementNum);
            mTvIdcode.setText(managementCode);
        }

    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(mIvManagerVipBack);
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == mIvManagerVipBack.getId()) {
            finish();
        }
    }


}
