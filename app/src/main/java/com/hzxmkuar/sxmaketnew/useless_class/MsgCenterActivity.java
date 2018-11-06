//package com.hzxmkuar.sxmaketnew.activity;
//
//import android.content.Context;
//import android.support.v7.widget.LinearLayoutManager;
//import android.view.View;
//import android.widget.ImageView;
//
//import com.common.adapter.helper.IRecyclerViewHelper;
//import com.common.mvp.BaseMvpActivity;
//import com.common.mvp.BasePresenter;
//import com.common.utils.SizeUtils;
//import com.common.widget.DividerDecoration.MarginDecoration;
//import com.common.widget.itemview.MyRadioRelativeLayout;
//import com.common.widget.recyclerview.refresh.adapter.CommonAdapter;
//import com.common.widget.recyclerview.refresh.adapter.ViewHolder;
//import com.common.widget.recyclerview.refresh.recycleview.XRecyclerView;
//import com.hzxmkuar.sxmaketnew.R;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//
//
///**
// * Created by STH on 2017/6/15.
// */
//
//public class MsgCenterActivity extends BaseMvpActivity {
//    private XRecyclerView recyclerView;
//    private MsgCenterAdapter adapter;
//    private List<String> bean = new ArrayList<>();
//    private ImageView mBack;
//
//    @Override
//    protected BasePresenter createPresenterInstance() {
//        return null;
//    }
//
//    @Override
//    protected void setStatusBar() {
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_msg;
//    }
//
//
//    @Override
//    protected void onViewCreated() {
//        recyclerView = (XRecyclerView) findViewById(R.id.recyclerview);
//        mBack = (ImageView) findViewById(R.id.back);
//    }
//
//    @Override
//    protected void doLogicFunc() {
//        attachClickListener(mBack);
//        bean.add("");
//        bean.add("");
//        bean.add("");
//        setRecyclerView();
//       /* statusLoading();
//        goToHttpReq();*/
//    }
//    /*private void goToHttpReq() {
//        CommonSubscriber<MsgBena> subscriber = new CommonSubscriber<>(new SubscriberListener() {
//            @Override
//            public void onNext(Object o) {
//                statusContent();
//                recyclerView.loadMoreComplete();
//                MsgBena beans = (MsgBena) o;
//                List<MsgBena.ListsBean> beanList = beans.getLists();
//                // 下拉刷新
//                if (mIsRefreshOrLoadMore == 0) {
//                    recyclerView.refreshComplete();
//                    adapter.clear();
//                }
//
//                if (EmptyUtils.isNotEmpty(beanList)) {
//                    bean = beanList;
//                    adapter.addAll(bean);
//                    statusContent();
//                }
//
//                // 接口未做分页直接调用设置列表无更多
//                //recyclerView.setNoMore(true);
//
//                // 接口未做分页 因此注释
//                if (EmptyUtils.isEmpty(beanList)) {
//                    recyclerView.setNoMore(true);
//                } else {
//                    mIsHasNoData = beanList.size() < mPageSize;
//                    recyclerView.setNoMore(mIsHasNoData);
//                }
//            }
//
//            @Override
//            public void onError(String e, int code) {
//                statusError();
//                showToastMsg(e);
//                recyclerView.setNoMore(true);
//                recyclerView.refreshComplete();
//                recyclerView.loadMoreComplete();
//            }
//        });
//        MessageMethods.getInstance().listData(subscriber,mPageIndex);
//        rxManager.add(subscriber);
//    }*/
//    /*private void goToHttpReqs(String id) {
//        CommonSubscriber<Object> subscriber = new CommonSubscriber<>(new SubscriberListener() {
//            @Override
//            public void onNext(Object o) {
//                statusContent();
//                showToastMsg("删除成功");
//                goToHttpReq();
//            }
//
//            @Override
//            public void onError(String e, int code) {
//                statusContent();
//                showToastMsg(e);
//            }
//        });
//        MessageMethods.getInstance().delData(subscriber,id);
//        rxManager.add(subscriber);
//    }*/
//
//    private void setRecyclerView() {
//        adapter = new MsgCenterAdapter(context, bean);
//        IRecyclerViewHelper.init().setRecycleLineLayout(context, LinearLayoutManager.VERTICAL, recyclerView);
//        recyclerView.addItemDecoration(new MarginDecoration(LinearLayoutManager.VERTICAL, (int) SizeUtils.dp2px(context, 0)));
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
//            @Override
//            public void onRefresh() {
//                mPageIndex = 1;
//                mIsRefreshOrLoadMore = 0;
//                //goToHttpReq();
//                recyclerView.refreshComplete();
//            }
//
//            @Override
//            public void onLoadMore() {
//                if (mIsHasNoData) {
//                    recyclerView.loadMoreComplete();
//                    recyclerView.setNoMore(true);
//                    return;
//                }
//                mPageIndex++;
//                mIsRefreshOrLoadMore = 1;
//                //goToHttpReq();
//                recyclerView.refreshComplete();
//            }
//        });
//    }
//    @Override
//    protected void onViewClicked(View view) {
//        if (view.getId()==mBack.getId()){
//            finish();
//        }
//    }
//    public class MsgCenterAdapter extends CommonAdapter<String>
//    {
//        private HashSet<MyRadioRelativeLayout> set = new HashSet<>();
//        public MsgCenterAdapter(Context context, List<String> data) {
//            super(context, data);
//        }
//
//        @Override
//        protected void convert(ViewHolder holder, final String item, final int position)
//        {
//
//        }
//
//        @Override
//        protected int getItemViewLayoutId(int position, String item) {
//            return R.layout.item_msg;
//        }
//    }
//}
