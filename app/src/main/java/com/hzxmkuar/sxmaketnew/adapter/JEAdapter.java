package com.hzxmkuar.sxmaketnew.adapter;

import android.content.Context;

import com.common.retrofit.entity.result.FBean;
import com.common.retrofit.entity.result.FiniBean;
import com.common.widget.recyclerview.refresh.adapter.CommonAdapter;
import com.common.widget.recyclerview.refresh.adapter.ViewHolder;
import com.hzxmkuar.sxmaketnew.R;

import java.util.List;

public class JEAdapter extends CommonAdapter<FiniBean.ListBean> {
    public JEAdapter(Context context, List<FiniBean.ListBean> data) {
        super(context, data);
    }

    @Override
    protected void convert(ViewHolder holder, final FiniBean.ListBean item, int position) {
        holder.setText(R.id.time, item.getCreate_time());
        holder.setText(R.id.num, "+" + item.getWithdraw_money());
    }

    @Override
    protected int getItemViewLayoutId(int position, FiniBean.ListBean item) {
        return R.layout.item_jf;
    }
}