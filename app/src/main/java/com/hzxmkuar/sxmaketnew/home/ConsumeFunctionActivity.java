package com.hzxmkuar.sxmaketnew.home;

import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.entity.result.CheckTicketsResultEntity;
import com.common.retrofit.entity.result.ConsumeFunctionEntity;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.hzxmkuar.sxmaketnew.R;

/**
 * 消费功能
 * Created by jc on 2019/1/4.
 */
public class ConsumeFunctionActivity extends BaseMvpActivity {
    private static final String TAG = "ConsumeFunctionActivity";
    private ImageView ivBack;
    private TextView mTvTitle;
    private Switch switch_bt_our_shop, switch_bt_other_shop;
    //    private MySwitchButtonStateChangeListener stateChangeListener;
    private MyOnclickListener btnClickListener;
    private Switch switch_bt_leaflet;
    private Switch switch_bt_mall_consume;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_consume_funcetion;
    }

    @Override
    protected void onViewCreated() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvTitle.setText("消费功能");
        switch_bt_our_shop = (Switch) findViewById(R.id.switch_bt_our_shop);
        switch_bt_other_shop = (Switch) findViewById(R.id.switch_bt_other_shop);
        switch_bt_mall_consume = (Switch) findViewById(R.id.switch_bt_mall_consume);
        switch_bt_leaflet = (Switch) findViewById(R.id.switch_bt_leaflet);

        btnClickListener = new MyOnclickListener();

        switch_bt_our_shop.setOnClickListener(btnClickListener);
        switch_bt_other_shop.setOnClickListener(btnClickListener);
        switch_bt_mall_consume.setOnClickListener(btnClickListener);
        switch_bt_leaflet.setOnClickListener(btnClickListener);
        getConsueFunctionData();
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(ivBack);
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == ivBack.getId()) {
            finish();
        }
    }

    public void getConsueFunctionData() {
        showProgressingDialog();
        CommonSubscriber<ConsumeFunctionEntity> subscriber = new CommonSubscriber<>(new SubscriberListener<ConsumeFunctionEntity>() {
            @Override
            public void onNext(ConsumeFunctionEntity result) {
                dismissProgressDialog();
                switch_bt_our_shop.setChecked(verifyResult2SetSwitchState(result.getOur_shop()));
                switch_bt_other_shop.setChecked(verifyResult2SetSwitchState(result.getOther_shop()));
                switch_bt_mall_consume.setChecked(verifyResult2SetSwitchState(result.getMall()));
                switch_bt_leaflet.setChecked(verifyResult2SetSwitchState(result.getLeaflet()));
            }

            @Override
            public void onError(String e, int code) {
                showToastMsg(e);
                dismissProgressDialog();
            }
        });
        BusinessUserMethods.getInstance().ability(subscriber);
        rxManager.add(subscriber);
    }


    class MyOnclickListener implements View.OnClickListener {
        String clickedBtn = "";

        @Override
        public void onClick(View buttonView) {
            if (buttonView.getId() == switch_bt_our_shop.getId()) {
//                showToastMsg(switch_bt_our_shop.isChecked() ? "本店消费-打开" : "本店消费-关闭");
                clickedBtn = "our_shop";
            } else if (buttonView.getId() == switch_bt_other_shop.getId()) {
//                showToastMsg(switch_bt_other_shop.isChecked() ? "他店消费-打开" : "他店消费-关闭");
                clickedBtn = "other_shop";
            } else if (buttonView.getId() == switch_bt_mall_consume.getId()) {
//                showToastMsg(switch_bt_mall_consume.isChecked() ? "商城消费-打开" : "商城消费-关闭");
                clickedBtn = "mall";
            } else if (buttonView.getId() == switch_bt_leaflet.getId()) {
//                showToastMsg(switch_bt_leaflet.isChecked() ? "开鑫传单-打开" : "开鑫传单-关闭");
                clickedBtn = "leaflet";
            }
//            showToastMsg(clickedBtn + "   的状态为      " + verifySwitchButtonState((Switch) buttonView) + "");
            requestChangeBtnState(clickedBtn, verifySwitchButtonState((Switch) buttonView));
        }
    }

    /**
     * 请求服务器更改按钮状态
     *
     * @param cleckedBtn      被点击的按钮   <br/>
     *                        our_shop      本店消费   <br/>
     *                        other_shop      他店消费   <br/>
     *                        mall          网上商城    <br/>
     *                        leaflet          开鑫传单    <br/>
     * @param clickedBtnState 按钮被点击时的状态          <br/>
     *                        按钮为true时  返回的值 为1         <br/>
     *                        按钮为false时  返回的值 为2         <br/>
     */
    private void requestChangeBtnState(final String cleckedBtn, final String clickedBtnState) {
        showProgressingDialog();

        Log.i(TAG, "requestChangeBtnState:     被点击的按钮：cleckedBtn    " + cleckedBtn);
        Log.i(TAG, "requestChangeBtnState:     被点击的按钮的状态：clickedBtnState    " + clickedBtnState);
        CommonSubscriber<CheckTicketsResultEntity> subscriber = new CommonSubscriber<>(new SubscriberListener<CheckTicketsResultEntity>() {
            @Override
            public void onNext(CheckTicketsResultEntity result) {
                dismissProgressDialog();
                // 网络请求服务更改被点击按钮的状态。 请求成功根据 返回值来设置被点击按钮的状态。
                Log.i(TAG, "onNext:     被点击的按钮：cleckedBtn    " + cleckedBtn);
                Log.i(TAG, "onNext:     被点击的按钮的状态：clickedBtnState    " + clickedBtnState);
                if ("our_shop".equals(cleckedBtn)) {
                    switch_bt_our_shop.setChecked(verifyResult2SetSwitchState(result.getStatus()));
                } else if ("other_shop".equals(cleckedBtn)) {
                    switch_bt_other_shop.setChecked(verifyResult2SetSwitchState(result.getStatus()));
                } else if ("mall".equals(cleckedBtn)) {
                    switch_bt_mall_consume.setChecked(verifyResult2SetSwitchState(result.getStatus()));
                } else if ("leaflet".equals(cleckedBtn)) {
                    switch_bt_leaflet.setChecked(verifyResult2SetSwitchState(result.getStatus()));
                }

            }

            @Override
            public void onError(String e, int code) {
                showToastMsg(e);
                dismissProgressDialog();
                //   网络请求服务更改被点击按钮的状态。 请求失败根据恢复按钮原来被点击时的状态。
                if ("our_shop".equals(cleckedBtn)) {
                    switch_bt_our_shop.setChecked(verifyResult2SetSwitchState(clickedBtnState));
                } else if ("other_shop".equals(cleckedBtn)) {
                    switch_bt_other_shop.setChecked(verifyResult2SetSwitchState(clickedBtnState));
                } else if ("mall".equals(cleckedBtn)) {
                    switch_bt_mall_consume.setChecked(verifyResult2SetSwitchState(clickedBtnState));
                } else if ("leaflet".equals(cleckedBtn)) {
                    switch_bt_leaflet.setChecked(verifyResult2SetSwitchState(clickedBtnState));
                }
            }
        });
        BusinessUserMethods.getInstance().abilityButton(subscriber, cleckedBtn, clickedBtnState);
        rxManager.add(subscriber);

    }

    /**
     * 校验SwitchButton的状态。  <br/>
     *
     * @param swtichBtn
     * @return 如果当前按钮   <br/>
     * 状态为true时，则返回 1    <br/>状态为false 则返回 2;   <br/>
     */
    private String verifySwitchButtonState(Switch swtichBtn) {
        return (!swtichBtn.isChecked()) ? "1" : "2";
    }

    /**
     * 根据传入的字符串内容来设定返回的boolean值。   <br/>
     *
     * @param stateStr 为 1 时返回true  <br/>为 其他 时返回true  <br/>
     * @return
     */
    private boolean verifyResult2SetSwitchState(String stateStr) {
        return "1".equals(stateStr) ? true : false;
    }
}

