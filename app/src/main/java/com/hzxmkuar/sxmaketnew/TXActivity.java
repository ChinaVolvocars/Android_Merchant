package com.hzxmkuar.sxmaketnew;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.SPUtils;
import com.common.widget.editview.DeleteEditText;
import com.tencent.mm.sdk.openapi.IWXAPI;

/**
 * Created by STH on 2018/1/8.
 */

public class TXActivity extends BaseMvpActivity {

    private TextView mName;
    private Button mButton;
    private ImageView mIma_wx;
    private ImageView mIma_ali,mIma_ye;
    private int type =1;
    private IWXAPI api;
    private ImageView mBack;
    private LinearLayout ll_one;
    private DeleteEditText mMoney;
    private TextView mNum;
    private double mParseDouble;
    private double mParseDouble2;
    private TextView mTil;
    private String mId = "";
    private ImageView mIma;
    private double mParseDouble1 = 0.00;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected void setStatusBar() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tx;
    }

    @Override
    protected void onViewCreated() {
        mBack = (ImageView) findViewById(R.id.back);
        mButton = (Button) findViewById(R.id.tijiao);
        ll_one = (LinearLayout) findViewById(R.id.ll_one);
        mMoney = (DeleteEditText) findViewById(R.id.money);
        mName = (TextView) findViewById(R.id.name);
        mNum = (TextView) findViewById(R.id.num);
        mTil = (TextView) findViewById(R.id.til);
        mTil.setVisibility(View.GONE);
        mIma = (ImageView) findViewById(R.id.ima);
        mMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    mParseDouble1 = Double.parseDouble(editable.toString())*1.00;
                    mNum.setText(mParseDouble1*mParseDouble*1.00+"元");
                }catch (Exception e){
                    mNum.setText("0.00");
                }
            }
        });
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(mButton);
        attachClickListener(mBack);
        attachClickListener(ll_one);
        String proportion = SPUtils.getShareString("proportion");
        String ratio = SPUtils.getShareString("ratio");
        mParseDouble2 = Double.parseDouble(proportion)*1.00;
        mParseDouble = Double.parseDouble(ratio)*1.00;
        mName.setText(mParseDouble2*100+"%");
    }

    @Override
    protected void onViewClicked(View view) {
         if (mBack.getId()==view.getId()){
            finish();
        }else if (mButton.getId()==view.getId()){
            if (mId.equals("")||mParseDouble1==0.00){
                showToastMsg("请输入完整的信息");
                return;
            }
            goToHttpReqsss(mParseDouble1+"",mId,mParseDouble+"");
        }else if (ll_one.getId()==view.getId()) {
             startActivityForResult(new Intent(context,MyBankActivity.class).putExtra("name","111"),50);
         }
        super.onViewClicked(view);
    }
    private void goToHttpReqsss(String id, String editTextStr, String textStr) {
        showProgressingDialog();
        CommonSubscriber<Object> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                dismissProgressDialog();
                startActivity(new Intent(context,TXSuccessActivity.class).putExtra("money",mParseDouble1*(1-mParseDouble)*1.00+""));
            }

            @Override
            public void onError(String e, int code) {
                dismissProgressDialog();
                showToastMsg(e);
            }
        });
        BusinessUserMethods.getInstance().withdraw(subscriber,id,editTextStr,textStr);
        rxManager.add(subscriber);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 50){
            if (resultCode==100){
                mTil.setVisibility(View.GONE);
                mTil.setText("（*** "+data.getStringExtra("name")+"）");
                mId = data.getStringExtra("id");
                if (!mId.equals("")){
                    mIma.setImageResource(R.mipmap.sel_sels);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
