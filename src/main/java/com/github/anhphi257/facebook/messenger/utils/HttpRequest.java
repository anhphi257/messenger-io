package com.github.anhphi257.facebook.messenger.utils;

import okhttp3.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by phiha on 12/08/2017.
 */
public class HttpRequest {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient client;

    static {
        client = getUnsafeOkHttpClient();
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain,
                                                       String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain,
                                                       String authType) throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            return new OkHttpClient.Builder()
                    .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier((hostname, session) -> true).connectTimeout(
                            5, SECONDS)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String sendPost(String url, String json, Map<String, String> headers) throws IOException {
        if (headers == null) {
            headers = new HashMap<>();
        }
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .headers(Headers.of(headers))
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String postData(String url, Map<String, String> headers, Map<String, String> parameters) throws IOException {
        if (headers == null) {
            headers = new HashMap<>();
        }
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        StringBuilder sb = new StringBuilder();
        if (parameters != null) {
            for (String key : parameters.keySet()) {
                sb.append('&');
                sb.append(key);
                sb.append("=");
                sb.append(parameters.get(key));
            }
        }

        RequestBody body = RequestBody.create(mediaType, sb.substring(1));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .headers(Headers.of(headers))
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String get(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
