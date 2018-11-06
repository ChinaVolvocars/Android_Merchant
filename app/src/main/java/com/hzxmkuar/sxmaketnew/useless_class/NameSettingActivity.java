//package com.hzxmkuar.sxmaketnew.activity;
//
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.common.mvp.BaseMvpActivity;
//import com.common.mvp.BasePresenter;
//import com.common.utils.EmptyUtils;
//import com.common.widget.editview.DeleteEditText;
//import com.hzxmkuar.sxmaketnew.R;
//
//public class NameSettingActivity extends BaseMvpActivity {
//    private DeleteEditText evName;
//    private TextView btnSave;
//    private ImageView mBack;
//
//    @Override
//    protected BasePresenter createPresenterInstance() {
//        return null;
//    }
//
//    @Override
//    protected void setStatusBar() {
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_setting_name;
//    }
//
//    @Override
//    protected void onViewCreated() {
//        evName = (DeleteEditText) findViewById(R.id.ev_name);
//        btnSave = (TextView) findViewById(R.id.btn_save);
//        mBack = (ImageView) findViewById(R.id.back);
//    }
//
//    @Override
//    protected void doLogicFunc() {
//        attachClickListener(btnSave);
//        attachClickListener(mBack);
//    }
//
//    @Override
//    protected void onViewClicked(View view) {
//        if (view.getId() == mBack.getId()) {
//            finish();
//        } else {
//            reqHttpData();
//        }
//    }
//
//    // 修改昵称
//    private void reqHttpData() {
//        if (EmptyUtils.isEmpty(getEditTextStr(evName))) {
//            showToastMsg("请设置昵称");
//            return;
//        }
//        finish();
//    }
//}
