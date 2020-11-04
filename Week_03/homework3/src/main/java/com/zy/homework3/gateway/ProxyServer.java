package com.zy.homework3.gateway;

import com.zy.homework3.gateway.inbound.InboundHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author zhangy
 * @version 3.0
 * @description:
 * @email: zhangy2222z@sina.cn
 * @create 2020-10-28 11:15
 **/

public class ProxyServer {

    private String serverUrl;
    private int port;

    public ProxyServer () {
    }

    public ProxyServer (final int port, final String serverUrl) {
        this.serverUrl = serverUrl;
        this.port = port;
    }

    public void boot(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).option(ChannelOption.SO_BACKLOG, 128).option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true).option(ChannelOption.SO_REUSEADDR, true)
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024).option(ChannelOption.SO_SNDBUF, 32 * 1024)
                    .option(EpollChannelOption.SO_REUSEPORT, true).childOption(ChannelOption.SO_KEEPALIVE, true)
                    .channel(NioServerSocketChannel.class).childHandler(new NettyInitializer(serverUrl));
            Channel ch = b.bind(port).sync().channel();
            ch.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args){
        ProxyServer server = new ProxyServer(8801, "http://localhost:8808");
        server.boot();
    }

}

class NettyInitializer extends ChannelInitializer<SocketChannel> {

    private String serverUrl;

    public NettyInitializer(){}
    public NettyInitializer(final String serverUrl){
        this.serverUrl = serverUrl;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();
        p.addLast(new HttpServerCodec());
        p.addLast(new HttpObjectAggregator(1024 * 1024));
        p.addLast(new InboundHandler(serverUrl));
    }
}
