package com.hzxmkuar.sxmaketnew.newversion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.adapter.helper.IRecyclerViewHelper;
import com.common.base.Constants;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.ApplyRecodEntity;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.EmptyUtils;
import com.common.widget.recyclerview.refresh.recycleview.XRecyclerView;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.adapter.ApplyRecordAdapter;
import com.hzxmkuar.sxmaketnew.base.BaseUrlActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 代收代付记录 和 发票申请记录
 */
public class RecordActivity extends BaseMvpActivity {
    private static final String TAG = "RecordActivity";
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.t_name)
    TextView tName;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_adv)
    ImageView ivAdv;
    @BindView(R.id.recycler_view)
    XRecyclerView recordRecyclerView;

    @OnClick(R.id.back)
    public void onFinishClicked() {
        finish();
    }

    /**
     * 申请列表类型  <br/>
     * 1 为发票提现申请  <br/>
     * 2 为代收代付申请  <br/>
     */
    private int fromApplyType = 1;
    private ApplyRecordAdapter applyRecordAdapter;
    List<ApplyRecodEntity.RecordEntity> entityDatas = new ArrayList<>();

    @Override
    protected void setStatusBar() {

    }

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_record_new;
    }

    @Override
    protected void onViewCreated() {
    }

    @Override
    protected void doLogicFunc() {

        Bundle bundle = getIntent().getExtras();
        /**
         *  flag参数  <br/>
         *  true 为 代收代付申请记录  <br/>
         *  false 为 发票提现申请记录  <br/>
         */
        boolean flag = bundle.getBoolean("flag", true);
        ivAdv.setImageResource(flag ? R.mipmap.adv_withdrawal_time : R.mipmap.adv_invoice_record);
        tName.setText(flag ? "代收代付申请记录" : "发票提现申请记录");
        if (flag) {
            fromApplyType = 2;
        } else {
            fromApplyType = 1;
        }
        getApplyRecord(fromApplyType);
    }

    /**
     * 获取申请记录列表 <br/>
     *
     * @param applyType 申请记录类型  <</br.>
     *                  1 为票提现申请记录 <br/>
     *                  2 为代收代付申请提现记录 <br/>
     */
    private void getApplyRecord(int applyType) {

        /**
         *
         * [NSString stringWithFormat:@"%@Home/Financial/invoiceResult/id/%@.html",BaseURL,model.obj_id];
         *   bseeUrl/Home/Financial/invoiceResult/id/  recordEntity.getId();   // 发票
         *   bseeUrl/Home/Financial/dsdfResult/id/  recordEntity.getId();   // 代收代付
         *
         */
        CommonSubscriber<ApplyRecodEntity> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                statusContent();
                recordRecyclerView.loadMoreComplete();
                ApplyRecodEntity entity = (ApplyRecodEntity) o;
                List<ApplyRecodEntity.RecordEntity> recordEntities = entity.getList();

                Log.i(TAG, "onNext: " + entity.toString());

                if (null != recordEntities) {
                    entityDatas.clear();
                    entityDatas.addAll(recordEntities);
                }
                // 下拉刷新
                if (mIsRefreshOrLoadMore == 0) {
                    recordRecyclerView.refreshComplete();
                    entityDatas.clear();
                    entityDatas.addAll(recordEntities);
                }
//                if (EmptyUtils.isNotEmpty(beanList)) {
//                    entityDatas = beanList;
//                    statusContent();
//                }

                if (EmptyUtils.isEmpty(entityDatas)) {
                    recordRecyclerView.setNoMore(true);
                } else {
                    mIsHasNoData = entityDatas.size() < mPageSize;
                    recordRecyclerView.setNoMore(mIsHasNoData);
                }
                setRecyclerView();
            }

            @Override
            public void onError(String e, int code) {
                statusError();
                showToastMsg(e);
                recordRecyclerView.setNoMore(true);
                recordRecyclerView.refreshComplete();
                recordRecyclerView.loadMoreComplete();
            }
        });
        BusinessUserMethods.getInstance().getApplyRecord(subscriber, String.valueOf(fromApplyType), mPageIndex);
        rxManager.add(subscriber);
    }

    /**
     *  初始化适配器
     */
    private void setRecyclerView() {
//        Log.i(TAG, "setRecyclerView:  列表条目为： " + entityDatas.size());
        applyRecordAdapter = new ApplyRecordAdapter(context, entityDatas, fromApplyType);
        IRecyclerViewHelper.init().setRecycleGridLayout(context, recordRecyclerView, 1);
        recordRecyclerView.setHasFixedSize(true);
        recordRecyclerView.setAdapter(applyRecordAdapter);
        recordRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPageIndex = 1;
                mIsRefreshOrLoadMore = 0;
                getApplyRecord(fromApplyType);
            }

            @Override
            public void onLoadMore() {
                if (mIsHasNoData) {
                    recordRecyclerView.loadMoreComplete();
                    recordRecyclerView.setNoMore(true);
                    return;
                }
                mPageIndex++;
                mIsRefreshOrLoadMore = 1;
                getApplyRecord(fromApplyType);
            }
        });

        applyRecordAdapter.setClickListerner(new ApplyRecordAdapter.OnItemClickListerner() {
            Intent urlIntent = new Intent(context, BaseUrlActivity.class);
            String subBaseUrl = Constants.BaseUrl.substring(0, Constants.BaseUrl.length() - 4);
            String h5Url = "";
            @Override
            public void itemClick(View view, int position, String id, String isToActivity, String invoiceWithdraw) {
                Log.i(TAG, "itemClick: 是否需要跳转到Activity去     isToActivity        " + isToActivity);
                if (EmptyUtils.isEmpty(isToActivity)) {
//                    bseeUrl/Home/Financial/dsdfResult/id/  recordEntity.getId();   // 代收代付
                    if (EmptyUtils.isEmpty(invoiceWithdraw)) {  // 代收代付
                        h5Url =  subBaseUrl+ "Home/Financial/dsdfResult/id/" + id + ".html";
                        urlIntent.putExtra(BaseUrlActivity.MAIN_URL, h5Url);
                    } else { // 发票提现
//                    bseeUrl/Home/Financial/invoiceResult/id/  recordEntity.getId();   // 发票
                        h5Url =  subBaseUrl + "Home/Financial/invoiceResult/id/" + id + ".html";
                    }
//                    Log.i(TAG, "itemClick:    h5链接是：        " + h5Url);
                    urlIntent.putExtra(BaseUrlActivity.MAIN_URL, h5Url);
                    startActivity(urlIntent);
                } else {  // 跳转到提交发票界面
                    showToastMsg("toActivity");
                }
            }
        });
    }
}
