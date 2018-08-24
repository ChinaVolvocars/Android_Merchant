package com.live.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import com.example.live.R;

/**
 * Created by Administrator on 2016/5/17.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder   implements View.OnClickListener {

    private SparseArray<View> viewCache;
    private MyItemClickListener mListener;
    private final ImageView rv_img;

    public RecyclerViewHolder(View itemView) {
        super(itemView);

        viewCache = new SparseArray<>();
        rv_img = (ImageView) itemView.findViewById(R.id.rv_img);
        rv_img.setOnClickListener(this);
    }

    public View getView(int resId) {
        View view = null;
        if (viewCache.indexOfKey(resId) < 0) {
            view = itemView.findViewById(resId);
            viewCache.put(resId, view);
        } else {
            view = viewCache.get(resId);
        }
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    public interface MyItemClickListener {
        void onItemClick(View view, int postion);
    }
}

