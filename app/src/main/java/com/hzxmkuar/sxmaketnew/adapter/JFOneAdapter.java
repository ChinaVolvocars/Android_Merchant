package com.hzxmkuar.sxmaketnew.adapter;

import android.content.Context;

import com.common.retrofit.entity.result.FiniBean;
import com.common.widget.recyclerview.refresh.adapter.CommonAdapter;
import com.common.widget.recyclerview.refresh.adapter.ViewHolder;
import com.hzxmkuar.sxmaketnew.R;

import java.util.List;

public class JFOneAdapter extends CommonAdapter<FiniBean.ListBean> {
    private int mFromeType;

    public JFOneAdapter(Context context, List<FiniBean.ListBean> data, int fromeType) {
        super(context, data);
        this.mFromeType = fromeType;
    }

    @Override
    protected void convert(ViewHolder holder, final FiniBean.ListBean item, int position) {
        holder.setText(R.id.time, item.getCreate_time());
        if (0 == mFromeType) {
            holder.setText(R.id.num, "+" + item.getXindou());
        } else if (1 == mFromeType) {
            holder.setText(R.id.num, "+" + item.getMoney());
        } else if (2 == mFromeType) {
            holder.setText(R.id.num, "+" + item.getTotal_money());
        }
    }

    @Override
    protected int getItemViewLayoutId(int position, FiniBean.ListBean item) {
        return R.layout.item_jf;
    }
}