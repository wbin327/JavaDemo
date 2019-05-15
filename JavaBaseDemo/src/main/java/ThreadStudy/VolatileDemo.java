package ThreadStudy;

/**
 * volatile关键字，轻量级同步机制
 * 1.防止指令重排
 * 2.可见性保证，线程将直接从主存中获取该变量的值，而不是线程的工作内存
 */
public class VolatileDemo {
    /**
     * 如果volatileDemo变量不添加volatile关键字，有可能会出现以下情况
     * 在执行到volatileDemo = new VolatileDemo();这句话时，JVM实际上会执行三步操作
     * 1.在堆中划分一块空间
     * 2.初始化对象
     * 3.volatileDemo指向该对象
     * 在执行2.3时有可能发生指令重排，先3后2，如果这个时候有个线程在执行if(volatileDemo != null)，会得到true的结果，
     * 因为volatileDemo确实不为空，并返回该对象引用，但是对象仍未初始化，这个时候直接使用该对线是会报错的
     * volatile关键字可以避免指令重排
     */
    private static volatile VolatileDemo volatileDemo;

    VolatileDemo(){}
    public static VolatileDemo getVolatileDemo(){
        // Double Check Singleton
        if(volatileDemo != null)
            return volatileDemo;
        synchronized(VolatileDemo.class){
            if(volatileDemo != null)
                return volatileDemo;
            volatileDemo = new VolatileDemo();
            return volatileDemo;
        }
    }
}
