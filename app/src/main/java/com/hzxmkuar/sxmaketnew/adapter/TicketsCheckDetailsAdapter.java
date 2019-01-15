package com.hzxmkuar.sxmaketnew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.retrofit.entity.result.CouponListItemEntity;
import com.common.widget.recyclerview.refresh.adapter.CommonAdapter;
import com.common.widget.recyclerview.refresh.adapter.ViewHolder;
import com.hzxmkuar.sxmaketnew.R;

/**
 * 验券详情列表
 * Created by jc on 2019/1/3.
 */
public class TicketsCheckDetailsAdapter extends CommonAdapter<CouponListItemEntity> {
    private Context mContext;

    public TicketsCheckDetailsAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void convert(ViewHolder holder, final CouponListItemEntity item, int position) {
        holder.setText(R.id.tv_tickets_code, item.getTicket_number());
        holder.setText(R.id.tv_check_date, item.getVerify_time());
        String status = item.getWithdraw_status();
        if ("1".equals(status)) {
            holder.setText(R.id.tv_state, "待提现");

        } else if ("2".equals(status)) {
            holder.setText(R.id.tv_state, "提现中");
        } else {
            holder.setText(R.id.tv_state, "已提现");
        }
//        holder.setOnClickListener(R.id.tv_state, new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showToastMsg(item.getWithdraw_status());
//            }
//        });
        if (position == mData.size()-1){
            holder.setBackgroundResource(R.id.ll_item_content,R.drawable.jx_10_w_conner_bottomleft_bottomright);
        }

    }

    @Override
    protected int getItemViewLayoutId(int position, CouponListItemEntity item) {
        return R.layout.item_check_tickets_detail;
    }



}
