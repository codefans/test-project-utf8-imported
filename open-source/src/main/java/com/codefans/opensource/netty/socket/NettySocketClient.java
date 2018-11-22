package com.codefans.opensource.netty.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import org.apache.rocketmq.common.protocol.RequestCode;
import org.apache.rocketmq.common.protocol.header.GetProducerConnectionListRequestHeader;
import org.apache.rocketmq.remoting.netty.NettyDecoder;
import org.apache.rocketmq.remoting.netty.NettyEncoder;
import org.apache.rocketmq.remoting.protocol.RemotingCommand;

import java.net.InetSocketAddress;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: ShengzhiCai
 * @date: 2018-11-22 15:50
 */
public class NettySocketClient {

    private EventLoopGroup eventLoopGroupWorker;

    private final DefaultEventExecutorGroup defaultEventExecutorGroup;

    /**
     * 启动类
     */
    private final Bootstrap bootstrap = new Bootstrap();

    Bootstrap handler;

    private final int PORT = 3456;

//    private final String HOST = "10.75.164.61";
    private final String HOST = "127.0.0.1";

//    private final String producerGroup = "";

    SocketClientHandler socketClientHandler;

    public NettySocketClient() {

        int clientWorkerThreads = Runtime.getRuntime().availableProcessors();
        defaultEventExecutorGroup = new DefaultEventExecutorGroup(
                clientWorkerThreads,
                new ThreadFactory() {
                    private AtomicInteger threadIndex = new AtomicInteger(0);
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "NettyClientWorkerThread_" + this.threadIndex.incrementAndGet());
                    }
                });

        eventLoopGroupWorker = new NioEventLoopGroup(1, new ThreadFactory() {
            private AtomicInteger threadIndex = new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, String.format("NettyClientSelector_%d", this.threadIndex.incrementAndGet()));
            }
        });

    }

    public static void main(String[] args) {
        NettySocketClient nettySocketClient = new NettySocketClient();
        nettySocketClient.startup();
    }

    public void startup() {

        this.init();

        /**
         * 创建一个ByteBuf
         */

        int msgCount = 2;
        String message = "";
        for(int i = 0; i < msgCount; i ++) {
            ByteBuf buf = Unpooled.buffer();
            message = "this is socket client message:" + (i + 1);
            buf.writeBytes(message.getBytes());
            sendMsg(buf);
        }

        eventLoopGroupWorker.shutdownGracefully();
        defaultEventExecutorGroup.shutdownGracefully();

    }

    public void init() {

        try {

            int connectTimeoutMillis = 3000;
            int socketSndbufSize = 65535;
            int socketRcvbufSize = 65535;
            final int clientChannelMaxIdleTimeSeconds = 120;

            socketClientHandler = new SocketClientHandler();

            handler = this.bootstrap.group(eventLoopGroupWorker).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeoutMillis)
                    .option(ChannelOption.SO_SNDBUF, socketSndbufSize)
                    .option(ChannelOption.SO_RCVBUF, socketRcvbufSize)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(
                                    defaultEventExecutorGroup,
//                                    new IdleStateHandler(0, 0, clientChannelMaxIdleTimeSeconds),
                                    socketClientHandler);
                        }
                    });

            this.sendMsg(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendMsg(ByteBuf byteBuf) {

        ChannelFuture channelFuture = this.bootstrap.connect(new InetSocketAddress(HOST, PORT));
        Channel channel = channelFuture.channel();

        ByteBuf buf = Unpooled.buffer();
        String message = "";
        message = "this is socket client message:" + (1 + 1);
        buf.writeBytes(message.getBytes());

        channel.writeAndFlush(buf).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture f) throws Exception {
                if (f.isSuccess()) {
                    System.out.println("send success");
                    return;
                } else {
                    System.out.println("send request ok=false");
                }
            }
        });

//        try {
//            Thread.sleep( 3 * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        channel.close().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                System.out.println("future.close():" + future.isSuccess());
            }
        });



    }



}
