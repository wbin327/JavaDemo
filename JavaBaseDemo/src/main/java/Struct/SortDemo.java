package Struct;

import java.util.Arrays;

/**
 * 排序算法
 */
public class SortDemo {

    /**
     * 直接插入排序
     * @param list 数组
     * @return list
     */
    static int[] insertSort(int[] list){
        list = list.clone();
        // temp用于交换时的中间变量;j用于内循环计数
        int temp, j;
        for(int i=1; i<list.length; i++){
            //首先判断是否需要排序
            if(list[i] < list[i-1]){
                temp = list[i];
                for(j=i-1; j>=0; j--){
                    if(temp<list[j])
                        // 往后移动一位
                        list[j+1] = list[j];
                    else
                        // 找到了需要插入的位置，中断循环记录J的值
                        break;
                }
                // 插入数据
                list[j + 1] = temp;
            }
        }
        return list;
    }

    /**
     * 折半插入排序
     * @param list 数组
     * @return list
     */
    static int[] binarySort(int[] list){
        list = list.clone();
        // temp用于交换时的中间变量;j用于内循环计数
        int temp, mid;
        for(int i=1; i<list.length; i++){
            // 首先判断是否需要排序
            if(list[i] < list[i-1]){
                // 二分查找，找到需要插入的位置
                int low = 0;
                // 注意，high应该为i-1, list[i]之前的序列才是有序序列，不包括list[i]
                int high = i-1;
                mid = (low + high)/2;
                while(low <= high){
                    mid = (low + high)/2;
                    if(list[i] < list[mid])
                        high = mid - 1;
                    else
                        low = mid + 1;
                }

                // 插入元素到数组中
                temp = list[i];
                for(int j=i-1; j>=mid; j--)
                    // 元素后移一位
                    list[j+1] = list[j];
                list[mid] = temp;
            }
        }
        return list;
    }

    /**
     * 希尔排序
     * @param list 待排序数组
     * @param dt 每趟希尔排序的增量值
     * @return list
     */
    static int[] shellSort(int[] list, int[] dt){
        list = list.clone();
        for(int i=0; i<dt.length; i++){
            shellInsert(list, dt[i]);
            //System.out.println(dt[i]);
            //System.out.println("希尔排序第" + i + "趟排序结果："+ Arrays.toString(list));
        }
        return list;
    }
    static void shellInsert(int[] list, int dk){
        // temp用于交换时的中间变量;j用于内循环计数
        int temp, j;
        // 直接插入排序
        for(int i=dk; i<list.length; i++){
            // 判断是否需要排序
            if(list[i] < list[i-dk]){
                temp = list[i];
                for(j=i-dk; j>=0; j-=dk){
                    if(list[j+dk] < list[j])
                        // 后移一位
                        list[j+dk] = list[j];
                    else
                        break;
                }
                list[j+dk] = temp;
            }
        }
    }

    /**
     * 冒泡排序
     * @param list
     * @return
     */
    static int[] bubbleSort(int[] list){
        list = list.clone();
        int temp;
        for(int i=0;i<list.length;i++){
            for(int j=1;j<list.length-i;j++){
                if(list[j-1]>list[j]){
                    // 交换
                    temp = list[j];
                    list[j] = list[j-1];
                    list[j-1] = temp;
                }
            }
        }
        return list;
    }

    /**
     * 快速排序
     * @param list
     * @return
     */
    static int[] quickSort(int[] list){
        list = list.clone();
        quickSort(list, 0, list.length-1);
        return list;
    }
    private static void quickSort(int[] list, int start, int end){
        int key = list[start];
        int low = start;
        int high = end;
        // 递归结束条件
        if(low>=high) {
            return;
        }
        // 使用循环实现分区操作
        while(low<high){
            // 1.high左移，找到第一个小于key的值
            while(list[high]>=key && low<high)
                high--;
            // 2.将右侧找到小于key的值赋给list[low],low++
            if(low<high){
                list[low] = list[high];
                low++;
            }
            // 3.low右移，找到第一个大于key的值
            while(list[low]<key && low<high)
                low++;
            // 4.将左侧找到的大于等于key的值赋给list[high],high--
            if(low<high){
                list[high] = list[low];
                high--;
            }
        }
        // 赋值
        list[low] = key;

        // 递归左右区间
        quickSort(list,start, low-1);
        quickSort(list,low+1,end);
    }

    /**
     * 选择排序
     * @param list
     * @return 数组
     */
    static int[] selectSort(int[] list){
        list = list.clone();
        int temp, min;
        for(int i=0;i<list.length;i++){
            min = i;
            for(int j=i;j<list.length;j++){
                if(list[j]<list[min])
                    min = j;
            }
            // 交换
            temp = list[i];
            list[i] = list[min];
            list[min] = temp;
        }
        return list;
    }

    public static void main(String[] args){
        int[] list = {49,38,65,97,76,13,27,49};
        System.out.println(Arrays.toString(SortDemo.insertSort(list)));
        System.out.println(Arrays.toString(SortDemo.binarySort(list)));
        System.out.println(Arrays.toString(SortDemo.shellSort(list, new int[]{4,2,1})));
        System.out.println(Arrays.toString(SortDemo.bubbleSort(list)));
        System.out.println(Arrays.toString(SortDemo.quickSort(list)));
        System.out.println(Arrays.toString(SortDemo.selectSort(list)));
    }
}
