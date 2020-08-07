package NIOStudy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 *  socketIO多路复用器，单线程版
 */

public class SocketMultipleSingle {
    private ServerSocketChannel server = null;
    private Selector selector = null;                   //linux 多路复用器
    int port = 9000;

    public void initServer() throws IOException {
        server = ServerSocketChannel.open();
        // 设置非阻塞
        server.configureBlocking(false);
        // 端口绑定
        server.bind(new InetSocketAddress(port));
        // 使用多路复用器（select,poll,epoll）,如果操作系统使用epoll，那么该语句将调用epoll_create()
        selector = Selector.open();
        // 如果操作系统使用epoll,将调用epoll_ctl,并绑定accept事件
        server.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void start() throws IOException {
        initServer();
        System.out.println("服务器启动");
        while(true){
            Set<SelectionKey> keys = selector.keys();
            System.out.println("keySize:" + keys.size());

            // selector.select()说明
            // 1.如果系统调用的是select，那么将直接调用select()
            // 2.如果系统调用的是epoll,那么将调用epoll_wait()
            while(selector.select(1000) > 0){
                // 返回已准备好的file descriptor
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectionKeys.iterator();
                while (iter.hasNext()){
                    SelectionKey key = iter.next();
                    // 不移除会重复循环处理
                    iter.remove();
                    if(key.isAcceptable()){
                        // 接收新连接
                        acceptHandler(key);
                    }else if(key.isReadable()){
                        // 接收数据
                        readHandler(key);
                    }
                }
            }
        }
    }

    /**
     *  接收新的连接
     *  1.accept()，接受连接
     *  2.设置非阻塞
     *  3.epoll_ctl
     */
    public void acceptHandler(SelectionKey key) throws IOException {
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        // 系统调用accept
        SocketChannel client = channel.accept();
        client.configureBlocking(false);
        // 在jvm堆中开辟一个8192个字节大小的内存空间,在进行数据读取时，将会使用这个buffer
         ByteBuffer buffer = ByteBuffer.allocate(8192);
        // 系统调用epoll_ctl()
         client.register(selector, SelectionKey.OP_READ, buffer);

        System.out.println("*******************************************************************");
        System.out.println("新客户端：" + client.getRemoteAddress());
        System.out.println("*******************************************************************");
    }

    /**
     *  接收数据
     *  这里没考虑接收的数据大小超过bytebuffer的最大值
     */
    public void readHandler(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        channel.read(buffer);
        // 将buffer的指针复位，position指向数据起始位，limit指向数据结束位
        buffer.flip();
        byte[] bytes = new byte[64];
        buffer.get(bytes, 0, buffer.limit()-1);
        String msg = new String(bytes);
        System.out.println("接收来自" + channel.getRemoteAddress() + "的消息：" + msg);
        // 将buffer清空，接收下一次传递的数据
        buffer.clear();
    }

    public static void main(String[] args) throws IOException {
        new SocketMultipleSingle().start();
    }
}
