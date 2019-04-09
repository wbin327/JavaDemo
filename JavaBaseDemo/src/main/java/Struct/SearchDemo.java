package Struct;

/**
 * 各种查找的集合
 */
public class SearchDemo {

    /**
     * 折半查找
     * @param list 有序数组
     * @param searchNum 待查找的整数
     * @return int 如果找到返回数组的索引，如果找不到，返回-1
     */
    public int binarySearch(int[] list, int searchNum){
        int low = 0;
        int high = list.length;
        while (high > low){
            int middle = (low + high +1)/2;
            if(list[middle] > searchNum)
                high = middle - 1;
            else if(list[middle] == searchNum)
                return middle;
            else
                low = middle + 1;
        }
        return -1;
    }

    public static void main(String[] args){
        int[] list = {2,10,15,20,22,30,33,40,50,55,58,61,62};
        SearchDemo sd = new SearchDemo();
        System.out.println(sd.binarySearch(list, 60));

        String s = "hello";
        System.out.println(s.hashCode());
    }
}
