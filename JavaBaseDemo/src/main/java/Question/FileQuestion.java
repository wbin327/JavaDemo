package Question;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 常见文件操作笔试题
 */
public class FileQuestion {
    public static void transesferTo() throws IOException {
        // 将A文件移动到B文件中去
        FileChannel inputChanel = new FileInputStream("1.txt").getChannel();
        FileChannel outputChanel = new FileOutputStream("2.txt").getChannel();
        inputChanel.transferTo(0, inputChanel.size(), outputChanel);
    }
}
