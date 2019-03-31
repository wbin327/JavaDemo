package com.springboot.demo;

import com.springboot.demo.filter.FilterDemo2;
import com.springboot.demo.listener.ListenerDemo2;
import com.springboot.demo.servlet.ServletDemo2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@ServletComponentScan       // 启动时扫描项目根目录，并将@WebServlet,@WebFilter,@WebListener注解的类实例化
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    //编写一个方法返回servlet对象
    @Bean
    public ServletRegistrationBean registerServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new ServletDemo2());
        bean.addUrlMappings("/servlet2");
        return bean;
    }

    //编写一个方法返回filter对象
    @Bean
    public FilterRegistrationBean registerFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean(new FilterDemo2());
        bean.addUrlPatterns("/servlet2");
        return bean;
    }

    //编写一个方法返回listener对象
    @Bean
    public ServletListenerRegistrationBean<ListenerDemo2> registerListener(){
        ServletListenerRegistrationBean<ListenerDemo2> bean = new ServletListenerRegistrationBean(new ListenerDemo2());
        return bean;
    }
}
