package com.springboot.demo.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * springboot整合servlet方式一,通过函数
 *
 * <servlet>
 *     <servlet-name>ServletDemo1</servlet-name>
 *     <servlet-class>com.springboot.servlet.ServletDemo1</servlet-class>
 * </servlet>
 *
 * <servlet-mapping>
 *     <servlet-name>ServletDemo1</servlet-name>
 *     <url-pattern>/servlet1</url-pattern>
 * </servlet-mapping>
 */

public class ServletDemo2 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        System.out.println("ServletDemo2");
    }
}
