package com.hzxmkuar.sxmaketnew;

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

import java.util.ArrayList;
import java.util.List;

/**
 *  添加银行卡
 * Created by STH on 2017/11/23.
 */
public class AddBankCardActivity extends BaseMvpActivity {

    private DeleteEditText mBankid, mId_card;
    private TextView mBankdes;
    private DeleteEditText mCardPhone;
    private Button mNext;
    private LinearLayout mChoose_bank;
    private OptionsPickerView banksPicker;
    private List<String> banksNames = new ArrayList<>();
    private List<String> banksids = new ArrayList<>();
    private String bankes = "";
    private ImageView mBack;


    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected void setStatusBar() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bd_bank;
    }

    @Override
    protected void onViewCreated() {
        mBankid = (DeleteEditText) findViewById(R.id.bankid);
        mBankdes = (TextView) findViewById(R.id.bankdes);
        mId_card = (DeleteEditText) findViewById(R.id.id_card);
        mCardPhone = (DeleteEditText) findViewById(R.id.ed_card_phone);
        mNext = (Button) findViewById(R.id.next);
        mChoose_bank = (LinearLayout) findViewById(R.id.choose_bank);
        mBack = (ImageView) findViewById(R.id.back);
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(mNext);
        attachClickListener(mChoose_bank);
        attachClickListener(mBankdes);
        attachClickListener(mBack);
        getBankListReq();
    }

    /**
     *  获取银行卡列表
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
        if (view.getId() == mNext.getId()) {
            if (bankes.equals("") || getEditTextStr(mBankid).equals("") || getEditTextStr(mCardPhone).equals("") || getEditTextStr(mId_card).equals("")) {
                showToastMsg("请填写完整的信息");
            } else {
                addBankCardReq(bankes, getEditTextStr(mBankid), getEditTextStr(mCardPhone), getEditTextStr(mId_card));
            }
        } else if (view.getId() == mChoose_bank.getId()) {
            if (banksPicker != null) {
                banksPicker.show();
            }
        } else if (view.getId() == mBankdes.getId()) {
            if (banksPicker != null) {
                banksPicker.show();
            }
        } else if (view.getId() == mBack.getId()) {
            finish();
        }
    }




    private void initBanksOptionsPicker() {
        banksPicker = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String bank_name = banksNames.get(options1);
                mBankdes.setText(bank_name);
                bankes = banksids.get(options1);
            }
        }).setSubmitText(getString(R.string.app_confirm)).
                setCancelText(getString(R.string.app_cancel)).
                build();
        banksPicker.setPicker(banksNames);
    }

    /**
     * 添加银行卡
     * @param cardNumber 卡号
     * @param cardPhone 绑卡手机
     * @param idCard 身份证
     * @param banksNames 开户行
     */
    private void addBankCardReq(String cardNumber, String cardPhone, String idCard, String banksNames) {
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
        CenterMethods.getInstance().addBank(subscriber, cardNumber, cardPhone, idCard, banksNames);
        rxManager.add(subscriber);
    }
}
