package net;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * okhttp缓存
 */
public class CacheInterceptor implements Interceptor {
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        /*if (!NetUtils.isNetworkConnected(AppContext.getInstance())) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }*/
        Response response = chain.proceed(request);
        // 有网络则不缓存
        response.newBuilder()
                .header("Cache-Control", "public, max-age=" + 0)
                .removeHeader("Pragma")
                .build();
        return response;
    }
}
