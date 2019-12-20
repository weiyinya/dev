package com.wy.http;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author weiyin
 * @date 2019-10-09 18:12
 *         //请求方式
 *         connection.setRequestMethod("GET");
 *         //是否使用缓存。如果是的话，协议尽量使用缓存（如果有的话）
 *         connection.setUseCaches(false);
 *         //是否允许用户交互
 *         connection.setAllowUserInteraction(false);
 *         //连接超时时间
 *         connection.setConnectTimeout(1000);
 *         //允许 读取数据
 *         connection.setDoInput(true);
 *         //允许 写入数据
 *         connection.setDoOutput(true);
 *         //???
 *         connection.setIfModifiedSince(1000);
 *         //读超时时间
 *         connection.setReadTimeout(1000);
 *         //协议的header
 *         connection.setRequestProperty("Content-Type", "application/json");
 */
public class UrlConnectionT {
    public static void main(String[] args) throws IOException {

    }

    /**
     * get请求
     */
    @Test
    void testGet() throws IOException {
        String urll = "https://www.baidu.com/";
        URL url = new URL(urll);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        connection.connect();
        //得到响应码
        int responseCode = connection.getResponseCode();

        if(responseCode == HttpURLConnection.HTTP_OK){
            //得到响应流
            InputStream inputStream = connection.getInputStream();
            System.out.println(inputToStr(inputStream));
        }
    }

    /**
     * post请求
     */
    @Test
    void testPost() throws IOException {
        String urll = "https://www.baidu.com/";
        URL url = new URL(urll);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        //是否使用缓存。如果是的话，协议尽量使用缓存（如果有的话）
        connection.setUseCaches(false);
        //是否允许用户交互
        connection.setAllowUserInteraction(false);
        //允许 读取数据
        connection.setDoInput(true);
        //允许 写入数据
        connection.setDoOutput(true);

        connection.connect();

        /**
         * 连接后写入post数据
         */
        String body = "userName=zs&password=123456";
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
        writer.write(body);
        writer.close();

        //得到响应码
        int responseCode = connection.getResponseCode();

        if(responseCode == HttpURLConnection.HTTP_OK){
            //得到响应流
            InputStream inputStream = connection.getInputStream();
            System.out.println(inputToStr(inputStream));
        }
    }


    /**
     * 像文件传输之类的与上述post类似，修改一些header的请求方式和写入内容即可
     */


    private String inputToStr(InputStream inputStream) throws IOException {
        //将响应流转换成字符串
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString(StandardCharsets.UTF_8.name());
    }
}
