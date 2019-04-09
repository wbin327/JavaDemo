package FileStudy;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList {
    public static void main(String[] args){
        File path = new File(".");
        String[] list;
        if(args.length == 0)
            list = path.list();
        else
            list = path.list(new DirFilter(args[0]));
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for(String dirItem : list)
            System.out.println(dirItem);
    }
}

class DirFilter implements FilenameFilter{
    /**
     *  通过实现FilenameFilter接口，实现过滤特定的文件
     */
    private Pattern pattern;
    public DirFilter(String regex){
        // 编译正则表达式，用于与输入的字符进行匹配
        pattern = Pattern.compile(regex);
    }
    public boolean accept(File dir, String name){
        return pattern.matcher(name).matches();
    }
}
