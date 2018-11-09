package com.hzxmkuar.sxmaketnew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.hzxmkuar.sxmaketnew.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivityAdapter extends RecyclerView.Adapter<HomeActivityAdapter.HomeActivityViewHolder> {


    private Context context;
    private final LayoutInflater layoutInflater;
    private ArrayList<String> pages = new ArrayList<>();

    public void setPages(ArrayList<String> pages) {
        this.pages = pages;
        notifyDataSetChanged();
    }

    public HomeActivityAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public HomeActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeActivityViewHolder(layoutInflater.inflate(R.layout.item_activity_new, parent, false));
    }

    @Override
    public void onBindViewHolder(HomeActivityViewHolder holder, int position) {
        RoundedImageView imageView = holder.imageView;

        Glide.with(context)
                .load(pages.get(position))
                .into(imageView);

    }

    @Override
    public int getItemCount() {
        return pages.size();
    }

    public class HomeActivityViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView1)
        RoundedImageView imageView;

        public HomeActivityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
