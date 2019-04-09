package NIOStudy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class ChannelTransfer {
    /**
     * 使用nio复制文件，下面的例子使用了transferTo将一个管道与另一个管道相连
     */
    private final int BSIZE = 1024;
    public static void main(String[] args) throws Exception{
        long startTime = System.currentTimeMillis();// 获取开始时间
        FileChannel in = new FileInputStream("jdk api 1.8_google.CHM").getChannel();
        FileChannel out = new FileOutputStream("NIOOutput.txt").getChannel();
        in.transferTo(0, in.size(), out);      //将输入管道与输出管道相连
        //out.transferFrom(in, 0, in.size());    //将输出管道与输入管道相连
        long endTime = System.currentTimeMillis();// 获取结束时间
        System.out.println("不使用缓冲写入文件时间：" + (endTime - startTime) + "ms");
    }
}
