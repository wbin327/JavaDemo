package NIOStudy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelCopy {
    /**
     * 使用nio进行文件复制
     */
    private static final int BSIZE = 1024;
    public static void main(String[] args) throws Exception{
        long startTime = System.currentTimeMillis();// 获取开始时间
        FileChannel in = new FileInputStream("jdk api 1.8_google.CHM").getChannel();
        FileChannel out = new FileOutputStream("NIOOutput.txt").getChannel();
        // ByteBuffer bb = ByteBuffer.allocate(BSIZE);           //复制同一个文件用时750ms
        ByteBuffer bb = ByteBuffer.allocateDirect(BSIZE);        //复制同一个文件用时242ms
        while(in.read(bb) != -1){
            bb.flip();       //做好被其他人读取字节的准备
            out.write(bb);   //写入文件
            bb.clear();
        }
        long endTime = System.currentTimeMillis();// 获取结束时间
        System.out.println("使用缓冲写入文件时间：" + (endTime - startTime) + "ms");
    }
}
