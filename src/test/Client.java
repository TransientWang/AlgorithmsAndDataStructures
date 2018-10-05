package com.example.demo;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class Client {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel
                .open()
                .bind(new InetSocketAddress("127.0.0.1",9888));
        socketChannel.connect(new InetSocketAddress("127.0.0.1",9999));
    }
}
