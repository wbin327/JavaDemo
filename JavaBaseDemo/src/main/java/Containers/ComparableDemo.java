package Containers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Comparator和Comparator区别
 * comparable接口实际上是出自java.lang包 它有一个 compareTo(Object obj)方法用来排序
 * comparator接口实际上是出自 java.util 包它有一个compare(Object obj1, Object obj2)方法用来排序
 */
public class ComparableDemo {
    public static void main(String[] args){
        List<Person> list = new ArrayList<>();
        list.add(new Person(3, "c"));
        list.add(new Person(1, "a"));
        list.add(new Person(2, "b"));
        list.add(new Person(5, "e"));
        list.add(new Person(4, "d"));
        // 排序,需要集合中的对象实现Comparable接口
        Collections.sort(list);
        for(Person person: list)
            System.out.println(person.getId() + ":" + person.getName());
    }
}

class Person implements Comparable<Person>{
    private int id;
    private String name;

    Person(int id, String name){
        this.id = id;
        this.name = name;
    }
    @Override
    public int compareTo(Person o) {
        // 升序
        return Integer.compare(this.id, o.id);
        // 倒序
        // return Integer.compare(o.id, this.id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}