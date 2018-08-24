package com.common.retrofit.methods;

import com.common.retrofit.base.BaseMethods;
import com.common.retrofit.entity.result.PicBean;
import com.common.retrofit.service.UserService;
import com.common.retrofit.uploadfile.UploadFileUtils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;

public class UploadMethods extends BaseMethods {

    private static UploadMethods m_ins = null;

    public static UploadMethods getInstance() {
        if (m_ins == null) {
            synchronized (UploadMethods.class) {
                if (m_ins == null) {
                    m_ins = new UploadMethods();
                }
            }
        }
        return m_ins;
    }

    @Override
    protected String getHttpUrl() {
        return "Upload/";
    }

    private UserService initService() {
        return getRetrofit().create(UserService.class);
    }

    // 修改用户头像
    public void photoUpload(Subscriber<PicBean> subscriber, String logo) {
        Map<String, RequestBody> map = new HashMap<>();
        UploadFileUtils.addFormParams(map, "hash", "03e302fce391af2497f499e28c30061b");
        UploadFileUtils.addFormParams(map, "time", System.currentTimeMillis() + "");
        UploadFileUtils.addFormParams(map, "uploadname", "uploadname");

        Observable observable = initService().uploadFace(map, UploadFileUtils.files2Parts("uploadname", new String[]{logo}, subscriber));
        toOtherSubscribe(observable, subscriber);
    }
}