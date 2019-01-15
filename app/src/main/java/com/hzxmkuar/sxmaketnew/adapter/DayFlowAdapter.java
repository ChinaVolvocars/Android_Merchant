package com.hzxmkuar.sxmaketnew.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.retrofit.model.DayFlowDto;
import com.common.retrofit.model.TodaysRevenue;
import com.hzxmkuar.sxmaketnew.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DayFlowAdapter extends RecyclerView.Adapter<DayFlowAdapter.DayFlowViewHolder> {

    private List<DayFlowDto> list = new ArrayList<>();
    private Context context;
    private final LayoutInflater inflater;

    public DayFlowAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setData(List<DayFlowDto> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void clearData() {
        this.list.clear();
        notifyDataSetChanged();
    }

    public void addAllData(List<DayFlowDto> dataList) {
        this.list.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public DayFlowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DayFlowViewHolder(inflater.inflate(R.layout.item_day_flow, parent, false));
    }

    @Override
    public void onBindViewHolder(DayFlowViewHolder holder, int position) {
        DayFlowDto dayFlowDto = list.get(position);
        holder.tvTime.setText(dayFlowDto.getPay_time());
        holder.tvActualPayment.setText(dayFlowDto.getXindou());
        holder.tvCashPayment.setText(dayFlowDto.getPay_money());
        holder.tvSubsidy.setText(dayFlowDto.getSubsidy());
        holder.tvTransactionSubtotal.setText(context.getResources().getString(R.string.format_total_money, dayFlowDto.getSubtotal()));
    }

    @Override
    public int getItemCount() {
        return null == list ? 0 : list.size();
    }

    public class DayFlowViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;
        //实付鑫豆
        @BindView(R.id.tv_actual_payment)
        TextView tvActualPayment;
        //实付金额
        @BindView(R.id.tv_cash_payment)
        TextView tvCashPayment;
        //补贴
        @BindView(R.id.tv_subsidy)
        TextView tvSubsidy;
        //交易小计
        @BindView(R.id.tv_transaction_subtotal)
        TextView tvTransactionSubtotal;


        public DayFlowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
