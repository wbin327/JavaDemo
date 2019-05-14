package Java8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * java 8, Map
 * Map 类型不支持 streams，不过Map提供了一些新的有用的方法来处理一些日常任务。
 * Map接口本身没有可用的 stream（）方法，但是你可以在键，值上创建专门的流或者通过 map.keySet().stream(),map.values().stream()和map.entrySet().stream()。
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
    static void foreach(){
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
        foreach();
    }

    /**
     *  根据Key获取value的值，如果key存在就获取value值，不存在就返回指定的值
     */
    static void getDefault(){
        System.out.println(map.getOrDefault(20, "not found"));
    }

    public static void main(String[] args){
        foreach();
        select();
        getDefault();
    }
}
