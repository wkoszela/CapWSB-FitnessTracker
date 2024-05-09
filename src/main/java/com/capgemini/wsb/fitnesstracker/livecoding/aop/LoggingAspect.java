package com.capgemini.wsb.fitnesstracker.livecoding.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(public String getName())")
    public void getNameAdvice() {
        log.info("Do something before getName is called");
    }

}
