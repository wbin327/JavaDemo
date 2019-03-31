package com.springboot.demo.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * springboot整合servlet方式一,通过自动扫描
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

@WebServlet(name="ServletDemo1", urlPatterns = "/servlet1")
public class ServletDemo1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
        System.out.println("ServletDemo1");
    }
}
