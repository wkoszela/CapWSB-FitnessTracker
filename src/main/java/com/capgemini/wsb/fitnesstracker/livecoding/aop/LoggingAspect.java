package com.capgemini.wsb.fitnesstracker.livecoding.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    /*
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


    @Before("getMappingPointcut()")
    public void logBeforeGetMapping(JoinPoint joinPoint) {
        log.info("Method with @GetMapping called: " + joinPoint.getSignature()
                                                               .getName());
        log.info("Arguments passed: " + Arrays.toString(joinPoint.getArgs()));
        log.info("Target class: " + joinPoint.getTarget()
                                             .getClass()
                                             .getName());
    }


    @Around("getMappingPointcut()")
    public Object logAroundGetMapping(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        log.info("Method with @GetMapping called: " + joinPoint.getSignature()
                                                               .getName());

        // Proceed to the target method
        Object result = joinPoint.proceed();

        long elapsedTime = System.currentTimeMillis() - start;
        log.info("Method with @GetMapping finished: " + joinPoint.getSignature()
                                                                 .getName());
        log.info("Execution time: " + elapsedTime + " milliseconds.");

        return result;
    }
     */

}





