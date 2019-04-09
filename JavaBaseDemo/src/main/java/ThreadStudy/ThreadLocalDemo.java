package ThreadStudy;

import java.util.Random;

/**
 * ThreadLocal,从名字上来理解就是线程本地的数据
 * 因为创建线程时，会为每个线程开辟独立的线程空间，线程本地的数据，也就是每个线程独立的数据
 * get/set/initiaValue
 */
public class ThreadLocalDemo {
    // private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    //更改初始值，需要继承ThreadLocal,并重写initiaValue方法
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        protected Integer initialValue(){
            return 200;
        }
    };
    public static void main(String[] args){
        Thread t0 = new Thread(()->System.out.println(Thread.currentThread().getName() + ":" +threadLocal.get()));
        Thread t1 = new Thread(()->{
            threadLocal.set(1);
            System.out.println(Thread.currentThread().getName() + ":" +threadLocal.get());
        });
        Thread t2 = new Thread(()->{
            threadLocal.set(2);
            System.out.println(Thread.currentThread().getName() + ":" +threadLocal.get());
        });
        t0.start();
        t1.start();
        t2.start();
    }
}
