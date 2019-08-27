package DesignModel.SingletonDemo;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

/**
 * 使用枚举的方式实现单例（饿汉式），该方式有以下优点
 * 1.线程安全
 * 2.在面对复杂的序列化或者反射攻击时仍然可以防止多次实例化
 */
public class EnumSingleton {
    public static void main(String[] args){
        Singleton singleton1 = Singleton.INSTANCE;
        Singleton singleton2 = Singleton.INSTANCE;
        System.out.println(singleton1 == singleton2);
    }
}

enum Singleton{
    INSTANCE;
    public void getInstance(){
        System.out.println("ok");
    }
}