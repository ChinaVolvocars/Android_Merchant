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
    private int clickType = 2;
    private List<WithdrawlBillEntity.WithdrawlBillItemEntity> dataList;

    public WithdrawBillAdapter(Context mContext, int clickType, List<WithdrawlBillEntity.WithdrawlBillItemEntity> dataList) {
        this.mContext = mContext;
        this.clickType = clickType;
        this.dataList = dataList;
    }

    /**
     * 清除数据
     */
    public void clearData() {
        dataList.clear();
        notifyDataSetChanged();
    }

    /**
     * 填充数据
     *
     * @param list
     */
    public void addAll(List<WithdrawlBillEntity.WithdrawlBillItemEntity> list) {
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 切换adapter的状态
     */
    public void changeClickType(int type) {
        this.clickType = type;
        notifyDataSetChanged();
    }

    @Override
    public WithdrawBillAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_withdrawl_bill, parent, false);
        MyHolder viewHolder = new MyHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WithdrawBillAdapter.MyHolder holder, int position) {
        holder.tv_date.setText(dataList.get(position).getCreate_time());

        String money = dataList.get(position).getMoney();

        //15 21
        SpannableStringBuilder stringMoney = new SpannableStringBuilder();
        stringMoney.append(mContext.getString(R.string.format_total_money, money));
        RelativeSizeSpan sizeSpan01 = new RelativeSizeSpan(1.0f);
        RelativeSizeSpan sizeSpan02 = new RelativeSizeSpan(1.4f);
        stringMoney.setSpan(sizeSpan01, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        stringMoney.setSpan(sizeSpan02, 1, stringMoney.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        if (clickType == 2) {
            holder.tv_divide_line.setBackgroundResource(R.mipmap.yellow_devide_line);
            holder.tv_money.setTextColor(UIUtils.getColor(R.color.color_fcc80a));
        } else {
            holder.tv_divide_line.setBackgroundResource(R.mipmap.blue_devide_line);
            holder.tv_money.setTextColor(UIUtils.getColor(R.color.color_55a7fb));
        }
        if (position == dataList.size() - 1) {
            holder.tv_divide_line.setVisibility(View.INVISIBLE);
        } else {
            holder.tv_divide_line.setVisibility(View.VISIBLE);
        }
        holder.tv_money.setText(stringMoney);

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
