package com.springboot.demo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * springBoot整合Listener,方式一：通过自动扫描
 * <listener>
 *     <listener-class>com.springboot.demo.listener.ListenerDemo1</listener-class>
 * </listener>
 */

@WebListener
public class ListenerDemo1 implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent arg0){
        System.out.println("实例化监听器对象1");
    }
}
