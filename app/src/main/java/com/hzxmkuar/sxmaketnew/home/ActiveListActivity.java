package com.hzxmkuar.sxmaketnew.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.adapter.helper.IRecyclerViewHelper;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.ActiveListEntity;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.EmptyUtils;
import com.common.widget.recyclerview.refresh.recycleview.XRecyclerView;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.adapter.ActiveListAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 *  活动中心
 */
public class ActiveListActivity extends BaseMvpActivity {
    private static final String TAG = "ActiveListActivity";
    private XRecyclerView mRvActiveList;
    private ImageView mIvActiveBack;
    private ActiveListAdapter mActiveListAdapter;
    private List<ActiveListEntity.ActiveListItemEntity> bean = new ArrayList<>();
    private TextView tv_active_count;
    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_active_list;
    }

    @Override
    protected void onViewCreated() {
        mRvActiveList = (XRecyclerView) findViewById(R.id.rv_active_list);
        mIvActiveBack = (ImageView) findViewById(R.id.iv_active_back);
        tv_active_count = (TextView) findViewById(R.id.tv_active_count);

    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(mIvActiveBack);
        getActiveList();
        initRvAdapter();
    }

    @Override
    protected void setStatusBar() {
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == mIvActiveBack.getId()){
            finish();
        }
    }

    private void getActiveList() {
        CommonSubscriber<HttpRespBean<ActiveListEntity>> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                statusContent();
                ActiveListEntity entity= (ActiveListEntity) o;
                List<ActiveListEntity.ActiveListItemEntity> entityItemList = entity.getList();
                // 下拉刷新
                if (mIsRefreshOrLoadMore == 0) {
                    mRvActiveList.refreshComplete();
                    mActiveListAdapter.clear();
                }

                if (EmptyUtils.isNotEmpty(entityItemList)) {
                    bean = entityItemList;
                    tv_active_count.setText("共"+entityItemList.size()+"条");
                    mActiveListAdapter.addAll(entityItemList);
                    statusContent();
                }else {
                    tv_active_count.setVisibility(View.GONE);
                }

                // 接口未做分页直接调用设置列表无更多
                //recyclerView.setNoMore(true);

                // 接口未做分页 因此注释
//                if (EmptyUtils.isEmpty(entityItemList)) {
//                    mRvActiveList.setNoMore(true);
//                } else {
//                    mIsHasNoData = entityItemList.size() < mPageSize;
//                    mRvActiveList.setNoMore(mIsHasNoData);
//                }
            }

            @Override
            public void onError(String e, int code) {
                statusError();
                showToastMsg(e);
                mRvActiveList.setNoMore(true);
                mRvActiveList.refreshComplete();
                mRvActiveList.loadMoreComplete();
            }
        });
        BusinessUserMethods.getInstance().getActiveListData(subscriber, mPageIndex);
        rxManager.add(subscriber);
    }

    private void initRvAdapter() {
        mActiveListAdapter = new ActiveListAdapter(context, bean);
        IRecyclerViewHelper.init().setRecycleGridLayout(context, mRvActiveList, 1);
        mRvActiveList.setHasFixedSize(true);
        mRvActiveList.setAdapter(mActiveListAdapter);
        mRvActiveList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPageIndex = 1;
                mIsRefreshOrLoadMore = 0;
                getActiveList();
            }

            @Override
            public void onLoadMore() {
                mRvActiveList.loadMoreComplete();
                mRvActiveList.setNoMore(true);
                mIsRefreshOrLoadMore = 1;
            }
        });
    }
}
