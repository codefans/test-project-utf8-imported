package com.codefans.opensource.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.CharsetUtil;

/**
 * @author: ShengzhiCai
 * @date: 2018-10-05 11:44
 */
public class NettyHttpServer {

    public static void main(String[] args) {

        String inetHost = "localhost";
        int inetPort = 6789;

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("http-decoder",new HttpRequestDecoder());
                        ch.pipeline().addLast("http-aggregator",new HttpObjectAggregator(65535));//将多个消息转化成一个
                        ch.pipeline().addLast("http-encoder",new HttpResponseEncoder());
                        ch.pipeline().addLast("http-chunked",new ChunkedWriteHandler());//解决大码流的问题
                        ch.pipeline().addLast("http-server",new HttpHandler());
                    }

                });
        ChannelFuture future = bootstrap.bind(inetHost,inetPort);
        try {
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private static class HttpHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext channelHandlerContext, Object object) throws Exception {

            if(object instanceof FullHttpRequest) {
                this.messageReceived(channelHandlerContext, (FullHttpRequest)object);
            } else if(object instanceof  FullHttpResponse) {
                System.out.println("object is FullHttpResponse!");
            }

//            FullHttpResponse response = new DefaultFullHttpResponse(
//                    HttpVersion.HTTP_1_1,
//                    HttpResponseStatus.OK,
//                    Unpooled.wrappedBuffer("欢迎光临".getBytes("utf-8")));
//            response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain;charset=UTF-8");
//            response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, response.content().readableBytes());
//            response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
//            channelHandlerContext.write(response);
//            channelHandlerContext.flush();
        }

        protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
            try {
                ByteBuf content = msg.content();
                byte[] bts = new byte[content.readableBytes()];
                content.readBytes(bts);
                System.out.println(content.toString(CharsetUtil.UTF_8));

                String result = null;
                if(msg.getMethod() == HttpMethod.GET) {
                    String url = msg.getUri().toString();
                    result = "get method and paramters is "+ url.substring(url.indexOf("?")+1);
                    System.out.println("method is GET, uri:" + url.substring(url.indexOf("?")+1));

                }else if(msg.getMethod() == HttpMethod.POST) {
                    result = "post method and paramters is "+ new String(bts);
                    System.out.println("method is POST, params:" + new String(bts));
                }

                this.writeResponse(ctx, msg);

            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void writeResponse(ChannelHandlerContext ctx, Object object) {

            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
            response.headers().set(HttpHeaders.Names.CONTENT_TYPE,"text/html;charset=UTF-8");
            response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);

            StringBuilder sb = new StringBuilder();
            sb.append("<html>")
                    .append("<head>")
                    .append("<title>netty http server</title>")
                    .append("</head>")
                    .append("<body>")
                    .append("欢迎光临")
                    .append("</body>")
                    .append("</html>\r\n");
            ByteBuf responseBuf = Unpooled.copiedBuffer(sb, CharsetUtil.UTF_8);
            response.content().writeBytes(responseBuf);

            response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, response.content().readableBytes());

            responseBuf.release();
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

        }


    }

}
