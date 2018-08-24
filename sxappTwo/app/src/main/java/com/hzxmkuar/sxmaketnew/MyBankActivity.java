package com.hzxmkuar.sxmaketnew;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.adapter.helper.IRecyclerViewHelper;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.BankListBean;
import com.common.retrofit.methods.CenterMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.BankFormBean;
import com.common.utils.EmptyUtils;
import com.common.utils.SizeUtils;
import com.common.widget.DividerDecoration.MarginDecoration;
import com.common.widget.imageview.image.ImageLoaderUtils;
import com.common.widget.recyclerview.refresh.adapter.CommonAdapter;
import com.common.widget.recyclerview.refresh.adapter.ViewHolder;
import com.common.widget.recyclerview.refresh.recycleview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 我的银行卡
 * Created by STH on 2017/6/15.
 */
public class MyBankActivity extends BaseMvpActivity {
    private XRecyclerView recyclerView;
    private MsgCenterAdapter adapter;
    private List<BankListBean.ListBean> bean = new ArrayList<>();
    private Map<String, BankFormBean> mBankFormBeanList = new HashMap<>();
    private TextView mTvAddCard;
    private Dialog mDialog;
    private ImageView mBack;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bank;
    }

    @Override
    protected void setStatusBar() {
    }

    @Override
    protected void onViewCreated() {
        recyclerView = (XRecyclerView) findViewById(R.id.recyclerview);
        mTvAddCard = (TextView) findViewById(R.id.tv_add_card);
        mBack = (ImageView) findViewById(R.id.back);
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(mTvAddCard);
        attachClickListener(mBack);
        setRecyclerView();
    }

    @Override
    protected void onResume() {
        statusLoading();
        goToHttpReq();
        super.onResume();
    }

    private void goToHttpReq() {
        CommonSubscriber<BankListBean> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                statusContent();
                BankListBean httpListBean = (BankListBean) o;
                List<BankListBean.ListBean> beanList = httpListBean.getList();
                recyclerView.loadMoreComplete();

                // 下拉刷新
                if (mIsRefreshOrLoadMore == 0) {
                    recyclerView.refreshComplete();
                    adapter.clear();
                }

                if (EmptyUtils.isNotEmpty(beanList)) {
                    bean = beanList;
                    adapter.addAll(bean);
                    statusContent();
                } else if (mIsRefreshOrLoadMore == 0) {
                    //statusEmpty();
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
                statusContent();
                showToastMsg(e);
                recyclerView.setNoMore(true);
                recyclerView.refreshComplete();
                recyclerView.loadMoreComplete();
            }
        });
        CenterMethods.getInstance().bankList(subscriber, mPageIndex);
        rxManager.add(subscriber);
    }

    private void goToHttpReqdel(String id) {
        showProgressingDialog();
        CommonSubscriber<Object> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                dismissProgressDialog();
                showToastMsg("删除成功");
                goToHttpReq();
            }

            @Override
            public void onError(String e, int code) {
                dismissProgressDialog();
                showToastMsg(e);
            }
        });
        CenterMethods.getInstance().delBank(subscriber, id);
        rxManager.add(subscriber);
    }

    private void setRecyclerView() {
        adapter = new MsgCenterAdapter(context, bean);
        IRecyclerViewHelper.init().setRecycleLineLayout(context, LinearLayoutManager.VERTICAL, recyclerView);
        recyclerView.addItemDecoration(new MarginDecoration(LinearLayoutManager.VERTICAL, (int) SizeUtils.dp2px(context, 0)));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPageIndex = 1;
                mIsRefreshOrLoadMore = 0;
                recyclerView.refreshComplete();
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
                recyclerView.refreshComplete();
            }
        });
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == mTvAddCard.getId()) {
            gotoActivity(AddBankCardActivity.class);
        } else if (mBack.getId() == view.getId()) {
            finish();
        }
    }

    public class MsgCenterAdapter extends CommonAdapter<BankListBean.ListBean> {
        public MsgCenterAdapter(Context context, List<BankListBean.ListBean> data) {
            super(context, data);
        }

        @Override
        protected void convert(final ViewHolder holder, final BankListBean.ListBean item, final int position) {
            holder.setText(R.id.name, item.getBank_name());
            holder.setText(R.id.card, item.getCard_number());
            ImageLoaderUtils.displayCircle((ImageView) holder.getParentView().findViewById(R.id.ima), item.getCard_logo());
            ImageLoaderUtils.displaySmallPhoto((ImageView) holder.getParentView().findViewById(R.id.iv_picture), item.getCard_bank_background());
            holder.setOnClickListener(R.id.mLlDelete, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //showdialog(item,"是否删除尾数"+item.getBank_id_num().substring(item.getBank_id_num().length()-4,item.getBank_id_num().length())+"银行卡");
                    goToHttpReqdel(item.getId());
                }

            });
            holder.setOnClickListener(R.id.ll_ll, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = item.getCard_number();
                    name = name.substring(name.length() - 4, name.length());
                    Intent intent = new Intent();
                    intent.putExtra("name", name);
                    intent.putExtra("id", item.getId());
                    setResult(100, intent);
                    finish();
                }
            });
        }

        @Override
        protected int getItemViewLayoutId(int position, BankListBean.ListBean item) {
            return R.layout.item_mybank;
        }
    }
    /*private void showdialog(final String item, final String tels) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = View.inflate(context, R.layout.dialog_del, null);
        builder.setView(dialogView);
        mDialog = builder.show();
        TextView yes = (TextView) dialogView.findViewById(R.id.yes);
        TextView tel = (TextView) dialogView.findViewById(R.id.money);
        TextView no = (TextView) dialogView.findViewById(R.id.no);
        tel.setText(tels);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                //取消
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                //确定
                //goToHttpReqs(item.getBank_id());
            }
        });
    }*/
}
