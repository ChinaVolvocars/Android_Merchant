package com.hzxmkuar.sxmaketnew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.retrofit.entity.result.ApplyRecodEntity;
import com.common.utils.UIUtils;
import com.hzxmkuar.sxmaketnew.R;

import java.util.List;

/**
 * 代收代付/发票提现
 * Created by jc on 2018/11/18.
 */
public class ApplyRecordAdapter extends RecyclerView.Adapter<ApplyRecordAdapter.ApplyRecordHolder> {
    private Context mContext;
    private List<ApplyRecodEntity.RecordEntity> dataList;
    /**
     * mRecordType申请记录类型   <br/>
     * 1 为 发票提现申请记录     <br/>
     * 2 为 代收代付申请记录     <br/>
     */
    private int mRecordType = 1;
    private OnItemClickListerner clickListerner;

    public interface OnItemClickListerner {
        /**
         *  条目点击
         * @param position  点击的条目  <br/>
         * @param id  条目id <br/>
         * @param isToActivity      是否需要跳转到Activity  <br/>
         * @param isInvoiceWithdrawl    是滞是发票提现  <br/>
         */
        void itemClick(View view, int position, String id, String isToActivity,String isInvoiceWithdrawl);
    }


    public void setClickListerner(OnItemClickListerner clickListerner) {
        this.clickListerner = clickListerner;
    }

    public ApplyRecordAdapter(Context context, List<ApplyRecodEntity.RecordEntity> datas, int type) {
        this.mContext = context;
        this.dataList = datas;
        this.mRecordType = type;
    }

    @Override
    public ApplyRecordAdapter.ApplyRecordHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_apply_record, parent, false);
        ApplyRecordHolder viewHolder = new ApplyRecordHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ApplyRecordAdapter.ApplyRecordHolder holder, final int position) {
        String state = dataList.get(position).getStatus();
        holder.tv_create_time.setText(dataList.get(position).getCreate_time());
        holder.tv_apply_money.setText("¥" + dataList.get(position).getMoney());
        final String itemId = dataList.get(position).getId();
        if (mRecordType == 1) {
            if ("3".equals(state) || "6".equals(state)) {
                holder.tv_state.setTextColor(UIUtils.getColor(R.color.color_e0421d));
            } else {
                holder.tv_state.setTextColor(UIUtils.getColor(R.color.color_60aeff));
            }
            if ("1".equals(state)) {
                holder.tv_state.setText("对账中");
            } else if ("3".equals(state)) {
                holder.tv_state.setText("对账失败");
            } else if ("4".equals(state)) {
                holder.tv_state.setText("发票审核中");
            } else if ("5".equals(state)) {
                holder.tv_state.setText("发票审核成功");
            } else if ("6".equals(state)) {
                holder.tv_state.setText("发票审核失败");
            }
            if ("2".equals(state)) {
                holder.tv_submit_invoice.setVisibility(View.VISIBLE);
                holder.tv_state.setVisibility(View.GONE);
                holder.tv_submit_invoice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (null != clickListerner) {
                            clickListerner.itemClick(view, position, itemId, "跳转到Activity","");
                        }
                    }
                });

            } else {
                holder.tv_submit_invoice.setVisibility(View.GONE);
                holder.tv_state.setVisibility(View.VISIBLE);
            }
        } else {  // 代收代付
            if ("1".equals(state)) {
                holder.tv_state.setText("对账中");
            } else if ("2".equals(state)) {
                holder.tv_state.setText("对账成功");
            } else if ("3".equals(state)) {
                holder.tv_state.setText("对账失败");
            }
            if ("3".equals(state)) {
                holder.tv_state.setTextColor(UIUtils.getColor(R.color.color_e0421d));
            } else {
                holder.tv_state.setTextColor(UIUtils.getColor(R.color.color_F8DB97));
            }
        }

        holder.tv_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != clickListerner) {
                    if (mRecordType == 1){
                        clickListerner.itemClick(view, position, itemId, "","invoiceWithdraw");
                    }else {
                        clickListerner.itemClick(view, position, itemId, "","");
                    }

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ApplyRecordHolder extends RecyclerView.ViewHolder {
        private TextView tv_create_time;
        private TextView tv_apply_money;
        private TextView tv_state;
        private TextView tv_submit_invoice;

        public ApplyRecordHolder(View itemView) {
            super(itemView);
            tv_create_time = (TextView) itemView.findViewById(R.id.tv_create_time);
            tv_apply_money = (TextView) itemView.findViewById(R.id.tv_apply_money);
            tv_state = (TextView) itemView.findViewById(R.id.tv_state);
            tv_submit_invoice = (TextView) itemView.findViewById(R.id.tv_submit_invoice);

        }
    }


}
