package com.hzxmkuar.sxmaketnew.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.retrofit.model.Home;
import com.common.retrofit.model.TodaysRevenue;
import com.hzxmkuar.sxmaketnew.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodayRevenueAdapter extends RecyclerView.Adapter<TodayRevenueAdapter.TodayRevenueViewHolder> {

    private List<TodaysRevenue> list = new ArrayList<>();
    private Context context;
    private final LayoutInflater inflater;

    public TodayRevenueAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<TodaysRevenue> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void clearData() {
        this.list.clear();
        notifyDataSetChanged();
    }

    public void addAllData(List<TodaysRevenue> dataList) {
        this.list.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public TodayRevenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TodayRevenueViewHolder(inflater.inflate(R.layout.item_today_revenue, parent, false));
    }

    @Override
    public void onBindViewHolder(TodayRevenueViewHolder holder, int position) {
        TodaysRevenue todaysRevenue = list.get(position);
        holder.tvTime.setText(todaysRevenue.getPay_time().substring(0,5));
        holder.tvWithdrawValue.setText(context.getString(R.string.plus, todaysRevenue.getXindou()));
        holder.tvCashValue.setText(context.getString(R.string.plus, todaysRevenue.getPay_money()));
        holder.tvSum.setText(context.getString(R.string.format_total_money, todaysRevenue.getSubtotal()));
    }

    @Override
    public int getItemCount() {
        return null == list ? 0 : list.size();
    }

    public class TodayRevenueViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_withdraw_value)
        TextView tvWithdrawValue;
        @BindView(R.id.tv_cash_value)
        TextView tvCashValue;
        @BindView(R.id.tv_sum)
        TextView tvSum;

        public TodayRevenueViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
