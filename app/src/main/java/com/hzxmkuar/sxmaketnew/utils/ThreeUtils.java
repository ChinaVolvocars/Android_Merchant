package com.hzxmkuar.sxmaketnew.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import static android.content.Context.BATTERY_SERVICE;
import static android.content.Context.WIFI_SERVICE;

/**
 * Created by STH on 2017/8/3.
 */

public class ThreeUtils {

    private static int mPercent = -1;
    private static ThreeUtils threeUtils;
    private int mRssi = -1;

    /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private ThreeUtils(Context context) {
        BatteryManager batteryManager=(BatteryManager)context.getSystemService(BATTERY_SERVICE);
        mPercent = batteryManager.getIntProperty(BatteryManager.BATTERY_PLUGGED_WIRELESS);
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_NETWORK_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_NETWORK_STATE},
                    0);
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService(WIFI_SERVICE);
        mRssi = wifiManager.getConnectionInfo().getRssi();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static ThreeUtils getInstance(Context context) {
        if (threeUtils == null) {
            threeUtils = new ThreeUtils(context);
        }
        //创建广播
        return threeUtils;
    }
    //获取电量
    public float getBatteryQuantity(){
        float num=(float)(Math.round(mPercent*1)/100.00);
        return num;
    }
    public  int  getNetworkType(Context mContext) {
        ConnectivityManager connMgr = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi =connMgr.getActiveNetworkInfo();
        //检查网络连接
        NetworkInfo info = connMgr.getActiveNetworkInfo();
        if (wifi != null && wifi.getType() == ConnectivityManager.TYPE_WIFI) {
            return 1;
        }else if (info == null || !connMgr.getBackgroundDataSetting()) {
            return 0;
        } else{
            return 2;
        }
    }
    public int getWIFIInfo(){
        if (mRssi<=-100){
            return 0;
        }else if (mRssi>-100&&mRssi<=-80){
            return 1;
        }else if (mRssi>-80&&mRssi<=-70){
            return 2;
        }else if (mRssi>-70&&mRssi<=-50){
            return 3;
        }else{
            return 4;
        }
    }
}
