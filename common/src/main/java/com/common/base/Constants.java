package com.common.base;

import android.Manifest;

import com.common.retrofit.entity.DataCenter;
import com.common.utils.MD5Utils;
import com.common.utils.SPUtils;

import java.util.Collections;
import java.util.List;

/**
 * @desc: 项目基础常量 配置BaseUrl等信息
 * @author: Leo
 * @date: 2016/12/26
 */
public class Constants {
    public static String getHash(List<String> list) {
        list.add("time");
        list.add("apiId");
        list.add("terminal");
        Collections.sort(list);
        int j;
        String string = "";
        for (j = 0; j < list.size(); j++) {
            string += list.get(j);
        }
        return MD5Utils.MD5Encode(string + "56733fdb9a8dd961c90ae8dc6b8c67da");
    }

    public static String TOKEN = SPUtils.getShareString(DataCenter.TOKEN);
    public static String Token = "pBkBzBV4967_l2Py66HCC7Tf3JRGsVAul5bCIsBG:FSTsCni8aftZma9xXkzP-Ry7Sx0=:eyJjYWxsYmFja1VybCI6Imh0dHA6XC9cL3htYXAxODAzMDEzLnBocC5oenhtbmV0LmNvbVwvYXBpXC9DYWxsYmFja1wvcWluaXUiLCJjYWxsYmFja0JvZHkiOiJ7XCJrZXlcIjpcIiQoa2V5KVwiLFwiaGFzaFwiOlwiJChldGFnKVwiLFwiZnNpemVcIjokKGZzaXplKSxcImJ1Y2tldFwiOlwiJChidWNrZXQpXCIsXCJuYW1lXCI6XCIkKHg6bmFtZSlcIixcImRlc2NcIjpcIiQoeDpkZXNjKVwiLFwibWltZVR5cGVcIjpcIiQobWltZVR5cGUpXCIsXCJib2R5U2hhMVwiOlwiJChib2R5U2hhMSlcIn0iLCJjYWxsYmFja0JvZHlUeXBlIjoiYXBwbGljYXRpb25cL2pzb24iLCJzY29wZSI6InBpemhpYm8iLCJkZWFkbGluZSI6MTUyMzI2MjY2MX0=";

    public static final int REQUEST_CODE_MOBILE = 1221;                            // 获取手机信息
    public static final int REQUEST_CODE_PHOTO = 1211;                             // 获取图片回调
    public static final int REQUEST_CODE_CAMERA = 2121;                            // 获取拍照回调

    public static final int FILE_CODE_PERMISSION = 5002;                            // 文件读取权限
    public static final int LOCATION_CODE_PERMISSION = 5004;                        // 定位权限
    public static final String[] REQUECT_NEEDS = new String[]
            {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE};                         // 定位所有权限


    /**
     * 线上环境用
     */
    public static final String BaseUrl = "http://app.zhongxinyingjia.com/Api/";                   //  高兴超线上环境用
    /**
     * 线下环境测试用
     *
     */
//    public static final String BaseUrl = "http://test.zhongxinyingjia.com/Api/";                   //  高兴超线下测试环境用
//    public static final String BaseUrl = "http://app.zhongxinyingjia.com/Api/";                   //  高兴超线下测试环境用
    /**
     * 线下环境测试用
     */
//    public static final String BaseUrl = "http://192.168.0.141/Api/";                 //  张兆伦线下测试环境用
    /**
     *  ？？？这是用来干嘛的？
     */
    public static final String BaseUrls = "http://xmap1802009.php.hzxmnet.com/";         // 网络请求地址
    public static final int SERVER_RESP_TOKEN_EXPRIED = 100004;                        // Token失效标志量
    public static final long DEFAULT_TIMEOUT = 30;// 网络请求超时时间 单位为秒
    public static boolean istzUseNet = false;
    public static boolean isDownUseNet = false;
    public static final int SERVER_RESP_SUCCESS_CODE = 0;                           // 请求成功
    public static final String VideoFace = "http://img4.jiecaojingxuan.com/2016/11/23/00b026e7-b830-4994-bc87-38f4033806a6.jpg@!640_360";//视频的封面
    public static final String VideoName = "";                              //视频的标题
}