package com.hzxmkuar.sxmaketnew.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.common.event.SpConstants;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.widget.recyclerview.refresh.adapter.CommonAdapter;
import com.common.widget.recyclerview.refresh.adapter.ViewHolder;
import com.common.widget.toast.ToastManager;
import com.hzxmkuar.sxmaketnew.R;
import com.common.retrofit.entity.result.ShopShowsEntity;
import com.hzxmkuar.sxmaketnew.home.AddNewActiveActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 商家活动列表展示
 */
public class ShopShowsRvAdapter extends CommonAdapter<ShopShowsEntity.ShopShowsItemEntity> {
    private Context mContext;
    private List<ShopShowsEntity.ShopShowsItemEntity> dataList;
    public ShopShowsRvAdapter(Context context, List<ShopShowsEntity.ShopShowsItemEntity> data) {
        super(context, data);
        this.mContext = context;
        this.dataList = data;
    }

    @Override
    protected void convert(ViewHolder holder, final ShopShowsEntity.ShopShowsItemEntity item, final int position) {
//        holder.setText(R.id.time, item.getCreate_time());
//        holder.setText(R.id.num, "+" + item.getWithdraw_money());
        holder.setText(R.id.tv_activie_desc, item.getActivity_desc());
        final TextView tvStatus = holder.getView(R.id.tv_activie_statue);
        TextView tvDelete = holder.getView(R.id.tv_delete_active);

        if ("1".equals(item.getCheck_status())) {
//            holder.setText(R.id.tv_activie_statue,"审核中");
            tvStatus.setText("审核中");
            tvStatus.setBackgroundColor(mContext.getResources().getColor(R.color.color_aaaaaa));
        } else if ("2".equals(item.getCheck_status())) {
            tvStatus.setText("审核通过");
        } else if ("3".equals(item.getCheck_status())) {
            tvStatus.setText("审核失败");
            tvStatus.setBackgroundColor(mContext.getResources().getColor(R.color.color_aaaaaa));
        }
        tvStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddNewActiveActivity.class);
                intent.putExtra(SpConstants.INTENT_TYPE,item.getCheck_status());
                Bundle bundle = new Bundle();
                bundle.putString("desc",item.getActivity_desc());
                bundle.putString("info",item.getActivity_info());
                intent.putExtras(bundle);
                mContext.startActivity(intent);

            }
        });
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletImte(position,item);
            }
        });

    }

    /**
     *  删除条目
     * @param position
     */
    private void deletImte(final int position, ShopShowsEntity.ShopShowsItemEntity item) {
        CommonSubscriber<Object> subscriber = new CommonSubscriber<>(new SubscriberListener() {

            @Override
            public void onNext(Object o) {
//                statusContent();
                dataList.remove(position);
                showToastMsg("删除活动成功");
                notifyDataSetChanged();
            }

            @Override
            public void onError(String e, int code) {
//                statusContent();
                showToastMsg(e);
            }
        });
        List<String> addNewParama = new ArrayList<>();
        addNewParama.add("time");
        addNewParama.add("aid");
        BusinessUserMethods.getInstance().deletShopActive(subscriber,addNewParama,item.getId());
        rxManager.add(subscriber);
    }

    @Override
    protected int getItemViewLayoutId(int position, ShopShowsEntity.ShopShowsItemEntity item) {
        return R.layout.item_shop_shows;
    }
}