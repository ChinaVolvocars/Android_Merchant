package com.hzxmkuar.sxmaketnew.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.common.retrofit.model.ServiceFunction;
import com.hzxmkuar.sxmaketnew.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeServiceAdapter extends RecyclerView.Adapter<HomeServiceAdapter.HomeServiceViewHolder> {


    private Context context;
    private final LayoutInflater layoutInflater;
    private ArrayList<ServiceFunction> pages = new ArrayList<>();

    public void setPages(ArrayList<ServiceFunction> pages) {
        this.pages = pages;
        notifyDataSetChanged();
    }

    public HomeServiceAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public HomeServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeServiceViewHolder(layoutInflater.inflate(R.layout.item_service_new, parent, false));
    }

    @Override
    public void onBindViewHolder(HomeServiceViewHolder holder, final int position) {
        RoundedImageView imageView = holder.imageView;

        Glide.with(context)
                .load(pages.get(position).getPicture())
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != listener) {
                    listener.onItemClick(view, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }

    public class HomeServiceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView1)
        RoundedImageView imageView;

        public HomeServiceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}
