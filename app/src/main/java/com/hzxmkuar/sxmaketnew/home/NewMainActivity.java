package com.hzxmkuar.sxmaketnew.home;


import android.support.v7.widget.RecyclerView;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.hzxmkuar.sxmaketnew.R;

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


    }

    @Override
    protected void doLogicFunc() {

    }
}
