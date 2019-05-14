package Java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Java 8,函数式接口Predicate
 * 1.Predicate接口，输入一个参数T,返回boolean类型的结果
 * 2.Predicate接口包含以下几个默认函数，and(),or(),negate(),isEqual(),
 *     and(Predicate):与&&相同，与
 *     or(Predicate):与||相同，或
 *     negate(Predicate): 与!相同，非
 *     isEqual(Object): 与==相同，等于
 *
 *
 * Java 8,Stream
 * 通过Colleacion.stream()创建串行Stream,或者Colleaction.parallelStream()来创建一个并行Stream,
 * 串行Stream上的操作是在一个线程中完成，而并行Stream则是在多个线程上执行
 * Stream类可以实现以下几种功能
 * 1.Filter(过滤)
 * 2.Sorted(排序)
 * 3.Map(映射),将元素根据指定的Function接口来依次将元素转换成另外的对象
 * 4.Match(匹配)
 * 5.Count(计数)
 *
 * Java 8, Function<T, R>接口,接收T对象返回R对象
 *
 */
public class StreamAndPredicate {
    /**
     * 使用Predicate接口，从集合中选择大于1且小于5的值
     */
    static void test1(){
        List<Integer> list =Arrays.asList(1, 5, 7, 2, 5, 2, 4, 0);
        Predicate<Integer> p1 = i -> i<5;
        Predicate<Integer> p2 = i -> i>1;
        List test = list.stream().filter(p1.and(p2)).collect(Collectors.toList());
        //test.forEach(System.out::println);
        System.out.println("从集合中选择大于1且小于5的值：" + Arrays.asList(test));
    }

    /**
     * 使用Predicate接口，从字符集合中选择以a开头的字符
     */
    static void test2(){
        List<String> list = Arrays.asList("a", "b", "ababa", "fhajf", "hg", "aaa", "hghg");
        Predicate<String> p1 = i -> i.startsWith("a");
        List test = list.stream().filter(p1).collect(Collectors.toList());
        //test.forEach(System.out::println);
        System.out.println("从字符集合中选择以a开头的字符:" + Arrays.asList(test));
    }

    /**
     * 排序,sort
     */
    static void sort(){
        List<Integer> list =Arrays.asList(1, 5, 7, 2, 5, 2, 4, 0);
        List test = list.stream().sorted((a, b) -> a.compareTo(b)).collect(Collectors.toList());
        System.out.println("排序：" + Arrays.asList(test));
    }

    /**
     * 映射,map
     * 使用Function接口，将集合中所有字符转换成大写
     */
    static void map(){
        List<String> list = Arrays.asList("a", "b", "ababa", "fhajf", "hg", "aaa", "hghg");
        Function<String, String> function= String::toUpperCase;
        List<String> test = list.stream().map(function).collect(Collectors.toList());
        System.out.println("映射：" + Arrays.asList(test));
    }

    /**
     * Match(匹配)
     */
    static void match(){
        List<String> list = Arrays.asList("a", "b", "ababa", "fhajf", "hg", "aaa", "hghg");
        Predicate<String> predicate = (a) -> a.startsWith("a");
        boolean anyMatch = list.stream().anyMatch(predicate);
        boolean allMatch = list.stream().allMatch(predicate);
        boolean noneMatch = list.stream().noneMatch(predicate);
        System.out.println("anyMatch:" + anyMatch + ";\n" + "allMatch:" + allMatch + ";\n" + "noneMatch:" + noneMatch );
    }

    /**
     *  Count(计数）
     *  计数是一个最终操作，返回Stream中元素的个数，返回值是Long
     *  计算集合中所有以a开头的元素个数
     */
    static void count(){
        List<String> list = Arrays.asList("a", "b", "ababa", "fhajf", "hg", "aaa", "hghg");
        Predicate<String> predicate = (a) -> a.startsWith("a");
        Long count = list.stream().filter(predicate).count();
        System.out.println("集合中以a开头的元素总数为：" + count);
    }

    public static void main(String[] args){
        test1();
        test2();
        sort();
        map();
        match();
        count();
    }
}
