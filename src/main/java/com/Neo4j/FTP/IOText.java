package com.Neo4j.FTP;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class IOText {

    public static void main(String... args) throws IOException {


        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition =
                reentrantLock.newCondition();
        reentrantLock.lock();
        reentrantLock.unlock();

//        condition.await();

    }

    public void selector() throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);//设置非
        // ket().bind(new InetSocketAddress(8080));
        //注册监听事件
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            Set<SelectionKey> selectionKeys =
                    selector.selectedKeys();
            Iterator<SelectionKey> it =
                    selectionKeys.iterator();
            while (it.hasNext()) {

                SelectionKey key = it.next();
                if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                    ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssChannel.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                    it.remove();
                } else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    while (true) {
                        buffer.clear();
                        int n = sc.read(buffer);
                        if (n <= 0) {
                            break;
                        }
                        buffer.flip();

                    }
                    it.remove();
                }
            }
        }
    }
}
