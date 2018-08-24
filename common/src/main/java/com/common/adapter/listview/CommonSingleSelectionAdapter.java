package com.common.adapter.listview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 基类适配器 单选适配器
 */
//用法
//SearchOnlyClassAdapter 继承 CommonSingleSelectionAdapter
//searchOnlyClassAdapter = new SearchOnlyClassAdapter(getContext(), list, R.layout.item_searchfragment_class);
//        searchOnlyClassAdapter.setListener(this);
//        recyclerView.setAdapter(searchOnlyClassAdapter);
//        recyclerView.setOnItemClickListener(searchOnlyClassAdapter);
public abstract class CommonSingleSelectionAdapter<T> extends BaseAdapter implements OnItemClickListener {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;
    private onItemClick itmeClick;
    private int mSelection = 0;

    public CommonSingleSelectionAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    public CommonSingleSelectionAdapter(Context context, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mItemLayoutId = itemLayoutId;
        this.mDatas = new ArrayList<>();
    }

    /**
     * 刷新数据
     *
     * @param datas
     */
    public void refreshDatas(List<T> datas) {
        mDatas.clear();
        if (datas != null && !datas.isEmpty()) {
            mDatas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clearDatas() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param datas
     */
    public void addMoreDatas(List<T> datas) {
        if (datas != null && !datas.isEmpty()) {
            mDatas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }


    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = getViewHolder(position, convertView,
                parent);
        convert(viewHolder, getItem(position), position);
        if (position == mSelection) {
            onChoicePosition(viewHolder, getItem(position));
        } else {
            onOtherPosition(viewHolder, getItem(position));
        }
        return viewHolder.getConvertView();
    }


    public void convert(ViewHolder helper, T item, int position) {
        convert(helper, item);
    }

    public abstract void convert(ViewHolder helper, T item);

    public void addAll(Collection<? extends T> list) {
        mDatas.clear();
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    public void onItemClick(onItemClick itmeClick) {
        this.itmeClick = itmeClick;
    }


    private ViewHolder getViewHolder(int position, View convertView,
                                     ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position);
    }

    //选中的条目
    public abstract void onChoicePosition(ViewHolder helper, T item);

    //未选中的条目
    public abstract void onOtherPosition(ViewHolder helper, T item);

    public List<T> getList() {
        return mDatas;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mSelection = i;
        notifyDataSetChanged();
        Log.d("onItemClick", "onItemClick: " + i);
        if (itmeClick != null)
            itmeClick.onItemClick(adapterView, view, i, l);
    }

    public interface onItemClick {
        void onItemClick(AdapterView<?> adapterView, View view, int i, long l);
    }

}