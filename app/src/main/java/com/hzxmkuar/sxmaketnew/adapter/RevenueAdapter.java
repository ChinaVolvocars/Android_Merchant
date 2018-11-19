package com.hzxmkuar.sxmaketnew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.retrofit.model.Revenue;
import com.hzxmkuar.sxmaketnew.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RevenueAdapter extends RecyclerView.Adapter<RevenueAdapter.RevenueViewHolder> {

    private List<Revenue> list;
    private Context context;
    private int type;

    public RevenueAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Revenue> list, int type) {
        this.list = list;
        this.type = type;
        notifyDataSetChanged();
    }


    @Override
    public RevenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_revenue_day, parent, false);
        return new RevenueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RevenueViewHolder holder, int position) {
        Revenue revenue = list.get(position);
        holder.tvTime.setText(type == 0 ? revenue.getDay() : revenue.getMonth());
        holder.tvAmount.setText(context.getString(R.string.format_total_money, revenue.getTotal_money()));
        int status = revenue.getStatus();
        holder.ivStatus.setImageResource(status == 1 ? R.mipmap.ic_rise : R.mipmap.ic_drop);


    }

    @Override

    public int getItemCount() {
        return null == list ? 0 : list.size();
    }

    public class RevenueViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_amount)
        TextView tvAmount;
        @BindView(R.id.iv_status)
        ImageView ivStatus;


        public RevenueViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
