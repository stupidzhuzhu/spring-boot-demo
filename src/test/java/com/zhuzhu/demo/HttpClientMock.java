package com.zhuzhu.demo;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class HttpClientMock implements IHttpClient {
    private final String siteCode = "8001";
    private AtomicInteger confNo = new AtomicInteger(10086);

    @Override
    public <T> T get(String url, Map<String, String> parameters, Map<String, String> headers, Class<T> clazz) {
        HttpResponse response = null;
        try {
            url = appendParam(url, parameters);
            HttpGet httpGet = new HttpGet(url);
            if (headers != null) {
                headers.forEach((k, v) -> httpGet.addHeader(k, v));
            }
            response = httpClient().execute(httpGet);
            System.out.println("http post invoked,url=" + url + ", status=" + response.getStatusLine().getStatusCode());
            if (isOk(response.getStatusLine())) {
                return fromResponse(response, clazz);
            }
            throw new RuntimeException("http get invoke fail");
        } catch (Exception e) {
            throw new RuntimeException("http get invoke fail", e);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                System.out.println("consume HttpEntity error:" + ex);
            }
        }    }

    @Override
    public <T> T put(String url, Map<String, String> parameters, Map<String, String> headers, Object entity, Class<T> clazz) {
        HttpResponse response = null;
        try {
            url = appendParam(url, parameters);
            HttpPut httpPut = new HttpPut(url);
            if (entity != null) {
                httpPut.setEntity(toEntity(entity));
            }
            if (headers != null) {
                headers.forEach((k, v) -> httpPut.addHeader(k, v));
            }
            response = httpClient().execute(httpPut);
            System.out.println("http post invoked,url=" + url + ", status=" + response.getStatusLine().getStatusCode());
            if (isOk(response.getStatusLine())) {
                return fromResponse(response, clazz);
            }
            throw new RuntimeException("http put invoke fail");
        } catch (Exception e) {
            throw new RuntimeException("http put invoke fail", e);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
                System.out.println("consume HttpEntity error:" + ex);
            }
        }    }

    @Override
    public <T> T post(String url, Map<String, String> parameters, Map<String, String> headers, Object entity, Class<T> clazz) {
        HttpResponse response = null;
        try {
            url = appendParam(url, parameters);
            HttpPost httpPost = new HttpPost(url);
            if (entity != null) {
                httpPost.setEntity(toEntity(entity));
            }
            if (headers != null) {
                headers.forEach((k, v) -> httpPost.addHeader(k, v));
            }
            response = httpClient().execute(httpPost);
            System.out.println("http post invoked,url=" + url + ", status=" + response.getStatusLine().getStatusCode());
            if (isOk(response.getStatusLine())) {
                return fromResponse(response, clazz);
            }
            throw new RuntimeException("http post invoke fail");
        } catch (Exception e) {
            throw new RuntimeException("http post invoke fail. url:" + url, e);
        } finally {
            try {
                if (response != null) {
                    EntityUtils.consume(response.getEntity());
                }
            } catch (Exception ex) {
            }
        }
    }

    protected String appendParam(String url, Map<String, String> param) {
        StringBuffer sb = new StringBuffer(url);
        if (param != null && param.size() > 0) {
            char prefix = url.contains("?") ? '&' : '?';
            boolean isFirst = true;
            Iterator<Map.Entry<String, String>> it = param.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                sb.append(isFirst ? prefix : "&");
                sb.append(entry.getKey()).append("=").append(entry.getValue());
                isFirst = false;
            }
        }
        return sb.toString();
    }
    protected abstract HttpClient httpClient();

    protected abstract <T> T fromResponse(HttpResponse response, Class<T> clazz) throws IOException;

    protected abstract <T> HttpEntity toEntity(T t);

    protected boolean isOk(StatusLine status) {
        return status.getStatusCode() / 100 == 2;
    }

}
