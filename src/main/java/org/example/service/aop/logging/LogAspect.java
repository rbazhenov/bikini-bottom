package org.example.service.aop.logging;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.AspectException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.service.aop.logging.annotation.LogMethodCall;
import org.example.service.aop.logging.annotation.MethodLogLevel;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Setter(onMethod = @__(@Autowired))
public class LogAspect {

    private LogService logService;

    @Around("org.example.service.aop.logging.LogMethodCallPointcut.logMethodCall()")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) {
        Object returnValue = null;
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        try {
            MethodLogLevel logLevel = method.getAnnotation(LogMethodCall.class).logLevel();
            String startMessage = logService.startMessage(joinPoint);

            logService.log(logLevel, startMessage);

            returnValue = proceed(joinPoint);
            String endMessage = logService.endMessage(method, returnValue);

            logService.log(logLevel, endMessage);
        } catch (Exception exception) {
            error(exception, method);
        }

        return returnValue;
    }

    private Object proceed(ProceedingJoinPoint joinPoint) {
        try {
            return joinPoint.proceed();
        } catch (RuntimeException exception) {
            log.error("JointPoint proceed error = {}", exception.getMessage());
            throw exception;
        } catch (Throwable throwable) {
            log.error("JointPoint proceed error = {}", throwable.getMessage());
            throw new AspectException("JointPoint proceed error", throwable);
        }
    }

    private void error(Exception exception, Method method) {
        log.error("Error while while log method {}. Message = {}", method.getName(), exception.getMessage());
    }
}
