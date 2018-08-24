package com.hzxmkuar.sxmaketnew.fragment.fragment;

import android.view.View;

import com.common.adapter.helper.IRecyclerViewHelper;
import com.common.mvp.BaseMvpFragment;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.FBean;
import com.common.retrofit.entity.result.FiniBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.EmptyUtils;
import com.common.widget.recyclerview.refresh.recycleview.XRecyclerView;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.fragment.adapter.JFOneAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by STH on 2017/8/1.
 */

public class JFOneFragment extends BaseMvpFragment {

    private XRecyclerView recyclerView;
    private List<FBean> bean = new ArrayList<>();
    private JFOneAdapter adapter;
    /**
     * 列表类型<br/>
     * 1、消费抵账鑫豆提现申请列表<br/>
     * 2、现金到账列表<br/>
     * 3、今日销售列表<br/>
     */
    private int fromType;

    public JFOneFragment(int type) {
        this.fromType = type;
    }

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_refresh;
    }

    @Override
    protected void onViewCreated(View view) {
        recyclerView = (XRecyclerView) view.findViewById(R.id.recyclerview);
    }

    @Override
    protected void doLogicFunc() {
        setRecyclerView();
        goToHttpReq();
    }

    private void goToHttpReq() {
        CommonSubscriber<FiniBean> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                statusContent();
                recyclerView.loadMoreComplete();
                FiniBean beans = (FiniBean) o;
                List<FiniBean.ListBean> list = beans.getList();
                List<FBean> beanList = new ArrayList<>();
                for (FiniBean.ListBean beanss : list) {
                    if (fromType == 0) {
                        beanList.add(new FBean(beanss.getXindou(), beanss.getCreate_time()));
                    } else if (fromType == 1) {
                        beanList.add(new FBean(beanss.getMoney(), beanss.getCreate_time()));
                    } else {
                        beanList.add(new FBean(beanss.getTotal_money(), beanss.getCreate_time()));
                    }
                }
                // 下拉刷新
                if (mIsRefreshOrLoadMore == 0) {
                    recyclerView.refreshComplete();
                    adapter.clear();
                }

                if (EmptyUtils.isNotEmpty(beanList)) {
                    bean = beanList;
                    adapter.addAll(bean);
                    statusContent();
                }

                // 接口未做分页直接调用设置列表无更多
                //recyclerView.setNoMore(true);

                // 接口未做分页 因此注释
                if (EmptyUtils.isEmpty(beanList)) {
                    recyclerView.setNoMore(true);
                } else {
                    mIsHasNoData = beanList.size() < mPageSize;
                    recyclerView.setNoMore(mIsHasNoData);
                }
            }

            @Override
            public void onError(String e, int code) {
                statusError();
                showToastMsg(e);
                recyclerView.setNoMore(true);
                recyclerView.refreshComplete();
                recyclerView.loadMoreComplete();
            }
        });
        BusinessUserMethods.getInstance().financeList(subscriber, mPageIndex);
        rxManager.add(subscriber);
    }

    private void setRecyclerView() {
        adapter = new JFOneAdapter(context, bean);
        IRecyclerViewHelper.init().setRecycleGridLayout(context, recyclerView, 1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPageIndex = 1;
                mIsRefreshOrLoadMore = 0;
                goToHttpReq();
            }

            @Override
            public void onLoadMore() {
                if (mIsHasNoData) {
                    recyclerView.loadMoreComplete();
                    recyclerView.setNoMore(true);
                    return;
                }
                mPageIndex++;
                mIsRefreshOrLoadMore = 1;
                goToHttpReq();
            }
        });
    }
}
