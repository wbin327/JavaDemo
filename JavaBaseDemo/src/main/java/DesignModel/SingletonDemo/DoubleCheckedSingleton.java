package DesignModel.SingletonDemo;


/**
 * 单例模式,懒汉式套路基础上加入并发控制，保证在多线程环境下，对外存在唯一一个对象
 * 1.构造器私有化 --》避免外部new构造器
 * 2.提供私有的静态属性 -》存储对象的地址
 * 3.提供公共的静态方法 -》获取属性
 */
public class DoubleCheckedSingleton {
    // 2.提供私有的静态属性
    // volatile保证了数据的一致性，所有线程都是从主存中取该值，而不是使用当前线程空间中的值
    private static volatile DoubleCheckedSingleton instance;

    // 1.构造器私有化
    private DoubleCheckedSingleton(){}

    // 3.提供公共的静态方法 --》获取属性
    public static DoubleCheckedSingleton getInstance(){
        // 双重检测，DoubleCheck,避免不必要的等待，提高性能
        if(instance != null)
            return instance;
        synchronized (DoubleCheckedSingleton.class) {
            if (null == instance)
                instance = new DoubleCheckedSingleton();
            return instance;
        }
    }

    public static void main(String[] args){
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName() + ":" +  HungryManSingleton.getInstance());
        for(int i=0; i<10; i++){
            Thread t = new Thread(runnable);
            t.setName("线程：" + i);
            t.start();
        }
    }

}
