package IOStudy;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileOutput {
    public static void main(String[] args) throws Exception{
        FileInputStream fis = new FileInputStream("pom.xml");
        FileOutputStream fos = new FileOutputStream("output.txt");
        int size = fis.available();    //返回这个输入流中可以被读的剩余的bytes字节的估计值
        byte[] array= new byte[size];  //创建byte数组用于保存数据
        fis.read(array);               //将数据读取到数组中
        fos.write(array);
        // 使用完数据流后切记主动关闭流
        fis.close();
        fos.close();
    }
}
