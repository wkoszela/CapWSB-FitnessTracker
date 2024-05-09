package com.capgemini.wsb.fitnesstracker;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class LoggingAspect {

    @Around("within(com.capgemini.wsb.fitnesstracker.*.internal.*ServiceImpl+)")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("Before method: " + proceedingJoinPoint.getSignature());
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("After method: " + proceedingJoinPoint.getSignature());
        System.out.println("After return: " + proceed.toString());
        return proceed;

    }

}
