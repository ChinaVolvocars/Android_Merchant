package com.hzxmkuar.sxmaketnew.home;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.adapter.helper.IRecyclerViewHelper;
import com.common.mvp.BaseHasListActivity;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.CheckRecordEntity;
import com.common.retrofit.entity.result.CheckTicketsDetailsEntity;
import com.common.retrofit.entity.result.CouponInfoEntity;
import com.common.retrofit.entity.result.CouponListItemEntity;
import com.common.retrofit.methods.CouponMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.DateUtils;
import com.common.utils.EmptyUtils;
import com.common.utils.UIUtils;
import com.common.widget.recyclerview.refresh.recycleview.XRecyclerView;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.adapter.TicketsCheckDetailsAdapter;
import com.hzxmkuar.sxmaketnew.utils.JCSpanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 验券记录 —— 难券详情
 * Created by jc on 2019/1/2.
 */
public class TicketsCheckDetailActiivty extends BaseHasListActivity {
    private XRecyclerView recyclerViewCheckTickets;
    private TicketsCheckDetailsAdapter ticketsCheckDetailsAdapter;
    private ImageView iv_back;
    private String mCodeId = "";
    private TextView tv_coupon_count_total;
    private TextView tv_coupon_name;
    private TextView tv_valid_time;
    private TextView tv_protocol_price;
    private View mHeaderView;
    private List<CouponListItemEntity> couponList = new ArrayList<>();

    @Override
    protected void doCurrentRefresh() {
        getServerData();
    }

    @Override
    protected void doCurrentLoadMore() {
        getServerData();
    }


    @Override
    protected int getCurrentLayoutId() {
        return R.layout.activity_tickets_check_details;
    }

    @Override
    protected void onCurrentViewCreate() {
        mCodeId = getIntent().getStringExtra("codeId");
        iv_back = (ImageView) findViewById(R.id.iv_back);
        recyclerViewCheckTickets = (XRecyclerView) findViewById(R.id.recyclerView_check_tickets);
        initRecyclerView();
        initAdapter();
        getServerData();
    }

    @Override
    protected void doCurrentLogic() {
        attachClickListener(iv_back);

    }

    @Override
    protected XRecyclerView initRecyclerView() {
        return recyclerViewCheckTickets;
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == iv_back.getId()) {
            finish();
        }
    }

    private void initAdapter() {
        ticketsCheckDetailsAdapter = new TicketsCheckDetailsAdapter(context);
        mHeaderView = LayoutInflater.from(context).inflate(R.layout.item_check_details_header,null,false);
        ticketsCheckDetailsAdapter.addHeaderView(mHeaderView);

        tv_coupon_count_total = (TextView)mHeaderView.findViewById(R.id.tv_coupon_count_total);
        tv_coupon_name = (TextView) mHeaderView.findViewById(R.id.tv_coupon_name);
        tv_valid_time = (TextView) mHeaderView.findViewById(R.id.tv_valid_time);
        tv_protocol_price = (TextView) mHeaderView.findViewById(R.id.tv_protocol_price);
        IRecyclerViewHelper.init().setRecycleGridLayout(context, recyclerViewCheckTickets, 1);
        recyclerViewCheckTickets.setHasFixedSize(true);
        recyclerViewCheckTickets.setAdapter(ticketsCheckDetailsAdapter);
    }


    private void getServerData() {
        showProgressingDialog();
        CommonSubscriber<CheckTicketsDetailsEntity> subscriber = new CommonSubscriber<>(new SubscriberListener<CheckTicketsDetailsEntity>() {
            @Override
            public void onNext(CheckTicketsDetailsEntity result) {
//                (共4笔，合计￥120.00)
//                tv_coupon_count_total.setText("(共"+result.getCount()+"笔，合计￥"+result.getSum()+"元)");

                tv_coupon_count_total.setText(new JCSpanUtils()
                        .append("(共"+result.getCount()+"笔，合计").setForegroundColor(UIUtils.getColor(R.color.color_cccccc))
                        .append("￥"+result.getSum()+"元").setForegroundColor(UIUtils.getColor(R.color.color_fdc009))
                        .append(")").setForegroundColor(UIUtils.getColor(R.color.color_cccccc))
                        .create());

                CouponInfoEntity infoEntity = result.getCoupon_info();
                tv_coupon_name.setText(infoEntity.getName());
                tv_valid_time.setText("有效时间:"+"\n"+infoEntity.getStart_time()+" "+infoEntity.getEnd_time());
                tv_protocol_price.setText("¥"+infoEntity.getProtocol_price());
                couponList = result.getCoupon_list();
                onNextMethodsAddDatas(couponList,null,ticketsCheckDetailsAdapter);

            }
            @Override
            public void onError(String e, int code) {
                showToastMsg(e);
                dismissProgressDialog();
            }
        });
        CouponMethods.getInstance().couponInfo(subscriber,mCodeId, mPageIndex);
        rxManager.add(subscriber);

    }
}
