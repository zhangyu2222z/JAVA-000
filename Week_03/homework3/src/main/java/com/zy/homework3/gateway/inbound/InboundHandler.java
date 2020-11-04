package com.zy.homework3.gateway.inbound;

import com.zy.homework3.gateway.outbound.OutboundHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author zhangy
 * @version 3.0
 * @description:
 * @email: zhangy2222z@sina.cn
 * @create 2020-11-03 16:42
 **/

public class InboundHandler extends ChannelInboundHandlerAdapter {
    private final String proxyServer;
    private OutboundHandler handler;

    public InboundHandler(String proxyServer) {
        this.proxyServer = proxyServer;
        handler = new OutboundHandler();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            String resultStr = handler.handle(fullRequest, ctx, this.proxyServer);
            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(resultStr.getBytes("UTF-8")));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());
            ctx.write(response);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
