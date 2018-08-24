package com.hzxmkuar.sxmaketnew.fragment.adapter;

import android.content.Context;

import com.common.retrofit.entity.result.FBean;
import com.common.widget.recyclerview.refresh.adapter.CommonAdapter;
import com.common.widget.recyclerview.refresh.adapter.ViewHolder;
import com.hzxmkuar.sxmaketnew.R;

import java.util.List;

public class JFOneAdapter extends CommonAdapter<FBean>
{
    public JFOneAdapter(Context context, List<FBean> data) {
        super(context, data);
    }

    @Override
    protected void convert(ViewHolder holder, final FBean item, int position)
    {
        holder.setText(R.id.time,item.getTime());
        holder.setText(R.id.num,"+"+item.getMoney());
    }

    @Override
    protected int getItemViewLayoutId(int position,FBean item) {
        return R.layout.item_jf;
    }
}