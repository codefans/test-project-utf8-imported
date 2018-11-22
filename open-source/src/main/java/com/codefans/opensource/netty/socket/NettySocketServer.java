package com.codefans.opensource.netty.socket;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;

import java.net.InetSocketAddress;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: ShengzhiCai
 * @date: 2018-11-22 15:50
 */
public class NettySocketServer {

    public static void main(String[] args) {
        NettySocketServer nettySocketServer = new NettySocketServer();
        nettySocketServer.startup();
    }

    public void startup() {

        try {

            int serverListenPort = 3456;
            int socketSndbufSize = 65535;
            int socketRcvbufSize = 65535;
            final int serverChannelMaxIdleTimeSeconds = 120;

            int serverWorkerThreadCount = Runtime.getRuntime().availableProcessors();
            final int serverSelectorThreadCount = serverWorkerThreadCount;

            final DefaultEventExecutorGroup defaultEventExecutorGroup = new DefaultEventExecutorGroup(
                    serverWorkerThreadCount,
                    new ThreadFactory() {
                        private AtomicInteger threadIndex = new AtomicInteger(0);
                        @Override
                        public Thread newThread(Runnable r) {
                            return new Thread(r, "NettyServerCodecThread_" + this.threadIndex.incrementAndGet());
                        }
                    }
            );

            ServerBootstrap serverBootstrap = new ServerBootstrap();

            EventLoopGroup eventLoopGroupBoss = new NioEventLoopGroup(1, new ThreadFactory() {
                private AtomicInteger threadIndex = new AtomicInteger(0);
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, String.format("NettyBoss_%d", this.threadIndex.incrementAndGet()));
                }
            });

            EventLoopGroup eventLoopGroupSelector = new NioEventLoopGroup(serverSelectorThreadCount, new ThreadFactory() {
                private AtomicInteger threadIndex = new AtomicInteger(0);
                private int threadTotal = serverSelectorThreadCount;
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, String.format("NettyServerNIOSelector_%d_%d", threadTotal, this.threadIndex.incrementAndGet()));
                }
            });

            ServerBootstrap childHandler =
                    serverBootstrap.group(eventLoopGroupBoss, eventLoopGroupSelector)
//                            .channel(useEpoll() ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                            .channel(NioServerSocketChannel.class)
                            .option(ChannelOption.SO_BACKLOG, 1024)
                            .option(ChannelOption.SO_REUSEADDR, true)
                            .option(ChannelOption.SO_KEEPALIVE, true)
                            .childOption(ChannelOption.TCP_NODELAY, true)
                            .childOption(ChannelOption.SO_SNDBUF, socketSndbufSize)
                            .childOption(ChannelOption.SO_RCVBUF, socketRcvbufSize)
                            .localAddress(new InetSocketAddress(serverListenPort))
                            .childHandler(new ChannelInitializer<SocketChannel>() {
                                @Override
                                public void initChannel(SocketChannel ch) throws Exception {
                                    ch.pipeline()
                                            .addLast(defaultEventExecutorGroup,
                                                    new IdleStateHandler(0, 0, serverChannelMaxIdleTimeSeconds),
                                                    new SocketServerHandler()
                                            );
                                }
                            });

            ChannelFuture sync = serverBootstrap.bind().sync();
            InetSocketAddress addr = (InetSocketAddress) sync.channel().localAddress();
            int port = addr.getPort();
            System.out.println("port=" + port);







        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
