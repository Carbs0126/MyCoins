package net.interceptor;

/**
 * okhttp头部验证
 */
public class AuthorizationInterceptor /*implements Interceptor*/ {

    /*public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Request.Builder builder = oldRequest.newBuilder();
        builder.addHeader("EBT-Authorization", getAuthorization());
        String token = getUserToken();
        if (!TextUtils.isEmpty(token))
            builder.addHeader("Token", getUserToken());
        builder.addHeader("User-Agent", getUserAgent());
        builder.url(addParam(oldRequest));
        Request request = builder.build();
        return chain.proceed(request);
    }

    private String getUserAgent() {
        return ConfigData.SIGN_APP_NAME + "/V" + UpdateService.getApkVersionName() + "/android";
    }

    public String getUserToken() {
        UserInfo userLicenceInfo = AppContext.getCurrentUser();
        return userLicenceInfo.getAccessToken();
    }

    public static String getAuthorization() {
        try {
            String deviceId = PrefKit.getString(AppContext.getInstance(), ConfigData.DEVICEID_KEY);
            String requestKey = MD5Util.encodeByMD5(deviceId + ConfigData.ORIGIN_KEY);
            long signTime = (System.currentTimeMillis() - ApiFactory.deltaTime);
            String sign = MD5Util.encodeByMD5(deviceId + signTime + requestKey);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("deviceId", deviceId);
            jsonObject.put("signTimestamp", signTime);
            jsonObject.put("signVersion", ConfigData.SIGN_VERSION);
            jsonObject.put("sign", sign);
            Logger.d("authorization", jsonObject.toString());
            return jsonObject.toString();
        } catch (Exception e) {
            Logger.e(e);
            return null;
        }
    }

    private HttpUrl addParam(Request oldRequest) {
        HttpUrl.Builder builder = oldRequest.url()
                .newBuilder()
                .setEncodedQueryParameter("deviceId", PrefKit.getString(AppContext.getInstance(), ConfigData.DEVICEID_KEY))
                .setEncodedQueryParameter("app_id", "zyj")
                .setEncodedQueryParameter("app_version", UpdateService.getApkVersionCode())
                .setEncodedQueryParameter("app_version_name", UpdateService.getApkVersionName())
                .setEncodedQueryParameter("app_source", EBTGetAdviceInfo.getMetadata(AppContext.getInstance(), "TD_CHANNEL_ID"))
                .setEncodedQueryParameter("app_os", EBTGetAdviceInfo.getSystemVersion());

        return builder.build();
    }*/
}
