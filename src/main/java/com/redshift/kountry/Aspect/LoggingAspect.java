package com.redshift.kountry.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
@Slf4j
public class LoggingAspect {
    @Before("execution(* com.redshift.kountry.*.*Controller.*(..))")
    public void logControllers(JoinPoint joinPoint) {
        String message = "invoked" + joinPoint.getSignature().getName();
        log.warn(message);
    }
}
