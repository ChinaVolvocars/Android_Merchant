package com.hzxmkuar.sxmaketnew.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.mvp.BaseMvpFragment;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.model.InvoiceElectronic;
import com.common.utils.SPUtils;
import com.google.gson.Gson;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.adapter.InvoiceElectronicAdapter;
import com.hzxmkuar.sxmaketnew.view.dialog.WithdrawDialogFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Subscriber;

import static com.hzxmkuar.sxmaketnew.fragment.InvoiceExpressFragment.WID;

public class InvoiceElectronicFragment extends BaseMvpFragment {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.ll_add)
    LinearLayout llAdd;
    private Unbinder bind;
    private String wId;
    private InvoiceElectronicAdapter adapter;

    public static InvoiceElectronicFragment newInstance(Bundle bundle) {
        InvoiceElectronicFragment fragment = new InvoiceElectronicFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invoice_electronic;
    }

    @Override
    protected void onViewCreated(View view) {
        bind = ButterKnife.bind(this, view);
       /* Bundle bundle = getArguments();
        wId = bundle.getString(WID, "0");*/

        wId = SPUtils.getShareString(WID);
        System.out.println("--11-获取到的值：" + wId);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        adapter = new InvoiceElectronicAdapter(getContext());
        recyclerView.setAdapter(adapter);

        InvoiceElectronic item = new InvoiceElectronic();
        adapter.add(item);

        adapter.setOnItemClickListener(new InvoiceElectronicAdapter.OnDateChangeListener() {
            @Override
            public void onDateChage(int position, List<InvoiceElectronic> list) {
                for (int i = 0; i < list.size(); i++) {
                    String invoice_code = list.get(i).getInvoice_code();
                    String invoice_num = list.get(i).getInvoice_num();
                    if (i == 0 && !TextUtils.isEmpty(invoice_code) && !TextUtils.isEmpty(invoice_num)) {
                        llAdd.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }


    @OnClick(R.id.ll_add)
    public void onAddClicked() {
        List<InvoiceElectronic> list = adapter.getDate();
        for (int i = 0; i < list.size(); i++) {
            String invoice_num = list.get(i).getInvoice_num();
            String invoice_code = list.get(i).getInvoice_code();
            if (TextUtils.isEmpty(invoice_num) || TextUtils.isEmpty(invoice_code)) {
                showToastMsg("请填写完毕再添加");
                return;
            }
        }

        InvoiceElectronic item = new InvoiceElectronic();
        adapter.add(item);
    }


    @OnClick(R.id.tv_confirm)
    public void onConfirmClicked() {
        List<InvoiceElectronic> list = adapter.getDate();
        Gson gson = new Gson();
        String listJson = gson.toJson(list);
        BusinessUserMethods.getInstance().invoiceSubmit(new Subscriber<HttpRespBean>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                showToastMsg(e.getMessage());
            }

            @Override
            public void onNext(HttpRespBean httpRespBean) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("flag", false);
                bundle.putString("invoice", "invoice");
                WithdrawDialogFragment dialogFragment = WithdrawDialogFragment.newInstance(bundle);
                dialogFragment.show(getFragmentManager(), "WithdrawDialogFragment");
            }
        }, wId, 1, listJson);

    }


    @Override
    protected void doLogicFunc() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != bind) bind.unbind();
    }

}
