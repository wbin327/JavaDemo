package NIOStudy;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 通道：
 *     用于源节点于目标节点的连接。
 *     通道只能用于数据传输，无法用于存储，所以必须配合byteBuffer一起使用
 *
 * 通道的主要实现类
 *     java.nio.channels.Channel 接口：
 *         |--FileChannel
 *         |--SocketChannel
 *         |--ServerSocketChannel
 *         |--DatagramChannel
 *
 *  获取通道
 *      1.java针对支持通道的类提供了getChannel()方法
 *          本地IO:
 *              FileInputStream/FileOutputStream
 *              RandomAccessFile
 *          网络IO:
 *              Socket
 *              ServerSocket
 *              DatagramSocket
 *      2.在JDK1.7以上的版本，针对各个通道提供了静态方法open();
 *      3.在JDK1.7以上，Files工具类的newByteChannel()
 *
 *  通道之间的数据传输
 *      transferFrom()
 *      transferTo()
 */

public class QuickStart {

    // 通道之间数据传输
    public static void test3() throws IOException {
        long start = System.currentTimeMillis();

        FileChannel inChanel = FileChannel.open(Paths.get("input.txt"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("output.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        inChanel.transferTo(0, inChanel.size(), outChannel);
        // outChannel.transferFrom(inChanel, 0, inChanel.size());

        inChanel.close();
        outChannel.close();

        long end = System.currentTimeMillis();
        System.out.println("耗费时间为：" + (end - start));
    }

    /**
     * 使用直接缓冲区完成文件的复制
     */
    public static void test4() throws IOException {
        long start = System.currentTimeMillis();

        FileChannel inChannel = FileChannel.open(Paths.get("input.txt"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("output.txt"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        // 开辟直接内存空间
        MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);

        inChannel.close();
        outChannel.close();

        long end = System.currentTimeMillis();
        System.out.println("耗费时间为：" + (end - start));
    }

    public static void main(String[] args) throws IOException {
        test3();
        test4();
    }
}
