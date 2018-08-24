package com.common.retrofit.uploadfile;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;

public class UploadFileUtils {

    /**
     * 将文件路径数组封装为{@link List<MultipartBody.Part>}
     * @param key 对应请求正文中name的值。目前服务器给出的接口中，所有图片文件使用<br>
     * 同一个name值，实际情况中有可能需要多个
     * @param filePaths 文件路径数组
     */
    public static <T> List<MultipartBody.Part> files2Parts(String key, String[] filePaths, Subscriber<T> subscribe)
    {
        List<MultipartBody.Part> parts = new ArrayList<>(filePaths.length);
        for (String filePath : filePaths) {
            File file = new File(filePath);
            // 根据类型及File对象创建RequestBody（okhttp的类）
            UploadFileRequestBody requestBody = new UploadFileRequestBody(file, subscribe);
            // 将RequestBody封装成MultipartBody.Part类型（同样是okhttp的）
            MultipartBody.Part part = MultipartBody.Part.
                    createFormData(key, file.getName(), requestBody);
            // 添加进集合
            parts.add(part);
        }
        return parts;
    }

    public static void addFormParams(Map<String, RequestBody> map, String key, String value) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), value);
        map.put(key, requestBody);
    }
}
