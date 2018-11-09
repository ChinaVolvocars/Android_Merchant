package com.hzxmkuar.sxmaketnew.view.dialog;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hzxmkuar.sxmaketnew.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogHomeWay extends BottomSheetDialogFragment {

    @BindView(R.id.tv_collection_payment)
    TextView tvCollectionPayment;
    @BindView(R.id.tv_invoice_withdrawal)
    TextView tvInvoiceWithdrawal;
    @BindView(R.id.ll_cancel)
    LinearLayout llCancel;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置背景透明，才能显示出layout中诸如圆角的布局，否则会有白色底（框）
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    public static DialogHomeWay newInstance() {
        DialogHomeWay dialogHomeWay = new DialogHomeWay();
        return dialogHomeWay;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_home_way, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.ll_cancel)
    public void onCancelClicked() {
        Toast.makeText(getContext(), "取消", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.tv_collection_payment)
    public void onCollectionPaymentClicked() {
        Toast.makeText(getContext(), "==", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.tv_invoice_withdrawal)
    public void onInvoiceWithdrawalClicked() {
        Toast.makeText(getContext(), "取--消", Toast.LENGTH_SHORT).show();
    }

}
