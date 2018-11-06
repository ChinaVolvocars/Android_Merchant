package com.hzxmkuar.sxmaketnew.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.common.retrofit.entity.result.ActiveListEntity;
import com.common.utils.EmptyUtils;
import com.common.widget.imageview.image.ImageLoaderUtils;
import com.common.widget.recyclerview.refresh.adapter.CommonAdapter;
import com.common.widget.recyclerview.refresh.adapter.ViewHolder;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.base.BaseUrlActivity;

import java.util.List;

/**
 *  商家活动列表
 */
public class ActiveListAdapter extends CommonAdapter<ActiveListEntity.ActiveListItemEntity> {
    private Context mContext;

    public ActiveListAdapter(Context context, List<ActiveListEntity.ActiveListItemEntity> data) {
        super(context, data);
        this.mContext = context;
    }

    @Override
    protected void convert(ViewHolder holder, final ActiveListEntity.ActiveListItemEntity item, int position) {

        holder.setText(R.id.tv_active_title, item.getTitle());
//        holder.setText(R.id.iv_item_icon, item.getTitle());
        ImageLoaderUtils.displaySmallPhoto((ImageView) holder.getParentView().findViewById(R.id.iv_item_icon), item.getPic());
        if ("1".equals(item.getType())){
        // 未开始
            holder.setText(R.id.tv_active_end_time,"距开始 还有"+item.getTime()+"天");
            holder.setTextColor(R.id.tv_active_end_time,mContext.getResources().getColor(R.color.color_30a4ee));
            ImageLoaderUtils.displaySmallPhoto((ImageView) holder.getParentView().findViewById(R.id.iv_active_type), R.mipmap.active_not_start_icon);
            ImageLoaderUtils.displaySmallPhoto((ImageView) holder.getParentView().findViewById(R.id.iv_time_icon), R.mipmap.time_not_start_icon);
        }else if ("2".equals(item.getType())){
            // 已开始
            holder.setText(R.id.tv_active_end_time,"距结束 还有"+item.getTime()+"天");
            holder.setTextColor(R.id.tv_active_end_time,mContext.getResources().getColor(R.color.color_b59b72));//R.color.color_b59b72
            ImageLoaderUtils.displaySmallPhoto((ImageView) holder.getParentView().findViewById(R.id.iv_active_type), R.mipmap.active_hot_icon);
            ImageLoaderUtils.displaySmallPhoto((ImageView) holder.getParentView().findViewById(R.id.iv_time_icon), R.mipmap.time_start_icon);
        }
        if (!EmptyUtils.isEmpty(item.getUrl())){
            holder.getParentView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!EmptyUtils.isEmpty(item.getUrl())){
                        Intent urlIntent = new Intent(mContext, BaseUrlActivity.class);
                        urlIntent.putExtra(BaseUrlActivity.MAIN_URL, item.getUrl());
                        mContext.startActivity(urlIntent);
                    }
                }
            });
        }
    }

    @Override
    protected int getItemViewLayoutId(int position, ActiveListEntity.ActiveListItemEntity item) {
        return R.layout.item_active_list;
    }
}