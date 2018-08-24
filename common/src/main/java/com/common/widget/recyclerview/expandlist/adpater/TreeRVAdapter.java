package com.common.widget.recyclerview.expandlist.adpater;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.widget.recyclerview.expandlist.viewholder.TreeItem;
import com.common.widget.recyclerview.expandlist.viewholder.TreeItemManager;
import com.common.widget.recyclerview.expandlist.viewholder.TreeParentItem;
import com.common.widget.recyclerview.refresh.adapter.CommonAdapter;
import com.common.widget.recyclerview.refresh.adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TreeRVAdapter<T extends TreeItem> extends CommonAdapter<T>
        implements TreeItemManager {
    protected TreeRecyclerViewType type;

    /** 存储可见的items */
    protected List<T> mShowDatas;

    /**
     * @param context 上下文
     * @param datas   条目数据
     */
    public TreeRVAdapter(Context context, List<T> datas, TreeRecyclerViewType type) {
        super(context, datas);
        this.type = type;
        datas = datas == null ? new ArrayList<T>() : datas;
        if (type == TreeRecyclerViewType.SHOW_ALL) {
            mShowDatas = new ArrayList<>();
            for (int i = 0; i < datas.size(); i++) {
                T t = datas.get(i);
                mShowDatas.add(t);
                if (t instanceof TreeParentItem) {
                    List allChilds = ((TreeParentItem) t).getChilds(type);
                    mShowDatas.addAll(allChilds);
                }
            }
        } else {
            mShowDatas = datas;
        }
    }

    /**
     * 相应ListView的点击事件 展开或关闭某节点
     * @param holder 触发的条目
     */
    private void expandOrCollapse(ViewHolder holder) {
        int position = holder.getLayoutPosition() - 1;
        TreeItem treeItem = mShowDatas.get(position);
        if (treeItem instanceof TreeParentItem && ((TreeParentItem) treeItem).isCanChangeExpand()) {
            TreeParentItem treeParentItem = (TreeParentItem) treeItem;
            boolean expand = treeParentItem.isExpand();
            List allChilds = treeParentItem.getChilds(type);
            if (expand) {
                mShowDatas.removeAll(allChilds);
                treeParentItem.onCollapse();
                treeParentItem.setExpand(false);
            } else {
                mShowDatas.addAll(position + 1, allChilds);
                treeParentItem.onExpand();
                treeParentItem.setExpand(true);
            }
            notifyDataSetChanged();
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    TreeItem treeItem = mShowDatas.get(position);
                    if (treeItem.getSpanSize() == 0) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return treeItem.getSpanSize();
                }
            });
        }
    }

    @Override
    protected void convert(final ViewHolder holder, T item, int position) {
        final TreeItem treeItem = mShowDatas.get(position);
        treeItem.setTreeItemManager(this);
        treeItem.onBindViewHolder(holder);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type != TreeRecyclerViewType.SHOW_ALL) {
                    expandOrCollapse(holder);
                }
            }
        });
    }

    @Override
    protected int getItemViewLayoutId(int position, T item) {
        return 0;
    }

    @Override
    public int getNormalItemViewType(int position) {
        return mShowDatas.get(position).getLayoutId();
    }

    @Override
    public ViewHolder createNormalViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(viewType, parent, false);
        ViewHolder holder = new ViewHolder(itemView, mContext);
        return holder;
    }

    public List<T> getShowDatas() {
        return mShowDatas;
    }
}