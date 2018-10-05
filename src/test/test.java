package com.example.demo;

import com.sun.org.apache.bcel.internal.generic.Select;
import org.springframework.expression.spel.ast.Selection;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class test {

    public static void ss() {
        System.out.println("ss");
    }

    public static void main(String[] args) throws Exception{

        Selector selector = SelectorProvider.provider().openSelector();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel
                .open()
                .bind(new InetSocketAddress("127.0.0.1",9999));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("等待连接");
        while (true) {
            while (selector.select()>0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey key:selectionKeys) {
                    if (key.isAcceptable()){
                        System.out.println("接受");
                        selectionKeys.remove(key);
                        System.out.println(selector.select());
                        System.out.println(selector.select());
                    }
                }
            }

        }


    }
}
