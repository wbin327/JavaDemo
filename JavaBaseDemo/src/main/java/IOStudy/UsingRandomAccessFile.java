package IOStudy;

import java.io.File;
import java.io.RandomAccessFile;

public class UsingRandomAccessFile {
    /**
     * RandomAccessFile学习
     */
    static String file = "rtest.dat";
    static void display() throws Exception{
        RandomAccessFile raf = new RandomAccessFile(new File(file), "r");
        for(int i=0; i<7; i++){
            System.out.println("value" + i + ":" + raf.readDouble());
        }
        System.out.println(raf.readUTF());
        raf.close();
    }

    public static void main(String[] args) throws Exception{
        RandomAccessFile raf = new RandomAccessFile(new File(UsingRandomAccessFile.file), "rw");
        for(int i=0; i<7; i++){
            raf.writeDouble(i*1.414);
        }
        raf.writeUTF("The end of the file");
        raf.close();
        UsingRandomAccessFile.display();
    }
}
