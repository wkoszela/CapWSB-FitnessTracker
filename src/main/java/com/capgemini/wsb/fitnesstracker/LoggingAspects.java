package com.capgemini.wsb.fitnesstracker;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspects {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspects.class);

    @Around("within(com.capgemini.wsb.fitnesstracker..*ServiceImpl+)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Entering method: {}", methodName);
        Object result;
        try {
            result = joinPoint.proceed();
            logger.info("Exiting method: {}", methodName);
            if (joinPoint.getArgs().length > 0 && result != null) {
                logger.debug("Method {} returned result: {}", methodName, result);
            }
        } catch (Throwable throwable) {
            logger.error("Exception occurred in method {}: {}", methodName, throwable.getMessage());
            throw throwable;
        }
        return result;
    }
}
