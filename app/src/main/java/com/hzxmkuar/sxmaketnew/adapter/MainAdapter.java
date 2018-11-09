package com.hzxmkuar.sxmaketnew.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzxmkuar.sxmaketnew.R;
import com.indicator.LinePageIndicator;
import com.looping.viewpager.LoopViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEWTYPE_BALANCE = 0;
    public static final int VIEWTYPE_FUNCTION = 1;
    public static final int VIEWTYPE_ADV = 2;
    public static final int VIEWTYPE_OPERATING_INCOME = 3;
    public static final int VIEWTYPE_SERVICE = 4;
    public static final int VIEWTYPE_ACTIVITY = 5;

    private Context context;

    public MainAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == VIEWTYPE_BALANCE) {
            return VIEWTYPE_BALANCE;
        } else if (position == VIEWTYPE_FUNCTION) {
            return VIEWTYPE_FUNCTION;
        } else if (position == VIEWTYPE_ADV) {
            return VIEWTYPE_ADV;
        } else if (position == VIEWTYPE_OPERATING_INCOME) {
            return VIEWTYPE_OPERATING_INCOME;
        } else if (position == VIEWTYPE_SERVICE) {
            return VIEWTYPE_SERVICE;
        } else {
            return VIEWTYPE_ACTIVITY;
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case VIEWTYPE_BALANCE:
                return new BalanceViewHolder(inflater.inflate(R.layout.layout_home_balance_new, parent, false));
            case VIEWTYPE_FUNCTION:
                return new FunctionViewHolder(inflater.inflate(R.layout.layout_home_function_new, parent, false));
            case VIEWTYPE_ADV:
                return new AdvViewHolder(inflater.inflate(R.layout.layout_home_adv_new, parent, false));
            case VIEWTYPE_OPERATING_INCOME:
                return new OperatingIncomeViewHolder(inflater.inflate(R.layout.layout_home_operating_income_new, parent, false));
            case VIEWTYPE_SERVICE:
                return new ServiceViewHolder(inflater.inflate(R.layout.layout_home_service_new, parent, false));
            case VIEWTYPE_ACTIVITY:
                return new ActivityViewHolder(inflater.inflate(R.layout.layout_home_activity_new, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof BalanceViewHolder) {

        } else if (viewHolder instanceof FunctionViewHolder) {

        } else if (viewHolder instanceof AdvViewHolder) {
            AdvViewHolder holder = (AdvViewHolder) viewHolder;
            LoopViewPager loopViewPager = holder.loopViewPager;
            LinePageIndicator indicator = holder.indicator;

            HomeAdvAdapter imageAdapter = new HomeAdvAdapter(context);
            ArrayList<String> pages = new ArrayList<>();
            pages.add("http://seopic.699pic.com/photo/50035/0520.jpg_wh1200.jpg");
            pages.add("https://pic3.zhimg.com/80/v2-5faa2ffcac1992a2663c8746abbde9ae_hd.jpg");
            pages.add("https://pic1.zhimg.com/80/v2-78b72fb37fbcd6224940b7f15d76ef64_hd.jpg");
            pages.add("https://pic4.zhimg.com/80/v2-84c93abead7d8744422af35167aeeb2b_hd.jpg");
            imageAdapter.setPages(pages);
            loopViewPager.setAdapter(imageAdapter);
            indicator.setViewPager(loopViewPager);
        } else if (viewHolder instanceof OperatingIncomeViewHolder) {

        } else if (viewHolder instanceof ServiceViewHolder) {
            ServiceViewHolder holder = (ServiceViewHolder) viewHolder;
            RecyclerView recyclerview = holder.serviceRecyclerview;
            recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            recyclerview.setHasFixedSize(true);
            HomeServiceAdapter adapter = new HomeServiceAdapter(context);
            recyclerview.setAdapter(adapter);
            ArrayList<String> pages = new ArrayList<>();
            pages.add("http://seopic.699pic.com/photo/50035/0520.jpg_wh1200.jpg");
            pages.add("https://pic3.zhimg.com/80/v2-5faa2ffcac1992a2663c8746abbde9ae_hd.jpg");
            pages.add("https://pic1.zhimg.com/80/v2-78b72fb37fbcd6224940b7f15d76ef64_hd.jpg");
            pages.add("https://pic4.zhimg.com/80/v2-84c93abead7d8744422af35167aeeb2b_hd.jpg");
            adapter.setPages(pages);


        } else if (viewHolder instanceof ActivityViewHolder) {
            ActivityViewHolder holder = (ActivityViewHolder) viewHolder;
            RecyclerView recyclerview = holder.activityRecyclerview;
            recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            recyclerview.setHasFixedSize(true);

            HomeActivityAdapter adapter = new HomeActivityAdapter(context);
            recyclerview.setAdapter(adapter);
            ArrayList<String> pages = new ArrayList<>();
            pages.add("http://seopic.699pic.com/photo/50035/0520.jpg_wh1200.jpg");
            pages.add("https://pic3.zhimg.com/80/v2-5faa2ffcac1992a2663c8746abbde9ae_hd.jpg");
            pages.add("https://pic1.zhimg.com/80/v2-78b72fb37fbcd6224940b7f15d76ef64_hd.jpg");
            pages.add("https://pic4.zhimg.com/80/v2-84c93abead7d8744422af35167aeeb2b_hd.jpg");
            adapter.setPages(pages);

        }
    }


    public class BalanceViewHolder extends RecyclerView.ViewHolder {

        public BalanceViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class FunctionViewHolder extends RecyclerView.ViewHolder {

        public FunctionViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class AdvViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.LoopViewPager)
        LoopViewPager loopViewPager;
        @BindView(R.id.indicator)
        LinePageIndicator indicator;

        public AdvViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.service_recyclerview)
        RecyclerView serviceRecyclerview;

        public ServiceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class OperatingIncomeViewHolder extends RecyclerView.ViewHolder {

        public OperatingIncomeViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class ActivityViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.activity_recyclerview)
        RecyclerView activityRecyclerview;

        public ActivityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
