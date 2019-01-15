package com.hzxmkuar.sxmaketnew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.common.retrofit.model.InvoiceElectronic;
import com.hzxmkuar.sxmaketnew.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;


public class InvoiceElectronicAdapter extends RecyclerView.Adapter<InvoiceElectronicAdapter.InvoiceElectronicViewHolder> {


    private List<InvoiceElectronic> list = new ArrayList<>();

    private Context context;

    public InvoiceElectronicAdapter(Context context) {
        this.context = context;
    }

    public void add(InvoiceElectronic item) {
        list.add(item);
        notifyDataSetChanged();
    }


    @Override
    public InvoiceElectronicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InvoiceElectronicViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_invoice_electronic, parent, false));
    }

    @Override
    public void onBindViewHolder(InvoiceElectronicViewHolder holder, int position) {
        InvoiceElectronic invoiceElectronic = list.get(position);
        holder.etNum.setText(invoiceElectronic.getInvoice_num());
        holder.etCode.setText(invoiceElectronic.getInvoice_code());

    }

    @Override
    public int getItemCount() {
        return null == list ? 0 : list.size();
    }


    public class InvoiceElectronicViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.et_code)
        EditText etCode;
        @BindView(R.id.et_num)
        EditText etNum;

        public InvoiceElectronicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnTextChanged(R.id.et_code)
        public void onCodeChanged(CharSequence code) {
            list.get(getLayoutPosition()).setInvoice_code(code.toString());
            System.out.println("==========" + list.get(getLayoutPosition()).getInvoice_code());

            if (listener != null) {
                listener.onDateChage(getLayoutPosition(), list);
            }
        }

        @OnTextChanged(R.id.et_num)
        public void onNumChanged(CharSequence num) {
            list.get(getLayoutPosition()).setInvoice_num(num.toString());
            if (listener != null) {
                listener.onDateChage(getLayoutPosition(), list);
            }
        }
    }

    public List<InvoiceElectronic> getDate() {
        return list;
    }

    private OnDateChangeListener listener;

    public interface OnDateChangeListener {
        void onDateChage(int position, List<InvoiceElectronic> list);
    }

    public void setOnItemClickListener(OnDateChangeListener listener) {
        this.listener = listener;
    }

}
