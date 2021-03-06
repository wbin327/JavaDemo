package Containers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Comparator和Comparator区别
 * comparable接口实际上是出自java.lang包 它有一个 compareTo(Object obj)方法用来排序
 * comparator接口实际上是出自 java.util 包它有一个compare(Object obj1, Object obj2)方法用来排序
 */
public class ComparatorDemo {
    public static void main(String[] args){
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(-1);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(-5);
        arrayList.add(7);
        arrayList.add(4);
        arrayList.add(-9);
        arrayList.add(-7);
        System.out.println("原始数组:");

        // void sort(List list),按自然排序的升序排序
        Collections.sort(arrayList);
        System.out.println("Collections.sort(arrayList):");
        System.out.println(arrayList);

        // 倒叙
        Collections.sort(arrayList,
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1<o2 ? 1:-1;
                    }
                });
        System.out.println("定制排序后：");
        System.out.println(arrayList);
    }
}


