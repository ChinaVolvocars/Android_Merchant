package com.hzxmkuar.sxmaketnew.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.BankNameBean;
import com.common.retrofit.methods.CenterMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.widget.editview.DeleteEditText;
import com.hzxmkuar.sxmaketnew.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加对公银行卡
 * Created by STH on 2017/11/23.
 */
public class AddBankCardToCompanyActivity extends BaseMvpActivity {
    private static final String TAG = "AddBankCardToCompanyAct";

    private ImageView tv_add_bank_card_to_company_back;
    private DeleteEditText edt_company_to_public;
    private TextView tv_bank_cadrd_name_to_public;
    private TextView tv_title;
    private DeleteEditText edt_bank_address_to_public;
    private DeleteEditText edt_bank_cadr_no_to_public;
    private Button btn_commoit_info_to_public;
    private LinearLayout choose_bank_to_public;
    private OptionsPickerView banksPicker;
    private List<String> banksNames = new ArrayList<>();
    private List<String> banksids = new ArrayList<>();
    private String bankes = "";


    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_bd_bank_to_public;
    }

    @Override
    protected void onViewCreated() {
        tv_add_bank_card_to_company_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("添加对公账户");
        edt_company_to_public = (DeleteEditText) findViewById(R.id.edt_company_to_public);
        tv_bank_cadrd_name_to_public = (TextView) findViewById(R.id.tv_bank_cadrd_name_to_public);
        edt_bank_address_to_public = (DeleteEditText) findViewById(R.id.edt_bank_address_to_public);
        edt_bank_cadr_no_to_public = (DeleteEditText) findViewById(R.id.edt_bank_cadr_no_to_public);
        btn_commoit_info_to_public = (Button) findViewById(R.id.btn_commoit_info_to_public);
        choose_bank_to_public = (LinearLayout) findViewById(R.id.choose_bank_to_public);
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(tv_add_bank_card_to_company_back);
        attachClickListener(choose_bank_to_public);
        attachClickListener(btn_commoit_info_to_public);
        getBankListReq();
    }

    /**
     * 获取银行卡列表
     */
    private void getBankListReq() {
        final CommonSubscriber<BankNameBean> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                BankNameBean bankNameBean = (BankNameBean) o;
                List<BankNameBean.DataBean> data = bankNameBean.getData();
                for (BankNameBean.DataBean bean : data) {
                    banksNames.add(bean.getName());
                    banksids.add(bean.getId());
                }
                initBanksOptionsPicker();
                statusContent();
            }

            @Override
            public void onError(String e, int code) {
                showToastMsg(e);
            }
        });
        CenterMethods.getInstance().bankType(subscriber);
        rxManager.add(subscriber);
    }


    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == btn_commoit_info_to_public.getId()) {
            if (bankes.equals("") || getEditTextStr(edt_company_to_public).equals("") ||
                    getTextViewStr(tv_bank_cadrd_name_to_public).equals("") || getEditTextStr(edt_bank_address_to_public).equals("")
                    || getEditTextStr(edt_bank_cadr_no_to_public).equals("")) {
                showToastMsg("请填写完整的信息");
            } else {
                addBankCardReq(getEditTextStr(edt_bank_cadr_no_to_public), getEditTextStr(edt_bank_address_to_public), bankes, getEditTextStr(edt_company_to_public));
            }
        } else if (view.getId() == tv_bank_cadrd_name_to_public.getId()) {
            if (banksPicker != null) {
                banksPicker.show();
            }
        } else if (view.getId() == choose_bank_to_public.getId()) {
            if (banksPicker != null) {
                banksPicker.show();
            }
        } else if (view.getId() == tv_add_bank_card_to_company_back.getId()) {
            finish();
        }
    }


    private void initBanksOptionsPicker() {
        banksPicker = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String bank_name = banksNames.get(options1);
                tv_bank_cadrd_name_to_public.setText(bank_name);
                bankes = banksids.get(options1);
            }
        }).setSubmitText(getString(R.string.app_confirm)).
                setCancelText(getString(R.string.app_cancel)).
                build();
        banksPicker.setPicker(banksNames);
    }

    /**
     * 添加对公银行卡  <br/>
     *
     * @param cardNumber 银行卡账号       <br/>
     * @param brancBank  支行名称      <br/>
     * @param bankId     发卡银行    <br/>
     * @param userName   公司名称   <br/>
     * @return
     */
    private void addBankCardReq(String cardNumber, String brancBank, String bankId, String userName) {
        showProgressingDialog();
        final CommonSubscriber<Object> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                dismissProgressDialog();
                showToastMsg("提交成功");
                finish();
            }

            @Override
            public void onError(String e, int code) {
                dismissProgressDialog();
                showToastMsg(e);
            }
        });
        CenterMethods.getInstance().addBankCardToCompany(subscriber, cardNumber, brancBank, bankId, userName);
        rxManager.add(subscriber);
    }
}
