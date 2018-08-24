package com.common.interceptor;
import android.content.Context;
import com.common.utils.APPUtil;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 *  添加请求头
 * Created by Administrator on 2018/8/24.
 */
public class HeaderIntercepter implements Interceptor {

    Context context;

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request().newBuilder()
                .addHeader("uuid", String.valueOf(APPUtil.getUniqueIMei()))
                .addHeader("versioncode", String.valueOf(APPUtil.getVersionCode(context)))
                .addHeader("types","2")
                .build();
        return chain.proceed(request);
    }

}
