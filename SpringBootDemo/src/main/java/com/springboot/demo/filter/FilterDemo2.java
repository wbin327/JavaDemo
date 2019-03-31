package com.springboot.demo.filter;


import javax.servlet.*;
import java.io.IOException;

/**
 * springboot整合filter方式二：使用函数的方式
 */
public class FilterDemo2 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("i'm filter2");
        // 执行servlet
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
