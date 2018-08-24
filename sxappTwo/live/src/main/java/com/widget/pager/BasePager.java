package com.widget.pager;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.common.widget.toast.ToastManager;

/**
 * Created by Administrator on 2016/8/29.
 */
public abstract class BasePager {
    private final String title;
    protected Context context;
    protected FragmentActivity activity;
    protected String hx_chatRoomID;
    protected String fid;

    public BasePager(Context context, FragmentActivity activity, String hx_chatRoomID, String fid,String title) {
        this.activity = activity;
        this.context = context;
        this.hx_chatRoomID = hx_chatRoomID;
        this.fid = fid;
        this.title = title;
    }

    public abstract View initView();

    public abstract void initData();

    public void showToast(String msg) {
        ToastManager.showShortToast(msg);
    }

    protected void goToLoginActivity() {

//        SharedPreferencesUtil.getInstance(context).putBoolean(Constant.KEY_HAS_LOGIN, false);
//        SPUtils.setShareString(Constant.KEY_UPIC, "");
//        SPUtils.setShareString(Constant.KEY_LOCAL_UPIC, "");
//        SPUtils.setShareString(Constant.KEY_EMAIL, "");
//        SPUtils.setShareString(Constant.KEY_HASHID, "");
//        SPUtils.setShareString(Constant.KEY_UID, "");
//        SPUtils.setShareString(Constant.NICK_NAME, "");
//        SPUtils.setShareString(Constant.USER_NAME, "");
//        SPUtils.setShareString(Constant.USER_SEX, "");
//        SPUtils.setShareString(Constant.KEY_HX_PASSWORD, "");
//        SPUtils.setShareString(Constant.KEY_HX_USER_NAME, "");
//        SPUtils.setShareString(Constant.KEY_PHONE, "");
//        SharedPreferencesUtil.getInstance(context).removeKey(Constant.KEY_HX_USER_NAME);
//        SharedPreferencesUtil.getInstance(context).removeKey(Constant.KEY_HX_PASSWORD);
//        AppManager.getAppManager().finishAllActivity();
//        EMClient.getInstance().logout(true);
//        Intent intent = new Intent(context, LoginPhoneActivity.class);
//        activity.startActivity(intent);
//        activity.finish();
    }
}
