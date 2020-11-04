package com.zy.homework3.gateway.outbound;

import com.zy.homework3.gateway.filter.CustomFilter;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.AttributeKey;

import java.net.URI;
import java.net.URISyntaxException;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author zhangy
 * @version 3.0
 * @description:
 * @email: zhangy2222z@sina.cn
 * @create 2020-11-03 16:46
 **/

public class OutboundHandler {

    private Bootstrap b;

    public OutboundHandler(){
    }

    public String handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final String backendUrl) {
        try {
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            ClientOutboundHandler clientOutboundHandler = new ClientOutboundHandler(ctx);

            b.handler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel ch) throws Exception {
                    //字符串编码器，一定要加在SimpleClientHandler 的上面
                    ch.pipeline().addLast(new HttpResponseDecoder());
//                客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
                    ch.pipeline().addLast(new HttpRequestEncoder());
                    ch.pipeline().addLast(clientOutboundHandler);
                }

            });
            // TODO: route
            String[] split = backendUrl.split(":");
            ChannelFuture f = b.connect(split[1].replace("//", ""), Integer.parseInt(split[2])).sync();
            URI uri = new URI(backendUrl);
            DefaultFullHttpRequest request = new DefaultFullHttpRequest(
                    HttpVersion.HTTP_1_1, HttpMethod.POST, uri.toASCIIString(),
                    Unpooled.wrappedBuffer("test".getBytes()));
            CustomFilter.getInstance().filter(request);
            f.channel().writeAndFlush(request);
            f.channel().closeFuture().sync();
            AttributeKey<String> key = AttributeKey.valueOf("ServerData");
            Object result = f.channel().attr(key).get();
            return result.toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }  catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

}

class ClientOutboundHandler extends ChannelInboundHandlerAdapter {
    private ByteBufToBytes reader;

    private ChannelHandlerContext mainCtx;

    public ClientOutboundHandler () {}

    public ClientOutboundHandler(ChannelHandlerContext ctx) {
        this.mainCtx = ctx;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx)
            throws Exception {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        if (msg instanceof HttpResponse) {
            HttpResponse response = (HttpResponse) msg;
            System.out.println("CONTENT_TYPE:"
                    + response.headers().get(HttpHeaderNames.CONTENT_TYPE));
            if (HttpUtil.isContentLengthSet(response)) {
                reader = new ByteBufToBytes(
                        (int) HttpUtil.getContentLength(response));
            }
        }
        if (msg instanceof HttpContent) {
            HttpContent httpContent = (HttpContent) msg;
            ByteBuf content = httpContent.content();
            reader.reading(content);
            content.release();
            if (reader.isEnd()) {
                String resultStr = new String(reader.readFull());
                FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(resultStr.getBytes("UTF-8")));
                response.headers().set("Content-Type", "application/json");
                response.headers().setInt("Content-Length", response.content().readableBytes());
//                response.headers().set(CONNECTION, KEEP_ALIVE);

                AttributeKey<String> key = AttributeKey.valueOf("ServerData");
                ctx.channel().attr(key).set(resultStr);

//                mainCtx.write(response);
//                mainCtx.flush();
//                ctx.write(response);
                ctx.close();
            }
        }
    }
}


class ByteBufToBytes {
    private ByteBuf temp;
    private boolean end = true;
    public ByteBufToBytes(int length) {
        temp = Unpooled.buffer(length);
    }
    public void reading(ByteBuf datas) {
        datas.readBytes(temp, datas.readableBytes());
        if (this.temp.writableBytes() != 0) {
            end = false;
        } else {
            end = true;
        }
    }
    public boolean isEnd() {
        return end;
    }
    public byte[] readFull() {
        if (end) {
            byte[] contentByte = new byte[this.temp.readableBytes()];
            this.temp.readBytes(contentByte);
            this.temp.release();
            return contentByte;
        } else {
            return null;
        }
    }
    public byte[] read(ByteBuf datas) {
        byte[] bytes = new byte[datas.readableBytes()];
        datas.readBytes(bytes);
        return bytes;
    }
}

