package com.dubbo.demo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;

@EnableDubbo
public class DubboSpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboSpringBootDemoApplication.class, args);
    }

}
