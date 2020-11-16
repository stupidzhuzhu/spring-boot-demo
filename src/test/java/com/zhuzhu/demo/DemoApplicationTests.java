package com.zhuzhu.demo;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class DemoApplicationTests {

    private static IHttpClient httpClient = new JsonHttpClient();

    @Test
    void contextLoads() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost("http://127.0.0.1:8081/callcenter/api/rest/internal/v1/configcenter/addIvrInfo");
            httpPost.addHeader("Content-Type","application/json");
            // 创建请求内容
            JSONObject json = new JSONObject();
            json.put("name", "test.mp4");
            json.put("path", "/zhuzhu/test");
            json.put("organzitionId", "654321");
            json.put("nemoNumber", "900900");
            StringEntity stringEntity = new StringEntity(json.toString(),"utf-8");
            httpPost.setEntity(stringEntity);
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

}
