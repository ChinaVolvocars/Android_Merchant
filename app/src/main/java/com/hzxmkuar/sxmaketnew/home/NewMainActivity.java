package com.hzxmkuar.sxmaketnew.home;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.adapter.MainAdapter;

import butterknife.BindView;

public class NewMainActivity extends BaseMvpActivity {


    @BindView(R.id.recycler_view)
    RecyclerView rv;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_new;
    }

    @Override
    protected void onViewCreated() {
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setHasFixedSize(true);

        MainAdapter adapter = new MainAdapter(this);
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void onViewPagerItemClick(View view, int position) {
                Log.e("广告的点击事件", "onViewPagerItemClick: " + position);
            }

            @Override
            public void onServiceItemClick(View view, int position) {
                Log.e("服务的点击事件", "onServiceItemClick: " + position);
            }

            @Override
            public void onActivityItemClick(View view, int position) {
                Log.e("活动的点击事件", "onActivityItemClick: " + position);
            }

            @Override
            public void onOtherItemClick(View view, String tag) {
                Log.e("其他的点击事件", "onOtherItemClick: " + tag);
            }
        });

    }

    @Override
    protected void doLogicFunc() {

    }
}
