package com.springboot.demo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * springboot整合filter 方式一：通过自动扫描
 *
 * <filter>
 *     <filter-name>FilterDemo1</filter-name>
 *     <filter-class>com.springboot.demo.filter.FilterDemo1</filter-class>
 * </filter>
 * <filter-mapping>
 *     <filter-name>FilterDemo1</filter-name>
 *     <url-pattern>/filter1</url-pattern>
 * </filter-mapping>
 */

@WebFilter(filterName ="filter1", urlPatterns = "/servlet1")
public class FilterDemo1 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("i'm filter1");
        // 执行servlet
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
