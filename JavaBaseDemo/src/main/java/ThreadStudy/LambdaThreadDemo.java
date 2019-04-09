package ThreadStudy;

/**
 * 使用Lambda表达式，简化线程
 */
public class LambdaThreadDemo {
    public static void main(String[] args){
        Thread t1 = new Thread(() -> System.out.println("线程1"));
        Thread t2 = new Thread(() -> System.out.println("线程2"));
    }
}
