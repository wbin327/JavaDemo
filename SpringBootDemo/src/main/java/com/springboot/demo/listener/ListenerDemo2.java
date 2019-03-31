package com.springboot.demo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * springBoot整合Listener,方式二：通过函数
 * <listener>
 *     <listener-class>com.springboot.demo.listener.ListenerDemo1</listener-class>
 * </listener>
 */

public class ListenerDemo2 implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent arg0){
        System.out.println("实例化监听器对象2");
    }
}
