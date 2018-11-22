package com.hzxmkuar.sxmaketnew.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.retrofit.model.ActivityList;
import com.common.retrofit.model.Home;
import com.common.retrofit.model.ServiceFunction;
import com.common.retrofit.model.ShopBanner;
import com.common.utils.SpannableStringUtils;
import com.hzxmkuar.sxmaketnew.R;
import com.indicator.LinePageIndicator;
import com.looping.viewpager.LoopViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String Conversion = "Conversion";
    public static final String CollectionCode = "CollectionCode";
    public static final String ScanCheck = "ScanCheck";
    public static final String Bank = "Bank";
    public static final String WithdrawalApplication = "WithdrawalApplication";
    public static final String WithdrawalAccounts = "WithdrawalAccounts";
    public static final String RevenueStatistics = "RevenueStatistics";
    public static final String InvoiceInformation = "InvoiceInformation";
    public static final String ConsumptionAuthority = "ConsumptionAuthority";
    public static final String TodayRevenue = "TodaysRevenue";

    public static final int VIEWTYPE_BALANCE = 0;
    public static final int VIEWTYPE_FUNCTION = 1;
    public static final int VIEWTYPE_ADV = 2;
    public static final int VIEWTYPE_OPERATING_INCOME = 3;
    public static final int VIEWTYPE_SERVICE = 4;
    public static final int VIEWTYPE_ACTIVITY = 5;

    private boolean flag = true;
    private Home home;
    private Context context;

    public MainAdapter(Context context) {
        this.context = context;
    }

    public void setData(Home home) {
        this.home = home;
        notifyDataSetChanged();
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
        return null == home ? 0 : 6;
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
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof BalanceViewHolder) {
            final BalanceViewHolder holder = (BalanceViewHolder) viewHolder;

            final String money = home.getMoney();
            final String xindou = home.getXindou();
            holder.tvBalance.setText(money);
            holder.tvConversionContent.setText("账户余额(元)");
            holder.tvConversion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (flag) {
                        flag = false;
                        holder.tvBalance.setText(xindou);
                        holder.tvConversionContent.setText("账户余额(豆)");
                    } else {
                        flag = true;
                        holder.tvBalance.setText(money);
                        holder.tvConversionContent.setText("账户余额(元)");
                    }

                    if (listener != null) listener.onOtherItemClick(view, Conversion);
                }
            });

            holder.tvCollectionCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) listener.onOtherItemClick(view, CollectionCode);
                }
            });
            holder.tvScanCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) listener.onOtherItemClick(view, ScanCheck);
                }
            });
            holder.tvBank.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) listener.onOtherItemClick(view, Bank);
                }
            });
        } else if (viewHolder instanceof FunctionViewHolder) {
            FunctionViewHolder holder = (FunctionViewHolder) viewHolder;
            //提现申请
            holder.tvWithdrawalApplication.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) listener.onOtherItemClick(view, WithdrawalApplication);
                }
            });
            //提现账款
            holder.tvWithdrawalAccounts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) listener.onOtherItemClick(view, WithdrawalAccounts);
                }
            });
            //营收统计
            holder.tvRevenueStatistics.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) listener.onOtherItemClick(view, RevenueStatistics);
                }
            });
            //发票信息
            holder.tvInvoiceInformation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) listener.onOtherItemClick(view, InvoiceInformation);
                }
            });
            //消费权限
            holder.tvConsumptionAuthority.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) listener.onOtherItemClick(view, ConsumptionAuthority);
                }
            });

        } else if (viewHolder instanceof AdvViewHolder) {
            AdvViewHolder holder = (AdvViewHolder) viewHolder;
            LoopViewPager loopViewPager = holder.loopViewPager;
            LinePageIndicator indicator = holder.indicator;
            HomeAdvAdapter adapter = new HomeAdvAdapter(context);
            ArrayList<ShopBanner> pages = new ArrayList<>();
            pages.addAll(home.getShop_banner());
            adapter.setPages(pages);
            loopViewPager.setAdapter(adapter);
            indicator.setViewPager(loopViewPager);
            loopViewPager.setBoundaryCaching(false);

            adapter.setOnItemClickListener(new HomeAdvAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Log.e("广告点击事件", "onItemClick: " + position);
                    if (listener != null) listener.onViewPagerItemClick(view, position);

                }
            });

        } else if (viewHolder instanceof OperatingIncomeViewHolder) {
            OperatingIncomeViewHolder holder = (OperatingIncomeViewHolder) viewHolder;
            String pay_xindou = home.getPay_xindou();
            String pay_money = home.getPay_money();
            String accumulative = home.getAccumulative();//累计
            //00 .  00
            //30 23
            //#fe6500
            SpannableStringBuilder stringAccumulative = new SpannableStringBuilder();
            stringAccumulative.append(accumulative);
            stringAccumulative.append(" 元");
            RelativeSizeSpan sizeSpan01 = new RelativeSizeSpan(1.0f);
            RelativeSizeSpan sizeSpan02 = new RelativeSizeSpan(0.767f);
            stringAccumulative.setSpan(sizeSpan01, 0, stringAccumulative.length() - 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            stringAccumulative.setSpan(sizeSpan02, stringAccumulative.length() - 4, stringAccumulative.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            holder.tvSettlementAccumulation.setText(stringAccumulative);

            //21 16 9
            SpannableStringBuilder stringXindou = new SpannableStringBuilder();
            stringXindou.append(pay_xindou);
            stringXindou.append(" 元");
            RelativeSizeSpan xindousize01 = new RelativeSizeSpan(1.0f);
            RelativeSizeSpan xindousize02 = new RelativeSizeSpan(0.762f);
            RelativeSizeSpan xindousize03 = new RelativeSizeSpan(0.4286f);
            stringXindou.setSpan(xindousize01, 0, stringXindou.length() - 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            stringXindou.setSpan(xindousize02, stringXindou.length() - 4, stringXindou.length() - 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            stringXindou.setSpan(xindousize03, stringXindou.length() - 2, stringXindou.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            holder.tvXindouSettlement.setText(stringXindou);

            SpannableStringBuilder stringCash = new SpannableStringBuilder();
            stringCash.append(pay_money);
            stringCash.append(" 元");
            RelativeSizeSpan cashsize01 = new RelativeSizeSpan(1.0f);
            RelativeSizeSpan cashsize02 = new RelativeSizeSpan(0.762f);
            RelativeSizeSpan cashsize03 = new RelativeSizeSpan(0.4286f);
            stringCash.setSpan(cashsize01, 0, stringCash.length() - 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            stringCash.setSpan(cashsize02, stringCash.length() - 4, stringCash.length() - 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            stringCash.setSpan(cashsize03, stringCash.length() - 2, stringCash.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            holder.tvCashSettlement.setText(stringCash);

            holder.tvTodayRevenue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) listener.onOtherItemClick(view, TodayRevenue);
                }
            });

        } else if (viewHolder instanceof ServiceViewHolder) {
            ServiceViewHolder holder = (ServiceViewHolder) viewHolder;
            RecyclerView recyclerview = holder.serviceRecyclerview;
            recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            recyclerview.setHasFixedSize(true);
            HomeServiceAdapter adapter = new HomeServiceAdapter(context);
            recyclerview.setAdapter(adapter);
            ArrayList<ServiceFunction> pages = new ArrayList<>();
            List<ServiceFunction> serviceFunction = home.getService_function();
            pages.addAll(serviceFunction);
            adapter.setPages(pages);

            adapter.setOnItemClickListener(new HomeServiceAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Log.e("服务点击事件", "onItemClick: " + position);
                    if (listener != null) listener.onServiceItemClick(view, position);
                }
            });

        } else if (viewHolder instanceof ActivityViewHolder) {
            ActivityViewHolder holder = (ActivityViewHolder) viewHolder;
            RecyclerView recyclerview = holder.activityRecyclerview;
            recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            recyclerview.setHasFixedSize(true);

            HomeActivityAdapter adapter = new HomeActivityAdapter(context);
            recyclerview.setAdapter(adapter);
            ArrayList<ActivityList> pages = new ArrayList<>();
            pages.addAll(home.getList());
            adapter.setPages(pages);

            adapter.setOnItemClickListener(new HomeActivityAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Log.e("活动点击事件", "onItemClick: " + position);
                    if (listener != null) listener.onActivityItemClick(view, position);
                }
            });

        }
    }

    public class BalanceViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_balance)
        TextView tvBalance;
        @BindView(R.id.tv_conversion)
        TextView tvConversion;
        @BindView(R.id.tv_conversion_content)
        TextView tvConversionContent;
        @BindView(R.id.tv_collection_code)
        TextView tvCollectionCode;
        @BindView(R.id.tv_scan_check)
        TextView tvScanCheck;
        @BindView(R.id.tv_bank)
        TextView tvBank;

        public BalanceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public class FunctionViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_withdrawal_application)
        TextView tvWithdrawalApplication;
        @BindView(R.id.tv_withdrawal_accounts)
        TextView tvWithdrawalAccounts;
        @BindView(R.id.tv_revenue_statistics)
        TextView tvRevenueStatistics;
        @BindView(R.id.tv_invoice_information)
        TextView tvInvoiceInformation;
        @BindView(R.id.tv_consumption_authority)
        TextView tvConsumptionAuthority;

        public FunctionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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
        @BindView(R.id.tv_settlement_accumulation)
        TextView tvSettlementAccumulation;
        @BindView(R.id.tv_xindou_settlement)
        TextView tvXindouSettlement;
        @BindView(R.id.tv_cash_settlement)
        TextView tvCashSettlement;
        @BindView(R.id.tv_today_revenue)
        TextView tvTodayRevenue;

        public OperatingIncomeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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


    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onViewPagerItemClick(View view, int position);

        void onServiceItemClick(View view, int position);

        void onActivityItemClick(View view, int position);

        void onOtherItemClick(View view, String tag);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}
