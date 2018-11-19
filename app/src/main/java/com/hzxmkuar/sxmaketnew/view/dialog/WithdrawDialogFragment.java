package com.hzxmkuar.sxmaketnew.view.dialog;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.hzxmkuar.sxmaketnew.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class WithdrawDialogFragment extends DialogFragment {


    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.tv_content)
    TextView tvContent;
    Unbinder unbinder;

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.9), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        return dialog;
    }

    public static WithdrawDialogFragment newInstance(Bundle bundle) {
        WithdrawDialogFragment dialogHomeWay = new WithdrawDialogFragment();
        dialogHomeWay.setArguments(bundle);
        return dialogHomeWay;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_withdraw_new, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCancelable(false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        boolean flag = bundle.getBoolean("flag", true);
        ivImage.setImageResource(flag ? R.mipmap.ic_withdraw_yellow : R.mipmap.ic_withdraw_blue);
        tvConfirm.setBackgroundResource(flag ? R.drawable.shape_dialog_withdraw : R.drawable.shape_dialog_withdraw_blue);
        String invoice = bundle.getString("invoice", "");
        if (invoice.equals("invoice")) {
            tvContent.setText("发票审核中 • • •");
        } else {
            tvContent.setText("财务对账中 • • •");
        }

    }

    @OnClick(R.id.tv_confirm)
    public void onViewClicked() {
        dismiss();
        getActivity().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
