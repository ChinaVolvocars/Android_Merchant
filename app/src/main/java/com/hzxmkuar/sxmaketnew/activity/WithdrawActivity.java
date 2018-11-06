package com.hzxmkuar.sxmaketnew.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.base.Constants;
import com.common.event.WidthDrawConstants;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.WidthDrawEntity;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.EmptyUtils;
import com.common.utils.SPUtils;
import com.common.widget.editview.DeleteEditText;
import com.hzxmkuar.sxmaketnew.R;
import com.tencent.mm.sdk.openapi.IWXAPI;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 提现
 * Created by STH on 2018/1/8.
 */
public class WithdrawActivity extends BaseMvpActivity {
    private static final String TAG = "WithdrawActivity";
    private TextView mName;
    private Button mBtnWidthDrawCommit;
    private ImageView mIma_wx;
    private ImageView mIma_ali, mIma_ye;
    private int type = 1;
    private IWXAPI api;
    private ImageView mBack;
    private LinearLayout mLlChoseBankCard;
    //    private DeleteEditText mMoney;
    private TextView mTvCanTakeXindou;
    //    private TextView mNum;
    private TextView mTvCanTakeMoney;
    private double mParseDouble;
    private double mParseDouble2;
    private TextView mTil;
    private String mId = "";
    private ImageView mIma;
    private double totalWidthDrawMoney = 0.00;
    private String canTakeXindous = "";
    private String widthDrawRation = "";

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
        canTakeXindous = getIntent().getStringExtra(WidthDrawConstants.CAN_TAKE_XINDOUS);
        widthDrawRation = getIntent().getStringExtra(WidthDrawConstants.WIDTHDRAW_RATION);

        mBack = (ImageView) findViewById(R.id.back);
        mBtnWidthDrawCommit = (Button) findViewById(R.id.tijiao);
        mLlChoseBankCard = (LinearLayout) findViewById(R.id.ll_chose_bank_card);
        mTvCanTakeXindou = (TextView) findViewById(R.id.tv_can_take_xindou);
        mName = (TextView) findViewById(R.id.name);
        mTvCanTakeMoney = (TextView) findViewById(R.id.tv_can_take_money);
        mTil = (TextView) findViewById(R.id.til);
        mTil.setVisibility(View.GONE);
        mIma = (ImageView) findViewById(R.id.ima);
        if (!EmptyUtils.isEmpty(canTakeXindous)) {
            mTvCanTakeXindou.setText(canTakeXindous);
        }
        if (!EmptyUtils.isEmpty(canTakeXindous) && !EmptyUtils.isEmpty(widthDrawRation) && Double.parseDouble(canTakeXindous) > 0) {
            String widthDrawMoney = String.valueOf(Double.parseDouble(canTakeXindous) * Double.parseDouble(widthDrawRation));
            mTvCanTakeMoney.setText(transforDoubleStr2Double(widthDrawMoney)+ "元");
        } else {
            mTvCanTakeMoney.setText("0.00元");
        }
    }

    /**
     *  将double类型的字符串，转换成double类型值，四舍五入保留两位小数  <br/>
     * @param doubleStr  double类型的字符串  <br/>
     * @return  返回一个double值   <br/>
     */
    private String transforDoubleStr2Double(String doubleStr){
        double str2double = 0.00;
        String returnStr = "0.00";
        if (!EmptyUtils.isEmpty(doubleStr)){
            BigDecimal b = new BigDecimal(doubleStr);
            str2double = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            DecimalFormat df = new DecimalFormat("0.00");
            returnStr = df.format(str2double); //6.20   这个是字符串，但已经是我要的两位小数了
        }
        return returnStr;
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(mBtnWidthDrawCommit);
        attachClickListener(mBack);
        attachClickListener(mLlChoseBankCard);
        String proportion = SPUtils.getShareString("proportion");
        String ratio = SPUtils.getShareString("ratio");
        mParseDouble2 = Double.parseDouble(proportion) * 1.00;
        mParseDouble = Double.parseDouble(ratio) * 1.00;
        mName.setText(mParseDouble2 * 100 + "%");
    }

    @Override
    protected void onViewClicked(View view) {
        if (mBack.getId() == view.getId()) {
            finish();
        } else if (mBtnWidthDrawCommit.getId() == view.getId()) {
            if (mId.equals("") || EmptyUtils.isEmpty(canTakeXindous)) {
                showToastMsg("请输入完整的信息");
                return;
            }
            goToHttpReqsss(canTakeXindous + "", mId, mParseDouble + "");
        } else if (mLlChoseBankCard.getId() == view.getId()) {
            startActivityForResult(new Intent(context, MyBankActivity.class).putExtra("name", "111"), 50);
        }
        super.onViewClicked(view);
    }

    private void goToHttpReqsss(String totalXindouCanTake, String editTextStr, String textStr) {
        showProgressingDialog();
        CommonSubscriber<WidthDrawEntity> subscriber = new CommonSubscriber<>(new SubscriberListener() {
            @Override
            public void onNext(Object o) {
                dismissProgressDialog();
                WidthDrawEntity result = (WidthDrawEntity) o;
                Log.i(TAG, "onNext:         " + result.getMoney());
                Intent intent = new Intent(context,WithdrawSuccessActivity.class);
                intent.putExtra("widthDrawSuccess",result.getMoney());
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(String e, int code) {
                dismissProgressDialog();
                showToastMsg(e);
            }
        });
        BusinessUserMethods.getInstance().withdraw(subscriber, totalXindouCanTake, editTextStr, textStr);
        rxManager.add(subscriber);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 50) {
            if (resultCode == 100) {
                mTil.setVisibility(View.GONE);
                mTil.setText("（*** " + data.getStringExtra("name") + "）");
                mId = data.getStringExtra("id");
                if (!mId.equals("")) {
                    mIma.setImageResource(R.mipmap.sel_sels);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
