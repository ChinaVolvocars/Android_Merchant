package com.common.retrofit.uploadfile.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.common.utils.LogUtils;
import com.qiniu.android.common.Zone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 初始化七牛文件上传管理器
 */
public class QiNiuInitialize {

    public static final String ACCESS_KEY = "8RBktiTzXhi0x3viPR2u02aSRMUb5A5Mscaj_aFQ";
    public static final String SECRET_KEY = "p0qlVQT7A4b1AApcpa13KUMx3gAS0K62Pq9iAT0_";
    /** 新建七牛空间的名字 */
    public static final String BUCKET_NAME = "yaoyaomedical";
    public static String ImageBaseUrl = "http://oq7e01g0r.bkt.clouddn.com/";

    private static UploadManager instance;

    //私有构造
    private QiNiuInitialize() {
    }

    //获取UploadManager
    public static UploadManager getSingleton() {
        if (instance == null) {
            synchronized (QiNiuInitialize.class) {
                if (instance == null) {
                    instance = new UploadManager(new Configuration.Builder()
                            .zone(Zone.zone2) // 设置区域为华北
                            .build());
                }
            }
        }
        return instance;
    }

    //上传单个文件
    public static void uploadFile(final String filePath, final UploadListener uploadListener) {
        if (filePath == null) return;
        new Thread(new Runnable() {
            @Override
            public void run() {
                QiNiuInitialize.getSingleton().put(filePath, null, getUpToken(),
                        new UpCompletionHandler() {
                            @Override
                            public void complete(String key, ResponseInfo respInfo, JSONObject jsonData) {

                                if (respInfo.isOK()) {
                                    String url = "";
                                    try {
                                        //图片的外链地址domain + key
                                        url = QiNiuInitialize.ImageBaseUrl + jsonData.getString("key");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    uploadListener.onUploadSuccess(url);

                                } else {
                                    uploadListener.onUploadFail(new Error("上传失败" + respInfo.error));
                                }
                            }

                        }, new UploadOptions(null, null, false,
                                new UpProgressHandler(){
                                    public void progress(String key, double percent){
                                        LogUtils.d((percent * 100) + "");
                                    }
                                }, null));
            }
        }).start();
    }

    //上传回调
    public interface UploadListener {
        void onUploadSuccess(String url);
        void onUploadFail(Error error);
    }

    public static String getFilePathFromContentUri(Uri selectedVideoUri,
                                            ContentResolver contentResolver) {
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};

        Cursor cursor = contentResolver.query(selectedVideoUri, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

    public static String getUpToken() {
        return Auth.create(ACCESS_KEY, SECRET_KEY).uploadToken(BUCKET_NAME);
    }
}
