package com.hzxmkuar.sxmaketnew.home;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.adapter.helper.IRecyclerViewHelper;
import com.common.event.BaseEvent;
import com.common.event.EventBusConstants;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.widget.imageview.image.ImageLoaderUtils;
import com.common.widget.recyclerview.refresh.recycleview.XRecyclerView;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.adapter.ShopShowsRvAdapter;
import com.common.retrofit.entity.result.ShopShowsEntity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 商家——我要展示
 * Created by little_bug on 2018/10/22.
 */
public class ShopShowActivity extends BaseMvpActivity {
    private ImageView mIvShowBack;
    private TextView tv_title;
    private ImageView mIvShopShowDetails;
    private ImageView mTvShowOrHide;
    private TextView mTvAddNewShows;
    private boolean isShow = true;
    private XRecyclerView mRvShopShowsList;
    private ShopShowsRvAdapter mShopShowsRvAdapter;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_show;
    }

    @Override
    protected void onViewCreated() {
        registerEvent();
        mIvShowBack = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("众商联盟-我要展示");
        mIvShopShowDetails = (ImageView) findViewById(R.id.iv_shop_show_details);
        mTvShowOrHide = (ImageView) findViewById(R.id.tv_show_or_hide);
        mTvAddNewShows = (TextView) findViewById(R.id.tv_add_new_shows);
        mIvShopShowDetails.setVisibility(View.VISIBLE);
        ImageLoaderUtils.display(mTvShowOrHide, R.mipmap.shop_show_arrow_up_icon);
        mRvShopShowsList = (XRecyclerView) findViewById(R.id.rv_shop_shows_list);
        mRvShopShowsList.setNestedScrollingEnabled(false);
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(mIvShowBack);
        attachClickListener(mTvShowOrHide);
        attachClickListener(mTvAddNewShows);
        getShopShowsList();
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == mIvShowBack.getId()) {
            finish();
        } else if (view.getId() == mTvShowOrHide.getId()) {
            isShow = !isShow;
            if (isShow) {
                ImageLoaderUtils.display(mTvShowOrHide, R.mipmap.shop_show_arrow_up_icon);
                mIvShopShowDetails.setVisibility(View.VISIBLE);
            } else {
                ImageLoaderUtils.display(mTvShowOrHide, R.mipmap.shop_show_arrow_down_icon);
                mIvShopShowDetails.setVisibility(View.GONE);
            }
        } else if (view.getId() == mTvAddNewShows.getId()) {
            gotoActivity(AddNewActiveActivity.class);
        }
    }


    /**
     * 获取商家个活动列表
     */
    private void getShopShowsList() {
        CommonSubscriber<ShopShowsEntity> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                statusContent();
                ShopShowsEntity entity = (ShopShowsEntity) o;
                List<ShopShowsEntity.ShopShowsItemEntity> entityListItem = entity.getList();
                if (null != entityListItem && entityListItem.size() > 0) {
                    initRvAdapter(entityListItem);
                }
            }

            @Override
            public void onError(String e, int code) {
                statusContent();
                showToastMsg(e);
            }
        });
        List<String> getActiveListParama = new ArrayList<>();
        getActiveListParama.add("time");
        getActiveListParama.add("uid");
        BusinessUserMethods.getInstance().getShopAllActivesList(subscriber, getActiveListParama);
        rxManager.add(subscriber);
    }

    /**
     * 初始化适配器
     */
    private void initRvAdapter(List<ShopShowsEntity.ShopShowsItemEntity> dataList) {
        if (null == mShopShowsRvAdapter) {
            mShopShowsRvAdapter = new ShopShowsRvAdapter(context, dataList);
        }
        IRecyclerViewHelper.init().setRecycleLineLayout(context, LinearLayoutManager.VERTICAL, mRvShopShowsList);
        mRvShopShowsList.setHasFixedSize(true);
        mRvShopShowsList.setLoadMoreEnabled(false);
        mRvShopShowsList.setRefreshEnabled(false);
        mRvShopShowsList.setAdapter(mShopShowsRvAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void reGetShopShowsDatas(BaseEvent event) {
        switch (event.getTag()) {
            case EventBusConstants.SHOP_ADD_NEW_ACTIVE:
                mShopShowsRvAdapter = null;
                getShopShowsList();
                break;
        }
    }

}
