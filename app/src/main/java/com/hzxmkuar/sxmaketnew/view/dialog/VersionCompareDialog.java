package com.hzxmkuar.sxmaketnew.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.common.utils.EmptyUtils;
import com.common.utils.FileUtils;
import com.common.widget.toast.ToastManager;
import com.hzxmkuar.sxmaketnew.R;

import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.DOWNLOAD_SERVICE;

/**
 *  版本升级提示弹窗
 * Created by Administrator on 2018/8/26.
 */
public class VersionCompareDialog extends Dialog implements View.OnClickListener {
    private static final String TAG = "VersionCompareDialog";
    private Button mBtnUpdate;
    private Button mTvCancelUpdate;
    private TextView tvVersionDesc;
    private TextView tvCancelUpdate;
    private Activity mActivity;
    private Context mContext;
    /**
     * 版本更新描述
     */
    private String mUpdateDesc ="";
    /**
     * 是否需要强制更新  <br/>
     * （是否强制 0不需要更新，1不强制，2强制）: "1"  <br/>
     */
    private String mIsNeedForceUpdate ="";
    /**
     * 下载链接
     */
    private String mDownLoadUrl ="";
    private String mNewVersionCode ="";

    /*  下载相关操作  */
    private DownloadManager downloadManager;
    private DownloadManager.Request request;
    private Timer timer;
    private TimerTask task;
    long apkDownLoadId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_version_compare);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        setDialogProperty();
        //初始化界面控件
        initView();
        downloadManager = (DownloadManager) mContext.getSystemService(DOWNLOAD_SERVICE);
        request = new DownloadManager.Request(Uri.parse(mDownLoadUrl));
        request.setTitle("省鑫商户端");
        request.setDescription("正在下载省鑫网商户端...");
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        request.setAllowedOverRoaming(false);
        request.setMimeType("application/vnd.android.package-archive");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        // 此处需要判断是否挂载了sd卡
        //创建目录
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).mkdir();
        if (FileUtils.isSDCardAvailable()) {
            request.setDestinationInExternalFilesDir(mContext, Environment.DIRECTORY_DOWNLOADS, "/sx_business_release" + mNewVersionCode + ".apk");
        }
    }

    /**
     *  版本更新弹窗
     * @param context  context <br/>
     * @param activity actvity <br/>
     * @param desc  版本更新描述  <br/>
     * @param isForceUpdate 是否需要强制更新  <br/>
     *  是需要更新 0不需要更新，不弹窗  <br/>
     *  1 不强制更新  <br/>
     *  2 强制更新 弹窗不可取消 <br/>
     * @param apkUrl  apk下载链接  <br/>
     * @param nearlyVersionCode  最新版本  <br/>
     */
    public VersionCompareDialog(@NonNull Context context, Activity activity, String desc, String isForceUpdate, String apkUrl, String nearlyVersionCode) {
        super(context, R.style.custom_dialog);
//        super(context);
        this.mActivity = activity;
        this.mContext = context;
        this.mUpdateDesc = desc;
        this.mIsNeedForceUpdate =isForceUpdate;
        this.mDownLoadUrl = apkUrl;
        this.mNewVersionCode =nearlyVersionCode;
    }


    /**
     * 初始化界面控件
     */
    private void initView() {
        mBtnUpdate = (Button) findViewById(R.id.btn_update);
        mTvCancelUpdate = (Button) findViewById(R.id.tv_cancel_update);
        tvVersionDesc = (TextView) findViewById(R.id.tv_version_desc);
        tvCancelUpdate = (TextView) findViewById(R.id.tv_cancel_update);
        mBtnUpdate.setOnClickListener(this);
        mTvCancelUpdate.setOnClickListener(this);

        if (!EmptyUtils.isEmpty(mUpdateDesc)){
            tvVersionDesc.setText(mUpdateDesc);
        }else {
            tvVersionDesc.setText("省鑫网有新版本更新啦，赶快来更新吧。");
        }
        if ("2".equals(mIsNeedForceUpdate)){
            tvCancelUpdate.setVisibility(View.GONE);
        }else {
            tvCancelUpdate.setVisibility(View.VISIBLE);
        }
        tvCancelUpdate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == mTvCancelUpdate.getId()){
//            ToastManager.showShortToast("暂不更新");
            dismiss();
        }else if (v.getId() == mBtnUpdate.getId()){
            beginDownloadApk();
        }else if (v.getId() == tvCancelUpdate.getId()){
            dismiss();
            cancel();
        }
    }

    /**
     * 设置dialog外部点击，以及返回无法取消
     */
    private void setDialogProperty() {
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
        this.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return (keyCode == KeyEvent.KEYCODE_SEARCH);
            }
        });
    }

    /**
     * 开始下载
     */
    private void beginDownloadApk() {
        apkDownLoadId = downloadManager.enqueue(request);
//        mBtnUpdate.setVisibility(View.GONE);
        mBtnUpdate.setClickable(false);
        final DownloadManager.Query query = new DownloadManager.Query();
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                Cursor cursor = downloadManager.query(query.setFilterById(apkDownLoadId));
                if (cursor != null && cursor.moveToFirst()) {
                    String address = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    Log.i(TAG, "run:   下载安装的路径:     " + address);
                    if (cursor.getInt(
                            cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                        VersionCompareDialog.this.dismiss();
                        install(address);
                        task.cancel();
                    }
                }
                cursor.close();
            }
        };
        timer.schedule(task, 0, 1000);
        task.run();
    }

    /**
     * 安装
     *
     * @param path
     */
    private void install(String path) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + path), "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//4.0以上系统弹出安装成功打开界面
        mContext.startActivity(intent);
    }
}
