package com.hzxmkuar.sxmaketnew;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;

import java.text.DecimalFormat;

/**
 * Created by STH on 2018/1/8.
 */

public class TXSuccessActivity extends BaseMvpActivity {

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
        return R.layout.activity_topay_success;
    }

    @Override
    protected void onViewCreated() {
        mBack = (ImageView) findViewById(R.id.back);
        TextView money = (TextView) findViewById(R.id.money);
        money.setText("￥"+doubleToString(Double.parseDouble(getIntent().getStringExtra("money"))));
    }
    public  String doubleToString(double num){
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }
    @Override
    protected void doLogicFunc() {
        attachClickListener(mBack);
    }

    @Override
    protected void onViewClicked(View view) {
          if (mBack.getId()==view.getId()){
             finish();
        }
        super.onViewClicked(view);
    }
}
