package io.github.nvbao.springdemo.springioc.excercise_2.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // Log before executing any method in the "Service" package
    @Before("execution(* io.github.nvbao.springiocexample.excercise_2.service.*.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        System.out.println("[LOG] Before executing method: " + joinPoint.getSignature().getName());
    }

    // Record log after returning the results
    @AfterReturning(pointcut = "execution(* io.github.nvbao.springiocexample.excercise_2.service.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("[LOG] Method executed: " + joinPoint.getSignature().getName() + " | Return value: " + result);
    }
}
