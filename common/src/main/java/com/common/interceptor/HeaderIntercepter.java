package com.common.interceptor;

import com.common.utils.APPUtil;
import com.common.utils.ContextUtils;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *  添加请求头
 * Created by Administrator on 2018/8/24.
 */
public class HeaderIntercepter implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request().newBuilder()
                .addHeader("uuid", String.valueOf(APPUtil.getUniqueIMei()))
                .addHeader("versioncode", String.valueOf(APPUtil.getVersionCode(ContextUtils.getAppContext())))
                .addHeader("types","2")
                .addHeader("is_shop","1")
                .build();
        return chain.proceed(request);
    }

}
