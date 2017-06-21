package test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @version 2017/6/20 0020.
 * @Author chenfei
 */
public class HelloServer {

    public void start(int port) throws Exception {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(boss, work).channel(NioServerSocketChannel.class).
                    childHandler(new ChannelInitializer<SocketChannel>() {
                                     @Override
                                     protected void initChannel(SocketChannel ch) throws Exception {
                                         ch.pipeline().addLast(new HelloServerInHandler());
                                     }
                                 }
                    ).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = b.bind(port).sync();
            future.channel().closeFuture().sync();
        } finally {

            work.shutdownGracefully();
            boss.shutdownGracefully();

        }

    }

    public static void main(String[] args) throws Exception {
        HelloServer server = new HelloServer();
        server.start(8000);
    }

}

