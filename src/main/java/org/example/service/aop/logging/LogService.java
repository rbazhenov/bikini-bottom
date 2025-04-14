package org.example.service.aop.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.service.aop.logging.annotation.MethodLogLevel;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
@Service
public class LogService {

    public void log(MethodLogLevel methodLogLevel, String message) {
        switch (methodLogLevel) {
            case INFO -> log.info(message);
            case DEBUG -> log.debug(message);
        }
    }

    public String startMessage(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Object[] args = joinPoint.getArgs();

        return "Start method " + method.getName() + "(). Parameters: " + Arrays.toString(args);
    }

    public String endMessage(Method method, Object returnValue) {
        return "Finish method " + method.getName() + "(). Result: " + returnValue;
    }
}
