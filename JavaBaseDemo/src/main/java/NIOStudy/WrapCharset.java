package NIOStudy;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;

public class WrapCharset {
    /**
     * 获取当前文件的编码，并返回该编码的Charset实例
     * @return Charset实例
     */
    public static Charset getDefaultCharset(){
        // 获取默认编码
        String defaultCharset = System.getProperty("file.encoding");
        // 根据编码名称创建Charset对象
        return Charset.forName(defaultCharset);
    }

    /**
     * 根据字符集名称创建Charset对象并返回
     * @param charset 字符集名称，如UTF-8
     * @return Charset
     */
    public static Charset getCharset(String charset){
        return Charset.forName(charset);
    }

    /**
     * 获取java支持的所有字符集，存储在List中，并将其返回
     * @return 存储了所有字符集的List
     */
    public static List<String> getAllCharset(){
        SortedMap<String, Charset> charSets = Charset.availableCharsets();
        Iterator<String> it = charSets.keySet().iterator();
        List<String> list = new ArrayList<String>();
        while (it.hasNext())
            list.add(it.next());
        return list;
    }


    public static void main(String[] args){
        getDefaultCharset();
        List<String> list = getAllCharset();
        for(String s: list)
            System.out.println(s);
    }
}
