package com.hzxmkuar.sxmaketnew.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.common.mvp.BaseMvpFragment;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.resultImpl.HttpRespBean;
import com.common.retrofit.methods.BusinessUserMethods;
import com.hzxmkuar.sxmaketnew.R;
import com.hzxmkuar.sxmaketnew.view.dialog.WithdrawDialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;
import rx.Subscriber;

public class InvoiceExpressFragment extends BaseMvpFragment {
    public static final String WID = "WID";

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_express_name)
    EditText etExpressName;
    @BindView(R.id.et_express_num)
    EditText etExpressNum;
    @BindView(R.id.et_other)
    EditText etOther;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    Unbinder unbinder;
    private Unbinder bind;
    private String wId;


    @OnTextChanged(R.id.et_name)
    public void onNameChanged(CharSequence name) {
        etName.setBackgroundResource(TextUtils.isEmpty(name) ? R.drawable.shape_rectangle_stroke_blue : R.drawable.shape_rectangle_normal_blue);
    }

    @OnTextChanged(R.id.et_phone)
    public void onPhoneChanged(CharSequence phone) {
        etPhone.setBackgroundResource(TextUtils.isEmpty(phone) ? R.drawable.shape_rectangle_stroke_blue : R.drawable.shape_rectangle_normal_blue);
    }

    @OnTextChanged(R.id.et_express_name)
    public void onExpressNameChanged(CharSequence expressName) {
        etPhone.setBackgroundResource(TextUtils.isEmpty(expressName) ? R.drawable.shape_rectangle_stroke_blue : R.drawable.shape_rectangle_normal_blue);
    }

    @OnTextChanged(R.id.et_express_num)
    public void onExpressNumChanged(CharSequence expressNum) {
        etPhone.setBackgroundResource(TextUtils.isEmpty(expressNum) ? R.drawable.shape_rectangle_stroke_blue : R.drawable.shape_rectangle_normal_blue);
    }

    @OnTextChanged(R.id.et_other)
    public void onOtherChanged(CharSequence other) {
        etPhone.setBackgroundResource(TextUtils.isEmpty(other) ? R.drawable.shape_rectangle_stroke_blue : R.drawable.shape_rectangle_normal_blue);
    }

    public static InvoiceExpressFragment newInstance(Bundle bundle) {
        InvoiceExpressFragment fragment = new InvoiceExpressFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invoice_express;
    }

    @Override
    protected void onViewCreated(View view) {
        bind = ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        wId = bundle.getString(WID, "0");
    }

    @OnClick(R.id.tv_confirm)
    public void onConfirmClicked() {
        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String expressName = etExpressName.getText().toString().trim();
        String expressNum = etExpressNum.getText().toString().trim();
        String other = etOther.getText().toString().trim();
        if (TextUtils.isEmpty(expressName)) {
            showToastMsg("请输入快递公司名称");
            return;
        }
        if (TextUtils.isEmpty(expressNum)) {
            showToastMsg("请输入快递单号");
            return;
        }

//        “sender” : “”		（寄件人）
//        “phone” : “”		（手机号）
//        “express_name” : “”	（快递公司）
//        “express_num” : “”		（快递单号）
//        “message” : “”			（备注留言）
        JSONObject infoJson = new JSONObject();
        try {
            infoJson.put("sender", name);
            infoJson.put("phone", phone);
            infoJson.put("express_name", expressName);
            infoJson.put("express_num", expressNum);
            infoJson.put("message", other);
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
        }, wId, 2, infoJson.toString());
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
