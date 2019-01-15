package com.hzxmkuar.sxmaketnew.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.retrofit.entity.result.CheckRecordEntity;
import com.common.utils.RegexUtils;
import com.common.widget.recyclerview.refresh.adapter.CommonAdapter;
import com.common.widget.recyclerview.refresh.adapter.ViewHolder;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.home.TicketsCheckDetailActiivty;

import java.util.ArrayList;
import java.util.List;

/**
 * 验券记录 适配器
 * Created by jc on 2019/1/3.
 */
public class CheckRecordsAdapter extends CommonAdapter<CheckRecordEntity.RecordItemEntity> {
    private Context mContext;
    public CheckRecordsAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void convert(ViewHolder holder, final CheckRecordEntity.RecordItemEntity item, final int position) {
        holder.setText(R.id.tv_check_time, item.getVerify_time());
        holder.setText(R.id.tv_ticket_name, item.getName());
        holder.setText(R.id.tv_ticket_code, item.getTicket_number());
        holder.setText(R.id.tv_ticket_value, "￥"+item.getProtocol_price());
        holder.setOnClickListener(R.id.ll_item_content, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onRecordItemClickListener.itemClick(dataList.get(position).getId(), position);
                mContext.startActivity(new Intent(mContext, TicketsCheckDetailActiivty.class).putExtra("codeId",item.getId()));
            }
        });
    }

    @Override
    protected int getItemViewLayoutId(int position, CheckRecordEntity.RecordItemEntity item) {
        return R.layout.item_check_tickets_record;
    }
}
