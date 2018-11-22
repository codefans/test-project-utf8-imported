package com.codefans.opensource.netty.socket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.NoSuchElementException;

/**
 * @author: ShengzhiCai
 * @date: 2018-11-22 16:03
 */
public class SocketServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {


        try {


            String message = msg.toString(Charset.defaultCharset());
            System.out.println("message=" + message);


            // Remove this handler
            ctx.pipeline().remove(this);
        } catch (NoSuchElementException e) {
            System.out.println("Error while removing HandshakeHandler" + e);
        }

        // Hand over this message to the next .
        ctx.fireChannelRead(msg.retain());
    }


}
