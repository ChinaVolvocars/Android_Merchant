package com.hzxmkuar.sxmaketnew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.retrofit.entity.result.CheckRecordEntity;
import com.common.retrofit.entity.result.ConsumeRightsEntity;
import com.common.widget.recyclerview.refresh.adapter.CommonAdapter;
import com.common.widget.recyclerview.refresh.adapter.ViewHolder;
import com.hzxmkuar.sxmaketnew.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 消费记录适配器
 * Created by jc on 2019/1/4.
 */
public class ConsumeRecordAdapter extends CommonAdapter<ConsumeRightsEntity.ConsumeRecordItemEntity> {
    private static final String TAG = "ConsumeRecordAdapter";

    public ConsumeRecordAdapter(Context context) {
        super(context);
    }

    @Override
    protected void convert(ViewHolder holder, ConsumeRightsEntity.ConsumeRecordItemEntity item, int position) {
        Log.i(TAG, "convert: 条目：        " + item.toString());
        holder.setText(R.id.tv_shop_name, item.getName());
        holder.setText(R.id.tv_expend_date, item.getCreate_time());
        if ("1".equals(item.getType())) {  // 开鑫传单
            holder.setBackgroundResource(R.id.iv_expend_type, R.mipmap.kaixin_leaflet_expend_icon);
            holder.setText(R.id.tv_expend_type, "消费：" + item.getXindou() + "鑫豆");
        } else if ("2".equals(item.getType())) {  // 线下消费
            holder.setBackgroundResource(R.id.iv_expend_type, R.mipmap.expend_underline_icon);
//            holder.setText(R.id.tv_expend_type, "消费：" + item.getXindou() + "鑫豆 " + item.getPay_money() + "现金");
            boolean isNotUseXindou = 0 == Double.parseDouble(item.getXindou());  // 是否未使用过 鑫豆
            boolean isNotPayMoney = 0 == Double.parseDouble(item.getPay_money()); // 是否未使用过 现金
            if (!isNotPayMoney && isNotUseXindou) {   // 现金和多豆都使用过
                holder.setText(R.id.tv_expend_type, "消费：" + item.getXindou() + "鑫豆 " + item.getPay_money() + "现金");
            } else {
                if (isNotUseXindou) {
                    holder.setText(R.id.tv_expend_type, "消费：" + item.getPay_money() + "现金");
                }
                if (isNotPayMoney) {
                    holder.setText(R.id.tv_expend_type, "消费：" + item.getXindou() + "鑫豆");
                }
            }
        } else if ("3".equals(item.getType())) { // 线上消费  1为0元秒杀  2为折上再返
            holder.setBackgroundResource(R.id.iv_expend_type, R.mipmap.expend_online_icon);
            if ("1".equals(item.getBuy_type())) {  // 0元秒杀
                holder.setText(R.id.tv_expend_type, "消费：" + item.getTotal_money() + "现金");
            } else if ("2".equals(item.getBuy_type())) {// 折上再返
                boolean isNotUseXindou = 0 == Double.parseDouble(item.getXindou());
                boolean isNotFulidou = 0 == Double.parseDouble(item.getFulidou());
                boolean isNotUseCash = 0 == Double.parseDouble(item.getTotal_money());
                if (!isNotUseXindou && !isNotFulidou &&!isNotUseCash){
                    holder.setText(R.id.tv_expend_type, "消费：" + item.getXindou() + "鑫豆 " + item.getFulidou() + "福豆 " + item.getTotal_money() + "现金");
                }else {
                    if (!isNotUseXindou && !isNotFulidou && isNotUseCash){
                        holder.setText(R.id.tv_expend_type, "消费：" + item.getXindou() + "鑫豆 " + item.getFulidou() + "福豆 " );
                    }else if (!isNotUseXindou && isNotFulidou && !isNotUseCash){
                        holder.setText(R.id.tv_expend_type, "消费：" + item.getXindou() + "鑫豆 " + item.getTotal_money() + "现金");
                    }else if (isNotUseXindou && !isNotFulidou && !isNotUseCash){
                        holder.setText(R.id.tv_expend_type, "消费："  + item.getFulidou() + "福豆 " + item.getTotal_money() + "现金");
                    }else if (!isNotUseXindou && isNotFulidou && isNotUseCash){
                        holder.setText(R.id.tv_expend_type, "消费：" + item.getXindou() + "鑫豆 " );
                    }else if (isNotUseXindou && !isNotFulidou && isNotUseCash){
                        holder.setText(R.id.tv_expend_type, "消费："  + item.getFulidou() + "福豆 ");
                    }else if (isNotUseXindou && isNotFulidou && !isNotUseCash){
                    holder.setText(R.id.tv_expend_type, "消费："  + item.getTotal_money() + "现金");
                    }
                }
            }
        }
    }

    @Override
    protected int getItemViewLayoutId(int position, ConsumeRightsEntity.ConsumeRecordItemEntity item) {
        return R.layout.item_consume_record;
    }
}
