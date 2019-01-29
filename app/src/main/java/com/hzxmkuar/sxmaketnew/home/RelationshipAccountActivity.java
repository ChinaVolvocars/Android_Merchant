package com.hzxmkuar.sxmaketnew.home;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.adapter.helper.IRecyclerViewHelper;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.RelationShipListEntity;
import com.common.retrofit.entity.result.RelationShipListItemEntity;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.EmptyUtils;
import com.common.widget.recyclerview.refresh.recycleview.XRecyclerView;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.adapter.RelationShipAccountAdapter;
import com.hzxmkuar.sxmaketnew.view.TabSelectView;

import java.util.ArrayList;
import java.util.List;

/**
 * 关联账号
 * Created by jc on 2019/1/4.
 */
public class RelationshipAccountActivity extends BaseMvpActivity {
    private ImageView mIvBack;
    private TextView mTvTitle;
    private TabSelectView tabselectview;
    private XRecyclerView recyclerView_account_list;
    private RelationShipAccountAdapter relationShipAccountAdapter;
    private List<RelationShipListItemEntity> relationShipList = new ArrayList<>();
    private View mHeadView;
    /**
     * 关联类型  <br/>
     * 1 为关联会员 <br/>
     * 2 为关联商家 <br/>
     */
    private int relationType = 2;
    private MyLoadingListener loadingListener;
    private TextView tv_count_num;
    private TextView tv_share_earng_count;
    private LinearLayout ll_emp;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_relationship_account;
    }

    @Override
    protected void onViewCreated() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("关联账号");
        tabselectview = (TabSelectView) findViewById(R.id.tabselectview);
        recyclerView_account_list = (XRecyclerView) findViewById(R.id.recyclerView_account_list);
        ll_emp = (LinearLayout) findViewById(R.id.ll_emp);
        loadingListener = new MyLoadingListener();
        mHeadView = LayoutInflater.from(context).inflate(R.layout.item_relation_ship_header, null, false);
        tv_count_num = (TextView) mHeadView.findViewById(R.id.tv_count_num);
        tv_share_earng_count = (TextView) mHeadView.findViewById(R.id.tv_share_earng_count);
        initAdapter();
        getServerData();
        tabselectview.setOnTabSelectedListener(new TabSelectView.OnTabSelectedListener() {
            @Override
            public void tabSelected(int tabSlectedPosition) {
                if (1 == tabSlectedPosition) {
                    relationType = 2;
                } else {
                    relationType = 1;
                }

                if (null != relationShipAccountAdapter) {
                    relationShipAccountAdapter.setRelationType(relationType);
                }
                if (null != loadingListener) {
                    loadingListener.onRefresh();
                }
            }
        });
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(mIvBack);
    }

    private void initAdapter() {
        relationShipAccountAdapter = new RelationShipAccountAdapter(context);
        relationShipAccountAdapter.addHeaderView(mHeadView);
        IRecyclerViewHelper.init().setRecycleGridLayout(context, recyclerView_account_list, 1);
        recyclerView_account_list.setHasFixedSize(true);
        recyclerView_account_list.setAdapter(relationShipAccountAdapter);
        recyclerView_account_list.setLoadingListener(loadingListener);

    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == mIvBack.getId()) {
            finish();
        }
    }

    /**
     * 获取相关数据
     */
    private void getServerData() {
        showProgressingDialog();
        CommonSubscriber<RelationShipListEntity> subscriber = new CommonSubscriber<>(new SubscriberListener<RelationShipListEntity>() {
            @Override
            public void onNext(RelationShipListEntity result) {
                dismissProgressDialog();
                List<RelationShipListItemEntity> recocrdList = result.getUser();
                if (1 == relationType) {
                    tv_count_num.setText("共" + result.getZj_user_count() + "人");
                    tv_share_earng_count.setText("累计为我赚取" + result.getUser_num_count() + "鑫豆");
                } else {
                    tv_count_num.setText("共" + result.getZj_shop_count() + "人");
                    tv_share_earng_count.setText("累计为我赚取" + result.getShop_num_count() + "鑫豆");
                }

                // 下拉刷新
                if (mIsRefreshOrLoadMore == 0) {
                    recyclerView_account_list.refreshComplete();
                    relationShipAccountAdapter.clear();
                }

                if (EmptyUtils.isNotEmpty(recocrdList)) {
                    relationShipList = recocrdList;
                    relationShipAccountAdapter.addAll(relationShipList);
                    statusContent();
                }

                if (relationShipList.size() <= 0) {
                    ll_emp.setVisibility(View.VISIBLE);
                } else {
                    ll_emp.setVisibility(View.GONE);
                }

                if (EmptyUtils.isEmpty(recocrdList)) {
                    recyclerView_account_list.setNoMore(true);
                } else {
                    mIsHasNoData = recocrdList.size() < mPageSize;
                    recyclerView_account_list.setNoMore(mIsHasNoData);
                }
            }

            @Override
            public void onError(String e, int code) {
                showToastMsg(e);
                dismissProgressDialog();

            }
        });
        BusinessUserMethods.getInstance().relation(subscriber, String.valueOf(relationType), mPageIndex);
        rxManager.add(subscriber);
    }


    class MyLoadingListener implements XRecyclerView.LoadingListener {

        @Override
        public void onRefresh() {
            mPageIndex = 1;
            mIsRefreshOrLoadMore = 0;
            relationShipList.clear();
            if (relationShipAccountAdapter != null) {
                relationShipAccountAdapter.clear();
            }
            getServerData();

        }

        @Override
        public void onLoadMore() {
            if (mIsHasNoData) {
                recyclerView_account_list.loadMoreComplete();
                recyclerView_account_list.setNoMore(true);
                return;
            }
            mPageIndex++;
            mIsRefreshOrLoadMore = 1;
            getServerData();
        }
    }
}
