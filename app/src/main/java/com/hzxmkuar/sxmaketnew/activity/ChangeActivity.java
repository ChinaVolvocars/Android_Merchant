package com.hzxmkuar.sxmaketnew.activity;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.ChangeBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.widget.editview.DeleteEditText;
import com.hzxmkuar.sxmaketnew.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by STH on 2018/5/16.
 */
public class ChangeActivity extends BaseMvpActivity {

    private DeleteEditText mNum;
    private TextView mDouzi;
    private TextView mMoney;
    private ImageView mBack;
    private RecyclerView mRecyclerView;
    private List<String> mList = new ArrayList<>();
    private int position = -1;
    private Button mNext;
    private String mId = "";
    private TextView mDouzix;
    private DeleteEditText mNum1;

    @Override
    protected void setStatusBar() {
    }

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change;
    }

    @Override
    protected void onViewCreated() {
        mBack = (ImageView) findViewById(R.id.back);
        mMoney = (TextView) findViewById(R.id.money);
        mDouzi = (TextView) findViewById(R.id.dz);
        mDouzix = (TextView) findViewById(R.id.xdz);
        mNum = (DeleteEditText) findViewById(R.id.num);
        mNum1 = (DeleteEditText) findViewById(R.id.num1);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mNext = (Button) findViewById(R.id.next);
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(mBack);
        attachClickListener(mNext);
        statusLoading();
        goToHttpReqss();
    }

    private void goToHttpReqss() {
        CommonSubscriber<ChangeBean> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                statusContent();
                ChangeBean newTestBean = (ChangeBean) o;
                mDouzi.setText("（剩余" + newTestBean.getXinlidou() + "）");
                mDouzix.setText("（剩余" + newTestBean.getXianglidou() + "）");
                newTestBean.getXianglidou();
                List<ChangeBean.ShopPriceBean> shop_price = newTestBean.getShop_price();
                VideoAdapter adapter = new VideoAdapter(context, shop_price);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 4);
                mRecyclerView.setLayoutManager(gridLayoutManager);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(String e, int code) {
                statusContent();
                showToastMsg(e);
            }
        });
        BusinessUserMethods.getInstance().store(subscriber);
        rxManager.add(subscriber);
    }

    private void goToHttpReqsss(String id, String editTextStr, String textStr) {
        showProgressingDialog();
        CommonSubscriber<Object> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                dismissProgressDialog();
                showToastMsg("提交审核成功");
            }

            @Override
            public void onError(String e, int code) {
                dismissProgressDialog();
                showToastMsg(e);
            }
        });
        BusinessUserMethods.getInstance().storeSubmit(subscriber, id, editTextStr, textStr);
        rxManager.add(subscriber);
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == mBack.getId()) {
            finish();
        } else if (view.getId() == mNext.getId()) {
            if (mId.equals("")) {
                showToastMsg("请选择购买的类型");
                return;
            }
            goToHttpReqsss(mId, getEditTextStr(mNum), getEditTextStr(mNum1));
        }
    }

    class VideoAdapter extends
            RecyclerView.Adapter<VideoAdapter.ViewHolder> {
        private LayoutInflater mInflater;
        private List<ChangeBean.ShopPriceBean> mDatas;
        private Context mContext;

        public VideoAdapter(Context context, List<ChangeBean.ShopPriceBean> datats) {
            mInflater = LayoutInflater.from(context);
            mDatas = datats;
            mContext = context;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View arg0) {
                super(arg0);
            }

            TextView name;
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        /**
         * 创建ViewHolder
         */
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
            View view = mInflater.inflate(R.layout.item_change,
                    viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            return viewHolder;
        }

        /**
         * 设置值
         */
        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
            viewHolder.name.setText(mDatas.get(i).getNum() + "张");
            if (position == i) {
                viewHolder.name.setBackgroundColor(getResources().getColor(R.color.base_color));
            } else {
                viewHolder.name.setBackgroundColor(getResources().getColor(R.color.white));
            }
            viewHolder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    position = i;
                    mId = mDatas.get(i).getId();
                    mMoney.setText(mDatas.get(i).getPrice());
                    notifyDataSetChanged();
                }
            });
        }
    }
}
