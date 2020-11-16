package com.zhuzhu.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Modifier;

public class JsonHttpClient extends HttpClientMock {
    private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 200;
    private static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 20;
    private static final int CONNECT_TIMEOUT = (3 * 1000);
    private static final int SOCKET_TIMEOUT = (8 * 1000);
    private static final int CONNECTION_REQUEST_TIMEOUT = (3 * 1000);
    private static Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.STATIC).create();

    private static HttpClient httpClient = null;

    public JsonHttpClient() {
    }

    @Override
    protected HttpClient httpClient() {

        if (this.httpClient == null) {
            this.httpClient = build();
        }

        return this.httpClient;
    }

    @Override
    protected <T> T fromResponse(HttpResponse response, Class<T> clazz) throws IOException {
        String json = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
        System.out.println("response entity:{}" + json);
        return gson.fromJson(json, clazz);
    }

    @Override
    protected <T> HttpEntity toEntity(T t) {
        String json = gson.toJson(t);
        System.out.println("request entity:{}" + json);
        return new StringEntity(json, Consts.UTF_8);
    }

    private CloseableHttpClient build() {

        try {
            Registry<ConnectionSocketFactory> schemeRegistry =
                    RegistryBuilder.<ConnectionSocketFactory>create()
                            .register("http", PlainConnectionSocketFactory.getSocketFactory())
                            .register("https", SSLConnectionSocketFactory.getSocketFactory())
                            .build();

            PoolingHttpClientConnectionManager connectionManager =
                    new PoolingHttpClientConnectionManager(schemeRegistry);
            connectionManager.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);
            connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTIONS_PER_ROUTE);

            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                    .setSocketTimeout(SOCKET_TIMEOUT)
                    .setConnectTimeout(CONNECT_TIMEOUT)
                    .build();

            return HttpClientBuilder.create()
                    .setConnectionManager(connectionManager)
                    .setDefaultRequestConfig(requestConfig)
                    .setRetryHandler((exception, executionCount, context) -> false)
                    .build();
        } catch (Exception ex) {
            System.out.println("build CloseableHttpClient catch exception:" + ex);
            return null;
        }
    }
}
