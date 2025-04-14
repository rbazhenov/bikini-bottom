package org.example.service.aop.logging;

import org.aspectj.lang.annotation.Pointcut;

public class LogMethodCallPointcut {

    @Pointcut("@annotation(org.example.service.aop.logging.annotation.LogMethodCall) && execution(public * *(..))")
    public void logMethodCall() {
    }
}
