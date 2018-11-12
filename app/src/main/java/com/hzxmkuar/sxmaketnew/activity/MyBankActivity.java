package com.hzxmkuar.sxmaketnew.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.common.adapter.helper.IRecyclerViewHelper;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.BankListBean;
import com.common.retrofit.methods.CenterMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.BankUtil;
import com.common.utils.EmptyUtils;
import com.common.utils.SizeUtils;
import com.common.widget.DividerDecoration.MarginDecoration;
import com.common.widget.imageview.image.ImageLoaderUtils;
import com.common.widget.recyclerview.refresh.adapter.CommonAdapter;
import com.common.widget.recyclerview.refresh.adapter.ViewHolder;
import com.common.widget.recyclerview.refresh.recycleview.XRecyclerView;
import com.hzxmkuar.sxmaketnew.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 我的银行卡
 * Created by STH on 2017/6/15.
 */
public class MyBankActivity extends BaseMvpActivity {
    private XRecyclerView recyclerView;
    private MsgCenterAdapter adapter;
    private List<BankListBean.ListBean> bean = new ArrayList<>();
    //    private Map<String, BankFormBean> mBankFormBeanList = new HashMap<>();
    private TextView mTvAddCard;
    private ImageView mBack;
    private PopupWindow mPopupWindow;
    private TextView tv_pop_location;

    public static final String ITEM = "item";

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
        mTvAddCard = (TextView) findViewById(R.id.tv_right);
        tv_pop_location = (TextView) findViewById(R.id.tv_pop_location);
        TextView tvName = (TextView) findViewById(R.id.t_name);
        mBack = (ImageView) findViewById(R.id.back);
        tvName.setText("我的银行卡");
        mTvAddCard.setText("添加");
        mTvAddCard.setTextColor(getResources().getColor(R.color.white));
        mTvAddCard.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        mTvAddCard.setBackgroundResource(R.drawable.shape_rectangle_bank_blue);
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
            initPopupWindow();
        } else if (mBack.getId() == view.getId()) {
            finish();
        }
    }

    private void initPopupWindow() {
        // 一个自定义的布局，作为显示的内容
        int layoutId = R.layout.popup_add_bank_card_content_layout;   // 布局ID
        final View contentView = LayoutInflater.from(this).inflate(layoutId, null);
        mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        View.OnClickListener menuItemOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.menu_item1:
                        gotoActivity(AddBankCardToPrivateActivity.class);
                        break;
                    case R.id.menu_item2:
                        gotoActivity(AddBankCardToCompanyActivity.class);
                        break;
                }
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        };
        contentView.findViewById(R.id.menu_item1).setOnClickListener(menuItemOnClickListener);
        contentView.findViewById(R.id.menu_item2).setOnClickListener(menuItemOnClickListener);
        // popupWindow.showAsDropDown(mButton1);  // 默认在mButton1的左下角显示
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
//        int xOffset = mButton1.getWidth() / 2 - contentView.getMeasuredWidth() / 2;
        mPopupWindow.showAsDropDown(tv_pop_location, 0, 0);    // 在mButton1的中间显示
    }

    public class MsgCenterAdapter extends CommonAdapter<BankListBean.ListBean> {
        public MsgCenterAdapter(Context context, List<BankListBean.ListBean> data) {
            super(context, data);
        }

        @Override
        protected void convert(final ViewHolder holder, final BankListBean.ListBean item, final int position) {

            holder.setText(R.id.tv_bank_num, BankUtil.hideBank(item.getCard_number()));
            LinearLayout view = holder.getView(R.id.ll_bank);

            Glide.with(context).load(item.getCard_bank_background())
                    .into(new ViewTarget<View, GlideDrawable>(view) {
                        //括号里为需要加载的控件
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            this.view.setBackground(resource.getCurrent());
                        }
                    });

//            holder.setText(R.id.name, item.getBank_name());
//            holder.setText(R.id.card, item.getCard_number());
//            ImageLoaderUtils.displayCircle((ImageView) holder.getParentView().findViewById(R.id.iv_bank_logo), item.getCard_bank_logo());
//            ImageLoaderUtils.displaySmallPhoto((ImageView) holder.getParentView().findViewById(R.id.iv_picture), item.getCard_bank_background());
//            holder.setOnClickListener(R.id.mLlDelete, new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //showdialog(item,"是否删除尾数"+item.getBank_id_num().substring(item.getBank_id_num().length()-4,item.getBank_id_num().length())+"银行卡");
//                    goToHttpReqdel(item.getId());
//                }
//
//            });
////            tv_card_type
//            if ("1".equals(item.getStatus())) {
//                holder.setText(R.id.tv_card_type, "对私账户");
//            } else if ("2".equals(item.getStatus())) {
//                holder.setText(R.id.tv_card_type, "对公账户");
//            }
            holder.setOnClickListener(R.id.ll_bank, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = item.getCard_number();
                    name = name.substring(name.length() - 4, name.length());
                    Intent intent = new Intent();
                    intent.putExtra("name", name);
                    intent.putExtra("id", item.getId());
                    intent.putExtra(ITEM, item);
                    setResult(100, intent);
                    finish();
                }
            });
        }

        @Override
        protected int getItemViewLayoutId(int position, BankListBean.ListBean item) {
            return R.layout.item_bank_new;
        }
    }
}
