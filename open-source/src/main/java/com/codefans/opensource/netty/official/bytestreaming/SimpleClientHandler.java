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
 * @Date: 2018-11-14 0:00
 */

public class SimpleClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {

        String content = "this is simple client!";
        byte[] contentBytes = content.getBytes();
        ByteBuf data = ctx.alloc().buffer(contentBytes.length);
        data.writeBytes(contentBytes);

        final ChannelFuture f = ctx.writeAndFlush(data);
//        f.addListener(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture future) {
//                assert f == future;
//                ctx.close();
//            }
//        });


    }

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
                        System.out.println("client接收到的内容为:" + new String(readBytes, "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

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
        System.out.println("exception ......");
        ctx.close();
    }

}
