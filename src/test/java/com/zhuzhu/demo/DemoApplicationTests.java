package com.zhuzhu.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.zhuzhu.demo.configcenter.pojo.ConfMgmtResp;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class DemoApplicationTests {

    private static IHttpClient httpClient = new JsonHttpClient();
    private static Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.STATIC).create();

    @Test
    void contextLoads() {
//        getTest();
        jsonObjTest();
    }

    private void jsonObjTest() {
        String json = "{\"code\":0,\"data\":{\"meetingId\":\"101-bj2-txdevSig2-2157599596760\",\"extraInfo\":\"{\\\"type\\\":\\\"CCC\\\",\\\"customizing\\\":\\\"\\\",\\\"ccNum\\\":\\\"881234\\\",\\\"ivrServerId\\\":\\\"172.25.48.219\\\"}\"}}";
        ConfMgmtResp confMgmtResp = gson.fromJson(json, ConfMgmtResp.class);
//        String json2 = "909090";
//        Object json3 = gson.fromJson(json2, Object.class);
        System.out.println(confMgmtResp);

//        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        Integer code = confMgmtResp.getCode();
        JsonObject data = confMgmtResp.getData();
        System.out.println(code);
        System.out.println(data);
    }

    private void getTest() {
        CloseableHttpClient httpClient = buildHttpClient();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpGet httpGet = new HttpGet("http://127.0.0.1:80/callcenter/api/rest/internal/v1/configcenter/enterprises_count");

            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(8000)
                    .build();

            httpGet.setConfig(requestConfig);
            long start = System.currentTimeMillis();
            System.out.println("start time:" + start + "ms");
            response = httpClient.execute(httpGet);

            long end = System.currentTimeMillis();
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("result:" + resultString + ", takes:" + (end - start) + "ms");
        } catch (Exception e) {

            System.out.println("end time:" + System.currentTimeMillis() + "ms");
            System.out.println(response);
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("send get req failed");
            }
        }
        System.out.println("send get req succeed");
    }

    private void postTest() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost("http://172.25.80.15:80/callcenter/api/rest/internal/v1/configcenter/addIvrInfo");

            httpPost.addHeader("Content-Type","application/json");
            // 创建请求内容
            JSONObject json = new JSONObject();
            json.put("name", "test.mp4");
            json.put("path", "/zhuzhu/test");
            json.put("enterpriseId", "654321");
//            json.put("nemoNumber", "900900");
//            JSONObject data = new JSONObject();
//            data.put("data", json.toString());
//            List<String> list = new ArrayList<>();
//            list.add("a");
//            list.add("b");
//            data.put("list", list);
            StringEntity stringEntity = new StringEntity(json.toString(),"utf-8");
            httpPost.setEntity(stringEntity);
            System.out.println("string entity: " + json.toString());
//            System.out.println("data: " + data);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("result:" + resultString);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("send post req failed");
            }
        }
        System.out.println("send post req succeed");
    }

    private CloseableHttpClient buildHttpClient() {
        try {
            Registry<ConnectionSocketFactory> schemeRegistry =
                    RegistryBuilder.<ConnectionSocketFactory>create()
                            .register("http", PlainConnectionSocketFactory.getSocketFactory())
                            .register("https", SSLConnectionSocketFactory.getSocketFactory())
                            .build();

            PoolingHttpClientConnectionManager connectionManager =
                    new PoolingHttpClientConnectionManager(schemeRegistry);
            connectionManager.setMaxTotal(200);
            connectionManager.setDefaultMaxPerRoute(20);

            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(1000)
                    .setSocketTimeout(5000)
                    .setConnectTimeout(1000)
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

    @Test
    void jsonTest() {
        Gson mGson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.STATIC).create();

        JsonObject jsonObject = new JsonObject();
        JsonObject jsonObject2 = new JsonObject();
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        jsonObject.addProperty("list", list.toString());
        jsonObject2.addProperty("uri", "12345@HARD");
        jsonObject2.addProperty("confUri", "90012345@CONFNO");
        jsonObject.addProperty("map", jsonObject2.toString());
        jsonObject.addProperty("allow", true);
        jsonObject.addProperty("count", 3);

        System.out.println(jsonObject.toString());

        String str = jsonObject.toString();
        JsonObject json = mGson.fromJson(str, JsonObject.class);
        JsonElement element = json.get("count");
        System.out.println(element.toString());
    }

    @Test
    void asyncTest() {
        try {
            System.out.println(Thread.currentThread().getName() + ", time:" + System.currentTimeMillis() + ", start testAsync!!!");
            new Thread() {
                @Override
                public void run() {
                    CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                        }
                        System.out.println(Thread.currentThread().getName() + ", time1:" + System.currentTimeMillis() + ", run1 end!!!");
                    });

//                    future.get();
                    System.out.println(Thread.currentThread().getName() + ", time1:" + System.currentTimeMillis());
                }
            }.start();

            new Thread(() -> {
                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                    }
                    System.out.println(Thread.currentThread().getName() + ", time2:" + System.currentTimeMillis() + ", run2 end!!!");
                });

//                    future.get();
                System.out.println(Thread.currentThread().getName() + ", time2:" + System.currentTimeMillis());

            }).start();
//            runAsync();
//            supplyAsync();
            TimeUnit.SECONDS.sleep(8);
            System.out.println(Thread.currentThread().getName() + ", time:" + System.currentTimeMillis() + ", end testAsync!!!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //无返回值
    public static void runAsync() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + ", time:" + System.currentTimeMillis() + ", run end!!!");
        });

        future.get();
        System.out.println(Thread.currentThread().getName() + ", time:" + System.currentTimeMillis());
    }

    //有返回值
    public static void supplyAsync() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + ", time:" + System.currentTimeMillis() + ", supply end!!!");
            return System.currentTimeMillis();
        });

        long time = future.get();
        System.out.println(Thread.currentThread().getName() + ", time:" + System.currentTimeMillis() + ", time=" + time);
    }

}
