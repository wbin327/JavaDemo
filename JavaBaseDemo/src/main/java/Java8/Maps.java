package Java8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * java 8, Map
 * Map 类型不支持 streams，不过Map提供了一些新的有用的方法来处理一些日常任务。
 * Map接口本身没有可用的 stream（）方法，但是你可以在键，值上创建专门的流或者通过 map.keySet().stream(),map.values().stream()
 * map.entrySet().stream()获取map的流，entry的结构就是一个键值对
 */
public class Maps {
    static Map<Integer, String> map = new HashMap<>();
    static {
        map = new HashMap<>();
        for(int i = 1; i<=10; i++){
            map.put(i, "value" + i);
        }
    }

    /**
     * 遍历map
     */
    static void foreach(Map map){
        map.forEach((key, value) -> System.out.println(String.format("key: %s;   value: %s", key, value)));
    }

    /**
     * 赛选出value中以1结尾的字符串
     */
    static void select(){
        List<String> list = map.values().stream().filter((s) -> s.endsWith("1")).collect(Collectors.toList());
        System.out.println(Arrays.asList(list));
    }

    /**
     * map删除一个键值对全都匹配的项
     */
    static void remove(){
        map.remove(1, "value1");
        foreach(Maps.map);
    }

    /**
     *  根据Key获取value的值，如果key存在就获取value值，不存在就返回指定的值
     */
    static void getDefault(){
        System.out.println(map.getOrDefault(20, "not found"));
    }

    /**
     * 根据key倒叙排序
     * 使用map.entrySet().stream()
     */
    static void sort(){
        System.out.println("原Map中元素的顺序为：");
        foreach(Maps.map);
        System.out.println("根据key倒叙排序后的顺序为：");
        List<Map.Entry<Integer, String>> list = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey((a, b) -> b-a))
                .collect(Collectors.toList());
                //.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        list.forEach(System.out::println);
    }

    public static void main(String[] args){
        select();
        getDefault();
        sort();
    }
}
