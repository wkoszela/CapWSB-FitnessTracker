package com.capgemini.wsb.fitnesstracker.livecoding.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(public String getName())")
    public void getNameAdvice() {
        log.info("Do something before getName is called");
    }

    @After("execution(public String getName())")
    public void doSomethingAfterGetName() {
        log.info("Do something after getName is called");
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMappingPointcut() {
        // this is a Pointcut for all methods annotated with @GetMapping
    }

    @Before("getMappingPointcut()")
    public void logControllerMethod() {
        log.info("Do something before controller method is called");
    }

}





