package com.zy.homework2.client;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author zhangy
 * @version 3.0
 * @description:
 * @email: zhangy2222z@sina.cn
 * @create 2020-10-28 15:33
 **/

public class HttpClientDemo {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientDemo.class);

    private static int MAX_TOTAL = 200 ;
    private static int DEFAULT_MAX_PERTOUTE =	50 ;
    private static int CONNECT_TIMEOUT = 5000 ;
    private static int CONNECT_REQUEST_TIMEOUT = 1500 ;
    private static int SOCKET_TIMEOUT = 20000 ;

    private static CloseableHttpClient httpClient;
    private static PoolingHttpClientConnectionManager cm  ;
    private static RequestConfig requestConfig;
    private static HttpClientBuilder httpClientBuilder;

    static {
        requestConfig = RequestConfig.custom()
                .setSocketTimeout(SOCKET_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(CONNECT_REQUEST_TIMEOUT)
                .build();
        cm = new PoolingHttpClientConnectionManager();
        //设置最大连接数
        cm.setMaxTotal(MAX_TOTAL);
        cm.setDefaultMaxPerRoute(DEFAULT_MAX_PERTOUTE);
        httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(cm);
        httpClient = httpClientBuilder.build();
    }

    public static void main(String[] args) {
        HttpGet httpGet = new HttpGet("http://localhost:8801/test");
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity he = response.getEntity();
                logger.info(EntityUtils.toString(he, "UTF-8"));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }





}
