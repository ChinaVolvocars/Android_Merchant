package com.hzxmkuar.sxmaketnew.home;

import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.common.adapter.helper.IRecyclerViewHelper;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.ConsumeRightsEntity;
import com.common.retrofit.entity.result.ShareBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.EmptyUtils;
import com.common.widget.recyclerview.refresh.recycleview.XRecyclerView;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.adapter.ConsumeRecordAdapter;
import com.hzxmkuar.sxmaketnew.entity.YQBean;
import com.hzxmkuar.sxmaketnew.view.SharePopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 消费权限
 * Created by jc on 2019/1/3.
 */

public class ConsumeRightsActivity extends BaseMvpActivity {
    private ImageView mIvBack;
    private TextView mTvTitle;
    private XRecyclerView recyclerViewRecord;
    private ConsumeRecordAdapter consumeRecordAdapter;
    private ImageView iv_tip01, iv_tip02;
    private Button btn_share;
    private LinearLayout ll_consume_function_content, ll_relationship_content;
    private TextView tv_manager_account;
    private TextView tv_invitation_code;
    private TextView tv_balance;
    private TextView tv_nodata_tips;
    private FrameLayout ll_roote_content;
    private LinearLayout ll_empty_view;
    private SharePopupWindow sharePopupWindow;
    private MyLoadingListener loadingListener;
    private List<ConsumeRightsEntity.ConsumeRecordItemEntity> consumeRecordList = new ArrayList<>();
    private View mHeaderView;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_consume_rights;
    }

    @Override
    protected void onViewCreated() {
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTvTitle.setText("消费权限");
        recyclerViewRecord = (XRecyclerView) findViewById(R.id.recyclerView_record);
        iv_tip02 = (ImageView) findViewById(R.id.iv_tip02);
        iv_tip01 = (ImageView) findViewById(R.id.iv_tip01);
        ll_roote_content = (FrameLayout) findViewById(R.id.ll_roote_content);
        ll_empty_view = (LinearLayout) findViewById(R.id.ll_empty_view);
        tv_nodata_tips = (TextView) findViewById(R.id.tv_nodata_tips);
        tv_nodata_tips.setText("暂时没有消费记录");
        initHeadView();
        loadingListener = new MyLoadingListener();
        initAdapter();
        getServerData();

    }

    /**
     * 初始化头布局
     */
    private void initHeadView() {
        mHeaderView = LayoutInflater.from(context).inflate(R.layout.layout_consume_rights_header, null, false);
        btn_share = (Button) mHeaderView.findViewById(R.id.btn_share);
        ll_consume_function_content = (LinearLayout) mHeaderView.findViewById(R.id.ll_consume_function_content);
        ll_relationship_content = (LinearLayout) mHeaderView.findViewById(R.id.ll_relationship_content);
        tv_manager_account = (TextView) mHeaderView.findViewById(R.id.tv_manager_account);
        tv_invitation_code = (TextView) mHeaderView.findViewById(R.id.tv_invitation_code);
        tv_balance = (TextView) mHeaderView.findViewById(R.id.tv_balance);
    }

    private void initAdapter() {
        consumeRecordAdapter = new ConsumeRecordAdapter(context);
        consumeRecordAdapter.addHeaderView(mHeaderView);
        IRecyclerViewHelper.init().setRecycleLineLayout(context, LinearLayoutManager.VERTICAL, recyclerViewRecord);
        recyclerViewRecord.setAdapter(consumeRecordAdapter);
        recyclerViewRecord.setNestedScrollingEnabled(false);
        recyclerViewRecord.setLoadingListener(loadingListener);
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(mIvBack);
        attachClickListener(iv_tip01);
        attachClickListener(iv_tip02);
//        attachClickListener(btn_share);
        attachClickListener(ll_consume_function_content);
        attachClickListener(ll_relationship_content);
        attachClickListener(ll_empty_view);
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == mIvBack.getId()) {
            finish();
        } else if (view.getId() == iv_tip01.getId()) {
            iv_tip01.setVisibility(View.GONE);
        } else if (view.getId() == iv_tip02.getId()) {
            iv_tip02.setVisibility(View.GONE);
        } else if (view.getId() == btn_share.getId()) {
//            showToastMsg("分享好友");
            ShareBean shareEntity = new ShareBean("测试标题", "000", "测试内容", "测试链接");

            YQBean qrEntity = new YQBean();
            qrEntity.setCode(shareEntity.getContent());
            qrEntity.setImg(shareEntity.getImg());
            if (null != shareEntity) {
                if (null != shareEntity && null != qrEntity) {
                    shareEntity = new ShareBean(shareEntity.getTitle(), shareEntity.getImg(), shareEntity.getContent(), shareEntity.getUrl());
                }
                if (sharePopupWindow == null) {
                    sharePopupWindow = new SharePopupWindow(context, ll_roote_content, shareEntity);
                } else {
                    sharePopupWindow.showAtLocation(ll_roote_content, Gravity.CENTER | Gravity.BOTTOM, 0, 0);
                }
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 0.7f;//设置阴影透明度
                getActivity().getWindow().setAttributes(lp);
                sharePopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                        lp.alpha = 1f;
                        getActivity().getWindow().setAttributes(lp);
                    }
                });
            }
        } else if (view.getId() == ll_consume_function_content.getId()) {
//            showToastMsg("消费功能");
            gotoActivity(ConsumeFunctionActivity.class);
        } else if (view.getId() == ll_relationship_content.getId()) {
//            showToastMsg("关联账号");
            gotoActivity(RelationshipAccountActivity.class);
        } else if (view.getId() == ll_empty_view.getId()) {
            if (null != ll_empty_view) {
                loadingListener.onRefresh();
            }
        }
    }

    /**
     * 获取服务器数据
     */
    public void getServerData() {
        showProgressingDialog();
        CommonSubscriber<ConsumeRightsEntity> subscriber = new CommonSubscriber<>(new SubscriberListener<ConsumeRightsEntity>() {
            @Override
            public void onNext(ConsumeRightsEntity result) {
                recyclerViewRecord.loadMoreComplete();
                dismissProgressDialog();
                ConsumeRightsEntity.ManagerUser userEntity = result.getUser();
                tv_manager_account.setText("管理员账号:" + userEntity.getUsername());
                tv_invitation_code.setText("邀请码:" + userEntity.getCode());
                tv_balance.setText("当前管理员账号余额:" + userEntity.getXindou() + "鑫豆");

                List<ConsumeRightsEntity.ConsumeRecordItemEntity> recocrdList = result.getList();

                // 下拉刷新
                if (mIsRefreshOrLoadMore == 0) {
                    recyclerViewRecord.refreshComplete();
                    consumeRecordAdapter.clear();
                }

                if (EmptyUtils.isNotEmpty(recocrdList)) {
                    consumeRecordList = recocrdList;
                    consumeRecordAdapter.addAll(consumeRecordList);
                    statusContent();
                }

                if (consumeRecordList.size() <= 0) {
                    ll_empty_view.setVisibility(View.VISIBLE);
                } else {
                    ll_empty_view.setVisibility(View.GONE);
                }

                if (EmptyUtils.isEmpty(recocrdList)) {
                    recyclerViewRecord.setNoMore(true);
                } else {
                    mIsHasNoData = recocrdList.size() < mPageSize;
                    recyclerViewRecord.setNoMore(mIsHasNoData);
                }
            }

            @Override
            public void onError(String e, int code) {
                showToastMsg(e);
                dismissProgressDialog();
            }
        });
        BusinessUserMethods.getInstance().consumePower(subscriber, mPageIndex);
        rxManager.add(subscriber);
    }

    class MyLoadingListener implements XRecyclerView.LoadingListener {

        @Override
        public void onRefresh() {
            mPageIndex = 1;
            mIsRefreshOrLoadMore = 0;
            getServerData();
        }

        @Override
        public void onLoadMore() {
            if (mIsHasNoData) {
                recyclerViewRecord.loadMoreComplete();
                recyclerViewRecord.setNoMore(true);
                return;
            }
            mPageIndex++;
            mIsRefreshOrLoadMore = 1;
            getServerData();
        }
    }
}
