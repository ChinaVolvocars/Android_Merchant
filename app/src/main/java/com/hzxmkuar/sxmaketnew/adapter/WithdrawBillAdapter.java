package com.hzxmkuar.sxmaketnew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.retrofit.entity.result.WithdrawlBillEntity;
import com.common.utils.UIUtils;
import com.hzxmkuar.sxmaketnew.R;

import java.util.List;

/**
 * 提现账款适配器
 * Created by jc on 2018/11/15.
 */
public class WithdrawBillAdapter extends RecyclerView.Adapter<WithdrawBillAdapter.MyHolder> {
    private Context mContext;
    private int clickType = 0;
    private List<WithdrawlBillEntity> dataList;

    public WithdrawBillAdapter(Context mContext, int clickType, List<WithdrawlBillEntity> dataList) {
        this.mContext = mContext;
        this.clickType = clickType;
        this.dataList = dataList;
    }

    @Override
    public WithdrawBillAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_withdrawl_bill,parent,false);
        MyHolder viewHolder = new MyHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WithdrawBillAdapter.MyHolder holder, int position) {
        holder.tv_date.setText(dataList.get(position).getDate());
        holder.tv_money.setText(dataList.get(position).getMoney());
        if (clickType == 0){
            holder.tv_divide_line.setBackgroundResource(R.mipmap.yellow_devide_line);
            holder.tv_money.setTextColor(UIUtils.getColor(R.color.color_fcc80a));
        }else {
            holder.tv_divide_line.setBackgroundResource(R.mipmap.blue_devide_line);
            holder.tv_money.setTextColor(UIUtils.getColor(R.color.color_55a7fb));
        }
        if (position == dataList.size()-1){
            holder.tv_divide_line.setVisibility(View.INVISIBLE);
        }else {
            holder.tv_divide_line.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        TextView tv_date;
        TextView tv_money;
        TextView tv_divide_line;
        public MyHolder(View itemView) {
            super(itemView);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_money = (TextView) itemView.findViewById(R.id.tv_money);
            tv_divide_line = (TextView) itemView.findViewById(R.id.tv_divide_line);
        }
    }
}
