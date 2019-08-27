package DesignModel.SingletonDemo;


/**
 * 饿汉式单例
 * 饿汉式单例是指，在类加载时就创建单例，就好像饿汉一般一看到食物就饥不择食
 * 用空间换时间，不存在线程安全问题
 *
 * 缺点：
 * 在类装载时就实例化，有可能在我们没有使用的情况下也进行实例化，导致内存浪费
 */
public class HungryManSingleton {
    private static HungryManSingleton INSTANCE = new HungryManSingleton();
    HungryManSingleton(){}
    public static HungryManSingleton getInstance(){
        return INSTANCE;
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
