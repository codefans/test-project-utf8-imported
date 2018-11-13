package com.codefans.opensource.netty.official.bytestreaming;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.io.UnsupportedEncodingException;

/**
 * @Author: ShengzhiCai
 * @Date: 2018-11-13 23:51
 */

public class SimpleServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(final ChannelHandlerContext ctx, Object msg) { // (2)
        try {
            // Do something with msg
            if(msg instanceof ByteBuf) {
                ByteBuf dataBuf = (ByteBuf)msg;
                if(dataBuf.isReadable()) {
                    byte[] readBytes = new byte[dataBuf.readableBytes()];
                    try {
                        dataBuf.readBytes(readBytes);
                        System.out.println("server接收到的内容为:" + new String(readBytes, "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    String content = "Simple Server Response!";
                    byte[] contentBytes = content.getBytes();
                    ByteBuf responseByteBuf = ctx.alloc().buffer(contentBytes.length);
                    responseByteBuf.writeBytes(contentBytes);
                    final ChannelFuture f = ctx.writeAndFlush(responseByteBuf); // (3)
                    f.addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture future) {
                            assert f == future;
                            ctx.close();
                        }
                    }); // (4)
                }
            } else {
                System.out.println("类型未知:msg=" + msg);
            }


        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

}
