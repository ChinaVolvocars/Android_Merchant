package com.hzxmkuar.sxmaketnew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        holder.tvTime.setText("");
        holder.tvAmount.setText("");

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


        public RevenueViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
