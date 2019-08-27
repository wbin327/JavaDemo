package DesignModel.SingletonDemo;

/**
 * 使用静态内部类实现单例
 * 1.懒加载，外部类加载时，并不会导致内部类的加载，只有当调用内部类时才会加载内部类
 * 2.JVM进行类加载时是线程安全的
 *
 */
public class StaticInnerClassSingleton {
    private static class StaticInnerClass{
        public static StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }
    public static StaticInnerClassSingleton getInstance(){
        return StaticInnerClass.INSTANCE;
    }

    public static void main(String[] args){
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName() + ":" +  StaticInnerClassSingleton.getInstance());
        for(int i=0; i<10; i++){
            Thread t = new Thread(runnable);
            t.setName("线程：" + i);
            t.start();
        }
    }
}
