package Java8;

import java.util.*;

/**
 * java 8 lambda表达式
 */
public class LambdaExpressions {
    public static void main(String[] args){
        // 不使用lambda排列字符串,使用匿名内部类
        List<String> names = Arrays.asList("a", "z", "h", "d", "y");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        // 使用lambda表达式,lambda会自动推导传参的类型
        Collections.sort(names, ( o1, o2) -> o2.compareTo(o1));
    }
}
