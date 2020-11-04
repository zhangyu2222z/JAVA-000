package com.zy.homework3.gateway.filter;

import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author zhangy
 * @version 3.0
 * @description:
 * @email: zhangy2222z@sina.cn
 * @create 2020-11-04 10:27
 **/

public class CustomFilter {

    private static CustomFilter filter;

    private CustomFilter() {}

    public static CustomFilter getInstance(){
        if (filter == null) {
            filter = new CustomFilter();
        }
        return filter;
    }

    public void filter(FullHttpRequest fullRequest){
        fullRequest.headers().add("demoToken", "12321");
    }

}
