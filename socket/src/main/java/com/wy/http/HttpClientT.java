package com.wy.http;

import lombok.var;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * apache httpclient
 * @author weiyin
 * @date 2019-10-10 18:00
 */
public class HttpClientT {

    /**
     * get请求
     */
    @Test
    void testGet() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.taobao.com/");
        CloseableHttpResponse response1 = httpclient.execute(httpGet);
        try {
            System.out.println(response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();
            String response= EntityUtils.toString(entity1,"utf-8");
            System.out.println(response);
            //必须完全的consume，否则connection manager可能无法复用连接
            EntityUtils.consume(entity1);
        } finally {
            //必须close response ， 否则无法释放持有的connection
            response1.close();
        }
    }

    /**
     * post请求
     */
    @Test
    void testPost() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://www.taobao.com/");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("username", "vip"));
        nvps.add(new BasicNameValuePair("password", "secret"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse response2 = httpclient.execute(httpPost);

        try {
            System.out.println(response2.getStatusLine());
            HttpEntity entity2 = response2.getEntity();
            String response=EntityUtils.toString(entity2,"utf-8");
            System.out.println(response);
            EntityUtils.consume(entity2);
        } finally {
            response2.close();
        }
    }
}
