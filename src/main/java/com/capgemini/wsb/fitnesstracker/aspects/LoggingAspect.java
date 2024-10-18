package com.capgemini.wsb.fitnesstracker.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void servicePointcut() {
        // @Service methods pointcut
    }

    @Before("servicePointcut()")
    public void beforeService(JoinPoint joinPoint) {

        String methodDetails = getMethodDetails(joinPoint);

        log.info("Running @Service method: {}", methodDetails);
    }

    @AfterReturning(value = "servicePointcut()", returning = "returnValue")
    public void afterService(JoinPoint joinPoint, Object returnValue) {

        String methodDetails = getMethodDetails(joinPoint);

        log.info("Finished @Service method: {} with the return value of: {}", methodDetails, returnValue);
    }


    /**
     * Return String with a format of: void className.methodName(args)
     *
     * @param joinPoint for method related information
     * @return String with proper format
     */
    private String getMethodDetails(JoinPoint joinPoint) {
        String returnType = joinPoint.getSignature().toString().split(" ")[0];
        String method = joinPoint.getSignature().toShortString().split("\\(")[0];
        StringBuilder argsString = new StringBuilder();
        var args = joinPoint.getArgs();
        for (Object arg : args) {
            argsString.append(arg.getClass().getSimpleName()).append(" ").append(arg).append(", ");
        }

        String argsDetails = !argsString.isEmpty()
                ? argsString.substring(0, argsString.length() - 2)
                : "";

        return returnType + " "
                + method + "("
                + argsDetails + ")";
    }
}
