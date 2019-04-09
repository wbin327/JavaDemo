package IOStudy;

import java.io.*;

public class BufferTest {
    /**
     * 写文件时使用缓冲和不使用缓冲的速度测试
     */
    public static void bufferRead(File file) throws Exception{
        //读取和写入文件时使用了缓冲
        InputStream is = new BufferedInputStream(new FileInputStream(file));
        OutputStream os = new BufferedOutputStream(new FileOutputStream(new File("BufferOutput.txt")));
        int s;
        while((s=is.read()) != -1){
            os.write(s);
        }
        is.close();
        os.close();
    }

    public static void read(File file) throws Exception{
        //读取文件时使用缓冲，写入文件不适用缓冲
        InputStream is = new FileInputStream(file);
        OutputStream os = new BufferedOutputStream(new FileOutputStream(new File("Output.txt")));
        int s;
        while((s=is.read()) != -1){
            os.write(s);
        }
        is.close();
        os.close();
    }

    public static void main(String[] args)throws Exception{
        // 缓冲写入文件时间：784ms，文件大小41M
        long startTime1 = System.currentTimeMillis();// 获取开始时间
        BufferTest.bufferRead(new File("jdk api 1.8_google.CHM"));
        long endTime1 = System.currentTimeMillis();//获取结束时间
        System.out.println("缓冲写入文件时间：" + (endTime1 - startTime1) + "ms");

        // 不使用缓冲写入文件时间：83002ms，文件大小41M
        long startTime2 = System.currentTimeMillis();// 获取开始时间
        BufferTest.read(new File("jdk api 1.8_google.CHM"));
        long endTime2 = System.currentTimeMillis();//获取结束时间
        System.out.println("不使用缓冲写入文件时间：" + (endTime2 - startTime2) + "ms");
    }
}
