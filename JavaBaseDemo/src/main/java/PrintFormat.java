import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class PrintFormat {
    /**
     * 将Collection格式化成特定格式的String并返回
     */
    public static String format(Collection<?> collection){
        //Collection<?>中的问号指的是Object类
        StringBuilder result = new StringBuilder();
        if(collection.size() > 0 ) {
            result.append("[\n");
            for(Object item: collection){
                result.append("\r" + item.toString() + "\n");
            }
            result.append("]");
        }
        else
            result.append("[]");
        return result.toString();
    }

    public static void main(String[] args){
        List<Integer> list = new ArrayList<Integer>();
        Random random = new Random();
        for(int i=0;i<10;i++){
            int j = random.nextInt();
            list.add(j);
        }
        System.out.println(PrintFormat.format(list));
    }
}
