package net.api;

import constants.Constant;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Andy.Wang on 2016/3/9.
 */
public class ApiFactory {

    private net.api.Api api;

    private static final int TIMEOUT_READ = 20;
    private static final int TIMEOUT_CONNECTION = 10;
    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private net.interceptor.CacheInterceptor cacheInterceptor = new net.interceptor.CacheInterceptor();
//    private AuthorizationInterceptor authorizationInterceptor = new AuthorizationInterceptor();

    public static <T> T createApiWithoutAuth(Class<T> clazz, String url) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
            OkHttpClient okHttpLogClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();
            builder.client(okHttpLogClient);
        Retrofit retrofit = builder
                .build();
        return retrofit.create(clazz);
    }


    private static class SingletonHolder {
        private static final ApiFactory INSTANCE = new ApiFactory();
    }

    public static ApiFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private ApiFactory() {
        api = createApiWithoutAuth(net.api.Api.class, Constant.NetConfig.BaseUrl);
    }

    public net.api.Api getEbtAPI() {
        return api;
    }

    /*private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
                .addNetworkInterceptor(cacheInterceptor)
                .addInterceptor(cacheInterceptor)
                .addInterceptor(authorizationInterceptor)
                .cache(HttpCache.getCache())
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        if (BuildConfig.ISDEBUG)
            builder.addInterceptor(interceptor);
        OkHttpClient okHttpClient = builder.build();
        return okHttpClient;
    }*/
}
