package com.hzxmkuar.sxmaketnew.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.event.BaseEvent;
import com.common.event.EventBusConstants;
import com.common.event.SpConstants;
import com.common.mvp.BaseMvpActivity;
import com.common.mvp.BasePresenter;
import com.common.retrofit.methods.BusinessUserMethods;
import com.common.retrofit.subscriber.CommonSubscriber;
import com.common.retrofit.subscriber.SubscriberListener;
import com.common.utils.EmptyUtils;
import com.common.widget.editview.DeleteEditText;
import com.hzxmkuar.sxmaketnew.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 新增商家个人活动  <br/>
 * 共用界面 新增活动、活动审核中、活动通过、活动不通过，都用此界面。根据不同的intentType展示不同的内容  <br/>
 * intentType 为 0 时：商家个人活动  新增  <br/>
 * intentType 为 1 时：商家个人活动  审核中 <br/>
 * intentType 为 2 时：商家个人活动  通过 <br/>
 * intentType 为 3 时：商家个人活动  不通过 <br/>
 * @return
 */
public class AddNewActiveActivity extends BaseMvpActivity {
    private ImageView iv_add_new_active_back;
    private DeleteEditText edt_input_active_them;
    private DeleteEditText edt_input_active_content;
    private Button btn_commoit_active;
    private TextView add_new_active_title;

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_new_active;
    }

    @Override
    protected void onViewCreated() {
        String intentType = getIntent().getStringExtra(SpConstants.INTENT_TYPE);
        iv_add_new_active_back = (ImageView) findViewById(R.id.iv_add_new_active_back);
        edt_input_active_them = (DeleteEditText) findViewById(R.id.edt_input_active_them);
        edt_input_active_content = (DeleteEditText) findViewById(R.id.edt_input_active_content);
        btn_commoit_active = (Button) findViewById(R.id.btn_commoit_active);
        add_new_active_title = (TextView) findViewById(R.id.add_new_active_title);
        if ("0".equals(intentType)){
            setEditTextNotEditAble(true);
            add_new_active_title.setText("新增活动");
        }else if ("1".equals(intentType)){
            setEditTextNotEditAble(false);
            add_new_active_title.setText("审核中");
        }else if ("2".equals(intentType)){
            setEditTextNotEditAble(false);
            add_new_active_title.setText("审核成功");
        }else if ("3".equals(intentType)){
            setEditTextNotEditAble(false);
            add_new_active_title.setText("审核失败");
        }
    }

    @Override
    protected void doLogicFunc() {
        attachClickListener(iv_add_new_active_back);
        attachClickListener(btn_commoit_active);
    }

    @Override
    protected void setStatusBar() {
    }

    @Override
    protected void onViewClicked(View view) {
        if (view.getId() == iv_add_new_active_back.getId()){
            finish();
        }else if (view.getId() == btn_commoit_active.getId()){
            if (EmptyUtils.isEmpty(getEditTextStr(edt_input_active_them))){
                showToastMsg("活动标题不能为空");
                return;
            }
            if (EmptyUtils.isEmpty(getEditTextStr(edt_input_active_content))){
                showToastMsg("活动内容不能为空");
                return;
            }
            commitNewActivie();
        }
    }

    /**
     *  提交商家个人新增活动
     */
    private void commitNewActivie() {
        CommonSubscriber<Object> subscriber = new CommonSubscriber<>(new SubscriberListener() {

            @Override
            public void onNext(Object o) {
                statusContent();
                showToastMsg("活动添加成功，请耐心等待审核");
                EventBus.getDefault().post(new BaseEvent(EventBusConstants.SHOP_ADD_NEW_ACTIVE));
                finish();
            }

            @Override
            public void onError(String e, int code) {
                statusContent();
                showToastMsg(e);
            }
        });
        List<String> addNewParama = new ArrayList<>();
        addNewParama.add("time");
        addNewParama.add("uid");
        addNewParama.add("activity_desc");
        addNewParama.add("activity_info");
        BusinessUserMethods.getInstance().addNewActive(subscriber,addNewParama,getEditTextStr(edt_input_active_them),getEditTextStr(edt_input_active_content));
        rxManager.add(subscriber);
    }

    /**
     * 设置EidtText控件为不可编辑状态
     * @param isEditAble  true为可编辑状态 false为不可编辑状态
     */
    private void setEditTextNotEditAble(boolean isEditAble){
        if (isEditAble){
            edt_input_active_them.setCursorVisible(true);
            edt_input_active_them.setFocusable(true);
            edt_input_active_them.setFocusableInTouchMode(true);
            edt_input_active_content.setCursorVisible(true);
            edt_input_active_content.setFocusable(true);
            edt_input_active_content.setFocusableInTouchMode(true);
            btn_commoit_active.setVisibility(View.VISIBLE);
        }else {
            edt_input_active_them.setCursorVisible(false);
            edt_input_active_them.setFocusable(false);
            edt_input_active_them.setFocusableInTouchMode(false);
            edt_input_active_content.setCursorVisible(false);
            edt_input_active_content.setFocusable(false);
            edt_input_active_content.setFocusableInTouchMode(false);
            btn_commoit_active.setVisibility(View.GONE);
            Bundle extras = getIntent().getExtras();
            edt_input_active_them.setText(extras.getString("desc").toString());
            edt_input_active_content.setText(extras.getString("info").toString());
        }
    }
}
