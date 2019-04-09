package NIOStudy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferToText {
    private static final int BSIZE = 1024;
    public static void main(String[] args) throws Exception{
        FileChannel fc = new FileOutputStream("data2.txt").getChannel();
        // write(),构造一个使用给定编码器编码字符的写入器，并将生成的字节写入给定的通道
        fc.write(ByteBuffer.wrap("Some text".getBytes()));
        // 写入缓存时指定编码才能输出正确的字符
        // fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
        fc.close();
        fc = new FileInputStream("data2.txt").getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        fc.read(buff);
        // flip方法用于将指针指向缓存头，调用这个方法的原因在于，上一步的read方法执行后，指针是指向缓存尾端，
        // 如果不调用flip方法再次执行read方法，输出的内容为空
        buff.flip();
        // 缓冲器容纳的是普通的字节，为了将他们转换成字符，要么在输入时对其编码或输出时对其解码
        System.out.println(buff.asCharBuffer());
    }
}
