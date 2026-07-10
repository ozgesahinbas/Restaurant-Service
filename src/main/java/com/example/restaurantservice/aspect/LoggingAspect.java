package com.example.restaurantservice.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.example.restaurantservice.controller..*(..))")
    public void logRequest(JoinPoint joinPoint) {

        log.info(
                "Request - Method: {}, Arguments: {}",
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs())
        );
    }

    @Around("execution(* com.example.restaurantservice.service..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint)
            throws Throwable {

        long startTime = System.currentTimeMillis();


        Object result = joinPoint.getArgs() == null ? joinPoint.proceed()
                : joinPoint.proceed(joinPoint.getArgs());

        long executionTime =
                System.currentTimeMillis() - startTime;

        log.info(
                "Execution Time - Method: {}, Time: {} ms",
                joinPoint.getSignature().getName(),
                executionTime
        );
        return result;
    }

    @AfterThrowing(
            pointcut = "execution(* com.example.restaurantservice.service..*(..))",
            throwing = "exception"
    )
    public void logException(JoinPoint joinPoint, Throwable exception) {
        log.error(
                "Exception - Method: {}, Message: {}",
                joinPoint.getSignature().getName(),
                exception.getMessage()
        );
    }
}
