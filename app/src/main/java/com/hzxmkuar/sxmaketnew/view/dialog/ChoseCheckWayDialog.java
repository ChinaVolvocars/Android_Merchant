package com.hzxmkuar.sxmaketnew.view.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.home.CheckTicketsActivity;
import com.hzxmkuar.sxmaketnew.home.ScanActivity;
import com.hzxmkuar.sxmaketnew.newversion.NewMainActivity;
import com.hzxmkuar.sxmaketnew.newversion.WithdrawalActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.hzxmkuar.sxmaketnew.newversion.NewMainActivity.KEY_WEEK;

public class ChoseCheckWayDialog extends BottomSheetDialogFragment implements View.OnClickListener {
    private static ChoseCheckWayDialog choseCheckWayDialog;
    private TextView tv_scan_check;
    private TextView tv_manual_operation_check;
    LinearLayout ll_cancel;

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

    public static ChoseCheckWayDialog newInstance() {
        choseCheckWayDialog = new ChoseCheckWayDialog();
//        choseCheckWayDialog.setArguments(bundle);
        return choseCheckWayDialog;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_chose_check_way, container, false);
        tv_scan_check = (TextView) view.findViewById(R.id.tv_scan_check);
        tv_manual_operation_check = (TextView) view.findViewById(R.id.tv_manual_operation_check);
        ll_cancel = (LinearLayout) view.findViewById(R.id.ll_cancel);
        tv_scan_check.setOnClickListener(this);
        tv_manual_operation_check.setOnClickListener(this);
        ll_cancel.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() ==  tv_scan_check.getId()){
            Intent intent = new Intent(getContext(), ScanActivity.class);
            startActivity(intent);
        }else if (v.getId() == tv_manual_operation_check.getId()){
            Intent intent = new Intent(getContext(), CheckTicketsActivity.class);
            startActivity(intent);
        }
        dismiss();
    }


}
