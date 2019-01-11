package com.hzxmkuar.sxmaketnew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.retrofit.entity.result.RelationShipListItemEntity;
import com.common.widget.recyclerview.refresh.adapter.CommonAdapter;
import com.common.widget.recyclerview.refresh.adapter.ViewHolder;
import com.hzxmkuar.sxmaketnew.R;

/**
 *  关联会员/关联商家列表适配
 * Created by jc on 2019/1/4.
 */
public class RelationShipAccountAdapter extends CommonAdapter<RelationShipListItemEntity>{
    private Context mContext;
    /**
     *   关联类型  <br/>
     *   1  为关联用户    <br/>
     *   2  为关联商家    <br/>
     */
    private int relationType = 2;

    public void setRelationType(int relationType) {
        this.relationType = relationType;
    }

    public RelationShipAccountAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void convert(ViewHolder holder, RelationShipListItemEntity item, int position) {
        if (0 == position){
            holder.setBackgroundResource(R.id.iv_small_cion,R.mipmap.relation_ship01_con);
        }else if (1 == position){
            holder.setBackgroundResource(R.id.iv_small_cion,R.mipmap.relation_ship02_icon);
        }else if (2 == position){
            holder.setBackgroundResource(R.id.iv_small_cion,R.mipmap.relation_ship03_icon);
        }else {
            holder.setVisible(R.id.iv_small_cion,false);
        }
        if (1 == relationType){
            holder.setText(R.id.tv_name_desc,item.getNickname());
            holder.setImageAvatar(R.id.iv_user_head_icon,item.getAvatar());
            holder.setText(R.id.tv_desc01,"他的个人消费为我赚取");
        }else if (2 == relationType){
            holder.setText(R.id.tv_name_desc,item.getShop_name());
            holder.setImageAvatar(R.id.iv_user_head_icon,item.getFace());
            holder.setText(R.id.tv_desc01,"他的门店消费为我赚取");
        }
        holder.setText(R.id.tv_earn,item.getNum());
        holder.setText(R.id.tv_share_earn,item.getFx_num());
        holder.setText(R.id.tv_share_conut,"分享"+item.getFx_count()+"人");

    }

    @Override
    protected int getItemViewLayoutId(int position, RelationShipListItemEntity item) {
        return R.layout.item_relationship_account;
    }

}
