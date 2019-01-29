package com.common.mvp;

import android.view.View;
import android.widget.LinearLayout;

import com.common.utils.EmptyUtils;
import com.common.widget.recyclerview.refresh.adapter.CommonAdapter;
import com.common.widget.recyclerview.refresh.recycleview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jc on 2019/1/11.
 */

public abstract class BaseHasListActivity<T> extends BaseMvpActivity implements XRecyclerView.LoadingListener {
    protected XRecyclerView xRecyclerView;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return getCurrentLayoutId();
    }

    @Override
    protected void setStatusBar() {

    }

    @Override
    protected void onViewCreated() {
        onCurrentViewCreate();
        xRecyclerView = initRecyclerView();
        xRecyclerView.setLoadingListener(this);

    }

    @Override
    protected void doLogicFunc() {
        doCurrentLogic();
    }

    @Override
    public void onRefresh() {
        doCurrentRefresh();
        mPageIndex = 1;
        mIsRefreshOrLoadMore = 0;
    }

    @Override
    public void onLoadMore() {
        if (mIsHasNoData) {
            if (null != xRecyclerView)
                xRecyclerView.loadMoreComplete();
            xRecyclerView.setNoMore(true);
            return;
        }
        mPageIndex++;
        mIsRefreshOrLoadMore = 1;
        doCurrentLoadMore();

    }

    protected abstract int getCurrentLayoutId();

    protected abstract void onCurrentViewCreate();

    protected abstract void doCurrentLogic();

    protected abstract XRecyclerView initRecyclerView();

    protected abstract void doCurrentRefresh();

    protected abstract void doCurrentLoadMore();

    protected List<T> tempList = null;

    protected void onNextMethodsAddDatas(List<T> list, LinearLayout ll_emp, CommonAdapter adapter) {
        dismissProgressDialog();
        xRecyclerView.loadMoreComplete();
        tempList = new ArrayList<>();
        List<T> beanList = list;
        if (null != ll_emp) {
            if (beanList.size() <= 0) {
                ll_emp.setVisibility(View.VISIBLE);
            } else {
                ll_emp.setVisibility(View.GONE);
            }
        }
        // 下拉刷新
        if (mIsRefreshOrLoadMore == 0) {
            xRecyclerView.refreshComplete();
            adapter.clear();
        }

        if (EmptyUtils.isNotEmpty(beanList)) {
            tempList = beanList;
            adapter.addAll(beanList);
        }

        if (EmptyUtils.isEmpty(beanList)) {
            xRecyclerView.setNoMore(true);
        } else {
            mIsHasNoData = beanList.size() < mPageSize;
            xRecyclerView.setNoMore(mIsHasNoData);
        }
    }


}
