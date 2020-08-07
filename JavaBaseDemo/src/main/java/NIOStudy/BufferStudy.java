package NIOStudy;

import java.nio.ByteBuffer;

/**
 * 缓冲区：在Java NIO中负责数据的存取。缓冲区就是数组，用于存储不同数据类型的数据
 *     ByteBuffer
 *     CharBuffer
 *     ShortBuffer
 *     IntBuffer
 *     LongBuffer
 *     FloatBuffer
 *     DoubleBuffer
 *     上述缓冲区都是通过allocate()在JVM的堆中进行内存空间的开辟
 *
 * 缓冲区存储数据的两个方法
 *     put(): 存入数据到缓冲区
 *     get(): 获取缓冲区中的数据
 *
 * 缓冲区的四个核心属性：
 *     capacity: 容量，缓冲区中最大存储数据的容量，一旦声明不能改变
 *     limit: 界限，缓冲区中可以操作的数据的大小
 *     position: 位置，表示缓冲区中正在操作数据的位置
 *     position <= limit <= capacity
 *
 * mark():标记当前指针的位置，之后可以使用reset()使指针恢复到标记位
 *
 * 直接缓冲区和非直接缓冲区
 *     非直接缓冲区：
 *         通过allocate()方法分配缓冲区，开辟的内存空间处于jvm的堆中
 *     直接缓冲区：
 *         通过allocateDirect()方法分配直接缓冲区，该方法划分的内存区域允许内核和用户共同访问（在jvm内存空间之外，也称为堆外内存）。
           直接缓冲区避免了非直接缓冲区进行IO操作时，需要先将数据从系统空间拷贝到用户空间，这一中间过程。
 *
 */
public class BufferStudy {

    public static void test1(){
        // 在堆中分配一个指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("hello world".getBytes());
        System.out.println(buffer.toString());

        // 将buffer中的指针复位到读数据模式
        buffer.flip();
        System.out.println(buffer.toString());

        // 数据读取
        byte[] dst = new byte[ buffer.limit()];
        buffer.get(dst);
        System.out.println(new String(dst));

        // mark(): 标记当前指针的位置，之后可以使用reset()使指针恢复到标记位
        buffer.flip();
        buffer.mark();
        System.out.println("mark标记时，指针的位置：" + buffer.toString());
        buffer.get(dst, 0, 5);
        System.out.println("读取数据后，指针位置：" + buffer.toString());
        buffer.reset();
        System.out.println("指针回到标记位：" + buffer.toString());


        // 注意：clear()方法只是将指针重置为初始状态，并没有真正的清空数据
        buffer.clear();
        System.out.println((char)buffer.get(0));

    }

    public static void main(String[] args){
        test1();
    }
}
