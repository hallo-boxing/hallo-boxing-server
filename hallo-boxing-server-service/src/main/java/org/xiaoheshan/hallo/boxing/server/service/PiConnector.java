package org.xiaoheshan.hallo.boxing.server.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xiaoheshan.hallo.boxing.server.common.util.ThreadSleepUtil;

import javax.annotation.PostConstruct;

/**
 * @author : _Chf
 * @since : 03-25-2018
 */
@Component
public class PiConnector implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(PiConnector.class);

    private Integer port;

    private Channel channel;

    public PiConnector(@Value("${pi-connect.port:8266}") Integer port) {
        this.port = port;
    }

    @PostConstruct
    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        LOGGER.info("启动树莓派监听器，监听端口：{}", port);
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ChannelFuture future = new ServerBootstrap()
                    .group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(port)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new StringDecoder())
                                    .addLast(new ServerHandler());
                        }
                    })
                    .bind()
                    .sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException ignored) {
        } finally {
            try {
                group.shutdownGracefully().sync();
            } catch (InterruptedException ignored) {
            }
        }
    }

    public boolean send(String message) {
        LOGGER.info("向树莓派发送消息：{}", message);
        if (channel != null) {
            for (int i = 0; i < 2; i++) {
                if (channel.isWritable()) {
                    channel.writeAndFlush(Unpooled.copiedBuffer(message, CharsetUtil.UTF_8));
                    LOGGER.info("向树莓派发送消息成功");
                    return true;
                }
                ThreadSleepUtil.sleep(1000);
            }
        }
        LOGGER.warn("向树莓派发送消息失败");
        return false;
    }

    private class ServerHandler extends SimpleChannelInboundHandler<String> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
            LOGGER.info("接收到树莓派的消息：{}", msg);
            //TODO 放入消息队列
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            super.channelActive(ctx);
            channel = ctx.channel();
            LOGGER.info("树莓派连接成功，IP：{}", ctx.channel().remoteAddress());
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            super.channelInactive(ctx);
            LOGGER.info("树莓派断开连接");
            if (channel == ctx.channel()) {
                channel = null;
            }
        }
    }

}
