package com.zy.homework2.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class Aop1 {

    @Before("pointCut()")
    public void startTransaction() {
        System.out.println("    ====>begin ding... ");
    }

    @After("pointCut()")
    public void commitTransaction() {
        System.out.println("    ====>finish ding... ");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("    ====>around begin ding");
        //调用process()方法才会真正的执行实际被代理的方法
        joinPoint.proceed();

        System.out.println("    ====>around finish ding");
    }

    @Pointcut("execution(* com.zy.homework2.aop.*.*(..))")
    public void pointCut() {
    }

}
