package com.hzxmkuar.sxmaketnew.base;


import android.app.ActivityManager;

import com.common.retrofit.entity.DataCenter;
import com.common.utils.ContextUtils;
import com.common.utils.EmptyUtils;
import com.common.widget.loadingView.LoadingLayout;
import com.common.widget.toast.ToastManager;
import com.hzxmkuar.sxmaketnew.R;
import com.xmkj.payandlogin.ShareConfig;
import com.xmkj.payandlogin.ShareManager;

import java.util.Iterator;
import java.util.List;


public class MainApplication extends ContextUtils {

    public static String mRegistrationId;

    @Override
    public void onCreate() {
        super.onCreate();
        oneInit();
        initApp();
        //环信
        //EaseUI.getInstance().init(this);
        //直播
        //StreamingEnv.init(getApplicationContext());

        //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了

    }

    public void oneInit() {
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        //默认的app会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就return掉
        if (processAppName == null || !processAppName.equalsIgnoreCase("com.example.leon")) {
            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
    }

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return processName;
    }

    private void initApp() {
        ToastManager.init(getAppContext());
        initLoadingView();
        initDataCenter();
        initThreeSDK();
    }

    // 初始化第三方SDK
    private void initThreeSDK() {
        ShareConfig config = ShareConfig.instance()
                .qqId("101431881")
                .weiboId("XXXXX")
                .wxId("wxd5542ca09b968dd0")
                .weiboRedirectUrl("XXXXXX")
                .wxSecret("aa90ede2e4d08ff23009eb1185fc4bda");
        ShareManager.init(config);
    }

    // 初始化本地数据存储
    private void initDataCenter() {
        if (EmptyUtils.isNull(DataCenter.getInstance())) {
            DataCenter.initDataCenter();
        }
    }

    // 初始化页面预加载View
    private void initLoadingView() {
        LoadingLayout.getConfig()
                .setEmptyText("暂无数据")
                .setNoNetworkText("无网络连接，请检查您的网络···")
                .setErrorImage(R.mipmap.loadingview_error)
                .setEmptyImage(R.mipmap.loadingview_empty)
                .setNoNetworkImage(R.mipmap.loadingview_error)
                .setAllTipTextColor(R.color.grey)
                .setAllTipTextSize(14)
                .setReloadButtonText("点我重试")
                .setReloadButtonTextSize(14)
                .setReloadButtonTextColor(R.color.grey)
                .setReloadButtonWidthAndHeight(150, 40)
                .setAllPageBackgroundColor(R.color.view_background_color);
//        .setLoadingPageLayout(R.layout.define_loading_page)
    }

}
