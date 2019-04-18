package com.aop.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//1.为代理对象添加@Component注解，交由spring管理对象，控制反转
//2.为代理对象添加@Aspect注解，启用spring aop
//3.在代理对象的具体方法前添加通知类型，通知类型包括以下五种
//  - @Before，前置通知
//  - @AfterReturning,后置通知
//  - @Around，环绕通知
//  - @AfterThrowing，异常通知
//  - @After,finally通知
// 4.给通知类型注解传value参数，参数为上面提到的切入点表达式，如@Before(value="execution(* com.aop.demo.controller.DemoController.before(..))")，增强DemoController类的before()方法。
@Aspect
@Component
public class AopDemo {

    @Before(value="execution(* com.aop.demo.controller.DemoController.before(..))")
    public void before(){
        System.out.println("--------------- before advice ------------------");
        System.out.println("i'm before advice");
    }

    @After(value="execution(* com.aop.demo.controller.DemoController.after(..))")
    public void after(){
        System.out.println("i'm after advice");
    }

    @Around(value="execution(* com.aop.demo.controller.DemoController.around(..))")
    public String around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        /**
         * 使用around advice时有一个点需要注意，原函数被装饰过了，原函数返回值被装饰函数覆盖，Controller中返回值无效
         */
        System.out.println("--------------- around advice ------------------");
        System.out.println("i'm around advice");
        proceedingJoinPoint.proceed();
        System.out.println("i'm after advice");
        return "around advice";
    }
}
